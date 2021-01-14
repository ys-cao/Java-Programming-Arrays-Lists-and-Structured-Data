
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encryptTwoKeys(String input) {
        StringBuilder encrypted = new StringBuilder(input);
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
    
    public String decrypt(String encrypted) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return cct.encryptTwoKeys(encrypted);
    }
}
