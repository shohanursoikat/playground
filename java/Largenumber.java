public class Largenumber{
public static void main(String[] args) {
    int first = 4;
    int second = 55;
    int diff;
    if (first < second) {
        diff = first - second;
        System.out.println("Diff: "+diff);
    }else if(second < first){
        diff = second - first;
        System.out.println("Diff: "+diff);
    }else{
        System.out.println("Numbers are equal.");
    }
}
} 