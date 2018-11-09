package com.xuyu.message;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 对微信企业号的操作，获取用户的详细信息，发送消息
 */
public class WxApi {
    /**
     * 发送信息，返回值可作为发送成功的判断
     *
     * @param accessToken 有效期内的accessToken
     * @param User        用户的user_id
     * @param txtContent  发送的信息内容
     * @return 信息是否发送成功
     */
    public static boolean sendTxtMsg1(String accessToken, String User, String txtContent) {
        if (null == User || null == accessToken) {
            return false;
        }
        StringBuilder url = new StringBuilder("https://qyapi.weixin.qq.com/cgi-bin/message/send?");
        url.append(IWxInfo.ACCESS_TOKEN + "=" + accessToken);
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url.toString());
            HttpGet get = new HttpGet();
            JSONObject root = new JSONObject();
            root.put(IWxInfo.TOUSER, User);
            root.put(IWxInfo.MSGTYPE, "text");
            root.put(IWxInfo.AGENTID, "1000002");
            JSONObject content = new JSONObject();
            content.put(IWxInfo.CONTENT, txtContent);
            root.put(IWxInfo.TEXT, content);
            root.put(IWxInfo.SAFE, 0);

            StringEntity stringEntity = new StringEntity(root.toString(), "utf-8");
            httpPost.setEntity(stringEntity);

            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(httpPost);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            JSONObject jsonObject = new JSONObject(result.toString());
            String netResult = jsonObject.getString(IWxInfo.INVALIDUSER);
            if (netResult.equals("")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }


    /**
     * 根据老师的UserId获取老师的姓名、电话号码
     * @param accessToken 微信企业号获取的accessToken
     * @param userId  老师的User_id
     * @return teacher对象
     */
    public static Teacher getTeacher(String accessToken, String userId) {
        Teacher teacher = null;
        StringBuilder result = new StringBuilder();
        StringBuilder url = new StringBuilder("https://qyapi.weixin.qq.com/cgi-bin/user/get?");
        url.append(IWxInfo.ACCESS_TOKEN + "=" + accessToken);
        url.append("&" + "userid" + "=" + userId);
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url.toString());
            HttpResponse response = client.execute(request);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            JSONObject jsonObject = new JSONObject(result.toString());
            if (!"".equals(jsonObject)) {
                teacher = new Teacher(jsonObject.getString("userid"), jsonObject.getString("name"), jsonObject.getString("mobile"), jsonObject.getString("email"));
            } else {
                System.out.print("该组成员为空");
            }
        } catch (Exception ex) {

        }
        return teacher;
    }

}
