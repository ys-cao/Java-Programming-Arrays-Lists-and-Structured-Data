
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int wordLength = word.length();
            for (int i = 0; i < wordLength; i++) {
                char currChar = word.charAt(i);
                if (i == 0 || i == wordLength - 1) {
                    if (!Character.isLetter(currChar)) {
                        wordLength--;
                    }
                }
            }
            
            if (wordLength < 30) {
                counts[wordLength]++;
            } else {
                counts[30]++;
            }
        }
        
        for (int j = 1; j < counts.length; j++) {
            if (counts[j] != 0) {
                System.out.println(counts[j] + " words of length " + j);
            }
        }
    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        
        int max = indexOfMax(counts);
        System.out.println("Max Count: " + max);
    }

    public int indexOfMax(int[] values) {
        int maxCount = 0;
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (maxCount < values[i]) {
                maxCount = values[i];
                index = i;
            }
        }
        return index;
    }


}
