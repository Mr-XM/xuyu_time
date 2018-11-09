package com.xuyu.youzan;

public class YouzanGoods {
	/**
	 * 商品价格
	 */
	private int price;

	/**
	 *星期几
	 */
	private String time;

	/**
	 * 商品的skus
	 */
	private String skus;

	/**
	 * 商品的sku_id
	 */
	private int sku_id;

	public YouzanGoods(int p, String t, String s, int skuid) {
		price = p;
		time = t;
		skus = s;
		sku_id = skuid;
	}

	public int getPrice() {
		return price;
	}

	public String getTime() {
		return time;
	}

	public String getSkus() {
		return skus;
	}

	public int getskuId() {
		return sku_id;
	}
}
