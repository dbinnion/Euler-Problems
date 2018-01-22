using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learn_C_Sharp
{
    class problem35
    {
        static void Main(string[] args)
        {
            int numCircularPrimes = 2;

            for(int i = 6; i < 1000000; i += 6)
            {
                if (IsPrime(i + 1))
                {
                    if (IsCircular(i + 1))
                    {
                        numCircularPrimes += 1;
                    }
                }
                if (IsPrime(i - 1))
                {
                    if (IsCircular(i - 1))
                    {
                        numCircularPrimes += 1;
                    }
                }
            }

            print("The number of circular primes is: " +  numCircularPrimes);
            WaitToClose();

        }
        public static void print(String output)
        {
            Console.WriteLine(output);
        }
        public static void PrintIntToFile(int output)
        {
            System.IO.File.WriteAllText(@"C:\Users\david.binnion\source\repos\Learn C Sharp\Learn C Sharp\" + output+".txt", ""+output);
        }
        public static void WaitToClose()
        {
            Console.WriteLine("Press enter to Close...");
            Console.ReadLine();
        }
        public static Boolean IsPrime(int input)
        {
            if (input < 2){
                return false;
            }
            else if (input == 2)
            {
                return true;
            }
            else if ((input % 2) == 0)
            {
                return false;
            }
            else
            {
                for ( int i = 3; i <= (Math.Sqrt(input)+1); i += 2)
                {
                    if(input % i == 0)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        public static Boolean IsCircular(int input)
        {
            string intAsString = input.ToString();
            foreach (char c in intAsString)
            {
                intAsString = intAsString.Substring(1)+c;
                if (!IsPrime(Convert.ToInt32(intAsString)))
                {
                    return false;
                }
            }
            return true;
        }
    }
}
