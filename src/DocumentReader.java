
import java.io.File;
import java.io.IOException;

public interface DocumentReader {
    String readDocument(File file) throws IOException;
}