package com.dev.mud_backend.repository;


import com.dev.mud_backend.models.Monster;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster, Long> {
}
