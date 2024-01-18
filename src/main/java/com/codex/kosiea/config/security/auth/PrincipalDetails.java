package com.codex.kosiea.config.security.auth;

import com.codex.kosiea.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 시큐리티가 /doLogin 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가 되면 시큐리티 session을 만들어줍니다.(Security ContextHolder)

public class PrincipalDetails implements UserDetails {

    private ArrayList<UserDTO> userDTOs;
    private UserDTO userDTO;

    public PrincipalDetails(ArrayList<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
        this.userDTO = userDTOs.get(0);
    }

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserDTO user : userDTOs) {
            authorities.add(new SimpleGrantedAuthority(user.getROLE_NM()));
        }

        return authorities;
    }

    public int getNo() {
        return userDTO.getNO();
    }

    public String getName() {
        return userDTO.getNAME();
    }

    @Override
    public String getPassword() {
        return userDTO.getPASSWORD();
    }

    public String getCreateDate() {
        return userDTO.getCREATE_DATE();
    }

    public String getUpdateDate() {
        return userDTO.getUPDATE_DATE();
    }

    @Override
    public String getUsername() {
        return userDTO.getNAME();
    }

    public String getRoleCd() {
        return userDTO.getROLE_CD();
    }
    public String getRoleNm() {
        return userDTO.getROLE_NM();
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

    @Override
    public String toString() {
        return "UserDetailsDTO [userDTO=" + userDTO.toString() + "]";
    }
}
