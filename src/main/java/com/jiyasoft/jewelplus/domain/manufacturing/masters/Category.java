package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categmast")
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "CategId")
	private Integer id;

	@Size(min = 3, message = "Category Name must be atleast 3 characters")
	@Column(name = "CategNm")
	private String name;

	@Column(name = "CategCode")
	private String categCode;

	@OneToMany(mappedBy = "category", cascade = CascadeType.DETACH)
	private List<SubCategory> subCategory;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.DETACH)
	private List<ProductSize> productSize;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "LabourRate")
	private Double labourRate = 0.0;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
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
	 * @return the categCode
	 */
	public String getCategCode() {
		return categCode;
	}

	/**
	 * @param categCode
	 *            the categCode to set
	 */
	public void setCategCode(String categCode) {
		this.categCode = categCode;
	}

	/**
	 * @return the subCategory
	 */
	public List<SubCategory> getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory
	 *            the subCategory to set
	 */
	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}

	public List<ProductSize> getProductSize() {
		return productSize;
	}

	public void setProductSize(List<ProductSize> productSize) {
		this.productSize = productSize;
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

	public Double getLabourRate() {
		return labourRate;
	}

	public void setLabourRate(Double labourRate) {
		this.labourRate = labourRate;
	}
	
	

}
