package com.sbgroup.haras.models;

import com.sbgroup.haras.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
@Entity
@Table(name = "Users")
public class UserModel implements Serializable, UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "users_id")
  private UUID id;
  @NotBlank
  @Column(name = "users_email",unique=true)
  private String email;
  @ManyToMany
  @JoinColumn(name = "roles_id")
  private UserRole role;
  @Column(name = "users_login")
  private String login;
  @NotBlank
  @Column(name = "users_first_name")
  private String firstName;
  @NotBlank
  @Column(name = "users_last_name")
  private String lastName;
  @ManyToMany
  @JoinColumn(name = "users_farm_id")
  private String farmId;
  @NotBlank
  @Column(name = "users_created_at")
  private Timestamp createdAt;
  @Column(name = "users_password")
  private String password;

  public UserModel(String firstName, String lastName, String email, String password, UserRole role, String farmId, Timestamp createdAt) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = email;
    this.email = email;
    this.password = password;
    this.role = role;
    this.farmId = farmId;
    this.createdAt = createdAt;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == UserRole.ADMIN) {
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    } else {
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
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
