package com.parkinglot.entity;
/*
 * ������Ϣʵ����
 * ʵ��Serializable���л��ӿ�
 */
import java.io.Serializable;

public class Infor implements Serializable{
	private static final long serialVersionUID = 1L;
	private String Name;
	//��Ʒ����
	private int Profit;
	//��Ʒ����
	private int SellNumber;
	//��������
	private int TscNumber;
	//���ױ���
	private int Income;
	//���루��Ʒ����*����������
	
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
