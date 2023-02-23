import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * MovieTags
 *
 * A program that searches for movie tags and displays the results in a user-friendly manner
 *
 * @author [Honghuai Ke]
 * @date [02/22/2023]
 */
public class MovieTags {
    // The name of the file that contains the movie tags.
    private static final String TAGS_FILE = "tags.csv";

    // Commands that can be entered by the user to control the program.
    private static final String EXIT_COMMAND = "EXIT";
    private static final String SEARCH_BY_TAG_COMMAND = "T";
    private static final String SEARCH_BY_TAG_COUNT_COMMAND = "C";

    /**
     * Represents a tag with its frequency
     */
    private static class TagEntry implements Comparable<TagEntry> {
        public final String tag;
        public int count;

        /**
         * Constructs a new TagEntry with the given tag and count.
         *
         * @param tag   The tag for this entry.
         * @param count The count for this entry.
         */

        public TagEntry(String tag, int count) {
            this.tag = tag;
            this.count = count;
        }

        /**
         * Compare TagEntries by their count, then by their tag in alphabetical order
         */
        @Override
        public int compareTo(TagEntry other) {
            if (count == other.count) {
                return tag.compareTo(other.tag);
            }
            return Integer.compare(other.count, count);
        }
    }


    /**
     * Read and process the tags file, and return the list of TagEntries sorted by their count.
     *
     * @param filename The name of the file to read the tags from.
     * @return A list of TagEntries sorted by their count.
     */
    private static List<TagEntry> readTagsFile(String filename) {
        // Use a map to count the frequency of each tag.
        Map<String, Integer> tagCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // skip the header row
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into fields and extract the tag.
                String[] fields = line.split(",", -1);
                String tag = fields[2];
                tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Convert the map entries to TagEntries and sort by count.
        List<TagEntry> tagEntries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
            tagEntries.add(new TagEntry(entry.getKey(), entry.getValue()));
        }
        Collections.sort(tagEntries);
        return tagEntries;
    }


    /**
     * Print the most and least popular tags.
     *
     * @param tagEntries A list of TagEntries sorted by their count.
     */
    private static void printPopularTags(List<TagEntry> tagEntries) {
        System.out.println("==========================================");
        System.out.println("*** Highest 3 movies by count ***");
        for (int i = 0; i < 3 && i < tagEntries.size(); i++) {
            System.out.printf("%d: %s\n", tagEntries.get(i).count, tagEntries.get(i).tag);
        }
        System.out.println("*** Lowest 3 movies by count ***");
        for (int i = 0; i < 3 && i < tagEntries.size(); i++) {
            System.out.printf("%d: %s\n", tagEntries.get(tagEntries.size() - 1 - i).count,
                    tagEntries.get(tagEntries.size() - 1 - i).tag);
        }
        System.out.println("==========================================");
    }


    /**
     * Search for a tag and print its count
     *
     * @param tagEntries the list of tags to search through
     * @param scanner    the Scanner object to read user input from
     */
    private static void searchByTag(List<TagEntry> tagEntries, Scanner scanner) {
        System.out.print("Tag to search for: ");
        String tag = scanner.nextLine();
        int count = 0;
        boolean found = false;
        for (TagEntry entry : tagEntries) {
            if (entry.tag.equalsIgnoreCase(tag)) {
                count = entry.count;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.printf("Tag \"%s\" does not exist.\n", tag);
        } else {
            System.out.printf("Tag \"%s\" occurred %d times.\n", tag, count);
        }
    }


    /**
     * Search for tags with a specific count and print them
     *
     * @param tagEntries the list of tags to search through
     * @param scanner    the Scanner object to read user input from
     */
    private static void searchByTagCount(List<TagEntry> tagEntries, Scanner scanner) {
        System.out.print("Count to search for: ");
        String countStr = scanner.nextLine();
        int count;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            System.out.println("Is " + countStr + " even a number? C'mon, man!");
            return;
        }
        System.out.println("Tags with " + count + " occurrences:");
        boolean found = false;
        for (TagEntry entry : tagEntries) {
            if (entry.count == count) {
                System.out.println("* " + entry.tag);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tags with " + count + " occurrences found.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Reading data file .....");
        List<TagEntry> tagEntries = readTagsFile(TAGS_FILE);
        Scanner scanner = new Scanner(System.in);

        printPopularTags(tagEntries);

        while (true) {
            System.out.print("Search by Tag or Tag Count? (Enter T or C... or EXIT to exit): ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase(EXIT_COMMAND)) {
                System.out.println("Bye!");
                break;
            } else if (command.equalsIgnoreCase(SEARCH_BY_TAG_COMMAND)) {
                searchByTag(tagEntries, scanner);
            } else if (command.equalsIgnoreCase(SEARCH_BY_TAG_COUNT_COMMAND)) {
                searchByTagCount(tagEntries, scanner);
            } else {
                System.out.println("Invalid command: " + command);
            }
        }
        scanner.close();
    }
}
