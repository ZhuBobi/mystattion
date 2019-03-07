package com.zhu.bobi.controller.in;

import com.alipay.api.AlipayApiException;
import com.zhu.bobi.aliPay.AliPayConfig;
import com.zhu.bobi.controller.BaseController;
import com.zhu.bobi.entity.Story;
import com.zhu.bobi.service.StoryService;
import com.zhu.bobi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class InnerController extends BaseController {

  @Autowired
  private StoryService storyService;

  @Autowired
  private UserService userService;

  @Autowired
  SessionRegistry sessionRegistry;

  @RequestMapping("/lovesickness")
  public String lovesickness() {
    logger.debug("进入lovesickness");
    return "in/lovesickness";
  }

  @GetMapping("story/list")
  @ResponseBody
  public Map<String, Object> getStories(HttpServletRequest request, HttpServletResponse response) {
    String page = request.getParameter("page");
    Integer pageNum = Integer.parseInt(page) + 1;
    Story story = storyService.getNextStoryByRownum(pageNum);
    int total = storyService.getTotalCount() - 1;
    Map<String, Object> map = new HashMap<>();
    map.put("data", story);
    map.put("pages", total);
    return map;
  }

  @GetMapping("story/add")
  @ResponseBody
  public Map<String, Object> addStory(Story story) {
    storyService.addStory(story);
    Map<String, Object> map = new HashMap<>();
    map.put("data", story);
    return map;
  }

  @GetMapping("story/list/")
  @ResponseBody
  public Map<String, Object> getInitStories() {
    List<Story> stories = storyService.getInitStoryByTime();
    Map<String, Object> map = new HashMap<>();
    map.put("data", stories);
    return map;
  }

  @RequestMapping(value = "/logout_demo")
  public void logout(String username) {
    UserDetails user = userService.loadUserByUsername(username);
    List<SessionInformation> allSessions = sessionRegistry.getAllSessions(user, false);
    if (allSessions != null) {
      for (int i = 0; i < allSessions.size(); i++) {
        SessionInformation sessionInformation = allSessions.get(i);
        sessionInformation.getSessionId();
        sessionInformation.expireNow();
      }
    }
  }

  @RequestMapping(value = "/them/getYangJingInfo")
  @ResponseBody
  public String getYangJingInfo(String username) {
  return "页面建设中。。。。";
  }

  @RequestMapping(value = "/them/getYangBoInfo")
  @ResponseBody
  public String getYangBoInfo(String username) {
  return "页面建设中。。。。";
  }

  @RequestMapping(value = "/them/getPengYixuanInfo")
  @ResponseBody
  public String getPengYixuanInfo(String username) {
  return "页面建设中。。。。";
  }

  @RequestMapping(value = "/them/getWangSenInfo")
  @ResponseBody
  public String getWangSenInfo(String username) {
  return "页面建设中。。。。";
  }

  @RequestMapping(value = "/them/getYangQiulinInfo")
  @ResponseBody
  public String getYangQiulinInfo(String username) {
  return "页面建设中。。。。";
  }

  @RequestMapping(value = "/them/getZhouXueInfo")
  @ResponseBody
  public String getZhouXueInfo(String username) {
  return "页面建设中。。。。";
  }



}
