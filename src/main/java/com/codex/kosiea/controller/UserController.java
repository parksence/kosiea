package com.codex.kosiea.controller;

import com.codex.kosiea.common.Utils;
import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dto.UserDTO;
import com.codex.kosiea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    // 사용자 등록
    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser,
                     @RequestParam(name = "avatar", required = false) MultipartFile file,
                     @RequestParam(name = "avatar2", required = false) MultipartFile file2) throws Exception {

        // 이미지가 업로드 되었는지 확인
        if(file != null && !file.isEmpty()) {
            // 파일 저장 경로
            String uploadDir = ResourceUtils.getFile("classpath:static/profile/").getAbsolutePath() + "/";

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
            String uploadDir = ResourceUtils.getFile("classpath:static/bc/").getAbsolutePath() + "/";
            System.out.println("uploadDir = " + uploadDir.toString());
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
    public void update(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser,
                     @RequestParam(name = "avatar", required = false) MultipartFile file,
                     @RequestParam(name = "avatar2", required = false) MultipartFile file2) throws Exception {

        // 이미지가 업로드 되었는지 확인
        if(file != null && !file.isEmpty()) {
            // 파일 저장 경로
            String uploadDir = ResourceUtils.getFile("classpath:static/profile/").getAbsolutePath() + "/";

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
            String uploadDir = ResourceUtils.getFile("classpath:static/bc/").getAbsolutePath() + "/";
            // 파일 저장 경로 설정
            File folder = new File(uploadDir);

            if(!folder.exists()) {
                folder.mkdirs();
            }

            try {
                byte[] bytes = file2.getBytes();
                String fileName = timeStamp + "_" + file2.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);

                // 파일 저장
                file2.transferTo(new File(path.toString()));

                // DB에 파일 경로&명 저장
                String staticPart = "static";
                String result = "";
                int index = path.toString().indexOf(staticPart);
                if (index != -1) {
                    result = path.toString().substring(index + staticPart.length());
                } else {
                    System.out.println("String does not contain 'static'");
                }

                param.put("file_location2", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 로그인한 사용자 정보 전달
        String loginUserName = authUser.getNAME();
        String loginUserTel = authUser.getTEL();

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
            response.sendRedirect("/");
        }
    }

    @RequestMapping(value = "/form/{name}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView update(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser,
                               HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                               @PathVariable String name) throws Exception {

        if (authUser == null) {
            modelView.setViewName("redirect:/");
            return modelView;
        }

        System.out.println("name = " + name);
        UserDTO userDTO = new UserDTO();
        userDTO.setNAME(name);

        userService.selectUserInfo(userDTO);

        // 로그인 정보 전달
//        UserDTO userDTO = userService.selectUserInfo(authUser);
        modelView.addObject(userDTO);

        // 우편번호 주소 나누기
        String addr1 = userDTO.getADDR1();
        String addr2 = userDTO.getADDR2();
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

        String phoneNumber = userDTO.getTEL();
        String[] telArray = phoneNumber.split("-");

        // 전화번호 전달
        modelView.addObject("tel1", telArray[0]);
        modelView.addObject("tel2", telArray[1]);
        modelView.addObject("tel3", telArray[2]);

        modelView.setViewName("/form");
        return modelView;
    }

//    @RequestMapping(value = "/myData/{h_id}", method = { RequestMethod.GET, RequestMethod.POST })
//    public ModelAndView myDataUserView(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
//                                       ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser, @PathVariable String h_id) throws IOException {
//
//        // 최고관리자가 아니면 메인 페이지로 이동
//        if(!authUser.getRoleCd().toString().equals("999")) {
//            modelView.setViewName("redirect:/");
//            return modelView;
//        }
//
//        // 권한 코드
//        modelView.addObject("role_cd", authUser.getRoleCd().toString());
//        // 로그인한 사용자 아이디
//        modelView.addObject("user_id", authUser.getUsername().toString());
//        // 선택한 유저 아이디 전달
//        param.put("h_id", h_id);
//
//        // 로그인 정보 전달
//        UserDTO userDTO = new UserDTO();
//        userDTO.setH_ID(h_id);
//        UserDTO userInfo = userService.selectUserInfo(userDTO);
//
//        modelView.addObject("user_info", userInfo);
//
//        modelView.setViewName("/web/user/myData");
//        return modelView;
//    }

//    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
//    public void delete(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
//                            ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {
//
//        userService.delete(param);
//        response.sendRedirect("/");
//    }


}
