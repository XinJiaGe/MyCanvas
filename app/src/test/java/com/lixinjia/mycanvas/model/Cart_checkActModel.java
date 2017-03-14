package com.lixinjia.mycanvas.model;


import com.lixinjia.mycanvas.utile.SDCollectionUtil;
import com.lixinjia.mycanvas.utile.SDTypeParseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Cart_checkActModel extends BaseActModel
{

	private List<Delivery_listModel> delivery_list;
	private List<Payment_listModel> payment_list;
	private List<VoucherModel> voucher_list;
	private List<CartGroupGoodsModel> listCartGroupsGoods;


	//private List<Cart_list_group> cart_list_groups;
	private Cart_count_buy_totalModel calculateModel;
	private Map<String, CartGroupGoodsModel> cart_list_group; // 商品数据.key:商家id,value:商品数据
	private Map<String, CartGroupGoodsModel> cart_list; // 商品数据.key:商家id,value:商品数据
	private CartTotal_dataModel total_data;

	private int is_score; // 0:普通商品，1:积分商品
	private int is_coupon; // 是否为发券订单，0否 1:是
	private int consignee_count; // 预设的配送地址数量 0：提示去设置收货地址 1以及以上显示选择其他收货方式
	private Consignee_infoModel consignee_info;
	private int is_delivery; // 是否需要配送 0无需 1需要
	private int show_payment; // 是否要显示支付方式 0:否（0元抽奖类） 1:是
	private int has_account; // 是否显示余额支付 0否 1是
	private int has_ecv; // 是否显示代金券支付 0否 1是
	private double account_money; // 余额
	private String order_memo; // 订单备注(用户继续支付时候返回)
	private int city_id;
	private String city_name;
	private String ctl;
	private int group_id;
	private String info;
	private String page_title;
	// add
	private  CartGroupGoodsModel groupGoodsModel;
	private String account_moneyFormat;

	public Map<String, CartGroupGoodsModel> getCart_list() {
		return cart_list;
	}

	public void setCart_list(Map<String, CartGroupGoodsModel> cart_list) {
		this.cart_list = cart_list;
	}

	public double getAccount_money() {
		return account_money;
	}

	public void setAccount_money(double account_money) {
		this.account_money = account_money;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	@Override
	public String getCtl() {
		return ctl;
	}

	@Override
	public void setCtl(String ctl) {
		this.ctl = ctl;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String getPage_title() {
		return page_title;
	}

	@Override
	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}

	@Override
	public String getRef_uid() {
		return ref_uid;
	}

	@Override
	public void setRef_uid(String ref_uid) {
		this.ref_uid = ref_uid;
	}

	@Override
	public String getSess_id() {
		return sess_id;
	}

	@Override
	public void setSess_id(String sess_id) {
		this.sess_id = sess_id;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	/*ref_uid		string	@mock=
		return		number	@mock=1
	sess_id		string	@mock=
	show_payment		number	@mock=1
	status		number	@mock=1
	total_data	购物车总价统计,结构如下	object
	return_total_score	当is_score为1时的总价显示	number	@mock=0
	total_price	当is_score为0时的总价显示	number	@mock=1057.8
	voucher_list		object*/

	private String ref_uid;
	private String sess_id;
	private int status;






	public Map<String, CartGroupGoodsModel> getCart_list_group()
	{
		return cart_list_group;
	}

	public void setCart_list_group(Map<String, CartGroupGoodsModel> cart_list_group)
	{
		this.cart_list_group = cart_list_group;
		updateListCartGroupsGoods();
	}

	public void updateListCartGroupsGoods()
	{
		if (cart_list_group != null)
		{
			List<CartGroupGoodsModel> listModel = new ArrayList<CartGroupGoodsModel>();
			for (Entry<String, CartGroupGoodsModel> item : cart_list_group.entrySet())
			{
				CartGroupGoodsModel value = item.getValue();
				listModel.add(value);
			}
			setListCartGroupsGoods(listModel);
		}
	}

	public List<CartGroupGoodsModel> getListCartGroupsGoods()
	{
		return listCartGroupsGoods;
	}

	public void setListCartGroupsGoods(List<CartGroupGoodsModel> listCartGroupsGoods)
	{
		this.listCartGroupsGoods = listCartGroupsGoods;
	}

	public List<VoucherModel> getVoucher_list()
	{
		return voucher_list;
	}

	public void setVoucher_list(List<VoucherModel> voucher_list)
	{
		if (!SDCollectionUtil.isEmpty(voucher_list))
		{
			VoucherModel model = new VoucherModel();
			model.setSn("");
			model.setName("不使用红包");
			voucher_list.add(0, model);
		}
		this.voucher_list = voucher_list;
	}

	public String getOrder_memo()
	{
		return order_memo;
	}

	public void setOrder_memo(String order_memo)
	{
		this.order_memo = order_memo;
	}

	public String getAccount_moneyFormat()
	{
		return account_moneyFormat;
	}

	public void setAccount_moneyFormat(String account_moneyFormat)
	{
		this.account_moneyFormat = account_moneyFormat;
		this.account_moneyFormat = SDFormatUtil.formatMoneyChina(account_money);
	}



	public CartTotal_dataModel getTotal_data()
	{
		return total_data;
	}

	public void setTotal_data(CartTotal_dataModel total_data)
	{
		this.total_data = total_data;
	}

	public int getIs_score()
	{
		return is_score;
	}

	public void setIs_score(int is_score)
	{
		this.is_score = is_score;
	}

	public int getIs_coupon()
	{
		return is_coupon;
	}

	public void setIs_coupon(int is_coupon)
	{
		this.is_coupon = is_coupon;
	}

	public int getConsignee_count()
	{
		return consignee_count;
	}

	public void setConsignee_count(int consignee_count)
	{
		this.consignee_count = consignee_count;
	}

	public Consignee_infoModel getConsignee_info()
	{
		return consignee_info;
	}

	public void setConsignee_info(Consignee_infoModel consignee_info)
	{
		this.consignee_info = consignee_info;
	}

	public List<Delivery_listModel> getDelivery_list()
	{
		return delivery_list;
	}

	public void setDelivery_list(List<Delivery_listModel> delivery_list)
	{
		this.delivery_list = delivery_list;
	}

	public List<Payment_listModel> getPayment_list()
	{
		return payment_list;
	}

	public void setPayment_list(List<Payment_listModel> payment_list)
	{
		this.payment_list = payment_list;
	}

	public int getIs_delivery()
	{
		return is_delivery;
	}

	public void setIs_delivery(int is_delivery)
	{
		this.is_delivery = is_delivery;
	}

	public int getShow_payment()
	{
		return show_payment;
	}

	public void setShow_payment(int show_payment)
	{
		this.show_payment = show_payment;
	}

	public int getHas_account()
	{
		return has_account;
	}

	public void setHas_account(int has_account)
	{
		this.has_account = has_account;
	}

	public int getHas_ecv()
	{
		return has_ecv;
	}

	public void setHas_ecv(int has_ecv)
	{
		this.has_ecv = has_ecv;
	}

	public Cart_count_buy_totalModel getCalculateModel()
	{
		return calculateModel;
	}

	public void setCalculateModel(Cart_count_buy_totalModel model)
	{
		this.calculateModel = model;
		if (model != null)
		{
			updateDeliveryFee(model);
		}
	}


	/**
	 * 更新运费
	 *
	 * @param model
	 */
	private void updateDeliveryFee(Cart_count_buy_totalModel model)
	{
		if (!SDCollectionUtil.isEmpty(listCartGroupsGoods)) // 商品数据不为空
		{
			if (model.getIs_pick() == 1)// 上门自提
			{
				for (CartGroupGoodsModel groupGoodsModel : listCartGroupsGoods)
				{
					groupGoodsModel.setDeliveryFeeInfo(null);
				}
			} else
			{
				Map<String, String> mapFee = model.getDelivery_fee_supplier();
				Delivery_infoModel delivery_infoModel = model.getDelivery_info();
				Map<String,Map<String,Object>> delivery_infos=model.getDelivery_infos();
				Map<String,Map<String,Object>> shop_total=model.getShop_total();
				if(delivery_infos!=null&&!delivery_infos.isEmpty()&&delivery_infoModel != null){
					for(Entry<String,Map<String,Object>> item : delivery_infos.entrySet()){
						String key = item.getKey();
						Map<String,Object> values = item.getValue();
						groupGoodsModel = cart_list_group.get(key);

						double fee = SDTypeParseUtil.getDouble(String.valueOf(values.get("supplier_delivery_fee")) );
						String name= (String) values.get("name");


						if(fee>=0&&name!=null){
							groupGoodsModel.setDeliveryFeeInfo("运费" + SDFormatUtil.formatMoneyChina(fee));
							groupGoodsModel.setName(name);
						}else {
							groupGoodsModel.setDeliveryFeeInfo("不支持" + delivery_infoModel.getName());
							groupGoodsModel.setName("");
						}
					}
					for(Entry<String,Map<String,Object>> items:shop_total.entrySet()){
						String key = items.getKey();
						Map<String,Object> values = items.getValue();
						//Double shop_total_price= (Double) values.get("shop_total_price");
						double shop_total_price= SDTypeParseUtil.getDouble(String.valueOf(values.get("shop_total_price")));
						int shop_total_score= (int) values.get("shop_total_score");
						if(shop_total_price>=0){
							groupGoodsModel = cart_list_group.get(key);
							groupGoodsModel.setShop_total_price(shop_total_price);
						}
					}
				}

				else
				{
					for (CartGroupGoodsModel groupGoodsModel : listCartGroupsGoods)
					{
						groupGoodsModel.setDeliveryFeeInfo(null);
					}
				}
			}
		}
	}

}
