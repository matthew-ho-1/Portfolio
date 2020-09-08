import Data.List

numToIpAddress :: Int -> Int-> String
numToIpAddress 0 4  = ""
numToIpAddress 0 count = numToIpAddress 0 (count + 1) ++ show 0 ++ "."
numToIpAddress n 0 = numToIpAddress (div n 256) (0 + 1) ++ show (mod n 256) 
numToIpAddress n count = numToIpAddress (div n 256) (count + 1) ++ show (mod n 256) ++ "."

ipAddress = numToIpAddress 3434 0 
ipAddress2 = numToIpAddress 2130706433 0
ipAddress3 = numToIpAddress 1234567890 0 
ipAddress4 = numToIpAddress 200000 0 

ipAddressToNum :: String -> Int ->  Int
ipAddressToNum "" 0 = 0
ipAddressToNum ip 0 = read (drop 0 ip) * (256^0) + ipAddressToNum ("") (0)
ipAddressToNum ip count = read (drop 0 (take (head (elemIndices '.' ip)) ip)) * (256^count) + ipAddressToNum (drop (head (elemIndices '.' ip) + 1) ip)(count - 1)
    
num = ipAddressToNum ipAddress 3
num2 = ipAddressToNum ipAddress2 3
num3 = ipAddressToNum ipAddress3 3 
num4 = ipAddressToNum ipAddress4 3

generateKey :: Int -> String
origString = "nZkHpcAigtTVU3bGSIe0D6oL9W1CQqFvYfBR4mwP8dOua7rjX5JhsxEKylMz2N"
list = [0.924515643499907, 0.6329206625423477, 0.9350004109009109, 0.6429428344945336, 0.4194183958340444, 0.7271373034031646, 0.9384415730200726, 0.7891084507560416, 0.26815610172062954, 0.04663428682872184]
randomList = [x * 11 | x <- list]
generateKey index = [(origString !! (round (randomList !! index)))] ++ generateKeyHelper (take (round (randomList !! index)) origString ++ drop (1 + (round (randomList !! index))) origString) index

generateKeyHelper :: String -> Int -> String
generateKeyHelper [] index = ""
generateKeyHelper loopedKey index 
    | index > ((length loopedKey) - 1) = [last loopedKey] ++ generateKeyHelper  (take ((length loopedKey) - 1) loopedKey ++ drop (1 + ((length loopedKey) - 1)) loopedKey) ((length loopedKey) - 1)
    | otherwise = [(loopedKey !! index)] ++ generateKeyHelper  (take index loopedKey ++ drop (1 + index) loopedKey) index  

key = generateKey 4 
key2 = generateKey 7 
key3 = generateKey 0 

encrypt :: String -> String -> String 
encrypt key [] = ""
encrypt key (y:ys) 
    | (length ys) > 0 = show (head (elemIndices y key)) ++ " " ++ encrypt key ys
    | otherwise = show (head (elemIndices y key)) ++ encrypt key ys

encrypted = encrypt key "HelloWorld"
encryptedB = encrypt key "Avengers"
encrypted2 = encrypt key2 "CSE216"
encrypted2B = encrypt key2 "BL00P"
encrypted3 = encrypt key3 "StonyBrook"
encrypted3B = encrypt key3 "Wehavedeliveredthepackageat0500hours"

decrypt :: [String] -> String -> String 
decrypt [] key = ""
decrypt x key = convertToKey (read(head x) :: Int) key ++ decrypt (drop 1 x) key 

convertToKey :: Int -> String -> String 
convertToKey index key = drop index (take (index + 1) key)

decrypted = decrypt (words encrypted) key 
decryptedB = decrypt (words encryptedB) key
decrypted2 = decrypt (words encrypted2) key2
decrypted2B = decrypt (words encrypted2B) key2
decrypted3 = decrypt (words encrypted3) key3
decrypted3B = decrypt (words encrypted3B) key3

main = do 
    putStrLn $ "numToIpAddress: " ++ show ipAddress
    putStrLn $ "ipAddressToNum: " ++ show num 
    putStrLn $ "numToIpAddress2: " ++ show ipAddress2
    putStrLn $ "ipAddressToNum2: " ++ show num2
    putStrLn $ "numToIpAddress3: " ++ show ipAddress3
    putStrLn $ "ipAddressToNum3: " ++ show num3
    putStrLn $ "numToIpAddress4: " ++ show ipAddress4
    putStrLn $ "ipAddressToNum4: " ++ show num4 
    putStrLn $ "generateKey: " ++ show key
    putStrLn $ "generateKey2: " ++ show key2 
    putStrLn $ "generateKey3: " ++ show key3
    putStrLn $ "encrypted using key: " ++ show encrypted
    putStrLn $ "decrypted using key: " ++ show decrypted
    putStrLn $ "encryptedB using key: " ++ show encryptedB
    putStrLn $ "decryptedB using key: " ++ show decryptedB
    putStrLn $ "encrypted2 using key2: " ++ show encrypted2
    putStrLn $ "decrypted2 using key2: " ++ show decrypted2
    putStrLn $ "encrypted2B using key2: " ++ show encrypted2B
    putStrLn $ "decrypted2B using key2: " ++ show decrypted2B
    putStrLn $ "encrypted3 using key3: " ++ show encrypted3
    putStrLn $ "decrypted3 using key3: " ++ show decrypted3
    putStrLn $ "encrypted3B using key3: " ++ show encrypted3B
    putStrLn $ "decrypted3B using key3: " ++ show decrypted3B
    
    
















