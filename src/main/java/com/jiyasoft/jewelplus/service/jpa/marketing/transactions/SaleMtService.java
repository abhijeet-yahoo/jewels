package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QSaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleMtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockTranRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class SaleMtService implements ISaleMtService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private ISaleMtRepository saleMtRepository;
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPackDtService packDtService;
	
	@Autowired
	private ISaleDtService saleDtService;
	

	@Autowired
	private IPackMetalDtService packMetalDtService;
	
	@Autowired
	private IPackStnDtService packStnDtService;
	
	@Autowired
	private IPackCompDtService packCompDtService;
	
	
	@Autowired
	private ISaleMetalDtService saleMetalDtService;
	
	@Autowired
	private ISaleStnDtService saleStnDtService;
	
	@Autowired
	private ISaleCompDtService saleCompDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockTranRepository stockTranRepository;
	
	@Autowired
	private IConsigDtService consigDtService;
	
	@Autowired
	private IConsigMetalDtService consigMetalDtService;
	
	@Autowired
	private IConsigCompDtService consigCompDtService;
	
	@Autowired
	private IConsigStnDtService consigStnDtService;
	
	@Autowired
	private ISaleLabDtService saleLabDtService;

	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPartyService partyService;

	@Autowired
	private IPackLabDtService packLabDtService;
	
	@Autowired
	private IConsigLabDtService consigLabDtService;
	
	@Autowired
	private ISaleRmMetalDtService saleRmMetalDtService;
	
	@Autowired
	private ISaleRmCompDtService saleRmCompDtService;
	
	@Autowired
	private ISaleRmStnDtService saleRmStnDtService;
	
	
	@Override
	public void save(SaleMt saleMt) {
		// TODO Auto-generated method stub
		saleMtRepository.save(saleMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		saleMtRepository.delete(id);
	}

	@Override
	public SaleMt findOne(int id) {
		// TODO Auto-generated method stub
		return saleMtRepository.findOne(id);
	}



	@Override
	public Page<SaleMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		QSaleMt qSaleMt = QSaleMt.saleMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression =qSaleMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qSaleMt.invNo.like("%" + search + "%");
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
		}
	
		Page<SaleMt> saleMtList =(Page<SaleMt>) saleMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return saleMtList;
	}



	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QSaleMt qSaleMt = QSaleMt.saleMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qSaleMt)
			.where(qSaleMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qSaleMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}



	@Override
	public List<SaleMt> findByParty(Party party) {
		// TODO Auto-generated method stub
		return saleMtRepository.findByParty(party);
	}



	@Override
	public String deleteSaleMt(Integer mtId,Principal principal) {
		// TODO Auto-generated method stub
String retVal="-1";
		
		SaleMt saleMt = findOne(mtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = saleMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
			
			
			
			List<SaleDt> saleDts = saleDtService.findBySaleMt(saleMt);
			
			for (SaleDt saleDt : saleDts) {
				
				if(saleDt.getAdjQty()>0) {
					
					return "Can not Delete,Record present in sales return";
					
				}else {
					
					List<SaleStnDt> saleStnDts = saleStnDtService.findBySaleDt(saleDt);
					for(SaleStnDt saleStnDt :saleStnDts) {
						saleStnDtService.delete(saleStnDt.getId());
						
					}
					
					List<SaleMetalDt> saleMetalDts = saleMetalDtService.findBySaleDt(saleDt);
					for(SaleMetalDt saleMetalDt :saleMetalDts) {
						saleMetalDtService.delete(saleMetalDt.getId());
						
					}
					
					List<SaleCompDt> saleCompDts = saleCompDtService.findBySaleDt(saleDt);
					for(SaleCompDt saleCompDt : saleCompDts) {
						saleCompDtService.delete(saleCompDt.getId());
						
					}
					
					
					List<SaleLabDt> saleLabDts = saleLabDtService.findBySaleDt(saleDt);
					for(SaleLabDt saleLabDt :saleLabDts) {
						saleLabDtService.delete(saleLabDt.getId());
						
					}
				
					
					//ConsigDt
					if(saleDt.getTranType().equalsIgnoreCase("CONSIGNMENTISS")) {
					SaleDt saleDt2 = saleDtService.findByRefTranDtIdAndTranType(saleDt.getRefTranDtId(), saleDt.getTranType());
					ConsigDt consigDt = consigDtService.findOne(saleDt2.getRefTranDtId());
					consigDt.setAdjQty(consigDt.getAdjQty() - saleDt2.getPcs());
					consigDtService.save(consigDt);
					}
					
					
					//PackingList
					if(saleDt.getTranType().equalsIgnoreCase("PACKLIST")) {
					SaleDt saleDt2 = saleDtService.findByRefTranDtIdAndTranType(saleDt.getRefTranDtId(), saleDt.getTranType());
					PackDt packDt = packDtService.findOne(saleDt2.getRefTranDtId());
					packDt.setAdjQty(packDt.getAdjQty() - saleDt2.getPcs());
					packDtService.save(packDt);
					}
					
					saleDtService.delete(saleDt.getId());
					
					StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("SALEISS", saleDt.getId(), true);
					if(stockTran !=null) {
						
						StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
						stockTran2.setCurrStk(true);
						stockTranService.save(stockTran2);
						
						stockTranService.delete(stockTran.getId());
						
					}
					
					
					/*
					 * StockMt stockMt =
					 * stockMtService.findByBarcodeAndCurrStkAndDeactive(saleDt.getBarcode(), false,
					 * false);
					 * 
					 * stockMt.setCurrStk(true); stockMtService.save(stockMt);
					 */
					
					
				}
				
			}
			
			List<SaleRmMetalDt> saleRmMetalDts = saleRmMetalDtService.findBySaleMt(saleMt);
			for (SaleRmMetalDt saleRmMetalDt : saleRmMetalDts) {
				saleRmMetalDtService.saleRmMetalDtDelete(saleRmMetalDt.getId(), principal);
			}
			
			List<SaleRmCompDt> saleRmCompDts = saleRmCompDtService.findBySaleMt(saleMt);
			for (SaleRmCompDt saleRmCompDt : saleRmCompDts) {
				saleRmCompDtService.saleRmCompDtDelete(saleRmCompDt.getId(), principal);
			}
			
			List<SaleRmStnDt> saleRmStnDts = saleRmStnDtService.findBySaleMt(saleMt);
			for (SaleRmStnDt saleRmStnDt : saleRmStnDts) {
				saleRmStnDtService.saleRmStnDtDelete(saleRmStnDt.getId(), principal);
			}
			
			delete(saleMt.getId());
			
			retVal ="1";
			

			
		}else {
			
			return "Can Not Delete BackDate Entry";
		
		}
	
		
		return retVal;
	}

	@Override
	public String saleMtListing(Integer limit, Integer offset, String sort, String order, String search,Principal principal) throws ParseException {
		// TODO Auto-generated method stub
StringBuilder sb = new StringBuilder();
		
		
		Page<SaleMt> saleMts = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		saleMts = searchAll(limit, offset, sort, order, search, true);

		
		sb.append("{\"total\":").append(saleMts.getTotalElements()).append(",\"rows\": [");

		for (SaleMt saleMt : saleMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(saleMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(saleMt.getId())
			.append("\",\"invNo\":\"")
			.append(saleMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(saleMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(saleMt.getParty().getName())
			.append("\",\"location\":\"")
			.append(saleMt.getLocation().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/marketing/transactions/saleMt/edit/")
								.append(saleMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteSaleMt(event,");					
								sb.append(saleMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(saleMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/marketing/transactions/saleMt/edit/")
					.append(saleMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deletePackList(event,")	
						.append(saleMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(saleMt.getId())
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
	public String saveSaleMt(SaleMt saleMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,Integer pPartyIds,
			Integer pLocationIds,String vTranDate,Double vOtherCharges,Double vFinalPrice,Double pIgst, Double pSgst,Double pCgst,String barcodeuploadFilePath) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/marketing/transactions/saleMt/add.html";
		
	
		if (result.hasErrors()) {
			return "saleMt/add";
		}

		
		synchronized (this) {
			
			
			saleMt.setInvNo(saleMt.getInvNo().replaceAll("[\\n\\t\\r ]", " ").trim());
			
			if(saleMt.getInvNo() != null) {
				SaleMt saleMtDuplicate = findByInvNo(saleMt.getInvNo());
				if (saleMtDuplicate != null) {
					if (id == null) {
						return "Duplicate Invoice No";
					} else {
						if (!saleMtDuplicate.getId().equals(id)) {
							return "Duplicate Invoice No";
						}
					}
				}
				}else {
					return "Invoice No Compulsory";
				}
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				saleMt.setInvDate(dates);
				
				}
				
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				saleMt.setCreatedBy(principal.getName());
				saleMt.setCreatedDate(new java.util.Date());
				
				

			} else {
				saleMt.setModiBy(principal.getName());
				saleMt.setModiDate(new java.util.Date());
				saleMt.setOtherCharges(vOtherCharges);
				saleMt.setFinalPrice(vFinalPrice);
				saleMt.setCgst(pCgst);
				saleMt.setSgst(pSgst);
				saleMt.setIgst(pIgst);
				
				if(pPartyIds != null) {
					Party party =  partyService.findOne(pPartyIds);
					Department department = departmentService.findOne(pLocationIds);
					
					saleMt.setParty(party);
					saleMt.setLocation(department);

				}

				action = "updated";
				retVal = "redirect:/marketing/transactions/saleMt.html";
			}
			

			if(saleMt.getHsnMast().getId() == null) {
				saleMt.setHsnMast(null);
			}
			
			if(saleMt.getPaymentTerm().getId() == null) {
				saleMt.setPaymentTerm(null);
			}
			
			save(saleMt);
			
			if (action.equals("added")) {
				
				Utils.barcodeGeneration(saleMt.getId(), saleMt.getInvNo(), barcodeuploadFilePath);
				
			 retVal = "redirect:/marketing/transactions/saleMt/edit/"+saleMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
	}
	}

	@Override
	public String salePackingListTransfer(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		SaleMt saleMt = findOne(pMtId);
		JSONArray jsonSaleDt = new JSONArray(data);
		
		
		try {
			

			for (int y = 0; y < jsonSaleDt.length(); y++) {
				
				JSONObject dataSaleDt = (JSONObject) jsonSaleDt.get(y);
				
				PackDt packDt = packDtService.findOne((int)(Double.parseDouble(dataSaleDt.get("id").toString())));
				
				SaleDt saleDt2 = saleDtService.findBySaleMtAndBarcode(saleMt,dataSaleDt.get("barcode").toString());
				if(saleDt2 != null) {
					return "Duplicate Barcode";
				}
				
				SaleDt saleDt =new SaleDt();
				saleDt.setBarcode(packDt.getBarcode());
				saleDt.setCreatedBy(principal.getName());
				saleDt.setCreatedDate(new Date());
				saleDt.setDesign(packDt.getDesign());
				saleDt.setGrossWt(packDt.getGrossWt());
				saleDt.setNetWt(packDt.getNetWt());
				saleDt.setSaleMt(saleMt);
				saleDt.setPcs(packDt.getPcs());
				saleDt.setMetalValue(packDt.getMetalValue());
				saleDt.setStoneValue(packDt.getStoneValue());
				saleDt.setLabValue(packDt.getLabValue());
				saleDt.setSetValue(packDt.getSetValue());
				saleDt.setCompValue(packDt.getCompValue());
				saleDt.setHandlingValue(packDt.getHandlingValue());
				saleDt.setFinalPrice((double) Math.round(packDt.getFinalPrice()));
				saleDt.setFob(packDt.getFob());
				saleDt.setRefTranDtId(packDt.getId());
				saleDt.setTranType("PACKLIST");
				saleDt.setDestination(packDt.getDestination());
				saleDt.setDiscAmount(packDt.getDiscAmount());
				saleDt.setDiscPerc(packDt.getDiscPerc());
				saleDt.setLossValue(packDt.getLossValue());
				saleDt.setLossWt(packDt.getLossWt());
				saleDt.setNetAmount(packDt.getNetAmount());
				saleDt.setOrderRef(packDt.getOrderRef());
				saleDt.setOther(packDt.getOther());
				saleDt.setHallMarking(packDt.getHallMarking());
				saleDt.setLazerMarking(packDt.getLazerMarking());
				saleDt.setEngraving(packDt.getEngraving());
				saleDt.setGrading(packDt.getGrading());
				
				saleDtService.save(saleDt);
				
				packDt.setAdjQty(packDt.getPcs());
				packDt.setModiBy(principal.getName());
				packDt.setModiDate(new Date());
				packDtService.save(packDt);
				
				List<PackMetalDt> packMetalDts =packMetalDtService.findByPackDt(packDt);
				for(PackMetalDt packMetalDt :packMetalDts) {
					
					SaleMetalDt saleMetalDt = new SaleMetalDt();
					saleMetalDt.setColor(packMetalDt.getColor());
					saleMetalDt.setCreateDate(new Date());
					saleMetalDt.setCreatedBy(principal.getName());
					saleMetalDt.setMainMetal(packMetalDt.getMainMetal());
					saleMetalDt.setMetalPcs(packMetalDt.getMetalPcs());
					saleMetalDt.setMetalWeight(packMetalDt.getMetalWeight());
					saleMetalDt.setSaleMt(saleMt);
					saleMetalDt.setSaleDt(saleDt);
					saleMetalDt.setPartNm(packMetalDt.getPartNm());
					saleMetalDt.setPurity(packMetalDt.getPurity());
					saleMetalDt.setPurityConv(packMetalDt.getPurityConv());
					saleMetalDt.setMetalValue(packMetalDt.getMetalValue());
					saleMetalDt.setMetalRate(Math.round((packDt.getMetalValue() / packDt.getNetWt())*1000.0)/1000.0);
					saleMetalDt.setPerGramRate(packMetalDt.getPerGramRate());
					saleMetalDt.setPerPcRate(packMetalDt.getPerPcRate());
					
					saleMetalDtService.save(saleMetalDt);
					
				}
				
				List<PackStnDt> packStnDts = packStnDtService.findByPackDt(packDt);
				for(PackStnDt packStnDt : packStnDts) {
					
					SaleStnDt saleStnDt =new SaleStnDt();
					saleStnDt.setCarat(packStnDt.getCarat());
					saleStnDt.setCreatedBy(principal.getName());
					saleStnDt.setCreatedDate(new Date());
					saleStnDt.setSaleDt(saleDt);
					saleStnDt.setSaleMt(saleMt);
					saleStnDt.setPartNm(packStnDt.getPartNm());
					saleStnDt.setQuality(packStnDt.getQuality());
					saleStnDt.setSetting(packStnDt.getSetting());
					saleStnDt.setSettingType(packStnDt.getSettingType());
					saleStnDt.setShape(packStnDt.getShape());
					saleStnDt.setSieve(packStnDt.getSieve());
					saleStnDt.setSize(packStnDt.getSize());
					saleStnDt.setSizeGroup(packStnDt.getSizeGroup());
					saleStnDt.setStone(packStnDt.getStone());
					saleStnDt.setStoneType(packStnDt.getStoneType());
					saleStnDt.setSubShape(packStnDt.getSubShape());
					saleStnDt.setCenterStone(packStnDt.getCenterStone());
					saleStnDt.setStoneRate(packStnDt.getStoneRate());
					saleStnDt.setStoneValue(packStnDt.getStoneValue());
					saleStnDt.setHandlingRate(packStnDt.getHandlingRate());
					saleStnDt.setHandlingValue(packStnDt.getHandlingValue());
					saleStnDt.setHdlgPerCarat(packStnDt.getHdlgPerCarat());
					saleStnDt.setHdlgPercentWise(packStnDt.getHdlgPercentWise());
					saleStnDt.setrLock(packStnDt.getrLock());
					saleStnDt.setSetRate(packStnDt.getSetRate());
					saleStnDt.setSetValue(packStnDt.getSetValue());
					saleStnDt.setPerPcsRateFlg(packStnDt.getPerPcsRateFlg());
					
					saleStnDtService.save(saleStnDt);
					
					
				}
				
				
				List<PackCompDt> packCompDts =packCompDtService.findByPackDt(packDt);
				for(PackCompDt packCompDt :packCompDts) {
					
					SaleCompDt saleCompDt = new SaleCompDt();
					saleCompDt.setColor(packCompDt.getColor());
					saleCompDt.setComponent(packCompDt.getComponent());
					saleCompDt.setCompQty(packCompDt.getCompQty());
					saleCompDt.setCreatedBy(principal.getName());
					saleCompDt.setCreatedDate(new Date());
					saleCompDt.setSaleDt(saleDt);
					saleCompDt.setSaleMt(saleMt);
					saleCompDt.setPurity(packCompDt.getPurity());
					saleCompDt.setPurityConv(packCompDt.getPurityConv());
					saleCompDt.setCompWt(packCompDt.getCompWt());
					saleCompDt.setCompRate(packCompDt.getCompValue());
					saleCompDt.setCompValue(packCompDt.getCompValue());
			//		saleCompDt.setLossPerc(packCompDt.getLossPerc());
					saleCompDt.setMetalRate(packCompDt.getMetalRate());
					saleCompDt.setMetalValue(packCompDt.getMetalValue());
			//		saleCompDt.setPerGramMetalRate(packCompDt.getPerGramMetalRate());
					saleCompDt.setPerGramRate(packCompDt.getPerGramRate());
					
					saleCompDtService.save(saleCompDt);
					
				}
				
				List<PackLabDt> packLabDts =packLabDtService.findByPackDt(packDt);
				for (PackLabDt packLabDt : packLabDts) {
					SaleLabDt saleLabDt = new SaleLabDt();
					saleLabDt.setLabourRate(packLabDt.getLabourRate());
					saleLabDt.setSaleDt(saleDt);
					saleLabDt.setSaleMt(saleMt);
					saleLabDt.setLabourType(packLabDt.getLabourType());
					saleLabDt.setLabourValue(packLabDt.getLabourValue());
					saleLabDt.setPerCaratRate(packLabDt.getPerCaratRate());
					saleLabDt.setPercentage(packLabDt.getPercentage());
					saleLabDt.setPerPcRate(packLabDt.getPerPcRate());
					saleLabDt.setPerGramRate(packLabDt.getPerGramRate());
					saleLabDt.setrLock(packLabDt.getrLock());
					saleLabDt.setMetal(packLabDt.getMetal());
					saleLabDtService.save(saleLabDt);
					
				}
				
				StockTran stockTran = stockTranRepository.findByTranTypeAndRefTranIdAndCurrStk("PACKLIST",packDt.getId(),true);
				StockTran stockTran2 = new StockTran();
				stockTran2.setBarcode(packDt.getBarcode());
				stockTran2.setCreatedBy(principal.getName());
				stockTran2.setCreatedDate(new Date());
				stockTran2.setCurrStatus("SALEISS");
				stockTran2.setCurrStk(true);
				stockTran2.setLocation(stockTran.getLocation());
				stockTran2.setRefStkTranId(stockTran.getId());
				stockTran2.setRefTranId(saleDt.getId());
				stockTran2.setTranDate(saleMt.getInvDate());
				stockTran2.setTranType("SALEISS");
				stockTran2.setParty(saleMt.getParty());
				stockTran2.setStockMt(stockTran.getStockMt());
				stockTranService.save(stockTran2);
				
				stockTran.setCurrStk(false);
				stockTran.setIssueDate(saleMt.getInvDate());
				stockTranService.save(stockTran);
				
				
			}
			retVal = "1";
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}

	@Override
	public String saleConsignmetTransfer(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		SaleMt saleMt = findOne(pMtId);
		JSONArray jsonSaleDt = new JSONArray(data);
		
		
		try {
			

			for (int y = 0; y < jsonSaleDt.length(); y++) {
				
				JSONObject dataSaleDt = (JSONObject) jsonSaleDt.get(y);
				
				ConsigDt consigDt = consigDtService.findOne((int)(Double.parseDouble(dataSaleDt.get("id").toString())));
				
				SaleDt saleDt2 = saleDtService.findBySaleMtAndBarcode(saleMt,dataSaleDt.get("barcode").toString());
				if(saleDt2 != null) {
					return "Duplicate Barcode";
				}
				
				SaleDt saleDt =new SaleDt();
				saleDt.setBarcode(consigDt.getBarcode());
				saleDt.setCreatedBy(principal.getName());
				saleDt.setCreatedDate(new Date());
				saleDt.setDesign(consigDt.getDesign());
				saleDt.setGrossWt(consigDt.getGrossWt());
				saleDt.setNetWt(consigDt.getNetWt());
				saleDt.setSaleMt(saleMt);
				saleDt.setPcs(consigDt.getPcs());
				saleDt.setMetalValue(consigDt.getMetalValue());
				saleDt.setStoneValue(consigDt.getStoneValue());
				saleDt.setLabValue(consigDt.getLabValue());
				saleDt.setSetValue(consigDt.getSetValue());
				saleDt.setCompValue(consigDt.getCompValue());
				saleDt.setHandlingValue(consigDt.getHandlingValue());
				saleDt.setFinalPrice((double) Math.round(consigDt.getFinalPrice()));
				saleDt.setFob((double) Math.round(consigDt.getFob()));
				saleDt.setDestination(consigDt.getDestination());
				saleDt.setDiscAmount(consigDt.getDiscAmount());
				saleDt.setDiscPerc(consigDt.getDiscPerc());
				saleDt.setLossValue(consigDt.getLossValue());
				saleDt.setLossWt(consigDt.getLossWt());
				saleDt.setNetAmount(consigDt.getNetAmount());
				saleDt.setOrderRef(consigDt.getOrderRef());
				saleDt.setOther(consigDt.getOther());
				saleDt.setRefTranDtId(consigDt.getId());
				saleDt.setTranType("CONSIGNMENTISS");
				saleDt.setHallMarking(consigDt.getHallMarking());
				saleDt.setLazerMarking(consigDt.getLazerMarking());
				saleDt.setEngraving(consigDt.getEngraving());
				saleDt.setGrading(consigDt.getGrading());
				
				saleDtService.save(saleDt);
				
				consigDt.setAdjQty(consigDt.getPcs());
				consigDt.setModiBy(principal.getName());
				consigDt.setModiDate(new Date());
				consigDtService.save(consigDt);
				
				List<ConsigMetalDt> consigMetalDts = consigMetalDtService.findByConsigDt(consigDt);
				for(ConsigMetalDt consigMetalDt :consigMetalDts) {
					
					SaleMetalDt saleMetalDt = new SaleMetalDt();
					saleMetalDt.setColor(consigMetalDt.getColor());
					saleMetalDt.setCreateDate(new Date());
					saleMetalDt.setCreatedBy(principal.getName());
					saleMetalDt.setMainMetal(consigMetalDt.getMainMetal());
					saleMetalDt.setMetalPcs(consigMetalDt.getMetalPcs());
					saleMetalDt.setMetalWeight(consigMetalDt.getMetalWeight());
					saleMetalDt.setSaleMt(saleMt);
					saleMetalDt.setSaleDt(saleDt);
					saleMetalDt.setPartNm(consigMetalDt.getPartNm());
					saleMetalDt.setPurity(consigMetalDt.getPurity());
					saleMetalDt.setPurityConv(consigMetalDt.getPurityConv());
					saleMetalDt.setMetalValue(consigMetalDt.getMetalValue());
					saleMetalDt.setMetalRate(Math.round((consigDt.getMetalValue() / consigDt.getNetWt())*1000.0)/1000.0);
					saleMetalDt.setPerGramRate(consigMetalDt.getPerGramRate());
					saleMetalDt.setPerPcRate(consigMetalDt.getPerPcRate());
					
					saleMetalDtService.save(saleMetalDt);
					
				}
				
				List<ConsigStnDt> consigStnDts = consigStnDtService.findByConsigDt(consigDt);
				for(ConsigStnDt consigStnDt : consigStnDts) {
					
					SaleStnDt saleStnDt =new SaleStnDt();
					saleStnDt.setCarat(consigStnDt.getCarat());
					saleStnDt.setCreatedBy(principal.getName());
					saleStnDt.setCreatedDate(new Date());
					saleStnDt.setSaleDt(saleDt);
					saleStnDt.setSaleMt(saleMt);
					saleStnDt.setPartNm(consigStnDt.getPartNm());
					saleStnDt.setQuality(consigStnDt.getQuality());
					saleStnDt.setSetting(consigStnDt.getSetting());
					saleStnDt.setSettingType(consigStnDt.getSettingType());
					saleStnDt.setShape(consigStnDt.getShape());
					saleStnDt.setSieve(consigStnDt.getSieve());
					saleStnDt.setSize(consigStnDt.getSize());
					saleStnDt.setSizeGroup(consigStnDt.getSizeGroup());
					saleStnDt.setStone(consigStnDt.getStone());
					saleStnDt.setStoneType(consigStnDt.getStoneType());
					saleStnDt.setSubShape(consigStnDt.getSubShape());
					saleStnDt.setCenterStone(consigStnDt.getCenterStone());
					saleStnDt.setStoneRate(consigStnDt.getStoneRate());
					saleStnDt.setStoneValue(consigStnDt.getStoneValue());
					saleStnDt.setHandlingRate(consigStnDt.getHandlingRate());
					saleStnDt.setHandlingValue(consigStnDt.getHandlingValue());
					saleStnDt.setHdlgPerCarat(consigStnDt.getHdlgPerCarat());
					saleStnDt.setHdlgPercentWise(consigStnDt.getHdlgPercentWise());
					saleStnDt.setrLock(consigStnDt.getrLock());
					saleStnDt.setSetRate(consigStnDt.getSetRate());
					saleStnDt.setSetValue(consigStnDt.getSetValue());
					saleStnDt.setPerPcsRateFlg(consigStnDt.getPerPcsRateFlg());
					saleStnDtService.save(saleStnDt);
					
					
				}
				
				
				List<ConsigCompDt> consigCompDts = consigCompDtService.findByConsigDt(consigDt);
				for(ConsigCompDt consigCompDt :consigCompDts) {
					
					SaleCompDt saleCompDt = new SaleCompDt();
					saleCompDt.setColor(consigCompDt.getColor());
					saleCompDt.setComponent(consigCompDt.getComponent());
					saleCompDt.setCompQty(consigCompDt.getCompQty());
					saleCompDt.setCreatedBy(principal.getName());
					saleCompDt.setCreatedDate(new Date());
					saleCompDt.setSaleDt(saleDt);
					saleCompDt.setSaleMt(saleMt);
					saleCompDt.setPurity(consigCompDt.getPurity());
					saleCompDt.setPurityConv(consigCompDt.getPurityConv());
					saleCompDt.setCompWt(consigCompDt.getCompWt());
					saleCompDt.setCompValue(consigCompDt.getCompValue());
			//		saleCompDt.setLossPerc(consigCompDt.getLossPerc());
					saleCompDt.setMetalRate(consigCompDt.getMetalRate());
					saleCompDt.setMetalValue(consigCompDt.getMetalValue());
			//		saleCompDt.setPerGramMetalRate(consigCompDt.getPerGramMetalRate());
					saleCompDt.setPerGramRate(consigCompDt.getPerGramRate());
				
					
					saleCompDtService.save(saleCompDt);
					
				}
				
				
				List<ConsigLabDt> consigLabDts = consigLabDtService.findByConsigDt(consigDt);
				for (ConsigLabDt consigLabDt : consigLabDts) {
					SaleLabDt saleLabDt = new SaleLabDt();
					saleLabDt.setLabourRate(consigLabDt.getLabourRate());
					saleLabDt.setSaleDt(saleDt);
					saleLabDt.setSaleMt(saleMt);
					saleLabDt.setLabourType(consigLabDt.getLabourType());
					saleLabDt.setLabourValue(consigLabDt.getLabourValue());
					saleLabDt.setPerCaratRate(consigLabDt.getPerCaratRate());
					saleLabDt.setPercentage(consigLabDt.getPercentage());
					saleLabDt.setPerPcRate(consigLabDt.getPerPcRate());
					saleLabDt.setPerGramRate(consigLabDt.getPerGramRate());
					saleLabDt.setrLock(consigLabDt.getrLock());
					saleLabDt.setMetal(consigLabDt.getMetal());
					saleLabDtService.save(saleLabDt);
					
				}
				
				StockTran stockTran = stockTranRepository.findByTranTypeAndRefTranIdAndCurrStk("CONSIGNMENTISS",consigDt.getId(),true);
				StockTran stockTran2 = new StockTran();
				stockTran2.setBarcode(consigDt.getBarcode());
				stockTran2.setCreatedBy(principal.getName());
				stockTran2.setCreatedDate(new Date());
				stockTran2.setCurrStatus("SALEISS");
				stockTran2.setCurrStk(true);
				stockTran2.setLocation(stockTran.getLocation());
				stockTran2.setRefStkTranId(stockTran.getId());
				stockTran2.setRefTranId(saleDt.getId());
				stockTran2.setTranDate(saleMt.getInvDate());
				stockTran2.setTranType("SALEISS");
				stockTran2.setParty(saleMt.getParty());
				stockTran2.setStockMt(stockTran.getStockMt());
				stockTranService.save(stockTran2);
				
				stockTran.setCurrStk(false);
				stockTran.setIssueDate(saleMt.getInvDate());
				stockTranService.save(stockTran);
				
				
			}
			retVal = "1";
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}

	

	@Override
	public String saleDtSummary(Integer mtId, Principal principal) {
		// TODO Auto-generated method stub

		
		SaleMt saleMt = findOne(mtId);
		
		List<SaleDt> saleDts = saleDtService.findBySaleMt(saleMt);
		
		JSONObject jsonObject = new JSONObject();
		
		Double totPcs = 0.0;
		Double grossWt = 0.0;
		Double netWt = 0.0;
	//	Double pureWt = 0.0;
		Integer diaStone =0;
		Double diaCarat = 0.0;
		Integer colStone = 0;
		Double colCarat = 0.0;
		
		
		try {
			
			for (SaleDt saleDt : saleDts) {
				
				totPcs += saleDt.getPcs();
				grossWt += saleDt.getGrossWt();
				netWt += saleDt.getNetWt();
				
				
				List<SaleStnDt> saleStnDts = saleStnDtService.findBySaleDt(saleDt);
				for (SaleStnDt saleStnDt : saleStnDts) {
					
					if(saleStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
						diaStone += saleStnDt.getStone();
						diaCarat += saleStnDt.getCarat();
					}else {
						colStone += saleStnDt.getStone();
						colCarat += saleStnDt.getCarat();
						
					}
				}
				
			}
			
			jsonObject.put("totPcs", totPcs);
			jsonObject.put("grossWt", Math.round((grossWt)*1000.0)/1000.0);
			jsonObject.put("netWt",  Math.round((netWt)*1000.0)/1000.0);
		//	jsonObject.put("pureWt", Math.round((pureWt)*1000.0)/1000.0);
			jsonObject.put("diaStone", diaStone);
			jsonObject.put("diaCarat",  Math.round((diaCarat)*1000.0)/1000.0);
			jsonObject.put("colStone", colStone);
			jsonObject.put("colCarat", Math.round((colCarat)*1000.0)/1000.0);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	return jsonObject.toString();
	}

	@Override
	public String addSummaryDetails(Integer mtId, Double fob, Double sgst, Double cgst, Double igst,Double otherCharges,Double finalPrice,
			Principal principal) {
		// TODO Auto-generated method stub
		String retVal="-1";
		SaleMt saleMt = findOne(mtId);
		
		try {
			
			saleMt.setCgst(cgst);
			saleMt.setSgst(sgst);
			saleMt.setIgst(igst);
			saleMt.setOtherCharges(otherCharges);
			saleMt.setFinalPrice(finalPrice);
			save(saleMt);
			
			retVal="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}

	@Override
	public String saleMtPickupList(Integer partyId) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		String str = null;
		Party party = partyService.findOne(partyId);
		List<SaleMt> saleMts = findByParty(party);
		
		
		
			try {
				
				sb.append("{\"total\":").append(saleMts.size()).append(",\"rows\": [");

				for (SaleMt saleMt : saleMts) {
					
					Boolean adjQtyFlg = false;
					
					List<SaleDt> saleDts = saleDtService.findBySaleMt(saleMt);
					
					for (SaleDt saleDt : saleDts) {
						
						 if(Math.round(saleDt.getPcs() - saleDt.getAdjQty()) > 0) {
							 adjQtyFlg = true;
						 }
						
					}
				
					if(adjQtyFlg) {
					sb.append("{\"id\":\"")
					.append(saleMt.getId())
					.append("\",\"invNo\":\"")
					.append(saleMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(saleMt.getInvDateStr())
					.append("\",\"party\":\"")
					.append(saleMt.getParty().getName())
					.append("\",\"location\":\"")
					.append(saleMt.getLocation().getName())
					.append("\"},");
					}
				}

				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	return str;
	}

	@Override
	public SaleMt findByInvNo(String invNo) {
		// TODO Auto-generated method stub
		return saleMtRepository.findByInvNo(invNo);
	}

	@Override
	public String checkInvoiceAvailable(Integer id, String invNo) {
		// TODO Auto-generated method stub
		Boolean invNoAvailable = true;

		if (id == null) {
			invNoAvailable = (findByInvNo(invNo) == null);
		} else {
			SaleMt saleMt = findOne(id);
			if (!(invNo.equalsIgnoreCase(saleMt.getInvNo()))) {
				invNoAvailable = (findByInvNo(invNo) == null);
			}
		}

		return invNoAvailable.toString();
	}

	@Override
	public String saleMtMetalPickupList(Integer partyId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		String str = null;
		Party party = partyService.findOne(partyId);
		List<SaleMt> saleMts = findByParty(party);
		
		
		
			try {
				
				sb.append("{\"total\":").append(saleMts.size()).append(",\"rows\": [");

				for (SaleMt saleMt : saleMts) {
					
					Boolean adjQtyFlg = false;
					
					List<SaleRmMetalDt> saleRmMetalDts = saleRmMetalDtService.findBySaleMt(saleMt);
					
					for (SaleRmMetalDt saleRmMetalDt : saleRmMetalDts) {
						
						 if(Math.round(saleRmMetalDt.getMetalWt() - saleRmMetalDt.getAdjWt()) > 0) {
							 adjQtyFlg = true;
						 }
						
					}
				
					if(adjQtyFlg) {
					sb.append("{\"id\":\"")
					.append(saleMt.getId())
					.append("\",\"invNo\":\"")
					.append(saleMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(saleMt.getInvDateStr())
					.append("\",\"party\":\"")
					.append(saleMt.getParty().getName())
					.append("\",\"location\":\"")
					.append(saleMt.getLocation().getName())
					.append("\"},");
					}
				}

				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	return str;
	}

	@Override
	public String saleRowCompPickupList(Integer partyId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		String str = null;
		Party party = partyService.findOne(partyId);
		List<SaleMt> saleMts = findByParty(party);
		
			try {
				
				sb.append("{\"total\":").append(saleMts.size()).append(",\"rows\": [");

				for (SaleMt saleMt : saleMts) {
					
					Boolean adjQtyFlg = false;
					
					List<SaleRmCompDt> saleRmCompDts = saleRmCompDtService.findBySaleMt(saleMt);
					
					for (SaleRmCompDt saleRmCompDt : saleRmCompDts) {
						
						 if(Math.round(saleRmCompDt.getMetalWt() - saleRmCompDt.getAdjWt()) > 0) {
							 adjQtyFlg = true;
						 }
						
					}
				
					if(adjQtyFlg) {
					sb.append("{\"id\":\"")
					.append(saleMt.getId())
					.append("\",\"invNo\":\"")
					.append(saleMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(saleMt.getInvDateStr())
					.append("\",\"party\":\"")
					.append(saleMt.getParty().getName())
					.append("\",\"location\":\"")
					.append(saleMt.getLocation().getName())
					.append("\"},");
					}
				}

				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	return str;
	}

	@Override
	public String saleRowStonePickupList(Integer partyId) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		String str = null;
		Party party = partyService.findOne(partyId);
		List<SaleMt> saleMts = findByParty(party);
		
		
		
			try {
				sb.append("{\"total\":").append(saleMts.size()).append(",\"rows\": [");

				for (SaleMt saleMt : saleMts) {
					
					
					Boolean adjQtyFlg = false;
					
					List<SaleRmStnDt> saleRmStnDts = saleRmStnDtService.findBySaleMt(saleMt);
					
					for (SaleRmStnDt saleRmStnDt : saleRmStnDts) {
						
						 if(Math.round(saleRmStnDt.getCarat() - saleRmStnDt.getAdjWt()) > 0) {
							 adjQtyFlg = true;
						 }
						
					}
				
					if(adjQtyFlg) {
					sb.append("{\"id\":\"")
					.append(saleMt.getId())
					.append("\",\"invNo\":\"")
					.append(saleMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(saleMt.getInvDateStr())
					.append("\",\"party\":\"")
					.append(saleMt.getParty().getName())
					.append("\",\"location\":\"")
					.append(saleMt.getLocation().getName())
					.append("\"},");
					}
				}

				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	return str;
	}


}
