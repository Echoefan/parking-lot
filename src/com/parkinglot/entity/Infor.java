package com.parkinglot.entity;
/*
 * 报表信息实体类
 * 实现Serializable序列化接口
 */
import java.io.Serializable;

public class Infor implements Serializable{
	private static final long serialVersionUID = 1L;
	private String Name;
	//商品名称
	private int Profit;
	//商品利润
	private int SellNumber;
	//销售数量
	private int TscNumber;
	//交易笔数
	private int Income;
	//收入（商品利润*销售数量）
	
	public Infor() {
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getProfit() {
		return Profit;
	}
	public void setProfit(int profit) {
		Profit = profit;
	}
	public int getSellNumber() {
		return SellNumber;
	}
	public void setSellNumber(int sellNumber) {
		SellNumber = sellNumber;
	}
	public int getTscNumber() {
		return TscNumber;
	}
	public void setTscNumber(int tscNumber) {
		TscNumber = tscNumber;
	}
	public int getIncome() {
		return Income;
	}
	public void setIncome(int income) {
		Income = income;
	}
	
}
