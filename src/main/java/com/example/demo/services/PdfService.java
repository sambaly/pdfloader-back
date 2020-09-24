package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

}
