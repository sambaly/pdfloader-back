package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
     * @param counter
     * @param pdfFileInText
     * @return
     */
    public int getOccurrencesOfArticleWriterInPDF(String findStr, int counter, String pdfFileInText) {
        int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = pdfFileInText.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                counter++;
                lastIndex += findStr.length();
            }
        }
        return counter;
    }

}
