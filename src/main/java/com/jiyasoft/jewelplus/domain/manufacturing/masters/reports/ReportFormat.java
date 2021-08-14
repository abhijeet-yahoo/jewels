package com.jiyasoft.jewelplus.domain.manufacturing.masters.reports;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reportformat")
public class ReportFormat {
	
	@Id
	@GeneratedValue
	@Column(name = "ReportFormatId")
	private Integer id;
	
	@Column(name = "ReportNm")
	private String reportNm;
	
	@Column(name = "ProcedureNm")
	private String procedureNm;
	
	
	@Column(name = "FilterForm")
	private String filterForm;

	@Column(name = "ReportHeading")
	private String reportHeading;
	
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

	

	public String getReportNm() {
		return reportNm;
	}

	public void setReportNm(String reportNm) {
		this.reportNm = reportNm;
	}

	public String getFilterForm() {
		return filterForm;
	}

	public void setFilterForm(String filterForm) {
		this.filterForm = filterForm;
	}

	public String getReportHeading() {
		return reportHeading;
	}

	public void setReportHeading(String reportHeading) {
		this.reportHeading = reportHeading;
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

	public String getProcedureNm() {
		return procedureNm;
	}

	public void setProcedureNm(String procedureNm) {
		this.procedureNm = procedureNm;
	}

	
}
