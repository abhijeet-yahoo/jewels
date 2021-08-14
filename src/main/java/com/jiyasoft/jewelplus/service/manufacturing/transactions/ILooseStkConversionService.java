package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;

public interface ILooseStkConversionService {

	public List<LooseStkConversion> findByStnLooseDt(StnLooseDt stnLooseDt);

	public void save(LooseStkConversion looseStkConversion);

	public void delete(int id);
	
	public LooseStkConversion findOne(int id);
	
	public String saveStnLooseConversionDt(LooseStkConversion looseStkConversion,Integer id,Double vCarat,Integer vStone,
			String sizeGroupStr,Principal principal,Integer vMtId,Integer vDtId);
	
	public Integer getMaxLotSrno();
	
	public String stnLooseConversionDtListing(Integer dtId,Principal principal);
	
	public String stnLooseConversionDtDelete(Integer id, Principal principal);
	
	public List<LooseStkConversion> findByStnLooseMt(StnLooseMt stnLooseMt);


}
