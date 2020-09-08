import Data.List 
topThree :: [Integer] -> [Integer] -> [Integer]
topThree x y
    | (length x) + (length y) >= 3 = topThreeHelper (sort(mergeList x y))
    | otherwise = []


mergeList :: [Integer] -> [Integer] -> [Integer]
mergeList [] [] = []
mergeList [] (y:ys) = y : mergeList [] ys 
mergeList (x:xs) y = x : mergeList xs y


topThreeHelper :: [Integer] -> [Integer]
topThreeHelper x = drop (length x - 3) x


topThreeDouble :: [Double] -> [Double] -> [Double]
topThreeDouble x y
    | (length x) + (length y) >= 3 = topThreeHelperDouble (sort(mergeListDouble x y))
    | otherwise = []


mergeListDouble :: [Double] -> [Double] -> [Double]
mergeListDouble [] [] = []
mergeListDouble [] (y:ys) = y : mergeListDouble [] ys 
mergeListDouble (x:xs) y = x : mergeListDouble xs y


topThreeHelperDouble :: [Double] -> [Double]
topThreeHelperDouble x = drop (length x - 3) x
    
test1 = topThree [] []
test2 = topThree [2][4]
test3 = topThree [][1,20,13,5,10]
test4 = topThreeDouble [5.0][3.3,4.4]
test5 = topThree [50,620,125,100,521][1,20,9,3,15]
test6 = topThree [10,20,30,40][5,15,25,35,45]
test7 = topThree [38,7,9,0][18,11,4,7,18,29,11]
test8 = topThree [18,11,4,7,18,29,11][38,7,9,0]

main = do
    putStrLn $ "test1: " ++ show test1
    putStrLn $ "test2: " ++ show test2
    putStrLn $ "test3: " ++ show test3
    putStrLn $ "test4: " ++ show test4
    putStrLn $ "test5: " ++ show test5
    putStrLn $ "test6: " ++ show test6
    putStrLn $ "test7: " ++ show test7
    putStrLn $ "test8: " ++ show test8





    




