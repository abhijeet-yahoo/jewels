package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "mtlpurcdt")
public class MetalPurchaseDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private MetalPurchaseMt metalPurchaseMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@Column(name = "InvWt")
	private Double invWt = 0.0;

	@Column(name = "Rate")
	private Double rate = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;


	@Column(name = "Balance")
	private Double balance = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;

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

	@Column(name = "Deactive")
	private Boolean deactive =false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	@Column(name = "Adjusted")
	private Boolean adjusted = false;
	
	
	@Column(name = "In999")
	private Boolean in999 =false;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public MetalPurchaseMt getMetalPurchaseMt() {
		return metalPurchaseMt;
	}


	public void setMetalPurchaseMt(MetalPurchaseMt metalPurchaseMt) {
		this.metalPurchaseMt = metalPurchaseMt;
	}


	public Purity getPurity() {
		return purity;
	}


	public void setPurity(Purity purity) {
		this.purity = purity;
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


	public Boolean getDeactive() {
		return deactive;
	}


	public void setDeactive(Boolean deactive) {
		this.deactive = deactive;
	}


	public Date getDeactiveDt() {
		return deactiveDt;
	}


	public void setDeactiveDt(Date deactiveDt) {
		this.deactiveDt = deactiveDt;
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


	public Metal getMetal() {
		return metal;
	}


	public void setMetal(Metal metal) {
		this.metal = metal;
	}
	
	
}
