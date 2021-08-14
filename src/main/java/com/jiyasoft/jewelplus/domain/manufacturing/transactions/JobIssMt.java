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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HSNMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

@Entity
@Table(name = "jobissmt")
public class JobIssMt {

	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer id;

	@Column(name = "InvNo")
	private String invNo;
	
	@Column(name ="SrNo")
	private Integer srNo;

	@Column(name = "InvDate")
	private Date invDate;

	@Column(name = "SbNo")
	private String sbNo;

	@Column(name = "Bank")
	private String bank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BuyerId", referencedColumnName = "PartyId")
	private Party buyer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;

	@Column(name = "GrNo")
	private String grNo;

	@Column(name = "PayTerm")
	private String payTerm;

	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "SilverRate")
	private Double silverRate = 0.0;

	@Column(name = "AlloyRate")
	private Double alloyRate = 0.0;
		
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
	private Boolean expClose = false;

	@Column(name = "VaddMtlRate")
	private Double vAddMtlRate = 0.0;

	@Column(name = "VaddWastage")
	private Double vAddWastage = 0.0;

	@Column(name = "In999")
	private Boolean in999 =false;
	
	@Column(name = "VaddIn999")
	private Boolean vaddIn999 =false;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "InWords")
	private String inWords;
	
	@Column(name = "LossPercMt")
	private Double lossPercMt = 0.0;

	@Column(name = "AddPerc")
	private Double addPercent = 0.0;

	@Column(name = "TagPerc")
	private Double tagPercent = 0.0;

	@Column(name = "DispPerc")
	private Double dispPercent = 0.0;
	

	@Column(name = "DutyPerc")
	private Double dutyPerc = 0.0;
	
	@Column(name = "DiscountPerc")
	private Double discountPerc = 0.0;

	@Column(name = "InsuranceAmount")
	private Double insuranceAmount = 0.0;

	@Column(name = "TagColor")
	private String tagColor;

	@Column(name = "Description")
	private String description;

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

	@Column(name = "VAddGoldRate")
	private Double vAddGoldRate = 0.0;

	@Column(name = "VAddSilverRate")
	private Double vAddSilverRate = 0.0;
	
	@Column(name = "VAddAlloyRate")
	private Double vAddAlloyRate = 0.0;

	@Column(name = "VAdded")
	private Double vAdded = 0.0;
	
	@Column(name = "ExchangeRate")
	private Double exchangeRate = 0.0;
	
	@Column(name = "Disc")
	private Double disc = 0.0;
	
	@Column(name = "DirectParcel")
	private Boolean directParcel = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="hsnId")
	private HSNMast hsnMast;

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

	public Party getBuyer() {
		return buyer;
	}

	public void setBuyer(Party buyer) {
		this.buyer = buyer;
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

	public Double getAlloyRate() {
		return alloyRate;
	}

	public void setAlloyRate(Double alloyRate) {
		this.alloyRate = alloyRate;
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

	public Double getvAddWastage() {
		return vAddWastage;
	}

	public void setvAddWastage(Double vAddWastage) {
		this.vAddWastage = vAddWastage;
	}

	public Boolean getIn999() {
		return in999;
	}

	public void setIn999(Boolean in999) {
		this.in999 = in999;
	}

	public Boolean getVaddIn999() {
		return vaddIn999;
	}

	public void setVaddIn999(Boolean vaddIn999) {
		this.vaddIn999 = vaddIn999;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getInWords() {
		return inWords;
	}

	public void setInWords(String inWords) {
		this.inWords = inWords;
	}

	public Double getLossPercMt() {
		return lossPercMt;
	}

	public void setLossPercMt(Double lossPercMt) {
		this.lossPercMt = lossPercMt;
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

	public Double getDutyPerc() {
		return dutyPerc;
	}

	public void setDutyPerc(Double dutyPerc) {
		this.dutyPerc = dutyPerc;
	}

	public Double getDiscountPerc() {
		return discountPerc;
	}

	public void setDiscountPerc(Double discountPerc) {
		this.discountPerc = discountPerc;
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

	public Double getvAddGoldRate() {
		return vAddGoldRate;
	}

	public void setvAddGoldRate(Double vAddGoldRate) {
		this.vAddGoldRate = vAddGoldRate;
	}

	public Double getvAddSilverRate() {
		return vAddSilverRate;
	}

	public void setvAddSilverRate(Double vAddSilverRate) {
		this.vAddSilverRate = vAddSilverRate;
	}

	public Double getvAddAlloyRate() {
		return vAddAlloyRate;
	}

	public void setvAddAlloyRate(Double vAddAlloyRate) {
		this.vAddAlloyRate = vAddAlloyRate;
	}

	public Double getvAdded() {
		return vAdded;
	}

	public void setvAdded(Double vAdded) {
		this.vAdded = vAdded;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Double getDisc() {
		return disc;
	}

	public void setDisc(Double disc) {
		this.disc = disc;
	}

	public Boolean getDirectParcel() {
		return directParcel;
	}

	public void setDirectParcel(Boolean directParcel) {
		this.directParcel = directParcel;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public HSNMast getHsnMast() {
		return hsnMast;
	}

	public void setHsnMast(HSNMast hsnMast) {
		this.hsnMast = hsnMast;
	}

	
	
}
