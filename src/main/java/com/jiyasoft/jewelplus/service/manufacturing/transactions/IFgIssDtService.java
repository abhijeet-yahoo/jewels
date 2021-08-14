package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssMt;


public interface IFgIssDtService {

	public List<FgIssDt> findAll();

	public Page<FgIssDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(FgIssDt fgIssDt);

	public void delete(int id);


	public FgIssDt findOne(int id);

	

	public List<FgIssDt> findByFgIssMt(FgIssMt fgIssMt);

	public Page<FgIssDt> findByFgIssMt(FgIssMt fgIssMt,
			Integer limit, Integer offset, String sort, String order,
			String search);
	
	
	public String fgTransferInStock(Principal principal,String vMtId,Integer fgIssMtId);
	
	public String fgApproval(Principal principal,String vFgIssDtIds);


	
	}
