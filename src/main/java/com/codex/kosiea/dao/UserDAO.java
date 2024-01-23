package com.codex.kosiea.dao;

import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {
    Map<String, Object> userChk(Map<String, Object> param);
    ArrayList<UserDTO> findByUserID(String username);
    int insertUser(Map<String, Object> param);
    int idFailYnCheck(Map<String, Object> loginInfo);
    String failPasswordCheck(Map<String, Object> loginInfo);
    UserDTO selectUserInfo(PrincipalDetails authUser);
    int updateUser(Map<String, Object> param);
    void insertLog(Map<String, Object> param);
    List<UserDTO> selectUserList(Map<String, Object> param);
    Map<String, Object> selectUserObject(String tel);
}
