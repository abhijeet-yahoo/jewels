package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.GeneralSearch;

public interface IGeneralSearchService {
	
	List<GeneralSearch> getGeneralSearchList(String partyId,Integer orderTypeId,String orderNo,String orderRefNo,Integer styleNo,
			Integer purityId,Integer colorId,String bagNm,Integer departmentId,String pOrdRef, Boolean exportFlg);
	
	public Long count();
	
}
