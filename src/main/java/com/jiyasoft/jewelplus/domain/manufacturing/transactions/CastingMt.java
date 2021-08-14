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
import javax.persistence.Table;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "castmt")
public class CastingMt {

	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer id;

	@Column(name = "Date")
	private Date cDate;

	@Column(name = "TreeNo")
	private String treeNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@Column(name = "BaseNo")
	private Integer baseNo;

	@Column(name = "BaseWt")
	private Double baseWt = 0.0;

	@Column(name = "WaxWt")
	private Double waxWt = 0.0;

	@Column(name = "ReqWt")
	private Double reqWt = 0.0;

	@Column(name = "FreshIssWt")
	private Double freshIssWt = 0.0;

	@Column(name = "UsedIssWt")
	private Double usedIssWt = 0.0;

	@Column(name = "TotIssWt")
	private Double totIssWt = 0.0;

	@Column(name = "AlloyId")
	private Integer alloyId = 0;

	@Column(name = "AlloyWt")
	private Double alloyWt = 0.0;

	@Column(name = "PureWt")
	private Double pureWt = 0.0;

	@Column(name = "StoneWt")
	private Double stoneWt = 0.0;

	@Column(name = "TreeWt")
	private Double treeWt = 0.0;

	@Column(name = "StubWt")
	private Double stubWt = 0.0;

	@Column(name = "PcsWt")
	private Double pcsWt = 0.0;

	@Column(name = "CastingLoss")
	private Double castingLoss = 0.0;

	@Column(name = "CuttingLoss")
	private Double cuttingLoss = 0.0;

	@Column(name = "WaxTreeWt")
	private Double waxTreeWt = 0.0;

	@Column(name = "FallenStnWt")
	private Double fallenStnWt = 0.0;

	@Column(name = "FallenStone")
	private Integer fallenStone = 0;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "UpdateIss")
	private Boolean updateIss;

	@Column(name = "UpdateRec")
	private Boolean updateRec;

	@Column(name = "ScrapWt")
	private Double scrapWt = 0.0;

	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

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
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	public String getDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(cDate);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public String getTreeNo() {
		return treeNo;
	}

	public void setTreeNo(String treeNo) {
		this.treeNo = treeNo;
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

	public Integer getBaseNo() {
		return baseNo;
	}

	public void setBaseNo(Integer baseNo) {
		this.baseNo = baseNo;
	}

	public Double getBaseWt() {
		return baseWt;
	}

	public void setBaseWt(Double baseWt) {
		this.baseWt = baseWt;
	}

	public Double getWaxWt() {
		return waxWt;
	}

	public void setWaxWt(Double waxWt) {
		this.waxWt = waxWt;
	}

	public Double getReqWt() {
		return reqWt;
	}

	public void setReqWt(Double reqWt) {
		this.reqWt = reqWt;
	}

	public Double getFreshIssWt() {
		return freshIssWt;
	}

	public void setFreshIssWt(Double freshIssWt) {
		this.freshIssWt = freshIssWt;
	}

	public Double getUsedIssWt() {
		return usedIssWt;
	}

	public void setUsedIssWt(Double usedIssWt) {
		this.usedIssWt = usedIssWt;
	}

	public Double getTotIssWt() {
		return totIssWt;
	}

	public void setTotIssWt(Double totIssWt) {
		this.totIssWt = totIssWt;
	}

	public Integer getAlloyId() {
		return alloyId;
	}

	public void setAlloyId(Integer alloyId) {
		this.alloyId = alloyId;
	}

	public Double getAlloyWt() {
		return alloyWt;
	}

	public void setAlloyWt(Double alloyWt) {
		this.alloyWt = alloyWt;
	}

	public Double getPureWt() {
		return pureWt;
	}

	public void setPureWt(Double pureWt) {
		this.pureWt = pureWt;
	}

	public Double getStoneWt() {
		return stoneWt;
	}

	public void setStoneWt(Double stoneWt) {
		this.stoneWt = stoneWt;
	}

	public Double getTreeWt() {
		return treeWt;
	}

	public void setTreeWt(Double treeWt) {
		this.treeWt = treeWt;
	}

	public Double getStubWt() {
		return stubWt;
	}

	public void setStubWt(Double stubWt) {
		this.stubWt = stubWt;
	}

	public Double getPcsWt() {
		return pcsWt;
	}

	public void setPcsWt(Double pcsWt) {
		this.pcsWt = pcsWt;
	}

	public Double getCastingLoss() {
		return castingLoss;
	}

	public void setCastingLoss(Double castingLoss) {
		this.castingLoss = castingLoss;
	}

	public Double getCuttingLoss() {
		return cuttingLoss;
	}

	public void setCuttingLoss(Double cuttingLoss) {
		this.cuttingLoss = cuttingLoss;
	}

	public Double getWaxTreeWt() {
		return waxTreeWt;
	}

	public void setWaxTreeWt(Double waxTreeWt) {
		this.waxTreeWt = waxTreeWt;
	}

	public Double getFallenStnWt() {
		return fallenStnWt;
	}

	public void setFallenStnWt(Double fallenStnWt) {
		this.fallenStnWt = fallenStnWt;
	}

	public Integer getFallenStone() {
		return fallenStone;
	}

	public void setFallenStone(Integer fallenStone) {
		this.fallenStone = fallenStone;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Boolean getUpdateIss() {
		return updateIss;
	}

	public void setUpdateIss(Boolean updateIss) {
		this.updateIss = updateIss;
	}

	public Boolean getUpdateRec() {
		return updateRec;
	}

	public void setUpdateRec(Boolean updateRec) {
		this.updateRec = updateRec;
	}

	public Double getScrapWt() {
		return scrapWt;
	}

	public void setScrapWt(Double scrapWt) {
		this.scrapWt = scrapWt;
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

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	

}
