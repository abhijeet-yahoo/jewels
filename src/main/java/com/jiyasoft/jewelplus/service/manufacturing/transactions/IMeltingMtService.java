package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;

public interface IMeltingMtService {

	public List<MeltingMt> findAll();

	public void save(MeltingMt meltingMt);

	public void delete(int id);

	public MeltingMt findOne(int id);
	
	public Page<MeltingMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);
	
	public Long count();
	
	public MeltingMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public Integer maxSrNo();
	
	public MeltingMt findByUniqueId(Long uniqueId);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<MeltingMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Page<MeltingMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String meltingMtDelete(int id);
	
	public String meltingStnRecReport(Integer mtId,String uploadDirecotryPath,String uploadParentDirecotryName,String uploadDirecotryName,
			String tmpUploadImage,String reportXmlDirectoryPath,String reportoutputdirectorypath,Principal principal) throws SQLException;
	
}
