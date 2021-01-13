import edu.duke.*;
// import java.lang.*;

public class CaesarBreaker() {
    public int[] countLetters(String s) {
        String alphabet = "abcdefghcjklmnopqrstuvwxyz";
        int[] lettersCount = new int[26];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char currChar = Character.toLowerCase(s.charAt(i));
            index = alphabet.indexOf(currChar);
            if (index != -1) {
                lettersCount[index]++;
            }
        }
        return lettersCount;
    }

    public int maxIndex(int[] countLetters) {
        int temp = 0;
        int index = 0;
        for (int i = 0; i < countLetters.length; i++) {
            int currCount = countLetters[i];
            if (temp < currCount) {
                temp = currCount;
                index = i;
            }
        }
        return index;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public void testDecrypt() {
        String encrypted = "";
        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        if (start == 0 || start == 1) {
            for (int = start; start <= message.length; start += 2) {
                char currChar = message.charAt(start);
                sb.append(currChar);
            }
        } else {
            System.out.println("Please use 0 or 1 for start value."")
        }
        String str = sb.toString();
        return str;
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

    public void decrpytTwoKeys (String encrypted) {
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Found Two Keys: " + key1 + " & " + key2 +".");

        CaesarCipher cc = new CaesarCiper();
        String decrypted = cc.encryptTwoKeys(encrypted, key1, key2);
        System.out.println("Decrypted: " + decrypted);
    }
}
