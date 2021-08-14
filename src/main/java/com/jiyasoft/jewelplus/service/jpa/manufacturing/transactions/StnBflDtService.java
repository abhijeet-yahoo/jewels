package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStnBflDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;


@Service
@Repository
@Transactional
public class StnBflDtService implements IStnBflDtService{
	
	
	@Autowired
	IStnBflDtRepository stnBflDtRepository;
	
	@Autowired
	IStoneInwardDtService stoneInwardDtService;
	
	@Autowired
	IStoneTranService stoneTranService;

	
	@Override
	public List<StnBflDt> findAll() {
		return stnBflDtRepository.findAll();
	}

	@Override
	public Page<StnBflDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return stnBflDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

	}

	@Override
	public void save(StnBflDt stnBflDt) {
		
		stnBflDtRepository.save(stnBflDt);
	}

	@Override
	public void delete(int id) {
		
		StnBflDt stnBflDt = stnBflDtRepository.findOne(id);
		stnBflDt.setDeactive(true);
		stnBflDt.setDeactiveDt(new java.util.Date());
		stnBflDtRepository.save(stnBflDt);
		
	}

	@Override
	public Long count() {
		
		return stnBflDtRepository.count();
	}

	@Override
	public StnBflDt findOne(int id) {
		
		return stnBflDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getStnBflDtList() {

		return null;
	}

	@Override
	public List<StnBflDt> findByStnBflMt(StnBflMt stnBflMt) {

		return stnBflDtRepository.findByStnBflMt(stnBflMt);
	}

	@Override
	public Page<StnBflDt> findByStnBflMt(StnBflMt stnBflMt, Integer limit,
			Integer offset, String sort, String order, String search) {
		
		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return stnBflDtRepository.findByStnBflMt(stnBflMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}



	@Override
	public List<StnBflDt> findByStnBflMtAndDeactiveOrderByIdDesc(
			StnBflMt stnBflMt, Boolean deactive) {

		return stnBflDtRepository.findByStnBflMtAndDeactiveOrderByIdDesc(stnBflMt, deactive);
	}

	
	@Override
	public StnBflDt findByUniqueId(Long uniqueId) {

		return stnBflDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<StnBflDt> findByStoneTypeAndShapeAndDeactive(
			StoneType stoneType, Shape shape, Boolean Deactive) {

		return null;
	}

	@Override
	public void delete(StoneInwardDt stnInwardDt, Integer id) {
		stoneInwardDtService.save(stnInwardDt);
		delete(id);
	}

	@Override
	public void save(List<StoneInwardDt> stnInwardDts, List<StnBflDt> stnBflDts) {
		stoneInwardDtService.saveAll(stnInwardDts);
		stnBflDtRepository.save(stnBflDts);
	}

	@Override
	public String deleteDt(Integer id,Principal principal) {
		String retVal="";
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		
		StnBflDt stnBflDt =findOne(id);
		
		
		StoneTran stoneTran=stoneTranService.RefTranIdAndTranType(stnBflDt.getId(), stnBflDt.getTranType());
		if(stoneTran !=null){
			stoneTranService.delete(stoneTran.getId());
		}
			
		StoneInwardDt stoneInwardDt=stoneInwardDtService.findOne(stnBflDt.getStnInwardDt().getId());
		if(stoneInwardDt !=null){
			if(stnBflDt.getTranType().equalsIgnoreCase("BrokenRec")){
				
				stoneInwardDt.setBrkCarat(Double.parseDouble(df.format(stoneInwardDt.getBrkCarat()+stnBflDt.getCarat())));
				stoneInwardDt.setBrkStone(stoneInwardDt.getBrkStone()+stnBflDt.getStone());   
				stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat()-stnBflDt.getCarat())));
				stoneInwardDt.setBalStone(stoneInwardDt.getBalStone()-stnBflDt.getStone());
				stoneInwardDt.setModiBy(principal.getName());
				stoneInwardDt.setModiDt(new java.util.Date());
				
			}else if(stnBflDt.getTranType().equalsIgnoreCase("BrokenLoss")){
				
				stoneInwardDt.setBrkCarat(Double.parseDouble(df.format(stoneInwardDt.getBrkCarat()+stnBflDt.getCarat())));
				stoneInwardDt.setBrkStone(stoneInwardDt.getBrkStone()+stnBflDt.getStone());   
				stoneInwardDt.setLossCarat(Double.parseDouble(df.format(stoneInwardDt.getLossCarat()-stnBflDt.getCarat())));
				stoneInwardDt.setLossStone(stoneInwardDt.getLossStone()-stnBflDt.getStone());
				stoneInwardDt.setModiBy(principal.getName());
				stoneInwardDt.setModiDt(new java.util.Date());
				
				
			}else if(stnBflDt.getTranType().equalsIgnoreCase("FallenRec")){
					stoneInwardDt.setFallCarat(Double.parseDouble(df.format(stoneInwardDt.getFallCarat()+stnBflDt.getCarat())));
					stoneInwardDt.setFallStone(stoneInwardDt.getFallStone()+stnBflDt.getStone());
					stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat()-stnBflDt.getCarat())));
					 stoneInwardDt.setBalStone(stoneInwardDt.getBalStone()-stnBflDt.getStone());
					 stoneInwardDt.setModiBy(principal.getName());
					 stoneInwardDt.setModiDt(new java.util.Date());
				
			}else if(stnBflDt.getTranType().equalsIgnoreCase("FallenLoss")){
				
				stoneInwardDt.setFallCarat(Double.parseDouble(df.format(stoneInwardDt.getFallCarat()+stnBflDt.getCarat())));
				stoneInwardDt.setFallStone(stoneInwardDt.getFallStone()+stnBflDt.getStone());
				stoneInwardDt.setLossCarat(Double.parseDouble(df.format(stoneInwardDt.getLossCarat()-stnBflDt.getCarat())));
				stoneInwardDt.setLossStone(stoneInwardDt.getLossStone()-stnBflDt.getStone());
				stoneInwardDt.setModiBy(principal.getName());
				stoneInwardDt.setModiDt(new java.util.Date());
				
				
			}
			
			stoneInwardDtService.save(stoneInwardDt);
			delete(id);
			retVal="1";
			
			
		}

		
		
			
			
			
		
		
		return retVal;
	}


}
