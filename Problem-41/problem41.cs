using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learn_C_Sharp
{
    public class Globals
    {
        private static int maxPrime = 0;
        public static int MaxPrime
        {
            get
            {
                return maxPrime;
            }
            set
            {
                maxPrime = value;
            }
        }
    }
    class problem41
    {
        static void Main(string[] args)
        {
            string[] array = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            for(int i = 0; i < 9; i++)
            {
                Perm(array, 0);
                print(""+Globals.MaxPrime);
                if(Globals.MaxPrime == 0)
                {
                    array = array.Take(array.Count() - 1).ToArray();
                }
                else
                {
                    break;
                }
            }
            
            WaitToClose();
        }
        public static void print(String output)
        {
            Console.WriteLine(output);
        }
        public static void WaitToClose()
        {
            Console.WriteLine("Press enter to Close...");
            Console.ReadLine();
        }
        private static List<string> StringPowerSet(string input)
        {
            int n = input.Length;
            // Power set contains 2^N subsets.
            int powerSetCount = 1 << n;
            var ans = new List<string>();

            for (int setMask = 0; setMask < powerSetCount; setMask++)
            {
                var s = new StringBuilder();
                for (int i = 0; i < n; i++)
                {
                    // Checking whether i'th element of input collection should go to the current subset.
                    if ((setMask & (1 << i)) > 0)
                    {
                        s.Append(input[i]);
                    }
                }
                ans.Add(s.ToString());
            }

            return ans;
        }
        public static void Perm<T>(T[] arr, int k)
        {
            if (k >= arr.Length)
                Test(arr);
            else
            {
                Perm(arr, k + 1);
                for (int i = k + 1; i < arr.Length; i++)
                {
                    Swap(ref arr[k], ref arr[i]);
                    Perm(arr, k + 1);
                    Swap(ref arr[k], ref arr[i]);
                }
            }
        }
        public static void Swap<T>(ref T item1, ref T item2)
        {
            T temp = item1;
            item1 = item2;
            item2 = temp;
        }
        private static void Test<T>(T[] arr)
        {
            int value = Convert.ToInt32(string.Join("", arr));
            if (IsPrime(value))
            {
                if(value > Globals.MaxPrime)
                {
                    Globals.MaxPrime = value;
                }
            }
        }
        public static Boolean IsPrime(int input)
        {
            if (input < 2)
            {
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
                for (int i = 3; i <= (Math.Sqrt(input) + 1); i += 2)
                {
                    if (input % i == 0)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
