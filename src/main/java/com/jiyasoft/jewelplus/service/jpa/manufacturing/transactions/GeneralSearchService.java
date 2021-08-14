package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.GeneralSearch;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IGeneralSearchRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IGeneralSearchService;

@Service
@Repository
@Transactional
public class GeneralSearchService implements IGeneralSearchService {
	
	
	@Autowired
	private IGeneralSearchRepository generalSearchRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	

	@Override
	public List<GeneralSearch> getGeneralSearchList(String partyId,
			Integer orderTypeId, String orderNo, String orderRefNo,
			Integer styleNo, Integer purityId, Integer colorId, String bagNm,
			Integer departmentId,String pOrdRef,Boolean exportFlg) {
		
		
		List<GeneralSearch> generalSearchList = null;
		
		
		String partyCond 			= "";
		String orderTypeCond 		= "";
		String orderNoCond 			= "";
		String orderRefNoCond 		= "";
		String styleNoCond 			= "";
		String purityCond 			= "";
		String colorCond 			= "";
		String bagNmCond 			= "";
		String departmentCond		= "";
		String ordRefCond		= "";
		
		
		if(partyId.length() > 0 ){
			partyCond = "PartyId in ("+partyId+")";
		}else{
			partyCond = "";
		}
		
		if(orderTypeId != null){
			orderTypeCond = " OrdTypeId in ("+orderTypeId+") ";
		}else{
			orderTypeCond = "";
		}
		
		if(orderNo.length() > 0){
			orderNoCond = "InvNo in ('"+orderNo+"')";
		}else{
			orderNoCond = "";
		}
		
		if(orderRefNo.length() > 0){
			orderRefNoCond = " RefNo in ('"+orderRefNo+"') ";
		}else{
			orderRefNoCond = "";
		}
		
		
		if(styleNo != 0){
			styleNoCond = " StyleId in ("+styleNo+") ";
		}else{
			styleNoCond = "";
		}
		
		if(purityId != null){
			purityCond = " PurityId in ("+purityId+") ";
		}else{
			purityCond = "";
		}
		
		if(colorId != null){
			colorCond = " ColorId in ("+colorId+") ";
		}else{
			colorCond = "";
		}
		
		if(bagNm.length() > 0){
			bagNmCond = "  BagNm in ('"+bagNm+"') ";
		}else{
			bagNmCond = "";
		}
		
		if(departmentId != null){
			departmentCond = " DeptId in ("+departmentId+") ";
		}else{
			departmentCond = "";
		}
		
		if(pOrdRef != ""){
			ordRefCond = " dtordref like '%"+pOrdRef+"%'";
		}else{
			ordRefCond = "";
		}
		
		
		
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<GeneralSearch> query =  (TypedQuery<GeneralSearch>)entityManager.createNativeQuery("{ CALL jsp_genaralsearch(?,?,?,?,?,?,?,?,?,?,?) }", GeneralSearch.class);
		
		query.setParameter(1,partyCond);
		query.setParameter(2,orderTypeCond);
		query.setParameter(3,orderNoCond);
		query.setParameter(4,orderRefNoCond); 
		query.setParameter(5,styleNoCond);
		query.setParameter(6,purityCond); 
		query.setParameter(7,colorCond);
		query.setParameter(8,bagNmCond);
		query.setParameter(9,departmentCond);
		query.setParameter(10,ordRefCond);
		query.setParameter(11,exportFlg);
		
		
		generalSearchList = query.getResultList();
	
		
		return generalSearchList;
	}
	
	

	@Override
	public Long count() {
		return generalSearchRepository.count();
	}

	
	
	
	
	
}
