package com.lbycpd2.todoexp.restful.user.clientusersecurity;

import com.lbycpd2.todoexp.restful.user.User;
import com.lbycpd2.todoexp.restful.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(s);

        user.orElseThrow(()->new UsernameNotFoundException("User not found"));

        return user.map(ClientDetails::new).get();
    }
}
