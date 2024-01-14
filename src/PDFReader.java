import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.io.File;
import java.io.IOException;

public class PDFReader implements DocumentReader {
    @Override
    public String readDocument(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        return pdfTextStripper.getText(document);
    }


}