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
@Table(name = "quotdt")
public class QuotDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private QuotMt quotMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId")
	private Design design;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeId")
	private ProductSize productSize;

	@Column(name = "RefNo")
	private String refNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "GrossWt")
	private Double grossWt = 0.0;

	@Column(name = "NetWt")
	private Double netWt = 0.0;

	@Column(name = "perGmWt")
	private Double perGmWt = 0.0;

	@Column(name = "Pcs")
	private Double pcs = 0.0;

	@Column(name = "MetalValue")
	private Double metalValue = 0.0;

	@Column(name = "StoneValue")
	private Double stoneValue = 0.0;

	@Column(name = "CompValue")
	private Double compValue = 0.0;

	@Column(name = "LabValue")
	private Double labValue = 0.0;

	@Column(name = "HdlgValue")
	private Double hdlgValue = 0.0;

	@Column(name = "LossValue")
	private Double lossValue = 0.0;

	@Column(name = "SetValue")
	private Double setValue = 0.0;

	@Column(name = "Fob")
	private Double fob = 0.0;
	
	@Column(name = "PerPcFinalPrice")
	private Double perPcFinalPrice = 0.0;

	@Column(name = "FinalPrice")
	private Double finalPrice = 0.0;

	@Column(name = "Rlock")
	private Boolean rLock = false;

	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "LossWt")
	private Double lossWt = 0.0;

	@Column(name = "Other")
	private Double other = 0.0;
	
	@Column(name = "DiscPerc")
	private Double discPercent = 0.0;
	
	@Column(name = "DiscAmount")
	private Double discAmount = 0.0;
	
	@Column(name = "NetAmount")
	private Double netAmount = 0.0;

	@Column(name = "DesignRemark")
	private String designRemark;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "Destination")
	private String destination;

	@Column(name = "StampInst")
	private String stampInst;
		
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

	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;
	
	@Column(name = "Barcode")
	private String barcode;

	
	@Column(name="AdjustedQty")
	private Double adjustedQty = 0.0;
	
	
	@Column(name = "LossPercDt")
	private Double lossPercDt = 0.0;
	
	@Column(name = "KtDesc")
	private String ktDesc;
	
	@Column(name = "QltyDesc")
	private String qltyDesc;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuotMt getQuotMt() {
		return quotMt;
	}

	public void setQuotMt(QuotMt quotMt) {
		this.quotMt = quotMt;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	
	public Double getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}

	public Double getNetWt() {
		return netWt;
	}

	public void setNetWt(Double netWt) {
		this.netWt = netWt;
	}

	public Double getPerGmWt() {
		return perGmWt;
	}

	public void setPerGmWt(Double perGmWt) {
		this.perGmWt = perGmWt;
	}

	public Double getPcs() {
		return pcs;
	}

	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
	}

	public Double getCompValue() {
		return compValue;
	}

	public void setCompValue(Double compValue) {
		this.compValue = compValue;
	}

	public Double getLabValue() {
		return labValue;
	}

	public void setLabValue(Double labValue) {
		this.labValue = labValue;
	}

	public Double getHdlgValue() {
		return hdlgValue;
	}

	public void setHdlgValue(Double hdlgValue) {
		this.hdlgValue = hdlgValue;
	}

	public Double getLossValue() {
		return lossValue;
	}

	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}

	public Double getSetValue() {
		return setValue;
	}

	public void setSetValue(Double setValue) {
		this.setValue = setValue;
	}

	public Double getFob() {
		return fob;
	}

	public void setFob(Double fob) {
		this.fob = fob;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Double getLossWt() {
		return lossWt;
	}

	public void setLossWt(Double lossWt) {
		this.lossWt = lossWt;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
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

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getDesignRemark() {
		return designRemark;
	}

	public void setDesignRemark(String designRemark) {
		this.designRemark = designRemark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStampInst() {
		return stampInst;
	}

	public void setStampInst(String stampInst) {
		this.stampInst = stampInst;
	}

	public Double getDiscPercent() {
		return discPercent;
	}

	public void setDiscPercent(Double discPercent) {
		this.discPercent = discPercent;
	}

	public Double getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Double getAdjustedQty() {
		return adjustedQty;
	}

	public void setAdjustedQty(Double adjustedQty) {
		this.adjustedQty = adjustedQty;
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

	public Double getLossPercDt() {
		return lossPercDt;
	}

	public void setLossPercDt(Double lossPercDt) {
		this.lossPercDt = lossPercDt;
	}

	public Double getPerPcFinalPrice() {
		return perPcFinalPrice;
	}

	public void setPerPcFinalPrice(Double perPcFinalPrice) {
		this.perPcFinalPrice = perPcFinalPrice;
	}

	public String getKtDesc() {
		return ktDesc;
	}

	public void setKtDesc(String ktDesc) {
		this.ktDesc = ktDesc;
	}

	public String getQltyDesc() {
		return qltyDesc;
	}

	public void setQltyDesc(String qltyDesc) {
		this.qltyDesc = qltyDesc;
	}
	
	

}
