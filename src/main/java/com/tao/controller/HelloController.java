package com.tao.controller;

import com.tao.model.User;
import com.tao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created with suntao on 2018/3/8
 */
@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN2')")
    @RequestMapping("/hello")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        User user = userService.findById(1);
        map.addAttribute("phone", user.getPhone());
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "hello";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"/","/home"})
    public String index() {
        return "home/home";
    }


    @RequestMapping(value={"/welcome"})
    public String welcome() {
        return "home/welcome";
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        System.out.println("to login----");

        //model.addAttribute("phone", user.getPhone());
        return "login/login";
    }

    @RequestMapping("/thymeleaf")
    public String test(Map<String,Object> map, ServletResponse response) throws UnsupportedEncodingException, IOException {
        map.put("name", "test");
        System.out.println("thymeleaf----");
        return "thymeleaf/hello2";
    }

    /**
     * 功能描述：角色管理
     * CHENY037 2017年11月29日
     * @return
     */
    @RequestMapping("/roleManage")
    @PreAuthorize("hasRole('ROLE_MANAGE')")
    public String roleManage(){
        return "home/role";
    }

}
