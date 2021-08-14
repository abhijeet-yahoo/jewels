package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "castcompdt")
public class CastingCompDt {

	@Id
	@GeneratedValue
	@Column(name = "CastCompDtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeId", referencedColumnName = "MtId")
	private CastingMt castingMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ComponentId")
	private Component component;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "MetalWt")
	private Double metalWt = 0.0;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "Qty")
	private Double qty = 0.0;

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

	public CastingMt getCastingMt() {
		return castingMt;
	}

	public void setCastingMt(CastingMt castingMt) {
		this.castingMt = castingMt;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Double getMetalWt() {
		return metalWt;
	}

	public void setMetalWt(Double metalWt) {
		this.metalWt = metalWt;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
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

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}
	
	

}
