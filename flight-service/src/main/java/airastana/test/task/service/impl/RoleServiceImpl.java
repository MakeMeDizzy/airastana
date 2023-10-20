package airastana.test.task.service.impl;

import airastana.test.task.model.Role;
import airastana.test.task.repository.RoleRepository;
import airastana.test.task.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found!"));
    }
}
