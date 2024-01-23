package com.codex.kosiea.controller;

import com.codex.kosiea.common.Utils;
import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dto.UserDTO;
import com.codex.kosiea.service.UserService;
import javassist.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${files.external.bc}")
    private String bPath;

    @Value("${files.external.profile}")
    private String profilePath;

    // 사용자 등록
    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser,
                     @RequestParam(name = "avatar", required = false) MultipartFile file,
                     @RequestParam(name = "avatar2", required = false) MultipartFile file2) throws Exception {

        // 이미지가 업로드 되었는지 확인
        if(file != null && !file.isEmpty()) {

            System.out.println("path = " + bPath);
            System.out.println("profilePath = " + profilePath);

            // 파일 저장 경로
            String uploadDir = bPath;
            // 아래 코드는 로컬에서 테스트할 때 사용
            // String uploadDir = ResourceUtils.getFile("classpath:static/profile/").getAbsolutePath() + "/";


            // 파일 저장 경로 설정
            File folder = new File(uploadDir);

            if(!folder.exists()) {
                folder.mkdirs();
            }

            try {
                byte[] bytes = file.getBytes();
                String fileName = timeStamp + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);

                // 파일 저장
                file.transferTo(new File(path.toString()));

                // DB에 파일 경로&명 저장
                String staticPart = "static";
                String result = "";
                int index = path.toString().indexOf(staticPart);
                if (index != -1) {
                    result = path.toString().substring(index + staticPart.length());
                    System.out.println(result);
                } else {
                    System.out.println("String does not contain 'static'");
                }

                param.put("file_location", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 이미지가 업로드 되었는지 확인
        if(file2 != null && !file2.isEmpty()) {
            // 파일 저장 경로
            String uploadDir = profilePath;

            // 아래 코드는 로컬 테스트할 때 사용
            // String uploadDir = ResourceUtils.getFile("classpath:static/bc/").getAbsolutePath() + "/";

            // 파일 저장 경로 설정
            File folder = new File(uploadDir);
            System.out.println("folder.toString() = " + folder.toString());

            if(!folder.exists()) {
                folder.mkdirs();
            }

            try {
                byte[] bytes = file2.getBytes();
                String fileName = timeStamp + "_" + file2.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);
                System.out.println("path.toString() = " + path.toString());

                // 파일 저장
                file2.transferTo(new File(path.toString()));

                // DB에 파일 경로&명 저장
                String staticPart = "static";
                String result = "";
                int index = path.toString().indexOf(staticPart);
                if (index != -1) {
                    result = path.toString().substring(index + staticPart.length());
                    System.out.println(result);
                } else {
                    System.out.println("String does not contain 'static'");
                }

                param.put("file_location2", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 로그 기능 추가
        Utils utils = new Utils();
        // 클라이언트 IP
        param.put("access_ip", utils.getClientIp(request));

        // 사용자 등록 후 성공시 경로 변경
        int result = userService.insertUser(param);
        System.out.println("result = " + result);
        if(result > 0) {
            response.sendRedirect("/");
        }
    }

    // 사용자 수정
    @RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser,
                     @RequestParam(name = "avatar", required = false) MultipartFile file,
                     @RequestParam(name = "avatar2", required = false) MultipartFile file2) throws Exception {

        // 이미지가 업로드 되었는지 확인
        if(file != null && !file.isEmpty()) {

            System.out.println("path = " + bPath);
            System.out.println("profilePath = " + profilePath);

            // 파일 저장 경로
            String uploadDir = bPath;
            // 아래 코드는 로컬에서 테스트할 때 사용
            // String uploadDir = ResourceUtils.getFile("classpath:static/profile/").getAbsolutePath() + "/";

            // 파일 저장 경로 설정
            File folder = new File(uploadDir);

            if(!folder.exists()) {
                folder.mkdirs();
            }

            try {
                byte[] bytes = file.getBytes();
                String fileName = timeStamp + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);

                // 파일 저장
                file.transferTo(new File(path.toString()));

                // DB에 파일 경로&명 저장
                String staticPart = "static";
                String result = "";
                int index = path.toString().indexOf(staticPart);
                if (index != -1) {
                    result = path.toString().substring(index + staticPart.length());
                    System.out.println(result);
                } else {
                    System.out.println("String does not contain 'static'");
                }

                param.put("file_location", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 이미지가 업로드 되었는지 확인
        if(file2 != null && !file2.isEmpty()) {
            // 파일 저장 경로
            String uploadDir = profilePath;

            // 아래 코드는 로컬 테스트할 때 사용
            // String uploadDir = ResourceUtils.getFile("classpath:static/bc/").getAbsolutePath() + "/";

            // 파일 저장 경로 설정
            File folder = new File(uploadDir);
            System.out.println("folder.toString() = " + folder.toString());

            if(!folder.exists()) {
                folder.mkdirs();
            }

            try {
                byte[] bytes = file2.getBytes();
                String fileName = timeStamp + "_" + file2.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);
                System.out.println("path.toString() = " + path.toString());

                // 파일 저장
                file2.transferTo(new File(path.toString()));

                // DB에 파일 경로&명 저장
                String staticPart = "static";
                String result = "";
                int index = path.toString().indexOf(staticPart);
                if (index != -1) {
                    result = path.toString().substring(index + staticPart.length());
                    System.out.println(result);
                } else {
                    System.out.println("String does not contain 'static'");
                }

                param.put("file_location2", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 로그인한 사용자 정보 전달
        String loginUserName = (String) param.get("name");

        System.out.println("param = " + param.toString());

        // 전화번호 포맷팅
        String loginUserTel = (String) param.get("origin_tel[0]");
        loginUserTel += "-";
        loginUserTel += (String) param.get("origin_tel[1]");
        loginUserTel += "-";
        loginUserTel += (String) param.get("origin_tel[2]");

        param.put("loginUserName", loginUserName);
        param.put("loginUserTel", loginUserTel);

        // 로그 기능 추가
        Utils utils = new Utils();
        // 클라이언트 IP
        param.put("access_ip", utils.getClientIp(request));

        // 사용자 정보 변경 후 성공시 경로 변경
        int result = userService.updateUser(param);
        System.out.println("result = " + result);
        if(result > 0) {
            response.sendRedirect("/form/"+loginUserTel);
        }
    }

    @RequestMapping(value = "/form/{tel}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView formTel(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser,
                               HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                               @PathVariable String tel) throws Exception {

        if (authUser == null) {
            modelView.setViewName("redirect:/");
            return modelView;
        }

        // 연락처로 회원정보 조회
        Map<String, Object> hm = userService.selectUserObject(tel);

        // null 값들도 hm에 포함하여 전달
        if(hm.get("FILE_LOCATION") == null) {
           hm.put("FILE_LOCATION", "");
        }

        if(hm.get("FILE_LOCATION2") == null) {
            hm.put("FILE_LOCATION2", "");
        }
        if(hm.get("LUNAR") == null) {
            hm.put("LUNAR", "");
        }

        // 로그인 정보 전달
        modelView.addObject("userDTO", hm);

        // 우편번호 주소 나누기
        String addr1 = (String) hm.get("ADDR1");
        String addr2 = (String) hm.get("ADDR2");
        // 우편번호 추출
        String postalCode = addr1.replaceAll("\\(([^)]+)\\).*", "$1");
        String postalCode2 = addr2.replaceAll("\\(([^)]+)\\).*", "$1");
        // 주소 추출
        String address = addr1.replaceAll(".*?\\)\\s*(.*)", "$1").trim();
        String address2 = addr2.replaceAll(".*?\\)\\s*(.*)", "$1").trim();

        // 우편번호, 주소 전달
        modelView.addObject("postalCode", postalCode);
        modelView.addObject("address", address);
        modelView.addObject("postalCode2", postalCode2);
        modelView.addObject("address2", address2);

        String phoneNumber = (String) hm.get("TEL");
        String[] telArray = phoneNumber.split("-");

        // 전화번호 전달
        modelView.addObject("tel1", telArray[0]);
        modelView.addObject("tel2", telArray[1]);
        modelView.addObject("tel3", telArray[2]);

        modelView.setViewName("/form");
        return modelView;
    }

//    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
//    public void delete(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
//                            ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {
//
//        userService.delete(param);
//        response.sendRedirect("/");
//    }


}
