package shevtsov.denis.calculator_api.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    public UserInfo() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
