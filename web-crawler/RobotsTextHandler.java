import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
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

    public RobotsTextHandler(String domain) throws IOException, URISyntaxException {
        this.domain = domain;
        robotsText();
    }

    /**
     * fetches and reads the robots.txt file from the domain and stores the
     * disallowed paths in set.
     * 
     * @throws IOException        If an error occurs while fetching or reading the
     *                            robots.txt file.
     * @throws URISyntaxException
     */
    private void robotsText() throws IOException, URISyntaxException {

        URI robotsTxtUri = new URI("https", domain, "/robots.txt", null);

        // Convert URI to URL to open a connection
        URL url = robotsTxtUri.toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Process disallowed paths for all user-agents
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    // Skip empty lines or comments
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue;
                    }

                    if (line.startsWith("Disallow:")) {
                        String path = line.substring("Disallow:".length()).trim();
                        if (!path.isEmpty()) {
                            disallowedPaths.add(path);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error reading robots.txt lines - " + e.getMessage());
            }
        } else {
            System.err.println("No robots.txt file found for domain: " + domain);
        }
    }

    /**
     * Checks if a given URL is allowed to be crawled based on the robots.txt rules.
     * 
     * @param url The URL to be checked.
     * @return True if the URL is allowed, false otherwise.
     */
    public boolean isUrlAllowed(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();

            // Ensure path is not null before processing
            if (path == null) {
                return true; // If there's no path, assume it's allowed
            }

            for (String disallowedPath : disallowedPaths) {
                // Ensure disallowedPath is not null before calling startsWith
                if (disallowedPath != null && path.startsWith(disallowedPath)) {
                    return false; // URL is disallowed
                }
            }

        } catch (URISyntaxException e) {
            System.err.println("Error processing the URL: " + url);
        }
        return true; // If no disallowed path matches, the URL is allowed
    }
}
