package com.identa.identaproject.service;

import com.identa.identaproject.dto.UserDTO;
import com.identa.identaproject.entities.User;
import com.identa.identaproject.mapper.UserMapper;
import com.identa.identaproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper = UserMapper.MAPPER;
    private final UserRepository repository;
    private final SimpMessagingTemplate template;

    @Override
    public List<UserDTO> getAll() {
        return mapper.fromUserList(repository.findAll());
    }

    @Override
    @Transactional
    public void addUser(UserDTO userDTO) {
        var user = mapper.toUser(userDTO);
        var saveUser = repository.save(user);
        template.convertAndSend("/topic/users", UserMapper.MAPPER.fromUser(saveUser));
    }

    @Override
    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Override
    @Transactional
    public User findByName(String email) {
        return repository.findFirstByEmail(email);
    }
}