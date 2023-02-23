MovieTags
MovieTags is a program that searches for movie tags and displays the results in a user-friendly manner.

Functional Requirements

Read the Data File
Data structures used: HashMap, ArrayList
Algorithm: Reads the CSV file line by line, splits each line into fields, extracts the tag field, and updates the corresponding count in a HashMap. Finally, the HashMap is converted to an ArrayList and sorted by count.
Big-O running time: O(n log n), where n is the number of lines in the input file. The time complexity of sorting the ArrayList is O(n log n).

List Most and Least Popular Tags
Data structures used: ArrayList
Algorithm: Prints the first three and last three TagEntries from the sorted ArrayList.
Big-O running time: O(1) because we're only printing a constant number of elements.

Find Tags by Count and Counts by Name
Data structures used: ArrayList
Algorithm: Linearly searches the ArrayList for the desired tag/count and prints the results.
Big-O running time: O(n), where n is the number of entries in the ArrayList.

Usage
Compile the program with javac MovieTags.java
Run the program with java MovieTags
Follow the prompts to search for tags or tag counts
Enter "EXIT" to exit the program
