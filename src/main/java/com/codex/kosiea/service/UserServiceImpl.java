package com.codex.kosiea.service;

import com.codex.kosiea.common.Utils;
import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dao.UserDAO;
import com.codex.kosiea.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    public int insertUser(Map<String, Object> param) {

        System.out.println("@@param.toString() = " + param.toString());

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

        // 로그 추가
        userDAO.insertLog(param);

        return userDAO.insertUser(param);
    }

    // 아이디 존재 여부 체크
    @Override
    public int idFailYnCheck(Map<String, Object> loginInfo) {
        int result = userDAO.idFailYnCheck(loginInfo);
        return result;
    }

    // 비밀번호 정합성 체크
    @Override
    public boolean failPasswordCheck(Map<String, Object> loginInfo) {
        String encodePassword = userDAO.failPasswordCheck(loginInfo);
        String password = (String) loginInfo.get("password");
        boolean result = passwordEncoder.matches(password, encodePassword);

        return result;
    }

    @Override
    public UserDTO selectUserInfo(PrincipalDetails authUser) {
        UserDTO userDTO = userDAO.selectUserInfo(authUser);
        return userDTO;
    }

    @Override
    public Map<String, Object> selectUserObject(String tel) {
        Map<String, Object> hm = userDAO.selectUserObject(tel);
        return hm;
    }

    @Override
    public int updateUser(Map<String, Object> param) {
        System.out.println("param.toString() = " + param.toString());

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

        // 로그 추가
        userDAO.insertLog(param);

        return userDAO.updateUser(param);
    }

    @Override
    public List<UserDTO> selectUserList(Map<String, Object> param) {
        return userDAO.selectUserList(param);
    }
}
