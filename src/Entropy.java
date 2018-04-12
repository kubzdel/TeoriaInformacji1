import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Entropy {
    private String sourceText;
    private String[] words;
    private Random random;
    private HashMap<Character,Integer> charMap;
    private HashMap<ArrayList<String>,Integer> charMapN;
    public Entropy() throws FileNotFoundException {
        charMap = new HashMap<Character, Integer>();
        charMapN = new HashMap<ArrayList<String>, Integer>();
        Scanner scanner = new Scanner( new File("norm_wiki_en.txt") );
        sourceText = scanner.useDelimiter("\\A").next();
        words = getWords(sourceText);

    }


    public void charEntropy ()
    {
        charMap = new HashMap<Character, Integer>();
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
        System.out.println("Entropia dla znaków rzędu 0 wynosi "+ entropy);
    }

    public String[] getWords(String text) {
        String words[] = text.split(" ");
        return words;
    }

    public void wordEntropy()
    {
        HashMap<String,Integer> wordMap = new HashMap();
        for(int i=0;i<words.length;i++)
        {
            if(!wordMap.containsKey(words[i]))
                wordMap.put(words[i],1);
            else{
                int currVal = wordMap.get(words[i]);
                wordMap.replace(words[i], currVal + 1);}
        }
        double entropy=0;
        for (Integer value : wordMap.values()) {
            double prob = value/(double)words.length;
            entropy -=prob *( Math.log(prob)/Math.log(2));
        }
        System.out.println("Entropia dla słów rzędu 0 wynosi "+ entropy);
    }


    public void wordEntropyN (int rank) {
        HashMap<ArrayList<String>, Integer> nGramProb = new HashMap<>();
        HashMap<ArrayList<ArrayList<String>>,Integer> wordMapN = new HashMap();
        for (int i = 0; i < words.length - rank; i++) {
            ArrayList key= new ArrayList();
            for(int k =i;k<i+rank;k++)
            {
                key.add(words[k]);
            }
            if (!nGramProb.containsKey(key)) {
                nGramProb.put(key, 1);
            } else {
                int currVal = nGramProb.get(key);
                nGramProb.replace(key, currVal + 1);
            }
            ArrayList<String> key2 = new ArrayList<>();
            key2.add(words[i+rank]);
            ArrayList<ArrayList<String>> keys = new ArrayList();
            keys.add(key);
            keys.add(key2);
            if (!wordMapN.containsKey(keys)) {
                wordMapN.put(keys, 1);
            } else {
                int currVal = wordMapN.get(keys);
                wordMapN.replace(keys, currVal + 1);
            }
        }
        double entropy = 0;
        for (HashMap.Entry<ArrayList<ArrayList<String>>, Integer> entry : wordMapN.entrySet()) {
            double probX = nGramProb.get(entry.getKey().get(0)) / ((double) (words.length - rank));
            double probXY = entry.getValue() / ((double) words.length - rank);
            entropy -= probXY * (Math.log(probXY / probX) / Math.log(2));
        }
        System.out.println("Entropia dla słów rzędu "+rank+" wynosi "+ entropy);
    }

    public void charEntropyN (int rank) {
            HashMap<String, Integer> nGramProb = new HashMap<>();
            charMapN = new HashMap<ArrayList<String>, Integer>();
            for (int i = 0; i < sourceText.length() - rank; i++) {
                String key = sourceText.substring(i, i + rank);
                if (!nGramProb.containsKey(key)) {
                    nGramProb.put(key, 1);
                } else {
                    int currVal = nGramProb.get(key);
                    nGramProb.replace(key, currVal + 1);
                }
                String key2 = String.valueOf(sourceText.charAt(i + rank));
                ArrayList<String> keys = new ArrayList();
                keys.add(key);
                keys.add(key2);
                if (!charMapN.containsKey(keys)) {
                    charMapN.put(keys, 1);
                } else {
                    int currVal = charMapN.get(keys);
                    charMapN.replace(keys, currVal + 1);
                }
            }
            double entropy = 0;
            for (HashMap.Entry<ArrayList<String>, Integer> entry : charMapN.entrySet()) {
                double probX = nGramProb.get(entry.getKey().get(0)) / ((double) (sourceText.length() - rank));
                double probXY = entry.getValue() / ((double) sourceText.length() - rank);
                entropy -= probXY * (Math.log(probXY / probX) / Math.log(2));
            }
            System.out.println("Entropia dla znaków rzędu "+rank+" wynosi "+ entropy);
        }


        public void processFiles(int maxRank) throws FileNotFoundException {
            File folder = new File("files/");
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Scanner scanner = new Scanner(file);
                    sourceText = scanner.useDelimiter("\\A").next();
                    words = getWords(sourceText);
                    System.out.println(file.getName());
                    charEntropy();
                    for(int i=1;i<=maxRank;i++)
                    {
                        charEntropyN(i);
                    }
                    wordEntropy();
                    for(int i=1;i<=maxRank;i++)
                    {
                        wordEntropyN(i);
                    }

                    System.out.println();


                }
            }
        }




}
