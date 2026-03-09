import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Memory {
    
    
    public static boolean isOK(ArrayList<String> iput, ArrayList<String> memory) throws IOException {

    if (iput.size() != memory.size()) return false;

    ArrayList<String> w1 = FileReader.readFile("src/words1.txt");
    ArrayList<String> w2 = FileReader.readFile("src/words2.txt");

    int matchCount = 0;

    for (int i = 0; i < iput.size(); i++) {
        String wordIn = iput.get(i);
        String wordMem = memory.get(i);

        if (wordIn.equalsIgnoreCase(wordMem)) {
            matchCount++;
            continue;
        }
        for (int k = 0; k < w1.size(); k++) {
            if ((wordIn.equalsIgnoreCase(w1.get(k)) && wordMem.equalsIgnoreCase(w2.get(k))) ||
                (wordIn.equalsIgnoreCase(w2.get(k)) && wordMem.equalsIgnoreCase(w1.get(k)))) {
                matchCount++;
                break;
            }
        }
    }
    return matchCount == iput.size();
}
    public static boolean isGoodEnough(ArrayList<String> iput, ArrayList<String> memory) throws IOException{
        double count = 0.0;
        for (int i = 0; i < iput.size(); i++){
            for (int j = 0; j < memory.size(); j++){
                if (iput.get(i).equalsIgnoreCase(memory.get(j))){
                    count ++;
                }
            }
        }
        if (count >= (((double)(memory.size()))/1.05)){
            return true;
        }else{
            return false;
        }
    }
    
    public static String getGuiResponse(String input) throws IOException {
    ArrayList<String> ques = InputReader.arrayifi(input);
    ArrayList<String> memoryList = FileReader.readFile("src/Memory.txt");
    ArrayList<String> responseList = FileReader.readFile("src/Response.txt");
    
    for (int i = 0; i < memoryList.size(); i++) {
        ArrayList<String> tMemory = FileReader.readFileBetter("src/Memory.txt", i);

        if (Memory.isOK(ques, tMemory)) {
            return responseList.get(i);
        } else if (Memory.isGoodEnough(ques, tMemory)){
            return responseList.get(i);
        }
    }
    return "";
}



}
