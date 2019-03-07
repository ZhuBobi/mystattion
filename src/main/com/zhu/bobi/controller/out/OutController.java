package com.zhu.bobi.controller.out;

import com.zhu.bobi.commonutil.CommonUtil;
import com.zhu.bobi.commonutil.MyException;
import com.zhu.bobi.commonutil.MyPasswordEncoder;
import com.zhu.bobi.commonutil.StringUtils;
import com.zhu.bobi.controller.BaseController;
import com.zhu.bobi.entity.UserInfo;
import com.zhu.bobi.result.ResultEnum;
import com.zhu.bobi.result.Result;
import com.zhu.bobi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Zhu波比 on 2018/3/31.
 */
@Controller
@RequestMapping
public class OutController extends BaseController {

  @Autowired
  private UserService userService;

  @Autowired
  private MyPasswordEncoder encoder;

  @RequestMapping("index")
  public String index() {
    logger.debug("进入index");
    return "out/index";
  }

  @RequestMapping("/index/character")
  public String character() {
    logger.debug("进入character");
    return "out/character";
  }

  @RequestMapping("/index/pastTimes")
  public String pastTimes() {
    logger.debug("进入pastTimes");
    return "out/pastTimes";
  }

  @RequestMapping("/index/myblogs")
  public String myblogs() {
    logger.debug("进入myblogs");
    return "out/myblogs";
  }

  @RequestMapping("person")
  public String person() {
    logger.debug("进入person");
    return "out/person";
  }

  @GetMapping("login")
  public String login() {
    logger.info("进入login");
    return "out/login";
  }

//  footer link controller
  @RequestMapping("Me")
  public String Me() {
    logger.debug("进入Me");
    return "mid/me";
  }

  @RequestMapping("Them")
  public String Them() {
    logger.debug("进入them");
    return "mid/them";
  }

  @RequestMapping("You")
  public String You() {
    logger.debug("进入You");
    return "mid/you";
  }

  @RequestMapping("Future")
  public String Future() {
    logger.debug("进入Future");
    return "mid/future";
  }


  @PostMapping("dologin")
  @ResponseBody
  public Result dologin(HttpServletRequest request, String username, String password) {
    try {
      logger.info("用户开始登陆，用户名：{}", username);
      UserInfo user = (UserInfo) userService.loadUserByUsername(username);
      String md5Password = encoder.MD5Maker(password, username);
      if (!user.getPassword().equals(md5Password)) {
        logger.info("密码错误，登陆失败，用户名：{}", username);
        throw new MyException(ResultEnum.ERROEPASS);
      }
      logger.info("用户名：{}，成功登陆", username);
      makeUser(request, user);
    } catch (Exception myException) {
      logger.info("登陆失败，用户名：{}", username);
      return resultMsg().failureData(myException.getMessage());
    }
    return resultMsg().successResult(ResultEnum.SUCCESS);
  }


//  registername: $("#registername").val(), registerword: $("#registerword").val(),
//  registerword2: $("#registerword2").val(),invitecode:$("#invitecode")

  @PostMapping("register")
  @ResponseBody
  public Result register(Model model,HttpServletRequest request, String registername,
                         String registerword, String registerword2, String invitecode) {
    String registerMessage = null;
    try {
      logger.info("用户注册，用户名：{}", registername);
      registerMessage = registerCheck(registername, registerword, registerword2, invitecode);
      if (null != registerMessage) {
        throw new Exception(registerMessage);
      }
      String passMd5 = encoder.MD5Maker(registerword, registername);
      userService.addUser(CommonUtil.getUUID(), registername, passMd5,
          "VISITER", new Date(), "Admin invite.", true);
      model.addAttribute("msg","");
      return resultMsg().successResult(ResultEnum.SUCCESS);
    } catch (Exception myException) {
      logger.info("注册失败，用户名：{}", registername);
      model.addAttribute("msg",myException.getMessage());
      return resultMsg().failureMessage(myException.getMessage());
    }
  }

  @GetMapping("initDataBase")
  @ResponseBody
  public Result initDataBase() {
    try {
      //init--table  sys_user
      String username = "zhubo";
      String password = "bob4267zhu";
      String role = "AMDIN";
      userService.addUser(CommonUtil.getUUID(), username,
          encoder.MD5Maker(password, username), role, new Date(), "Something like this!", true);
      username = "zhubobi";
      role = "USER";
      userService.addUser(CommonUtil.getUUID(), username,
          encoder.MD5Maker(password, username), role, new Date(), "Something like this!", true);

      //init--table sys_role
      userService.addRole(CommonUtil.getUUID(), "admin", "/sys/*", new Date());
      userService.addRole(CommonUtil.getUUID(), "user", "/user/*", new Date());

      for (int i = 0; i < 12; i++) {
        userService.addStory(CommonUtil.getUUID(), "My Reeeeeeeed",
            i + ".jpg", "Someone like you", new Date());
      }
      return resultMsg().successResult("Success");
    } catch (Exception ex) {
      return resultMsg().failureResult(ex.getMessage());
    }
  }


  @GetMapping("/logout")
  public String getOut() {
    logOut();
    return "/login";
  }

  private String registerCheck(String registername, String registerword,
                               String registerword2, String invitecode) {
    if (!StringUtils.isEquals(registerword, registerword2)) {
      return "两次密码输入不一致。";
    }
    UserInfo user = (UserInfo) userService.loadUserByUsername(registername);
    if (user != null) {
      return "该账号已注册。";
    }
    UserInfo inviter = (UserInfo) userService.loadUserByUsername(invitecode);
    if (null == inviter || !"ADMIN".equals(inviter.getSysRoleName())) {
      return "邀请码无效。";
    }
    return null;
  }
}
