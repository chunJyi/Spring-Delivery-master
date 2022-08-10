package com.jdc.spring.delivery.entiity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "ORDER_HISTORY")
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Status {
		Order, Cancel, Finish
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime orderTime;
	private LocalDate desireDate;
	private LocalTime timeFrom;
	private LocalTime timeTo;

	@ManyToOne
	private Account customer;
	private Address address;
	private String phone;
	private Status status;

	@OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private List<OrdersDetails> orders;

	public Orders() {
		status = Status.Order;
		orders = new ArrayList<OrdersDetails>();
	}
	
	public void addItem(Item item) {
		
		OrdersDetails od = orders.stream()
			.filter(a -> a.getItem().getId() == item.getId())
			.findAny().orElse(null);
		
		if(null == od) {
			od = new OrdersDetails();
			od.setItem(item);
			od.setOwner(this);
			od.setUnitPrice(item.getPrice());
			orders.add(od);
		}
		
		od.setQuentity(od.getQuentity() + 1);
		
	}
	
	public Map<String, Integer> getSummary() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer subTotal = orders.stream().mapToInt(a -> a.getQuentity() * a.getUnitPrice()).sum();
		map.put("subTotal", subTotal);
		map.put("tax", subTotal / 100 * 5);
		map.put("total", subTotal + map.get("tax"));
		
		return map;
	}
	
	public List<OrdersDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrdersDetails> orders) {
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public LocalDate getDesireDate() {
		return desireDate;
	}

	public void setDesireDate(LocalDate desireDate) {
		this.desireDate = desireDate;
	}

	public LocalTime getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(LocalTime timeFrom) {
		this.timeFrom = timeFrom;
	}

	public LocalTime getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(LocalTime timeTo) {
		this.timeTo = timeTo;
	}

	public Account getCustomer() {
		return customer;
	}

	public void setCustomer(Account customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@PostPersist
	public void postPersist() {
		orders.forEach(od -> od.getId().setOrderId(id));
	}
	
	public String getOrderTimeStr() {
		return null  == orderTime ? "" : orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

}
