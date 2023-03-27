package com.ergun.videos.dataAccess;

import com.ergun.videos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByAllowNotificationTrue();
    User findByUsername(String username);
    User findById(int id);
    User findByEmail(String email);
}
