package Capstone.Project.VacationFinder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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

    String firstName;
    String lastName;
    String email;
    String phone;
    String username;
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Transient
    @JsonIgnore
    public URI locationURI;

    @Column(name = "timestamp")
    @CreationTimestamp
    public Timestamp timestamp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Trip> trips;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Destination> savedDestinations;


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
