package com.lexian.manager.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.bean.CommoditySpec;


public interface CommodityDao {
	public List<Commodity> getCommodities(Map<String, Object> params);
	
	public int getCountCommodity();
	
	public Commodity getCommodityByName(String name);
	
	public Commodity getCommodityBycommodityNo(String commodityNo);
	
	public Commodity getCommodityById(int id);
	
	public void updateCommodity(Commodity commodity);
	
	public void addCommodity(Commodity commodity);
	
	public void addCommoditySpec(CommoditySpec commoditySpec);
	
	public void deleteCommoditySpec(String commodityNo);
	
	public void addCommodityPicture(@Param("commodityNo")String commodityNo,@Param("pictureUrl")String pictureUrl);

	public void deleteCommodityPicture(@Param("commodityNo")String commodityNo);
}
