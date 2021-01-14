
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    private int[] countLetters(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] lettersCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char currChar = Character.toLowerCase(s.charAt(i));
            int index = alphabet.indexOf(currChar);
            if (index != -1) {
                lettersCount[index] += 1;
            }
        }
        return lettersCount;
    }

    private int maxIndex(int[] countLetters) {
        int maxDex = 0;
        for (int i = 0; i < countLetters.length; i++) {
            if (countLetters[i] > countLetters[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }
    
    public void SimpleTests() {
        FileResource fr = new FileResource();
        String str = fr.asString();
        int key = 23;
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(str);
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        
        String test = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        String result = breakCaesarCipher(test);
        System.out.println("breakCaesarCipher: " + result);
    }
    
    private String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
}
