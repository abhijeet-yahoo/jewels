package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;



import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneAdjustment;

public interface IStoneAdjustmentService {
	
	
	public String saveAdj(String id,String addCarat,String dedCarat,Principal principal);
	
	public void save(StoneAdjustment stoneAdjustment);
	
	public StoneAdjustment findByUniqueId(Long uniqueId);


}
