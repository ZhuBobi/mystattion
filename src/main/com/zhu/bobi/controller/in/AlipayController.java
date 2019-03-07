package com.zhu.bobi.controller.in;

import com.zhu.bobi.aliPay.AliPayConfig;
import com.zhu.bobi.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class AlipayController  extends BaseController {

    @RequestMapping(value = "/alipay")
    public String aliPay(){
        String result = null;
        try {
            result = AliPayConfig.aliPay();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return  result;
    }
}
