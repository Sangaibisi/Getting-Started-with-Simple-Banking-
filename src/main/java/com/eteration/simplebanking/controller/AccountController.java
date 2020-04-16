package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.EtarationException.InsufficientBalanceException;
import com.eteration.simplebanking.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        Account theAccount;
        try {
            theAccount = accountService.findById(id);

        }catch (RuntimeException e) {
            headers.add("Account-Header", e.getMessage());
            return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/credit/{id}/{value}",method = RequestMethod.GET)
    public ResponseEntity credit(@PathVariable("id") int id, @PathVariable("value") int value) {
        HttpHeaders headers = new HttpHeaders();
        try {
            accountService.credit(value,id);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }  catch (Exception e) {
            headers.add("Account-Header", e.getMessage());
            return new ResponseEntity<>(headers,HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/debit/{id2}/{value2}",method = RequestMethod.GET)
    public ResponseEntity debit(@PathVariable("id2") int id, @PathVariable("value2") int value){
        HttpHeaders headers = new HttpHeaders();
        try {
            accountService.debit(value,id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InsufficientBalanceException e) {
            headers.add("Account-Header", e.getMessage());
            return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            headers.add("Account-Header", e.getMessage());
            return new ResponseEntity<>(headers,HttpStatus.NOT_MODIFIED);
        }
    }
}