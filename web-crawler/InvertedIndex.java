import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class InvertedIndex {

    // The key is a term (word), and the value is a set of URLs where the term appears.
    private Map<String, Set<String>> index;

    public InvertedIndex() {
        this.index = new HashMap<>();
    }

    /**
     * Index all documents (URLs) in the visited pages set.
     *
     * For each URL, the content is fetched, tokenized, and indexed.
     * 
     * @param visitedPages A set of URLs that have been creeped by our crawler.
     */
    public void indexVisitedPages(Set<String> visitedPages) {
        for (String url : visitedPages) {
            try {
                // Fetch the content of the URL
                String content = fetchContent(url);

                // Index the content
                if (content != null) {
                    indexDocument(url, content);
                }
            } catch (IOException e) {
                System.err.println("Error fetching content from URL: " + url + " - " + e.getMessage());
            }
        }
    }

    /**
     * Fetches the HTML content of a given URL.
     *
     * @param url The URL to fetch content from.
     * @return The text content of the page.
     * @throws IOException If an error occurs during fetching.
     */
    private String fetchContent(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.text(); // Returns the text content of the page (stripped of HTML tags)
    }

    /**
     * Tokenizes and indexes the content of a document (web page).
     * Each word (token) is added to the inverted index, associated with the document's URL.
     *
     * @param url     The URL of the document being indexed.
     * @param content The text content of the document.
     */
    private void indexDocument(String url, String content) {

        // Tokenize the content into individual words
        String[] tokens = tokenize(content);

        for (String token : tokens) {
            // If the token is not already in the index, create a new entry
            index.computeIfAbsent(token, k -> new HashSet<>()).add(url);
        }
    }

     /**
     * Tokenizes the text content by performing the following steps:
     * 1. Converts the content to lowercase to ensure case-insensitive indexing.
     * 2. Removes non-alphanumeric characters (e.g., punctuation) to focus on actual words.
     * 3. Splits the content into words (tokens) based on whitespace.
     *
     * @param content The text content to tokenize.
     * @return An array of tokens (words).
     */
    private String[] tokenize(String content) {
        return content.toLowerCase()
                      .replaceAll("[^a-zA-Z0-9\\s]", "") // Remove all characters except letters, numbers, and whitespace
                      .split("\\s+"); // Split by one or more whitespace characters (e.g., spaces, tabs, newlines)
    }

    /**
     * Searches the index for documents containing a given term.
     * The search is case-insensitive due to the lowercase conversion during tokenization.
     *
     * @param term The term to search for.
     * @return A set of URLs where the term appears, or an empty set if the term is not found.
     */
    public Set<String> search(String term) {
        term = term.toLowerCase(); // Normalize the search term to lowercase
        return index.getOrDefault(term, Collections.emptySet());
    }

    /**
     * Prints the entire inverted index.
     * Each entry consists of a term and the set of URLs where that term appears.
     * This method is useful for debugging or inspecting the index.
     */
    public void printIndex() {
        for (Map.Entry<String, Set<String>> entry : index.entrySet()) {
            System.out.println("Term: " + entry.getKey() + " -> Documents: " + entry.getValue());
        }
    }
}
