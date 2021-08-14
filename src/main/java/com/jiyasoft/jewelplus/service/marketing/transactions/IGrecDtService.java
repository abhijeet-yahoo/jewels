package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecDtService {

	public void save(GrecDt grecDt);

	public void delete(int id);

	public GrecDt findOne(int id);

	public GrecDt findByUniqueId(Long uniqueId);

	public String transactionalSave(GrecDt grecDt, Integer id, Integer grecMtIdPk, String metalDtData,
			Principal principal, Boolean netWtWithCompFlg);

	public List<GrecDt> findByGrecMt(GrecMt grecMt);

	public Page<GrecDt> searchAll(Integer limit, Integer offset, String sort, String order, String name, Integer mtId);

	public Integer getMaxSrNo(Integer mtId);

	public String getGrecDtTotal(Integer mtId);

	public String grecDtLisiting(Integer limit, Integer offset, String sort, String order, String name,Integer mtId, Principal principal,Boolean disableFlg);
	
	public String updateFob(GrecDt grecDt,Boolean netWtWithCompFlg); 
	
	public String updateGrossWt(GrecDt grecDt,Boolean netWtWithComp);
	
	public String deleteGrecDt(Integer dtId);

}
