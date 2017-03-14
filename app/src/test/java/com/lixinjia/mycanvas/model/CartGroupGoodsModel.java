package com.lixinjia.mycanvas.model;

import java.util.List;

public class CartGroupGoodsModel
{
	/*
	*   is_delivery	该商户下是否有需配送商品	number	@mock=1
        shop_total_count	商品数量	number
        shop_total_price	该商户下商品总价	number	@mock=829.8
        shop_total_score	该商户下商品总积分	number	@mock=0
        supplier	商品名称	string	@mock=百度烤肉
        supplier_consignee	商户配送方式列表	array<object>
         supplier_consignee	商户配送方式列表
         supplier_preferential	商户优惠列表
	* */

	private int id; // 商家id

	private String supplier; // 商家名字
	private List<CartGoodsModel> goods_list;
	private String deliveryFeeInfo; // 运费描述


	public List<Supplier_consignee> getSupplier_consignee() {
		return supplier_consignee;
	}

	public void setSupplier_consignee(List<Supplier_consignee> supplier_consignee) {
		this.supplier_consignee = supplier_consignee;
	}

	private List<Supplier_consignee> supplier_consignee;
	private List<Supplier_preferential> supplier_preferentials;

	public List<Default_consignee> getDefault_consignees() {
		return default_consignees;
	}

	public void setDefault_consignees(List<Default_consignee> default_consignees) {
		this.default_consignees = default_consignees;
	}

	private List<Default_consignee> default_consignees;
	//新增
	private int is_carriage;//该商户下是否有需配送商品
	private int is_delivery;//该商户下是否有需配送商品
	private int shop_total_count;//商品数量	number

	private double shop_total_price;//该商户下商品总价
	private int shop_total_score;//该商户下商品总积分
	private String supplier_id;//该商户id
	private DefaultConsigneeBean default_consignee;
	private String name;
	private String old_memo;


	public String getOld_memo() {
		return old_memo;
	}

	public void setOld_memo(String old_memo) {
		this.old_memo = old_memo;
	}

	public List<Supplier_preferential> getSupplier_preferentials() {
		return supplier_preferentials;
	}

	public void setSupplier_preferentials(List<Supplier_preferential> supplier_preferentials) {
		this.supplier_preferentials = supplier_preferentials;
	}



	public int getIs_carriage() {
		return is_carriage;
	}

	public void setIs_carriage(int is_carriage) {
		this.is_carriage = is_carriage;
	}

	public int getIs_delivery() {
		return is_delivery;
	}

	public void setIs_delivery(int is_delivery) {
		this.is_delivery = is_delivery;
	}

	public int getShop_total_count() {
		return shop_total_count;
	}

	public void setShop_total_count(int shop_total_count) {
		this.shop_total_count = shop_total_count;
	}

	public double getShop_total_price() {
		return shop_total_price;
	}

	public void setShop_total_price(double shop_total_price) {
		this.shop_total_price = shop_total_price;
	}

	public int getShop_total_score() {
		return shop_total_score;
	}

	public void setShop_total_score(int shop_total_score) {
		this.shop_total_score = shop_total_score;
	}

	public String getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}

	public DefaultConsigneeBean getDefault_consignee() {
		return default_consignee;
	}

	public void setDefault_consignee(DefaultConsigneeBean default_consignee) {
		this.default_consignee = default_consignee;
	}





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class DefaultConsigneeBean {
		private String text;
		private String value;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}


	public static class SupplierConsigneeBean {
		private String text;
		private String value;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public String getDeliveryFeeInfo()
	{
		return deliveryFeeInfo;
	}

	public void setDeliveryFeeInfo(String deliveryFeeInfo)
	{
		this.deliveryFeeInfo = deliveryFeeInfo;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getSupplier()
	{
		return supplier;
	}

	public void setSupplier(String supplier)
	{
		this.supplier = supplier;
	}

	public List<CartGoodsModel> getGoods_list()
	{
		return goods_list;
	}

	public void setGoods_list(List<CartGoodsModel> goods_list)
	{
		this.goods_list = goods_list;
	}

}
