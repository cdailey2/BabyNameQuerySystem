// COLIN DAILEY //
import java.util.ArrayList;
import java.util.Scanner;

public class BabyNameQueryApplication {
	public void babyNameQueryApplication() {
		// Read in data
		DataProcessor dataProcessor = new DataProcessor();
		ArrayList<BabyNameRecord> babyNameRecordList = dataProcessor.rawDataToArrayList(Constants.START_YEAR, Constants.END_YEAR, Constants.START_OF_FILE_PATH);
		
		// Initialize user input and handler
		InputHandler inputHandler = new InputHandler();
		Scanner userInput = inputHandler.createInputScanner();
		
		// Run query system
		BabyNameQuery babyNameQuerySystem = new BabyNameQuery(inputHandler);
		babyNameQuerySystem.displayInteractiveMenu();
		babyNameQuerySystem.querySelector(userInput, babyNameRecordList);
		
		userInput.close();
	}
}
