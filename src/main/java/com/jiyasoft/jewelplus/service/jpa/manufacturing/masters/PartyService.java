package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CountryMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LedgerGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.PartyExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StateMaster;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IPartyRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICountryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILedgerGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStateMasterService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class PartyService implements IPartyService {

	@Autowired
	private IPartyRepository partyRepository;
	
	@Autowired
	private ILedgerGroupService ledgerGroupService;
	
	@Autowired
	private IStateMasterService stateMasterService;
	
	@Autowired
	private ICountryService countryService;

	@Override
	public List<Party> findAll() {
		return partyRepository.findAll();
	}

	@Override
	public Page<Party> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return partyRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Party party) {
		partyRepository.save(party);
	}

	@Override
	public void delete(int id) {
		Party party = partyRepository.findOne(id);
		party.setDeactive(true);
		party.setDeactiveDt(new java.util.Date());
		partyRepository.save(party);
	}

	@Override
	public Long count() {
		return partyRepository.count();
	}

	@Override
	public Party findOne(int id) {
		return partyRepository.findOne(id);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qParty.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qParty.deactive.eq(false).and(
						qParty.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qParty.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("name")
					&& colValue != null) {
				expression = qParty.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return partyRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return partyRepository.count();
			}
		}

		return partyRepository.count(expression);
	}

	@Override
	public Page<Party> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {

		QParty qParty = QParty.party;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qParty.deactive.eq(false);
			} else {
				expression = qParty.deactive.eq(false).and(
						qParty.partyCode.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qParty.partyCode.like(name + "%");
			}
		}

		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "partyCode";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		
		
		Page<Party> partyList = (Page<Party>) partyRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC :  Direction.ASC), sort));

		return partyList;

	}

	@Override
	public Party findByName(String name) {
		return partyRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getPartyList(Boolean moreData) {
		Map<Integer, String> partyMap = new HashMap<Integer, String>();
		List<Party> partyList = findActivePartys();

		for (Party party : partyList) {
			if (moreData) {
				partyMap.put(party.getId(),
						party.getName() + " [" + party.getCreditLimit() + "]");
			} else {
				partyMap.put(party.getId(), party.getName());
			}
		}

		return partyMap;
	}

	@Override
	public Map<Integer, String> getPartyList() {
		Map<Integer, String> partyMapp = new LinkedHashMap<Integer, String>();
	//	Page<Party> partyList = findActivePartysSortByName();         //Old service -- 
		List<Party> partyList = findByDeactiveOrderByPartyCodeAsc(false);
		
		for (Party party : partyList) {
			partyMapp.put(party.getId(), party.getPartyCode());
		}
		
		return partyMapp;
	}

	@Override
	public List<Party> findActivePartys() {
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false).and(qParty.exportClient.eq(false));
		List<Party> partyList = (List<Party>) partyRepository.findAll(expression);
		return partyList;
	}

	
	@Override
	public Double getCreditLimit(int id) {
		return partyRepository.findOne(id).getCreditLimit();
	}
	

	@Override
	public Page<Party> findActivePartysSortByName() {
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false);
		Page<Party> partyList = partyRepository.findAll(expression,new PageRequest(0, 10000, Direction.ASC, "name"));
		return partyList;
	}
	

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left,
				Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public Party findByDefaultFlag(Boolean defaultFlag) {
		
		return partyRepository.findByDefaultFlag(defaultFlag);
	}

	@Override
	public Map<Integer, String> getExportClientPartyList() {
		
		Map<Integer, String> partyMapp = new LinkedHashMap<Integer, String>();
	//	Page<Party> partyList = findActiveExportClientPartysSortByName();
		List<Party> partyList = findAByExportClientOrderByPartyCodeAsc(true);
		for (Party party : partyList) {
			partyMapp.put(party.getId(), party.getPartyCode());
		}
		
		return partyMapp;
	}

	@Override
	public Page<Party> findActiveExportClientPartysSortByName() {
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false).and(qParty.exportClient.eq(true));
		Page<Party> partyList = partyRepository.findAll(expression,new PageRequest(0, 10000, Direction.ASC, "name"));
		return partyList;
	}

	@Override
	public Page<Party> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {

		QParty qParty = QParty.party;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qParty.deactive.eq(false);
			}else{
				expression = qParty.deactive.eq(false).and(qParty.name.like("%" + search + "%").or(qParty.partyCode.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qParty.name.like("%" + search + "%").or(qParty.partyCode.like("%" + search + "%"));
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("partyCode")) {
			sort = "categCode";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<Party> partymasttList =(Page<Party>) partyRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return partymasttList;
		
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		QParty qParty = QParty.party;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qParty.deactive.eq(false);			
				}else{
					expression = qParty.deactive.eq(false).and(qParty.name.like("%" + colValue + "%").or(qParty.partyCode.like("%" + colValue + "%")));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qParty.name.like("%" + colValue + "%").or(qParty.partyCode.like("%" + colValue + "%"));
				}
			}
			
			return partyRepository.count(expression);
	
	}

	@Override
	public Party findByPartyCodeAndDeactive(String partyCode, Boolean deactive) {
		// TODO Auto-generated method stub
		return partyRepository.findByPartyCodeAndDeactive(partyCode, deactive);
	}

	@Override
	public List<Party> findAllExportClient() {
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false).and(qParty.exportClient.eq(true));
		List<Party> partyList = (List<Party>) partyRepository.findAll(expression);
		return partyList;
	}

	@Override
	public Map<Integer, String> getExportClientPartyListForSupplier() {
		Map<Integer, String> partyMapp = new LinkedHashMap<Integer, String>();
	//	Page<Party> partyList = findActiveExportClientPartysSortByNameForSupplier();
		List<Party> partyList = findAByExportClientOrderByPartyCodeAsc(false);
		
		
		for (Party party : partyList) {
			partyMapp.put(party.getId(), party.getPartyCode());
		}
		
		return partyMapp;
	}

	@Override
	public Page<Party> findActiveExportClientPartysSortByNameForSupplier() {
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false).and(qParty.exportClient.eq(false));
		Page<Party> partyList = partyRepository.findAll(expression,new PageRequest(0, 10000, Direction.ASC, "name"));
		return partyList;
	}

	@Override
	public List<Party> findAByExportClientOrderByPartyCodeAsc(Boolean exportClient) {
		// TODO Auto-generated method stub
		return partyRepository.findAByExportClientOrderByPartyCodeAsc(exportClient);
	}

	@Override
	public Map<Integer, String> getTransporterList() {
		Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
		Page<Party>  partyLists = findActiveTransporter();
		
		for(Party party : partyLists){
		  mapList.put(party.getId(), party.getName());
		}
		return mapList;
	}

	@Override
	public Page<Party> findActiveTransporter() {
		QParty qParty = QParty.party;
	       BooleanExpression expression = qParty.deactive.eq(false).and(qParty.ledgerGroup.name.equalsIgnoreCase("Transporter"));
	       Page<Party> lederGroupList = partyRepository.findAll(expression, new PageRequest(0,10000, Direction.ASC,"name"));
	       
			return lederGroupList;
	}

	@Override
	public String partyExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile,
			Principal principal) throws ParseException {
		
		String retVal ="";
		Boolean remarkFlg = false;
		synchronized (this) {
			
			try {
				
				if(!tempExcelFile.isEmpty()){
				
				List<PartyExcel> partyExcelList = new ArrayList<PartyExcel>();
				
				    if (tempExcelFile.endsWith("xlsx")) {
				    	
				    	int i=1;
				    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				        XSSFSheet worksheet = workbook.getSheetAt(0);
						while (i <= worksheet.getLastRowNum()) {
							
							String remark = "";	
							PartyExcel partyExcel = new PartyExcel();
							
							XSSFRow row = worksheet.getRow(i++);
							
							if( row != null) {
							
							
							Cell partyCell = row.getCell(0, row.RETURN_BLANK_AS_NULL);
							if (partyCell != null) {
								if (row.getCell(0, row.RETURN_BLANK_AS_NULL).toString() != "") {
								
									Party party2 = findByName(row.getCell(0).toString());
									if (party2 != null) {
										if (remark == "") {
											remark = "Duplicate Party Name";
										} else {
											remark += ",Duplicate Party Name";
										}
									}
								}
								
							}else {
								if(remark == ""){
									remark = "Party compulsory"; 
								}else {
									remark += ",Party compulsory";
								}
							}
							
							
							Cell partyCodeCell = row.getCell(28, row.RETURN_BLANK_AS_NULL);
							if (partyCodeCell != null) {
								if (row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() != "") {
								
									Party party = findByPartyCodeAndDeactive(row.getCell(28).toString(),false);
									if (party != null) {
										if (remark == "") {
											remark = "Duplicate PartyCode";
										} else {
											remark += ",Duplicate PartyCode";
										}
									}
								}
								
							}else {
								if(remark == ""){
									remark = "PartyCode compulsory"; 
								}else {
									remark += ",PartyCode compulsory";
								}
							}
							
							Cell ledgerGroupCell = row.getCell(2, row.RETURN_BLANK_AS_NULL);
							if (ledgerGroupCell != null) {
								if (row.getCell(2, row.RETURN_BLANK_AS_NULL).toString() != "") {
								
									LedgerGroup ledgerGroup = ledgerGroupService.findByName(row.getCell(2).toString());
									if(ledgerGroup == null){
										if(remark == ""){
											remark = "No such LedgerGroup exists"; 
										}else {
											remark += ",No such LedgerGroup exists";
										}
										
									}
								}
								
							}
							
							Cell stateCell = row.getCell(10, row.RETURN_BLANK_AS_NULL);
							if (stateCell != null) {
								if (row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() != "") {
								
									StateMaster stateMaster = stateMasterService.findByName(row.getCell(10).toString());
									if(stateMaster == null){
										if(remark == ""){
											remark = "No such State exists"; 
										}else {
											remark += ",No such State exists";
										}
										
									}
								}
								
							}
							
							
							Cell countryCell = row.getCell(11, row.RETURN_BLANK_AS_NULL);
							if (countryCell != null) {
								if (row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() != "") {
								
									CountryMaster countryMaster = countryService.findByNameAndDeactive(row.getCell(11).toString(), false);
									if(countryMaster == null){
										if(remark == ""){
											remark = "No such Country exists"; 
										}else {
											remark += ",No such Country exists";
										}
										
									}
								}
								
							}
							
							Cell maillingNameCell = row.getCell(1, row.RETURN_BLANK_AS_NULL);
							if (maillingNameCell == null) {
								if(remark == ""){
									remark = "Mailing Name compulsory"; 
								}else {
									remark += ",Mailing Name compulsory";
								}
							}
							
							
							Cell cityCell = row.getCell(8, row.RETURN_BLANK_AS_NULL);
							if (cityCell == null) {
								if(remark == ""){
									remark = "City compulsory"; 
								}else {
									remark += ",City compulsory";
								}
							}
							
								
								
								
						
							if (remark != "") {
								
								remarkFlg = true;
								partyExcel.setPartyname(row.getCell(0, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(0, row.RETURN_BLANK_AS_NULL).toString() :"");
								partyExcel.setPartyCode(row.getCell(28, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() :"");
								partyExcel.setLedgerGroup(row.getCell(2, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(2, row.RETURN_BLANK_AS_NULL).toString() :"");
								partyExcel.setCountry(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() :"");
								partyExcel.setStateNm(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() :"");
								partyExcel.setMailingName(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() :"");
								partyExcel.setStatusRemark(remark);
								partyExcelList.add(partyExcel);
								
								}
							
						}
													
						}
						
						workbook.close();
						
				    } else if (tempExcelFile.endsWith("xls")) {
				    	int i = 1;
						HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
						HSSFSheet worksheet = workbook.getSheetAt(0);
						while (i <= worksheet.getLastRowNum()) {
							String remark = "";	
							PartyExcel partyExcel = new PartyExcel();
							
							HSSFRow row = worksheet.getRow(i++);
							if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
								continue;
							}
							
							Party party2 = findByName(row.getCell(0).toString());
							if (party2 != null) {
								if (remark == "") {
									remark = "Duplicate Party Name";
								} else {
									remark += ",Duplicate Party Name";
								}
							}
							
							Party party = findByPartyCodeAndDeactive(row.getCell(28).toString(),false);
							if (party != null) {
								if (remark == "") {
									remark = "Duplicate Party Code";
								} else {
									remark += ",Duplicate Party Code";
								}
							}
							
								LedgerGroup ledgerGroup = ledgerGroupService.findByName(row.getCell(2).toString());
							if(ledgerGroup == null){
								if(remark == ""){
									remark = "No such LedgerGroup exists"; 
								}else {
									remark += ",No such LedgerGroup exists";
								}
							
							}
							
							
						
						if (remark != "") {
							remarkFlg = true;
							partyExcel.setPartyname(row.getCell(0).toString());
							partyExcel.setPartyCode(row.getCell(28).toString());
							partyExcel.setLedgerGroup(row.getCell(2).toString());
							partyExcel.setStatusRemark(remark);
							partyExcelList.add(partyExcel);
						
						}
							
					}
					
						workbook.close();
						
				    } else {
				        throw new IllegalArgumentException("The specified file is not Excel file");
				    }
				    
				    
				    // Create Design
				    
				    if (remarkFlg) {
				    	 session.setAttribute("partyExcelSessionList", partyExcelList);  
				    	 retVal="yes";
				    	
				    }else {
				    	
				    	
				    	   if (tempExcelFile.endsWith("xlsx")) {
				    		 
				    		   int i=1;
						    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
						        XSSFSheet worksheet = workbook.getSheetAt(0);
						     
								while (i <= worksheet.getLastRowNum()) {
									
									XSSFRow row = worksheet.getRow(i++);
									
									if(row != null) {
										
									if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
										continue;
									}
									 
								
									//note : temporary list
									//remark is set in createdBy for temporary list
									//status is set in updatedBy for temporary list
																		
									
									Party partyCode = findByPartyCodeAndDeactive(row.getCell(28, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() :null, false);
									
									StateMaster stateMaster = stateMasterService.findByName(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() :null);
									CountryMaster countryMaster = countryService.findByNameAndDeactive(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() :null, false);
									if(partyCode == null) {
								
										Party partyNew = new Party(); 
										
										LedgerGroup ledgerGroup = ledgerGroupService.findByName(row.getCell(2).toString());
										
										partyNew.setPartyCode(row.getCell(28, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setName(row.getCell(0, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(0, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setMailingName(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setLedgerGroup(ledgerGroup);
										//partyNew.setType(row.getCell(3).toString());
										partyNew.setContactPerson(row.getCell(4, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(4, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setAddress(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setArea(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setZone(row.getCell(7, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setCity(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setZip(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setStateMast(stateMaster);
										partyNew.setCountry(countryMaster);;
										partyNew.setOfficePhone(row.getCell(12, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(12, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setFax(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(13, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setWebSite(row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(14, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setMobile(row.getCell(15, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(15, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setEmailAddress(row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(16, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setSalesMan(row.getCell(17, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(17, row.RETURN_BLANK_AS_NULL).toString() :null);
										/*partyNew.setBranch(row.getCell(18).toString());
										partyNew.setAccountNo(row.getCell(19).toString());
										partyNew.setAccountHolderName(row.getCell(20).toString());
										partyNew.setBsrCode(row.getCell(21).toString());
										partyNew.setIfsCode(row.getCell(22).toString());
										*/
										partyNew.setCreditDays(row.getCell(23, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(23, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setCreditLimit(row.getCell(24, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(24, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										partyNew.setGst(row.getCell(25, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(25, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setPanNo(row.getCell(26, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(26, row.RETURN_BLANK_AS_NULL).toString() :null);
										partyNew.setCreateDt(new Date());
										partyNew.setCreatedBy(principal.getName());
									    save(partyNew);
										
									
								}

								}
								
								workbook.close();
								}
								
						    } else if (tempExcelFile.endsWith("xls")) {
						    	int i = 1;
								HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
								HSSFSheet worksheet = workbook.getSheetAt(0);
									
									while (i <= worksheet.getLastRowNum()) {
										HSSFRow row = worksheet.getRow(i++);
							
										if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
											continue;
										}
									
									
										//note : temporary list
										//remark is set in createdBy for temporary list
										//status is set in updatedBy for temporary list
																			
							
											Party partyNew = new Party(); 
											
											LedgerGroup ledgerGroup = ledgerGroupService.findByName(row.getCell(2).toString());
											
											partyNew.setPartyCode(row.getCell(28).toString() != null ? row.getCell(28).toString() :"");
											partyNew.setName(row.getCell(0).toString() != null ? row.getCell(0).toString() :"");
										//	partyNew.setMailingName(row.getCell(1).toString());
											partyNew.setLedgerGroup(ledgerGroup);
											//partyNew.setType(row.getCell(3).toString());
											Cell contacPersonCell = row.getCell(4, row.RETURN_BLANK_AS_NULL);
											partyNew.setContactPerson(contacPersonCell != null ? row.getCell(4).toString() :"");
											partyNew.setAddress(row.getCell(5).toString() != null ? row.getCell(5).toString() :"");
											partyNew.setArea(row.getCell(6).toString() != null ? row.getCell(6).toString() :"");
											partyNew.setZone(row.getCell(7).toString() != null ? row.getCell(7).toString() :"");
											partyNew.setCity(row.getCell(8).toString() != null ? row.getCell(8).toString() :"");
											partyNew.setZip(row.getCell(9).toString() != null ? row.getCell(9).toString() :"");
									//		partyNew.setState(row.getCell(10).toString());
									//		partyNew.setCountry(row.getCell(11).toString());
											partyNew.setOfficePhone(row.getCell(12).toString() != null ? row.getCell(12).toString() :"");
											partyNew.setFax(row.getCell(13).toString() != null ? row.getCell(13).toString() :"");
											partyNew.setWebSite(row.getCell(14).toString() != null ? row.getCell(14).toString() : "");
											partyNew.setMobile(row.getCell(15).toString() != null ? row.getCell(15).toString() :"");
											partyNew.setEmailAddress(row.getCell(16).toString() != null ? row.getCell(16).toString() :"");
											partyNew.setSalesMan(row.getCell(17).toString() != null ? row.getCell(17).toString() :"");
											/*partyNew.setBranch(row.getCell(18).toString());
											partyNew.setAccountNo(row.getCell(19).toString());
											partyNew.setAccountHolderName(row.getCell(20).toString());
											partyNew.setBsrCode(row.getCell(21).toString());
											partyNew.setIfsCode(row.getCell(22).toString());
											*/
											partyNew.setCreditDays(row.getCell(23).toString() != null ? row.getCell(23).toString() :"");
											partyNew.setCreditLimit(row.getCell(24).toString() != null ? Double.parseDouble(row.getCell(24).toString()) :0.0);
											partyNew.setGst(row.getCell(25).toString() != null ? row.getCell(25).toString() :"");
											partyNew.setPanNo(row.getCell(26).toString() != null ? row.getCell(26).toString() :"" );
											partyNew.setCreateDt(new Date());
											partyNew.setCreatedBy(principal.getName());
										    save(partyNew);
											
										
									
										
									

										
									}

								workbook.close();
								
						    }
				    	
				    	retVal="1";
				    	   
				    }
				    
				    
				    
				    
				    
				   
					}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
		return retVal;
	}



	@Override
	public List<Party> findByDeactiveOrderByPartyCodeAsc(Boolean deactive) {
		// TODO Auto-generated method stub
		return partyRepository.findByDeactiveOrderByPartyCodeAsc(deactive);
	}


}
