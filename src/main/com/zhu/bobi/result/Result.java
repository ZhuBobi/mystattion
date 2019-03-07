package com.zhu.bobi.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class Result extends ResponseEntity {

  public Result(HttpStatus status) {
    super(status);
  }

  public Result(ResultBody body, HttpStatus status) {
    super(body, status);
  }

  public Result(MultiValueMap headers, HttpStatus status) {
    super(headers, status);
  }

  public Result(ResultBody body, MultiValueMap headers, HttpStatus status) {
    super(body, headers, status);
  }
}
