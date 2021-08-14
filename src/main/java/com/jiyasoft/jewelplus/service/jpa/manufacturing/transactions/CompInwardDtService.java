package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompInwardDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICompInwardDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class CompInwardDtService implements ICompInwardDtService {

	@Autowired
	ICompInwardDtRepository compInwardDtRepository;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private ICompInwardMtService compInwardMtService;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CompInwardDt> findAll() {
		
		QCompInwardDt qCompInwardDt = QCompInwardDt.compInwardDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<CompInwardDt> compInwardList = null;
		
		compInwardList = query.from(qCompInwardDt).
						where(qCompInwardDt.deactive.eq(false))
						.orderBy(qCompInwardDt.component.name.asc(),qCompInwardDt.purity.name.asc(),qCompInwardDt.color.name.asc())
						.list(qCompInwardDt);
		
		return compInwardList;
	}

	@Override
	public Page<CompInwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return compInwardDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(CompInwardDt compInwardDt) {
		compInwardDtRepository.save(compInwardDt);

	}

	@Override
	public void delete(int id) {
		CompInwardDt compInwardDt = compInwardDtRepository.findOne(id);
		compInwardDt.setDeactive(true);
		compInwardDt.setDeactiveDt(new java.util.Date());
		compInwardDtRepository.save(compInwardDt);

	}

	@Override
	public Long count() {
		return compInwardDtRepository.count();
	}

	@Override
	public CompInwardDt findOne(int id) {
		return compInwardDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getCompInwardDtList() {

		return null;
	}

	@Override
	public Page<CompInwardDt> findByCompInwardMt(CompInwardMt compInwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search) {
		
		if (limit == null) {
			limit = 10000;
		}
		
		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return compInwardDtRepository.findByCompInwardMt(compInwardMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public CompInwardDt findByUniqueId(Long uniqueId) {
		return compInwardDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<CompInwardDt> findByCompInwardMt(CompInwardMt compInwardMt) {
		return compInwardDtRepository.findByCompInwardMt(compInwardMt);
	}


	@Override
	public List<CompInwardDt> findByComponentAndPurityAndColorAndDeactive(
			Component component, Purity purity, Color color, Boolean deactive) {
		return compInwardDtRepository.findByComponentAndPurityAndColorAndDeactive(component, purity, color,deactive);
	}

	@Override
	public List<CompInwardDt> findByCompInwardMtAndDeactive(
			CompInwardMt compInwardMt, Boolean deactive) {
		return compInwardDtRepository.findByCompInwardMtAndDeactive(compInwardMt, deactive);
	}

	@Override
	public String compInwardDtSave(CompInwardDt compInwardDt, Integer id,
			String pInvNo, Integer vPurityId, Double invWtt, Principal principal) {
		
		String action = "added";
		String retVal="-1";
				
		try {
			
					
			CompTran compTran = null;

			if (id == null || id.equals("") || (id != null && id == 0)) {
				compInwardDt.setCreatedBy(principal.getName());
				compInwardDt.setCreatedDt(new java.util.Date());
				compInwardDt.setCompInwardMt(compInwardMtService.findByInvNoAndDeactive(pInvNo, false));
				compInwardDt.setUniqueId(new Date().getTime());
			//	compInwardDt.setDepartment(departmentService.findByName("Component"));
				compInwardDt.setPurityConv(purityService.findOne(vPurityId).getPurityConv());
				compInwardDt.setBalance(compInwardDt.getMetalWt());
				compTran = new CompTran();
				retVal = "1";
			} else {
				
				compInwardDt.setModiBy(principal.getName());
				compInwardDt.setModiDt(new java.util.Date());
				compInwardDt.setPurityConv(purityService.findOne(vPurityId).getPurityConv());
				compInwardDt.setCompInwardMt(compInwardMtService.findByInvNoAndDeactive(pInvNo, false));
				compInwardDt.setBalance(compInwardDt.getMetalWt());
			//	compInwardDt.setDepartment(departmentService.findByName("Component"));

				compTran = compTranService.RefTranIdAndTranType(id, "InWard");
				
				action = "updated";	
				
				
			}

			
			save(compInwardDt);
			
			CompInwardDt compInward = null;
			if(action == "added"){
				compInward=findByUniqueId(compInwardDt.getUniqueId());
				compTran.setCreatedBy(compInward.getCreatedBy());
				compTran.setCreatedDt(compInward.getCreatedDt());
				compTran.setTrandate(compInward.getCompInwardMt().getInvDate());
			}else{
				compInward=findOne(id);
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			

			compTran.setColor(compInward.getColor());
			compTran.setPurity(compInward.getPurity());
			compTran.setInOutFld("C");
			compTran.setBagMt(null);
			compTran.setBalance(compInward.getMetalWt());
			compTran.setMetalWt(compInward.getMetalWt());
			compTran.setDeptLocation(compInward.getDepartment());
			compTran.setDepartment(compInward.getDepartment());
			compTran.setPurityConv(compInward.getPurityConv());
			compTran.setRefTranId(compInward.getId());
			compTran.setTranType("InWard");
			compTran.setRemark("");
			compTran.setDepartment(null);
			compTran.setComponent(compInward.getComponent());
			
			compTran.setPcs(compInward.getQty());
			compTran.setBalancePcs(compInward.getQty());
			compTran.setMetalRate(compInward.getRate());
		

			compTranService.save(compTran);
			
			
			retVal=retVal+","+action;
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String compInwDtDelete(Integer id, Principal principal) {
		
		String retVal ="-1";
		
		try {
			
			CompInwardDt compInwardDt = findOne(id);
			if(principal.getName().equalsIgnoreCase("Administrator")){
					retVal = "1";
					
			}else{
			
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				CompInwardMt compInwardMt = compInwardMtService
						.findOne(compInwardDt.getCompInwardMt().getId());
				Date cDate = compInwardMt.getCreatedDt();
				String dbDate = dateFormat.format(cDate);

				Date date = new Date();
				String curDate = dateFormat.format(date);

				if (dbDate.equals(curDate)) {

					retVal = "1";

				}

			}
			
			if(retVal != "-1"){
			delete(id);
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "Inward");
			for (CompTran comTran : compTran) {
				compTranService.delete(comTran.getId());
			}
			}
			retVal ="1";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
