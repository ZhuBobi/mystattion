package com.zhu.bobi.aliPay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AliPayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092000555447";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC59UJfzTce+f0AQ7MhJAWL0CrNEsD63crVxbYyneAq9DmQqVesTj3cj+Nmiv5VLiHogvv2tRL8PTijSYLh/yRwgBJYCJpVo8QAlcti69L0aXJH79BvZEUMZ9ZFBWBrxX0x1ITQzunpESmuv6c0gSBSfVeK4onbXaMZQ8HApNdyQNIHOIQsy93pZlBreQkzXaMZ90DcDNi9WWHuIGXWHS3cxmLlHNx3gsbIFGypSyptT0yQjVzWvJr0nnkSCBI0kmndtS0twhyxm/ByV4hcVrwc0TDN6KyRm3Edm2ytsjiDDQabUUiTWZv5mbALad68Xae8L//nD+ibVqlWSrq4qDPFAgMBAAECggEBAJIg9SOrc+FXuUT37k/gqqsPzlKQpi19FOOgFmj4PywB9yqCqG6ynVIxkBUIrIvrUKISLEXWTTECOvu1zLAje1MckU20lPMvWdrL26PEhWWjZza4cSbXuJrjDjH246pM6vC5fBVKQ+3umr7DxHUx82BfNufg1UwY7Q3xfNQRtWwFxRqyYQ77PD9oC7Fe345uY8Zs8sp5If+lbp3l6pykJuwbSxe9kSn/4AEftH9Kp1oKef4kZZTqEU8rdHur8ccWmeX0BuMUkXrRp7hysqQ38jRZPrXPJmfrr/5L+kRJyf9Ec7QbR8QbZyn09rpLFsUgl9KggWDomSARtFW6yQqe6ZECgYEA220UEwcLuYk+l5LgNuxcDBCfvrQlgnYXl4ml54pkUtwfOLeXyNJmHcnlRjC48GyflObZ1mf9A4DaERGmg1n0cu1deTFZkSiSOY9NDJxF1mUOFRjc395/oeI3muyl+EuREt3qQZrKFFQ5WxM3zYFw5kCUwa/R8dRo3F0jQ6Q1byMCgYEA2PQZDMnoQD036WUAXrpXN5yOPdvjsi0NqiooVQtx5LlrVtxfO+QtX0eioCSgltXUEHWiaCS+aE1jZXsKTVKhxv85y0s6sOEqeENne6f+fjkHCPEf8MXeRUAkaHg79bpYXaL2Y0sFisLNoMn6MHxngwNFFntGBszCtgznINaSM/cCgYBSnybw5zVF/mSgJEDizWr3bAcKgsCg+CB4Ae+DWLvf61yiOZcgmgCzdMMtA8mPVgdcxPP/ZJaDjDAQP0ooqWV/JNuOd8OXAeTVYZ0Mg4dUbCa6UOtSLHB4nzp6zmLMNIACz6aWX1d6AiC6p2jaq8O8H1Mj/zUZTvbRCwztOlaYawKBgCViYpKmlCPFqMXKBrdMCs1lZ8lXA7RnOQySeSK2dyi/nrR4At0SI5Ngh7Bqv1VegU1Yx+SEOF9GvpL1EcBjNcdRoYbbbsCd/DB/BOz09NCRSRoMWsGlhviN5fguh75/Hsz70NMpUUoo6UAErdz/AuM7hAMsuNNhI0g1jvpEnyrfAoGBAI30gOp4UZZg7iVXcbRHNFUfjZnVRcSJ3vlNbGU4Zjz6kU6yBOXe0ttfjAQU22gaV74vRLtNP4rTpzUdbNmuWZfNbsyvOyFKUhHTDj+2Z1qW7gb5y8WOFt46777ME5beJx2O0+q64X9liwhWHx9U/gv+IdyW/6K+5n5vYj8IeP2W";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsHkMUBVQ/3j6CFeFUTLW37M0xphgGz6vK8BSMVX7LV3LTgVBwbFCEAlb+m6hbFVWihFxDqYSXXBEs//BD2WWBlmo2HQgrt9UZTzwHLCgZJkvg1qV7YPLDGD62z/t/caehbt9BZXaD5kEDooIwrQctEZqndcZJxcOEucK5SvSePYbn9s3uV3ldVm2FcxGvW0biDLWkBSX3yJhrVmxzB6wv+Lm7G9sqbWn1d9Mz8RKtiZB55yESKb+1beTKKJbZ9wexOehf+HYWmTT/9U7Kl9v5hqRowUxMMITloknWPuoc5TQFdq56yYaUCwBdsJFEEbWgYDpddU9x/dnoZZE4gJ7QwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://zhubo.yasinyt.com/loginMid";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://zhubo.yasinyt.com/index";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


    public static String dev_gateway = "https://openapi.alipaydev.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String aliPay() throws UnsupportedEncodingException {
        AlipayClient alipayClient = new DefaultAlipayClient(dev_gateway, app_id, merchant_private_key, "json", charset, alipay_public_key, "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(AliPayConfig.return_url);
        request.setNotifyUrl(AliPayConfig.notify_url);

        String out_trade_no = new String("20181114165433214".getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String("2999.99".getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String("零食".getBytes("ISO-8859-1"),"UTF-8");
        String buyer_id = new String("20181114165".getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String("".getBytes("ISO-8859-1"),"UTF-8");
        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
            + "\"total_amount\":\""+ total_amount +"\","
            + "\"subject\":\""+ subject +"\","
            + "\"buyer_id\":\""+ buyer_id +"\","
            + "\"body\":\""+ body +"\","
            + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result="";
        try {
            result = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return  result;
    }

}