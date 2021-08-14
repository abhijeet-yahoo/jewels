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
@Table(name = "stoneadj")
public class StoneAdjustment {
	
	@Id
	@GeneratedValue
	@Column(name = "StnAdjId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StnRecDtId")
	private StoneInwardDt stnInwardDt;
	
	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;
		
	@Column(name = "EntryType")
	private String entryType;
	
	@Column(name = "Stone")
	private Integer stone = 0;
	
	@Column(name = "Carat")
	private Double carat = 0.0;
	
	@Column(name = "BalStone")
	private Integer balStone = 0;
	
	@Column(name = "BalCarat")
	private Double BalCarat = 0.0;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StoneInwardDt getStnInwardDt() {
		return stnInwardDt;
	}

	public void setStnInwardDt(StoneInwardDt stnInwardDt) {
		this.stnInwardDt = stnInwardDt;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
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

	public Integer getBalStone() {
		return balStone;
	}

	public void setBalStone(Integer balStone) {
		this.balStone = balStone;
	}

	public Double getBalCarat() {
		return BalCarat;
	}

	public void setBalCarat(Double balCarat) {
		BalCarat = balCarat;
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
	
	
	
	
	

}
