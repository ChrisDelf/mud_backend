package com.dev.mud_backend.repository;

import com.dev.mud_backend.models.Map;
import org.springframework.data.repository.CrudRepository;

public interface MapRepository extends CrudRepository<Map, Long> {

    Map findByMapid(long mapid);

}
