// COLIN DAILEY //
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataProcessor {
	
	
	// READ IN FILES //
	
	// Method used to read in txt files
	public Scanner getFileScanner(String fileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			Scanner fileScanner = new Scanner(fileInputStream);
			return fileScanner;
		}
		
		catch(IOException ie) {
			System.out.println("File exception occured.");
			ie.printStackTrace();
			return null;
		}
	}
	
	// assignRank is meant to handle ties, which will make BabyNameRecord ranks the same if frequencies are equal
	public int assignRank(int previousFrequency, int newFrequency, int rank) {
		if(previousFrequency == newFrequency) {
			return rank;
		}
		else {
			return rank + 1;
		}
	}
	
	
	// Method reads in txt files to as array list that contains BabyNameRecord objects
	public ArrayList<BabyNameRecord> rawDataToArrayList(int startYear, int endYear, String startOfFilePath) {
		ArrayList<BabyNameRecord> babyNameRecordList = new ArrayList<>();
		// names file contains yob1880.txt, yob1881.txt, [...], yob2023.txt where # refers to the year
		for(int year = startYear; year <= endYear; year++) {
			String filePath = startOfFilePath + year + Constants.FILE_EXTENSION;
			Scanner scanner = getFileScanner(filePath);
			
			boolean isFirstInFile = true;
			int babyRank = 1;
			while(scanner.hasNextLine()) {
				// Each line is in CSV-style format
				String[] babyNameAttributes = scanner.nextLine().split(",");
				
				// File format of each line: Name,Gender(M/F),Frequency 
				String currentBabyName = babyNameAttributes[0];
				char currentSex = babyNameAttributes[1].charAt(0);
				int currentFrequency = Integer.parseInt(babyNameAttributes[2]);
				
				// For first iteration
				if(babyNameRecordList.isEmpty()) {
					babyNameRecordList.add(new BabyNameRecord(year, currentBabyName, currentSex, currentFrequency, babyRank));
					isFirstInFile = false;
				}
				
				else {
					// if statement will ensure that the first item in the file is ranked 1 rather than 2
					if(isFirstInFile) {
						isFirstInFile = false;
					}
					else {
						// previousFrequency is the last frequency of the babyNameRecordList
						int previousFrequency = babyNameRecordList.get(babyNameRecordList.size() - 1).getFrequency();
						babyRank = assignRank(previousFrequency, currentFrequency, babyRank);
					}
					
					babyNameRecordList.add(new BabyNameRecord(year, currentBabyName, currentSex, currentFrequency, babyRank));
				}
			}
		}
		return babyNameRecordList;
	}
	
}
