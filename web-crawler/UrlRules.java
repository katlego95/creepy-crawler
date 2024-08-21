import java.net.URI;
import java.util.Set;

/**
 * Handle checking whether a given url is valid
 * based on predefined rules
 */
public class UrlRules {
    // List of file extensions to exclude from crawling
    private static final Set<String> EXCLUDED_EXTENSIONS = Set.of(
            ".pdf", ".jpg", ".jpeg", ".png", ".gif", ".bmp",
            ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx",
            ".mp4", ".avi", ".mkv", ".mov", ".zip", ".rar");

    // constructor
    public UrlRules() {
    }

    // TO DO:
    // creature rule to skip
    // Error accessing the URL: https://support.sedna.com/hc/en-us

    /**
     * Checks given the path for validity
     * 
     * @return True if path is valid
     */
    public static boolean isUrlValid(URI uri, String contentType) {
        String path = uri.getPath();
        String scheme = uri.getScheme();

        if (path != null && !path.isEmpty()) {
            return checkRules(path.toLowerCase(),scheme,contentType);
        } else {
            return false;
        }

    }

    /**
     * Checks the path for validity
     * 
     * @return True if path has has satisfied all rules
     */
    private static boolean checkRules(String path, String scheme, String contentType ) {
        return fileExt(path) && scheme(scheme) && content(contentType);
    }

    private static boolean fileExt(String path) {
        // URLs that match excluded file extensions are invalid
        for (String ext : EXCLUDED_EXTENSIONS) {
            if (path.endsWith(ext)) {
                return false;
            }
        }
        return true;
    }

    private static boolean scheme (String scheme) {
        if (scheme == null || (!scheme.equals("http") && !scheme.equals("https"))) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean content (String contentType) {
        // Check if the content is HTML
        if (contentType != null && contentType.contains("text/html")) {
            return true;
        } else {
            return false;
        }
    }

}