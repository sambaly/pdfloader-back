package com.example.demo.services;

import com.example.demo.domain.ArticleWriter;
import com.example.demo.domain.SearchResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

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

        for (int i = 0; i < articleWriters.size(); i++) {
            int lastIndex = 0;

            while (lastIndex != -1) {
                lastIndex = pdfFileInText.indexOf(articleWriters.get(i).getName(), lastIndex);

                if (lastIndex != -1) {
                    counters[i]++;
                    lastIndex += articleWriters.get(i).getName().length();
                }
            }
            searchResults.add(new SearchResult(null, articleWriters.get(i).getName(), counters[i]));
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

}
