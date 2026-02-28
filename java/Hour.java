public class Hour{
    public static void main(String[] args) {
        int sec = 360077;
        int hour = sec / 3600;
        int remin = sec % 3600;
        int min = remin / 60;
        int resec = remin % 60;

        int second = resec % 60;
    
        System.out.println("Hour: " + hour + " Minute: " + min + " Second: " + second);
    }
}