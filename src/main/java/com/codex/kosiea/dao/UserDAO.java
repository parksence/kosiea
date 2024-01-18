package com.codex.kosiea.dao;

import com.codex.kosiea.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface UserDAO {
    Map<String, Object> userChk(Map<String, Object> param);
    ArrayList<UserDTO> findByUserID(String username);
    void insertUser(Map<String, Object> param);

}
