package com.xuyu.message;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;
import org.json.JSONObject;

import com.xuyu.tool.Utils;

/**
 * Oauth认证后根据code获取user_id
 */
public class GOauth2Core {
	public static final String REDIRECT_URI = "http://www.xuyutech.com/xuyu_time/oauth";
	public static String GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORP_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	/**
	 * 企业获取code地址处理
	 * <p>
	 * appid            企业的CorpID
	 * redirect_uri     授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * response_type    返回类型，此时固定为：code
	 * scope            应用授权作用域，此时固定为：snsapi_base
	 * state            重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值
	 * #wechat_redirect 微信终端使用此参数判断是否需要带上身份信息
	 * 员工点击后，页面将跳转至 redirect_uri/?code=CODE&state=STATE，企业可根据code参数获得员工的userid
	 */
	public String getCode(String state) {
		return GET_CODE.replace("CORP_ID", IWxInfo.CORP_ID).replace("REDIRECT_URI", Utils.urlEncode(REDIRECT_URI)).replace("STATE", state);
	}

	public static final String USERINFO_BASE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?";

	/**
	 * 根据code获取成员信息
	 *
	 * @param access_token 调用接口凭证
	 * @param code         通过员工授权获取到的code，每次员工授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
	 */
	public static String getUserID(String access_token, String code) {
		String userId = "";
		String url = USERINFO_BASE_URL + "access_token=" + access_token + "&code=" + code;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		String responseStr = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				responseStr = EntityUtils.toString(entity, "utf-8");
				JSONObject jsonobject = new JSONObject(responseStr);
				if (null != jsonobject) {
					userId = jsonobject.getString("UserId");
					if (TextUtils.isEmpty(userId)) {
						int errorrcode = jsonobject.getInt("errcode");
						String errmsg = jsonobject.getString("errmsg");

						userId = "errcode:" + errorrcode + ",errmsg:" + errmsg;
					}
				}
			}
		} catch (Exception ex) {
			userId = "exception:" + ex.toString() + ",json:" + responseStr;
		}
		return userId;
	}

}

