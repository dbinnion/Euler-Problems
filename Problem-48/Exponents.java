
public class Exponents {
	public static void main (String [] args){
		long sum=0;
		long modnum=10000000000L;
		for(int i=1;i<=1000;i++){
			long number=i;
			long temp=i;
			//multiply number x by itself x number of times 
			for(int j=1;j<i;j++){
				number*=temp;
				//mod to keep number of places while keeping memory use low
				number %=modnum;
			}
			//more modular arithmetic
			sum +=number;
			sum %= modnum;
		}
		System.out.println(sum);
	}
}
