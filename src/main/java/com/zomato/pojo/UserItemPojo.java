package com.zomato.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eatfit")
public class UserItemPojo {
	
	@Id
	@GeneratedValue
	private int itemid;
	private String item;
	private int price;
	private String quantity;
	private String typeof;
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTypeof() {
		return typeof;
	}
	public void setTypeof(String typeof) {
		this.typeof = typeof;
	}

}
