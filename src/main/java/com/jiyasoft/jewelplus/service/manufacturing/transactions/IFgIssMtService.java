package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssMt;



public interface IFgIssMtService {

	public List<FgIssMt> findAll();

	public Page<FgIssMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(FgIssMt fgIssMt);

	public void delete(int id);

	public FgIssMt findOne(int id);

	public FgIssMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Page<FgIssMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	
	public Page<FgIssMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public Integer getMaxInvSrno();
	
	

}
