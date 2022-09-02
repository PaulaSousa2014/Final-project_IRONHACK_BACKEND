package com.example.demo.Repositories.User;

import com.example.demo.Models.User.ThirdPartUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartUserRepository extends JpaRepository<ThirdPartUser, Long> {
    ThirdPartUser findByHashedKey(String hashedKey);

}
