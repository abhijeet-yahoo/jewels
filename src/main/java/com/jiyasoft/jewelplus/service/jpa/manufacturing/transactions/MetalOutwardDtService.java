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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalOutwardDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@Service
@Repository
@Transactional
public class MetalOutwardDtService implements IMetalOutwardDtService {

	@Autowired
	IMetalOutwardDtRepository metalOutwardDtRepository;
	
	@Autowired
	private IMetalOutwardMtService metalOutwardMtService;
	
	@Autowired
	private IMetalTranService metalTranService;


	@Autowired
	private IPurityService purityService;


	@Override
	public List<MetalOutwardDt> findAll() {

		return metalOutwardDtRepository.findAll();
	}

	@Override
	public Page<MetalOutwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return metalOutwardDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(MetalOutwardDt metalOutwardDt) {
		metalOutwardDtRepository.save(metalOutwardDt);

	}

	@Override
	public void delete(int id) {
		MetalOutwardDt metalOutwardDt = metalOutwardDtRepository.findOne(id);
		metalOutwardDt.setDeactive(true);
		metalOutwardDt.setDeactiveDt(new java.util.Date());
		metalOutwardDtRepository.save(metalOutwardDt);
	}

	@Override
	public Long count() {

		return metalOutwardDtRepository.count();
	}

	@Override
	public MetalOutwardDt findOne(int id) {

		return metalOutwardDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getMetalOutwardDtList() {

		return null;
	}

	@Override
	public Page<MetalOutwardDt> findByMetalOutwardMt(
			MetalOutwardMt metalOutwardMt, Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return metalOutwardDtRepository.findByMetalOutwardMt(metalOutwardMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public MetalOutwardDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return metalOutwardDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<MetalOutwardDt> findByMetalOutwardMt(
			MetalOutwardMt metalOutwardMt) {

		return metalOutwardDtRepository.findByMetalOutwardMt(metalOutwardMt);
	}

	@Override
	public String metalOutwardSave(MetalOutwardDt metalOutwardDt, Integer id,
			String pInvNo, Double metalWt, Double prevMetalWt,
			Principal principal) {
		
		String action ="added";
		String retVal="-1";
		
		
		try {
			MetalTran metalTran = null;
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Double balance = metalTranService.getStockBalance(metalOutwardDt.getPurity().getId(), metalOutwardDt.getColor().getId(),metalOutwardDt.getDepartment().getId());
				
				if (balance == null) {
					balance = 0.0;
					
				}

				if (balance < metalWt) {
					return "Error : Stock Not Available (Available In Stock : "+balance+")";
				}

		
				metalOutwardDt.setCreatedBy(principal.getName());
				metalOutwardDt.setCreatedDt(new java.util.Date());
				metalOutwardDt.setMetalOutwardMt(metalOutwardMtService.findByInvNo(pInvNo));
				metalOutwardDt.setUniqueId(new Date().getTime());
				metalOutwardDt.setPurityConv(purityService.findOne(metalOutwardDt.getPurity().getId()).getPurityConv());
				metalOutwardDt.setBalance(Math.round((metalOutwardDt.getInvWt()*purityService.findOne(metalOutwardDt.getPurity().getId()).getPurityConv())*1000.0)/1000.0);
				
				MetalOutwardMt metalOutwardMt = metalOutwardMtService.findByInvNo(pInvNo);
				if (metalOutwardMt.getDeactive() == false) {
					metalOutwardDt.setDeactive(false);
				}

				
			
				
				save(metalOutwardDt);


				metalTran = new MetalTran();
				retVal = "1";
				
			} else {
				
				
				MetalOutwardDt metalOutwardDt2 = findOne(id);

				Double balance = metalTranService.getStockBalance(metalOutwardDt2.getPurity().getId(), metalOutwardDt2.getColor().getId(),metalOutwardDt2.getDepartment().getId());

				
				
				Double difference = metalWt - prevMetalWt;
				if (balance == null) {
					balance = 0.0;
					
				}

				if (balance < difference) {
					return "Error : Stock Not Available (Available In Stock : "+balance+")";
				}
				
				
				metalOutwardDt2.setInvWt(metalOutwardDt.getInvWt());
				metalOutwardDt2.setMetalWt(metalOutwardDt.getMetalWt());
				metalOutwardDt2.setAmount(metalOutwardDt.getAmount());
				metalOutwardDt2.setRate(metalOutwardDt.getRate());
				metalOutwardDt2.setModiBy(principal.getName());
				metalOutwardDt2.setModiDt(new java.util.Date());
				metalOutwardDt2.setBalance(Math.round((metalOutwardDt.getInvWt()*purityService.findOne(metalOutwardDt2.getPurity().getId()).getPurityConv())*1000.0)/1000.0);
				
		
				save(metalOutwardDt2);	
			

				metalTran = metalTranService.RefTranIdAndTranType(id, "OutWard");
				action = "updated";	
			}

			
			MetalOutwardDt metalOutward=null;
			if(action == "added"){
			metalOutward = findByUniqueId(metalOutwardDt.getUniqueId());
			metalTran.setCreatedBy(metalOutward.getCreatedBy());
			metalTran.setCreatedDt(metalOutward.getCreatedDt());
			
			}else{
				metalOutward = findOne(id);
			
				metalTran.setModiBy(principal.getName());
				metalTran.setModiDt(new java.util.Date());
				
			}
			
			
			Double mtlRate=metalTranService.getMetalRate(metalOutward.getPurity().getId(), metalOutward.getColor().getId(), metalOutward.getDepartment().getId(), metalOutward.getMetalWt());
			
			//metalTran.setTranDate(new java.util.Date());
			metalTran.setColor(metalOutward.getColor());
			metalTran.setPurity(metalOutward.getPurity());
			metalTran.setInOutFld("D");
			metalTran.setBagMt(null);
			metalTran.setBalance(metalOutward.getMetalWt() * -1);
			metalTran.setMetalWt(metalOutward.getMetalWt());
			metalTran.setDeptLocation(metalOutward.getDepartment());
			metalTran.setPurityConv(metalOutward.getPurityConv());
			metalTran.setRefTranId(metalOutward.getId());
			metalTran.setTranType("OutWard");
			metalTran.setRemark("");
			metalTran.setDepartment(null);
			metalTran.setTranDate(metalOutward.getMetalOutwardMt().getInvDate());
			
			metalTran.setMetalRate(mtlRate);
			
			metalTranService.save(metalTran);
			
			metalOutward.setRate(mtlRate);
			save(metalOutward);
			retVal=retVal+","+action;
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public List<MetalOutwardDt> findByMetalOutwardMtAndDeactive(
			MetalOutwardMt metalOutwardMt, Boolean deactive) {

		return metalOutwardDtRepository.findByMetalOutwardMtAndDeactive(metalOutwardMt,deactive);
	}

	@Override
	public String metalOutDelete(Integer id, Principal principal) {
		
		String retVal ="-1";
		
		try {
			
			MetalOutwardDt metalOutwardDt = findOne(id);
			if(principal.getName().equalsIgnoreCase("Administrator")){
					retVal = "1";
					
			}else{
			
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					MetalOutwardMt metalOutwardMt = metalOutwardMtService.findOne(metalOutwardDt.getMetalOutwardMt().getId());
					Date cDate = metalOutwardMt.getInvDate();
					String dbDate = dateFormat.format(cDate);
					
					Date date = new Date();
					String curDate = dateFormat.format(date);

					if(dbDate.equals(curDate)){
						
							retVal = "1";
					}
		
			}
			
			if(retVal != "-1"){
			
			delete(id);
			MetalTran metalTran = metalTranService.RefTranIdAndTranType(id, "Outward");
			metalTranService.delete(metalTran.getId());
			}
			
			retVal ="1";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

}
