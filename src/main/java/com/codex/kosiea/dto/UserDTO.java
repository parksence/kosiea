package com.codex.kosiea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO implements Serializable {
    private int NO;
    private String NAME;
    private String PASSWORD;
    private String GENDER;
    private String KOB;
    private String COMPANY;
    private String POSITION;
    private String BIRTHDAY;
    private String ADDR1;
    private String ADDR2;
    private String LUNAR;
    private int LUNAR_YN;
    private String TEL;
//    private String TYPE1;
//    private String TYPE2;
//    private String TYPE3;
//    private String PAYDATE;
//    private int DUES;
//    private String ETC1;
//    private String ETC2;
//    private String IDNUMBER;
//    private String STATUS;
//    private int MBAMT;
    private String FILE_LOCATION;
    private String FILE_LOCATION2;
    private String ROLE_CD;
    private String ROLE_NM;
    private String CREATE_DATE;
    private String UPDATE_DATE;

    private UserDTO userDTO;
    private List<UserDTO> userDTOList;
}
