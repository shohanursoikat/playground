public class Ship{
    public static void main(String[] args){
        int load = 33;
        int goods = load /4;
        int lose = load % 4;
        System.out.println("Goods: " + goods + " Lose: " + lose);
    }
}