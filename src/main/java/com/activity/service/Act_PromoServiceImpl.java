package com.activity.service;

import static com.activity.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activity.model.Act_PromoDAO;
import com.activity.model.Act_PromoDAOImpl;
import com.activity.model.Act_PromoVO;
import com.activity.util.HibernateUtil;




//搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
public class Act_PromoServiceImpl implements Act_PromoService{
	
	// 一個 service 實體對應一個 dao 實體
		private Act_PromoDAO dao;
		
		public Act_PromoServiceImpl() {
			dao = new Act_PromoDAOImpl();
		}

		public Act_PromoVO addAct_PromoVO(
				String promotion_title,String promotion_content,
				Byte promotion_state,Double promotion_discount,
				Integer promotion_coupon,Date promotion_started,Date promotion_end) {

			Act_PromoVO act_PromoVO =new Act_PromoVO(); 
			
			act_PromoVO.setPromotion_title(promotion_title);
			act_PromoVO.setPromotion_content(promotion_content);
			act_PromoVO.setPromotion_state(promotion_state);
			act_PromoVO.setPromotion_discount(promotion_discount);
			act_PromoVO.setPromotion_coupon(promotion_coupon);
			act_PromoVO.setPromotion_started(promotion_started);
			act_PromoVO.setPromotion_end(promotion_end);
			
			dao.insert(act_PromoVO);
			
			return act_PromoVO;
		}

		public Act_PromoVO updateAct_PromoVO(Integer promotion_id,
				String promotion_title,String promotion_content,
				Byte promotion_state,Double promotion_discount,
				Integer promotion_coupon,Date promotion_started,Date promotion_end) {
			
			Act_PromoVO act_PromoVO =new Act_PromoVO(); 
			
			act_PromoVO.setPromotion_id(promotion_id);
			act_PromoVO.setPromotion_title(promotion_title);
			act_PromoVO.setPromotion_content(promotion_content);
			act_PromoVO.setPromotion_state(promotion_state);
			act_PromoVO.setPromotion_discount(promotion_discount);
			act_PromoVO.setPromotion_coupon(promotion_coupon);
			act_PromoVO.setPromotion_started(promotion_started);
			act_PromoVO.setPromotion_end(promotion_end);
			
			dao.update(act_PromoVO);
			
			return act_PromoVO;
		}

		@Override
		public void deleteAct_PromoVO(Integer promotion_id) {
			
			dao.delete(promotion_id);
			
		}

		@Override
		public Act_PromoVO getAct_PromoVO_ByPromotion_id(Integer promotion_id) {
			// TODO Auto-generated method stub
			return dao.getById(promotion_id);
		}

		@Override
		public List<Act_PromoVO> getAllAct_PromoVOs(int currentPage) {
			
			return dao.getAll(currentPage);
		}
	
		public List<Act_PromoVO> getAllAct_PromoVOs() {
			
			return dao.getAll();
		}

		

		@Override
		public List<Act_PromoVO> getAct_PromoVO_ByCompositeQuery(Map<String, String[]> map) {
			
			Map<String, String>query = new HashMap<>();
			// Map.Entry即代表一組key-value
			Set<Map.Entry<String, String[]>> entry = map.entrySet();
			
			for(Map.Entry<String, String[]> row : entry) {
				String key = row.getKey();
				// 因為請求參數裡包含了action，做個去除動作
				if ("action".equals(key)) {
					continue;
				}
				// 若是value為空即代表沒有查詢條件，做個去除動作
				String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
				if (value == null || value.isEmpty()) {
					continue;
				}
				query.put(key, value);
			}
			
			System.out.println(query);
			
			return dao.getByCompositeQuery(query);
		}
		
		@Override
		public int getPageTotal() {
			long total = dao.getTotal();
			
			// 計算Act_PromoVO數量每頁3筆的話總共有幾頁
			int pageQty =(int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
			return pageQty;
		}
		
		

}
