package com.xuyu.youzan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuyu.tool.TimeUtils;
import com.xuyu.tool.Change;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.*;
import com.youzan.open.sdk.gen.v3_0_0.model.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 关于有赞库存的处理
 */
public class YouzanApi {

	/**
	 * 获得商品的itemId
	 * @param itemNo
	 * @return
	 */
	public static String getItem_id(String itemNo) {
    	String accessToken = YouzanContext.getInstance().getAccessToken();
        YZClient client = new DefaultYZClient(new Token(accessToken));
        YouzanItemsCustomGetParams youzanItemsCustomGetParams = new YouzanItemsCustomGetParams();
    	youzanItemsCustomGetParams.setItemNo(itemNo);
    	
    	YouzanItemsCustomGet youzanItemsCustomGet = new YouzanItemsCustomGet();
    	youzanItemsCustomGet.setAPIParams(youzanItemsCustomGetParams);
    	YouzanItemsCustomGetResult result = client.invoke(youzanItemsCustomGet);
    	try {
            ObjectMapper mapper = new ObjectMapper();  
            JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(result));
            JSONArray array =jsonObj.getJSONArray("items"); 
            if(!array.isNull(0)) {
            	String item_id=array.getJSONObject(0).get("item_id").toString();
                return item_id;
            }else {
            	return null;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

	/**
	 * 创建一个sku_stocks json字符串
	 * @param list 有赞商品原来的一些信息，价格、库存
	 * @param data 用户设置完提交界面获取到的上课时间设置信息
	 * @param cnt 从有赞库存获得的不能选择的上课时间 IDLE库存为0（即被选择）
	 * @return  构造好的，在更新商品信息用到的json字符串
	 */
	public static String createSkuStocks(List<YouzanGoods> list, String data[], String cnt) {
		String sku_stocks=null;
		StringBuilder s=new StringBuilder();
		TimeUtils abouttime=new TimeUtils();
		Change change =new Change();
		change.initQuantityAndFlag(data,cnt);
		if(!list.isEmpty()) {
			s.append("[");
			for(int i=0;i<list.size();i++) {
				if (i > 0 && i <list.size()) {
	                s.append(",");
	            }
				s.append("{"+"\"price\":"+list.get(i).getPrice()+",\"quantity\":"+change.getQuantity(list.get(i).getTime())+",\"item_no\":"+"\""+change.getSign(change.getValueb(list.get(i).getTime()))+abouttime.change(list.get(i).getTime())+"\""+",\"skus\":"+list.get(i).getSkus()+"}");
			}
			s.append("]");
		}
		sku_stocks=s.toString();
		return sku_stocks;
	}


	/**
	 * 根据用户的ItemId获得有赞商品信息
	 * @param numIid 用户的ItemId
	 * @return
	 */
	public static List<YouzanGoods> getSkus(Long numIid){
		List<YouzanGoods> list = new ArrayList<>();
		String accessToken = YouzanContext.getInstance().getAccessToken();
        YZClient client = new DefaultYZClient(new Token(accessToken));
        YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();
        youzanItemGetParams.setItemId(numIid);

        YouzanItemGet youzanItemGet = new YouzanItemGet();
        youzanItemGet.setAPIParams(youzanItemGetParams);
        YouzanItemGetResult result = client.invoke(youzanItemGet);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(result));
            JSONObject jsonObj1=jsonObj.getJSONObject("item") ;
            JSONArray array=jsonObj1.getJSONArray("skus");
            for(int i=0;i<array.length();i++) {
            	JSONObject ob = array.getJSONObject(i);
            	JSONArray array2=new JSONArray(ob.getString("properties_name_json"));
            	for(int j=0;j<array2.length();j++)
            	{
            		JSONObject ob1 = array2.getJSONObject(j);
                	if(ob1.getString("k").toString().equals("可选时间")) {
                		YouzanGoods good=new YouzanGoods(ob.getInt("price"),ob1.getString("v"),ob.getString("properties_name_json"),ob.getInt("sku_id"));
                		list.add(good);
                	}
    		}
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
     	return list;
	}

	/**
	 * 更新商品
	 * @param item_id 该用户对应的有赞商城的item_id
	 * @param sku_stocks   更新商品用的sku_stocks (json)字符串
	 * @return boolean是否更新成功的判定依据
	 */
	public static boolean updateGood(String item_id,String sku_stocks) {
		String token = YouzanContext.getInstance().getAccessToken();
		YZClient client = new DefaultYZClient(new Token(token));
		YouzanItemUpdateParams youzanItemUpdateParams = new YouzanItemUpdateParams();
		
		youzanItemUpdateParams.setItemId(Long.parseLong(item_id));
		youzanItemUpdateParams.setSkuStocks(sku_stocks);
		
		YouzanItemUpdate youzanItemUpdate = new YouzanItemUpdate();
		youzanItemUpdate.setAPIParams(youzanItemUpdateParams);
		YouzanItemUpdateResult result = client.invoke(youzanItemUpdate);
		return result.getIsSuccess();
	}


	/**
	 * 从有赞获取默认有空的时间
	 * @param numIid  商品编码
	 * @return
	 */
	public static String getDEF_CHOOSED(Long numIid) {
		StringBuffer s = new StringBuffer();
		String data = null;
		int k=0;
		String accessToken = YouzanContext.getInstance().getAccessToken();
		YZClient client = new DefaultYZClient(new Token(accessToken));
		YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();
		youzanItemGetParams.setItemId(numIid);

		YouzanItemGet youzanItemGet = new YouzanItemGet();
		youzanItemGet.setAPIParams(youzanItemGetParams);
		YouzanItemGetResult result = client.invoke(youzanItemGet);
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(result));
			JSONObject jsonObj1=jsonObj.getJSONObject("item") ;

			JSONArray array=jsonObj1.getJSONArray("skus");
			for(int i=0;i<array.length();i++) {
				JSONObject ob = array.getJSONObject(i);
				JSONArray array2=new JSONArray(ob.getString("properties_name_json"));
				if(ob.getString("item_no")!=null&&ob.getString("item_no").length()>4) {
					if(ob.getString("item_no").substring(0, 4).equals("IDLE")) {
						for(int j=0;j<array2.length();j++)
						{
							JSONObject ob1 = array2.getJSONObject(j);
							if(ob1.getString("k").toString().equals("可选时间")) {
								if (k > 0 && k <array.length()) {
									s.append(",");
								}
								s.append(Change.setValue(ob1.getString("v")));
							}
						}
						k++;
					}

				}
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		data=s.toString();
		return data;
	}


	/**
	 * 获得该用户默认的商品，并转换成对应的数据
	 * @param numIid   item_id
	 * @return
	 */
	 public static String getCNT_CHOOSED(Long numIid) {
		 StringBuffer s = new StringBuffer();
		    int k=0;
		    String data = null;
		    String accessToken = YouzanContext.getInstance().getAccessToken();
			YZClient client = new DefaultYZClient(new Token(accessToken));
	        YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();
	        youzanItemGetParams.setItemId(numIid);

	        YouzanItemGet youzanItemGet = new YouzanItemGet();
	        youzanItemGet.setAPIParams(youzanItemGetParams);
	        YouzanItemGetResult result = client.invoke(youzanItemGet);
	        try {
	            ObjectMapper mapper = new ObjectMapper();  
	            JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(result));
	            JSONObject jsonObj1=jsonObj.getJSONObject("item") ;
	      
	            JSONArray array=jsonObj1.getJSONArray("skus");
	            for(int i=0;i<array.length();i++) {
	            	JSONObject ob = array.getJSONObject(i);
	            	JSONArray array2=new JSONArray(ob.getString("properties_name_json"));
	            	if(ob.getString("item_no")!=null&&ob.getString("item_no").length()>4) {
	            		if(ob.getString("item_no").substring(0, 4).equals("IDLE")&&ob.getInt("quantity")==0) {
	                		for(int j=0;j<array2.length();j++)
	                    	{
	                    		JSONObject ob1 = array2.getJSONObject(j);
	                        	if(ob1.getString("k").toString().equals("可选时间")) {
	                        		if (k > 0 && k <array.length()) {
	                	                s.append(",");
	  
	                	            }
	                	            s.append(Change.setValue(ob1.getString("v")));
	                        	}
	            		}
	                		k++;
	            	 }
	            	
	            	}
	            }
	            
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        data=s.toString();
	        return data;
	 }
}

