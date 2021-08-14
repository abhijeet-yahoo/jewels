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
@Table(name = "meltingissdt")
public class MeltingIssDt {

	@Id
	@GeneratedValue
	@Column(name = "IssDtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private MeltingMt meltingMt;

	@Column(name = "IssDate")
	private Date issDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationId", referencedColumnName = "DeptId")
	private Department deptLocation;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "FreshMetalWt")
	private Double issFreshMetalWt = 0.0;

/*	@Column(name = "UsedMetalWt")
	private Double issUsedMetalWt = 0.0;*/

	@Column(name = "ExcpPureWt")
	private Double excpPureWt = 0.0;

	@Column(name = "NetWt")
	private Double netWt = 0.0;

	@Column(name = "Stone")
	private Integer stone = 0;

	@Column(name = "Carat")
	private Double carat=0.0;

	@Column(name = "Loss")
	private Double loss;
	
	@Column(name = "DtRemark")
	private String dtRemark;

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
	
	@Column(name = "RefMtId")
	private Integer refMtId;
	
	@Column(name = "RefTranMetalId")
	private Integer refTranMetalId;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;
	
	@Column(name = "TranType")
	private String tranType;
	
	@Column(name = "Barcode")
	private String barcode;

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

	public Date getIssDate() {
		return issDate;
	}

	public void setIssDate(Date issDate) {
		this.issDate = issDate;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Double getIssFreshMetalWt() {
		return issFreshMetalWt;
	}

	public void setIssFreshMetalWt(Double issFreshMetalWt) {
		this.issFreshMetalWt = issFreshMetalWt;
	}

	public Double getExcpPureWt() {
		return excpPureWt;
	}

	public void setExcpPureWt(Double excpPureWt) {
		this.excpPureWt = excpPureWt;
	}

	public Double getNetWt() {
		return netWt;
	}

	public void setNetWt(Double netWt) {
		this.netWt = netWt;
	}

	public Integer getStone() {
		return stone;
	}

	public void setStone(Integer stone) {
		this.stone = stone;
	}

	public Double getCarat() {
		return carat;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Double getLoss() {
		return loss;
	}

	public void setLoss(Double loss) {
		this.loss = loss;
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

	public Integer getRefMtId() {
		return refMtId;
	}

	public void setRefMtId(Integer refMtId) {
		this.refMtId = refMtId;
	}

	public Integer getRefTranMetalId() {
		return refTranMetalId;
	}

	public void setRefTranMetalId(Integer refTranMetalId) {
		this.refTranMetalId = refTranMetalId;
	}

	public String getDtRemark() {
		return dtRemark;
	}

	public void setDtRemark(String dtRemark) {
		this.dtRemark = dtRemark;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Department getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(Department deptLocation) {
		this.deptLocation = deptLocation;
	}
	


}
