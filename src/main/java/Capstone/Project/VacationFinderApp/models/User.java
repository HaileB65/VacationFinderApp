package Capstone.Project.VacationFinderApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "First name cannot be empty.")
    @Size(min = 2, max = 25, message = "First name must be between 2-25 characters long.")
    String firstName;

    @NotEmpty(message = "Last name cannot be empty.")
    @Size(min = 2, max = 25, message = "Last name must be between 2-25 characters long.")
    String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email
    String email;

    @NotEmpty
    @Size(max = 10)
    String phone;

    @NotEmpty
    @Size(min = 8, max = 25, message = "Username must be between 8-25 characters long.")
    String username;

    String password;

    @CreationTimestamp
    public Timestamp timestamp;

    @Transient
    @JsonIgnore
    public URI locationURI;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Checklist> checklists;

    @Column(name = "timestamp")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Trip> trips;

    @Builder.Default
    boolean locked = false;

    @Builder.Default
    boolean enabled = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
