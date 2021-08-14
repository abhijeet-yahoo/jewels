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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name ="vouretdt")
public class VouRetDt {

	@Id
	@GeneratedValue
	@Column(name ="DtId")
	private Integer id;
	
	@Column(name ="Barcode")
	private String barcode;
	
	@Column(name ="CompValue")
	private Double compValue = 0.0;
	
	@Column(name ="Destination")
	private String destination;
	
	@Column(name ="DiscAmount")
	private Double discAmount = 0.0;
	
	@Column(name ="DiscPerc")
	private Double dispPerc = 0.0;
	
	@Column(name  ="FinalPrice")
	private Double finalPrice=0.0;
	
	@Column(name ="GrossWt")
	private Double grossWt =0.0;
	
	@Column(name ="HandlingValue")
	private Double handlingValue =0.0;
	
	@Column(name ="LabValue")
	private Double labValue =0.0;
	
	@Column(name ="LossValue")
	private Double lossValue=0.0;
	
	@Column(name ="LossWt")
	private Double lossWt = 0.0;
	
	@Column(name ="Metalrate")
	private Double metalRate;
	
	@Column(name ="MetalValue")
	private Double metalValue = 0.0;
	
	@Column(name ="NetAmount")
	private Double netAmount = 0.0;
	
	@Column(name ="NetWt")
	private Double netWt =0.0;
	
	@Column(name ="OrdRef")
	private String ordRef ;
	
	@Column(name ="Other")
	private Double other = 0.0;
	
	@Column(name ="Pcs")
	private Double pcs = 0.0;
	
	@Column(name ="PurityConv")
	private Double purityConv = 0.0;
	
	@Column(name ="RLock")
	private Boolean rLock = false;
	
	@Column(name ="RefNo")
	private String refNo;
	
	@Column(name ="Remark")
	private String remark;
	
	@Column(name ="SetValue")
	private Double setValue= 0.0;
	
	@Column(name ="SrNo")
	private Integer srNo;
	
	@Column(name ="StoneValue")
	private Double stoneValue;
	
	@Column(name ="TagPrice")
	private Double tagPrice =0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ColorId")
	private Color color;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="StyleId")
	private Design design;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="MtId")
	private VouRetMt vouRetMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ProductSizeId")
	private ProductSize productSize;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="PurityId")
	private Purity purity;
	
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Double getCompValue() {
		return compValue;
	}

	public void setCompValue(Double compValue) {
		this.compValue = compValue;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
	}

	public Double getDispPerc() {
		return dispPerc;
	}

	public void setDispPerc(Double dispPerc) {
		this.dispPerc = dispPerc;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}

	public Double getHandlingValue() {
		return handlingValue;
	}

	public void setHandlingValue(Double handlingValue) {
		this.handlingValue = handlingValue;
	}

	public Double getLabValue() {
		return labValue;
	}

	public void setLabValue(Double labValue) {
		this.labValue = labValue;
	}

	public Double getLossValue() {
		return lossValue;
	}

	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}

	public Double getLossWt() {
		return lossWt;
	}

	public void setLossWt(Double lossWt) {
		this.lossWt = lossWt;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getNetWt() {
		return netWt;
	}

	public void setNetWt(Double netWt) {
		this.netWt = netWt;
	}

	public String getOrdRef() {
		return ordRef;
	}

	public void setOrdRef(String ordRef) {
		this.ordRef = ordRef;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getPcs() {
		return pcs;
	}

	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getSetValue() {
		return setValue;
	}

	public void setSetValue(Double setValue) {
		this.setValue = setValue;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
	}

	public Double getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(Double tagPrice) {
		this.tagPrice = tagPrice;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public VouRetMt getVouRetMt() {
		return vouRetMt;
	}

	public void setVouRetMt(VouRetMt vouRetMt) {
		this.vouRetMt = vouRetMt;
	}

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
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
	
	
	
	
	
	
	
	
	
}
