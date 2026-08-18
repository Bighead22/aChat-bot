import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class InputReader {
    
    public static ArrayList<String> arrayifi(String input) throws IOException{
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> cen = FileReader.readFile("src/cen.txt");
     
            String line = input;

            int kys = 0;
        
            int space = line.indexOf(" ");
            while(space != -1){
                
                words.add(line.substring(0,space));
                line = line.substring(space+1);
                space = line.indexOf(" ");
            }
            words.add(line);

            for (int j = 0; j < cen.size(); j++) {
                for (int i = 0; i < words.size(); i++) {
                    if (words.get(i).equals(cen.get(j))) {
                        kys ++;
                    }
                }
            }
            
            if (kys >= 1) {
                for (int i = words.size() - 1; i >= 0; i--) {
                    words.remove(i);
                }
                int num = (int)(Math.random() * 6);
                if (num == 0) {
                    words.add("600");
                } else if (num == 1) {
                    words.add("601");
                } else if (num == 2) {
                    words.add("602");
                } else if (num == 3) {
                    words.add("603");
                } else if (num == 4) {
                    words.add("604");
                } else if (num == 5) {
                    words.add("605");
                }
            }
        
        return words;
    }

}
