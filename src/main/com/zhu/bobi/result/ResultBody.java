package com.zhu.bobi.result;

import java.util.Collection;

public class ResultBody {

  private boolean status;

  private String message;

  private Object data;

  private Collection<?> list;

  private Long count;//总条数

  private Integer page;//当前页

  private Integer totalPage;//总页数

  private Integer code = 0;

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Collection<?> getList() {
    return list;
  }

  public void setList(Collection<?> list) {
    this.list = list;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

}
