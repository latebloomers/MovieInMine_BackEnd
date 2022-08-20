package com.latebloomers.MovieInMine.repository;

import com.latebloomers.MovieInMine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
