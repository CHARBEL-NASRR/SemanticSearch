
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SemanticSearch {

    private final List<DocumentReader> documentReaders;

    public SemanticSearch() {
        // Initialize the document readers for different file types
        documentReaders = List.of(
                new PDFReader(),
                new DOCXReader(),
                new XMLReader(),
                new JSONReader()
        );
    }

    public List<String> performSemanticSearch(String query, List<File> documents) {
        List<String> results = new ArrayList<>();

        for (File document : documents) {
            try {
                String content = readDocument(document);
                if (isSemanticMatch(content, query)) {
                    results.add(String.format("[*] found in: %s", document.getName()));
                    results.add(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    private String readDocument(File document) throws IOException {
        String fileExtension = getFileExtension(document);
        for (DocumentReader reader : documentReaders) {
            if (reader.supportsExtension(fileExtension)) {
                return reader.readDocument(document);
            }
        }
        return ""; // Unsupported file type
    }

    private boolean isSemanticMatch(String content, String query) {
        // Implement your semantic matching logic here
        // For simplicity, this example uses a basic case-insensitive match
        return content.toLowerCase().contains(query.toLowerCase());
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex + 1).toLowerCase();
        }
        return "";
    }
}