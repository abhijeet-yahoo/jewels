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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "ktconvdt")
public class KtConversionDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private KtConversionMt ktConversionMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssPurityId")
	private Purity issPurity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssColorId")
	private Color issColor;

	@Column(name = "FreshMetalWt")
	private Double issFreshMetalWt = 0.0;

	@Column(name = "UsedMetalWt")
	private Double issUsedMetalWt = 0.0;

	@Column(name = "PureWt")
	private Double pureWt = 0.0;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

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

	public KtConversionMt getKtConversionMt() {
		return ktConversionMt;
	}

	public void setKtConversionMt(KtConversionMt ktConversionMt) {
		this.ktConversionMt = ktConversionMt;
	}

	public Purity getIssPurity() {
		return issPurity;
	}

	public void setIssPurity(Purity issPurity) {
		this.issPurity = issPurity;
	}

	public Color getIssColor() {
		return issColor;
	}

	public void setIssColor(Color issColor) {
		this.issColor = issColor;
	}

	public Double getIssFreshMetalWt() {
		return issFreshMetalWt;
	}

	public void setIssFreshMetalWt(Double issFreshMetalWt) {
		this.issFreshMetalWt = issFreshMetalWt;
	}

	public Double getIssUsedMetalWt() {
		return issUsedMetalWt;
	}

	public void setIssUsedMetalWt(Double issUsedMetalWt) {
		this.issUsedMetalWt = issUsedMetalWt;
	}

	public Double getPureWt() {
		return pureWt;
	}

	public void setPureWt(Double pureWt) {
		this.pureWt = pureWt;
	}

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
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

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}
	
	

}
