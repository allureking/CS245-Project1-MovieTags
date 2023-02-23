MovieTags is a program that searches for movie tags and displays the results in a user-friendly manner.

Functional Requirements


Read the data file

This algorithm uses tagEntries: an ArrayList of TagEntry objects, each of which is a pair of { String: tag, int: count }.
For each line in the input data file, the implementation checks if a TagEntry object with the current tag already exists in the list:
If the object exists, the count is incremented.
If the object does not exist, a new TagEntry object with the current tag and a count of 1 is added to the end of the tagEntries ArrayList.
The implementation then sorts the tagEntries ArrayList by count.
Data structures used: ArrayList
Algorithm: linear search to check for existing TagEntry objects, followed by sorting of the ArrayList
Running time: O(n^2) where n is the number of lines in the input file, because each line of input requires a linear search on the ArrayList tagEntries, which may contain up to "n" entries. The sorting step has a worst-case time complexity of O(n log n), where n is the number of entries in the ArrayList. However, the linear search dominates the running time for large inputs.


Sort the list of TagEntries using the Merge sort algorithm:

This implementation uses Merge sort to sort the list of TagEntry objects by their count.
Merge sort is a divide-and-conquer sorting algorithm that works by recursively dividing the list into smaller sublists, sorting those sublists, and then merging them back together.
The running time of Merge sort is O(n log n), where n is the number of elements in the list. This is because the algorithm divides the list into halves at each level of recursion, resulting in log n levels of recursion, and the merge operation takes O(n) time, resulting in a total running time of O(n log n).
In the program, the mergeSort() method is used to sort the list of TagEntry objects, and the merge() method is used to merge two sorted sublists. These methods are implemented using recursion and are based on the standard Merge sort algorithm.


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
