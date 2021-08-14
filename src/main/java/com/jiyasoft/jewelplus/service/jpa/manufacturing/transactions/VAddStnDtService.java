
package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QVAddStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnDtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class VAddStnDtService implements IVAddStnDtService {

	@Autowired
	private IVAddStnDtRepository vAddStnDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Override
	public void save(VAddStnDt vAddStnDt) {
		vAddStnDtRepository.save(vAddStnDt);
	}

	@Override
	public void delete(int id) {
		VAddStnDt vAddStnDt = vAddStnDtRepository.findOne(id);
		vAddStnDt.setDeactive(true);
		vAddStnDt.setDeactiveDt(new java.util.Date());
		vAddStnDtRepository.save(vAddStnDt);
	}

	@Override
	public VAddStnDt findOne(int id) {
		return vAddStnDtRepository.findOne(id);
	}

	@Override
	public Long count() {
		return vAddStnDtRepository.count();
	}

	@Override
	public List<VAddStnDt> findByVAddDtAndDeactive(VAddDt VAddDt,
			Boolean deactive) {
		return vAddStnDtRepository.findByVAddDtAndDeactive(VAddDt, deactive);
	}
	
	@Override
	public Double calulateVAddStnDtTotLoanVal(Integer costMt) {
		
		QVAddStnDt qVAddStnDt = QVAddStnDt.vAddStnDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Double> totLoanVal = null;
		
		totLoanVal = query.from(qVAddStnDt). 
				where(qVAddStnDt.costingMt.id.eq(costMt).and(qVAddStnDt.deactive.eq(false))).list(qVAddStnDt.loanValue.sum());
		
		Double retVal = 0.0;
		
		for(Double totLoan : totLoanVal){
			retVal = totLoan;
		}
		
		if(retVal == null){
			retVal = 0.0;
		}
		
		return retVal;
	}

	@Override
	public List<VAddStnDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return vAddStnDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	
	
	
	@Override
	public Map<Integer, String> getQualityList(Integer costMtId) {
		
		CostingMt costingMt =costingMtService.findOne(costMtId);
		
		Map<Integer, String> qualityMap = new LinkedHashMap<Integer, String>();
		List<VAddStnDt> qualityList = findByCostingMtAndDeactive(costingMt,false);

		for (VAddStnDt vAddStnDt : qualityList) {
			qualityMap.put(vAddStnDt.getQuality().getId(), vAddStnDt.getQuality().getName());
		}

		return qualityMap;
	}


	
	
	
	@Override
	public Map<Integer, String> getSizeGroupList(Integer costMtId) {
		
		CostingMt costingMt =costingMtService.findOne(costMtId);
		
		Map<Integer, String> sizeGroupMap = new LinkedHashMap<Integer, String>();
		List<VAddStnDt> vAddStnDts = findByCostingMtAndDeactive(costingMt,false);

		for (VAddStnDt vAddStnDt : vAddStnDts) {
			sizeGroupMap.put(vAddStnDt.getSizeGroup().getId(), vAddStnDt.getSizeGroup().getName());
		}

		return sizeGroupMap;
	}

	@Override
	public List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndDeactive(CostingMt costingMt,
			StoneType stoneType, Shape shape, Quality quality, Boolean deactive) {
		// TODO Auto-generated method stub
		return vAddStnDtRepository.findByCostingMtAndStoneTypeAndShapeAndQualityAndDeactive(costingMt, stoneType, shape, quality, deactive);
	}

	@Override
	public List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(CostingMt costingMt,
			StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup, Boolean deactive) {
		// TODO Auto-generated method stub
		return vAddStnDtRepository.findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(costingMt, stoneType, shape, quality, sizeGroup, deactive);
	}

	@Override
	public List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndDeactive(CostingMt costingMt,
			StoneType stoneType, Shape shape, Quality quality, String size, Boolean deactive) {
		// TODO Auto-generated method stub
		return vAddStnDtRepository.findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndDeactive(costingMt, stoneType, shape, quality, size, deactive);
	}

	@Override
	public List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndDeactive(CostingMt costingMt, StoneType stoneType,
			Shape shape, Boolean deactive) {
		// TODO Auto-generated method stub
		return vAddStnDtRepository.findByCostingMtAndStoneTypeAndShapeAndDeactive(costingMt, stoneType, shape, deactive);
	}

	@Override
	public Map<Integer, String> getShapeList(Integer costMtId) {
	CostingMt costingMt =costingMtService.findOne(costMtId);
		
		Map<Integer, String> shapeMap = new LinkedHashMap<Integer, String>();
		List<VAddStnDt> shapeList = findByCostingMtAndDeactive(costingMt,false);

		for (VAddStnDt vAddStnDt : shapeList) {
			shapeMap.put(vAddStnDt.getShape().getId(), vAddStnDt.getShape().getName());
		}

		return shapeMap;
	}

	@Override
	public List<VAddStnDt> findByCostingMtAndShapeAndQualityAndDeactive(CostingMt costingMt, Shape shape,
			Quality quality, Boolean deactive) {
		// TODO Auto-generated method stub
		return vAddStnDtRepository.findByCostingMtAndShapeAndQualityAndDeactive(costingMt, shape, quality, deactive);
	}




	

}
