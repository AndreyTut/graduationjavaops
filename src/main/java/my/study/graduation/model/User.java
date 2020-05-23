package my.study.graduation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @Column
    @NotBlank
    @Size(min = 5, max = 150)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled;

    public User(Integer id, String name, String email, String password, Set<Role> roles, boolean enabled) {
        super(id, name);
        this.enabled = enabled;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {
        super();
    }

    public User(String name, String email, String password, Set<Role> roles, boolean enabled) {
        this(null, name, email, password, roles, enabled);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
