
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codons;
    
    public CodonCount() {
        codons = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        codons.clear();
        for (int i = start; (i+3) < dna.length()+1; i += 3) {
            String codon = dna.substring(i, i + 3);  
            if (codons.containsKey(codon)) {
                codons.put(codon, codons.get(codon) + 1);
            } else {
                codons.put(codon, 1);
            }
        }
        System.out.println("Reading frame starting with " + start 
                            + " results in " + codons.size() + " unique codons");
    }
    
    private String getMostCommonCodon() {
        int maxCount = 0;
        String maxCodon = "";
        for (String key : codons.keySet()) {
            if (maxCount < codons.get(key)) {
                maxCount = codons.get(key);
                maxCodon = key;
            }
        }
        return maxCodon;
    }
    
    private void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are: ");
        for (String key : codons.keySet()) {
            int counts = codons.get(key);
            if (counts >= start && counts <= end) {
                System.out.println(key + "\t" + counts);
            }
        }
        System.out.println("\n");
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        int start = 1;
        int end = 5;
        for (int i = 0; i < 3; i ++) {
            buildCodonMap(i, dna);
            
            String mostCommonCodon = getMostCommonCodon();
            int countMostCommonCodon = codons.get(mostCommonCodon);
            System.out.println("and most common codon is " + mostCommonCodon 
                                + " with count " + countMostCommonCodon +".");

            printCodonCounts(start, end);
        }
    }
}
