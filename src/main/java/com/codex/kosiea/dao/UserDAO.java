package com.codex.kosiea.dao;

import com.codex.kosiea.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface UserDAO {
    Map<String, Object> userChk(Map<String, Object> param);
    ArrayList<UserDTO> findByUserID(String username);
    int insertUser(Map<String, Object> param);
    int idFailYnCheck(Map<String, Object> loginInfo);
    String failPasswordCheck(Map<String, Object> loginInfo);
}
