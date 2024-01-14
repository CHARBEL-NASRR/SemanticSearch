import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DOCXReader implements DocumentReader {
    @Override
    public String readDocument(File file) throws IOException {
        try (XWPFDocument document = new XWPFDocument(Files.newInputStream(file.toPath()))) {
            XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
            return wordExtractor.getText();
        }
    }
}