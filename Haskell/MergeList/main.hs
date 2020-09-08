{-|

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

-}
threeListStripe :: [String] -> [String] -> [String] -> [String]
threeListStripe x y z 
    |(length x) == (length y) && (length y) == (length z) && (length x) == (length z) = threeListStripeHelper x y z
    | otherwise = []
    
threeListStripeHelper :: [String] -> [String] -> [String] -> [String]
threeListStripeHelper [] [] [] = []
threeListStripeHelper (x:xs) (y:ys) (z:zs) = x : y : z : threeListStripeHelper xs ys zs

one = ["one","two","three"]
two = ["1","2","3"]
three = ["once","twice","thrice"]

a = ["one","two"]
b = ["1","2","3"]
c = ["once"]

list1 = ["Mary","Little"]
list2 = ["Had","Lamb"]
list3 = ["A","Song"]

listA = ["Stony","CSE"]
listB = ["Brook","216"]
listC = ["University"]

listStripedA = threeListStripe one two three
listStripedB = threeListStripe a b c 
listStripedC = threeListStripe list1 list2 list3
listStripedD = threeListStripe listA listB listC

main = do
    putStrLn $ "listStripedA: " ++ show listStripedA
    putStrLn $ "listStripedB: " ++ show listStripedB
    putStrLn $ "listStripedC: " ++ show listStripedC
    putStrLn $ "listStripedD: " ++ show listStripedD

