package com.jiyasoft.jewelplus.domain.manufacturing.masters;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmailConcept;

@Entity
@Table(name = "design")
public class Design {

	@Id
	@GeneratedValue
	@Column(name = "StyleId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DesignGroupId")
	private DesignGroup designGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategId")
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCategId")
	private SubCategory subCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CollectionId")
	private CollectionMaster collectionMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ConceptId")
	private Concept concept;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VendorId", referencedColumnName = "PartyId")
	private Party vendor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SubConceptId")
	private SubConcept subConcept;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeId")
	private ProductSize productSize;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PatternId")
	private PatternMast patternMast;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LookId")
	private LookMast lookMast;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Process")
	private LookUpMast process;

	@Column(name = "DesignNo")
	private String designNo;

	@Column(name = "OldStyleNo")
	private String oldStyleNo;

	@Column(name = "StyleNo")
	private String styleNo;

	@Column(name = "MainStyleNo")
	private String mainStyleNo;

	// this is for designer textbox
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DesignerId")
	private Employee designerEmployee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MerchandiserId")
	private Employee merchandiser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CadDesignerId")
	private Employee cadDesigner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CadQcId")
	private Employee cadQc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CamQcId")
	private Employee camQc;
	
	@Column(name = "CastedPcs")
	private Integer castedPcs  = 0;
	
	@Column(name = "ModelModiDt")
	private Date modelModiDt;

	@Column(name = "CustStyleNo")
	private String custStyleNo;
	
	@Column(name = "CamWt")
	private Double camWt = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SamplePurityId")
	private Purity purity;
	
	@Column(name = "SampleWt")
	private Double sampleWt = 0.0;
	
	@Column(name = "Version")
	private String version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ModelMakerId")
	private Employee modelMakerEmployee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizerId")
	private Employee sizerEmployee;

	@Column(name = "PsizeId")
	private Integer psizeId = 0;

	@Column(name = "WaxWt")
	private Double waxWt = 0.0;

	@Column(name = "SilverWt")
	private Double silverWt = 0.0;

	@Column(name = "Grms")
	private Double grms = 0.0;
	
	@Column(name = "FinishWt")
	private Double finishWt = 0.0;

	@Column(name = "Hold")
	private Boolean hold = false;

	@Column(name = "HoldRemark")
	private String holdRemark;

	@Column(name = "SilverModel")
	private Boolean silverModel = false;

	@Column(name = "RubberMould")
	private Boolean rubberMould = false;
	
	@Column(name = "MasterBal")
	private Boolean masterBal = false;

	@Column(name = "Cancel")
	private Boolean cancel = false;
	
	@Column(name = "ExclusiveClient")
	private Boolean exclusiveClient = false;

	@Column(name = "Imagecode")
	private String imageCode;

	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DesignDt")
	private Date designDt;

	@Column(name = "Remarks")
	private String remarks;

	@Column(name = "WaxTemp")
	private Double waxTemp = 0.0;

	@Column(name = "WaxPre")
	private Double waxPre = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EmailConceptId")
	private EmailConcept emailConcept;

	@Column(name = "Bbc")
	private Boolean bbc = false;

	@Column(name = "MustHave")
	private Boolean mustHave = false;

	@Column(name = "Msi")
	private Boolean msi = false;

	@Column(name = "Image1")
	private String image1;

	@Column(name = "Image2")
	private String image2;

	@Column(name = "Image3")
	private String image3;

	@Column(name = "defaultImage")
	private String defaultImage;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "CreatedDt", updatable = false)
	private Date createDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	@Column(name = "UniqueId")
	private Long uniqueId;

	@Column(name = "RefNo")
	private String refNo;

	@Column(name = "DefImgChk")
	private Integer defImgChk = 0;

	@Column(name = "BarCode")
	private String barCode;

	@Column(name = "TotStone")
	private Integer totStone = 0;

	@Column(name = "TotCarat")
	private Double totCarat = 0.0;
	
	@Column(name="RptWt")
	private Double rptWt = 0.0;
	
	@Column(name = "LiquidMould")
	private Boolean liquidMould = false;
	
	
	@Column(name = "GrossWt")
	private Double grossWt = 0.0;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MarketTypeId")
	private MarketTypeMast marketTypeMast;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductTypeId")
	private ProductTypeMast productTypeMast;
	
	@Column(name = "VendorStyle")
	private String vendorStyle;
	
	@Column(name = "ParentStyle")
	private String parentStyle;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DesignGroup getDesignGroup() {
		return designGroup;
	}

	public void setDesignGroup(DesignGroup designGroup) {
		this.designGroup = designGroup;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public SubConcept getSubConcept() {
		return subConcept;
	}

	public void setSubConcept(SubConcept subConcept) {
		this.subConcept = subConcept;
	}


	public String getDesignNo() {
		return designNo;
	}

	public void setDesignNo(String designNo) {
		this.designNo = designNo;
	}

	public String getOldStyleNo() {
		return oldStyleNo;
	}

	public void setOldStyleNo(String oldStyleNo) {
		this.oldStyleNo = oldStyleNo;
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

	public Integer getPsizeId() {
		return psizeId;
	}

	public Employee getDesignerEmployee() {
		return designerEmployee;
	}

	public void setDesignerEmployee(Employee designerEmployee) {
		this.designerEmployee = designerEmployee;
	}

	public Employee getModelMakerEmployee() {
		return modelMakerEmployee;
	}

	public void setModelMakerEmployee(Employee modelMakerEmployee) {
		this.modelMakerEmployee = modelMakerEmployee;
	}

	public Employee getSizerEmployee() {
		return sizerEmployee;
	}

	public void setSizerEmployee(Employee sizerEmployee) {
		this.sizerEmployee = sizerEmployee;
	}

	public void setPsizeId(Integer psizeId) {
		this.psizeId = psizeId;
	}

	

	public Double getWaxWt() {
		return waxWt;
	}

	public void setWaxWt(Double waxWt) {
		this.waxWt = waxWt;
	}

	public Double getSilverWt() {
		return silverWt;
	}

	public void setSilverWt(Double silverWt) {
		this.silverWt = silverWt;
	}

	public Double getGrms() {
		return grms;
	}

	public void setGrms(Double grms) {
		this.grms = grms;
	}

	public Boolean getHold() {
		return hold;
	}

	public void setHold(Boolean hold) {
		this.hold = hold;
	}

	public String getHoldRemark() {
		return holdRemark;
	}

	public void setHoldRemark(String holdRemark) {
		this.holdRemark = holdRemark;
	}

	public Boolean getSilverModel() {
		return silverModel;
	}

	public void setSilverModel(Boolean silverModel) {
		this.silverModel = silverModel;
	}

	public Boolean getRubberMould() {
		return rubberMould;
	}

	public void setRubberMould(Boolean rubberMould) {
		this.rubberMould = rubberMould;
	}

	public Boolean getCancel() {
		return cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getDesignDtStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return (designDt == null ? "" : dateFormat.format(designDt));
	}

	public Date getDesignDt() {
		return designDt;
	}

	public void setDesignDt(Date designDt) {
		this.designDt = designDt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getWaxTemp() {
		return waxTemp;
	}

	public void setWaxTemp(Double waxTemp) {
		this.waxTemp = waxTemp;
	}

	public Double getWaxPre() {
		return waxPre;
	}

	public void setWaxPre(Double waxPre) {
		this.waxPre = waxPre;
	}

	public EmailConcept getEmailConcept() {
		return emailConcept;
	}

	public void setEmailConcept(EmailConcept emailConcept) {
		this.emailConcept = emailConcept;
	}

	public Boolean getBbc() {
		return bbc;
	}

	public void setBbc(Boolean bbc) {
		this.bbc = bbc;
	}

	public Boolean getMustHave() {
		return mustHave;
	}

	public void setMustHave(Boolean mustHave) {
		this.mustHave = mustHave;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Integer getDefImgChk() {
		return defImgChk;
	}

	public void setDefImgChk(Integer defImgChk) {
		this.defImgChk = defImgChk;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public CollectionMaster getCollectionMaster() {
		return collectionMaster;
	}

	public void setCollectionMaster(CollectionMaster collectionMaster) {
		this.collectionMaster = collectionMaster;
	}

	public Boolean getMsi() {
		return msi;
	}

	public void setMsi(Boolean msi) {
		this.msi = msi;
	}

	public Integer getTotStone() {
		return totStone;
	}

	public void setTotStone(Integer totStone) {
		this.totStone = totStone;
	}

	public Double getTotCarat() {
		return totCarat;
	}

	public void setTotCarat(Double totCarat) {
		this.totCarat = totCarat;
	}

	public String getMainStyleNo() {
		return mainStyleNo;
	}

	public void setMainStyleNo(String mainStyleNo) {
		this.mainStyleNo = mainStyleNo;
	}

	public LookUpMast getProcess() {
		return process;
	}

	public void setProcess(LookUpMast process) {
		this.process = process;
	}

	public Double getRptWt() {
		return rptWt;
	}

	public void setRptWt(Double rptWt) {
		this.rptWt = rptWt;
	}

	public Boolean getLiquidMould() {
		return liquidMould;
	}

	public void setLiquidMould(Boolean liquidMould) {
		this.liquidMould = liquidMould;
	}

	public PatternMast getPatternMast() {
		return patternMast;
	}

	public void setPatternMast(PatternMast patternMast) {
		this.patternMast = patternMast;
	}

	public LookMast getLookMast() {
		return lookMast;
	}

	public void setLookMast(LookMast lookMast) {
		this.lookMast = lookMast;
	}

	public Double getFinishWt() {
		return finishWt;
	}

	public void setFinishWt(Double finishWt) {
		this.finishWt = finishWt;
	}

	public Double getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}

	public Boolean getMasterBal() {
		return masterBal;
	}

	public void setMasterBal(Boolean masterBal) {
		this.masterBal = masterBal;
	}


	public Integer getCastedPcs() {
		return castedPcs;
	}

	public void setCastedPcs(Integer castedPcs) {
		this.castedPcs = castedPcs;
	}
	
	public String getModelModiDtStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return (modelModiDt == null ? "" : dateFormat.format(modelModiDt));
	}

	public Date getModelModiDt() {
		return modelModiDt;
	}

	public void setModelModiDt(Date modelModiDt) {
		this.modelModiDt = modelModiDt;
	}

	public String getCustStyleNo() {
		return custStyleNo;
	}

	public void setCustStyleNo(String custStyleNo) {
		this.custStyleNo = custStyleNo;
	}

	public Double getCamWt() {
		return camWt;
	}

	public void setCamWt(Double camWt) {
		this.camWt = camWt;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public Double getSampleWt() {
		return sampleWt;
	}

	public void setSampleWt(Double sampleWt) {
		this.sampleWt = sampleWt;
	}

	public Employee getCadDesigner() {
		return cadDesigner;
	}

	public void setCadDesigner(Employee cadDesigner) {
		this.cadDesigner = cadDesigner;
	}

	public Employee getCadQc() {
		return cadQc;
	}

	public void setCadQc(Employee cadQc) {
		this.cadQc = cadQc;
	}

	public Employee getCamQc() {
		return camQc;
	}

	public void setCamQc(Employee camQc) {
		this.camQc = camQc;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public MarketTypeMast getMarketTypeMast() {
		return marketTypeMast;
	}

	public void setMarketTypeMast(MarketTypeMast marketTypeMast) {
		this.marketTypeMast = marketTypeMast;
	}

	public ProductTypeMast getProductTypeMast() {
		return productTypeMast;
	}

	public void setProductTypeMast(ProductTypeMast productTypeMast) {
		this.productTypeMast = productTypeMast;
	}

	public String getVendorStyle() {
		return vendorStyle;
	}

	public void setVendorStyle(String vendorStyle) {
		this.vendorStyle = vendorStyle;
	}

	public Boolean getExclusiveClient() {
		return exclusiveClient;
	}

	public void setExclusiveClient(Boolean exclusiveClient) {
		this.exclusiveClient = exclusiveClient;
	}

	public Party getVendor() {
		return vendor;
	}

	public void setVendor(Party vendor) {
		this.vendor = vendor;
	}

	public String getParentStyle() {
		return parentStyle;
	}

	public void setParentStyle(String parentStyle) {
		this.parentStyle = parentStyle;
	}

	public Employee getMerchandiser() {
		return merchandiser;
	}

	public void setMerchandiser(Employee merchandiser) {
		this.merchandiser = merchandiser;
	}
	
	
	
	

}
