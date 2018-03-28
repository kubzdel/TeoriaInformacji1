import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String args[])
    {
English english = new English();
EnglishWords englishWords = new EnglishWords();
        try {
//            english.prepare();
//            english.firstRank(10000);
//            english.thirdRank(2000);
//            english.fifthRank(1000);

            englishWords.prepare();

            englishWords.prepareFirstRank();
            englishWords.approxFirstRank(50);

            englishWords.prepareSecondRank();
            englishWords.approxSecondRank(50);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
