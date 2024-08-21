import java.util.Scanner;
import java.util.Set;

import org.jsoup.Connection;

//import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    // robots.txt handler object
    private RobotsTextHandler roboTxtHandler;

    // List of file extensions to exclude from crawling
    private static final Set<String> EXCLUDED_EXTENSIONS = Set.of(
            ".pdf", ".jpg", ".jpeg", ".png", ".gif", ".bmp",
            ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx",
            ".mp4", ".avi", ".mkv", ".mov", ".zip", ".rar");

    /**
     * Constructor to initialize the crawler with the start URL.
     * 
     * @param startURL The starting URL from where the crawl begins.
     * @throws IOException        If an error occurs while processing the URL.
     * @throws URISyntaxException
     */
    public CreepyCrawler(String newURL) throws IOException, URISyntaxException {
        System.out.print("Creeping..." + "\n");
        URI uri = new URI(newURL);
        this.domain = uri.getHost();

        // Initialize the RobotsTxtHandler for the domain
        this.roboTxtHandler = new RobotsTextHandler(this.domain);

        // Initialize the creeping from the start URL
        creep(newURL);
    }

    /**
     * The main method that handles the creeping process.
     * 
     * @param url The URL of the page to be creeped.
     */
    private void creep(String url) {

        // Check if the page is allowed, if the page has been added to directory, if the
        // url is a file and if the url is in the domain
        if (this.roboTxtHandler.isUrlAllowed(url) && !pages.contains(url) && url.contains(domain)) {
            try {

                // Parse the URL to check its scheme
                URI uri = new URI(url);
                String scheme = uri.getScheme();
                String path = uri.getPath().toLowerCase();

                // Skip URLs that match excluded file extensions
                for (String ext : EXCLUDED_EXTENSIONS) {
                    if (path.endsWith(ext)) {
                        // System.out.println("Skipping file URL: " + url);
                        return;
                    }
                }

                // Skip URLs with the 'file' scheme or other invalid schemes
                if (scheme == null || (!scheme.equals("http") && !scheme.equals("https"))) {
                    // System.err.println("Skipping invalid URL: " + url);
                    return;
                }

                // Connect to the URL and get the response
                Connection connection = Jsoup.connect(url);
                Connection.Response response = connection.execute();

                // Check if the page is accessible (status code 200)
                if (response.statusCode() == 200) {
                    Document document = connection.get();
                    pages.add(url);
                    System.out.println("URL: " + url);

                    Elements links = document.select("a[href]");
                    for (Element link : links) {
                        String absUrl = link.attr("abs:href");
                        if (absUrl.contains(domain)) {
                            creep(absUrl);
                        }
                    }
                } else {
                    return;
                    // System.err.println("Error, cannot access: " + url + " (HTTP " +
                    // response.statusCode() + ")");
                }
            } catch (IOException | URISyntaxException e) {
                System.err.println("Error accessing the URL: " + url);
            }
        }
    }

    /**
     * Getter method to retrieve the set of visited pages.
     * 
     * @return A set containing all the URLs that have been visited.
     */
    public Set<String> getVisitedPages() {
        return pages;
    }

    public static void printPages(Set<String> visitedPages) {
        System.out.println("Crawled pages: " + "\n");
        for (String page : visitedPages) {
            System.out.println(page);
        }
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

                if (input.toLowerCase().equals("exit")) {
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
                                    Set<String> pages = crawler.getVisitedPages();

                                    System.out.println("Crawling " + sednaURL);
                                    printPages(pages);
                                } catch (IOException | URISyntaxException e) {
                                    System.err.println("Error starting the crawl: " + e.getMessage());
                                }
                            } else {
                                System.out.println("Enter URL: ");
                                String customURL = scanner.nextLine();

                                try {
                                    // Create an instance of the WebCrawler and start crawling
                                    CreepyCrawler crawler = new CreepyCrawler(customURL);
                                    Set<String> pages = crawler.getVisitedPages();
                                    System.out.println("Crawling " + customURL);
                                    printPages(pages);

                                } catch (IOException | URISyntaxException e) {
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
