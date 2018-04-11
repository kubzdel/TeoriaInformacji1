import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Entropy {
    private String sourceText;
    private Random random;
    private HashMap<Character,Integer> charMap;
    private HashMap<ArrayList<Character>,Integer> charMapN;
    public Entropy() throws FileNotFoundException {
        charMap = new HashMap<Character, Integer>();
        charMapN = new HashMap<ArrayList<Character>, Integer>();
        Scanner scanner = new Scanner( new File("norm_wiki_en.txt") );
        sourceText = scanner.useDelimiter("\\A").next();
    }


    public void charEntropy (int rank)
    {
        for(int i=0;i<sourceText.length();i++)
        {
            if(!charMap.containsKey(sourceText.charAt(i)))
            charMap.put(sourceText.charAt(i),1);
            else{
            int currVal = charMap.get(sourceText.charAt(i));
            charMap.replace(sourceText.charAt(i), currVal + 1);}

        }
        double entropy=0;
        for (Integer value : charMap.values()) {
            double prob = value/(double)sourceText.length();
            entropy -=prob *( Math.log(prob)/Math.log(2));
        }
        System.out.println(entropy);
    }

    public void charEntropyN (int rank) {

        for (int i = 0; i < sourceText.length() - 1; i++) {
            if (i > 0) {
                ArrayList<Character> keys = new ArrayList<>();
                for (int k = 0; k <= rank; k++) {
                    keys.add(sourceText.charAt(i - k));
                    if (!charMapN.containsKey(keys)) {
                        charMapN.put(keys, 0);
                    } else {
                        int currVal = charMapN.get(keys);
                        charMapN.replace(keys, currVal + 1);
                    }
                }
            }

        }
        double entropy = 0;
        for (Integer value : charMap.values()) {
            double prob = value / (double) sourceText.length();
            entropy -= prob * (Math.log(prob) / Math.log(2));
        }
        System.out.println(entropy);
    }
}
