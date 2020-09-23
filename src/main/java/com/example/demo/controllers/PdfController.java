package com.example.demo.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api")
public class PdfController {

    @PostMapping(value = "/pdf-loaded")
    public void getPostedPdf(@RequestParam("postedPdf") MultipartFile postedPdf) {
        System.out.println("received file " + postedPdf);
    }

}
