import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.Set;

import org.junit.*;

public class UrlRulesTest {

    @Test
    public void testCrawlFileType() throws IOException, URISyntaxException {
        // Test that files (e.g., PDFs) are skipped

        // mock or a specific URL known to return non-HTML content
        String startURL = "https://example.com/sample.pdf";
        URI uri = new URI(startURL);

        // Verify that the non-HTML page was skipped
        assertFalse(UrlRules.isUrlValid(uri), "Visited pages should be empty for non-HTML content");
    }
}