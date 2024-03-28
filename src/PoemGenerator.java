import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PoemGenerator {
    // Function to generate a random integer within a given range
    public static int randomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    // Function to capitalize the first letter of each word in a string
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Function to read words and phrases from a text file hosted online
    public static List<String> readWordsFromURL(String urlString) throws IOException {
        List<String> words = new ArrayList<>();
        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            words.add(line);
        }
        reader.close();
        return words;
    }

    // Function to generate a random line of the poem
    public static String generateLine(List<String> words) {
        Random rand = new Random();
        String line = "";

        // Choose random words
        String word1 = words.get(rand.nextInt(words.size()));
        String word2 = words.get(rand.nextInt(words.size()));
        String word3 = words.get(rand.nextInt(words.size()));

        // Create the line
        line = capitalize(word1) + " " + word2 + " " + word3 + ".";
        return line;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // URL of the text file containing words and phrases
        String url = "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt";

        System.out.print("Enter the topic for your poem: ");
        String topic = scanner.nextLine();

        try {
            // Read words from the URL
            List<String> words = readWordsFromURL(url);

            // Add the topic to the list of words
            words.add(topic);

            System.out.println("\nYour poem:\n");

            // Generate and print poem lines
            for (int i = 0; i < 4; i++) {
                String line = generateLine(words);
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading words from URL: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
