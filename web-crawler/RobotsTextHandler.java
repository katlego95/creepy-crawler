import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Handle reading and processing the robots.txt file
 * of a given domain. It checks whether a URL is allowed
 * based on the rules specified in the robots.txt file.
 */
public class RobotsTextHandler {
    private Set<String> disallowedPaths = new HashSet<>();
    private String domain;

    public RobotsTextHandler(String domain) throws IOException {
        this.domain = domain;
        robotsText();
    }

    /**
     * fetches and reads the robots.txt file from the domain and stores the disallowed paths in set.
     * @throws IOException If an error occurs while fetching or reading the robots.txt file.
     */
    private void robotsText() throws IOException {

    }

}