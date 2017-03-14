package com.lixinjia.mycanvas.model;


import com.lixinjia.mycanvas.utile.SDTypeParseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Cart_count_buy_totalModel extends BaseActModel
{

	private String pay_price; // 当前要付的余额，如为0表示不需要使用在线支付，则支付方式不让选中
	private List<FeeinfoModel> feeinfo;
	private Map<String, String> delivery_fee_supplier;
	private Delivery_infoModel delivery_info;
	private int is_pick; // 1:上门自提


	//新增
	private Map<String,Map<String,Object>> delivery_infos;
	private Map<String,Map<String,Object>> shop_total;


	public void setShop_total(Map<String, Map<String, Object>> shop_total) {
		this.shop_total = shop_total;
		if(shop_total==null){
		List<Shop_totalModel> list_shop=new ArrayList<>();
			for(Entry<String,Map<String,Object>> itemss:shop_total.entrySet()){
				String key=itemss.getKey();
				Map<String,Object> value=itemss.getValue();
				Shop_totalModel models=new Shop_totalModel();
				models.setShop_total_price((Double) value.get("shop_total_price"));
				models.setShop_total_score((Integer) value.get("shop_total_score"));
				list_shop.add(models);
			}
		}
	}

	public void setDelivery_infos(Map<String, Map<String, Object>> delivery_infos) {
		this.delivery_infos = delivery_infos;
		if(delivery_infos!=null){
			List<Delivery_infosModel> list=new ArrayList<>();
			for(Entry<String,Map<String,Object>> items:delivery_infos.entrySet()){
				String key = items.getKey();
				Map<String,Object> values = items.getValue();
				Delivery_infosModel model=new Delivery_infosModel();
				model.setId((String) values.get("id"));
				model.setName((String) values.get("name"));
				model.setSupplier_delivery_fee((Integer) values.get("supplier_delivery_fee"));
				list.add(model);
			}
		}
	}
	public Map<String, Map<String, Object>> getShop_total() {
		return shop_total;
	}

	public Map<String, Map<String, Object>> getDelivery_infos() {
		return delivery_infos;
	}
	public void setDelivery_fee_supplier(Map<String, String> delivery_fee_supplier)
	{
		this.delivery_fee_supplier = delivery_fee_supplier;
		if (delivery_fee_supplier != null)
		{
			List<SupplierDeliveryFeeModel> listModel = new ArrayList<SupplierDeliveryFeeModel>();
			for (Entry<String, String> item : delivery_fee_supplier.entrySet())
			{
				SupplierDeliveryFeeModel model = new SupplierDeliveryFeeModel();
				model.setId(SDTypeParseUtil.getInt(item.getKey()));
				model.setFee(SDTypeParseUtil.getInt(item.getValue(), -1));
				listModel.add(model);
			}
		}
	}

	public Map<String, String> getDelivery_fee_supplier()
	{
		return delivery_fee_supplier;
	}
	public String getPay_price()
	{
		return pay_price;
	}

	public void setPay_price(String pay_price)
	{
		this.pay_price = pay_price;
	}

	public List<FeeinfoModel> getFeeinfo()
	{
		return feeinfo;
	}

	public void setFeeinfo(List<FeeinfoModel> feeinfo)
	{
		this.feeinfo = feeinfo;
	}

	public int getIs_pick()
	{
		return is_pick;
	}

	public void setIs_pick(int is_pick)
	{
		this.is_pick = is_pick;
	}
	public Delivery_infoModel getDelivery_info()
	{
		return delivery_info;
	}

	public void setDelivery_info(Delivery_infoModel delivery_info)
	{
		this.delivery_info = delivery_info;
	}
}
