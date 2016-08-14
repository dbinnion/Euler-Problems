import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountLines {
public static void main(String [] args){
	BufferedReader bufferedReader;
    // results were put in file to not have to wait every time the code was tested
    String fileName = "smallprimes.txt";

    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        bufferedReader = new BufferedReader(fileReader);
        long numlines=0;
        while((bufferedReader.readLine()) != null) {
        	numlines++;
        }   
        System.out.println(numlines);
        // Always close files.
        bufferedReader.close();     
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");                  
    }
}
}