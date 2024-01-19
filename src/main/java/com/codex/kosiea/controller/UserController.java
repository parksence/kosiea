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

    // 사용자 등록
    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser,
                     @RequestParam(name = "avatar", required = false) MultipartFile file,
                     @RequestParam(name = "avatar2", required = false) MultipartFile file2) throws Exception {

        // 현재 날짜와 시간을 이용하여 파일명 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

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
                Files.write(path, bytes);

                // 파일 저장
                file.transferTo(new File(path.toString()));
                // DB에 파일 경로&명 저장
                param.put("file_location", path.toString());
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
                Files.write(path, bytes);

                // 파일 저장
                file2.transferTo(new File(path.toString()));
                // DB에 파일 경로&명 저장
                param.put("file_location2", path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 사용자 등록 후 성공시 경로 변경
        int result = userService.insertUser(param);
        if(result > 0) {
            response.sendRedirect("/");
        }
    }

//    @RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public int update(@RequestParam Map<String, Object> param) {
//        return userService.update(param);
//    }

//    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
//    public void delete(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
//                            ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {
//
//        userService.delete(param);
//        response.sendRedirect("/");
//    }


}
