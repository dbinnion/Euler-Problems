import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
	
public class Sum {

	public static long maxnum = 1000000000000L;
	public static long  modnum = 1000000000L;
	public static int maxroot = 1000000;
	public static Vector <Integer> primes= new Vector <Integer>();
	public static int [] primeroots = new int [78498];
	
	public static void main (String [] args)
	{	
		// sum of primes up to 10^12 mod 10^9, found w/ wolfram alpha
		long sum = 705911377;
		
		//get all primes.  already in file so no need to sieve
		makeprimeroots();
		for(int i=0;i<primeroots.length;i++){
			primes.addElement(primeroots[i]);
			sum -= primeroots[i];
		}
		
		//find the number of iterations as smpf for each prime
		//multiply by the prime itself
		//modular arithmetic
	    for(int i=0; i<primes.size(); ++i)
	    {
	    	sum += (hedgehog(maxnum/primes.elementAt(i),i)*primes.elementAt(i)) % modnum;
	    	sum %=modnum;
	    }
	    System.out.println(sum);
	}
	 
	//
	public static long hedgehog(long num, int i){
		//if the number can't be squared, it is the smpf only once
	    if (num<primes.elementAt(i)) return 1;
	    
	    //start with the divided max number
	    long output = num;
	    for (int j=0; j<i; ++j){
	    	//exclude any iterations where there's a smaller prime factor
	    	output -= hedgehog(num/primes.elementAt(j),j);
	    }
	    return output;
	}
	public static void makeprimeroots(){
        // results were put in file to not have to wait every time the code was tested
        String fileName = "primes.txt";

        // This will reference one line at a time
        String line = null;
        
	       try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int count=0;
	            //get each number in the prime file and put it in the array
	            while((line = bufferedReader.readLine()) != null) {
	            	int tempint=Integer.parseInt(line);
	            	primeroots[count]=tempint;
	            	count++;
	            }   
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
