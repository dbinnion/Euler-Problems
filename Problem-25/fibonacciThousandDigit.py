fib = [0,0,1]

def nextTerm():
    fib[0] = fib[1]
    fib[1] = fib[2]
    fib[2] = fib[0] + fib[1]

index = 1
while str(fib[2]).__len__() < 1000:
    nextTerm()
    index += 1
print index
