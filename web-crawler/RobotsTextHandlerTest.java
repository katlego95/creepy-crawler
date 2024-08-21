import static org.junit.jupiter.api.Assertions.assertFalse;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import org.junit.*;
import java.net.URI;

public class RobotsTextHandlerTest{
    @Test
    public void test() {
        assert(true);
    }

    @Test
    public void testIsAllowedURL() throws IOException, URISyntaxException {
        // Testing the RobotsTxtHandler's isAllowed method

        URI uri = new URI("https://sedna.com/");
        RobotsTextHandler roboTxtHandler = new RobotsTextHandler(uri.getHost());

        // Mock the response of RobotsTxtHandler to always return false for a particular URL
        Set<String> disallowedpages = new HashSet<>();
        disallowedpages.add("https://sedna.com/legal");
        disallowedpages.add("https://sedna.com/documentation");

        for (String pages : disallowedpages){
            assertFalse(roboTxtHandler.isUrlAllowed(pages), "Disallowed URL should not be crawled");
        }
    }
}