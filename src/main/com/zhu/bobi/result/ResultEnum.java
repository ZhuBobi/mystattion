package com.zhu.bobi.result;

public enum ResultEnum {
  SUCCESS(100, "Success"),
  FAIL(200, "Fail"),
  NORIGHTS(201, "Without Rights"),//无权限
  EMPTYNAME(202, "Empty Username"),//空用户名
  NOTEXIST(203, "Not Exist Username"),//无该用户
  ERROEPASS(204, "Error Password"),//无该用户
  UNENABLE(205, "Unenable"),//未启用
  UNKNOWN(206, "Unknown");//未启用

  private int status;
  private String msg;

  public int getStatus() {
    return this.status;
  }

  public String getMsg() {
    return this.msg;
  }

  ResultEnum(int status, String msg) {
    this.status = status;
    this.msg = msg;
  }

  public static ResultEnum getEnumByStatus(int Status) {
    for (ResultEnum enmu : ResultEnum.values()) {
      if (enmu.status == Status) {
        return enmu;
      }
    }
    return ResultEnum.UNKNOWN;
  }
}
