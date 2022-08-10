package com.jdc.spring.delivery.pvo;

import com.jdc.spring.delivery.entiity.Orders;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class InvoiceListVO {

	private long id;
	private String customer;
	private String phone;
	private LocalDateTime invoiceTime;
	private LocalDate deilvaryDate;
	private LocalTime timeFrom;
	private LocalTime timeTo;
	private Orders.Status status;
	private int subTotal;
	private int tax;
	private int total;
	
	public InvoiceListVO() {
	}

	public InvoiceListVO(Orders invoice) {
		super();
		this.id = invoice.getId();
		this.customer = invoice.getCustomer().getName();
		this.phone = invoice.getPhone();
		this.invoiceTime = invoice.getOrderTime();
		this.deilvaryDate = invoice.getDesireDate();
		this.timeFrom = invoice.getTimeFrom();
		this.timeTo = invoice.getTimeTo();
		this.status = invoice.getStatus();
		Map<String, Integer> summary = invoice.getSummary();
		this.subTotal = summary.get("subTotal");
		this.tax = summary.get("tax");
		this.total = summary.get("total");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getInvoiceTime() {
		return invoiceTime;
	}

	public void setInvoiceTime(LocalDateTime invoiceTime) {
		this.invoiceTime = invoiceTime;
	}

	public LocalDate getDeilvaryDate() {
		return deilvaryDate;
	}

	public void setDeilvaryDate(LocalDate deilvaryDate) {
		this.deilvaryDate = deilvaryDate;
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

	public Orders.Status getStatus() {
		return status;
	}

	public void setStatus(Orders.Status status) {
		this.status = status;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
