package com.codex.kosiea.service;

import com.codex.kosiea.dao.UserDAO;
import com.codex.kosiea.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> userLogin(Map<String, Object> param) {

        if(!param.isEmpty()) {
            // 비밀번호 암호화
            System.out.println("111 param.toString() = " + param.toString());
            String pw = (String) param.get("password");
            pw = passwordEncoder.encode(pw);

            System.out.println("pw = " + pw);

            param.put("password", pw);
        }
        Map<String, Object> userInfo = userDAO.userChk(param);

        return userInfo;
    }

    @Override
    public void insertUser(Map<String, Object> param) {
        // 비밀번호 암호화
        String pw = (String) param.get("password");
        param.put("password", passwordEncoder.encode(pw));

        // 전화번호 포맷팅
        String tel_sum = (String) param.get("tel[0]");
        tel_sum += "-";
        tel_sum += (String) param.get("tel[1]");
        tel_sum += "-";
        tel_sum += (String) param.get("tel[2]");

        // 결과 출력
        param.put("tel", tel_sum);

        // 직장 주소
        String work_location = "(";
        work_location += (String) param.get("addr1-1");
        work_location += ")";
        work_location += (String) param.get("addr1-2");

        param.put("addr1", work_location);

        // 자택 주소
        String home_location = "(";
        home_location += (String) param.get("addr2-1");
        home_location += ")";
        home_location += (String) param.get("addr2-2");

        param.put("addr2", home_location);

        // 음력 생일
        String lunar = (String) param.get("lunar_birthday");
        if(lunar.equals("")){
            param.put("lunar_yn", 0);
        }

        System.out.println("param = " + param.toString());

        userDAO.insertUser(param);
    }
}
