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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "quotmt")
public class QuotMt {

	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer id;

	@Column(name = "InvNo")
	private String invNo;

	@Column(name = "InvDate")
	private Date invDate;

	@Column(name = "SbNo")
	private String sbNo;

	@Column(name = "Bank")
	private String bank;
	
	@Column(name = "RefNo")
	private String refNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;

	@Column(name = "PartyNm")
	private String partyNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity =null;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color=null;

	@Column(name = "GrNo")
	private String grNo;

	@Column(name = "PayTerm")
	private String payTerm;

	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "SilverRate")
	private Double silverRate = 0.0;

	@Column(name = "ValueAdd")
	private Double valueAdd = 0.0;

	@Column(name = "PreCarriage")
	private String preCarriage;

	@Column(name = "FlightNo")
	private String flightNo;

	@Column(name = "PortOfDischarge")
	private String portOfDischarge;

	@Column(name = "PortOfLoading")
	private String portOfLoading;

	@Column(name = "CountryOfGoods")
	private String countryOfGoods;

	@Column(name = "FinalDestination")
	private String finalDestination;

	@Column(name = "CountryOfFinalDestination")
	private String countryOfFinalDestination;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "InsuredBy  ")
	private String insuredBy;

	@Column(name = "Cif")
	private String cif;

	@Column(name = "LoanGold")
	private Double loanGold = 0.0;

	@Column(name = "Freight")
	private Double frieght = 0.0;

	@Column(name = "MAWB")
	private String mawb;

	@Column(name = "HAWB")
	private String hawb;

	@Column(name = "ItcCode")
	private String itcCode;

	@Column(name = "FinYear")
	private String finYear;

	@Column(name = "RepairRemark")
	private String repairRemark;

	@Column(name = "AuthSign")
	private String authSign;

	@Column(name = "OtherRemark")
	private String otherRemark;

	@Column(name = "ExpClose")
	private Boolean expClose;
	
	@Column(name = "MasterFlg")
	private Boolean masterFlg=false;

	@Column(name = "VaddMtlRate")
	private Double vAddMtlRate;
	
	@Column(name = "In999")
	private Boolean in999;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "AddPerc")
	private Double addPercent = 0.0;

	@Column(name = "TagPerc")
	private Double tagPercent = 0.0;

	@Column(name = "DispPerc")
	private Double dispPercent = 0.0;

	@Column(name = "HandlingPerc")
	private Double handlingPerc = 0.0;

	@Column(name = "InsuranceAmount")
	private Double insuranceAmount = 0.0;
	
	@Column(name = "DiscPerc")
	private Double discPercent = 0.0;

	@Column(name = "TagColor")
	private String tagColor;

	@Column(name = "Description")
	private String description;

	@Column(name = "Rlock")
	private Boolean rLock = false;

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

	@Column(name = "SrNo", updatable = false)
	private Long srNo = 0l;
	
	@Column(name = "ExchangeRate")
	private Double exchangeRate = 0.0;

	public String getInvDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(invDate);
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

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public String getSbNo() {
		return sbNo;
	}

	public void setSbNo(String sbNo) {
		this.sbNo = sbNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public String getPartyNm() {
		return partyNm;
	}

	public void setPartyNm(String partyNm) {
		this.partyNm = partyNm;
	}

	public Metal getMetal() {
		return metal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public String getPayTerm() {
		return payTerm;
	}

	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Double getSilverRate() {
		return silverRate;
	}

	public void setSilverRate(Double silverRate) {
		this.silverRate = silverRate;
	}

	
	public Double getValueAdd() {
		return valueAdd;
	}

	public void setValueAdd(Double valueAdd) {
		this.valueAdd = valueAdd;
	}

	public String getPreCarriage() {
		return preCarriage;
	}

	public void setPreCarriage(String preCarriage) {
		this.preCarriage = preCarriage;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getPortOfDischarge() {
		return portOfDischarge;
	}

	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	public String getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public String getCountryOfGoods() {
		return countryOfGoods;
	}

	public void setCountryOfGoods(String countryOfGoods) {
		this.countryOfGoods = countryOfGoods;
	}

	public String getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
	}

	public String getCountryOfFinalDestination() {
		return countryOfFinalDestination;
	}

	public void setCountryOfFinalDestination(String countryOfFinalDestination) {
		this.countryOfFinalDestination = countryOfFinalDestination;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInsuredBy() {
		return insuredBy;
	}

	public void setInsuredBy(String insuredBy) {
		this.insuredBy = insuredBy;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Double getLoanGold() {
		return loanGold;
	}

	public void setLoanGold(Double loanGold) {
		this.loanGold = loanGold;
	}

	public Double getFrieght() {
		return frieght;
	}

	public void setFrieght(Double frieght) {
		this.frieght = frieght;
	}

	public String getMawb() {
		return mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}

	public String getHawb() {
		return hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getItcCode() {
		return itcCode;
	}

	public void setItcCode(String itcCode) {
		this.itcCode = itcCode;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getRepairRemark() {
		return repairRemark;
	}

	public void setRepairRemark(String repairRemark) {
		this.repairRemark = repairRemark;
	}

	public String getAuthSign() {
		return authSign;
	}

	public void setAuthSign(String authSign) {
		this.authSign = authSign;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}

	public Boolean getExpClose() {
		return expClose;
	}

	public void setExpClose(Boolean expClose) {
		this.expClose = expClose;
	}

	public Double getvAddMtlRate() {
		return vAddMtlRate;
	}

	public void setvAddMtlRate(Double vAddMtlRate) {
		this.vAddMtlRate = vAddMtlRate;
	}

	
	public Boolean getIn999() {
		return in999;
	}

	public void setIn999(Boolean in999) {
		this.in999 = in999;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getAddPercent() {
		return addPercent;
	}

	public void setAddPercent(Double addPercent) {
		this.addPercent = addPercent;
	}

	public Double getTagPercent() {
		return tagPercent;
	}

	public void setTagPercent(Double tagPercent) {
		this.tagPercent = tagPercent;
	}

	public Double getDispPercent() {
		return dispPercent;
	}

	public void setDispPercent(Double dispPercent) {
		this.dispPercent = dispPercent;
	}

	public Double getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(Double insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getTagColor() {
		return tagColor;
	}

	public void setTagColor(String tagColor) {
		this.tagColor = tagColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
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

	public Double getHandlingPerc() {
		return handlingPerc;
	}

	public void setHandlingPerc(Double handlingPerc) {
		this.handlingPerc = handlingPerc;
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

	public Long getSrNo() {
		return srNo;
	}

	public void setSrNo(Long srNo) {
		this.srNo = srNo;
	}

	public Double getDiscPercent() {
		return discPercent;
	}

	public void setDiscPercent(Double discPercent) {
		this.discPercent = discPercent;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Boolean getMasterFlg() {
		return masterFlg;
	}

	public void setMasterFlg(Boolean masterFlg) {
		this.masterFlg = masterFlg;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	

}
