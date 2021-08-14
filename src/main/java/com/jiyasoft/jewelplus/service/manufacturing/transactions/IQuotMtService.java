package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotMtService {
	
	
	public List<QuotMt> findAll();
	
	public Page<QuotMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(QuotMt quotMt);
	
	public void delete(int id);
	
	public Long count();
	
	public QuotMt findOne(int id);
	
	public QuotMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public QuotMt findByRefNoAndDeactive(String refNo,Boolean deactive);
	
	public QuotMt findByPartyAndMasterFlgAndDeactive(Party party,Boolean masterFlg,Boolean deactive);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<QuotMt> findByInvNo(Integer limit, Integer offset, String sort,
			String order, String invNo, Boolean onlyActive);
	
	public Page<QuotMt> findByInvNoListByParty(Integer limit, Integer offset, String sort,
			String order, String invNo, Boolean onlyActive,String partyNm);
	
	
	
	public QuotMt findByUniqueId(Long uniqueId);
	
	public Long maxBySrNo();

	
	public Long countAll(String colValue);

	
	public Page<QuotMt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name);
	
	
	public Page<QuotMt> findByParty(Integer limit, Integer offset, String sort, String order, String search, Boolean onlyActive,String party);


	public long countByParty(String partyName,String colName, String colValue, Boolean onlyActive);

	

}
