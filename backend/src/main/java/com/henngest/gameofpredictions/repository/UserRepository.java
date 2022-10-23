package com.henngest.gameofpredictions.repository;

import com.henngest.gameofpredictions.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
