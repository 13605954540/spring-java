package com.lp.first.framework.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * @author LP
 * @date 2018/11/2
 */
public class SysUser implements UserDetails {

    private String userId;

    private String username;

    private String password;

    private String realName;

    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled = true;

    private Set<GrantedAuthority> authorities;

    public String getUserId() {
        return userId;
    }

    public SysUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public SysUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public SysUser setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public SysUser setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public void eraseCredentials() {

    }
}
