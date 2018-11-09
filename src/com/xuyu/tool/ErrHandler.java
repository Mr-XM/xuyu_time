package com.xuyu.tool;

/**
 * 错误捕捉类，输出错误
 */
public class ErrHandler {
    public static String Error(int i){
        String error=null;
        switch (i){
            case 0 :error="该链接无效";break;
            case 1 :error="当前页面已失效";break;
            case 2 :error="该链接与您的身份信息不匹配";break;
            case 3 :error="有赞商城商家编码不能匹配，请及时联系管理员";break;
        }
        return error;
    }
}
