package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMakingMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QConsigMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMakingMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMakingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMakingRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MakingMtService implements IMakingMtService{

	@Autowired
	private IMakingMtRepository makingMtRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IMakingRecDtService makingRecDtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IPurityService purityService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Override
	public List<MakingMt> findAll() {
		return makingMtRepository.findAll();
	}

	@Override
	public Page<MakingMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return makingMtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(MakingMt makingMt) {
		makingMtRepository.save(makingMt);
		
	}

	@Override
	public void delete(int id) {
		MakingMt makingMt = makingMtRepository.findOne(id);
		makingMt.setDeactive(true);
		makingMt.setDeactiveDt(new java.util.Date());
		makingMtRepository.save(makingMt);
		
	}

	@Override
	public Long count() {
		return makingMtRepository.count();
	}

	@Override
	public MakingMt findOne(int id) {
		return makingMtRepository.findOne(id);
	}

	
	
	
	@Override
	public Map<Integer, String> getMakingMtList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MakingMt> findActiveMakingMts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QMakingMt qMakingMt = QMakingMt.makingMt;
		BooleanExpression expression = qMakingMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qMakingMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qMakingMt.deactive.eq(false).and(
						qMakingMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qMakingMt.invNo.like(colValue + "%");
			}
		}

		return makingMtRepository.count(expression);
	}

	@Override
	public Page<MakingMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QMakingMt qMakingMt = QMakingMt.makingMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qMakingMt.deactive.eq(false);
			} else {
				expression = qMakingMt.deactive.eq(false).and(
						qMakingMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qMakingMt.invNo.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<MakingMt> makingMtList = (Page<MakingMt>) makingMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return makingMtList;
	}

	@Override
	public MakingMt findByUniqueId(Long uniqueId) {
		return makingMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Long maxBySrNo() {
		QMakingMt qMakingMt = QMakingMt.makingMt;
		
		JPAQuery query = new JPAQuery(entityManager);
		Long retVal = -1l;
		Calendar date = new GregorianCalendar();
		
		List<Long> maxSrno = query
				.from(qMakingMt)
				.where(qMakingMt.deactive.eq(false).and(
						qMakingMt.createdDt.year().eq(date.get(Calendar.YEAR)))).list(qMakingMt.srno.max());

		for (Long srno : maxSrno) {
			retVal = srno;
		}
	
		return retVal;
	}

	@Override
	public MakingMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		
		return makingMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public String saveMakingMt(MakingMt makingMt,Integer id,Principal principal,Integer prevKt,Integer prevColor,Double prevFreshMetalWt,String vTranDate) throws ParseException {
		
		String retVal="-5";
		String action = "added";
		
		Integer purityId = makingMt.getPurity().getId();
		Integer colorId = makingMt.getColor().getId();
		Integer locationId = departmentService.findByName("Central").getId();
		
		Double mtlRates = metalTranService.getMetalRate(purityId,colorId,locationId, makingMt.getFreshIssWt());
		mtlRates=mtlRates != null ? mtlRates :0.0;
		Double freshBalance = metalTranService.getStockBalance(purityId, colorId, locationId);
		MetalTran metalTran = null;
		Purity tempPurityObj = purityService.findOne(purityId);
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date dates = originalFormat.parse(vTranDate);
			
			makingMt.setInvDate(dates);
			
			}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			if(freshBalance <=0 || freshBalance < makingMt.getFreshIssWt()){
				retVal = "-1";
				return retVal;
			}
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			Long vSrNo = maxBySrNo();
			if(vSrNo == null){
				vSrNo =0L;
			}
			makingMt.setSrno(vSrNo+1);
			makingMt.setInvNo("MKG"+"-"+vSrNo+1+"-"+vYear);
			
			if(makingMt.getInvDate() == null){
				makingMt.setInvDate(new Date());
			}
			
			makingMt.setCreatedBy(principal.getName());
			makingMt.setCreatedDt(new java.util.Date());
			makingMt.setUniqueId(new java.util.Date().getTime());
			/*makingMt.setLoss(vLoss);*/
			makingMt.setPurityConv(tempPurityObj.getPurityConv());
			metalTran = new MetalTran();
			
		} else {
			
			if(makingMt.getPurity().getId().equals(prevKt) && makingMt.getColor().getId().equals(prevColor)){
				
				
				
					Double freshmetalDiff = makingMt.getFreshIssWt() - prevFreshMetalWt;
					
					if(freshBalance <=0 || freshBalance < freshmetalDiff){
						retVal = "-3";
						return retVal;
					}
					
			
			}else{
				
				if(freshBalance <=0 || freshBalance < makingMt.getFreshIssWt()){
					retVal = "-3";
					return retVal;
				}
				
	
			}
			
			makingMt.setModiBy(principal.getName());
			makingMt.setModiDt(new java.util.Date());
			/*makingMt.setLoss(vLoss);*/
			makingMt.setPurityConv(tempPurityObj.getPurityConv());

			makingMt.setId(id);

			action = "updated";
			/*retVal = "/jewels/manufacturing/transactions/makingMt.html?success=true";*/
		}

		makingMt.setMetalRate(mtlRates != null ? mtlRates :0.0);
		save(makingMt);
	
		
	/*	if (action.equals("added")) {
			MakingMt makingMtNew = findByUniqueId(makingMt.getUniqueId());
			Integer makingId = makingMtNew.getId();
			retVal  = "/jewels/manufacturing/transactions/makingMt/edit/"+makingId+".html?success=true";
		}*/
		
		
		MakingMt makMt = findByUniqueId(makingMt.getUniqueId());
		
		Boolean check ;
		
		for(int i=1;i<3; i++){
			
		check = false;
		
		if(i == 1){
			
			//Double mtlRate = metalTranService.getMetalRate(purityId,colorId,locationId, makMt.getFreshIssWt());
			
			
				metalTran = metalTranService.findByRefTranIdAndTranTypeAndSrNo(makMt.getId(), "Making", 1);
				if (metalTran != null) {
					if (makMt.getFreshIssWt() > 0) {
						metalTran.setTranDate(new java.util.Date());
						metalTran.setDeptLocation(departmentService.findByName("Central"));
						metalTran.setDepartment(departmentService.findByName("Making"));
						metalTran.setInOutFld("D");
						metalTran.setPurity(makMt.getPurity());
						metalTran.setColor(makMt.getColor());
						metalTran.setMetalWt(makMt.getFreshIssWt());
						metalTran.setBalance(makMt.getFreshIssWt() * -1);
						if (makMt.getPurity() != null) {
							metalTran.setPurityConv(tempPurityObj.getPurityConv());
						} else {
							metalTran.setPurityConv(null);
						}
						metalTran.setTranType("Making");
						metalTran.setRefTranId(makMt.getId());
						metalTran.setSrNo(1);
						metalTran.setMetalRate(mtlRates);
						check = true;
		
					} else {
						metalTranService.delete(metalTran.getId());
					}
				} else {
					if (makMt.getFreshIssWt() > 0) {
						metalTran = new MetalTran();
						metalTran.setTranDate(new java.util.Date());
						metalTran.setDeptLocation(departmentService.findByName("Central"));
						metalTran.setDepartment(departmentService.findByName("Making"));
						metalTran.setInOutFld("D");
		
						metalTran.setPurity(makMt.getPurity());
						metalTran.setColor(makMt.getColor());
						metalTran.setMetalWt(makMt.getFreshIssWt());
						metalTran.setBalance(makMt.getFreshIssWt() * -1);
						if (makMt.getPurity() != null) {
							metalTran.setPurityConv(tempPurityObj.getPurityConv());
						} else {
							metalTran.setPurityConv(null);
						}
						metalTran.setTranType("Making");
						metalTran.setRefTranId(makMt.getId());
						metalTran.setSrNo(1);
						metalTran.setMetalRate(mtlRates);
						check = true;
					}
				}
		
		}
		
	
		
		
		
		//return Metal
		
		if (i == 2) {
			
			//Double mtlRate = metalTranService.getMetalRate(purityId,colorId,locationId, makMt.getReturnMetal());
			//mtlRate =	mtlRate != null ? mtlRate :0.0;
			metalTran = metalTranService.findByRefTranIdAndTranTypeAndSrNo(makMt.getId(), "Making", 2);
			if (metalTran != null) {
				if (makMt.getReturnMetal() > 0){
					metalTran.setTranDate(new java.util.Date());
					metalTran.setDeptLocation(departmentService.findByName("Central"));
					metalTran.setDepartment(departmentService.findByName("Making"));
					metalTran.setInOutFld("C");

					metalTran.setPurity(makMt.getPurity());
					metalTran.setColor(makMt.getColor());
					metalTran.setMetalWt(makMt.getReturnMetal());
					metalTran.setBalance(makMt.getReturnMetal());
					if (makMt.getPurity() != null) {
						metalTran.setPurityConv(tempPurityObj.getPurityConv());
					} else {
						metalTran.setPurityConv(null);
					}
					metalTran.setTranType("Making");
					metalTran.setRefTranId(makMt.getId());
					metalTran.setSrNo(2);
					metalTran.setMetalRate(mtlRates);
					check = true;
				}else{
					metalTranService.delete(metalTran.getId());
				}
			}else{
				if (makMt.getReturnMetal() > 0){
					metalTran = new MetalTran();
					metalTran.setTranDate(new java.util.Date());
					metalTran.setDeptLocation(departmentService.findByName("Central"));
					metalTran.setDepartment(departmentService.findByName("Making"));
					metalTran.setInOutFld("C");
	
					metalTran.setPurity(makMt.getPurity());
					metalTran.setColor(makMt.getColor());
					metalTran.setMetalWt(makMt.getReturnMetal());
					metalTran.setBalance(makMt.getReturnMetal());
					if (makMt.getPurity() != null) {
						metalTran.setPurityConv(tempPurityObj.getPurityConv());
					} else {
						metalTran.setPurityConv(null);
					}
					metalTran.setTranType("Making");
					metalTran.setRefTranId(makMt.getId());
					metalTran.setSrNo(2);
					metalTran.setMetalRate(mtlRates);
					check = true;
				}
			}

		}
		
		
		
		
		
		
		if(check){
			metalTran.setCreatedBy(makMt.getCreatedBy());
			metalTran.setCreatedDt(makMt.getCreatedDt());
			metalTran.setModiBy(makMt.getModiBy());
			metalTran.setModiDt(makMt.getModiDt());
			metalTran.setDeactive(false);
			metalTranService.save(metalTran);
		}
		
		
		
		

	} // for loop

		
		if(action.equalsIgnoreCase("added")){
			retVal  = "/jewels/manufacturing/transactions/makingMt/edit/"+makingMt.getId()+".html?success=true";
			
		}else{
			retVal = "/jewels/manufacturing/transactions/makingMt.html?success=true";
		}
		
		
		return retVal;
	}

	@Override
	public String makingMtDelete(Integer id) {
		String retVal="-1";
		
		delete(id);
		
		MakingMt makingMt = findOne(id);
		
		List<MakingRecDt> makingRecDts = makingRecDtService.findByMakingMtAndDeactive(makingMt, false);
		for(MakingRecDt makingRecDt:makingRecDts){
			makingRecDtService.delete(makingRecDt.getId());
			
			List<CompTran> compTrans = compTranService.findByRefTranIdAndTranType(makingRecDt.getId(), "Making");
			for(CompTran compTran : compTrans){
				compTranService.delete(compTran.getId());
			}
			
			
		}
		
		
		List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "Making",false);
		for(MetalTran metalTran:metalTrans){
			metalTranService.delete(metalTran.getId());
		}
			
		retVal="1";
		// TODO Auto-generated method stub
		return retVal;
	}

	@Override
	public String makingMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		Page<MakingMt> makingMtList = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("makingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		makingMtList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(makingMtList.getTotalElements()).append(",\"rows\": [");

		for (MakingMt makingMt : makingMtList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(makingMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(makingMt.getId())
			.append("\",\"invNo\":\"")
			.append(makingMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(makingMt.getInvDateStr())
			.append("\",\"purity\":\"")
			.append((makingMt.getPurity() != null ? makingMt.getPurity().getName() : ""))
			.append("\",\"color\":\"")
			.append((makingMt.getColor() != null ? makingMt.getColor().getName() : ""));
			sb.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/makingMt/edit/")
								.append(makingMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/makingMt/delete/");			
								sb.append(makingMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(makingMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/makingMt/edit/")
					.append(makingMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/makingMt/delete/")
						.append(makingMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(makingMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		
		return str;
	}

	@Override
	public Page<MakingMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		QMakingMt qMakingMt = QMakingMt.makingMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qMakingMt.deactive.eq(false);
			}else{
				expression = qMakingMt.deactive.eq(false).and(qMakingMt.invNo.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression =qMakingMt.invNo.like("%" + search + "%");
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		}else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<MakingMt> makingList =(Page<MakingMt>) makingMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
			
		return makingList;
	}

	
	
	

}
