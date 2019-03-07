package com.zhu.bobi.result;

import com.zhu.bobi.commonutil.IGenericPage;
import org.springframework.http.HttpStatus;

import java.util.List;

public class Msg {

  private HttpStatus httpStatus;

  public Msg(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public Msg() {
    this.httpStatus = HttpStatus.OK;
  }

  /**
   * 输出列表
   *
   * @param list
   * @param count
   * @return
   */
  public Result list(List<?> list, Long count) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setList(list);
    mb.setCount(count);
    mb.setStatus(true);
    return m;
  }

  /**
   * 输出列表
   *
   * @param list
   * @return
   */
  public Result list(List<?> list) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setList(list);
    mb.setStatus(true);
    return m;
  }

  public Result data(Object data) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setData(data);
    mb.setStatus(true);
    return m;
  }

  /*
   * 输出分页数据
   *
   * @param list
   * @param count
   * @param page
   * @param pageSize
   * @return
   **/
  public Result page(List<?> list, Long count, Integer page, Integer pageSize) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setData(list);
    mb.setCount(count);
    mb.setPage(page);
    if (pageSize > 0) {
      Integer totalPage = (count % pageSize == 0) ? new Double(Math.floor(count / pageSize)).intValue() : (new Double(Math.floor(count / pageSize)).intValue() + 1);
      mb.setTotalPage(totalPage);
    }
    mb.setStatus(true);
    return m;
  }

  /*
   * 输出分页数据
   *
   * @param page
   * @return
   **/
  public Result page(IGenericPage<?> page) {
    return page(page.getThisPageElements(), page.getCount(), page.getPage(), page.getLimit());
  }

  /*
   * 输出简单信息
   *
   * @param msg
   * @param isSuccess
   * @return
   **/
  public Result msg(String msg, boolean isSuccess) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setMessage(msg);
    mb.setStatus(isSuccess);
    return m;
  }

  /*
   * 输入成功标志消息
   *
   * @param msg
   * @return
   **/
  public Result successResult(String msg) {
    return msg(msg, true);
  }

  public Result successResult(ResultEnum resultEnum) {
    return msg(resultEnum.getMsg(), true);
  }

  /**
   * 输入失败标志消息
   *
   * @param msg 入参
   * @return Result
   */
  public Result failureResult(String msg) {
    return msg(msg, false);
  }

  public Result failureData(Object data) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setData(data);
    mb.setStatus(false);
    return m;
  }

  public Result failureMessage(String message) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setMessage(message);
    mb.setStatus(false);
    return m;
  }

  public Result pageData(Integer code, Long count, Object data, String msg) {
    ResultBody mb = new ResultBody();
    Result m = new Result(mb, httpStatus);
    mb.setData(data);
    mb.setCode(code);
    mb.setCount(count);
    mb.setStatus(true);
    mb.setMessage(msg);
    return m;
  }
}
