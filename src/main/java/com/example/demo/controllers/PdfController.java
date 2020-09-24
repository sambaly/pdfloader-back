package com.example.demo.controllers;

import com.example.demo.services.PdfService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    /**
     * This method permits us to get the pdf
     * file from the front end part and do
     * transformations on it.
     * @param postedPdf
     * @throws IOException
     */
    @PostMapping(value = "/pdf-loaded")
    public void getPostedPdf(@RequestParam("postedPdf") MultipartFile postedPdf) throws IOException {

        // Original pdf got back after conversion of the multipart file
        File pdfFile = this.pdfService.convertPdfToFile(postedPdf);
        int counter = 0;

        // Stripping the file to read data in it
        try (PDDocument document = PDDocument.load(pdfFile)) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                counter = this.pdfService.getOccurrencesOfArticleWriterInPDF("Sambaly KOTE", counter, pdfFileInText);

                System.out.println(counter);

                // split by whitespace
                // String lines[] = pdfFileInText.split("\\r\\n\\r\\n");
                // System.out.println(pdfFileInText);
                /*counter = (int) List.of(pdfFileInText).parallelStream()
                        .filter(word -> word.equalsIgnoreCase("sambaly kote"))
                        .count();
                System.out.println(counter);
                for (String line : lines) {
                    System.out.println(line);
                }*/

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
