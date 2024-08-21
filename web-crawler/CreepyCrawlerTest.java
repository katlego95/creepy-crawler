import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URISyntaxException;
import java.io.IOException;
import java.util.Set;

import org.junit.*;

public class CreepyCrawlerTest{
    @Test
    public void test() {
assert(true);
    }

    @Test
    public void testCrawlValidURL() throws IOException, URISyntaxException {
        // Test crawling a valid URL with HTML content

        // Mocking or using a real URL depending on your test setup
        String startURL = "https://gardens-mvp.web.app/";
        CreepyCrawler creepyCrawler = new CreepyCrawler(startURL);

        Set<String> visitedPages = creepyCrawler.getVisitedPages();

        // Assuming your test environment returns a known set of URLs
        assertTrue(visitedPages.contains(startURL),"Visited pages should contain the start URL");
        assertFalse(visitedPages.isEmpty(),"Visited pages should not be empty");
    }
}
