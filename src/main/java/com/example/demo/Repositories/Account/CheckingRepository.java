package com.example.demo.Repositories.Account;

import com.example.demo.Models.Account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

    Checking findBySecretKey(Long secretKey);
}
