import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class English {
    public char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','w','x','y','z',' ' };
    private String dictionary;
    private String sourceText;
    private Random random;

    public void prepare() throws FileNotFoundException {
        Scanner scanner = new Scanner( new File("norm_hamlet.txt") );
        sourceText = scanner.useDelimiter("\\A").next();
        random = new Random();
    }
    public void firstRank(int n){
        dictionary="probability";
        for(int k=0;k<n;k++) {
            char lastChar = dictionary.charAt(dictionary.length() - 1);
            char nextChar = letters[random.nextInt(letters.length)];
            boolean success = false;
            while(success==false)
            {
                int letterPos = random.nextInt(sourceText.length());
                for (int i = letterPos; i < sourceText.length(); i++) {
                    if (sourceText.charAt(i) == lastChar) {
                        nextChar = sourceText.charAt(i + 1);
                        success=true;
                        break;
                    }
                }
            }
            dictionary = dictionary + nextChar;
        }
        System.out.println(dictionary);
        System.out.println("Srednia dlugosc wyrazu:"+countWords(dictionary));
    }

    public void thirdRank(int n){
        dictionary="probability";
        for(int k=0;k<n;k++) {
            String lastThree = dictionary.substring(dictionary.length()-3);
            char nextChar = letters[random.nextInt(letters.length)];
            boolean success = false;
            int o=0;
            while(success==false && o<20)
            {
                int letterPos = random.nextInt(sourceText.length());
                String pom = sourceText.substring(letterPos);
                int index = pom.indexOf(lastThree);
                if(index >-1)
                {
                    success=true;
                    nextChar = pom.charAt(index+3);
                }
                o++;
            }
            dictionary = dictionary + nextChar;
        }
        System.out.println(dictionary);
        System.out.println("Srednia dlugosc wyrazu:"+countWords(dictionary));
    }

    public void fifthRank(int n){
        dictionary="probability";
        for(int k=0;k<n;k++) {
            String lastThree = dictionary.substring(dictionary.length()-5);
            char nextChar = letters[random.nextInt(letters.length)];
            boolean success = false;
            int o=0;
            while(success==false && o<20)
            {
                int letterPos = random.nextInt(sourceText.length());
                String pom = sourceText.substring(letterPos);
                int index = pom.indexOf(lastThree);
                if(index >-1)
                {
                    success=true;
                    nextChar = pom.charAt(index+5);
                }
                o++;
            }
            dictionary = dictionary + nextChar;
        }
        System.out.println(dictionary);
        System.out.println("Srednia dlugosc wyrazu:"+countWords(dictionary));
    }

    public  float countWords(String text)
    {
        float count=0;
        for(int i=0;i<text.length()-2;i++)
        {
            if(text.charAt(i)!=' ' && text.charAt(i+1)==' ')
            {
                count++;
            }
        }
        return text.length()/count;
    }
}
