package com.example.ivcmf.config;

import com.example.ivcmf.dao.UserEntity;
import com.example.ivcmf.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByLogin(username);
        return user.map(MyUserDetail::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
