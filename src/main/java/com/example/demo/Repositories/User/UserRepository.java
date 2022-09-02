package com.example.demo.Repositories.User;

import com.example.demo.Models.User.AccountHolder;
import com.example.demo.Models.User.Admin;
import com.example.demo.Models.User.Dto.UserDto;
import com.example.demo.Models.User.ThirdPartUser;
import com.example.demo.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByName(String name);


}
