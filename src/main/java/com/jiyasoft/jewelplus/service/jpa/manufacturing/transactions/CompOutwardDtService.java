package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICompOutwardDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;

@Service
@Repository
@Transactional
public class CompOutwardDtService implements ICompOutwardDtService {

	@Autowired
	ICompOutwardDtRepository compOutwardDtRepository;
	
	@Autowired
	private ICompOutwardMtService compOutwardMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ICompTranService compTranService;

	@Override
	public List<CompOutwardDt> findAll() {
		return compOutwardDtRepository.findAll();
	}

	@Override
	public Page<CompOutwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return compOutwardDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(CompOutwardDt compOutwardDt) {
		compOutwardDtRepository.save(compOutwardDt);
	}

	@Override
	public void delete(int id) {
		CompOutwardDt compOutwardDt = compOutwardDtRepository.findOne(id);
		compOutwardDt.setDeactive(true);
		compOutwardDt.setDeactiveDt(new java.util.Date());
		compOutwardDtRepository.save(compOutwardDt);

	}

	@Override
	public Long count() {
		return compOutwardDtRepository.count();
	}

	@Override
	public CompOutwardDt findOne(int id) {
		return compOutwardDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getCompOutwardDtList() {
		return null;
	}

	@Override
	public Page<CompOutwardDt> findByCompOutwardMt(CompOutwardMt compOutwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return compOutwardDtRepository.findByCompOutwardMt(compOutwardMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

	}

	@Override
	public CompOutwardDt findByUniqueId(Long uniqueId) {
		return compOutwardDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<CompOutwardDt> findByCompOutwardMt(CompOutwardMt compOutwardMt) {
		return compOutwardDtRepository.findByCompOutwardMt(compOutwardMt);
	}

	@Override
	public String compOutwardDtSave(CompOutwardDt compOutwardDt, Integer id,
			String pInvNo, Double metalWt, Double prevMetalWt,
			Principal principal) {
		
		String action = "added";
		String retVal="-1";
			
		
		try {
			
			CompTran compTran = null;


		
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Double tempBal = compTranService.getStockBalance(compOutwardDt.getPurity().getId(),compOutwardDt.getColor().getId(),compOutwardDt.getDepartment().getId(), 
						compOutwardDt.getComponent().getId());
				
				
				
				if (tempBal == null) {
					tempBal = 0.0;
					
				}

				if (tempBal < metalWt) {
					return "Error : Stock Not Available (Available In Stock : "+tempBal+")";
				}
				
				compOutwardDt.setCreatedBy(principal.getName());
				compOutwardDt.setCreatedDt(new java.util.Date());
				compOutwardDt.setCompOutwardMt(compOutwardMtService.findByInvNo(pInvNo));
				compOutwardDt.setUniqueId(new Date().getTime());
				compOutwardDt.setPurityConv(purityService.findOne(compOutwardDt.getPurity().getId()).getPurityConv());
				compOutwardDt.setBalance(compOutwardDt.getMetalWt());
				
				

				save(compOutwardDt);
				compTran = new CompTran();
				retVal ="1";

				} else {
				
					CompOutwardDt compOutwardDt2 =findOne(id);	
					compTran = compTranService.RefTranIdAndTranType(compOutwardDt2.getId(),"OutWard");
					
					Double tempBal = compTranService.getStockBalance(compOutwardDt2.getPurity().getId(),compOutwardDt2.getColor().getId(),compOutwardDt2.getDepartment().getId(), 
							compOutwardDt2.getComponent().getId());
					
					Double difference = metalWt - prevMetalWt;

					if (tempBal < difference) {
						return "Error : Stock Not Available (Available In Stock : "+tempBal+")";
					}
					
					
				compOutwardDt2.setModiBy(principal.getName());
				compOutwardDt2.setModiDt(new java.util.Date());
				compOutwardDt2.setInvWt(compOutwardDt.getInvWt());
				compOutwardDt2.setQty(compOutwardDt.getQty());
				compOutwardDt2.setMetalWt(compOutwardDt.getMetalWt());
				compOutwardDt2.setRate(compOutwardDt.getRate());
				compOutwardDt2.setAmount(compOutwardDt.getAmount());
				compOutwardDt2.setBalance(compOutwardDt.getMetalWt());
				compOutwardDt2.setPerGramRate(compOutwardDt.getPerGramRate());
				save(compOutwardDt2);

				
				action = "updated";	
				
			}

			
			
			
			CompOutwardDt compOutward=null;
			
			if(action == "added"){
				compOutward = findByUniqueId(compOutwardDt.getUniqueId());	
				compTran.setCreatedBy(compOutward.getCreatedBy());
				compTran.setCreatedDt(compOutward.getCreatedDt());
				compTran.setTrandate(compOutward.getCompOutwardMt().getInvDate());
			}else{
				compOutward=findOne(id);
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			 

			compTran.setPurity(compOutward.getPurity());
			compTran.setInOutFld("D");
			compTran.setBagMt(null);
			compTran.setBalance(compOutward.getMetalWt() * -1);
			compTran.setMetalWt(compOutward.getMetalWt());
			compTran.setDeptLocation(compOutward.getDepartment());
			compTran.setPurityConv(compOutward.getPurityConv());
			compTran.setRefTranId(compOutward.getId());
			compTran.setTranType("OutWard");
			compTran.setRemark("");
			compTran.setDepartment(null);
			compTran.setPcs(compOutward.getQty());
			compTran.setComponent(compOutward.getComponent());
			
			compTran.setColor(compOutward.getColor());
			compTran.setMetalRate(compOutward.getRate());
			compTranService.save(compTran);
			
			retVal=retVal+","+action;
									
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public List<CompOutwardDt> findByCompOutwardMtAndDeactive(
			CompOutwardMt compOutwardMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return compOutwardDtRepository.findByCompOutwardMtAndDeactive(compOutwardMt, deactive);
	}

	@Override
	public String compOutwardDtDelete(Integer id, Principal principal) {
		
		String retVal ="-1";
		
		try {
			
			CompOutwardDt compOutwardDt = findOne(id);
			if(principal.getName().equalsIgnoreCase("Administrator")){
					retVal = "1";
					
			}else{
			
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					CompOutwardMt compOutwardMt = compOutwardMtService.findOne(compOutwardDt.getCompOutwardMt().getId());
					Date cDate = compOutwardMt.getInvDate();
					String dbDate = dateFormat.format(cDate);
					
					Date date = new Date();
					String curDate = dateFormat.format(date);

					if(dbDate.equals(curDate)){
						
							retVal = "1";
					}
				}
			
			if(retVal != "-1"){
			delete(id);
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "Outward");
			for (CompTran comTran : compTran) {
				compTranService.delete(comTran.getId());
			}
		}
			retVal ="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

}
