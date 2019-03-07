package com.zhu.bobi.dao;

import com.zhu.bobi.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface StoryDao extends JpaRepository<Story, Long> {

  Story getById(String id);

  @Query(value = "select * from our_story ORDER by TIME  DESC ", nativeQuery = true)
  List<Story> getAllStories();

  @Query(value = "select top 1 from our_story where time >?1 ORDER by TIME  DESC ", nativeQuery = true)
  Story getNestStoryByTime(Date tiem);

  @Query(value = "select top 1 from our_story where id >?1 ORDER by id  DESC ", nativeQuery = true)
  Story getNestStoryById(String id);

  @Query(value = "select * from our_story order by time limit 0,1 ", nativeQuery = true)
  List<Story> getInitStoryByTime();

  @Query(value = "select * from our_story ORDER by TIME  limit ?1 offset ?2 ", nativeQuery = true)
  Story getNextStoryByRownum(int m, int n);

  @Transactional
  @Modifying
  @Query(value = "insert into  our_story (id,msg,img_path,note,time) value(?1,?2,?3,?4,?5)", nativeQuery = true)
  void addStory(String id, String msg, String imgPath, String note, Date time);

  @Query(value = "select count(1) from our_story", nativeQuery = true)
  int getTotalCount();
}
