package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController

public class ThirdPartUserController implements com.example.demo.Controller.Interfaces.ThirdPartUserInterface {


    @Override
    @PostMapping("/transfer-send/{key}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void sendMoney(@PathVariable(name="key") String hashedKey, @RequestParam Long accountId, @RequestParam BigDecimal amount, @RequestParam Long accountSecretKey) {

    }

    @Override
    @PostMapping ("/transfer-receive/{key}")
    @ResponseStatus (value = HttpStatus.ACCEPTED)
    public void receiveMoney(@PathVariable(name = "key") String hashedKey, @RequestParam Long accountId, @RequestParam BigDecimal amount, @RequestParam Long accountSecretKey) {

    }
}
