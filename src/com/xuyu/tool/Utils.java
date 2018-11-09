package com.xuyu.tool;

import org.apache.http.util.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 获得GOauth2Core工具类，将url惊醒Encode处理
 */
public class Utils {
    public static String urlEncode(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        try {
            String urlEncodedStr = URLEncoder.encode(url, "utf-8");
            return urlEncodedStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
}
