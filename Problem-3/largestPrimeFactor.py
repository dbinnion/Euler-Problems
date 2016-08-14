from math import sqrt


number = 600851475143
maxPrime = 0

def testPrime(number):
    numcheck = 3
    maxroot = sqrt(number)

    while numcheck <= maxroot:
        if not number % numcheck:
            return False
        numcheck += 2
    return True


def isFactor(root):
    if not number % root:
        return True
    return False

i=1
while (6 * i) < sqrt(number):
    firstNum = (6 * i) - 1
    secondNum = (6 * i) + 1
    if testPrime(firstNum):
        if isFactor(firstNum):
            if firstNum > maxPrime:
                maxPrime = firstNum
    if testPrime(secondNum):
        if isFactor(secondNum):
            if secondNum > maxPrime:
                maxPrime = secondNum
    i += 1

print maxPrime
