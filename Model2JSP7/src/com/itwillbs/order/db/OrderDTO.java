package com.itwillbs.order.db;

import java.sql.Date;

public class OrderDTO {
	
	private int o_num;
	private String o_trade_num;
	private int o_g_num;
	private String o_g_name;
	private int o_g_amount;
	private String o_g_size;
	private String o_g_color;
	private String o_m_id;
	private String o_receive_name;
	private String o_receive_addr1;
	private String o_receive_addr2;
	private String o_receive_phone;
	private String o_receive_memo;
	private int o_sum_money;
	private String o_trade_type;
	private String o_trade_payer;
	private Date o_trade_date;
	private String o_trans_num;
	private Date o_date;
	private int o_status;
	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public String getO_trade_num() {
		return o_trade_num;
	}
	public void setO_trade_num(String o_trade_num) {
		this.o_trade_num = o_trade_num;
	}
	public int getO_g_num() {
		return o_g_num;
	}
	public void setO_g_num(int o_g_num) {
		this.o_g_num = o_g_num;
	}
	public String getO_g_name() {
		return o_g_name;
	}
	public void setO_g_name(String o_g_name) {
		this.o_g_name = o_g_name;
	}
	public int getO_g_amount() {
		return o_g_amount;
	}
	public void setO_g_amount(int o_g_amount) {
		this.o_g_amount = o_g_amount;
	}
	public String getO_g_size() {
		return o_g_size;
	}
	public void setO_g_size(String o_g_size) {
		this.o_g_size = o_g_size;
	}
	public String getO_g_color() {
		return o_g_color;
	}
	public void setO_g_color(String o_g_color) {
		this.o_g_color = o_g_color;
	}
	public String getO_m_id() {
		return o_m_id;
	}
	public void setO_m_id(String o_m_id) {
		this.o_m_id = o_m_id;
	}
	public String getO_receive_name() {
		return o_receive_name;
	}
	public void setO_receive_name(String o_receive_name) {
		this.o_receive_name = o_receive_name;
	}
	public String getO_receive_addr1() {
		return o_receive_addr1;
	}
	public void setO_receive_addr1(String o_receive_addr1) {
		this.o_receive_addr1 = o_receive_addr1;
	}
	public String getO_receive_addr2() {
		return o_receive_addr2;
	}
	public void setO_receive_addr2(String o_receive_addr2) {
		this.o_receive_addr2 = o_receive_addr2;
	}
	public String getO_receive_phone() {
		return o_receive_phone;
	}
	public void setO_receive_phone(String o_receive_phone) {
		this.o_receive_phone = o_receive_phone;
	}
	public String getO_receive_memo() {
		return o_receive_memo;
	}
	public void setO_receive_memo(String o_receive_memo) {
		this.o_receive_memo = o_receive_memo;
	}
	public int getO_sum_money() {
		return o_sum_money;
	}
	public void setO_sum_money(int o_sum_money) {
		this.o_sum_money = o_sum_money;
	}
	public String getO_trade_type() {
		return o_trade_type;
	}
	public void setO_trade_type(String o_trade_type) {
		this.o_trade_type = o_trade_type;
	}
	public String getO_trade_payer() {
		return o_trade_payer;
	}
	public void setO_trade_payer(String o_trade_payer) {
		this.o_trade_payer = o_trade_payer;
	}
	public Date getO_trade_date() {
		return o_trade_date;
	}
	public void setO_trade_date(Date o_trade_date) {
		this.o_trade_date = o_trade_date;
	}
	public String getO_trans_num() {
		return o_trans_num;
	}
	public void setO_trans_num(String o_trans_num) {
		this.o_trans_num = o_trans_num;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	public int getO_status() {
		return o_status;
	}
	public void setO_status(int o_status) {
		this.o_status = o_status;
	}
	@Override
	public String toString() {
		return "OrderDTO [o_num=" + o_num + ", o_trade_num=" + o_trade_num + ", o_g_num=" + o_g_num + ", o_g_name="
				+ o_g_name + ", o_g_amount=" + o_g_amount + ", o_g_size=" + o_g_size + ", o_g_color=" + o_g_color
				+ ", o_m_id=" + o_m_id + ", o_receive_name=" + o_receive_name + ", o_receive_addr1=" + o_receive_addr1
				+ ", o_receive_addr2=" + o_receive_addr2 + ", o_receive_phone=" + o_receive_phone + ", o_receive_memo="
				+ o_receive_memo + ", o_sum_money=" + o_sum_money + ", o_trade_type=" + o_trade_type
				+ ", o_trade_payer=" + o_trade_payer + ", o_trade_date=" + o_trade_date + ", o_trans_num=" + o_trans_num
				+ ", o_date=" + o_date + ", o_status=" + o_status + "]";
	}
	
	

}
