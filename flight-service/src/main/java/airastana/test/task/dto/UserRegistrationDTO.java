package airastana.test.task.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 20, message = "Password must have at least 6 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must have at least one digit, one lowercase " +
                    "letter, one uppercase letter, one special character, " +
                    "and must be at least 8 characters long without spaces")
    private String password;
}
