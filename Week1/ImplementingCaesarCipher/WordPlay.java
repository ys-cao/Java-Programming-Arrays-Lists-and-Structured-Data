
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        char chToLower = Character.toLowerCase(ch);
        if (chToLower == 'a' || chToLower == 'e' 
            || chToLower == 'i' || chToLower == 'o'
            || chToLower == 'u') {
                return true;
        } else {
                return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sbPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i)) == true) {
                sbPhrase.setCharAt(i, ch);
            }
        }
        String newPhrase = sbPhrase.toString();
        return newPhrase;
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sbPhrase = new StringBuilder(phrase);
         for (int i = 0; i < sbPhrase.length(); i++) {
            Character currCharLower = Character.toLowerCase(sbPhrase.charAt(i));
            
            if (currCharLower.equals(ch) && i % 2 == 0) {
                sbPhrase.setCharAt(i, '*');
            }
            else if (currCharLower.equals(ch) && i % 2 != 0) {
                sbPhrase.setCharAt(i, '+');
            }
        }
        String newPhrase = sbPhrase.toString();
        return newPhrase;
    }
}
