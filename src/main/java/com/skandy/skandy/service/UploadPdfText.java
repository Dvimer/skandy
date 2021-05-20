package com.skandy.skandy.service;

import com.skandy.skandy.dao.entity.Apartment;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UploadPdfText {

    private final AppartmentService appartmentService;

    public void upload(Integer houseNumber) throws IOException {
        String baseFileName = "src/main/resources/skandy" + houseNumber + ".pdf";
        //Loading an existing document
        File file = new File(baseFileName);
        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);

        String[] split = text.split("Корпус");
        Integer houseBuilding = 0;
        for (int i = 0; i < split.length; i++) {
            houseBuilding++;
            String[] splitFlat = split[i].split(houseNumber + "-");
            System.out.println(houseBuilding);
            for (String s : splitFlat) {
                if (s.contains("Квартира")) {
                    String appartment = s.split("2.99")[0];
                    String[] fields = appartment.split(" ");
                    saveAppartment(houseBuilding, fields, houseNumber);
                }
            }
        }

        //Closing the document
        document.close();
    }

    public void uploadWithCorpus(Integer houseNumber, Integer corpus) throws IOException {
        String baseFileName = "src/main/resources/skandy" + houseNumber + "." + corpus + ".pdf";
        //Loading an existing document
        File file = new File(baseFileName);
        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);

        String[] splitFlat = text.split(houseNumber + "." + corpus + "-");
        for (String s : splitFlat) {
            if (s.contains("Квартира")) {
                String appartment = s.split("2.99")[0];
                String[] fields = appartment.split(" ");
                saveAppartment(corpus, fields, houseNumber);
            }
        }


        //Closing the document
        document.close();
    }


    private Apartment saveAppartment(Integer houseBuilding, String[] fields, Integer houseNumber) {
        return appartmentService.save(
                Integer.parseInt(fields[0]),
                Integer.parseInt(fields[2]),
                Integer.parseInt(fields[3]),
                Float.parseFloat(fields[4]),
                Integer.parseInt(fields[5]),
                houseBuilding,
                houseNumber);
    }
}
