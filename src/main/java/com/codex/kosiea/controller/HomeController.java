package com.codex.kosiea.controller;

import com.codex.kosiea.common.Pagination;
import com.codex.kosiea.config.security.auth.PrincipalDetails;
import com.codex.kosiea.dto.UserDTO;
import com.codex.kosiea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView home(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser
                           , @RequestParam Map<String, Object> param, @RequestParam(defaultValue = "1") int page) throws Exception {

        if (authUser == null) {
            modelView.setViewName("redirect:/");
            return modelView;
        }
        UserDTO userDTO = userService.selectUserInfo(authUser);
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

    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView login(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/login");
        return modelView;
    }

    @RequestMapping(value = "/create", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView create(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {
        modelView.setViewName("/create");
        return modelView;
    }

    @RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ModelAndView search(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser,
                               HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

        if (authUser == null) {
            modelView.setViewName("redirect:/");
            return modelView;
        }

        System.out.println("param = " + param.toString());

        // 회원 조회
        List<UserDTO> userDTOList = userService.selectUserList(param);

        modelView.addObject("userList", userDTOList);

        modelView.setViewName("/search");
        return modelView;
    }

    @RequestMapping(value = "/form", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView update(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        if (authUser == null) {
            modelView.setViewName("redirect:/");
            return modelView;
        }

        UserDTO userDTO = userService.selectUserInfo(authUser);
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

}
