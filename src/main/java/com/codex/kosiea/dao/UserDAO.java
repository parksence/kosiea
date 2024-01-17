package com.codex.kosiea.dao;

import com.codex.kosiea.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserDAO {
    ArrayList<UserDTO> findByUserID(String username);
}
