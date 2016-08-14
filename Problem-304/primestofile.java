import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class primestofile {
	public static void main(String [] args)throws IOException {
		int numprimes=0;
		//starting with 10^14, print out 100,000 primes to file
			//to avoid having to wait for data every test of code
		long numcheck=100000000000000L;
		
		File fout = new File("primes.txt");
		FileOutputStream fos = new FileOutputStream(fout); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));	 
		
		do{
			numcheck++;
			if(is_prime(numcheck)){
				numprimes++;
				System.out.println(numprimes+" :"+numcheck);
				bw.write(""+numcheck);
				bw.newLine();
			}
		}while(numprimes<100000);
		bw.close();
	}
	
	//to check if a number is prime
		//check all numbers from 2 to its square root
	public static boolean is_prime(long input){
		long max =  (long) (Math.sqrt(input) + 1);
		if(input%2==0)return false;
		for (long i = 3; i <= max; i = i + 2) {
			if(input%i==0){
				return false;
			}
		}
		return true;
	}
}
