// COLIN DAILEY //

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BabyNameQuery {
	
	private InputHandler inputHandler;
	
	public BabyNameQuery(InputHandler inputHandler) {
		this.inputHandler = new InputHandler();
	}

	
	
	// QUERIES //
	
	
	// FORMAT ANSWER FOR QUERIES
	public String formatAnswer(BabyNameRecord foundBaby) {
		if(foundBaby == null) {
			return "Baby not found.";
		}
		return "The name " + foundBaby.getName() + ", gender " + foundBaby.getSex() + ", in the year " + foundBaby.getYear() + " occured with frequency " + foundBaby.getFrequency() + ", and rank " + foundBaby.getRank();
	}
	
	
	
	
	
	// SHOW MOST POPULAR NAME FOR A GIVEN YEAR AND GENDER
	public String queryName(Scanner scanner, ArrayList<BabyNameRecord> babyNameRecordList) {
		String gender = inputHandler.enterGender(scanner);
		String year = inputHandler.enterYear(scanner);
		 
		BabyNameRecord foundBaby = getBabyForQueryName(gender, year, babyNameRecordList);
		
		return formatAnswer(foundBaby);
	}
	
	// Actual query process for this method and what will be used in testing
	public BabyNameRecord getBabyForQueryName(String gender, String year, ArrayList<BabyNameRecord> babyNameRecordList) {
		for(BabyNameRecord baby : babyNameRecordList) {
			// Because baby names are ranked in descending order, the if statement below will always hit the most popular name given a gender and year.
			if(baby.sexIs(gender) && baby.bornIn(year)) {
				BabyNameRecord foundBaby = baby;
				return foundBaby;
			}
		}
		return null;
	}
	
	
	// SHOW RANK FOR GIVEN NAME, GENDER, AND YEAR
	public String queryRank(Scanner scanner, ArrayList<BabyNameRecord> babyNameRecordList) {
		String name = inputHandler.enterName(scanner, babyNameRecordList);
		String gender = inputHandler.enterGender(scanner);
		String year = inputHandler.enterYear(scanner);
		
		BabyNameRecord foundBaby = getBabyForQueryRank(name, gender, year, babyNameRecordList);
		
		return formatAnswer(foundBaby);
	}
	
	// Actual query process for this method and what will be used in testing
	public BabyNameRecord getBabyForQueryRank(String name, String gender, String year, ArrayList<BabyNameRecord> babyNameRecordList) {
		for(BabyNameRecord baby : babyNameRecordList) {
			if(baby.sexIs(gender) && baby.bornIn(year) && baby.nameIs(name)) {
				BabyNameRecord foundBaby = baby;
				return foundBaby;
			}
		}
		return null;
	}
	
	
	
	// FIND THE YEAR IN WHICH THE GIVEN NAME AND GENDER COMBINATION WAS MOST POPULAR
	public String queryYear(Scanner scanner, ArrayList<BabyNameRecord> babyNameRecordList) {
		String name = inputHandler.enterName(scanner, babyNameRecordList);
		String gender = inputHandler.enterGender(scanner);
	
		BabyNameRecord foundBaby = getBabyForQueryYear(name, gender, babyNameRecordList);
		
		return formatAnswer(foundBaby);
	}
	
	// Actual query process for this method and what will be used in testing
	public BabyNameRecord getBabyForQueryYear(String name, String gender, ArrayList<BabyNameRecord> babyNameRecordList) {
		if(babyExists(name, gender, babyNameRecordList)) {
			BabyNameRecord mostPopular = null;
			for(BabyNameRecord baby : babyNameRecordList) {
				if(baby.nameIs(name) && baby.sexIs(gender)) {
					// For first instance of matching baby
					if(mostPopular == null) {
						mostPopular = baby;
					}
					else if(baby.getFrequency() > mostPopular.getFrequency()) {
						mostPopular = baby;
					}
				}
			}
			return mostPopular;
		}
		return null;
	}
	
	// Used in getBabyForQueryYear
	public boolean babyExists(String name, String gender, ArrayList<BabyNameRecord> babyNameRecordList) {
		for(BabyNameRecord baby : babyNameRecordList) {
			if(baby.nameIs(name) && baby.sexIs(gender)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	// QUIT
	public void quit() {
		System.out.println("Exiting the Application...");
		System.exit(0);
	}
	
	
	
	
	// SHOW QUERY OPTIONS
	public void displayInteractiveMenu() {
		System.out.println("Choose an option:");
        System.out.println("P  Show most popular names for a given year and gender.");
        System.out.println("R  Show rank for a given name, gender, and year.");
        System.out.println("Y  Find the year in which the given name and gender combination was most popular.");
        System.out.println("Q  Quit");
        System.out.print("Enter your choice: ");
	}
	
	public void querySelector(Scanner scanner, ArrayList<BabyNameRecord> babyNameRecordList) {
		String userInput = scanner.nextLine().toUpperCase();
		System.out.println();
      
		switch(userInput) {
      		case "P":
      			System.out.println(queryName(scanner, babyNameRecordList));
      			break;
      		case "R":
      			System.out.println(queryRank(scanner, babyNameRecordList));
      			break;
      		case "Y":
      			System.out.println(queryYear(scanner, babyNameRecordList));
      			break;
      		case "Q":
      			quit();
      			break;
      		default:
      			System.out.println("Invalid input. Please try again.");
      			querySelector(scanner, babyNameRecordList);
      }
	}
	
	
	
	
	
	// EXTRA FUNCTIONALITY //
	
	public ArrayList<Integer> queryRankings(String name, String gender, ArrayList<BabyNameRecord> babyNameRecordList, int startYear, int endYear) {
		ArrayList<Integer> listOfRankings = new ArrayList<>();
		
		for(BabyNameRecord baby : babyNameRecordList) {
			if(baby.sexIs(gender) && baby.nameIs(name)) {
				if(baby.getYear() >= startYear && baby.getYear() <= endYear) {
					listOfRankings.add(baby.getRank());
				}
			}
		}
		
		return listOfRankings;
	}
	
	// Given a name and a gender, return all the rankings of that name/gender pair so the 
	// data can be used to show how its popularity has changed for all the years in the data set.
	public ArrayList<Integer> queryAllRankings(String name, String gender, ArrayList<BabyNameRecord> babyNameRecordList) {
		ArrayList<Integer> listOfRankings = new ArrayList<>();
		listOfRankings = queryRankings(name, gender, babyNameRecordList, Constants.START_YEAR, Constants.END_YEAR);
		return listOfRankings;
	}
	
	
	
	
	// Given a name, gender, and range of years (start and end), return the rankings of that name/gender pair 
	// within the range so the data can be used to show how its popularity has changed within the given years.
	public ArrayList<Integer> queryRangeOfRankings(String name, String gender, int startYear, int endYear, ArrayList<BabyNameRecord> babyNameRecordList) {
		ArrayList<Integer> listOfRankings = new ArrayList<>();

		listOfRankings = queryRankings(name, gender, babyNameRecordList, startYear, endYear);
		return listOfRankings;
	}
	
	
	
	
	
	
	// Used in querySameRankInMostRecentYear
	public String getRankGivenNameYearGender(String name, String gender, String year, ArrayList<BabyNameRecord> babyNameRecordList) {
		for(BabyNameRecord baby : babyNameRecordList) {
			if(baby.sexIs(gender) && baby.bornIn(year) && baby.nameIs(name)) {
				return String.valueOf(baby.getRank());
			}
		}
		// If no baby is found the following value will be used in querySameRankInMostRecentYear which will have no hits and subsequently return null
		return "Doesn't exist";
	}
	
	// Given a name, a gender, and a year, return the name/gender pair that has the same rank in the most 
	// recent year in your data set as the given name/gender pair had in the given year.
	public BabyNameRecord querySameRankInMostRecentYear(String name, String gender, String year, ArrayList<BabyNameRecord> babyNameRecordList) {
		
		String rank = getRankGivenNameYearGender(name, gender, year, babyNameRecordList);

		// The most recent instance of the name/gender pair that has the same rank will be at the end because 
		// The babies are sorted first by year in ascending order.
		for(int i = babyNameRecordList.size() - 1; i >= 0; i--) {
			BabyNameRecord baby = babyNameRecordList.get(i);
			if(rank.equals(String.valueOf(baby.getRank())) && baby.getYear() > Integer.parseInt(year)) {
				return baby;
			}
		}
		return null;
	}
	
	// Given a name, a gender, and a range of years, return the average rank of that name/gender pair 
	// so the data can be used to show how popular a name has been over that timespan.
	// Returns zero if no babies match parameters
	public int queryAvgRank(String name, String gender, int startYear, int endYear, ArrayList<BabyNameRecord> babyNameRecordList) { 
		ArrayList<Integer> listOfRankings = new ArrayList<>();
		listOfRankings = queryRangeOfRankings(name, gender, startYear, endYear, babyNameRecordList);
		int sum = 0;
		int numberOfRankings = listOfRankings.size();
		
		for(int ranking : listOfRankings) {
			sum += ranking;
		}
		
		if (sum == 0) {
			return 0;
		}
		
		return sum / numberOfRankings;
	}
	
	// Creates a hashmap of every letter in the alphabet all of which map to 0
	public HashMap<Character, Integer> initializeLetterFrequencyMap() {
		HashMap<Character, Integer> letterFrequencyMap = new HashMap<>();
		
		// Loop through each letter of the alphabet
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			letterFrequencyMap.put(letter, 0); // Map each letter to 0
		}
		return letterFrequencyMap;
	}
	
	// Loads the hashmap by incrementally finding the frequency of all letters for the babies of the given criteria (year range & gender)
	public HashMap<Character, Integer> loadLetterFrequencyMap(String gender, int startYear, int endYear, ArrayList<BabyNameRecord> babyNameRecordList) {
		HashMap<Character, Integer> letterFrequencyMap = new HashMap<>();
		letterFrequencyMap = initializeLetterFrequencyMap();
		
		int incrementedFrequency = 0;
		
		for(BabyNameRecord baby : babyNameRecordList) {
			if(baby.getYear() >= startYear && baby.getYear() <= endYear) {
				if(baby.sexIs(gender)) {
					// Loops through letters in name
					for(int i = 0; i < baby.getName().length(); i++) {
						char letter = Character.toUpperCase(baby.getName().charAt(i));
						incrementedFrequency = letterFrequencyMap.get(letter) + 1;
						letterFrequencyMap.put(letter, incrementedFrequency);
					}
				}
			}	
		}
		
		return letterFrequencyMap;
	}
	
	// Given a range of years and a gender, return the most popular letter that names of that gender have 
	// started with in the range, along with an alphabetized list of all names that start with this letter. 
	// Ties would be handled by getting the first valid letter alphabetically
	public char queryMostPopularLetter(String gender, int startYear, int endYear, ArrayList<BabyNameRecord> babyNameRecordList) {
		HashMap<Character, Integer> letterFrequencyMap = new HashMap<>();
		letterFrequencyMap = loadLetterFrequencyMap(gender, startYear, endYear, babyNameRecordList);
		char mostPopularLetter = 'A';
		
		for(Character key : letterFrequencyMap.keySet()) {
			if(letterFrequencyMap.get(key) > letterFrequencyMap.get(mostPopularLetter)) {
				mostPopularLetter = key;
			}
		}
		return mostPopularLetter;
	}
	
}
