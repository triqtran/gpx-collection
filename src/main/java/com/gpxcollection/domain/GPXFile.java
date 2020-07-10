package com.gpxcollection.domain;

import io.jenetics.jpx.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Document(collection = "GPXFile")
public class GPXFile implements Serializable {

    @Id
    private String id;

    private String version;

    private String creator;

    private String metadataName;

    private String metadataDescription;

    private String metadataTime;

    private String authorName;

    private String gpxFilePath;

    public GPXFile() {}

    public GPXFile(String version, String creator, String metadataName, String metadataDescription, String metadataTime, String authorName, String gpxFilePath) {
        this.version = version;
        this.creator = creator;
        this.metadataName = metadataName;
        this.metadataDescription = metadataDescription;
        this.metadataTime = metadataTime;
        this.authorName = authorName;
        this.gpxFilePath = gpxFilePath;
    }

    public GPXFile(GPX gpx, String gpxFilePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.version = gpx.getVersion();
        this.creator = gpx.getCreator();
        this.metadataName = gpx.getMetadata().flatMap(Metadata::getName).orElse("");
        this.metadataDescription = gpx.getMetadata().flatMap(Metadata::getDescription).orElse("");
        this.metadataTime = gpx.getMetadata().flatMap(Metadata::getTime).orElse(ZonedDateTime.now()).format(formatter);
        this.authorName = gpx.getMetadata().flatMap(Metadata::getAuthor).flatMap(Person::getName).orElse("");
        this.gpxFilePath = gpxFilePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMetadataName() {
        return metadataName;
    }

    public void setMetadataName(String metadataName) {
        this.metadataName = metadataName;
    }

    public String getMetadataDescription() {
        return metadataDescription;
    }

    public void setMetadataDescription(String metadataDescription) {
        this.metadataDescription = metadataDescription;
    }

    public String getMetadataTime() {
        return metadataTime;
    }

    public void setMetadataTime(String metadataTime) {
        this.metadataTime = metadataTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String retrieveGpxFilePath() {
        return gpxFilePath;
    }

    public void setGpxFilePath(String gpxFilePath) {
        this.gpxFilePath = gpxFilePath;
    }

    @Override
    public String toString() {
        return "GPXFile{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", creator='" + creator + '\'' +
                ", metadataName='" + metadataName + '\'' +
                ", metadataDescription='" + metadataDescription + '\'' +
                ", metadataTime=" + metadataTime +
                ", authorName='" + authorName + '\'' +
                ", gpxFilePath='" + gpxFilePath + '\'' +
                '}';
    }
}
