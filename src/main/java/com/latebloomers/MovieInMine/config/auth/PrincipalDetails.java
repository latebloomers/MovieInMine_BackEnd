package com.latebloomers.MovieInMine.config.auth;

// 시큐리티가 /login 주소 요청이 오면 대신 로그인을 진행시킨다.
// 로그인이 완료 되면 시큐리티 session을 만들어주는데, 이때 키 값이 Security ContextHolder이다
// 이때 session에 들어갈 수 있는 오브젝트는 Authentication Type의 객체여야 한다.
// Authentication 안에는 User 정보가 있어야한다.
// 또, User 오브젝트 타입은 UserDetails 타입 객체여야한다는 규칙이 있다.

import com.latebloomers.MovieInMine.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 즉, Security Session => Authentication => UserDetails
public class PrincipalDetails implements UserDetails {

    private User user; //콤포지션??

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 리턴하는 곳!!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();

        return null;
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
}
