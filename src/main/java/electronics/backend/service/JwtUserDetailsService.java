package electronics.backend.service;

import electronics.backend.entity.User;
import electronics.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return user;
    }

    public User save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bcryptEncoder.encode(password));
        return userRepository.save(user);
    }

    public boolean isUserExist(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

}
