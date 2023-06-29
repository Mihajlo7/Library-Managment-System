package com.np.library.domain;


import com.np.library.domain.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class User {
    private static final long serialVersionUID = 8055251038771851999L;
    private String username;
    private String password;
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public void setUsername(String username) {
        if(username==null){
            throw new IllegalArgumentException("The username must not be a null value");
        }
        if(username.equals("")){
            throw new IllegalArgumentException("The username must not be a empty value");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if(password==null){
            throw new IllegalArgumentException("The password must not be a null value");
        }
        if(password.equals("")){
            throw new IllegalArgumentException("The password must not be a empty value");
        }
        this.password = password;
    }

    public void setRole(UserRole role) {
        if(role==null){
            throw new IllegalArgumentException("The role must not be a null value");
        }
        this.role = role;
    }
}
