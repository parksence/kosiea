package com.codex.kosiea.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public interface UserService {

    Map<String, Object> userLogin(Map<String, Object> param); // 사용자 로그인
    int insertUser(Map<String, Object> param); // 사용자 등록
    int idFailYnCheck(Map<String, Object> loginInfo);
    boolean failPasswordCheck(Map<String, Object> loginInfo);
}
