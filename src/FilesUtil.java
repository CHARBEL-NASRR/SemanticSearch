
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesUtil {

    // ... (previous code)

    public static Set<String> listFiles(String path) throws IOException {
        Stream<Path> stream = Files.list(Paths.get(path));
        return stream.filter(file -> !Files.isDirectory(file))
                .map(Path::toString)
                .collect(Collectors.toSet());
    }

    public static String getResourcesDocumentsPath() throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resourceFolderURL = classLoader.getResource("documents");

        if (resourceFolderURL != null)
            return URLDecoder.decode(resourceFolderURL.getPath(), StandardCharsets.UTF_8);

        throw new IOException("Resources path not found");
    }

    public static File getResourceFile(String path) throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resourceFolderURL = classLoader.getResource(path);

        if (resourceFolderURL != null) {
            String decodedPath = URLDecoder.decode(resourceFolderURL.getFile(), StandardCharsets.UTF_8);
            return new File(decodedPath).getAbsoluteFile();
        }

        throw new IOException("Resource not found: " + path);
    }
}