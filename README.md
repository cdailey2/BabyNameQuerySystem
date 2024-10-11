# Baby Name Query System Application

<h2>Description</h2>
<p>This project is a console-based query system for baby names, written entirely in Java. This project was used to practice clean code principles and  and object-oriented programming to ensure reliability, readability, reusability, scalability, and testability.</p>

<h2>The Data</h2>
<p>The raw data are in a csv-style format within txt files and are formatted like the following: (Name, Sex (M/F), Frequency)
More on the raw data can be found within the <a href='https://github.com/cdailey2/BabyNameQuerySystem/blob/main/names/NationalReadMe.pdf'>names directory</a>.
The data were taken from the U.S. Social Security Administration and can be found <a href='https://catalog.data.gov/dataset/baby-names-from-social-security-card-applications-national-data'>here</a>.</p>

<p>The data were read in using the <a href='https://github.com/cdailey2/BabyNameQuerySystem/blob/main/src/DataProcessor.java'>DataProcessor class</a> into an array list of BabyNameRecord objects.</p>

The <a href='https://github.com/cdailey2/BabyNameQuerySystem/blob/main/src/BabyNameRecord.java'>BabyNameRecord class</a> has the following attributes:
- int year (extracted from the txt file that the baby comes from)
- String name
- char sex
- int frequency
- int rank (largest frequency means highest rank in a given year. ties in frequency are given the same rank.)

<h2>Supported Queries</h2>

**Supported by UI**
 - Show most popular names for a given year and gender.
 - Show rank for a given name, gender, and year.
 - Find the year in which the given name, gender combination was most popular.
 - Quit

**Working Queries not added to UI**
- Given a name and a gender, return all the rankings of that name/gender pair so the data can be used to show how its popularity has changed for all the years in the data set.
- Given a name, gender, and range of years (start and end), return the rankings of that name/gender pair within the range so the data can be used to show how its popularity has changed within the given years.
- Given a name, a gender, and a year, return the name/gender pair that has the same rank in the most recent year in your data set as the given name/gender pair had in the given year.
- Given a name, a gender, and a range of years, return the average rank of that name/gender pair so the data can be used to show how popular a name has been over that timespan.
- Given a range of years and a gender, return the most popular letter that names of that gender have within the range.

Logic to all queries can be found in the <a href='https://github.com/cdailey2/BabyNameQuerySystem/blob/main/src/BabyNameQuery.java'>BabyNamesQuery class</a>.

<h2>Testing</h2>
<p>All testing was done using JUnit.</p>
Testing document can be found <a href='https://github.com/cdailey2/BabyNameQuerySystem/blob/main/src/TestForBabyNameQueryApplication.java'>here</a>.
