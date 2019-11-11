# Words Counter
The program reads text files from the specified folder, exludes the words specified in a separate file, counts the words (case insensitive) and writes the results accordingly to the alphabetically generated files (A.txt, ..., Z.txt).

## Getting Started
What you’ll need:
    JDK 1.11 or later
  
A programm has one class with main method.
* [WordsCounter.java](https://github.com/SergejJerma/springsamples/blob/master/counter/src/main/java/counter/WordsCounter.java) 


The folder must contain .txt files with texts and the exlude.txt file with the words to be exluded.
The program runs from the command line where the jar file is placed.
* [counter.jar](https://github.com/SergejJerma/springsamples/blob/master/counter/counter.jar) 
```
java -jar counter.jar
```
When the application starts, the program will ask you to enter path of the folder with files:
```
Input path e.g.: C:/counter/ or C:\\counter\\
```
if a non-existent path is entered or there are no text files or exlude.txt in the specified folder - the program will show the message:

```
The specified path does not exist, please read README.md file and run the program again
```
or
```
exlude.txt file not found, please read README.md file and run the program again```

```
or
```
Text files not found, please read README.md file and run the program again
```
or
```
Text files are empty, please read README.md file and run the program again
```
and will be terminated.

If you enter the correct path with text files  and exlude.txt, the program will read all the files, exlude the words from exlude.txt file, count all the remaining words (etc. and the number of words exluded) and save results to the files (A.txt, ..., Z.txt and exlude_number.txt) by the first letter of the word and will show the message:

```
The words from the text files were counted and the results saved in folderPath/files A.txt, B.txt, ..., Z.txt and exlude_number.txt) files
```
When the program starts, A.txt, ..., Z.txt files are generated in a separate thread (multithreading).
Before saving the results, the program checks whether these files are generated, or if not, waits for them to be generated.

notes:
A.txt, B.txt, ..., Z.txt files are re-generated each time the program is started.
exlude_number.txt: if file exists  - the program rewrites it, if not - creates;

# Example
C:/counter/ placed 4 text files with text generated on https://www.lipsum.com/:
* [sample1.txt](https://github.com/SergejJerma/springsamples/blob/master/counter/text_sample1.txt) - 200 words
* [sample2.txt](https://github.com/SergejJerma/springsamples/blob/master/counter/text_sample2.txt) - 200 words
* [sample3.txt](https://github.com/SergejJerma/springsamples/blob/master/counter/text_sample3.txt) - 200 words
* [sample4.txt](https://github.com/SergejJerma/springsamples/blob/master/counter/text_sample4.txt) - 200 words

exlude.txt file content (10 words):
```
nisi auctor Morbi vitae amet vehicula ligula ut purus tempus
```

results e.g.:
exlude_number.txt content:
```
57
```
A.txt file content:
```
ALIQUET 4
AC 15
AT 12
ANTE 4
A 10
ARCU 6
AENEAN 3
ALIQUAM 12
AUGUE 5
ADIPISCING 2
ACCUMSAN 7
```
...
L.txt file content:
```
LOREM 5
LECTUS 4
LAOREET 3
LIBERO 4
LACINIA 2
LUCTUS 2
LACUS 2
LEO 6
LOBORTIS 3
```
and so on.
