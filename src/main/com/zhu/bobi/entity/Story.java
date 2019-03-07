package com.zhu.bobi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhu.bobi.commonutil.ObjectClone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "our_story", schema = "zhu")
public class Story extends BaseId  implements Serializable {

  private String id;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  private String msg;
  private String imgPath;
  private String note;

  @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
  private Date time;


  @Column(name = "MSG")
  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Column(name = "IMG_PATH")
  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  @Column(name = "NOTE")
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Column(name = "TIME")
  public Date getTime() {
    return (Date)ObjectClone.getInstance().clone(time);
  }

  public void setTime(Date time) {
    this.time = time;
  }
}
