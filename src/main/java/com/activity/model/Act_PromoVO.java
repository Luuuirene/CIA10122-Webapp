package com.activity.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="activity_promotion")
public class Act_PromoVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="promotion_id", updatable = false)
	private Integer promotion_id;
	
	@Column(name = "promotion_title")
	private String promotion_title;
	
	@Column(name = "promotion_content")
	private String promotion_content;
	
	@Column(name = "promotion_state")
	private Byte promotion_state;
	
	@Column(name = "promotion_discount")
	private Double promotion_discount;
	
	@Column(name = "promotion_coupon")
	private Integer promotion_coupon;
	
	@Column(name = "promotion_started")
	private Date promotion_started;
	
	@Column(name = "promotion_end")
	private Date promotion_end;

	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}

	public String getPromotion_title() {
		return promotion_title;
	}

	public void setPromotion_title(String promotion_title) {
		this.promotion_title = promotion_title;
	}

	public String getPromotion_content() {
		return promotion_content;
	}

	public void setPromotion_content(String promotion_content) {
		this.promotion_content = promotion_content;
	}

	public Byte getPromotion_state() {
		return promotion_state;
	}

	public void setPromotion_state(Byte promotion_state) {
		this.promotion_state = promotion_state;
	}

	public Double getPromotion_discount() {
		return promotion_discount;
	}

	public void setPromotion_discount(Double promotion_discount) {
		this.promotion_discount = promotion_discount;
	}

	public Integer getPromotion_coupon() {
		return promotion_coupon;
	}

	public void setPromotion_coupon(Integer promotion_coupon) {
		this.promotion_coupon = promotion_coupon;
	}

	public Date getPromotion_started() {
		return promotion_started;
	}

	public void setPromotion_started(Date promotion_started) {
		this.promotion_started = promotion_started;
	}

	public Date getPromotion_end() {
		return promotion_end;
	}

	public void setPromotion_end(Date promotion_end) {
		this.promotion_end = promotion_end;
	}

	@Override
	public String toString() {
		return "Act_PromoVO [promotion_id=" + promotion_id + ", promotion_title=" + promotion_title
				+ ", promotion_content=" + promotion_content + ", promotion_state=" + promotion_state
				+ ", promotion_discount=" + promotion_discount + ", promotion_coupon=" + promotion_coupon
				+ ", promotion_started=" + promotion_started + ", promotion_end=" + promotion_end + "]";
	}
	
	

}
