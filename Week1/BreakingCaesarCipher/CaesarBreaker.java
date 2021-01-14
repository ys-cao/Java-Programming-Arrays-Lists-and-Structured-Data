
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String s) {
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

    public int maxIndex(int[] countLetters) {
        int maxDex = 0;
        for (int i = 0; i < countLetters.length; i++) {
            if (countLetters[i] > countLetters[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }

    public void testDecrypt() {
        String encrypted = "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";
        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    public String halfOfString(String message, int start) {
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
    
    public void testHalfOfString() {
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        int start = 0;
        System.out.println("Halved message = " + halfOfString(message, start));
        
        message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        start = 1;
        System.out.println("Halved message = " + halfOfString(message, start));
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return dkey;
    }
    
    public void decrpytTwoKeys () {
        String encrypted = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Found Two Keys: " + key1 + " & " + key2 +".");
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println("Decrypted: " + decrypted);
    }
}
