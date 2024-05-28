package com.demo.gram.controller;

import com.demo.gram.dto.CreditRequest;
import com.demo.gram.entity.Credit;
import com.demo.gram.service.CreditService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditController {


  private final CreditService creditService;

  @PostMapping("/add")
  public ResponseEntity<?> addCredit(@RequestBody CreditRequest creditRequest) {
    try {
      Credit credit = creditService.addCredit(creditRequest.getMemberId(), creditRequest.getAmount());
      return ResponseEntity.ok(credit);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}

