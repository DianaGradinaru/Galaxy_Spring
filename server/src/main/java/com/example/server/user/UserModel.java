package com.example.server.user;

import com.example.server.galaxies.GalaxyModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    private Long userid;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<GalaxyModel> listOfGalaxies = new ArrayList<>();
    private Boolean locked = false;
    private Boolean enabled = false;

    public UserModel(Long userid) {
        this.userid = userid;

    }

    public UserModel(String username, String password, String email, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    public void addGalaxy(GalaxyModel galaxyModel) {
        listOfGalaxies.add(galaxyModel);
        galaxyModel.setUser(this);
    }

    public void removeGalaxy(GalaxyModel galaxyModel) {
        listOfGalaxies.remove(galaxyModel);
        galaxyModel.setUser(null);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
