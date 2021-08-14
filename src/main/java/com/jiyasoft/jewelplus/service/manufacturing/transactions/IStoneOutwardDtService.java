package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;

public interface IStoneOutwardDtService {
	
	public List<StoneOutwardDt> findAll();
	
	public Page<StoneOutwardDt> findAll(Integer limit,Integer offset,
			String sort,String order,String search);
	
	public void save(StoneOutwardDt stoneOutwardDt);
	
	public void delete(int id);
	
	public Long count();
	
	public StoneOutwardDt findOne(int id);
	
	public Map<Integer, String > getStoneOutwardDtList();
	
	public List<StoneOutwardDt> findByStoneOutwardMt(StoneOutwardMt stoneOutwardMt);
	
	public Page<StoneOutwardDt> findByStoneOutwardMt(StoneOutwardMt stoneOutwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search);
	
	public StoneOutwardDt findByUniqueId(Long uniqueId);

	
	public List<StoneOutwardDt> findByStoneOutwardMtAndDeactiveOrderByIdDesc(StoneOutwardMt stoneOutwardMt, Boolean deactive);
	
	public String saveStoneOutwardDt(StoneOutwardDt stoneOutwardDt,Integer id, String pInvNo,Double vCarat,Double prevCarat,Integer vStone,String sizeGroupStr,Principal principal,String stockType,Boolean allowNegative);
	
	public String stoneOutDtDelete(Integer id, Principal principal);
	
	public Integer getMaxTranSrno(Integer sordDtId);
}
