/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
        int index = myWords.indexOf(word.toLowerCase());
            if(index == -1) {
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
    }

    public void tester() {
        findUnique();
        
        for (int i = 0; i < myWords.size(); i++) {
            String word = myWords.get(i);
            int freq = myFreqs.get(i);
            System.out.println(freq + "\t" +word);
        }
        System.out.println("Number of unique words: " + myWords.size());
        
        
        int maxIndex = findIndexOfMax();
        System.out.println("Max Counts: " + myFreqs.get(maxIndex) + "\t" + "Word is " + myWords.get(maxIndex));
    }

    private int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i ++) {
            if(myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
