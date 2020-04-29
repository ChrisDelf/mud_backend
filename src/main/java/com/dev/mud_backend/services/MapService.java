package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Map;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MapService {
    List<Map> getMap(Long userid);
}
