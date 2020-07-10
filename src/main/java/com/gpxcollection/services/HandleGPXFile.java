package com.gpxcollection.services;

import com.gpxcollection.domain.GPXFile;
import io.jenetics.jpx.GPX;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HandleGPXFile {

    public GPX read(String filePath) {
        try {
            return GPX.read(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
