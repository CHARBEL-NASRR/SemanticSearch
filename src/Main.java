import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String documentsPath = FilesUtil.getResourcesDocumentsPath();
            Set<String> documentFiles = FilesUtil.listFiles(documentsPath);

            List<File> documentList = documentFiles.stream()
                    .map(File::new)
                    .toList();

            SemanticSearch semanticSearch = new SemanticSearch();
            List<String> carResults = semanticSearch.searchForCars(documentList);

            if (!carResults.isEmpty()) {
                System.out.println("Results for cars:");
                carResults.forEach(System.out::println);
            } else {
                System.out.println("No results found for cars.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}