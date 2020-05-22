package my.study.graduation.service;

import my.study.graduation.model.User;
import my.study.graduation.repository.CrudUserRepository;
import my.study.graduation.to.UserTo;
import my.study.graduation.util.ToConverters;
import my.study.graduation.util.exceptions.NotFoundInDataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
public class UserService extends BaseService<User> implements UserDetailsService {
    private CrudUserRepository repository;

    private PasswordEncoder passwordEncoder;

    @Autowired
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

    @Transactional
    public User save(UserTo userTo) {
        User user = (userTo.getId() == null)
                ? ToConverters.createNewFromTo(userTo)
                : ToConverters.updateFromTo(get(userTo.getId()), userTo);
        User user1 = super.save(prepareToSave(user, passwordEncoder));
        return user1;
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

    private static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

}
