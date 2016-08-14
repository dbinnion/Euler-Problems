from math import sqrt

primes = [2, 3]

def testPrime(number):
    numcheck = 3
    maxroot = sqrt(number)

    while numcheck <= maxroot:
        if not number % numcheck:
            return False
        numcheck += 2
    return True

i=1
while primes.__len__() < 10001:
    firstNum = (6 * i) - 1
    secondNum = (6 * i) + 1
    if testPrime(firstNum):
        primes.append(firstNum)
    if testPrime(secondNum):
        primes.append(secondNum)
    i += 1

print primes[primes.__len__()-1]
