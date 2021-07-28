package com.select.shop.security;

import com.select.shop.model.User;
import com.select.shop.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
     */

    private static final String ROLE_PREFIX = "ROLE_";
    //USER와 ADMIN을 Enum 값으로 만들었지만 스프링시큐리티가 이해하려면 ROLE_USER 형태로 되어 있어야 한다.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole userRole = user.getRole();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + userRole.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>(); //여러 개의 권한 설정도 가능하다는 거네.
        authorities.add(authority);

        return authorities; //이렇게 하면 스프링시큐리티가 이제 회원의 권한을 인지하게 됐다!
    }
}
