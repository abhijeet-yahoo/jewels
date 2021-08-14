package com.jiyasoft.jewelplus.service.manufacturing.transactions;

	import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssMtService {

	public List<VouIssMt> findAll();
		
		public Page<VouIssMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
		
		public void save(VouIssMt vouIssMt);
		
		public void delete(int id);
		
		public VouIssMt findOne(int id);

		public VouIssMt findByInvNoAndDeactive(String invNo,Boolean deactive);
		
		public Page<VouIssMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name);
		
		public VouIssMt findByUniqueId(Integer uniqueId);
		
		public Page<VouIssMt> getInvNoAutoFill(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);
		
		public Map<Integer,String> getVouIssList();
		
		
		public String vouIssTransfer(String fromInvNo,String toInvNo,String dtids,Principal principal);

		public List<Object[]>  partyWiseAndDateWiseListing(Integer limit, Integer offset, String sort, String order, String search,String partyIds,String fromDate,String toDate) throws ParseException;
		
		public String addBagInVouIss(String pOIds,Integer vouIssMtId,Principal principal, String vProcessId);

		public String deleteVouIssMtInvNo(Integer vouIssMtId,Principal principal);
		
		
	}


