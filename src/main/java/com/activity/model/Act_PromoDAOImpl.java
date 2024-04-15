package com.activity.model;

import static com.activity.util.Constants.PAGE_MAX_RESULT;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.activity.util.HibernateUtil;

import com.activity.model.Act_PromoVO;








public class Act_PromoDAOImpl implements Act_PromoDAO{
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
		private SessionFactory factory;

		public Act_PromoDAOImpl() {
			factory = HibernateUtil.getSessionFactory();
		}
		
		// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
		// 以避免請求執行緒共用了同個 Session
		private Session getSession() {
			return factory.getCurrentSession();
		}

		@Override
		public int insert(Act_PromoVO act_PromoVO) {
			// 回傳給 service 剛新增成功的自增主鍵值
			return (Integer) getSession().save(act_PromoVO);
		}

		@Override
		public int update(Act_PromoVO act_PromoVO) {
			try {
				getSession().update(act_PromoVO);
				return 1;
			}catch (Exception e) {
				// TODO: handle exception
				return -1;
			}
				
		}

		@Override
		public int delete(Integer promotion_id) {
			Act_PromoVO act_PromoVO = getSession().get(Act_PromoVO.class, promotion_id);
			if(act_PromoVO != null) {
				getSession().delete(act_PromoVO);
				// 回傳給 service，1代表刪除成功
				return 1;
			}else {
				// 回傳給 service，-1代表刪除失敗
				return -1;
			}
		}

		@Override
		public Act_PromoVO getById(Integer promotion_id) {
			
			return getSession().get(Act_PromoVO.class, promotion_id);
		}

		@Override
		public List<Act_PromoVO> getAll() {
			
			return getSession().createQuery("from Act_PromoVO", Act_PromoVO.class).list();
//			NativeQuery<Act_PromoVO> query = getSession().createNativeQuery("select * from cactus_activity.activity_promotion",Act_PromoVO.class);
//			List<Act_PromoVO> listAll = query.list();
//			return(listAll);
			
		}

		@Override
		public List<Act_PromoVO> getByCompositeQuery(Map<String, String> map) {
			if(map.size() == 0)
			return getAll();
			
//			NativeQuery<Act_PromoVO> query = getSession().createNativeQuery
//					("select * from cactus_activity.activity_promotion\r\n"
//					+ "where (promotion_title like '%?%') and (promotion_started >= ?) and (promotion_end <= ?);",Act_PromoVO.class);
//			
//			List<Act_PromoVO> listResultAll = query.list();
//			return(listResultAll);
			
			
			CriteriaBuilder builder =getSession().getCriteriaBuilder(); //建立查詢條件
			CriteriaQuery<Act_PromoVO> criteria = builder.createQuery(Act_PromoVO.class); //建立查詢語法
			Root<Act_PromoVO> root = criteria.from(Act_PromoVO.class); //Root代表查詢的實體型別
			
			List<Predicate> predicates = new ArrayList<>();  //Predicate用於過濾數據
			
		
			for(Map.Entry<String, String>row : map.entrySet()){
				if("promotion_title".equals(row.getKey())) {
					predicates.add(builder.like(root.get("promotion_title"),"%" + row.getValue() + "%"));
				}
				if("promotion_started".equals(row.getKey())) {
					if(!map.containsKey("promotion_end")) {
						predicates.add(builder.greaterThanOrEqualTo(root.get("promotion_started"), Date.valueOf(row.getValue())));
					}
//					if(map.containsKey("promotion_end")) {
//						predicates.add(builder.greaterThanOrEqualTo(root.get("promotion_started"), Date.valueOf(row.getValue())));
//						predicates.add(builder.lessThanOrEqualTo(root.get("promotion_end"), Date.valueOf(row.getValue())));
//					}
				}
				if("promotion_end".equals(row.getKey())) {
					if(!map.containsKey("promotion_started")) {
						predicates.add(builder.lessThanOrEqualTo(root.get("promotion_end"), Date.valueOf(row.getValue())));
					}
					if(map.containsKey("promotion_started")) {
						predicates.add(builder.lessThanOrEqualTo(root.get("promotion_end"), Date.valueOf(row.getValue())));
						predicates.add(builder.greaterThanOrEqualTo(root.get("promotion_started"), Date.valueOf(row.getValue())));
					}
				}
			}
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			criteria.orderBy(builder.asc(root.get("promotion_id")));
			TypedQuery<Act_PromoVO> query = getSession().createQuery(criteria); //設定分頁並取得結果

			return query.getResultList();
			
		}

		@Override
		public List<Act_PromoVO> getAll(int currentPage) {
			int first =(currentPage - 1) * PAGE_MAX_RESULT;
			return getSession().createQuery("from Act_PromoVO", Act_PromoVO.class)
					.setFirstResult(first)
					.setMaxResults(PAGE_MAX_RESULT)
					.list();
			
			//first:索引值，代表第N筆，搜尋從第N筆開始
			//一頁最多有三筆
		}
		//分頁參考
		//Query<Emp> query2 = session.createQuery("from Emp", Emp.class);
		//query2.setFirstResult(0); 0:索引值，代表第一筆，搜尋從第一筆開始
		//query2.setMaxResults(3); //最多拿的筆數，適合做分頁
		//List<Emp> list3 = query2.list();
		//System.out.println(list3);

		@Override
		public long getTotal() {
			
		//單筆查詢使用uniqueResult() 回傳一個物件
			return getSession().createQuery("select count(*) from Act_PromoVO", Long.class).uniqueResult();
		}
		
}
		
		
		
		



