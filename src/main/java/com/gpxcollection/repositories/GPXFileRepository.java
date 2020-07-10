package com.gpxcollection.repositories;

import com.gpxcollection.domain.GPXFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GPXFileRepository extends MongoRepository<GPXFile, String> {

}
