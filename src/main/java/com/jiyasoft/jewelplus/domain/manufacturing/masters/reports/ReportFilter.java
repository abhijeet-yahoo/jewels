package com.jiyasoft.jewelplus.domain.manufacturing.masters.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class ReportFilter {

	@Id
	@GeneratedValue
	@Column(name = "ReportId")
	private Integer id;

	@Column(name = "ReportNm")
	private String name;

	@Column(name = "ProcedureNm")
	private String procedureNm;

	@Column(name = "FilterForm")
	private String filterForm;

	@Column(name = "Xml")
	private String xml;

	@Column(name = "FilterName")
	private String filterName;
	
	@Column(name = "CompulsoryFilter")
	private String compulsoryFilter;
	
	@Column(name = "MandatoryFilter")
	private String mandatoryFilter;
	
	@Column(name = "GroupList")
	private String groupList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcedureNm() {
		return procedureNm;
	}

	public void setProcedureNm(String procedureNm) {
		this.procedureNm = procedureNm;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getFilterForm() {
		return filterForm;
	}

	public void setFilterForm(String filterForm) {
		this.filterForm = filterForm;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}

	public String getCompulsoryFilter() {
		return compulsoryFilter;
	}

	public void setCompulsoryFilter(String compulsoryFilter) {
		this.compulsoryFilter = compulsoryFilter;
	}

	public String getMandatoryFilter() {
		return mandatoryFilter;
	}

	public void setMandatoryFilter(String mandatoryFilter) {
		this.mandatoryFilter = mandatoryFilter;
	}
	
	

}
