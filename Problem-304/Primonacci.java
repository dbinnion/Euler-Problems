import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Primonacci {

	public static long modnum = 1234567891011L;
	
	public static void main (String [] args){
        // results were put in file to not have to wait every time the code was tested
        String fileName = "primes.txt";

        // This will reference one line at a time
        String line = null;
        long summation=0L;
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            int count=0;
            while((line = bufferedReader.readLine()) != null) {
            	count++;
            	System.out.println(count+": "+line);
            	long templong=Long.parseLong(line);
            	//modular arithmetic used here to reduce size of result
            	BigInteger tempint=fastFibonacciMatrix(templong).mod(BigInteger.valueOf(modnum));
    			long f_val=tempint.longValueExact();
            	summation=(summation+f_val)%modnum;
            }   
            long output=summation%modnum;
            System.out.println(output);
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

	/* 
	 * Fast matrix method. Easy to describe, but has a constant factor slowdown compared to doubling method.
	 * [1 1]^n   [F(n+1) F(n)  ]
	 * [1 0]   = [F(n)   F(n-1)].
	 */
	private static BigInteger fastFibonacciMatrix(long n) {
		BigInteger[] matrix = {BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO};
		return matrixPow(matrix, n)[1];
	}
	
	// Computes the power of a matrix. The matrix is packed in row-major order.
	private static BigInteger[] matrixPow(BigInteger[] matrix, long n) {
		if (n < 0)
			throw new IllegalArgumentException();
		BigInteger[] result = {BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE};
		while (n != 0) {  // Exponentiation by squaring
			if (n % 2 != 0)
				result = matrixMultiply(result, matrix);
			n /= 2;
			matrix = matrixMultiply(matrix, matrix);
		}
		return result;
	}
	
	// Multiplies two matrices.
	private static BigInteger[] matrixMultiply(BigInteger[] x, BigInteger[] y) {
		return new BigInteger[] {
			//add modular arithmetic to speed up process
			multiply(x[0], y[0]).add(multiply(x[1], y[2])).mod(BigInteger.valueOf(modnum)),
			multiply(x[0], y[1]).add(multiply(x[1], y[3])).mod(BigInteger.valueOf(modnum)),
			multiply(x[2], y[0]).add(multiply(x[3], y[2])).mod(BigInteger.valueOf(modnum)),
			multiply(x[2], y[1]).add(multiply(x[3], y[3])).mod(BigInteger.valueOf(modnum))
		};
	}
	
	// Requirement: CUTOFF >= 64, or else there will be infinite recursion.
	private static final int CUTOFF = 1536;
	public static BigInteger multiply(BigInteger x, BigInteger y) {
		if (x.bitLength() <= CUTOFF || y.bitLength() <= CUTOFF) {  // Base case
			return x.multiply(y);
			
		} 
		else {
			int n = Math.max(x.bitLength(), y.bitLength());
			int half = (n + 32) / 64 * 32;  // Number of bits to use for the low part
			BigInteger mask = BigInteger.ONE.shiftLeft(half).subtract(BigInteger.ONE);
			BigInteger xlow = x.and(mask);
			BigInteger ylow = y.and(mask);
			BigInteger xhigh = x.shiftRight(half);
			BigInteger yhigh = y.shiftRight(half);
			
			BigInteger a = multiply(xhigh, yhigh);
			BigInteger b = multiply(xlow.add(xhigh), ylow.add(yhigh));
			BigInteger c = multiply(xlow, ylow);
			BigInteger d = b.subtract(a).subtract(c);
			return a.shiftLeft(half).add(d).shiftLeft(half).add(c);
		}
	}		
}
