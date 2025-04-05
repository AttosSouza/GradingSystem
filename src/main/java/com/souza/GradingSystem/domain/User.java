package com.souza.GradingSystem.domain;

import com.souza.GradingSystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id", nullable = true)
    private User teacher;

    public User(String name, String email, String password, UserRole role, User teacher) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.teacher = teacher;
    }

    @PrePersist
    @PreUpdate
    private void validateRole() {
        if (this.role == UserRole.TEACHER && this.teacher != null) {
            throw new IllegalArgumentException("A teacher cannot have a professor reference.");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         if(this.role == UserRole.TEACHER) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
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
