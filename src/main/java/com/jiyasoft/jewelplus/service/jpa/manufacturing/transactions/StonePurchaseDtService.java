package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStonePurchaseDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseMtService;

@Service
@Transactional
@Repository
public class StonePurchaseDtService implements IStonePurchaseDtService{
	
	@Autowired
	private IStonePurchaseDtRepository stonePurchaseDtRepository;
	
	@Autowired
	private IStonePurchaseMtService stonePurchaseMtService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	
	@Override
	public StonePurchaseDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return stonePurchaseDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<StonePurchaseDt> findByStonePurchaseMtAndDeactive(StonePurchaseMt stonePurchaseMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return stonePurchaseDtRepository.findByStonePurchaseMtAndDeactive(stonePurchaseMt, deactive);
	}

	@Override
	public void save(StonePurchaseDt stonePurchaseDt) {
		// TODO Auto-generated method stub
		stonePurchaseDtRepository.save(stonePurchaseDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		StonePurchaseDt stonePurchaseDt = stonePurchaseDtRepository.findOne(id);
		stonePurchaseDt.setDeactive(true);
		stonePurchaseDt.setDeactiveDt(new Date());
		stonePurchaseDtRepository.save(stonePurchaseDt);
	}

	@Override
	public StonePurchaseDt findOne(int id) {
		// TODO Auto-generated method stub
		return stonePurchaseDtRepository.findOne(id);
	}

	@Override
	public String stonePurchaseSave(StonePurchaseDt stonePurchaseDt, Integer id, Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		String retVal = "-1";
		
		StonePurchaseMt stonePurchaseMt = stonePurchaseMtService.findOne(mtId);
		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(stonePurchaseDt.getShape(), stonePurchaseDt.getSize(),false);
		if (id == null || id.equals("") || (id != null && id == 0)) {
	
			stonePurchaseDt.setCreatedBy(principal.getName());
			stonePurchaseDt.setCreatedDt(new java.util.Date());
			stonePurchaseDt.setUniqueId(new Date().getTime());
			retVal = "1";
			
		} else {
			
			if(stonePurchaseDt.getBalCarat() > stonePurchaseDt.getCarat()){
				return retVal = "-11";
			}
			
			
			stonePurchaseDt.setModiBy(principal.getName());
			stonePurchaseDt.setModiDt(new java.util.Date());
			stonePurchaseDt.setDepartment(stonePurchaseDt.getDepartment());
			retVal ="2";
			
		}
		
	
		stonePurchaseDt.setBalCarat(stonePurchaseDt.getCarat());
		stonePurchaseDt.setBalStone(stonePurchaseDt.getStone());

		
		if (stonePurchaseDt.getSubShape().getId() == null) {
			stonePurchaseDt.setSubShape(null);
		}
		
		stonePurchaseDt.setStonePurchaseMt(stonePurchaseMt);
		stonePurchaseDt.setSieve(stoneChart.getSieve());
		stonePurchaseDt.setSizeGroup(stoneChart.getSizeGroup());
		
		save(stonePurchaseDt);
		
				
		
		return retVal;

	}

	@Override
	public String stonePurchaseDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		try {
			
			StonePurchaseDt stonePurchaseDt = findOne(id);
			if(stonePurchaseDt.getBalCarat() < stonePurchaseDt.getCarat()){
				return "-2";
			}
			
				delete(id);
				retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

}
