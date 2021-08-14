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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "vaddcompinv")
public class VAddCompInv {

	@Id
	@GeneratedValue
	@Column(name = "VAddCompInv")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CostMtId")
	private CostingMt costingMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CompId")
	private Component component;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;
	
	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "CompWt")
	private Double compWt = 0.0;

	@Column(name = "CompPcs")
	private Double compPcs = 0.0;

	@Column(name = "AdjBal")
	private Double adjBal = 0.0;

	@Column(name = "Adjusted")
	private Boolean adjusted = false;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CostingMt getCostingMt() {
		return costingMt;
	}

	public void setCostingMt(CostingMt costingMt) {
		this.costingMt = costingMt;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}
	
	

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Boolean getDeactive() {
		return deactive;
	}

	public void setDeactive(Boolean deactive) {
		this.deactive = deactive;
	}

	public Double getCompWt() {
		return compWt;
	}

	public void setCompWt(Double compWt) {
		this.compWt = compWt;
	}

	public Double getCompPcs() {
		return compPcs;
	}

	public void setCompPcs(Double compPcs) {
		this.compPcs = compPcs;
	}

	public Double getAdjBal() {
		return adjBal;
	}

	public void setAdjBal(Double adjBal) {
		this.adjBal = adjBal;
	}

	public Boolean getAdjusted() {
		return adjusted;
	}

	public void setAdjusted(Boolean adjusted) {
		this.adjusted = adjusted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModiBy() {
		return modiBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	public Date getModiDate() {
		return modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	public Date getDeactiveDt() {
		return deactiveDt;
	}

	public void setDeactiveDt(Date deactiveDt) {
		this.deactiveDt = deactiveDt;
	}

}
