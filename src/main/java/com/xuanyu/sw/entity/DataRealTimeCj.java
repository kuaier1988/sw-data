package com.xuanyu.sw.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataRealTimeCj {
	private int zd_code;
	private String zd_name;
	private BigDecimal zd_shuiwei;
	private int zd_shuiwei_type;
	private Date zd_date;
	private String zd_des;
	public int getZd_code() {
		return zd_code;
	}
	public void setZd_code(int zd_code) {
		this.zd_code = zd_code;
	}
	public String getZd_name() {
		return zd_name;
	}
	public void setZd_name(String zd_name) {
		this.zd_name = zd_name;
	}
	public BigDecimal getZd_shuiwei() {
		return zd_shuiwei;
	}
	public void setZd_shuiwei(BigDecimal zd_shuiwei) {
		this.zd_shuiwei = zd_shuiwei;
	}
	public int getZd_shuiwei_type() {
		return zd_shuiwei_type;
	}
	public void setZd_shuiwei_type(int zd_shuiwei_type) {
		this.zd_shuiwei_type = zd_shuiwei_type;
	}
	@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getZd_date() {
		return zd_date;
	}
	public void setZd_date(Date zd_date) {
		this.zd_date = zd_date;
	}
	public String getZd_des() {
		return zd_des;
	}
	public void setZd_des(String zd_des) {
		this.zd_des = zd_des;
	}
}
