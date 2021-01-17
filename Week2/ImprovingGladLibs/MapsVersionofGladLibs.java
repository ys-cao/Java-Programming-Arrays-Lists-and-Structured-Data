
/**
 * Write a description of MapsVersionofGladLibs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
import java.lang.Sting.*;

public class MapsVersionofGladLibs {
    private ArrayList<String> wordSeen;
    private int wordSeenCount = 0;
    private HashMap<String,ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
    private Random myRandom;
    private ArrayList<String> usedCategories;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public MapsVersionofGladLibs() {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordSeen = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    public void GladLibMap(String source) {
        initializeFromSource(source);
        wordSeen = new ArrayList<String>();
        myRandom = new Random();
        usedCategories = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective","animal","color","country","fruit","name","noun","timeframe","verb"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    
    private String randomFrom(ArrayList<String> Source) {
          int index = myRandom.nextInt(Source.size());
          return Source.get(index);
        
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        addUsedCategory(label);
        return randomFrom(myMap.get(label));
    }
    
    private void addUsedCategory(String label) {
       if (usedCategories.indexOf(label) == -1){
           usedCategories.add(label);
        }
    }
    
    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;
        while(true){
            sub = getSubstitute(w.substring(first+1,last));
            int findIndex = wordSeen.indexOf(sub);
           
            if(!wordSeen.contains(sub)){
             wordSeen.add(sub);
             wordSeenCount+=1;
             break;
            }
        } 
        
        return prefix+sub+suffix;
     } 
              
    private int totalWordsInMap() {
       int sum = 0;      
       for (String category : myMap.keySet()) {
           ArrayList<String> words = myMap.get(category);
           System.out.println("Category:" +category+ "\tTotal words in category:"
            + words.size());
           sum += words.size();
        }
       System.out.println("Lists size: "+sum);
       return sum;
       
    }
    
  
    private int totalWordsConsidered() {
        ArrayList<String> content = new ArrayList<String>();
        int sum = 0;
        System.out.println("\nCategories used in this story:");
        for (int index = 0; index < usedCategories.size(); index++) {
            String category = usedCategories.get(index);
            content = myMap.get(category);
            System.out.println("Category: " + category + "\tWords in category: "
                    + content.size());
            sum += content.size();
        }
        System.out.println("sum of possible words: " + sum);
        return sum;
    }
    
    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for(String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        System.out.println("Total numbers of words replaced "+ wordSeenCount);
        return story;
    }
    
    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()) {
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory() {
        wordSeen.clear();
        System.out.println("\n");
        String story = fromTemplate("file.txt");
        printOut(story, 60);
        wordSeenCount = 0;
        System.out.println("\n");
        System.out.println(totalWordsInMap());
        System.out.println("\n"+totalWordsConsidered());
    }
}
