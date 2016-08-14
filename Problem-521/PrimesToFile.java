import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PrimesToFile {
	public static void main(String [] args)throws IOException {
		//starting with 10^14, print out 100,000 primes to file
			//to avoid having to wait for data every test of code
		long numcheck=1;
		long maxnum=1000000000000L;
		double maxroot = Math.sqrt(maxnum);
		
		File fout = new File("primes.txt");
		FileOutputStream fos = new FileOutputStream(fout); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));	 
		
        //could use faster method, but not necessary for primes up to 10^6
		do{
			//check all odd numbers (2 isn't necessary for this)
			numcheck+=2;
			//check for primes
			if(is_prime(numcheck)){
				bw.write(""+numcheck);
				bw.newLine();
			}
		//from 2 to the square root of 10^12
		}while(numcheck<=maxroot);
		bw.close();
	}
	
	//to check if a number is prime
		//check all numbers from 2 to its square root
	public static boolean is_prime(long input){
		long max =  (long) (Math.sqrt(input) + 1);
		if(input<2)return false;
		if(input==2)return true;
		if(input%2==0)return false;
		for (long i = 3; i <= max; i = i + 2) {
			if(input%i==0){
				return false;
			}
		}
		return true;
	}
}
