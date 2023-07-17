package com.group2.eshopbe.security;

import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EUser> user = userRepository.findByUsername(username);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("Username not found");
        } else {
            return EUserDetails.build(user.get());
        }
    }
}
