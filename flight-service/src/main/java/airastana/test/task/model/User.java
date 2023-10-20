package airastana.test.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user")
public class User {
    @Id
    private Long id;
    private String username;

    private String password;

    @Column("roleId")
    private Long roleId;
}
