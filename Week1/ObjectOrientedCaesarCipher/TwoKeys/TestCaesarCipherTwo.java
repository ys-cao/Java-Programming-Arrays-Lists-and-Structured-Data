
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
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
    
    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        if (start == 0 || start == 1) {
            for (int i = start; i < message.length(); i += 2) {
                char currChar = message.charAt(i);
                sb.append(currChar);
            }
        } else {
            System.out.println("Please use 0 or 1 for start value.");
        }
        return sb.toString();
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String str = fr.asString();
        int key1 = 21;
        int key2 = 18;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        String encrypted = cct.encryptTwoKeys(str);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = cct.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        String test = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        System.out.println("Decrypted Break: " + breakCaesarCipher(test));
    }
    
    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    private String breakCaesarCipher(String encrypted) {
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Found Two Keys: " + key1 + " & " + key2 +".");
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        String decrypted = cct.encryptTwoKeys(encrypted);
        return decrypted;
    }
        
}
