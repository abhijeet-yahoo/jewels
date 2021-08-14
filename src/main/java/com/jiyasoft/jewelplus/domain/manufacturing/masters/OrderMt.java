package com.jiyasoft.jewelplus.domain.manufacturing.masters;

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

@Entity
@Table(name = "sordmt")
public class OrderMt {

	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrdTypeId")
	private OrderType orderType;

	@Column(name = "InvNo")
	private String invNo;

	@Column(name = "RefNo")
	private String refNo;

	@Column(name = "In999")
	private Boolean in999;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;

	@Column(name = "InvDate")
	private Date invDate;

	@Column(name = "ProductionDate")
	private Date productionDate;

	@Column(name = "StoneReqDate")
	private Date stoneReqDate;

	@Column(name = "DispatchDate")
	private Date dispatchDate;
	
	@Column(name = "ApproveDate")
	private Date approveDate;

	@Column(name = "CancelDate")
	private Date cancelDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "SalesPerson")
	private String salesPerson;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "OrderClose")
	private Boolean orderClose = false;

	@Column(name = "OrderCloseDt")
	private Date orderCloseDt;

	@Column(name = "FinYear")
	private String finYear;
	
	@Column(name = "Priority")
	private String priority;

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

	@Column(name = "Srno", updatable = false)
	private Long srno = 0l;
	
	@Column(name = "DiscPerc")
	private Double discPercent = 0.0;
	
	@Column(name = "Rlock")
	private Boolean rLock = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HallMarkId")
	private LookUpMast hallMark;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GradingId")
	private LookUpMast grading;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LaserMarkId")
	private LookUpMast laserMark;

	@Column(name = "PendApprovalFlg")
	private Boolean pendApprovalFlg = false;
	
	@Column(name = "ExchangeRate")
	private Double exchangeRate = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrdDivisionId")
	private LookUpMast ordDivision;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getStoneReqDate() {
		return stoneReqDate;
	}

	public void setStoneReqDate(Date stoneReqDate) {
		this.stoneReqDate = stoneReqDate;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getOrderClose() {
		return orderClose;
	}

	public void setOrderClose(Boolean orderClose) {
		this.orderClose = orderClose;
	}

	public Date getOrderCloseDt() {
		return orderCloseDt;
	}

	public void setOrderCloseDt(Date orderCloseDt) {
		this.orderCloseDt = orderCloseDt;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
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

	public Long getSrno() {
		return srno;
	}

	public void setSrno(Long srno) {
		this.srno = srno;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	
	public String getInvDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(invDate);
	}

	public Double getDiscPercent() {
		return discPercent;
	}

	public void setDiscPercent(Double discPercent) {
		this.discPercent = discPercent;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getProdDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(productionDate);
	}
	
	public String getDischpathDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(dispatchDate);
	}

	public Boolean getIn999() {
		return in999;
	}

	public void setIn999(Boolean in999) {
		this.in999 = in999;
	}

	public LookUpMast getHallMark() {
		return hallMark;
	}

	public void setHallMark(LookUpMast hallMark) {
		this.hallMark = hallMark;
	}

	public LookUpMast getGrading() {
		return grading;
	}

	public void setGrading(LookUpMast grading) {
		this.grading = grading;
	}

	public LookUpMast getLaserMark() {
		return laserMark;
	}

	public void setLaserMark(LookUpMast laserMark) {
		this.laserMark = laserMark;
	}

	public Boolean getPendApprovalFlg() {
		return pendApprovalFlg;
	}

	public void setPendApprovalFlg(Boolean pendApprovalFlg) {
		this.pendApprovalFlg = pendApprovalFlg;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public LookUpMast getOrdDivision() {
		return ordDivision;
	}

	public void setOrdDivision(LookUpMast ordDivision) {
		this.ordDivision = ordDivision;
	}


	
	

}
