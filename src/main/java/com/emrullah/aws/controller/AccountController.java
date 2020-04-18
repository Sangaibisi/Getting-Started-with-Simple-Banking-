package com.emrullah.aws.controller;

import com.alibaba.fastjson.JSONObject;
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

        } catch (RuntimeException e) {
            headers.add("Account-Header", e.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.accepted().headers(headers).body(theAccount);
    }

    @RequestMapping(value = "/credit/{id}", method = RequestMethod.GET)
    public ResponseEntity credit(@PathVariable("id") int id, @RequestBody Map requestBody) {
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObject = new JSONObject();

        double amount = Double.parseDouble((String) requestBody.get("amount"));

        try {
            accountService.credit(amount, id);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("error", e.getMessage());
            jsonObject.put("status", "NOT OKEY");
            headers.add("Account-Header", e.getMessage());
            return ResponseEntity.accepted().headers(headers).body(jsonObject);
        }
    }

    @RequestMapping(value = "/debit/{id}", method = RequestMethod.GET)
    public ResponseEntity debit(@PathVariable("id") int id, @RequestBody Map requestBody) {
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObject = new JSONObject();

        double amount = Double.parseDouble((String) requestBody.get("amount"));

        try {
            accountService.debit(amount, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
            jsonObject.put("error", e.getMessage());
            jsonObject.put("status", "NOT OKEY");
            headers.add("Account-Header", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).headers(headers).body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("error", e.getMessage());
            jsonObject.put("status", "NOT OKEY");
            headers.add("Account-Header", e.getMessage());
            return ResponseEntity.badRequest().headers(headers).body(jsonObject);
        }
    }
}
