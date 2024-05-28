package com.demo.api.service;

import com.demo.api.dto.MembersDTO;
import com.demo.api.entity.Members;
import com.demo.api.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//    List<Members> list = membersRepository.findAll();
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
}