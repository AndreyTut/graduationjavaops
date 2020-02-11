package my.study.graduation.model;

import java.util.Set;

public class User extends AbstractNamedEntity {
    private String email;
    private String password;
    private Set<Role> roles;

    public User(int id, String name, String email, String password, Set<Role> roles) {
        super(id, name);
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, String email, String password, Set<Role> roles) {
        super(null, name);
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
