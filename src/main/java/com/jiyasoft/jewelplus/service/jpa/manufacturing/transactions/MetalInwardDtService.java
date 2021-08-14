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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMetalInwardDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalInwardDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalInwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MetalInwardDtService implements IMetalInwardDtService {

	@Autowired
	IMetalInwardDtRepository metalInwardDtRepository;
	
	@Autowired
	private IMetalInwardMtService metalInwardMtService;
	
	@Autowired
	private IMetalTranService metalTranService;


	@Autowired
	private IPurityService purityService;


	@Autowired
	private IDepartmentService departmentService;

	@Override
	public List<MetalInwardDt> findAll() {
		return metalInwardDtRepository.findAll();
	}

	@Override
	public Page<MetalInwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return metalInwardDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(MetalInwardDt metalInwardDt) {
		metalInwardDtRepository.save(metalInwardDt);
	}

	@Override
	public void delete(int id) {
		MetalInwardDt metalInwardDt = metalInwardDtRepository.findOne(id);
		metalInwardDt.setDeactive(true);
		metalInwardDt.setDeactiveDt(new java.util.Date());
		metalInwardDtRepository.save(metalInwardDt);

	}

	@Override
	public Long count() {
		return metalInwardDtRepository.count();

	}

	@Override
	public MetalInwardDt findOne(int id) {
		return metalInwardDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getMetalInwardDtList() {

		return null;
	}

	@Override
	public Page<MetalInwardDt> findByMetalInwardMt(MetalInwardMt metalInwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return metalInwardDtRepository.findByMetalInwardMt(metalInwardMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

	}

	@Override
	public MetalInwardDt findByUniqueId(Long uniqueId) {

		return metalInwardDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<MetalInwardDt> findByMetalInwardMt(MetalInwardMt metalInwardMt) {

		return metalInwardDtRepository.findByMetalInwardMt(metalInwardMt);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QMetalInwardDt qMetalInwardDt = QMetalInwardDt.metalInwardDt;
		BooleanExpression expression = qMetalInwardDt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qMetalInwardDt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qMetalInwardDt.deactive.eq(false).and(
						qMetalInwardDt.metal.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qMetalInwardDt.metal.name.like(colValue + "%");
			}
		}

		return metalInwardDtRepository.count(expression);
	}

	@Override
	public MetalInwardDt findByMetalInwardMtAndMetal(
			MetalInwardMt metalInwardMt, Metal metal) {

		return metalInwardDtRepository.findByMetalInwardMtAndMetal(
				metalInwardMt, metal);
	}

	@Override
	public List<MetalInwardDt> findByMetalInwardMtAndDeactive(MetalInwardMt metalInwardMt, Boolean deactive) {
		return metalInwardDtRepository.findByMetalInwardMtAndDeactive(metalInwardMt, deactive);
	}

	@Override
	public List<MetalInwardDt> findByMetalAndDeactive(Metal metal,
			Boolean deactive) {
		
		return metalInwardDtRepository.findByMetalAndDeactive(metal, deactive);
	}

	@Override
	public String metalInwardSave(MetalInwardDt metalInwardDt, Integer id,
			String pInvNo, Integer purityyId, Double invWtt, Principal principal) {
		
		String action = "added";
		String retVal="-1";
		Date aDate = null;
		
		try {
			
			
			
			
			MetalTran metalTran = null;

			if (id == null || id.equals("") || (id != null && id == 0)) {
				aDate = new java.util.Date();
				metalInwardDt.setCreatedBy(principal.getName());
				metalInwardDt.setCreatedDt(new java.util.Date());
				metalInwardDt.setMetalInwardMt(metalInwardMtService.findByInvNoAndDeactive(pInvNo, false));
				metalInwardDt.setUniqueId(aDate.getTime());
				metalInwardDt.setDepartment(departmentService.findByName("Central"));
				metalInwardDt.setPurityConv(purityService.findOne(purityyId).getPurityConv());
				metalInwardDt.setBalance(Math.round((invWtt*purityService.findOne(purityyId).getPurityConv())*1000.0)/1000.0);
				metalTran = new MetalTran();
				retVal = "1"; 

			} else {
				metalInwardDt.setId(id);
				metalInwardDt.setModiBy(principal.getName());
				metalInwardDt.setModiDt(new java.util.Date());
				metalInwardDt.setMetalInwardMt(metalInwardMtService.findByInvNoAndDeactive(pInvNo, false));
				metalInwardDt.setDepartment(departmentService.findByName("Central"));
				metalInwardDt.setPurityConv(purityService.findOne(purityyId).getPurityConv());
				metalInwardDt.setBalance(Math.round((invWtt*purityService.findOne(purityyId).getPurityConv())*1000.0)/1000.0);
				metalTran = metalTranService.RefTranIdAndTranType(id, "Inward");
				action = "updated";	

			}

			metalInwardDt.setRemark(metalInwardDt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			save(metalInwardDt);
			
			MetalInwardDt metalInward= null;

			if(action == "added"){
			metalInward = findByUniqueId(metalInwardDt.getUniqueId());
			metalTran.setCreatedBy(metalInward.getCreatedBy());
			metalTran.setCreatedDt(metalInward.getCreatedDt());
			}else{
				metalInward=findOne(id);
				metalTran.setModiBy(principal.getName());
				metalTran.setModiDt(new java.util.Date());
			}
			
			//metalTran.setTranDate(new java.util.Date());
			metalTran.setColor(metalInward.getColor());
			metalTran.setPurity(metalInward.getPurity());
			metalTran.setInOutFld("C");
			metalTran.setBalance(metalInward.getMetalWt());
			metalTran.setMetalWt(metalInward.getMetalWt());
			metalTran.setDeptLocation(metalInward.getDepartment());
			metalTran.setPurityConv(metalInward.getPurityConv());
			metalTran.setRefTranId(metalInward.getId());
			metalTran.setTranType("InWard");
			metalTran.setTranDate(metalInwardDt.getMetalInwardMt().getInvDate());
			metalTran.setMetalRate(metalInward.getRate());
			
			metalTranService.save(metalTran);
			
			
			retVal=retVal+","+action;
			
						
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String metalInwardDtDelete(Integer id, Principal principal) {

		String retVal ="-1";
		
		try {
			
			MetalInwardDt metalInwardDt = findOne(id);
			if(principal.getName().equalsIgnoreCase("Administrator")){
					retVal = "1";
					
			}else{
			
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					MetalInwardMt metalInwardMt = metalInwardMtService.findOne(metalInwardDt.getMetalInwardMt().getId());
					Date cDate = metalInwardMt.getInvDate();
					String dbDate = dateFormat.format(cDate);
					
					Date date = new Date();
					String curDate = dateFormat.format(date);

					if(dbDate.equals(curDate)){
						
							retVal = "1";
					}
				}
			
			if(retVal != "-1"){
			
			delete(id);

			Integer metalTranId = null;
			List<MetalTran> metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "Inward",false);
			for (MetalTran metTran : metalTran) {
				metalTranId = metTran.getId();
				metalTranService.delete(metalTranId);
			}
			
		}
	}catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	
	

	@Override
	public Page<MetalInwardDt> balanceInvoice(Integer limit, Integer offset,
			String sort, String order, String search,Metal metal) {

		QMetalInwardDt qMetalInwardDt = QMetalInwardDt.metalInwardDt;
		BooleanExpression expression=null;
		if (search == null) {
				expression = qMetalInwardDt.deactive.eq(false).and(qMetalInwardDt.metal.eq(metal)).and(qMetalInwardDt.balance.gt(0));
			}else{
				expression = qMetalInwardDt.deactive.eq(false).and(qMetalInwardDt.metal.eq(metal)).and(qMetalInwardDt.balance.gt(0))
						.and(qMetalInwardDt.metalInwardMt.invNo.like("%" +search+"%"));
			}
		
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} 
	
		Page<MetalInwardDt> metalInwardDtList =(Page<MetalInwardDt>) metalInwardDtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		

	
		return metalInwardDtList;
	}



}
