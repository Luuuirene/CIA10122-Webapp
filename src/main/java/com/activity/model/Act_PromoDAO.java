package com.activity.model;

import java.util.List;
import java.util.Map;

import com.activity.model.Act_PromoVO;

public interface Act_PromoDAO {
	
	int insert(Act_PromoVO act_PromoVO);

	int update(Act_PromoVO act_PromoVO);
	
	int delete(Integer promotion_id);
	 
	Act_PromoVO getById(Integer promotion_id);
	
	List<Act_PromoVO> getAll();
	
	List<Act_PromoVO> getAll(int currentPage);
	
	List<Act_PromoVO> getByCompositeQuery(Map<String, String> map);
	
	long getTotal();


}
