package my.study.graduation.service;

import my.study.graduation.util.exceptions.*;
import my.study.graduation.model.User;
import my.study.graduation.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
public class UserService extends BaseService<User> implements UserDetailsService {
    private CrudUserRepository repository;

    private PasswordEncoder passwordEncoder;

    public UserService(CrudUserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository, User.class);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User get(String email) {
        return repository.getByEmail(email).orElseThrow(() -> new NotFoundInDataBaseException("Not found User with email: " + email));
    }

    public Integer getId(String email) {
        return repository.getIdByEmail(email).orElseThrow(() -> new NotFoundInDataBaseException("Not found User with email: " + email));
    }

    @Override
    public User save(User user) {
        return super.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase()).orElseThrow(() -> new NotFoundInDataBaseException("User " + email + " is not found"));
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoles();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
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
                return user.isEnabled();
            }
        };
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

}
