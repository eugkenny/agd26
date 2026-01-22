import java.io.*;
import java.util.Scanner;

public class MapTest {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/GreatExpectations.txt");

        Scanner sc = new Scanner(file);

        LinearProbingHashMap<String,Integer> map = new LinearProbingHashMap<>(101);

        int count = 0;
        while(sc.hasNext()){
            map.put(sc.next(),0);
            count++;
        }
        System.out.println("Total words:" + count);
        System.out.println("Distinct words: " + map.size());
    }
}
