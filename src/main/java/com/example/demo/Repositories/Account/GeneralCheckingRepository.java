package com.example.demo.Repositories.Account;

import com.example.demo.Models.Account.Checking;
import com.example.demo.Models.Account.GeneralChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GeneralCheckingRepository extends JpaRepository<GeneralChecking, Long> {
    Checking findBySecretKey(Long secretKey);
}
