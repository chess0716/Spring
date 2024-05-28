package com.demo.gram.service;

import com.demo.gram.dto.ChatRoomDTO;
import com.demo.gram.dto.MembersDTO;
import com.demo.gram.dto.PostDTO;
import com.demo.gram.entity.ChatRoom;
import com.demo.gram.entity.Members;
import com.demo.gram.entity.Post;
import com.demo.gram.repository.ChatRoomRepository;
import com.demo.gram.repository.MembersRepository;
import com.demo.gram.repository.PostRepository;
import com.demo.gram.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService, UserDetailsService {

  private final PostRepository postRepository;
  private final MembersRepository membersRepository;
  private final PasswordEncoder passwordEncoder;
  private final ChatRoomRepository chatRoomRepository;
  private final JWTUtil jwtUtil;
  private final PostRepository myPostRepository;
  private final ModelMapper modelMapper;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Members user = membersRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .roles("USER")
        .build();
  }

  @Override
  public Long registMembersDTO(MembersDTO membersDTO) {
    membersDTO.setPassword(passwordEncoder.encode(membersDTO.getPassword()));
    return membersRepository.save(dtoToEntity(membersDTO)).getMno();
  }

  @Override
  public void updateMembersDTO(MembersDTO membersDTO) {
    if (membersDTO.getMno() == null) {
      throw new IllegalArgumentException("Member ID (mno) must not be null");
    }
    Members existingMember = membersRepository.findById(membersDTO.getMno())
        .orElseThrow(() -> new IllegalArgumentException("Member not found"));

    existingMember.setName(membersDTO.getName());
    if (membersDTO.getPassword() != null && !membersDTO.getPassword().isEmpty()) {
      existingMember.setPassword(passwordEncoder.encode(membersDTO.getPassword()));
    }
    existingMember.setMobile(membersDTO.getMobile());
    membersRepository.save(existingMember);
  }

  @Override
  public void removeMembers(Long mno) {
    membersRepository.deleteById(mno);
  }

  @Override
  public MembersDTO get(Long mno) {
    Optional<Members> result = membersRepository.findById(mno);
    return result.map(this::entityToDTO).orElse(null);
  }

  @Override
  public List<MembersDTO> getAll() {
    List<Members> membersList = membersRepository.findAll();
    return membersList.stream().map(this::entityToDTO).collect(Collectors.toList());
  }

  @Override
  public String login(String email, String password, JWTUtil jwtUtil) {
    Members user = membersRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    if (passwordEncoder.matches(password, user.getPassword())) {
      try {
        return jwtUtil.generateToken(email);
      } catch (Exception e) {
        log.error("Error generating token: ", e);
        return "";
      }
    } else {
      return "";
    }
  }

  @Override
  public Members getCurrentLoggedInUser(String token) throws Exception {
    String email = jwtUtil.validateAndExtract(token);
    return membersRepository.findByEmail(email)
        .orElseThrow(() -> new Exception("Logged in user not found"));
  }

  @Override
  public List<PostDTO> getUserPosts(Long userId) {
    Members member = membersRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    List<Post> userPosts = postRepository.findByUser(member);
    return userPosts.stream()
        .map(post -> modelMapper.map(post, PostDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<ChatRoomDTO> getUserChatRooms(Long postId) {
    return chatRoomRepository.findByPostId(postId)
        .map(chatRoom -> Collections.singletonList(modelMapper.map(chatRoom, ChatRoomDTO.class)))
        .orElse(Collections.emptyList());
  }


  @Override
  public Long getUserIdFromToken(String token) throws Exception {
    String email = jwtUtil.validateAndExtract(token);
    Members user = membersRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return user.getMno();
  }

  @Override
  public MembersDTO getUserByEmail(String email) {
    Members member = membersRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    return entityToDTO(member);
  }

  @Override
  public Members findByEmail(String email) {
    return membersRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
  }

  @Override
  @Transactional(readOnly = true)
  public List<MembersDTO> getChatRoomMembers(Long chatRoomId) {
    log.info("Fetching members for chat room id: {}", chatRoomId);
    ChatRoom chatRoom = chatRoomRepository.findByIdWithMembers(chatRoomId)
        .orElseThrow(() -> new RuntimeException("Chat room not found"));
    List<MembersDTO> membersDTOs = chatRoom.getMembers().stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
    log.info("Members for chat room id {}: {}", chatRoomId, membersDTOs);
    return membersDTOs;
  }

  @Override
  @Transactional
  public void joinChatRoom(String email, Long chatRoomId) {
    Members member = membersRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    ChatRoom chatRoom = chatRoomRepository.findByIdWithMembers(chatRoomId)
        .orElseThrow(() -> new RuntimeException("Chat room not found"));

    // 중복 체크
    if (!member.getChatRooms().contains(chatRoom)) {
      member.joinChatRoom(chatRoom);
      membersRepository.save(member);
    }
  }

  public Members findByToken(String token) {
    try {
      String email = jwtUtil.validateAndExtract(token);
      return findByEmail(email);
    } catch (Exception e) {
      throw new RuntimeException("Invalid token", e);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<String> getChatRoomMemberNames(Long chatRoomId) {
    log.info("Fetching member names for chat room id: {}", chatRoomId);
    List<String> memberNames = membersRepository.findByChatRoomId(chatRoomId)
        .stream()
        .map(Members::getName)
        .collect(Collectors.toList());
    log.info("Member names for chat room id {}: {}", chatRoomId, memberNames);
    return memberNames;
  }

  public MembersDTO entityToDTO(Members member) {
    // 엔티티를 DTO로 변환하는 로직
    return modelMapper.map(member, MembersDTO.class);
  }

  public Members dtoToEntity(MembersDTO membersDTO) {
    // DTO를 엔티티로 변환하는 로직
    return modelMapper.map(membersDTO, Members.class);
  }

  @Override
  public MembersDTO getUserById(String id) {
    Members member = membersRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    return entityToDTO(member);
  }
}
