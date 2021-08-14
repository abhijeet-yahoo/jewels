package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.MetalLock;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMeltingRecDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalLockService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@Service
@Repository
@Transactional
public class MeltingRecDtService implements IMeltingRecDtService {
	
	@Autowired
	private IMeltingRecDtRepository meltingRecDtRepositoty;
	
	@Autowired
	private IPurityService purityService;

	
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IMeltingMtService meltingMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@Autowired
	private IMetalLockService metalLockService;
	
	@Autowired
	private IMeltingIssDtService meltingIssDtService;

	@Override
	public List<MeltingRecDt> findAll() {
		return meltingRecDtRepositoty.findAll();
	}

	@Override
	public void save(MeltingRecDt meltingRecDt) {
		meltingRecDtRepositoty.save(meltingRecDt);

	}

	@Override
	public void delete(int id) {
		MeltingRecDt meltingRecDt = meltingRecDtRepositoty.findOne(id);
		meltingRecDt.setDeactive(true);
		meltingRecDt.setDeactiveDt(new java.util.Date());
		meltingRecDtRepositoty.save(meltingRecDt);

	}

	@Override
	public MeltingRecDt findOne(int id) {
		return meltingRecDtRepositoty.findOne(id);
	}

	@Override
	public Page<MeltingRecDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return meltingRecDtRepositoty
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Long count() {
		return meltingRecDtRepositoty.count();
	}

	

	@Override
	public MeltingRecDt findByUniqueId(Long uniqueId) {
		
		return meltingRecDtRepositoty.findByUniqueId(uniqueId);
	}

	@Override
	public List<MeltingRecDt> findByMeltingMtAndDeactive(MeltingMt meltingMt,
			Boolean deactive) {
		return meltingRecDtRepositoty.findByMeltingMtAndDeactive(meltingMt, deactive);
	}

	@Override
	public String meltingRecDtSave(MeltingRecDt meltingRecDt, Integer id, String recInvNo,
			Double recPNetWt, Double recPExcpPureWt, Double pRecFMetalWt,
			Double vLoss, Double pRecTotalExpcPureWts, Principal principal,Double pMeltingBal) {
		
		String action ="added";
		String retVal ="-1";
		if(vLoss == null) {
			vLoss =0.0;
		}
		
		
		pMeltingBal = Math.round((pMeltingBal - vLoss)*1000.0)/1000.0;
		
		
		MetalTran metalTran=null;
		try {
			
			Purity tempPurityObj = purityService.findOne(meltingRecDt.getPurity().getId());
			
			MeltingMt meltingMt = meltingMtService.findByInvNoAndDeactive(recInvNo, false);
			List<MeltingIssDt> meltingIssDts = meltingIssDtService.findByMeltingMtAndDeactive(meltingMt, false);
			
			Double totVal=0.0;
			Double totWt=0.0;
		
			
			
			for (MeltingIssDt meltingIssDt : meltingIssDts) {
				
				if(meltingIssDt.getPurity().getMetal().getId().equals(tempPurityObj.getMetal().getId())) {
					totVal += Math.round((meltingIssDt.getNetWt() * meltingIssDt.getMetalRate())*100.0)/100.0;
					totWt +=  Math.round((meltingIssDt.getNetWt() * meltingIssDt.getPurityConv())*1000.0)/1000.0;
				}
				
				
				
				
			}
			
			Double avgRate = Math.round((totVal / totWt)*100.0)/100.0;
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				if(pMeltingBal < recPExcpPureWt){
					return retVal = "-5";
				}
				
				
				meltingRecDt.setCreatedBy(principal.getName());
				meltingRecDt.setCreatedDt(new java.util.Date());
				meltingRecDt.setMeltingMt(meltingMtService.findByInvNoAndDeactive(recInvNo, false));
				meltingRecDt.setUniqueId(new Date().getTime());
				meltingRecDt.setRecNetWt(recPNetWt);
				meltingRecDt.setRecExcpPureWt(recPExcpPureWt);
				meltingRecDt.setRecFreshMetalWt(pRecFMetalWt);
				
				
				if(tempPurityObj.getPurityConv() != null){
					meltingRecDt.setPurityConv(tempPurityObj.getPurityConv());
				}else{
					meltingRecDt.setPurityConv(0.0);
				}
				
				metalTran =new MetalTran();
				retVal = "1";
				
			} else {
				
				
				
				MeltingRecDt meltingRecDtOld = findOne(id);
				
				if(recPExcpPureWt-meltingRecDtOld.getRecExcpPureWt() > pMeltingBal){
					return retVal = "-5";
				}
					
					
				meltingRecDt.setId(id);
				meltingRecDt.setModiBy(principal.getName());
				meltingRecDt.setModiDt(new java.util.Date());
				meltingRecDt.setMeltingMt(meltingMtService.findByInvNoAndDeactive(recInvNo, false));
				meltingRecDt.setRecNetWt(recPNetWt);
				meltingRecDt.setRecExcpPureWt(recPExcpPureWt);
				meltingRecDt.setRecFreshMetalWt(pRecFMetalWt);
				
				action = "updated";
				metalTran  = metalTranService.findByRefTranIdAndTranTypeAndInOutFld(id, "MeltingReceive", "C");
				
				if(tempPurityObj.getPurityConv() != null){
					meltingRecDt.setPurityConv(tempPurityObj.getPurityConv());
				}else{
					meltingRecDt.setPurityConv(0.0);
				}
				
				retVal = "2";
			}
			meltingRecDt.setMetalRate(Math.round((avgRate * tempPurityObj.getPurityConv())*100.0)/100.0);
			save(meltingRecDt);
			
			MeltingRecDt meltingRec = findByUniqueId(meltingRecDt.getUniqueId());
			
			if(meltingRec.getRecFreshMetalWt() > 0){
				
			
//				metalTran.setDepartment(departmentService.findByName("Melting"));
//				metalTran.setDeptLocation(departmentService.findByName("Central"));
				
				metalTran.setDepartment(departmentService.findByName("Melting"));
				metalTran.setDeptLocation(meltingRecDt.getDeptLocation());
				
				metalTran.setRefTranId(meltingRec.getId());
				metalTran.setPurity(meltingRec.getPurity());
				metalTran.setColor(meltingRec.getColor());
				metalTran.setMetalWt(meltingRec.getRecNetWt());
				metalTran.setBalance(meltingRec.getRecNetWt());
				metalTran.setInOutFld("C");
				if(meltingRec.getPurity().getPurityConv() != null){
					metalTran.setPurityConv(tempPurityObj.getPurityConv());
				}else{
					metalTran.setPurityConv(0.0);
				}
				metalTran.setTranType("MeltingReceive");
				metalTran.setCreatedBy(meltingRec.getCreatedBy());
				metalTran.setCreatedDt(meltingRec.getCreatedDt());
				metalTran.setDeactive(false);
				metalTran.setTranDate(new Date());
				metalTran.setMetalRate(Math.round((avgRate * tempPurityObj.getPurityConv())*100.0)/100.0);
				
				
				if(tempPurityObj.getPure().equals(true)) {
					
					MetalLock metalLock =metalLockService.findByMetalLockDtAndMetalAndDeactive(new Date(),tempPurityObj.getMetal(), false);
					if(metalLock !=null) {
						metalTran.setMetalRate(metalLock.getMetalLockRate());
					}
					
					
					
				}
				
				metalTranService.save(metalTran);
			}
			
			retVal=retVal+","+action;
		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return retVal;
	}


}
