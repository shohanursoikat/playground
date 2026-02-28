# Simple Microcoded CPU Simulator
# This code will run in the notebook and show a step-by-step simulation of a tiny CPU
# that executes high-level instructions via a microcode control store.
#
# - CPU has registers R0-R3, PC, MAR, MDR, FLAGS
# - Memory is a list of "instructions/data".
# - Each instruction is an (opcode, *operands) tuple. For simplicity we store them in memory.
# - Microcode maps opcode -> list of micro-operations (each micro-op is a callable).
# - We'll print a detailed trace showing microinstructions firing and CPU state changes.
#
# Run this cell to see the simulation and example program.
from dataclasses import dataclass, field
from typing import List, Tuple, Dict, Callable, Any

@dataclass
class CPUState:
    R: List[int] = field(default_factory=lambda: [0,0,0,0])  # R0..R3
    PC: int = 0
    MAR: int = 0
    MDR: Any = None
    FLAGS: Dict[str,bool] = field(default_factory=lambda: {"Z":False, "C":False})
    running: bool = True
    microstep: int = 0

    def __repr__(self):
        regs = " ".join(f"R{i}={self.R[i]}" for i in range(len(self.R)))
        flags = " ".join(f"{k}={int(v)}" for k,v in self.FLAGS.items())
        return f"PC={self.PC} {regs} {flags} MDR={self.MDR} MAR={self.MAR}"

# Micro-op type
MicroOp = Callable[['CPUState', Tuple], None]

class MicrocodedCPU:
    def __init__(self, memory: List[Tuple]):
        self.memory = memory  # list of instructions or data
        self.cpu = CPUState()
        self.trace: List[str] = []
        # Control store: opcode -> list of micro-ops (each micro-op is a tuple (func, args...))
        self.control_store: Dict[str, List[Tuple[MicroOp, Tuple]]] = {}
        self._build_microcode()
    
    # Helper micro-op implementations
    def m_FETCH(self, cpu: CPUState, instr_context):
        # Place instruction from memory[PC] into MDR and increment PC in a separate microop
        cpu.MDR = self.memory[cpu.PC]
        self.trace.append(f"micro: FETCH -> MDR = {cpu.MDR}")
    
    def m_INC_PC(self, cpu: CPUState, instr_context):
        cpu.PC += 1
        self.trace.append(f"micro: INC_PC -> PC = {cpu.PC}")
    
    def m_DECODE(self, cpu: CPUState, instr_context):
        # DECODING is conceptual here. We set MAR to the decoded instruction (for visibility).
        cpu.MAR = cpu.MDR[0]  # opcode name as MAR for trace
        self.trace.append(f"micro: DECODE -> opcode = {cpu.MDR[0]}")
    
    def m_LOAD_IMM(self, cpu: CPUState, instr_context):
        # instr_context contains the full instruction tuple stored earlier
        _, reg_idx, imm = instr_context
        cpu.R[reg_idx] = imm
        cpu.MDR = imm
        self.trace.append(f"micro: LOAD_IMM -> R{reg_idx} = {imm}")
    
    def m_LOAD_REG(self, cpu: CPUState, instr_context):
        _, dest, src = instr_context
        cpu.R[dest] = cpu.R[src]
        cpu.MDR = cpu.R[src]
        self.trace.append(f"micro: LOAD_REG -> R{dest} = R{src} ({cpu.R[src]})")
    
    def m_ALU_ADD(self, cpu: CPUState, instr_context):
        _, dest, a, b = instr_context
        res = cpu.R[a] + cpu.R[b]
        cpu.MDR = res
        cpu.FLAGS["Z"] = (res == 0)
        cpu.FLAGS["C"] = (res > 0xFFFFFFFF)
        self.trace.append(f"micro: ALU_ADD -> R{a}({cpu.R[a]}) + R{b}({cpu.R[b]}) = {res}")
    
    def m_ALU_SUB(self, cpu: CPUState, instr_context):
        _, dest, a, b = instr_context
        res = cpu.R[a] - cpu.R[b]
        cpu.MDR = res
        cpu.FLAGS["Z"] = (res == 0)
        cpu.FLAGS["C"] = (res < 0)
        self.trace.append(f"micro: ALU_SUB -> R{a}({cpu.R[a]}) - R{b}({cpu.R[b]}) = {res}")
    
    def m_WRITEBACK_REG(self, cpu: CPUState, instr_context):
        # write MDR to destination register
        _, dest = instr_context
        cpu.R[dest] = cpu.MDR
        self.trace.append(f"micro: WRITEBACK_REG -> R{dest} = {cpu.MDR}")
    
    def m_STORE_MEM(self, cpu: CPUState, instr_context):
        _, src, addr = instr_context
        self.memory[addr] = cpu.R[src]
        cpu.MDR = cpu.R[src]
        self.trace.append(f"micro: STORE_MEM -> MEM[{addr}] = R{src} ({cpu.R[src]})")
    
    def m_LOAD_MEM(self, cpu: CPUState, instr_context):
        _, dest, addr = instr_context
        val = self.memory[addr]
        cpu.R[dest] = val
        cpu.MDR = val
        self.trace.append(f"micro: LOAD_MEM -> R{dest} = MEM[{addr}] ({val})")
    
    def m_JUMP(self, cpu: CPUState, instr_context):
        _, addr = instr_context
        cpu.PC = addr
        self.trace.append(f"micro: JUMP -> PC = {addr}")
    
    def m_HALT(self, cpu: CPUState, instr_context):
        cpu.running = False
        self.trace.append("micro: HALT -> CPU stopped")
    
    # Build a small control store (microcode)
    def _build_microcode(self):
        # Each opcode maps to an ordered list of micro-ops.
        # Micro-ops are tuples (function, (instruction tuple coming from MDR))
        self.control_store = {
            # LOADI Rn, imm  -> fetch immediate into register
            "LOADI": [
                (MicrocodedCPU.m_FETCH, ()),   # put instruction in MDR
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_LOAD_IMM, ())
            ],
            # MOV Rdest, Rsrc -> copy register
            "MOV": [
                (MicrocodedCPU.m_FETCH, ()) ,
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_LOAD_REG, ())
            ],
            # ADD Rdest, Ra, Rb -> perform ALU add then writeback
            "ADD": [
                (MicrocodedCPU.m_FETCH, ()) ,
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_ALU_ADD, ()) ,
                (MicrocodedCPU.m_WRITEBACK_REG, ())
            ],
            "SUB": [
                (MicrocodedCPU.m_FETCH, ()) ,
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_ALU_SUB, ()) ,
                (MicrocodedCPU.m_WRITEBACK_REG, ())
            ],
            # STORE Rsrc, addr
            "STORE": [
                (MicrocodedCPU.m_FETCH, ()) ,
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_STORE_MEM, ())
            ],
            # LOAD Rdest, addr
            "LOAD": [
                (MicrocodedCPU.m_FETCH, ()) ,
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_LOAD_MEM, ())
            ],
            # JMP addr
            "JMP": [
                (MicrocodedCPU.m_FETCH, ()) ,
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_JUMP, ())
            ],
            "HALT": [
                (MicrocodedCPU.m_FETCH, ()) ,
                (MicrocodedCPU.m_INC_PC, ()) ,
                (MicrocodedCPU.m_DECODE, ()) ,
                (MicrocodedCPU.m_HALT, ())
            ]
        }
    
    def run(self, max_cycles=100):
        cpu = self.cpu
        cycles = 0
        while cpu.running and cycles < max_cycles:
            # fetch instruction at PC to determine opcode (we simulate an initial quick fetch here to choose microcode)
            instr = self.memory[cpu.PC]
            opcode = instr[0]
            if opcode not in self.control_store:
                self.trace.append(f"ERROR: Unknown opcode '{opcode}' at PC={cpu.PC}")
                break
            micro_sequence = self.control_store[opcode]
            # Execute micro-ops for this opcode. Micro-ops expect the full instruction as context.
            for micro_op_tuple in micro_sequence:
                func, _ = micro_op_tuple
                # Call with (self, cpu, instr) signature by binding method correctly
                func(self, cpu, instr)
                cpu.microstep += 1
            cycles += 1
            self.trace.append(f"--- End of instruction cycle. CPU State: {cpu}")
        if cpu.running:
            self.trace.append("WARNING: Max cycles reached or CPU didn't halt cleanly.")
        return self.trace

# Example program: compute R0 = 3 + 4, store to MEM[10], halt
# We'll place instructions in memory indices 0..
program = [
    ("LOADI", 0, 3),    # R0 = 3
    ("LOADI", 1, 4),    # R1 = 4
    ("ADD",   2, 0, 1), # R2 = R0 + R1
    ("STORE", 2, 10),   # MEM[10] = R2
    ("LOAD",  3, 10),   # R3 = MEM[10]
    ("HALT",)           # stop
]

cpu_sim = MicrocodedCPU(program)
trace = cpu_sim.run(max_cycles=50)

# Print the microcode control store (human readable)
print("CONTROL STORE (microcode) overview:\n")
for op, seq in cpu_sim.control_store.items():
    print(f"{op}: {[s[0].__name__ for s in seq]}")
print("\n--- SIMULATION TRACE ---\n")
for line in trace:
    print(line)

print("\n--- FINAL CPU STATE ---")
print(cpu_sim.cpu)
print("\n--- MEMORY SNAPSHOT (addresses 0..15) ---")
for i in range(16):
    v = cpu_sim.memory[i] if i < len(cpu_sim.memory) else None
    print(f"[{i:02d}] = {v}")
