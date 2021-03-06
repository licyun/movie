package com.licyun.controller;

import com.licyun.model.*;
import com.licyun.service.UserPermissionService;
import com.licyun.service.UserRoleService;
import com.licyun.service.UserService;
import com.licyun.service.VideoService;
import com.licyun.util.UploadImg;
import com.licyun.util.Validate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/16.
 */
@Controller
public class AdminController {


    @Autowired
    private Validate validate;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserPermissionService userPermissionService;

    private static Logger logger = LoggerFactory.getLogger(VideoController.class);


    /**
     * 管理员登录
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = {"/admin/login"}, method = {RequestMethod.GET})
    public String login(Model model, HttpSession session) {
        User sessionUser = (User)session.getAttribute("admin");
        if(sessionUser != null){
            model.addAttribute("users", userService.findAllUsers());
            return "admin/index";
        }
        model.addAttribute("user", new User());
        return "admin/login";
    }
    @RequestMapping(value = {"/admin/login"}, method = {RequestMethod.POST})
    public String login(@Valid User user, BindingResult result,
                        Model model, HttpSession session){
        //管理员登录验证
        validate.adminLoginValidate(user, result);
        if(result.hasErrors()){
            return "admin/login";
        }
        session.setAttribute("admin", userService.findByEmail(user.getEmail()));
        model.addAttribute("users", userService.findAllUsers());
        return "admin/index";

    }

    /**
     * 退出登录
     * @param session 清除session
     * @return
     */
    @RequestMapping(value = {"/admin/loginout"}, method = {RequestMethod.GET})
    public String loginout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/login";
    }

    /**
     * 管理员首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"/admin/index", "/admin"}, method = {RequestMethod.GET})
    public String admin(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin/index";
    }

    /**
     * 查看所有视频
     * @param model
     * @return
     */
    @RequestMapping(value = {"/admin/videos"}, method = {RequestMethod.GET})
    public String video(Model model) {
        model.addAttribute("videos", videoService.findAllVideos());
        return "admin/videos";
    }

    /**
     * 编辑用户
     * @param uid  用户id
     * @param model
     * @return
     */
    @RequestMapping(value = {"/admin/edituser-{uid}"}, method = {RequestMethod.GET})
    public String editUser(@PathVariable int uid, Model model) {
        UserPR userPR = userService.findUserPRById(uid);
        model.addAttribute("userPR", userPR);
        return "admin/edituser";
    }
    @RequestMapping(value = {"/admin/edituser-{uid}"}, method = {RequestMethod.POST})
    public String editUser(@Valid UserPR userPR, BindingResult result,
                           @PathVariable int uid) {
        //从userPR中分离出user
        User user = new User(userPR.getName(), userPR.getPassword(), userPR.getEmail());
        validate.updateValidate(user, uid, result);
        if(result.hasErrors()){
            return "user/edituser";
        }
        //获取id对应的email
        String email = userService.findByUserId(uid).getEmail();
        //插入role
        userRoleService.updateRoles(email, userPR.getUserRole(), userPR.getAdmin());
        //插入permission
        userPermissionService.updatePermissions(email, userPR.getUserPermission());
        //插入user
        userService.updateUserById(user, uid);
        return "redirect:/admin/index";
    }

    /**
     * 删除用户
     * @param uid   用户id
     * @return
     */
    @RequestMapping(value = {"/admin/deleteuser-{uid}"}, method = {RequestMethod.GET})
    public String deleteUser(@PathVariable int uid) {
        //获取id对应的email
        String email = userService.findByUserId(uid).getEmail();
        //删除role
        userRoleService.deleteRolesByEmail(email);
        //删除permission
        userPermissionService.deletePermissionsByEmail(email);
        //删除user
        userService.deleteById(uid);
        return "redirect:/admin/index";
    }

}
