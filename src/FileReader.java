import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class FileReader
 {

   
    public static void appendToFile(String input, String filePath)throws IOException {
        
            Files.write(
                Paths.get(filePath), 
                (input + System.lineSeparator()).getBytes(), 
                StandardOpenOption.CREATE, 
                StandardOpenOption.APPEND
            );
            System.out.println("Saved\n");
        
    }

    public static ArrayList<String> readFile(String filePath) throws IOException{
        
        
        ArrayList<String> words = new ArrayList<String>();
        File myFile = new File(filePath);
        Scanner scan = new Scanner(myFile);
        while(scan.hasNext()) {
            String line = scan.nextLine();
            words.add(line);
        }
        scan.close();
        return words;
    }

    public static ArrayList<String>readFileBetter (String filePath, int location) throws IOException{
        ArrayList<String> words = new ArrayList<String>();
        
        
            ArrayList<String> memory = readFile(filePath);

            String line = memory.get(location);
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