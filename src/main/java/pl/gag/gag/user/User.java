package pl.gag.gag.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true,length = 45)
    private String email;


    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false,unique = false,length = 20)
    private String firstName;

    @Column(nullable = false,unique = false,length = 20)
    private String lastName;


    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(nullable = true, length = 64)
    private String photos;

}
