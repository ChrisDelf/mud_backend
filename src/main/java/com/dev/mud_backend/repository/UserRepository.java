package com.dev.mud_backend.repository;

import com.dev.mud_backend.models.Role;
import com.dev.mud_backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
   User findById(long userid);
}
