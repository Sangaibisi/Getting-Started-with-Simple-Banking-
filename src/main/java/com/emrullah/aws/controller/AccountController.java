package com.emrullah.aws.controller;

import com.emrullah.aws.model.exception.InsufficientBalanceException;
import com.emrullah.aws.services.IAccountService;
import com.emrullah.aws.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        return ResponseEntity.accepted().headers(headers).body(theAccount);
    }

    @RequestMapping(value = "/credit/{id}",method = RequestMethod.GET)
    public ResponseEntity credit(@PathVariable("id") int id, @RequestBody Map requestBody) {
        HttpHeaders headers = new HttpHeaders();
        double amount = Double.parseDouble((String) requestBody.get("amount"));
        try {
            accountService.credit(amount,id);
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