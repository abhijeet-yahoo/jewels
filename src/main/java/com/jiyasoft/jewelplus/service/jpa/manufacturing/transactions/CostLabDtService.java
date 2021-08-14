package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostLabDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostLabDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.mysema.query.jpa.impl.JPAUpdateClause;


@Service
@Repository
@Transactional
public class CostLabDtService implements ICostLabDtService {

	@Autowired
	private ICostLabDtRepository costLabDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Override
	public List<CostLabDt> findAll() {
		return costLabDtRepository.findAll();
	}

	@Override
	public void save(CostLabDt costLabDt) {
		costLabDtRepository.save(costLabDt);
	}

	@Override
	public void delete(int id) {
		CostLabDt costLabDt = costLabDtRepository.findOne(id);
		costLabDt.setDeactive(true);
		costLabDt.setDeactiveDt(new java.util.Date());
		costLabDtRepository.save(costLabDt);
	}

	@Override
	public Long count() {
		return costLabDtRepository.count();
	}

	@Override
	public CostLabDt findOne(int id) {
		return costLabDtRepository.findOne(id);
	}

	@Override
	public List<CostLabDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return costLabDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}
	
	@Override
	public List<CostLabDt> findByCostingDtAndDeactive(CostingDt costingDt,
			Boolean deactive) {
		return costLabDtRepository.findByCostingDtAndDeactive(costingDt, deactive);
	}

	@Override
	public int labAsPerMaster(Integer costMtId) {
			Query query = entityManager.createNativeQuery("{ CALL jsp_updatecostinglabour(?) }");
			query.setParameter(1, costMtId);
			int retval = query.executeUpdate();

		return retval;
	}

	@Override
	public List<CostLabDt> findByItemNoAndCostingDtAndDeactive(String itemNo,
			CostingDt costingDt, Boolean deactive) {
		return costLabDtRepository.findByItemNoAndCostingDtAndDeactive(itemNo, costingDt, deactive);
	}

	
	@Override
	public void lockUnlockLabDt(Integer CostMtId, Boolean status) {
		QCostLabDt qCostLabDt = QCostLabDt.costLabDt;
		new JPAUpdateClause(entityManager, qCostLabDt).where(qCostLabDt.costingMt.id.eq(CostMtId))
		.set(qCostLabDt.rLock, status)
		.execute();
	}
	
	@Override
	public void updateItemNo(Integer costDtId, String itemNo) {
		QCostLabDt qCostLabDt = QCostLabDt.costLabDt;
		new JPAUpdateClause(entityManager, qCostLabDt).where(qCostLabDt.costingDt.id.eq(costDtId))
		.set(qCostLabDt.itemNo, itemNo)
		.execute();
	}

	@Override
	public List<CostLabDt> findByCostingDtAndMetalAndDeactive(
			CostingDt costingDt, Metal metal, Boolean deactive) {
		// TODO Auto-generated method stub
		return costLabDtRepository.findByCostingDtAndMetalAndDeactive(costingDt, metal, deactive);
	}

	@Override
	public String costLabDtSave(CostLabDt costLabDt, Integer id,
			Integer costMtId, Integer costDtId, Principal principal) {
		
	
		String retVal ="-1";
		
		try {
			
			CostingMt costingMt = costingMtService.findOne(costMtId);
			CostingDt costingDt = costingDtService.findOne(costDtId);
			
			
			if(costLabDt.getPcsWise() == true && costLabDt.getGramWise() == true ){
				return retVal = "-11";
			}
			
			if(costLabDt.getPcsWise() == true && costLabDt.getPercentWise() == true ){
				return retVal = "-11";
			}
			
			if(costLabDt.getGramWise() == true && costLabDt.getPercentWise() == true){
				return retVal = "-11";
			}
			if(costLabDt.getPcsWise() == false && costLabDt.getGramWise() == false && costLabDt.getPercentWise() == false){
				return retVal = "-12";
			}
			
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				costLabDt.setCostingMt(costingMt);
				costLabDt.setCostingDt(costingDt);
				costLabDt.setItemNo(costingDt.getItemNo());
				costLabDt.setBagmt(costingDt.getBagMt());
				costLabDt.setMetal(costLabDt.getMetal());
				costLabDt.setCreatedBy(principal.getName());
				costLabDt.setCreatedDate(new java.util.Date());
				costLabDt.setOrderDt(costingDt.getOrderDt());
				
				
			}else{
				costLabDt.setId(id);
				costLabDt.setCostingMt(costingMt);
				costLabDt.setCostingDt(costingDt);
				costLabDt.setItemNo(costingDt.getItemNo());
				costLabDt.setMetal(costLabDt.getMetal());
				costLabDt.setBagmt(costingDt.getBagMt());
				costLabDt.setOrderDt(costingDt.getOrderDt());
				costLabDt.setModiBy(principal.getName());
				costLabDt.setModiDate(new java.util.Date());
				retVal = "-2";
			}
			
			
			save(costLabDt);
			
			
				
		costingDtService.updateFob(costingDt);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	

}
