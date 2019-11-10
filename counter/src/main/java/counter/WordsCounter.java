package counter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class WordsCounter extends Thread {
	static int exludedWordsNumber;
	static String folderPath = "";
	static AtomicBoolean running = new AtomicBoolean(true);

	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Input path to text files e.g.: C:/counter/ or C:\\\\counter\\\\");
		folderPath = scanner.next();

		WordsCounter counter = new WordsCounter();

		WordsCounter thread = new WordsCounter();
		thread.start();

		counter.writeDataToFiles(
				counter.countWords(counter.readTextFiles(folderPath), counter.readExludeFile(folderPath)),
				exludedWordsNumber, folderPath);

		scanner.close();

		System.out.println("The words from the text files were counted and " + "the results saved in " + folderPath
				+ "files (A.txt, B.txt, ..., Z.txt and exlude_number.txt) files");
	}

	public void run() {

		try {
			generateFilesByAlphabetLetter(folderPath);
			running.set(false);
		} catch (Exception e) {
			System.out.println("Error during files (A.txt, B.txt, ..., Z.txt) generating");
			e.printStackTrace();
		}
	}

	public void writeDataToFiles(Map<String, Integer> wordsCounted, int exludeWordsNumber, String folderPath)
			throws InterruptedException {
		while (running.get()) {
			sleep(100L);
		}
		try {

			for (Map.Entry<String, Integer> entry : wordsCounted.entrySet()) {
				String str = "";
				String firstLetter = "";
				str = entry.getKey() + " " + entry.getValue();
				firstLetter = str.substring(0, 1);

				BufferedWriter writer = new BufferedWriter(
						new FileWriter(folderPath + "files/" + firstLetter + ".txt", true));
				writer.write(str);
				writer.newLine();
				writer.close();
			}

			File exludeNumberFile = new File(folderPath + "files/exlude_number.txt");
			if (!exludeNumberFile.exists()) {
				exludeNumberFile.createNewFile();
			}
			FileWriter fw = new FileWriter(exludeNumberFile, false);
			fw.write(String.valueOf(exludeWordsNumber));
			fw.close();

		} catch (IOException iox) {
			System.out.println("Problems with results writing in files");
			iox.printStackTrace();
		}
	}

	public List<String> readExludeFile(String folderPath) throws IOException {
		String line = null;
		String text = "";

		File file = new File(folderPath + "exlude.txt");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("exlude.txt file not found, please read README.md file and run the program again");
			System.exit(0);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	
		while ((line = br.readLine()) != null) {
				text += line;
			}
			br.close();
		
		List<String> list = Arrays.asList(text.split("\\W+"));

		return list.stream().map(e -> e.toUpperCase()).collect(Collectors.toList());
	}

	public List<String> readTextFiles(String folderPath) throws IOException {
		String line = null;
		String text = "";
		String fileName = null;

		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null) {
			System.out.println("The specified path does not exist, please read README.md file and run the program again");
			System.exit(0);
		}

		for (File file : listOfFiles) {
			if (file.isFile() && !file.getName().equals("exlude.txt")) {
				fileName = file.getName();
			} else
				continue;

			file = new File(folderPath + fileName);
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			while ((line = br.readLine()) != null) {
				text += line;
			}
			text += " ";
			br.close();

		}
		if (fileName == null) {
			System.out.println("Text files not found, please read README.md file and run the program again");
			System.exit(0);
		} else if (text.isBlank()) {
			System.out.println("Text files are empty, please read README.md file and run the program again");
			System.exit(0);
		}
		List<String> list = Arrays.asList(text.split("\\W+"));

		return list.stream().map(e -> e.toUpperCase()).collect(Collectors.toList());
	}

	public Map<String, Integer> countWords(List<String> words, List<String> wordsExlude) {
		Map<String, Integer> countedWords = new HashMap<>();

		int wordsNumberBefore = words.size();
		words.removeAll(wordsExlude);
		int wordsNumberAfter = words.size();
		exludedWordsNumber = wordsNumberBefore - wordsNumberAfter;

		for (String item : words) {
			if (countedWords.containsKey(item))
				countedWords.put(item, countedWords.get(item) + 1);
			else
				countedWords.put(item, 1);
		}
		return countedWords;
	}

	public void generateFilesByAlphabetLetter(String folderPath) throws IOException {
		char[] alphabet = new char[26];
		for (int i = 0; i < 26; i++) {
			alphabet[i] = (char) (65 + i);
			File file = new File(folderPath + "files/");
			file.mkdir();
			file = new File(file.getPath() + "/" + alphabet[i] + ".txt");
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		}
	}
}
