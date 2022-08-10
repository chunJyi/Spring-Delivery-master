package com.jdc.spring.delivery.entiity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_SETTING")
public class ApplicationSetting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ruleName;
	private double ruleValue;
	private boolean enable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public double getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(double ruleValue) {
		this.ruleValue = ruleValue;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
