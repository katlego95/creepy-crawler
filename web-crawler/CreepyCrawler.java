import java.util.Scanner;
import java.util.Set;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CreepyCrawler {

    // Sedna URL
    private static final String sednaURL = "https://sedna.com/";

    // A set to keep track of visited pages to avoid duplicate visits
    private Set<String> pages = new HashSet<>();

    // The domain of the website to be crawled
    private String domain;

    /**
     * Constructor to initialize the crawler with the start URL.
     * 
     * @param startURL The starting URL from where the crawl begins.
     * @throws IOException If an error occurs while processing the URL.
     */
    public CreepyCrawler(String URL) throws IOException {
        System.out.print("Crawling..." + "\n");
    }

    /**
     * The main method that handles the crawling process.
     * 
     * @param url The URL of the page to be crawled.
     */
    private void creep(String url) {

    }

    /**
     * Getter method to retrieve the set of visited pages.
     * 
     * @return A set containing all the URLs that have been visited.
     */
    public Set<String> getVisitedPages() {
        return pages;
    }

    public static void errorMessage() {
        System.out.print("Please select a valid option! " + "\n");
    }

    /**
     * The main entry point of the program.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        Scanner scanner = null;

        // Menu options
        List<Integer> menuOptions = Arrays.asList(0, 1);

        try {
            scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Please select an option or type exit to end program ");

                System.out.print("\n" + "0: Start crawling Sedna.com");

                System.out.print("\n" + "1: Crawl custom URL ");

                System.out.print("\n");

                String input = scanner.nextLine();

                if (input.equals("exit")) {
                    // End the program
                    System.out.println("Exiting program...");
                    break;
                } else {
                    try {
                        int menuSelection = Integer.parseInt(input);

                        if (menuOptions.contains(menuSelection)) {

                            if (menuSelection == menuOptions.get(0)) {
                                try {
                                    // Create an instance of the WebCrawler and start crawling
                                    CreepyCrawler crawler = new CreepyCrawler(sednaURL);

                                } catch (IOException e) {
                                    System.err.println("Error starting the crawl: " + e.getMessage());
                                }
                            } else {
                                System.out.println("Enter URL: ");
                                String customURL = scanner.nextLine();

                                try {
                                    // Create an instance of the WebCrawler and start crawling
                                    CreepyCrawler crawler = new CreepyCrawler(customURL);

                                } catch (IOException e) {
                                    System.err.println("Error starting the crawl: " + e.getMessage());
                                }
                            }
                        } else {
                            errorMessage();
                        }
                    } catch (NumberFormatException e) {
                        errorMessage();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            if (scanner != null) {
                System.out.println("Scanner Closed!");
                scanner.close(); // Ensure the scanner is closed to free resources
            }
        }
    }
}
