
public class problem10 {
	public static void main (String [] args){
		long summation=2+3;
		//every prime after 2 and 3 is 6n+/-1
		//wheel of factorization
		for(int i=6;i<2000000;i=i+6){
			boolean tempbool=is_prime(i-1);
			if(tempbool){
				summation+=(i-1);
				System.out.println((i-1));
			}
			tempbool=is_prime(i+1);
			if(tempbool){
				summation+=(i+1);
				System.out.println((i+1));
			}
		}
		System.out.println(summation);
	}
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
