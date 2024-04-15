package com.activity.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.result.Output;

import com.activity.model.Act_PromoVO;
import com.activity.service.Act_PromoService;
import com.activity.service.Act_PromoServiceImpl;




@WebServlet("/act/act_promo.do")
public class Act_PromoServlet extends HttpServlet{
	// 一個 servlet 實體對應一個 service 實體
	private Act_PromoService act_PromoService;

	@Override
	public void init() throws ServletException {
		
		act_PromoService = new Act_PromoServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
			case "getAll":
				forwardPath = getAllAct_PromoVOs(req, res);
				break;
			case "compositeQuery":
				forwardPath = getAct_PromoVO_ByCompositeQuery(req, res);
				break;
			case "insert":
				forwardPath = insertAct_PromoVO(res,req);
				break;
			case "getOne_For_Update":
				forwardPath = oneUpdateAct_PromoVO(res,req);
				break;
			case "update":
				forwardPath = updateAct_PromoVO(res,req);
			case "delete":
				forwardPath = deleteAct_PromoVO(req,res);
				break;
			default:
				forwardPath = "/index.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
//刪除	

	private String deleteAct_PromoVO(HttpServletRequest req, HttpServletResponse res) {
		//儲存錯誤訊息，並顯示於畫面提醒使用者
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

						
						
	/***************************接收請求並做錯誤判斷**********************/
						
		Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id"));
		
	/***************************開始刪除資料**********************/

		act_PromoService.deleteAct_PromoVO(promotion_id);;
				
	/***************************刪除完成，轉交jsp**********************/
		
		
		return "/act_Promo/updateAct_Promos.jsp";
	}
		
	
	
//修改	
	
	private String oneUpdateAct_PromoVO(HttpServletResponse res, HttpServletRequest req) {
		//儲存錯誤訊息，並顯示於畫面提醒使用者
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

				
				
	/***************************接收請求並做錯誤判斷**********************/
				
		Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id"));
				
	/***************************輸入正確，做資料查詢**********************/

		Act_PromoVO act_PromoVO = act_PromoService.getAct_PromoVO_ByPromotion_id(promotion_id);
				
	/***************************查詢完成，轉交修改jsp**********************/
		
		req.setAttribute("act_PromoVO", act_PromoVO);
		return "/act_Promo/listAllAct_Promos.jsp";
	}


//修改

	private String updateAct_PromoVO(HttpServletResponse res, HttpServletRequest req) {

		//儲存錯誤訊息，並顯示於畫面提醒使用者
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		
		
		/***************************接收請求並做錯誤判斷**********************/
		
		Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id").trim());
		
		String promotion_title = req.getParameter("promotion_title");
		if(promotion_title == null || promotion_title.trim().length()== 0) {
			errorMsgs.add("活動促銷標題：不得為空！");
		}
		
		String promotion_content = req.getParameter("promotion_content");
		if(promotion_content == null || promotion_content.trim().length()== 0) {
			errorMsgs.add("活動促銷內容：不得為空！");
		}
		Byte promotion_state = Byte.valueOf(req.getParameter("promotion_state").trim());
		
		Double promotion_discount = Double.valueOf(req.getParameter("promotion_discount").trim()) ;
		
		Integer promotion_coupon = Integer.valueOf(req.getParameter("promotion_coupon").trim()) ;
		
		if((promotion_discount > 0.0) && (promotion_discount <= 1.0)) {
			promotion_coupon = 0;
		}else if (promotion_coupon > 0 ) {
			promotion_discount = 1.0 ;
		}else if((promotion_discount > 0.0)&&(promotion_discount < 1.0) && (promotion_coupon > 0)){ 
			errorMsgs.add("促銷折扣與折價只能擇一做調整喔!");
		}
		
		java.sql.Date promotion_started = null;
		try {
			promotion_started = java.sql.Date.valueOf(req.getParameter("promotion_started").trim());
		}catch(IllegalArgumentException ie){
			errorMsgs.add("促銷開始時間:不得為空!");
		}
		
		java.sql.Date promotion_end = null;
		try {
			promotion_end = java.sql.Date.valueOf(req.getParameter("promotion_end").trim());
		}catch(IllegalArgumentException ie){
			errorMsgs.add("促銷結束時間:不得為空!");
		}
		
		Act_PromoVO act_PromoVO =new Act_PromoVO(); 
		
		act_PromoVO.setPromotion_title(promotion_title);
		act_PromoVO.setPromotion_content(promotion_content);
		act_PromoVO.setPromotion_state(promotion_state);
		act_PromoVO.setPromotion_discount(promotion_discount);
		act_PromoVO.setPromotion_coupon(promotion_coupon);
		act_PromoVO.setPromotion_started(promotion_started);
		act_PromoVO.setPromotion_end(promotion_end);
		
		//若有錯誤訊息，則帶使用者回到頁面
		if(!errorMsgs.isEmpty()) {
			req.setAttribute("act_PromoVO", act_PromoVO); // 先前輸入的act_PromoVO物件也存入req
//			String failUrl = "addAct_Promos.jsp" ;
//			RequestDispatcher fail = req.getRequestDispatcher(failUrl);
//			fail.forward(req, res);
			return "/act_Promo/updateAct_Promos.jsp";
		}
		
		/***************************輸入正確，做資料查詢**********************/

		act_PromoVO = act_PromoService.updateAct_PromoVO(promotion_id, promotion_title, promotion_content, promotion_state, promotion_discount, promotion_coupon, promotion_started, promotion_end);
		
		/***************************查詢完成，轉交修改jsp**********************/
		req.setAttribute("act_PromoVO", act_PromoVO);
		
		return "/act_Promo/listAllAct_Promos.jsp";
	}
	
//新增
	private String insertAct_PromoVO(HttpServletResponse res, HttpServletRequest req) {
		// 儲存錯誤訊息，並顯示於畫面提醒使用者
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		/***************************接收請求並做錯誤判斷**********************/
		
		String promotion_title = req.getParameter("promotion_title");
		if(promotion_title == null || promotion_title.trim().length()== 0) {
			errorMsgs.add("活動促銷標題：不得為空！");
		}
		
		String promotion_content = req.getParameter("promotion_content");
		if(promotion_content == null || promotion_content.trim().length()== 0) {
			errorMsgs.add("活動促銷內容：不得為空！");
		}
		Byte promotion_state = Byte.valueOf(req.getParameter("promotion_state").trim());
		
		Double promotion_discount = Double.valueOf(req.getParameter("promotion_discount").trim()) ;
		
		Integer promotion_coupon = Integer.valueOf(req.getParameter("promotion_coupon").trim()) ;
		
		if((promotion_discount > 0.0) && (promotion_discount <= 1.0)) {
			promotion_coupon = 0;
		}else if (promotion_coupon > 0 ) {
			promotion_discount = 1.0 ;
		}else if((promotion_discount > 0.0)&&(promotion_discount < 1.0) && (promotion_coupon > 0)){ 
			errorMsgs.add("促銷折扣與折價只能擇一做調整喔!");
		}
		
		java.sql.Date promotion_started = null;
		try {
			promotion_started = java.sql.Date.valueOf(req.getParameter("promotion_started").trim());
		}catch(IllegalArgumentException ie){
			errorMsgs.add("促銷開始時間:不得為空!");
		}
		
		java.sql.Date promotion_end = null;
		try {
			promotion_end = java.sql.Date.valueOf(req.getParameter("promotion_end").trim());
		}catch(IllegalArgumentException ie){
			errorMsgs.add("促銷結束時間:不得為空!");
		}
		
		
		
		Act_PromoVO act_PromoVO =new Act_PromoVO(); 
		
		act_PromoVO.setPromotion_title(promotion_title);
		act_PromoVO.setPromotion_content(promotion_content);
		act_PromoVO.setPromotion_state(promotion_state);
		act_PromoVO.setPromotion_discount(promotion_discount);
		act_PromoVO.setPromotion_coupon(promotion_coupon);
		act_PromoVO.setPromotion_started(promotion_started);
		act_PromoVO.setPromotion_end(promotion_end);
		
		//若有錯誤訊息，則帶使用者回到頁面
		if(!errorMsgs.isEmpty()) {
			req.setAttribute("act_PromoVO", act_PromoVO); // 先前輸入的act_PromoVO物件也存入req
//			String failUrl = "addAct_Promos.jsp" ;
//			RequestDispatcher fail = req.getRequestDispatcher(failUrl);
//			fail.forward(req, res);
			return "/act_Promo/addAct_Promos.jsp"; 
		}
		
		/***************************輸入正確，做資料新增**********************/
		
		act_PromoVO = act_PromoService.addAct_PromoVO(promotion_title, promotion_content, promotion_state, promotion_discount, promotion_coupon, promotion_started, promotion_end);
		
		
		/***************************新增完成，轉交前台**********************/
		req.setAttribute("act_PromoVO", act_PromoVO);
		
		
		return "/act_Promo/listAllAct_Promos.jsp";
		
	}

	private String getAct_PromoVO_ByCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
		
		if (map != null) {
			List<Act_PromoVO> act_PromoList = act_PromoService.getAct_PromoVO_ByCompositeQuery(map);
			req.setAttribute("act_PromoList", act_PromoList);
		} else {
			return "/index.jsp";
		}
		return "/act_Promo/listCompositeQueryAct_Promos.jsp";
	}

	private String getAllAct_PromoVOs(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
		List<Act_PromoVO> act_PromoList = act_PromoService.getAllAct_PromoVOs(currentPage);

		if (req.getSession().getAttribute("act_Promo_PageQty") == null) {
			int act_Promo_PageQty = act_PromoService.getPageTotal();
			req.getSession().setAttribute("act_Promo_PageQty", act_Promo_PageQty);
		}
		
	
		
//		String page = req.getParameter("page");
//		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
//		
//		
//		List<Act_PromoVO> act_PromoList = act_PromoService.getAllAct_PromoVOs(currentPage);
//
//		if (req.getSession().getAttribute("act_Promo_PageQty") == null) {
//			int act_Promo_PageQty = act_PromoService.getPageTotal();
//			req.getSession().setAttribute("act_Promo_PageQty", act_Promo_PageQty);
//		}
		
		req.setAttribute("act_PromoList", act_PromoList );
		req.setAttribute("currentPage", currentPage);
		
		
//		List<Act_PromoVO> act_PromoList = act_PromoService.getAllAct_PromoVOs();
//
//		req.setAttribute("act_PromoList", act_PromoList );
		
		return "/act_Promo/listAllAct_Promos.jsp";

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	
	
	
}
