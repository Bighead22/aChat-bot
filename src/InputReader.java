import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class InputReader {
    
    public static ArrayList<String> arrayifi(String input){
        ArrayList<String> words = new ArrayList<String>();
     
            String line = input;

        
            int space = line.indexOf(" ");
            while(space != -1){
                
                words.add(line.substring(0,space));
                line = line.substring(space+1);
                space = line.indexOf(" ");
            }
            words.add(line);
        
        return words;
    }

}
