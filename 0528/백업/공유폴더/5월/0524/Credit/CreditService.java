package com.demo.gram.service;

import com.demo.gram.entity.Credit;
import com.demo.gram.entity.Members;
import com.demo.gram.repository.CreditRepository;
import com.demo.gram.repository.MembersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

  @Autowired
  private CreditRepository creditRepository;

  @Autowired
  private MembersRepository memberRepository;

  @Transactional
  public Credit addCredit(Long memberId, int amount) {
    Members member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
    Credit credit = Credit.builder()
        .member(member)
        .amount(amount)
        .build();
    member.getCredits().add(credit);
    memberRepository.save(member);
    return creditRepository.save(credit);
  }
}
