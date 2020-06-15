package com.dev.mud_backend.repository;

import com.dev.mud_backend.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
