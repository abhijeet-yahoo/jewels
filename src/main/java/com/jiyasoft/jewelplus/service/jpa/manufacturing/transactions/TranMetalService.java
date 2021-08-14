package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ITranMetalRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;

@Service
@Repository
@Transactional
public class TranMetalService implements ITranMetalService{

	@Autowired
	private ITranMetalRepository tranMetalRepository;
	
	
	
	@Override
	public List<TranMetal> findByBagMtAndCurrStk(BagMt bagMt,
			Boolean currStk) {
		
		return tranMetalRepository.findByBagMtAndCurrStk(bagMt, currStk);
		
	}

	@Override
	public TranMetal findOne(int id) {
		// TODO Auto-generated method stub
		return tranMetalRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getLookpList(BagMt bagMt) {
		Map<Integer, String> partMap = new LinkedHashMap<Integer, String>();
		List<TranMetal> tranMetalsList = findByBagMtAndCurrStk(bagMt, true);
		
		for(TranMetal tranMetal : tranMetalsList){
			partMap.put(tranMetal.getPartNm().getId(), tranMetal.getPartNm().getFieldValue());
		}
		
		return partMap;
	}

	@Override
	public TranMetal findByBagMtIdAndPartNmIdAndCurrStk(
			Integer bagId, Integer partId, Boolean currStk) {
		// TODO Auto-generated method stub
		return tranMetalRepository.findByBagMtIdAndPartNmIdAndCurrStk(bagId, partId, currStk);
	}

	@Override
	public List<TranMetal> findByTranMtAndCurrStk(TranMt tranMt, Boolean currStk) {
		// TODO Auto-generated method stub
		return tranMetalRepository.findByTranMtAndCurrStk(tranMt, currStk);
	}

	@Override
	public void save(TranMetal tranMetal) {
		// TODO Auto-generated method stub
		tranMetalRepository.save(tranMetal);
	}

	@Override
	public List<TranMetal> findByDepartmentAndCurrStkAndPurityAndColor(
			Department department, Boolean currStk, Purity purity, Color color) {
		// TODO Auto-generated method stub
		return tranMetalRepository.findByDepartmentAndCurrStkAndPurityAndColor(department, currStk, purity, color);
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		 tranMetalRepository.delete(id);
		 
		 return "1";
		 
	}
	
	
	@Override
	public Map<Integer, String> getLossBookPart(BagMt bagMt,
			Department department) {
		Map<Integer, String> partNmMap = new LinkedHashMap<Integer, String>();
		List<TranMetal> tranMetalList = findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		for(TranMetal tranMetal:tranMetalList){
			partNmMap.put(tranMetal.getPartNm().getId(), tranMetal.getPartNm().getFieldValue());
		}
		
		
		return partNmMap;
	}

	
	@Override
	public List<TranMetal> findByBagMtAndDepartmentAndCurrStk(
			BagMt bagMt, Department department, Boolean currStk) {
		
		return tranMetalRepository.findByBagMtAndDepartmentAndCurrStk(bagMt, department, currStk);
	}
	
	
	
	
	

	@Override
	public Map<Integer, String> getPartListByTranMt(TranMt tranMt) {
		
		List<TranMetal>tranMetals =findByTranMtAndCurrStk(tranMt, true);
		Map<Integer,String> partMap = new LinkedHashMap<Integer, String>();
		for(TranMetal tranMetal: tranMetals){
			partMap.put(tranMetal.getPartNm().getId(), tranMetal.getPartNm().getFieldValue());
		}
		
		
		// TODO Auto-generated method stub
		return partMap;
	}

	@Override
	public TranMetal findByBagMtIdAndMainMetalAndCurrStk(
			Integer bagId, Boolean mainMetal, Boolean currStk) {
		// TODO Auto-generated method stub
		return tranMetalRepository.findByBagMtIdAndMainMetalAndCurrStk(bagId, mainMetal, currStk);
	}

	@Override
	public List<TranMetal> findByBagMtIdAndPartNmId(Integer bagId,
			Integer partId) {
		// TODO Auto-generated method stub
		return tranMetalRepository.findByBagMtIdAndPartNmId(bagId, partId);
	}

	@Override
	public TranMetal findByRefTranMetalIdAndCurrStk(Integer refTranMetalId,
			Boolean currStk) {
		// TODO Auto-generated method stub
		return tranMetalRepository.findByRefTranMetalIdAndCurrStk(refTranMetalId, currStk);
	}

	
	

}
