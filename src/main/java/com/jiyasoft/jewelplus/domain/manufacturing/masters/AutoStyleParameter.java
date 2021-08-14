package com.jiyasoft.jewelplus.domain.manufacturing.masters;

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
@Table(name = "autostyleparameter")
public class AutoStyleParameter {
	
	@Id
	@GeneratedValue
	@Column(name ="AutoStyleParamId")
	private Integer id;
	
	@Column(name ="LastNo")
	private Integer lastNo = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="CategId")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="CollectionId")
	private CollectionMaster collectionMaster;
	
	@Column(name = "CreatedBy" , updatable = false)
	private String createdBy;
	
	@Column(name = "CreatedDt" , updatable = false)
	private Date createdDate;
	
	@Column(name = "ModiBy" , insertable = false)
	private String modiBy;
	
	@Column(name = "ModiDt" , insertable = false)
	private Date modiDate;
	
	@Column(name = "Deactive")
	private boolean deactive =false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getLastNo() {
		return lastNo;
	}

	public void setLastNo(Integer lastNo) {
		this.lastNo = lastNo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CollectionMaster getCollectionMaster() {
		return collectionMaster;
	}

	public void setCollectionMaster(CollectionMaster collectionMaster) {
		this.collectionMaster = collectionMaster;
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

	public boolean isDeactive() {
		return deactive;
	}

	public void setDeactive(boolean deactive) {
		this.deactive = deactive;
	}

	public Date getDeactiveDt() {
		return deactiveDt;
	}

	public void setDeactiveDt(Date deactiveDt) {
		this.deactiveDt = deactiveDt;
	}

	
	
}
