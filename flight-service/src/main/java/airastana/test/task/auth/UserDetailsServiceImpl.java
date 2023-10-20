package airastana.test.task.auth;

import airastana.test.task.model.User;
import airastana.test.task.repository.UserRepository;
import airastana.test.task.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Optional<User> user = userRepository.findUserByUsername(username);
            if (user.isPresent()) {
                return new UserPrincipal(user.get(), roleService);
            } else {
                throw new UsernameNotFoundException("User not found!");
            }
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Error while loading user by username", ex);
        }
    }
}
