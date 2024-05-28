package com.example.hacker.service;

import com.example.hacker.dto.MembersDTO;
import com.example.hacker.entity.Members;
import com.example.hacker.repository.MembersRepository;
import com.example.hacker.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

  private final MembersRepository membersRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Long registMembersDTO(MembersDTO membersDTO) {
    membersDTO.setPassword(passwordEncoder.encode(membersDTO.getPassword()));
    return membersRepository.save(dtoToEntity(membersDTO)).getMno();
  }

  @Override
  public void updateMembersDTO(MembersDTO membersDTO) {
    membersRepository.save(dtoToEntity(membersDTO)).getMno();
  }

  @Override
  public void removeMembers(Long num) {
    membersRepository.deleteById(num);
  }

  @Override
  public MembersDTO get(Long num) {
    Optional<Members> result = membersRepository.findById(num);
    if (result.isPresent()) {
      return entityToDTO(result.get());
    }
    return null;
  }

  @Override
  public List<MembersDTO> getAll() {
    List<Members> membersList = membersRepository.getAll();
    return membersList.stream().map(
        new Function<Members, MembersDTO>() {
          @Override
          public MembersDTO apply(Members members) {
            return entityToDTO(members);
          }
        }
    ).collect(Collectors.toList());
  }

  @Override
  public String login(String email, String password, JWTUtil jwtUtil) {
    log.info("login............");
    String token = "";
    MembersDTO membersDTO;
    Optional<Members> result = membersRepository.findByEmail(email, false);
    if (result.isPresent()) {
      membersDTO = entityToDTO(result.get());
      log.info("serviceimpl result: " + membersDTO);
      log.info("matches: " + passwordEncoder.matches(password, membersDTO.getPassword()));
      if (passwordEncoder.matches(password, membersDTO.getPassword())) {
        try {
          token = jwtUtil.generateToken(email);
          log.info("token:" + token);
        } catch (Exception e) {e.printStackTrace();}
      }
    }
    return token;
  }
}
