package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StateMaster;

public interface IPartyService {
	
	public List<Party> findAll();

	public Page<Party> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Party party);

	public void delete(int id);

	public Long count();

	public Party findOne(int id);

	public Party findByName(String name);

	public Map<Integer, String> getPartyList(Boolean moreData);
	
	public Map<Integer, String> getPartyList();

	public List<Party> findActivePartys();
	
	public List<Party> findAllExportClient();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Party> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public Double getCreditLimit(int id);
	
	public Page<Party> findActivePartysSortByName();
	
	public Party findByDefaultFlag(Boolean defaultFlag);
	
	public Map<Integer, String> getExportClientPartyList();
	
	public Page<Party> findActiveExportClientPartysSortByName();
	
	public Page<Party> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public Party findByPartyCodeAndDeactive(String partyCode, Boolean deactive);

	//public List<Party> findByOrderByPartyCodeAndDeactive(String partyCode, Boolean deactive);

	public Map<Integer, String> getExportClientPartyListForSupplier();
	
	public Page<Party> findActiveExportClientPartysSortByNameForSupplier();
	
	public List<Party> findAByExportClientOrderByPartyCodeAsc(Boolean exportClient);
	
  	public Map<Integer, String> getTransporterList();
	
	public Page<Party> findActiveTransporter();
	
	public String partyExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile, Principal principal) throws ParseException;
	
	public List<Party> findByDeactiveOrderByPartyCodeAsc(Boolean deactive);
}
