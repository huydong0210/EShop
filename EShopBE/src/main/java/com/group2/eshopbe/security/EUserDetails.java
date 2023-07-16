package com.group2.eshopbe.security;

import com.group2.eshopbe.entity.EUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        GrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(role);
        return new EUserDetails(user.getUsername(),user.getPassword(),roles);
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
