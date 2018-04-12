import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {
English english = new English();
EnglishWords englishWords = new EnglishWords();
Entropy entropy = new Entropy();
        try {
//            english.prepare();
//            english.firstRank(10000);
//            english.thirdRank(2000);
//            english.fifthRank(1000);

            englishWords.prepare();

//            englishWords.prepareFirstRank();
//            englishWords.approxFirstRank(50);
//
//            englishWords.prepareSecondRank();
//            englishWords.approxSecondRank(50);

          //  entropy.charEntropy(2);
//            entropy.wordEntropy();
//            entropy.wordEntropyN(2);
            entropy.processFiles(4);




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
