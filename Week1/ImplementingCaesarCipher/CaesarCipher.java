
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char currCharToUpper = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currCharToUpper);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (Character.isLowerCase(currChar)) {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, Character.toUpperCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char currCharToUpper = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currCharToUpper);
            if (idx != -1) {
                char newChar = '\0';
                if ((i + 1) % 2 == 0){
                    newChar = shiftedAlphabet2.charAt(idx);
                } else {
                    newChar = shiftedAlphabet1.charAt(idx);
                }
                
                if (Character.isLowerCase(currChar)) {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
}
