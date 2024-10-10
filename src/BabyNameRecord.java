// COLIN DAILEY //

public class BabyNameRecord {
	// INSTANCE VARIABLES //
	private int year;
	private String name;
	private char sex;
	private int frequency;
	private int rank;
	
	
	// CONSTRUCTORS //
	public BabyNameRecord (int year, String name, char sex, int frequency, int rank) {
		setYear(year);
		setName(name);
		setSex(sex);
		setFrequency(frequency);
		setRank(rank);
	}
	
	
	// SETTERS //
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	// GETTERS //
	public int getYear() {
		return year;
	}
	
	public String getName() {
		return name;
	}
	
	public char getSex() {
		return sex;
	}
	public int getFrequency() {
		return frequency;
	}	
	
	public int getRank() {
		return rank;
	}
	
	
	// METHODS //
	public boolean bornIn(String year) {
		if(year.equals(Integer.toString(this.year))) {
			return true;
		}
		return false;
	}
	
	public boolean sexIs(String sex) {
		if(sex.equals(Character.toString(this.sex))) {
			return true;
		}
		return false;
	}
	
	public boolean nameIs(String name) {
		if(name.equals(this.name) ) {
			return true;
		}
		return false;
	}
	
	public boolean equals(BabyNameRecord baby) {
		return this.year == baby.getYear() 
				&& this.name.equals(baby.getName()) 
				&& this.sex == baby.getSex()
				&& this.frequency == baby.getFrequency()
				&& this.rank == baby.getRank();		
	}
	
	public String toString() {
		return "name: " + this.name + " year: " + this.year + " sex: " + this.sex + " frequency: " + this.frequency + " rank: " + this.rank;
	}
}
