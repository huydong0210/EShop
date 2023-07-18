package com.group2.eshopbe.security;

import com.group2.eshopbe.entity.EUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;
    public EUserDetails(String username, String password, Collection<? extends GrantedAuthority> roles){
        this.username=username;
        this.password=password;
        this.roles=roles;
    }
    public static EUserDetails build(EUser user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new EUserDetails(user.getUsername(),user.getPassword(),authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
