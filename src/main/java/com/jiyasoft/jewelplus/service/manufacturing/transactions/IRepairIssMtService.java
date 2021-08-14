package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssMt;

public interface IRepairIssMtService {

	public List<RepairIssMt> findAll();

	public Page<RepairIssMt> findAll(Integer limit, Integer offset, String sort, String order, String search);

	public void save(RepairIssMt repairIssMt);

	public void delete(int id);

	public RepairIssMt findOne(int id);

	public RepairIssMt findByInvNoAndDeactive(String invNo, Boolean deactive);

	public Page<RepairIssMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name,
			Boolean onlyActive);

	public Page<RepairIssMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive);

	public Integer getMaxInvSrno();
	
	public String repairIssMtListing(Model model,Integer limit,Integer offset,String sort,String order, String search, Principal principal)	throws ParseException ;
}
