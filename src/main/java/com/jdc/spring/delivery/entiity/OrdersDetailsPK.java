package com.jdc.spring.delivery.entiity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrdersDetailsPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "owner_id")
	private Long orderId;
	@Column(name = "item_id")
	private Integer itemId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdersDetailsPK other = (OrdersDetailsPK) obj;
		if (itemId != other.itemId)
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
