package com.zhu.bobi.service.impl;

import com.zhu.bobi.dao.StoryDao;
import com.zhu.bobi.entity.Story;
import com.zhu.bobi.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

  @Autowired
  private StoryDao storyDao;

  @Override
  public List<Story> getAllStories() {
    return storyDao.getAllStories();
  }

  @Override
  public Story getNextStory(Date time) {
    return storyDao.getNestStoryByTime(time);
  }

  @Override
  public Story getNextStoryByRownum(int rownum) {
    Integer m = 1;
    Integer n = rownum - 1;
    return storyDao.getNextStoryByRownum(m, n);
  }

  @Override
  public Story getNextStoryById(String id) {
    return storyDao.getNestStoryById(id);
  }

  @Override
  public List<Story> getInitStoryByTime() {
    return storyDao.getInitStoryByTime();
  }

  @Override
  public void addStory(Story story) {
    storyDao.addStory(story.getId(), story.getMsg(),
        story.getImgPath(), story.getNote(), story.getTime());
  }

  @Override
  public int getTotalCount() {
    return storyDao.getTotalCount();
  }
}
