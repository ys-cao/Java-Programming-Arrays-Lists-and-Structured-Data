
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        } else {
            int freq = counts.get(index);
            counts.set(index, freq+1);
        }
    }
    
    private void findAllCharacters() {
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int firstPeriodIndex = line.indexOf(".");
            if (firstPeriodIndex != -1) {
                String name = line.substring(0, firstPeriodIndex);
                update(name);
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        
        int freq = 1;
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) > freq) {
                System.out.println(counts.get(i) + "\t" + names.get(i));
            }
        }
        System.out.println("----------------------------------");

        int num1 = 10;
        int num2 = 15;
        charactersWithNumParts(num1, num2);
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        if (num1 > num2) System.out.println("Invalid numbers.");
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(counts.get(i) + "\t" + names.get(i));
            }
        }
    }
}
