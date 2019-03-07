package com.zhu.bobi.controller.mid;

import com.zhu.bobi.controller.BaseController;
import com.zhu.bobi.entity.Story;
import com.zhu.bobi.service.StoryService;
import com.zhu.bobi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping
public class MidController extends BaseController {

  @Autowired
  private UserService userService;

  @Autowired
  private StoryService storyService;

  @GetMapping("loginMid")
  public String loginMid(Model model) {
    logger.info("进入loginMid");
    if (checkAuthentication()) {
      logger.info("未登陆或登陆失效");
      return "out/login";
    }
    List<Story> stories = storyService.getInitStoryByTime();
    model.addAttribute("stories", stories);
    return "mid/loginMid";
  }

  @GetMapping("firsteye")
  public String firstEye() {
    logger.info("进入firsteye");
    if (checkAuthentication()) {
      logger.info("未登陆或登陆失效");
      return "out/login";
    }
    return "in/firstEye";
  }
}
