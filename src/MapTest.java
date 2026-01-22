import java.io.*;
import java.util.Scanner;

public class MapTest {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/GreatExpectations.txt");

        Scanner sc = new Scanner(file);

        LinearProbingHashMap<String,Integer> map = new LinearProbingHashMap<>(101);
        //SeperateChainingHashMap<String, Integer> map = new SeperateChainingHashMap<>(101);

        while (sc.hasNext()){
            System.out.println(sc.next());
        }
    }
}
