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

    /**
     * Checks given the path for validity
     * 
     * @return True if path is valid
     */
    public static boolean isUrlValid(URI uri) {
        String path = uri.getPath().toLowerCase();

        if (path != null && !path.isEmpty()) {
            return !checkRules(path);
        } else {
            return false;
        }

    }

    /**
     * Checks the path for validity
     * 
     * @return True if path has has satisfied all rules
     */
    private static boolean checkRules(String path) {
        return isFileExt(path);
    }

    private static boolean isFileExt(String path) {
        // URLs that match excluded file extensions are invalid
        for (String ext : EXCLUDED_EXTENSIONS) {
            if (path.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

}