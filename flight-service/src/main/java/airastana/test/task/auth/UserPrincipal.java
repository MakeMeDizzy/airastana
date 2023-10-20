package airastana.test.task.auth;

import airastana.test.task.model.User;
import airastana.test.task.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final User user;
    private final RoleService roleService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = roleService.findRoleById(user.getRoleId()).getCode();
        log.debug("roleName: {}", roleName);
        return Collections.singletonList(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
