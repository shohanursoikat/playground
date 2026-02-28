public class Test{
    public static void main(String[] args) {
        Counter c1=new Counter();
        Counter c2=new Counter();
        Counter c3=new Counter();
        System.out.println(Counter.getCount());
    }
}

class Counter{
    static int count=0;
    public Counter(){
        count++;
        
    }
    static int getCount(){
        return count;
    }

}Hello Java
what's up bro....
