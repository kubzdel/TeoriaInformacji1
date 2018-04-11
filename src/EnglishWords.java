import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class EnglishWords {
    public char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'x', 'y', 'z', ' '};
    private String sourceText;
    private Random random;
    private HashMap<String, Integer> dictionary;
    private HashMap<String, ArrayList<String>>  firstRank;
    private HashMap<ArrayList<String>, ArrayList<String>> secondRank;
    private int topWords;

    public void prepare() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("norm_wiki_sample.txt"));
        sourceText = scanner.useDelimiter("\\A").next();
        random = new Random();
        dictionary = new HashMap<>();
        firstRank = new HashMap<>();
        secondRank = new HashMap<>();
    }


    public String[] getWords(String text) {
        String words[] = text.split(" ");
        return words;
    }


    public void fillFirstRank(String[] words)
    {
        for (int i = 0; i < words.length; i++) {

            if (!firstRank.containsKey(words[i])) {
                firstRank.put(words[i],new ArrayList<String>());
            }
            if(i>0) {
                firstRank.get(words[i - 1]).add(words[i]);
            }
        }
    }

    public void fillSecondRank(String[] words)
    {
        for (int i = 0; i < words.length-1; i++) {
            if(i>0) {
                ArrayList<String> keys = new ArrayList<>();
                keys.add(words[i-1]);
                keys.add(words[i]);
                if(!secondRank.containsKey(keys))
                {
                    secondRank.put(keys,new ArrayList<String>());
                }
                    secondRank.get(keys).add(words[i + 1]);
            }
        }
    }

    public void approxFirstRank(int n)
    {
        ArrayList<String> approx = new ArrayList<>();
        approx.add("probability");
        for(int k=0;k<n;k++) {
            String lastWord = approx.get(approx.size()-1);
            ArrayList<String> wordsList = firstRank.get(lastWord);
            String newWord = wordsList.get(random.nextInt(wordsList.size()));
            approx.add(newWord);
        }

        System.out.println(approx);
    }

    public void approxSecondRank(int n)
    {
        ArrayList<String> approx = new ArrayList<>();
        approx.add("high");
        approx.add("probability");
        for(int k=0;k<n;k++) {
            ArrayList<String> lastWords = new ArrayList<>();
            lastWords.add(approx.get(approx.size()-2));
            lastWords.add(approx.get(approx.size()-1));
            ArrayList<String> wordsList = secondRank.get(lastWords);
            String newWord = wordsList.get(random.nextInt(wordsList.size()));
            approx.add(newWord);
        }

        System.out.println(approx);
    }
    public void countWords(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (dictionary.containsKey(words[i])) {
                   int currVal = dictionary.get(words[i]);
                   dictionary.replace(words[i],currVal+1);
            }
            else
            {
                dictionary.put(words[i],1);
            }
        }
    }


    public void process()
    {
//        countWords(getWords(sourceText));
//        System.out.println(dictionary.size());
//        sortByValues(dictionary);
//        zad1();
     //   fillRanks(getWords(sourceText));



    }

    public void prepareFirstRank()
    {
        fillFirstRank(getWords(sourceText));
    }

    public void prepareSecondRank()
    {
        fillSecondRank(getWords(sourceText));
    }

    public void zad1()
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(dictionary.values());
        list.sort(Comparator.reverseOrder());
        double words=0;
        for(int i=0;i<6000;i++)
        {
            words+=list.get(i);
        }
        System.out.println(sourceText);
        double totalWords = getWords(sourceText).length;
        System.out.println(words/totalWords);

    }

    public void countProbabilities()
    {

    }


    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }

        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}