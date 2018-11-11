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

    /**
     * 获取用户user_id、name、mobile
     *
     * @param accessToken
     * @return
     */
    public static List<Teacher> getUerId(String accessToken) {
        List<Teacher> list = new ArrayList<Teacher>();
        StringBuilder result = new StringBuilder();
        StringBuilder url = new StringBuilder("https://qyapi.weixin.qq.com/cgi-bin/user/list?");
        url.append(IWxInfo.ACCESS_TOKEN + "=" + accessToken);
        url.append("&" + "department_id" + "=" + "4");
        url.append("&" + "fetch_child" + "=" + "0");
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
            if (jsonObject.has("userlist")) {
                JSONArray array = jsonObject.getJSONArray("userlist");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject ob = (JSONObject) array.get(i);//得到json对象
                    //System.out.println(ob.toString());
                    Teacher teacher = new Teacher(ob.getString("userid"), ob.getString("name"), ob.getString("mobile"),ob.getString("email"));
                    list.add(teacher);

                }
            } else {
                System.out.print("该组成员为空");
            }
        } catch (Exception ex) {

        }
        return list;
    }

}
