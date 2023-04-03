package Capstone.Project.VacationFinderApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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

    @NotEmpty(message = "User's first name cannot be empty.")
    @Size(min=2, max=30, message = "First name should be between 2-30 characters long.")
    String firstName;

    @NotEmpty(message = "Name is mandatory")
    String lastName;

    @NotEmpty
    String email;

    @NotEmpty
    String phone;

    @NotEmpty
    String username;

    @NotEmpty
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
