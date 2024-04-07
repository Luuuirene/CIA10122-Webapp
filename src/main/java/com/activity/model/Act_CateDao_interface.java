package com.activity.model;

import java.util.*;


public interface Act_CateDao_interface {
	// 此介面定義對資料庫的相關存取抽象方法
	
	public void insert(Act_CateVO act_CateVO);
	
    public void update(Act_CateVO act_CateVO);
    
    public void delete(Integer activity_category_id);
    
    public Act_CateVO findByPrimaryKey(Integer activity_category_id);
    
    public List<Act_CateVO> getAll();
    

}
