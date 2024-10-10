// COLIN DAILEY //

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
	// USER INPUT METHODS //
	
	public Scanner createInputScanner() {
		Scanner scanner = new Scanner(System.in);
		return scanner;
	}
	
	public void closeInputScanner(Scanner scanner) {
		scanner.close();
	}

	
	public String enterGender(Scanner scanner) {

		System.out.println("Enter the gender as a capital letter (M/F).");
		String userResponse = scanner.nextLine();
		
		if(userResponse.equals("M") || userResponse.equals("F")) {
			return userResponse;
		}
		
		else {
			System.out.println("Invalid input.");
			return enterGender(scanner);
		}
	}
	
	public String enterYear(Scanner scanner) {
		System.out.println("Enter the year.");
		int userResponse = Integer.parseInt(scanner.nextLine());
		
		if(userResponse >= Constants.START_YEAR && userResponse <= Constants.END_YEAR) {
			return String.valueOf(userResponse);
		}
		
		else {
			System.out.println("Invalid input.");
			return enterYear(scanner);
		}
	}
	
	public String enterName(Scanner scanner, ArrayList<BabyNameRecord> BabyNameRecordList) {
		System.out.println("Enter the name.");
		String userResponse = scanner.nextLine();	
		
		if(nameExists(userResponse, BabyNameRecordList)) {
			return userResponse;
		}
		
		else {
			System.out.println("Name does not exist in dataset");
			return enterName(scanner, BabyNameRecordList);
		}
	}
	// nameExists is used in the enterName method
	public boolean nameExists(String name, ArrayList<BabyNameRecord> BabyNameRecordList) {
		for (BabyNameRecord baby : BabyNameRecordList) {
			if(name.equals(baby.getName())) {
				return true;
			}
		}
		return false;
	}
	
	
	// EXTENDED FUNCTIONALITY //
	// For error handling
	public int[] enterYearRange(Scanner scanner) {
		int[] yearRange = new int[2];
	
		System.out.println("Enter a range of years to query through...");
		System.out.println("Start year");
		yearRange[0] = Integer.parseInt(enterYear(scanner));
		System.out.println("End year");
		yearRange[1] = Integer.parseInt(enterYear(scanner));
		
		return yearRange;
	}

}
