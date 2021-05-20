package com.skandy.skandy.controller;

import com.skandy.skandy.dao.entity.Apartment;
import com.skandy.skandy.service.AppartmentService;
import com.skandy.skandy.service.UploadPdfText;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SkandyController {
    public final AppartmentService appartmentService;
    public final UploadPdfText uploadPdfText;

    @RequestMapping(value = "/skandy/{house}/upload",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<Apartment>> upload(@PathVariable("house") Integer house) throws IOException {
        uploadPdfText.upload(house);

        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/skandy/{house}/corp/{corp}/upload",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<Apartment>> upload(@PathVariable("house") Integer house, @PathVariable("corp") Integer corp) throws IOException {
        uploadPdfText.uploadWithCorpus(house, corp);

        return ResponseEntity.ok(null);
    }


    @RequestMapping(value = "/skandy/{house}/flat",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<Apartment>> getAll() {

        List<Apartment> all = appartmentService.getAll();

        return ResponseEntity.ok(all);
    }

    @RequestMapping(value = "/skandy/{house}/flat/{flat}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<Apartment>> get(@PathVariable("flat") Integer flat, @PathVariable("house") Integer house) {

        List<Apartment> apartment = appartmentService.getByHouseAndFlat(house, flat);

        return ResponseEntity.ok(apartment);
    }
}
