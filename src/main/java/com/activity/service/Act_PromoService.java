package com.activity.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.activity.model.Act_PromoVO;



public interface Act_PromoService {
	
	Act_PromoVO addAct_PromoVO(
			String promotion_title,String promotion_content,
			Byte promotion_state,Double promotion_discount,
			Integer promotion_coupon,Date promotion_started,Date promotion_end);
	
	Act_PromoVO updateAct_PromoVO(Integer promotion_id,
			String promotion_title,String promotion_content,
			Byte promotion_state,Double promotion_discount,
			Integer promotion_coupon,Date promotion_started,Date promotion_end);
	
	void deleteAct_PromoVO(Integer promotion_id);
	
	Act_PromoVO getAct_PromoVO_ByPromotion_id(Integer promotion_id);
	
	List<Act_PromoVO> getAllAct_PromoVOs();
	
	List<Act_PromoVO> getAllAct_PromoVOs(int currentPage);
	
	int getPageTotal();
	
	List<Act_PromoVO> getAct_PromoVO_ByCompositeQuery(Map<String, String[]> map);

}
