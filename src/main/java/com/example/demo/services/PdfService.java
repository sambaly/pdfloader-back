package com.example.demo.services;

import com.example.demo.domain.ArticleWriter;
import com.example.demo.domain.SearchResult;
import com.example.demo.repositories.SearchResultRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    private SearchResultRepository searchResultRepository;

    /**
     * Method that permits us to convert Multipart file
     * received back to pdf.
     * This will allow us to do operations on the pdf file.
     * @param file
     * @return
     * @throws IOException
     */
    public File convertPdfToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        convertedFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    /**
     * This method enables us to search for a word
     * in the text and give back the
     * number of occurrences of this word.
     * @param counters
     * @param pdfFileInText
     * @return
     */
    public List<SearchResult> getOccurrencesOfArticleWriterInPDF(List<ArticleWriter> articleWriters, int[] counters, String pdfFileInText) {
        List<SearchResult> searchResults = new ArrayList<>();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String dateFormatee = date.format(formatter);

        for (int i = 0; i < articleWriters.size(); i++) {
            int lastIndex = 0;

            while (lastIndex != -1) {
                lastIndex = pdfFileInText.indexOf(articleWriters.get(i).getName(), lastIndex);

                if (lastIndex != -1) {
                    counters[i]++;
                    lastIndex += articleWriters.get(i).getName().length();
                }
            }
            searchResults.add(new SearchResult(null, articleWriters.get(i).getName(), counters[i], dateFormatee));
        }
        /*int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = pdfFileInText.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                counter++;
                lastIndex += findStr.length();
            }
        }
        return counter;*/
        return searchResults;
    }

    public void GetResultsFromPdfFile(File pdfFile, List<ArticleWriter> articleWriters) {
        int[] counters = new int[articleWriters.toArray().length];

        // Stripping the file to read data in it
        try (PDDocument document = PDDocument.load(pdfFile)) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                List<SearchResult> results = getOccurrencesOfArticleWriterInPDF(articleWriters, counters, pdfFileInText);
                this.searchResultRepository.saveAll(results);

                System.out.println(results);
                // System.out.println(counters);
                // System.out.println(articleWriters);

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
