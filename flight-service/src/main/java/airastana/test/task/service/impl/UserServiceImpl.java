package airastana.test.task.service.impl;

import airastana.test.task.dto.UserRegistrationDTO;
import airastana.test.task.model.User;
import airastana.test.task.repository.UserRepository;
import airastana.test.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String DEFAULT_ROLE = "2";

    @Override
    @Transactional
    public void createUser(UserRegistrationDTO dto) {
        User newUser = new User();
        newUser.setUsername(dto.getUserName());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setRoleId(Long.valueOf(DEFAULT_ROLE));
        userRepository.save(newUser);
    }
}
