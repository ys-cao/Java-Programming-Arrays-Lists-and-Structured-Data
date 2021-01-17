
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsMap;
    
    public WordsInFiles() {
        wordsMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for (String word : fr.words()) {
            if (!wordsMap.containsKey(word)) {
                ArrayList<String> currArray = new ArrayList<String>();
                currArray.add(fileName);
                wordsMap.put(word, currArray);
            } else {
                ArrayList<String> currArray = wordsMap.get(word);
                if (!currArray.contains(fileName)) {
                    currArray.add(fileName);
                    wordsMap.put(word, currArray);
                }
            }
        }
    }
    
    private void buildWordFileMap() {
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        // return max number of files anuy word appears in.
        int maxNumber = 0;
        for (String word : wordsMap.keySet()) {
            int currNumber = wordsMap.get(word).size();
            if (maxNumber < currNumber) {
                maxNumber = currNumber;
            }
        }
        return maxNumber;
    }
    
    private ArrayList<String> wordsInNumbFiles(int number) {
        ArrayList<String> wordsInFiles = new ArrayList<String>();
        for (String word : wordsMap.keySet()) {
            if (wordsMap.get(word).size() == number) {
                wordsInFiles.add(word);
            }
        }
        return wordsInFiles;
    }
    
    private void printFilesIn (String word) {
        ArrayList<String> fileNames = wordsMap.get(word);
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println(fileNames.get(i) + "\n");
        }
    }
    
    public void tester() {
        buildWordFileMap();
        System.out.println("Max Number of Files any words is in: " + maxNumber());
        
        for (String word : wordsMap.keySet()) {
            ArrayList<String> currArray = wordsMap.get(word);
            if (currArray.size() == maxNumber()) {
                System.out.println("Word: " + word + "\n" + "Files in: ");
                for (int i = 0; i < currArray.size(); i++) {
                    System.out.println(currArray.get(i));
                }
                System.out.println("\n");
            }
        }
    }
}
