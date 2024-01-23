package com.codex.kosiea.service;

import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    Map<String, Object> userLogin(Map<String, Object> param); // 사용자 로그인
    int insertUser(Map<String, Object> param); // 사용자 등록
    int idFailYnCheck(Map<String, Object> loginInfo);
    boolean failPasswordCheck(Map<String, Object> loginInfo);
    UserDTO selectUserInfo(PrincipalDetails authUser);
    int updateUser(Map<String, Object> param);
    List<UserDTO> selectUserList(Map<String, Object> param);
    Map<String, Object> selectUserObject(String tel);
}
