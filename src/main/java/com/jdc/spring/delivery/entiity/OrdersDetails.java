package com.jdc.spring.delivery.entiity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrdersDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdersDetailsPK id;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Orders owner;

	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Item item;
	private int quentity;
	private int unitPrice;
	
	public OrdersDetails() {
		id = new OrdersDetailsPK();
	}

	public OrdersDetailsPK getId() {
		return id;
	}

	public void setId(OrdersDetailsPK id) {
		this.id = id;
	}

	public Orders getOwner() {
		return owner;
	}

	public void setOwner(Orders owner) {
		this.owner = owner;
		this.id.setOrderId(owner.getId());
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		this.id.setItemId(item.getId());
	}

	public int getQuentity() {
		return quentity;
	}

	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getTotal() {
		return unitPrice * quentity;
	}
	
	public String getCategory() {
		return null == item ? "" : item.getCategory().getName();
	}
}
