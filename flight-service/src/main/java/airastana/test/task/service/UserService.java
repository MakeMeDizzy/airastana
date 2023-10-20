package airastana.test.task.service;

import airastana.test.task.dto.UserRegistrationDTO;
import airastana.test.task.model.User;

public interface UserService {
    void createUser(UserRegistrationDTO dto);

}
