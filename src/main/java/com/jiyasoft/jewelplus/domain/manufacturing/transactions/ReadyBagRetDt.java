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

@Entity
@Table(name = "readybagretdt")
public class ReadyBagRetDt {

	@Id
	@GeneratedValue
	@Column(name ="DtId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private ReadyBagRetMt readyBagRetMt;


	@Column(name = "BagPcs")
	private Double bagPcs;

	@Column(name = "Stone")
	private Integer stone = 0;

	@Column(name = "Carat")
	private Double carat = 0.0;
	
	@Column(name ="RefReadyBagId")
	private Integer refReadyBagId;
	
	@Column(name = "PurityNm")
	private String purityNm;
	
	@Column(name = "ColorNm")
	private String colorNm;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

		
	@Column(name = "PendApprovalFlg")
	private Boolean pendApprovalFlg = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Double getBagPcs() {
		return bagPcs;
	}

	public void setBagPcs(Double bagPcs) {
		this.bagPcs = bagPcs;
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

	public Date getModiDate() {
		return modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	
	

	public Integer getRefReadyBagId() {
		return refReadyBagId;
	}

	public void setRefReadyBagId(Integer refReadyBagId) {
		this.refReadyBagId = refReadyBagId;
	}

	public String getPurityNm() {
		return purityNm;
	}

	public void setPurityNm(String purityNm) {
		this.purityNm = purityNm;
	}

	public String getColorNm() {
		return colorNm;
	}

	public void setColorNm(String colorNm) {
		this.colorNm = colorNm;
	}

	public ReadyBagRetMt getReadyBagRetMt() {
		return readyBagRetMt;
	}

	public void setReadyBagRetMt(ReadyBagRetMt readyBagRetMt) {
		this.readyBagRetMt = readyBagRetMt;
	}

	public Boolean getPendApprovalFlg() {
		return pendApprovalFlg;
	}

	public void setPendApprovalFlg(Boolean pendApprovalFlg) {
		this.pendApprovalFlg = pendApprovalFlg;
	}
	
	
	
	
	
}
