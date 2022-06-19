package com.example.learnkeycloak.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping()
public class TestController {


  @GetMapping("/posts")
  public String getAll() {
    return "GET success to /posts";
  }
  @GetMapping("/posts/admin")
  public String getAdmin() {
    return "GET success to /posts/admin";
  }
  @DeleteMapping("/posts/{id}")
  public String delete(@PathVariable("id") Long id) {
    return "POST success to /posts/{id}";
  }
  @GetMapping("/logout")
  public String logout(HttpServletRequest request) throws Exception {
    request.logout();
    return "Logout success";
  }
}
