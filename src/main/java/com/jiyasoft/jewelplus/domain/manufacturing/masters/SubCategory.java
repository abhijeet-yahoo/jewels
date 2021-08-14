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
import javax.validation.constraints.Size;

@Entity
@Table(name = "subcategmast")
public class SubCategory {

	@Id
	@GeneratedValue
	@Column(name = "SCategId")
	private Integer id;

	@Size(min = 3, message = "Sub Category Name must be atleast 3 characters")
	@Column(name = "SCategNm")
	private String name;

	@Column(name = "SCategCode")
	private String sCategCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategId")
	private Category category;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sCategCode
	 */
	public String getsCategCode() {
		return sCategCode;
	}

	/**
	 * @param sCategCode
	 *            the sCategCode to set
	 */
	public void setsCategCode(String sCategCode) {
		this.sCategCode = sCategCode;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modiBy
	 */
	public String getModiBy() {
		return modiBy;
	}

	/**
	 * @param modiBy
	 *            the modiBy to set
	 */
	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	/**
	 * @return the modiDt
	 */
	public Date getModiDt() {
		return modiDt;
	}

	/**
	 * @param modiDt
	 *            the modiDt to set
	 */
	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}

	/**
	 * @return the deactive
	 */
	public Boolean getDeactive() {
		return deactive;
	}

	/**
	 * @param deactive
	 *            the deactive to set
	 */
	public void setDeactive(Boolean deactive) {
		this.deactive = deactive;
	}

	/**
	 * @return the deactiveDt
	 */
	public Date getDeactiveDt() {
		return deactiveDt;
	}

	/**
	 * @param deactiveDt
	 *            the deactiveDt to set
	 */
	public void setDeactiveDt(Date deactiveDt) {
		this.deactiveDt = deactiveDt;
	}
}