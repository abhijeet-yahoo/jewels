package com.jiyasoft.jewelplus.domain.marketing.transactions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name ="consigretrmmetaldt")
public class ConsigRetRmMetalDt {
	
	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private ConsigRetMt consigRetMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;

	@Column(name = "MetalWt")
	private Double metalWt = 0.0;

	@Column(name = "InvWt")
	private Double invWt = 0.0;

	@Column(name = "Rate")
	private Double rate = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@Column(name = "Balance")
	private Double balance = 0.0;

	@Column(name = "PureWt")
	private Double pureWt = 0.0;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "Amount")
	private Double amount = 0.0;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;


	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

	@Column(name = "Adjusted")
	private Boolean adjusted = false;
	
	
	@Column(name = "In999")
	private Boolean in999 =false;
	
	@Column(name = "RefTranDtId")
	private Integer RefTranDtId = 0;
	
	@Column(name = "AdjWt")
	private Double adjWt = 0.0;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public ConsigRetMt getConsigRetMt() {
		return consigRetMt;
	}


	public void setConsigRetMt(ConsigRetMt consigRetMt) {
		this.consigRetMt = consigRetMt;
	}


	public Purity getPurity() {
		return purity;
	}


	public void setPurity(Purity purity) {
		this.purity = purity;
	}


	public Metal getMetal() {
		return metal;
	}


	public void setMetal(Metal metal) {
		this.metal = metal;
	}


	public Double getMetalWt() {
		return metalWt;
	}


	public void setMetalWt(Double metalWt) {
		this.metalWt = metalWt;
	}


	public Double getInvWt() {
		return invWt;
	}


	public void setInvWt(Double invWt) {
		this.invWt = invWt;
	}


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public Double getPureWt() {
		return pureWt;
	}


	public void setPureWt(Double pureWt) {
		this.pureWt = pureWt;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Double getPurityConv() {
		return purityConv;
	}


	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDt() {
		return createdDt;
	}


	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}


	public String getModiBy() {
		return modiBy;
	}


	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}


	public Date getModiDt() {
		return modiDt;
	}


	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}



	public Long getUniqueId() {
		return uniqueId;
	}


	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}


	public Boolean getAdjusted() {
		return adjusted;
	}


	public void setAdjusted(Boolean adjusted) {
		this.adjusted = adjusted;
	}


	public Boolean getIn999() {
		return in999;
	}


	public void setIn999(Boolean in999) {
		this.in999 = in999;
	}


	public Double getAdjWt() {
		return adjWt;
	}


	public void setAdjWt(Double adjWt) {
		this.adjWt = adjWt;
	}


	public Integer getRefTranDtId() {
		return RefTranDtId;
	}


	public void setRefTranDtId(Integer refTranDtId) {
		RefTranDtId = refTranDtId;
	}
	
	

}
