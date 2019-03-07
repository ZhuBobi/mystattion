package com.zhu.bobi.service;

import com.zhu.bobi.entity.Story;

import java.util.Date;
import java.util.List;

public interface StoryService {

  List<Story> getAllStories();

  Story getNextStory(Date time);

  Story getNextStoryByRownum(int rownum);

  Story getNextStoryById(String id);

  List<Story> getInitStoryByTime();

  void addStory(Story story);

  int getTotalCount();
}
