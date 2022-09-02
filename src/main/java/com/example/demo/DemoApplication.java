package com.example.demo;

import com.example.demo.Models.Account.Checking;
import com.example.demo.Models.User.AccountHolder;
import com.example.demo.Models.User.Address;
import com.example.demo.Models.User.Admin;
import com.example.demo.Models.User.Role;
import com.example.demo.Repositories.Account.CheckingRepository;
import com.example.demo.Repositories.User.AccountHolderRepository;
import com.example.demo.Repositories.User.AdminRepository;
import com.example.demo.Repositories.User.RoleRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	AccountHolderRepository accountHolderRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	CheckingRepository checkingRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		adminRepository.save(new Admin("Paula", "1234"));
		AccountHolder accountHolder = accountHolderRepository.save(new AccountHolder("Joana","1234",null, null, LocalDate.of(1990,10, 05),new Address(),null));
		checkingRepository.save(new Checking(BigDecimal.valueOf(100),accountHolder,null,null,null,null,null));


	}


}
