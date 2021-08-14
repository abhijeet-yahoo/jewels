package com.jiyasoft.jewelplus.domain.manufacturing.masters.reports;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "designrpt")
public class DesignRpt {

	@Id
	@GeneratedValue
	@Column(name = "DesignRptId")
	private Integer id;

	@Column(name = "StyleId")
	private Integer styleId;

	@Column(name = "StyleNo")
	private String styleNo;

	@Column(name = "Version")
	private String version;

	@Column(name = "SCategId")
	private Integer sCategId;

	@Column(name = "CategId")
	private Integer categId;

	@Column(name = "SilverWt")
	private Double silverWt;

	@Column(name = "GrossWt")
	private Double grossWt;

	@Column(name = "WaxWt")
	private Double WaxWt;

	@Column(name = "ConceptId")
	private Integer conceptId;
	
	@Column(name = "CollectionId")
	private Integer collectionId;

	@Column(name = "SubConceptId")
	private Integer subConceptId;

	@Column(name = "defaultImage")
	private String defaultImage;

	@Column(name = "ScategNm")
	private String scategNm;

	@Column(name = "DesignGroupNm")
	private String designGroupNm;

	@Column(name = "CategNm")
	private String categNm;

	@Column(name = "ConceptNM")
	private String conceptNm;

	@Column(name = "CollectionNM")
	private String collectionNm;
	
	@Column(name = "SubConceptNM")
	private String subConceptNm;

	@Column(name = "Stone")
	private Integer stone;

	@Column(name = "Carat")
	private Double carat;

	@Column(name = "DesignDt")
	private Date designDt;

	@Column(name = "CreatedBy")
	private String createdBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getsCategId() {
		return sCategId;
	}

	public void setsCategId(Integer sCategId) {
		this.sCategId = sCategId;
	}

	public Integer getCategId() {
		return categId;
	}

	public void setCategId(Integer categId) {
		this.categId = categId;
	}

	public Double getSilverWt() {
		return silverWt;
	}

	public void setSilverWt(Double silverWt) {
		this.silverWt = silverWt;
	}

	public Double getWaxWt() {
		return WaxWt;
	}

	public void setWaxWt(Double waxWt) {
		WaxWt = waxWt;
	}

	public Integer getConceptId() {
		return conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}

	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public String getScategNm() {
		return scategNm;
	}

	public void setScategNm(String scategNm) {
		this.scategNm = scategNm;
	}

	public String getCategNm() {
		return categNm;
	}

	public void setCategNm(String categNm) {
		this.categNm = categNm;
	}

	public String getConceptNm() {
		return conceptNm;
	}

	public void setConceptNm(String conceptNm) {
		this.conceptNm = conceptNm;
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

	public String getDesignGroupNm() {
		return designGroupNm;
	}

	public void setDesignGroupNm(String designGroupNm) {
		this.designGroupNm = designGroupNm;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Integer getSubConceptId() {
		return subConceptId;
	}

	public void setSubConceptId(Integer subConceptId) {
		this.subConceptId = subConceptId;
	}

	public String getSubConceptNm() {
		return subConceptNm;
	}

	public void setSubConceptNm(String subConceptNm) {
		this.subConceptNm = subConceptNm;
	}

	public Date getDesignDt() {
		return designDt;
	}

	public void setDesignDt(Date designDt) {
		this.designDt = designDt;
	}

	public Double getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public String getCollectionNm() {
		return collectionNm;
	}

	public void setCollectionNm(String collectionNm) {
		this.collectionNm = collectionNm;
	}

}
