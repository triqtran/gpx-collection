
package com.gpxcollection.controllers;

import com.gpxcollection.domain.GPXFile;
import com.gpxcollection.domain.ResponseData;
import com.gpxcollection.exception.StorageException;
import com.gpxcollection.repositories.GPXFileRepository;
import com.gpxcollection.services.HandleGPXFile;
import com.gpxcollection.services.StorageService;
import io.jenetics.jpx.GPX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/gpx")
public class GPXControler {

    @Autowired
    private StorageService storageService;

    @Autowired
    private HandleGPXFile handleGPXFile;

    @Autowired
    private GPXFileRepository gpxFileRepository;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseData> upload(@RequestParam MultipartFile file) {
        String filePath = storageService.uploadFile(file);
        GPX gpx = handleGPXFile.read(filePath);
        if(gpx == null) {
            Map<String, Object> failure = new HashMap<>();
            return ResponseData.FAILED("upload failed!");
        }
        GPXFile gpxFile = new GPXFile(gpx, filePath);
        gpxFileRepository.save(gpxFile);

        return ResponseData.SUCCESS(gpxFile);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseData> list() {
        List<GPXFile> gpxFileList = gpxFileRepository.findAll();
        return ResponseData.SUCCESS(gpxFileList);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseData> getDetailById(@PathVariable(value = "id") String id) {
        GPXFile gpxFile = gpxFileRepository.findById(id).get();
        GPX gpx = handleGPXFile.read(gpxFile.retrieveGpxFilePath());
        Map<String, Object> response = new HashMap<>();
        response.put("gpx", gpx);
        response.put("info", gpxFile);
        return ResponseData.SUCCESS(response);
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Map<String, Object>> handleStorageFileNotFound(StorageException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bad request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
