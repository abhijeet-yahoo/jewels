package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigMtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockTranRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ConsigMtService implements IConsigMtService{
	
	@Autowired
	private IConsigMtRepository consigMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPackDtService packDtService;
	
	@Autowired
	private IConsigDtService consigDtService;
	
	@Autowired
	private IPackMetalDtService packMetalDtService;
	
	@Autowired
	private IPackStnDtService packStnDtService;
	
	@Autowired
	private IPackCompDtService packCompDtService;
	
	
	@Autowired
	private IConsigMetalDtService consigMetalDtService;
	
	@Autowired
	private IConsigStnDtService consigStnDtService;
	
	@Autowired
	private IConsigCompDtService consigCompDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockTranRepository stockTranRepository;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IConsigLabDtService consigLabDtService ;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IPackMtService packMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IConsigRmMetalDtService consigRmMetalDtService;
	
	@Autowired
	private IConsigRmStnDtService consigRmStnDtService;
	
	@Autowired
	private IConsigRmCompDtService consigRmCompDtService;
	
	@Autowired
	private IPackLabDtService packLabDtService;
	

	
	@Override
	public Page<ConsigMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		QConsigMt qConsigMt = QConsigMt.consigMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression = qConsigMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qConsigMt.invNo.like("%" + search + "%");
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
	
		Page<ConsigMt> consigList =(Page<ConsigMt>) consigMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return consigList;
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QConsigMt qConsigMt = QConsigMt.consigMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qConsigMt)
			.where(qConsigMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qConsigMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public void save(ConsigMt consigMt) {
		// TODO Auto-generated method stub
		consigMtRepository.save(consigMt);	
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigMtRepository.delete(id);
	
	}

	@Override
	public ConsigMt findOne(int id) {
		// TODO Auto-generated method stub
		return consigMtRepository.findOne(id);
	}

	@Override
	public String packingListTransfer(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		ConsigMt consigMt = findOne(pMtId);
		JSONArray jsonPackDt = new JSONArray(data);
		
		
		try {
			

			for (int y = 0; y < jsonPackDt.length(); y++) {
				
				JSONObject datapackDt = (JSONObject) jsonPackDt.get(y);
				
				PackDt packDt = packDtService.findOne((int)(Double.parseDouble(datapackDt.get("id").toString())));
				
				ConsigDt consigDt2 = consigDtService.findByConsigMtAndBarcode(consigMt,packDt.getBarcode());
				if(consigDt2 != null) {
					return "Duplicate Barcode";
				}
				
				ConsigDt consigDt =new ConsigDt();
				consigDt.setBarcode(packDt.getBarcode());
				consigDt.setCreatedBy(principal.getName());
				consigDt.setCreatedDate(new Date());
				consigDt.setDesign(packDt.getDesign());
				consigDt.setGrossWt(packDt.getGrossWt());
				consigDt.setNetWt(packDt.getNetWt());
				consigDt.setConsigMt(consigMt);
				consigDt.setPcs(packDt.getPcs());
				consigDt.setMetalValue(packDt.getMetalValue());
				consigDt.setStoneValue(packDt.getStoneValue());
				consigDt.setLabValue(packDt.getLabValue());
				consigDt.setSetValue(packDt.getSetValue());
				consigDt.setCompValue(packDt.getCompValue());
				consigDt.setHandlingValue(packDt.getHandlingValue());
				consigDt.setFinalPrice((double) Math.round(packDt.getFinalPrice()));
				consigDt.setFob((double) Math.round(packDt.getFob()));
				consigDt.setDestination(packDt.getDestination());
				consigDt.setDiscAmount(packDt.getDiscAmount());
				consigDt.setDiscPerc(packDt.getDiscPerc());
				consigDt.setLossValue(packDt.getLossValue());
				consigDt.setLossWt(packDt.getLossWt());
				consigDt.setNetAmount(packDt.getNetAmount());
				consigDt.setOrderRef(packDt.getOrderRef());
				consigDt.setOther(packDt.getOther());
				consigDt.setRefTranDtId(packDt.getId());
				consigDt.setTranType("PACKLIST");
				consigDt.setHallMarking(packDt.getHallMarking());
				consigDt.setLazerMarking(packDt.getLazerMarking());
				consigDt.setEngraving(packDt.getEngraving());
				consigDt.setGrading(packDt.getGrading());
				
				
				consigDtService.save(consigDt);
				
				packDt.setAdjQty(packDt.getPcs());
				packDt.setModiBy(principal.getName());
				packDt.setModiDate(new Date());
				packDtService.save(packDt);
				
				List<PackMetalDt> packMetalDts =packMetalDtService.findByPackDt(packDt);
				for(PackMetalDt packMetalDt :packMetalDts) {
					ConsigMetalDt consigMetalDt = new ConsigMetalDt();
					consigMetalDt.setColor(packMetalDt.getColor());
					consigMetalDt.setCreateDate(new Date());
					consigMetalDt.setCreatedBy(principal.getName());
					consigMetalDt.setMainMetal(packMetalDt.getMainMetal());
					consigMetalDt.setMetalPcs(packMetalDt.getMetalPcs());
					consigMetalDt.setMetalWeight(packMetalDt.getMetalWeight());
					consigMetalDt.setConsigMt(consigMt);
					consigMetalDt.setConsigDt(consigDt);
					consigMetalDt.setPartNm(packMetalDt.getPartNm());
					consigMetalDt.setPurity(packMetalDt.getPurity());
					consigMetalDt.setPurityConv(packMetalDt.getPurityConv());
					consigMetalDt.setMetalValue(packMetalDt.getMetalValue());
					consigMetalDt.setMetalRate(Math.round((packDt.getMetalValue() / packDt.getNetWt())*1000.0)/1000.0);
					consigMetalDt.setPerGramRate(packMetalDt.getPerGramRate());
					consigMetalDt.setPerPcRate(packMetalDt.getPerPcRate());
					
					
					consigMetalDtService.save(consigMetalDt);
					
				}
				
				List<PackStnDt> packStnDts = packStnDtService.findByPackDt(packDt);
				for(PackStnDt packStnDt : packStnDts) {
					ConsigStnDt consigStnDt =new ConsigStnDt();
					consigStnDt.setCarat(packStnDt.getCarat());
					consigStnDt.setCreatedBy(principal.getName());
					consigStnDt.setCreatedDate(new Date());
					consigStnDt.setConsigDt(consigDt);
					consigStnDt.setConsigMt(consigMt);
					consigStnDt.setPartNm(packStnDt.getPartNm());
					consigStnDt.setQuality(packStnDt.getQuality());
					consigStnDt.setSetting(packStnDt.getSetting());
					consigStnDt.setSettingType(packStnDt.getSettingType());
					consigStnDt.setShape(packStnDt.getShape());
					consigStnDt.setSieve(packStnDt.getSieve());
					consigStnDt.setSize(packStnDt.getSize());
					consigStnDt.setSizeGroup(packStnDt.getSizeGroup());
					consigStnDt.setStone(packStnDt.getStone());
					consigStnDt.setStoneType(packStnDt.getStoneType());
					consigStnDt.setSubShape(packStnDt.getSubShape());
					consigStnDt.setCenterStone(packStnDt.getCenterStone());
					consigStnDt.setStoneRate(packStnDt.getStoneRate());
					consigStnDt.setStoneValue(packStnDt.getStoneValue());
					consigStnDt.setHandlingRate(packStnDt.getHandlingRate());
					consigStnDt.setHandlingValue(packStnDt.getHandlingValue());
					consigStnDt.setHdlgPerCarat(packStnDt.getHdlgPerCarat());
					consigStnDt.setHdlgPercentWise(packStnDt.getHdlgPercentWise());
					consigStnDt.setrLock(packStnDt.getrLock());
					consigStnDt.setSetRate(packStnDt.getSetRate());
					consigStnDt.setSetValue(packStnDt.getSetValue());
					consigStnDt.setPerPcsRateFlg(packStnDt.getPerPcsRateFlg());
				
					
					consigStnDtService.save(consigStnDt);
					
					
				}
				
				
				List<PackCompDt> packCompDts =packCompDtService.findByPackDt(packDt);
				for(PackCompDt packCompDt :packCompDts) {
					ConsigCompDt consigCompDt = new ConsigCompDt();
					consigCompDt.setColor(packCompDt.getColor());
					consigCompDt.setComponent(packCompDt.getComponent());
					consigCompDt.setCompQty(packCompDt.getCompQty());
					consigCompDt.setCreatedBy(principal.getName());
					consigCompDt.setCreatedDate(new Date());
					consigCompDt.setConsigDt(consigDt);
					consigCompDt.setConsigMt(consigMt);
					consigCompDt.setPurity(packCompDt.getPurity());
					consigCompDt.setPurityConv(packCompDt.getPurityConv());
					consigCompDt.setCompWt(packCompDt.getCompWt());
					consigCompDt.setCompRate(packCompDt.getCompValue());
					consigCompDt.setCompValue(packCompDt.getCompValue());
					consigCompDt.setLossPerc(packCompDt.getLossPerc());
					consigCompDt.setMetalRate(packCompDt.getMetalRate());
					consigCompDt.setMetalValue(packCompDt.getMetalValue());
					consigCompDt.setPerGramMetalRate(packCompDt.getPerGramMetalRate());
					consigCompDt.setPerGramRate(packCompDt.getPerGramRate());
				
					
					consigCompDtService.save(consigCompDt);
					
				}
				
				List<PackLabDt> packLabDts =packLabDtService.findByPackDt(packDt);
				for (PackLabDt packLabDt : packLabDts) {
					ConsigLabDt consigLabDt = new ConsigLabDt();
					consigLabDt.setLabourRate(packLabDt.getLabourRate());
					consigLabDt.setConsigDt(consigDt);
					consigLabDt.setConsigMt(consigMt);
					consigLabDt.setLabourType(packLabDt.getLabourType());
					consigLabDt.setLabourValue(packLabDt.getLabourValue());
					consigLabDt.setPerCaratRate(packLabDt.getPerCaratRate());
					consigLabDt.setPercentage(packLabDt.getPercentage());
					consigLabDt.setPerPcRate(packLabDt.getPerPcRate());
					consigLabDt.setPerGramRate(packLabDt.getPerGramRate());
					consigLabDt.setrLock(packLabDt.getrLock());
					consigLabDt.setMetal(packLabDt.getMetal());
					consigLabDtService.save(consigLabDt);
					
					
				}
				
				
				StockTran stockTran = stockTranRepository.findByTranTypeAndRefTranIdAndCurrStk("PACKLIST",packDt.getId(),true);
				StockTran stockTran2 = new StockTran();
				stockTran2.setBarcode(packDt.getBarcode());
				stockTran2.setCreatedBy(principal.getName());
				stockTran2.setCreatedDate(new Date());
				stockTran2.setCurrStatus("CONSIGNMENTISS");
				stockTran2.setCurrStk(true);
				stockTran2.setLocation(stockTran.getLocation());
				stockTran2.setRefStkTranId(stockTran.getId());
				stockTran2.setRefTranId(consigDt.getId());
				stockTran2.setTranDate(consigMt.getInvDate());
				stockTran2.setTranType("CONSIGNMENTISS");
				stockTran2.setParty(consigMt.getParty());
				stockTran2.setEmployee(consigMt.getEmployee());
				stockTran2.setStockMt(stockTran.getStockMt());
				stockTranService.save(stockTran2);
				
				stockTran.setCurrStk(false);
				stockTran.setIssueDate(consigMt.getInvDate());
				stockTranService.save(stockTran);
				
				
			}
			retVal = "1";
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}

	@Override
	public String locationWiseStockTransfer(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		ConsigMt consigMt = findOne(pMtId);
		JSONArray jsonConsigDt = new JSONArray(data);
		
		try {
			for (int y = 0; y < jsonConsigDt.length(); y++) {
				
				JSONObject dataConsigDt = (JSONObject) jsonConsigDt.get(y);
			
				StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(dataConsigDt.get("barcode").toString(), true, false);
				if(stockMt != null && stockMt.getCurrStk().equals(true)) {
					
					ConsigDt consigDt2 = consigDtService.findByConsigMtAndBarcode(consigMt,dataConsigDt.get("barcode").toString());
					if(consigDt2 != null) {
						return "Duplicate Barcode";
					}
				
					ConsigDt consigDt =new ConsigDt();
					consigDt.setBarcode(stockMt.getBarcode());
					consigDt.setCreatedBy(principal.getName());
					consigDt.setCreatedDate(new Date());
					consigDt.setDesign(stockMt.getDesign());
					consigDt.setGrossWt(stockMt.getGrossWt());
					consigDt.setNetWt(stockMt.getNetWt());
					consigDt.setConsigMt(consigMt);
					consigDt.setPcs(stockMt.getQty());
					consigDt.setMetalValue(stockMt.getMetalValue());
					consigDt.setLabValue(stockMt.getLabourValue());
				
					
					
					consigDtService.save(consigDt);
					
					List<StockMetalDt>stockMetalDts =stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
					for(StockMetalDt stockMetalDt :stockMetalDts) {
						ConsigMetalDt consigMetalDt = new ConsigMetalDt();
						consigMetalDt.setColor(stockMetalDt.getColor());
						consigMetalDt.setCreateDate(new Date());
						consigMetalDt.setCreatedBy(principal.getName());
						consigMetalDt.setMainMetal(stockMetalDt.getMainMetal());
						consigMetalDt.setMetalPcs(stockMetalDt.getMetalPcs());
						consigMetalDt.setMetalWeight(stockMetalDt.getMetalWt());
						consigMetalDt.setConsigDt(consigDt);
						consigMetalDt.setConsigMt(consigMt);
						consigMetalDt.setPartNm(stockMetalDt.getPartNm());
						consigMetalDt.setPurity(stockMetalDt.getPurity());
						consigMetalDt.setPurityConv(stockMetalDt.getPurityConv());
						consigMetalDt.setMetalRate(Math.round((stockMt.getMetalValue() / stockMt.getNetWt())*1000.0)/1000.0);
						consigMetalDtService.save(consigMetalDt);
						
					}
					
					List<StockStnDt>stockStnDts=stockStnDtService.findByStockMtAndDeactive(stockMt, false);
					for(StockStnDt stockStnDt :stockStnDts) {
						ConsigStnDt consigStnDt =new ConsigStnDt();
						consigStnDt.setCarat(stockStnDt.getCarat());
						consigStnDt.setCreatedBy(principal.getName());
						consigStnDt.setCreatedDate(new Date());
						consigStnDt.setConsigDt(consigDt);
						consigStnDt.setConsigMt(consigMt);
						consigStnDt.setPartNm(stockStnDt.getPartNm());
						consigStnDt.setQuality(stockStnDt.getQuality());
						consigStnDt.setSetting(stockStnDt.getSetting());
						consigStnDt.setSettingType(stockStnDt.getSettingType());
						consigStnDt.setShape(stockStnDt.getShape());
						consigStnDt.setSieve(stockStnDt.getSieve());
						consigStnDt.setSize(stockStnDt.getSize());
						consigStnDt.setSizeGroup(stockStnDt.getSizeGroup());
						consigStnDt.setStone(stockStnDt.getStone());
						consigStnDt.setStoneType(stockStnDt.getStoneType());
						consigStnDt.setSubShape(stockStnDt.getSubShape());
						consigStnDt.setCenterStone(stockStnDt.getCenterStone());
						consigStnDtService.save(consigStnDt);
						
						
					}
					
					
					List<StockCompDt>stockCompDts =stockCompDtService.findByStockMtAndDeactive(stockMt, false);
					for(StockCompDt stockCompDt :stockCompDts) {
						ConsigCompDt consigCompDt = new ConsigCompDt();
						consigCompDt.setColor(stockCompDt.getColor());
						consigCompDt.setComponent(stockCompDt.getComponent());
						consigCompDt.setCompQty(stockCompDt.getCompQty());
						consigCompDt.setCreatedBy(principal.getName());
						consigCompDt.setCreatedDate(new Date());
						consigCompDt.setConsigDt(consigDt);
						consigCompDt.setConsigMt(consigMt);
						consigCompDt.setPurity(stockCompDt.getPurity());
						consigCompDt.setPurityConv(stockCompDt.getPurityConv());
						consigCompDt.setCompWt(stockCompDt.getCompWt());
					
						consigCompDtService.save(consigCompDt);
						
						
						
					}
					stockMt.setCurrStk(false);
					stockMtService.save(stockMt);
					
					StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(dataConsigDt.get("barcode").toString(), true);
					StockTran stockTran2 = new StockTran();
					stockTran2.setBarcode(consigDt.getBarcode());
					stockTran2.setCreatedBy(principal.getName());
					stockTran2.setCreatedDate(new Date());
					stockTran2.setCurrStatus("CONSIGNMENTISS");
					stockTran2.setCurrStk(true);
					stockTran2.setLocation(stockTran.getLocation());
					stockTran2.setRefStkTranId(stockTran.getId());
					stockTran2.setRefTranId(consigDt.getId());
					stockTran2.setTranDate(consigMt.getInvDate());
					stockTran2.setTranType("CONSIGNMENTISS");
					stockTran2.setParty(consigMt.getParty());
					stockTran2.setEmployee(consigMt.getEmployee());
					stockTran2.setStockMt(stockMt);
					stockTranService.save(stockTran2);
					
					stockTran.setCurrStk(false);
					stockTran.setIssueDate(consigMt.getInvDate());
					stockTranService.save(stockTran);
					
					
					
					
				}else {
					
					return "Item Not Found In Stock";
				}
				
			}
			
			retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public String deleteConsigMt(Integer mtId,Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		
		ConsigMt consigMt = findOne(mtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = consigMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
			
			
			
			List<ConsigDt> consigDts = consigDtService.findByConsigMt(consigMt);
			
			for (ConsigDt consigDt : consigDts) {
				
				if(consigDt.getAdjQty()>0) {
					
					return "Can not Delete, Record present in consignment return or sales invoice";
					
				}else {
					
					
					
					List<ConsigStnDt> consigStnDts=consigStnDtService.findByConsigDt(consigDt);
					for(ConsigStnDt consigStnDt :consigStnDts) {
						consigStnDtService.delete(consigStnDt.getId());
						
					}
					
					List<ConsigMetalDt> consigMetalDts = consigMetalDtService.findByConsigDt(consigDt);
					for(ConsigMetalDt consigMetalDt :consigMetalDts) {
						consigMetalDtService.delete(consigMetalDt.getId());
						
					}
					
					List<ConsigCompDt> consigCompDts = consigCompDtService.findByConsigDt(consigDt);
					for(ConsigCompDt consigCompDt :consigCompDts) {
						consigCompDtService.delete(consigCompDt.getId());
						
					}
					
					
					List<ConsigLabDt> consigLabDts = consigLabDtService.findByConsigDt(consigDt);
					for(ConsigLabDt consigLabDt :consigLabDts) {
					consigLabDtService.delete(consigLabDt.getId());
						
					}
					
					//PackDt
					if(consigDt.getRefTranDtId() != null) {
					ConsigDt consigDt2 = consigDtService.findByRefTranDtIdAndTranType(consigDt.getRefTranDtId(), consigDt.getTranType());
					PackDt packDt = packDtService.findOne(consigDt2.getRefTranDtId());
					packDt.setAdjQty(packDt.getAdjQty() - consigDt2.getPcs());
					packDtService.save(packDt);
					}
					
					consigDtService.delete(consigDt.getId());
					
					StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("CONSIGNMENTISS", consigDt.getId(), true);
					if(stockTran !=null) {
						
						StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
						stockTran2.setCurrStk(true);
						stockTranService.save(stockTran2);
						
						stockTranService.delete(stockTran.getId());
						
					}
					
					
					if(consigDt.getRefTranDtId() == null && consigDt.getTranType() == null) {
				//	StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(consigDt.getBarcode(), false, false);
						
					StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());	
					stockMt.setCurrStk(true);
					stockMtService.save(stockMt);
					}
					
				}
				
			}
			
			List<ConsigRmMetalDt> consigRmMetalDts = consigRmMetalDtService.findByConsigMt(consigMt);
			for (ConsigRmMetalDt consigRmMetalDt : consigRmMetalDts) {
				consigRmMetalDtService.consigRmMetalDtDelete(consigRmMetalDt.getId(), principal);
			}
			
			List<ConsigRmCompDt> consigRmCompDts = consigRmCompDtService.findByConsigMt(consigMt);
			for (ConsigRmCompDt consigRmCompDt : consigRmCompDts) {
				consigRmCompDtService.consigRmCompDtDelete(consigRmCompDt.getId(), principal);
			}
			
			List<ConsigRmStnDt> consigRmStnDts = consigRmStnDtService.findByConsigMt(consigMt);
			for (ConsigRmStnDt consigRmStnDt : consigRmStnDts) {
				consigRmStnDtService.consigRmStnDtDelete(consigRmStnDt.getId(), principal);
			}
		
			delete(consigMt.getId());
			
			retVal ="1";
		}else {
			
			return "Can Not Delete BackDate Entry";
		
		}
		
	
		return retVal;
	}

	@Override
	public List<ConsigMt> findByParty(Party party) {
		// TODO Auto-generated method stub
		return consigMtRepository.findByParty(party);
	}

	@Override
	public String consigMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
	StringBuilder sb = new StringBuilder();
		
		
		Page<ConsigMt> consigMtList = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		consigMtList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(consigMtList.getTotalElements()).append(",\"rows\": [");

		for (ConsigMt consigMt : consigMtList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(consigMt.getInvDate()));

			sb.append("{\"id\":\"").append(consigMt.getId())
			.append("\",\"invNo\":\"")
			.append(consigMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(consigMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(consigMt.getParty().getName())
			.append("\",\"location\":\"")
			.append(consigMt.getLocation().getName())
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/marketing/transactions/consigMt/edit/")
								.append(consigMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteConsigMt(event,");					
								sb.append(consigMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(consigMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/marketing/transactions/consigMt/edit/")
					.append(consigMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteConsigMt(event,")	
						.append(consigMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(consigMt.getId())
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
	public String saveConsigMt(ConsigMt consigMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result,Integer pPartyIds,Integer pLocationIds,Integer pEmployeeIds,String vTranDate) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/marketing/transactions/consigMt/add.html";
	
		if (result.hasErrors()) {
			return "consigMt/add";
		}
		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				consigMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno =getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				consigMt.setSrNo(++maxSrno);
				int si = maxSrno.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				Integer presentYear = Integer.parseInt(vYear);
				Integer nextYear = presentYear + 1;
				
				String bagSr = null;
				
				switch (si) {
				case 1:
					bagSr = "000"+maxSrno;
					break;
					
				case 2:
					bagSr = "00"+maxSrno;
					break;
					
				case 3:
					bagSr = "0"+maxSrno;
					break;
					
				default:
					bagSr = maxSrno.toString();
					break;
				}
				
				
				consigMt.setInvNo("DC/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				consigMt.setCreatedBy(principal.getName());
				consigMt.setCreatedDate(new java.util.Date());
				

			} else {
				consigMt.setModiBy(principal.getName());
				consigMt.setModiDate(new java.util.Date());
				
				if(pPartyIds != null) {

					Party party =  partyService.findOne(pPartyIds);
					Department department = departmentService.findOne(pLocationIds);
					
					consigMt.setParty(party);
					consigMt.setLocation(department);
				}

				
				action = "updated";
				retVal = "redirect:/marketing/transactions/consigMt.html";
			}
			

			if(consigMt.getHsnMast().getId() == null) {
				consigMt.setHsnMast(null);
			}
			
			if(consigMt.getModeOfTransport().getId() == null) {
				consigMt.setModeOfTransport(null);
			}
			
			if(pEmployeeIds != null) {
				Employee employee = employeeService.findOne(pEmployeeIds);
				consigMt.setEmployee(employee);
			}
			
			if(consigMt.getEmployee() != null) {
			if(consigMt.getEmployee().getId() == null) {
				consigMt.setEmployee(null);
			}
			}
			
			save(consigMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/consigMt/edit/"+consigMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
			
		}
		
	}

	@Override
	public String packingPickupList(Integer partyId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		String str = null;
		Party party = partyService.findOne(partyId);
		List<PackMt> packMts = packMtService.findByParty(party);
		
		
		
			try {
				
				sb.append("{\"total\":").append(packMts.size()).append(",\"rows\": [");

				for (PackMt packMt : packMts) {
					
					Boolean adjQtyFlg = false;
					
					List<PackDt> packDts = packDtService.findByPackMt(packMt);
					
					for (PackDt packDt : packDts) {
						
						 if(Math.round(packDt.getPcs() - packDt.getAdjQty()) > 0) {
							 adjQtyFlg = true;
						 }
						
					}
				
					if(adjQtyFlg) {
					sb.append("{\"id\":\"")
					.append(packMt.getId())
					.append("\",\"invNo\":\"")
					.append(packMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(packMt.getInvDateStr())
					.append("\",\"party\":\"")
					.append(packMt.getParty().getName())
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
	public String consignMentPickupListing(Integer partyId,String tranType) {
		// TODO Auto-generated method stub
		
		
		String str="";
		StringBuilder sb = new StringBuilder();
		try {
			
			
			if(tranType.equalsIgnoreCase("consigRet")) {
			List<Object[]> objects =new ArrayList<Object[]>();
			
			 SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call JSP_consigIssPickup(?) }");
			query.setParameter(1, partyId);
			objects = query.getResultList();
			
			 
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
				
				 
				 String	invDate="";
				
				 if(list[0] != null ){
					 Date inDate = dfOutput.parse((list[0] != null ? list[0] : "").toString());
					 invDate = dfInput.format(inDate);
				 }
					
					sb.append("{\"invDate\":\"")
				     .append(invDate)
				     .append("\",\"invNo\":\"")
				     .append(list[1] != null ? list[1] : "")
				     .append("\",\"mainStyleNo\":\"")
				     .append(list[2] != null ? list[2] : "")
				     .append("\",\"barcode\":\"")
				     .append(list[3] != null ? list[3] : "")
				     .append("\",\"pcs\":\"")
				     .append(list[4] != null ? list[4] : "")
				     .append("\",\"grossWt\":\"")
					 .append(list[5] != null ? list[5] : "")
				     .append("\",\"netWt\":\"")
					 .append(list[6] != null ? list[6] : "")
				     .append("\",\"ktColor\":\"")
					 .append(list[7] != null ? list[7] +"-"+ list[8]: "")
					 .append("\",\"location\":\"")
					 .append(list[9] != null ? list[9] : "")
					 .append("\",\"party\":\"")
					 .append(list[10] != null ? list[10] : "")
					 .append("\",\"mtid\":\"")
					 .append(list[11] != null ? list[11] : "")
					 .append("\",\"dtid\":\"")
					 .append(list[12] != null ? list[12] : "")
					 .append("\",\"id\":\"")
					 .append(list[12] != null ? list[12] : "")
					 .append("\"},");
				}
			
			 str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
			return str;
		
			
			}else {
				
				Party party = partyService.findOne(partyId);
				
				
				List<ConsigMt> consigMts = findByParty(party);
				
				
				
					try {
						
						sb.append("{\"total\":").append(consigMts.size()).append(",\"rows\": [");

						for (ConsigMt consigMt : consigMts) {
							
							Boolean adjQtyFlg = false;
							
							List<ConsigDt> consigDts = consigDtService.findByConsigMt(consigMt);
							
							for (ConsigDt consigDt : consigDts) {
								
								 if(Math.round(consigDt.getPcs() - consigDt.getAdjQty()) > 0) {
									 adjQtyFlg = true;
								 }
								
							}
						
							if(adjQtyFlg) {
							sb.append("{\"id\":\"")
							.append(consigMt.getId())
							.append("\",\"invNo\":\"")
							.append(consigMt.getInvNo())
							.append("\",\"invDate\":\"")
							.append(consigMt.getInvDateStr())
							.append("\",\"party\":\"")
							.append(consigMt.getParty().getName())
							.append("\",\"location\":\"")
							.append(consigMt.getLocation().getName())
							.append("\"},");
							}
						}

						str = sb.toString();
						str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
						str += "]}";
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		

			
		
		
	return str;
	}

	@Override
	public String stockList(Integer locationId) {
		// TODO Auto-generated method stub
Department location = departmentService.findOne(locationId);
		
		List<StockMt> stockMts = stockMtService.findByLocationAndCurrStkAndDeactive(location, true,false);
				
		String str="";
		 StringBuilder sb = new StringBuilder();
		 
		 try {
			 
			 if(stockMts.size() > 0) {
			 sb.append("{\"total\":").append(stockMts.size()).append(",\"rows\": [");
			 
			 for(StockMt stockMt :stockMts){
				 
				 List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
				 String purityVal="";
				 for(StockMetalDt stockMetalDt : stockMetalDts) {
					 if(purityVal.length()>0) {
						 purityVal=purityVal+","+stockMetalDt.getPurity().getName()+"-"+stockMetalDt.getColor().getName();
					 }else {
						 purityVal=stockMetalDt.getPurity().getName()+"-"+stockMetalDt.getColor().getName();
					 }
					 
				 }
				 
				 sb.append("{\"id\":\"")
				 .append(stockMt.getMtId())
				 .append("\",\"barcode\":\"")
				 .append(stockMt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(stockMt.getDesign().getMainStyleNo())
				 .append("\",\"ktColor\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(stockMt.getQty())
				 .append("\",\"grossWt\":\"")
				 .append(stockMt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(stockMt.getNetWt())
				 .append("\"},");
			 
				 
			 }
			 
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";

			 }	
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	
	return str;
	}

	@Override
	public String getDtItemSummary(Integer mtId) {
		// TODO Auto-generated method stub
		
		ConsigMt consigMt = findOne(mtId);
		
		List<ConsigDt> consigDts = consigDtService.findByConsigMt(consigMt);
		
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
			
			for (ConsigDt consigDt : consigDts) {
				
				totPcs += consigDt.getPcs();
				grossWt += consigDt.getGrossWt();
				netWt += consigDt.getNetWt();
				
				
				List<ConsigStnDt>consigStnDts = consigStnDtService.findByConsigDt(consigDt);
				for (ConsigStnDt consigStnDt : consigStnDts) {
					
					if(consigStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
						diaStone += consigStnDt.getStone();
						diaCarat += consigStnDt.getCarat();
					}else {
						colStone += consigStnDt.getStone();
						colCarat += consigStnDt.getCarat();
						
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
	public String consigRowMetalPickupList(Integer partyId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		String str = null;

		try {
		
			List<Object[]> objects =new ArrayList<Object[]>();
			
			 SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call JSP_consigRmMetalIssPickup(?) }");
			query.setParameter(1, partyId);
			objects = query.getResultList();
			
			 
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
				 Double issueWt =0.0; 
				 
				 String	invDate="";
				
				 if(list[0] != null ){
					 Date inDate = dfOutput.parse((list[0] != null ? list[0] : "").toString());
					 invDate = dfInput.format(inDate);
				 }
					
					sb.append("{\"invDate\":\"")
				     .append(invDate)
				     .append("\",\"invNo\":\"")
				     .append(list[1] != null ? list[1] : "")
				     .append("\",\"metal\":\"")
				     .append(list[2] != null ? list[2] : "")
				     .append("\",\"metalWt\":\"")
				     .append(list[3] != null ? list[3] : "")
				     .append("\",\"balanceWt\":\"")
				     .append(list[4] != null ? list[4] : "")
				     .append("\",\"ktColor\":\"")
					 .append(list[5] != null ? list[5] +"-"+ list[6]: "")
					 .append("\",\"invLocation\":\"")
					 .append(list[7] != null ? list[7] : "")
					 .append("\",\"location\":\"")
					 .append(list[8] != null ? list[8] : "")
					 .append("\",\"party\":\"")
					 .append(list[9] != null ? list[9] : "")
					 .append("\",\"mtid\":\"")
					 .append(list[10] != null ? list[10] : "")
					 .append("\",\"dtid\":\"")
					 .append(list[11] != null ? list[11] : "")
					 .append("\",\"id\":\"")
					 .append(list[11] != null ? list[11] : "")
					 .append("\",\"issueWt\":\"")
					 .append(issueWt)
					 .append("\"},");
				}
			
			 str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
			return str;
	}catch (Exception e) {
		// TODO: handle exception
	}
		
		return null;
		
		
		
		
		
		//		Party party = partyService.findOne(partyId);
//		List<ConsigMt> consigMts = findByParty(party);
		
		
		
//			try {
//				
//				sb.append("{\"total\":").append(consigMts.size()).append(",\"rows\": [");
//
//				for (ConsigMt consigMt : consigMts) {
//					
//					Boolean adjQtyFlg = false;
//					
//					List<ConsigRmMetalDt> consigRmMetalDts = consigRmMetalDtService.findByConsigMt(consigMt);
//					
//					for (ConsigRmMetalDt consigRmMetalDt : consigRmMetalDts) {
//						
//						 if((consigRmMetalDt.getMetalWt() - consigRmMetalDt.getAdjWt()) > 0) {
//							 adjQtyFlg = true;
//						 }
//						
//					}
//				
//					if(adjQtyFlg) {
//					sb.append("{\"id\":\"")
//					.append(consigMt.getId())
//					.append("\",\"invNo\":\"")
//					.append(consigMt.getInvNo())
//					.append("\",\"invDate\":\"")
//					.append(consigMt.getInvDateStr())
//					.append("\",\"party\":\"")
//					.append(consigMt.getParty().getName())
//					.append("\",\"location\":\"")
//					.append(consigMt.getLocation().getName())
//					.append("\"},");
//					}
//				}
//
//				str = sb.toString();
//				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
//				str += "]}";
//
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		
	
	}

	@Override
	public String consigRowStonePickupList(Integer partyId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		String str = null;
		
		
	
		try {
		
			List<Object[]> objects =new ArrayList<Object[]>();
			
			 SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call JSP_consigRmStoneIssPickup(?) }");
			query.setParameter(1, partyId);
			objects = query.getResultList();
			
			 
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
				 
				 String	invDate="";
				
				 if(list[0] != null ){
					 Date inDate = dfOutput.parse((list[0] != null ? list[0] : "").toString());
					 invDate = dfInput.format(inDate);
				 }
					
					sb.append("{\"invDate\":\"")
				     .append(invDate)
				     .append("\",\"invNo\":\"")
				     .append(list[1] != null ? list[1] : "")
				     .append("\",\"location\":\"")
				     .append(list[2] != null ? list[2] : "")
				     .append("\",\"invLocation\":\"")
				     .append(list[3] != null ? list[3] : "")
				     .append("\",\"party\":\"")
				     .append(list[4] != null ? list[4] : "")
				     .append("\",\"mtId\":\"")
					 .append(list[5] != null ? list[5] : "")
					 .append("\",\"dtId\":\"")
					 .append(list[6] != null ? list[6] : "")
					 .append("\",\"id\":\"")
					 .append(list[6] != null ? list[6] : "")
					 .append("\",\"balCarat\":\"")
					 .append(list[7] != null ? list[7] : "")
					 .append("\",\"balStone\":\"")
					 .append(list[8] != null ? list[8] : "")
					 .append("\",\"stone\":\"")
					 .append(list[9] != null ? list[9] : "")
					 .append("\",\"carat\":\"")
					 .append(list[10] != null ? list[10] : "")
					 .append("\",\"rate\":\"")
					 .append(list[11] != null ? list[11] : "")
					 .append("\",\"amount\":\"")
					 .append(list[12] != null ? list[12] : "")
					 .append("\",\"stoneType\":\"")
					 .append(list[13] != null ? list[13] : "")
					 .append("\",\"shape\":\"")
					 .append(list[14] != null ? list[14] : "")
					 .append("\",\"subShape\":\"")
					 .append(list[15] != null ? list[15] : "")
					 .append("\",\"quality\":\"")
					 .append(list[16] != null ? list[16] : "")
					 .append("\",\"size\":\"")
					 .append(list[17] != null ? list[17] : "")
					 .append("\",\"sieve\":\"")
					 .append(list[18] != null ? list[18] : "")
					 .append("\",\"sizeGroupStr\":\"")
					 .append(list[19] != null ? list[19] : "")
					 .append("\",\"issueStone\":\"")
					 .append("0")
					 .append("\",\"issueCarat\":\"")
					 .append("0")
					 .append("\"},");
				}
			
			 str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
			return str;
	}catch (Exception e) {
		// TODO: handle exception
	}
		
//		Party party = partyService.findOne(partyId);
//		List<ConsigMt> consigMts = findByParty(party);
//		
//		
//		
//			try {
//				
//				sb.append("{\"total\":").append(consigMts.size()).append(",\"rows\": [");
//
//				for (ConsigMt consigMt : consigMts) {
//					
//					Boolean adjQtyFlg = false;
//					
//					List<ConsigRmStnDt> consigRmStnDts = consigRmStnDtService.findByConsigMt(consigMt);
//					
//					for (ConsigRmStnDt consigRmStnDt : consigRmStnDts) {
//						
//						 if(Math.round(consigRmStnDt.getCarat() - consigRmStnDt.getAdjWt()) > 0) {
//							 adjQtyFlg = true;
//						 }
//						
//					}
//				
//					if(adjQtyFlg) {
//					sb.append("{\"id\":\"")
//					.append(consigMt.getId())
//					.append("\",\"invNo\":\"")
//					.append(consigMt.getInvNo())
//					.append("\",\"invDate\":\"")
//					.append(consigMt.getInvDateStr())
//					.append("\",\"party\":\"")
//					.append(consigMt.getParty().getName())
//					.append("\",\"location\":\"")
//					.append(consigMt.getLocation().getName())
//					.append("\"},");
//					}
//				}
//
//				str = sb.toString();
//				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
//				str += "]}";
//
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		
		
	return str;
	}

	@Override
	public String consigRowCompPickupList(Integer partyId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		String str = null;
		
		
		try {
			
			List<Object[]> objects =new ArrayList<Object[]>();
			
			 SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call JSP_consigRmCompIssPickup(?) }");
			query.setParameter(1, partyId);
			objects = query.getResultList();
			
			 
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
				 Double issueWt =0.0; 
				 Double issueQty =0.0;
				 
				 
				 String	invDate="";
				
				 if(list[0] != null ){
					 Date inDate = dfOutput.parse((list[0] != null ? list[0] : "").toString());
					 invDate = dfInput.format(inDate);
				 }
					
					sb.append("{\"invDate\":\"")
				     .append(invDate)
				     .append("\",\"invNo\":\"")
				     .append(list[1] != null ? list[1] : "")
				     .append("\",\"metal\":\"")
				     .append(list[2] != null ? list[2] : "")
				     .append("\",\"metalWt\":\"")
				     .append(list[3] != null ? list[3] : "")
				     .append("\",\"balanceWt\":\"")
				     .append(list[4] != null ? list[4] : "")
				     .append("\",\"ktColor\":\"")
					 .append(list[5] != null ? list[5] +"-"+ list[6]: "")
					 .append("\",\"invLocation\":\"")
					 .append(list[7] != null ? list[7] : "")
					 .append("\",\"location\":\"")
					 .append(list[8] != null ? list[8] : "")
					 .append("\",\"party\":\"")
					 .append(list[9] != null ? list[9] : "")
					 .append("\",\"mtid\":\"")
					 .append(list[10] != null ? list[10] : "")
					 .append("\",\"dtid\":\"")
					 .append(list[11] != null ? list[11] : "")
					 .append("\",\"id\":\"")
					 .append(list[11] != null ? list[11] : "")
					 .append("\",\"component\":\"")
					 .append(list[12] != null ? list[12] : "")
					 .append("\",\"qty\":\"")
					 .append(list[13] != null ? list[13] : "")
					 .append("\",\"balanceQty\":\"")
					 .append(list[14] != null ? list[14] : "")
					 .append("\",\"issueWt\":\"")
					 .append(issueWt)
					 .append("\",\"issueQty\":\"")
					 .append(issueQty)
					 .append("\"},");
				}
			
			 str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
			return str;
	}catch (Exception e) {
		// TODO: handle exception
	}
		
		return null;
		
		
//		Party party = partyService.findOne(partyId);
//		List<ConsigMt> consigMts = findByParty(party);
//		
//			try {
//				
//				sb.append("{\"total\":").append(consigMts.size()).append(",\"rows\": [");
//
//				for (ConsigMt consigMt : consigMts) {
//					
//					Boolean adjQtyFlg = false;
//					
//					List<ConsigRmCompDt> consigRmCompDts = consigRmCompDtService.findByConsigMt(consigMt);
//					
//					for (ConsigRmCompDt consigRmCompDt : consigRmCompDts) {
//						
//						 if(Math.round(consigRmCompDt.getMetalWt() - consigRmCompDt.getAdjWt()) > 0) {
//							 adjQtyFlg = true;
//						 }
//						
//					}
//				
//					if(adjQtyFlg) {
//					sb.append("{\"id\":\"")
//					.append(consigMt.getId())
//					.append("\",\"invNo\":\"")
//					.append(consigMt.getInvNo())
//					.append("\",\"invDate\":\"")
//					.append(consigMt.getInvDateStr())
//					.append("\",\"party\":\"")
//					.append(consigMt.getParty().getName())
//					.append("\",\"location\":\"")
//					.append(consigMt.getLocation().getName())
//					.append("\"},");
//					}
//				}
//
//				str = sb.toString();
//				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
//				str += "]}";
//
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		
		
//	return str;
	}

	

}
