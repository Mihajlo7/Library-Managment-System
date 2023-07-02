package com.np.library.domain;


import com.np.library.domain.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
    @ManyToOne
    @JoinColumn(name = "library_id",nullable = false)
    private Library library;

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

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", role=" + role +
                ", library=" + library +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && id.equals(user.id) && role == user.role && Objects.equals(library, user.library);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, id, role, library);
    }
}
