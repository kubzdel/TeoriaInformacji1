import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String args[])
    {
English english = new English();
        try {
            english.prepare();
            english.firstRank(500);
            english.thirdRank(500);
            english.fifthRank(500);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
