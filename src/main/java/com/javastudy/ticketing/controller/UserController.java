package com.javastudy.ticketing.controller;

import com.javastudy.ticketing.domain.User;
import com.javastudy.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
public class UserController {

    private final UserService userservice;
    @Autowired
    public UserController(UserService userservice) { this.userservice=userservice; }
    //회원가입폼띄우기
    @GetMapping("signupform")
    public String signUpForm(){
        return "user/signupForm";
    }
    //회원가입하기
    @PostMapping("signup")
    public String signUp(UserForm form, Model model){
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        //service로 전달하기위해 domain(entity)사용
        userservice.signUp(user);
//        model.addAttribute("user",user);
        return "redirect:/";
    }

    //유저정보하나가져오기
    @GetMapping("user")
    public String getUserInfo(@RequestParam(value = "user_id") int user_id, Model model){
        Optional<User> user = userservice.getUserInfo(user_id);
        user.ifPresent(singleUser -> model.addAttribute("user",singleUser));
        if (user.isEmpty()){ return new String("해당 아이디의 회원이 존재하지 않습니다."); }//이따가 테스트해보기
        return "user/userInfo";
    }

    //유저정보수정하기
    @PatchMapping("user")
    @ResponseBody
    public Optional<User> updateUserInfo(@RequestParam(value = "user_id") int user_id , @RequestBody User user){
        return userservice.updateUserInfo(user_id , user);
    }

    //유저정보삭제하기
    @DeleteMapping("user")
    public String deleteUserInfo(@RequestParam(value = "user_id") int user_id){
       userservice.deleteUserInfo(user_id);
       return "redirect:/";
    }

    @GetMapping("/loginform")
    public String loginForm() { return "user/loginForm"; }

    @PostMapping("/login")
    public String login(String inputEmail, String inputPassword){
        User user = this.userservice.findUser(inputEmail, inputPassword);
        if(user != null){
            return "user/loginOk";
        }
        return "user/loginFail";
    }

}
