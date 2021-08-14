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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "meltingrecdt")
public class MeltingRecDt {

	@Id
	@GeneratedValue
	@Column(name = "RecDtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private MeltingMt meltingMt;

	@Column(name = "RecDate")
	private Date recDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationId", referencedColumnName = "DeptId")
	private Department deptLocation;


	@Column(name = "FreshMetalWt")
	private Double recFreshMetalWt = 0.0;

	/*@Column(name = "UsedMetalWt")
	private Double recUsedMetalWt = 0.0;*/

	@Column(name = "Stone")
	private Integer recStone = 0;

	@Column(name = "Carat")
	private double recCarat;

	@Column(name = "ExcpPureWt")
	private Double recExcpPureWt = 0.0;

	@Column(name = "NetWt")
	private Double recNetWt = 0.0;

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

	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MeltingMt getMeltingMt() {
		return meltingMt;
	}

	public void setMeltingMt(MeltingMt meltingMt) {
		this.meltingMt = meltingMt;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
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

	public Double getRecFreshMetalWt() {
		return recFreshMetalWt;
	}

	public void setRecFreshMetalWt(Double recFreshMetalWt) {
		this.recFreshMetalWt = recFreshMetalWt;
	}

	/*public Double getRecUsedMetalWt() {
		return recUsedMetalWt;
	}

	public void setRecUsedMetalWt(Double recUsedMetalWt) {
		this.recUsedMetalWt = recUsedMetalWt;
	}*/

	public Integer getRecStone() {
		return recStone;
	}

	public void setRecStone(Integer recStone) {
		this.recStone = recStone;
	}

	public double getRecCarat() {
		return recCarat;
	}

	public void setRecCarat(double recCarat) {
		this.recCarat = recCarat;
	}

	public Double getRecExcpPureWt() {
		return recExcpPureWt;
	}

	public void setRecExcpPureWt(Double recExcpPureWt) {
		this.recExcpPureWt = recExcpPureWt;
	}

	public Double getRecNetWt() {
		return recNetWt;
	}

	public void setRecNetWt(Double recNetWt) {
		this.recNetWt = recNetWt;
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

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Department getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(Department deptLocation) {
		this.deptLocation = deptLocation;
	}

	
}
