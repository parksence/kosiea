package com.codex.kosiea.controller;

import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dto.UserDTO;
import com.codex.kosiea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 사용자 등록
    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        // 사용자 등록
        userService.insertUser(param);
        response.sendRedirect("/");
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
