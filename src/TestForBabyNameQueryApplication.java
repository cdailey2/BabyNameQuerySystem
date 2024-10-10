// COLIN DAILEY //

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestForBabyNameQueryApplication {
	
	static ArrayList<BabyNameRecord> testList;
	static Scanner userInput;
	static BabyNameQuery babyNameQuerySystem;
	
	@BeforeAll
	public static void initializeFakeDataAndQuerySystem() {
		DataProcessor dataProcessor = new DataProcessor();
		testList = new ArrayList<>();
		testList = dataProcessor.rawDataToArrayList(Constants.START_YEAR_TESTING, Constants.END_YEAR_TESTING, Constants.START_OF_FILE_PATH_TESTING);
		
		InputHandler inputHandler = new InputHandler();
		userInput = inputHandler.createInputScanner();
		
		babyNameQuerySystem = new BabyNameQuery(inputHandler);
	}
	
	
	// TESTS FOR QUERY-NAME METHOD //
	
	@Test
	public void testFemaleFirstFileForQueryName() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryName("F", "1880", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1880, "Mary", 'F', 7065, 1);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testMaleLastFileForQueryName() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryName("M", "1881", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1881, "Colin", 'M', 91, 7);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testDoesNotExitForQueryName() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryName("M", "1880", testList);
		
		assertNull(foundBaby);
	}
	
	
	
	
	// TESTS FOR QUERY-RANK METHOD //
	
	@Test
	public void testFirstForQueryRank() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryRank("Mary", "F", "1880", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1880, "Mary", 'F', 7065, 1);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testLastForQueryRank() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryRank("Sean", "M", "1881", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1881, "Sean", 'M', 90, 8);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testTies() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryRank("May", "F", "1881", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1881, "May", 'F', 1653, 6);
		
		assertTrue(foundBaby.equals(expectedBaby));		
	}
	
	@Test
	public void testDoesNotExitForQueryRank() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryRank("DOESN'T EXIST!", "F", "1881", testList);
		
		assertNull(foundBaby);
	}
	
	
	
	// TESTS FOR QUERY-YEAR METHOD //
	
	@Test
	public void testFirstFileForQueryYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryYear("Mary", "F", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1880, "Mary", 'F', 7065, 1);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testLastFileForQueryYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryYear("Emma", "F", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1881, "Emma", 'F', 2034, 3);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testOneInstanceForQueryYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryYear("Colin", "M", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1881, "Colin", 'M', 91, 7);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test public void testDoesNotExistForQueryYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.getBabyForQueryYear("DOESN'T EXIST!", "M", testList);
		
		assertNull(foundBaby);
	}
	
	
	

	// TESTS FOR EXTENDED FUNCTIONALITY // 
	
	// TESTS FOR QUERY-ALL-RANKINGS METHOD // 
	
	@Test
	public void testForMultipleSameInstancesQueryAllRankings() {
		ArrayList<Integer> foundListOfRankings = new ArrayList<>();
		foundListOfRankings = babyNameQuerySystem.queryAllRankings("Mary", "F", testList);
		
		ArrayList<Integer> expectedListOfRankings = new ArrayList<>();
		expectedListOfRankings.add(1);
		expectedListOfRankings.add(1);
		expectedListOfRankings.add(1);
		
		assertTrue(foundListOfRankings.equals(expectedListOfRankings));
	}
	
	@Test
	public void testForMultipleStaggeredInstancesQueryAllRankings() {
		ArrayList<Integer> foundListOfRankings = new ArrayList<>();
		foundListOfRankings = babyNameQuerySystem.queryAllRankings("Minnie", "F", testList);
		
		ArrayList<Integer> expectedListOfRankings = new ArrayList<>();
		expectedListOfRankings.add(5);
		expectedListOfRankings.add(6);
		expectedListOfRankings.add(5);
		
		assertTrue(foundListOfRankings.equals(expectedListOfRankings));
	}
	
	@Test
	public void testForSingleInstanceQueryAllRankings() {
		ArrayList<Integer> foundListOfRankings = new ArrayList<>();
		foundListOfRankings = babyNameQuerySystem.queryAllRankings("Colin", "M", testList);
		
		ArrayList<Integer> expectedListOfRankings = new ArrayList<>();
		expectedListOfRankings.add(7);

		assertTrue(foundListOfRankings.equals(expectedListOfRankings));
	}
	
	@Test
	public void testForNonExistentQueryAllRankings() {
		ArrayList<Integer> foundListOfRankings = new ArrayList<>();
		foundListOfRankings = babyNameQuerySystem.queryAllRankings("DOESN'T EXIST!", "M", testList);
		
		assertTrue(foundListOfRankings.isEmpty());
	}	
	
	
	
	// TESTS FOR QUERY-RANGE-OF-RANKINGS METHOD // 
	
	@Test
	public void testMultipleYearsForQueryRangeOfRankings() {
		ArrayList<Integer> foundListOfRankings = new ArrayList<>();
		foundListOfRankings = babyNameQuerySystem.queryRangeOfRankings("Mary", "F", 1880, 1881, testList);
		
		ArrayList<Integer> expectedListOfRankings = new ArrayList<>();
		expectedListOfRankings.add(1);
		expectedListOfRankings.add(1);
		
		assertTrue(foundListOfRankings.equals(expectedListOfRankings));
	}
	
	@Test
	public void testSingleYearForQueryRangeOfRankings() {
		ArrayList<Integer> foundListOfRankings = new ArrayList<>();
		foundListOfRankings = babyNameQuerySystem.queryRangeOfRankings("Mary", "F", 1880, 1880, testList);
		
		ArrayList<Integer> expectedListOfRankings = new ArrayList<>();
		expectedListOfRankings.add(1);
		
		assertTrue(foundListOfRankings.equals(expectedListOfRankings));
	}
	
	@Test
	public void testForNonExistentQueryRangeOfRankings() {
		ArrayList<Integer> foundListOfRankings = new ArrayList<>();
		foundListOfRankings = babyNameQuerySystem.queryRangeOfRankings("DOESN'T EXIST", "F", 1880, 1881, testList);
		
		assertTrue(foundListOfRankings.isEmpty());
	}
	
	// TESTS FOR QUERY-SAME-RANK-IN-MOST-RECENT-YEAR METHOD //
	
	@Test
	public void testFemalesForQuerySameRankInMostRecentYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.querySameRankInMostRecentYear("Mary", "F", "1880", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1882, "Mary", 'F', 6918, 1);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testMalesForQuerySameRankInMostRecentYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.querySameRankInMostRecentYear("Colin", "M", "1881", testList);
		BabyNameRecord expectedBaby = new BabyNameRecord(1882, "Fred", 'M', 900, 7);
		
		assertTrue(foundBaby.equals(expectedBaby));
	}
	
	@Test
	public void testHasNoAnswerForQuerySameRankInMostRecentYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.querySameRankInMostRecentYear("Sean", "M", "1881", testList);
		
		assertNull(foundBaby);
	}
	
	@Test
	public void testForNonExistentForQuerySameRankInMostRecentYear() {
		BabyNameRecord foundBaby = babyNameQuerySystem.querySameRankInMostRecentYear("DOESN'T EXIST", "M", "1881", testList);
		
		assertNull(foundBaby);
	}
	
	// TESTS FOR QUERY-AVG-RANK METHOD //
	
	@Test
	public void testAllSameRankForQueryAvgRank() {
		int avgRank = babyNameQuerySystem.queryAvgRank("Mary", "F", 1880, 1882, testList);
		int expectedAvg = 1;
		
		assertTrue(avgRank == expectedAvg);
	}
	
	@Test
	public void testOneInstanceForQueryAvgRank() {
		int avgRank = babyNameQuerySystem.queryAvgRank("Colin", "M", 1880, 1882, testList);
		int expectedAvg = 7;
		
		assertTrue(avgRank == expectedAvg);
	}
	
	@Test
	public void testNonExistentForQueryAvgRank() {
		int avgRank = babyNameQuerySystem.queryAvgRank("DOESN'T EXIST!", "F", 1880, 1882, testList);
		int expectedAvg = 0;
		
		assertTrue(avgRank == expectedAvg);
	}
	
	@Test
	public void testOneYearForQueryAvgRank() {
		int avgRank = babyNameQuerySystem.queryAvgRank("Mary", "F", 1880, 1880, testList);
		int expectedAvg = 1;
		
		assertTrue(avgRank == expectedAvg);
	}
	
	// TESTS FOR QUERY-MOST-POPULAR-LETTER METHOD //
	
	@Test
	public void testFemalesForQueryMostPopularLetter() {
		char mostPopularLetter = babyNameQuerySystem.queryMostPopularLetter("F", 1880, 1882, testList);
		char expectedLetter = 'A';
		
		assertEquals(mostPopularLetter, expectedLetter);
	}
	
	@Test
	public void testMalesForQueryMostPopularLetter() {
		char mostPopularLetter = babyNameQuerySystem.queryMostPopularLetter("M", 1880, 1882, testList);
		char expectedLetter = 'E';
		
		assertEquals(mostPopularLetter, expectedLetter);
	}
	
	@Test
	public void testHandleTiesForQueryMostPopularLetter() {
		char mostPopularLetter = babyNameQuerySystem.queryMostPopularLetter("M", 1882, 1882, testList);
		char expectedLetter = 'D';
		
		assertEquals(mostPopularLetter, expectedLetter);
	}
}
