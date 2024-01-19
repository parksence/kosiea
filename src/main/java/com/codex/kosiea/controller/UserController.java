package com.codex.kosiea.controller;

import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dto.UserDTO;
import com.codex.kosiea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        // 사용자 정보 변경 후 성공시 경로 변경
        int result = userService.updateUser(param);
        System.out.println("result = " + result);
        if(result > 0) {
            response.sendRedirect("/");
        }
    }

//    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
//    public void delete(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
//                            ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {
//
//        userService.delete(param);
//        response.sendRedirect("/");
//    }


}
