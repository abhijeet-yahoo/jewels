package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.text.SimpleDateFormat;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "ktconv")
public class KtConversionMt {

	@Id
	@GeneratedValue
	@Column(name = "KtConvId")
	private Integer id;

	@Column(name = "InvNo")
	private String invNo;

	@Column(name = "Date")
	private Date cDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReqPurityId", referencedColumnName = "PurityId")
	private Purity reqPurity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReqColorId", referencedColumnName = "ColorId")
	private Color reqColor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssPurityId", referencedColumnName = "PurityId")
	private Purity issPurity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssColorId", referencedColumnName = "ColorId")
	private Color issColor;

	@Column(name = "ReqMetalWt")
	private Double reqMetalWt = 0.0;

	private Double reqPure = 0.0;

	private Double reqAlloy = 0.0;

	@Column(name = "ReqPurityConv")
	private Double reqPurityConv = 0.0;

	@Column(name = "IssMetalWt")
	private Double issMetalWt = 0.0;

	@Column(name = "UIssMetalWt")
	private Double uIssMetalWt = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssAlloyId", referencedColumnName = "PurityId")
	private Purity issAlloy;

	@Column(name = "IssAlloyWt")
	private Double issAlloyWt = 0.0;

	@Column(name = "IssPureWt")
	private Double issPureWt = 0.0;

	@Column(name = "TotIssWt")
	private Double totIssWt = 0.0;

	@Column(name = "PureNetWt")
	private Double pureNetWt = 0.0;

	@Column(name = "AlloyNetWt")
	private Double alloyNetWt = 0.0;

	@Column(name = "RecWt")
	private Double recWt = 0.0;

	@Column(name = "Loss")
	private Double loss = 0.0;

	@Column(name = "ScrapWt")
	private Double scrapWt = 0.0;

	@Column(name = "FinYear")
	private String finYear;

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

	@Column(name = "SrNo", updatable = false)
	private Integer srNo;

	public String getInvDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(cDate);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public Metal getMetal() {
		return metal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public Purity getReqPurity() {
		return reqPurity;
	}

	public void setReqPurity(Purity reqPurity) {
		this.reqPurity = reqPurity;
	}

	public Color getReqColor() {
		return reqColor;
	}

	public void setReqColor(Color reqColor) {
		this.reqColor = reqColor;
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

	public Double getReqMetalWt() {
		return reqMetalWt;
	}

	public void setReqMetalWt(Double reqMetalWt) {
		this.reqMetalWt = reqMetalWt;
	}

	public Double getReqPure() {
		return reqPure;
	}

	public void setReqPure(Double reqPure) {
		this.reqPure = reqPure;
	}

	public Double getReqAlloy() {
		return reqAlloy;
	}

	public void setReqAlloy(Double reqAlloy) {
		this.reqAlloy = reqAlloy;
	}

	public Double getReqPurityConv() {
		return reqPurityConv;
	}

	public void setReqPurityConv(Double reqPurityConv) {
		this.reqPurityConv = reqPurityConv;
	}

	public Double getIssMetalWt() {
		return issMetalWt;
	}

	public void setIssMetalWt(Double issMetalWt) {
		this.issMetalWt = issMetalWt;
	}

	public Double getuIssMetalWt() {
		return uIssMetalWt;
	}

	public void setuIssMetalWt(Double uIssMetalWt) {
		this.uIssMetalWt = uIssMetalWt;
	}

	public Purity getIssAlloy() {
		return issAlloy;
	}

	public void setIssAlloy(Purity issAlloy) {
		this.issAlloy = issAlloy;
	}

	public Double getIssAlloyWt() {
		return issAlloyWt;
	}

	public void setIssAlloyWt(Double issAlloyWt) {
		this.issAlloyWt = issAlloyWt;
	}

	public Double getIssPureWt() {
		return issPureWt;
	}

	public void setIssPureWt(Double issPureWt) {
		this.issPureWt = issPureWt;
	}

	public Double getTotIssWt() {
		return totIssWt;
	}

	public void setTotIssWt(Double totIssWt) {
		this.totIssWt = totIssWt;
	}

	public Double getPureNetWt() {
		return pureNetWt;
	}

	public void setPureNetWt(Double pureNetWt) {
		this.pureNetWt = pureNetWt;
	}

	public Double getAlloyNetWt() {
		return alloyNetWt;
	}

	public void setAlloyNetWt(Double alloyNetWt) {
		this.alloyNetWt = alloyNetWt;
	}

	public Double getRecWt() {
		return recWt;
	}

	public void setRecWt(Double recWt) {
		this.recWt = recWt;
	}

	public Double getLoss() {
		return loss;
	}

	public void setLoss(Double loss) {
		this.loss = loss;
	}

	public Double getScrapWt() {
		return scrapWt;
	}

	public void setScrapWt(Double scrapWt) {
		this.scrapWt = scrapWt;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
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

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

}
