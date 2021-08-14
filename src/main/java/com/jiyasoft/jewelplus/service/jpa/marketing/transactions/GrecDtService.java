package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QGrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockCompDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMetalDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockStnDtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IGrecDtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockTranRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class GrecDtService implements IGrecDtService{
	
	@Autowired
	private IGrecDtRepository grecDtRepository;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private IGrecStnDtService grecStnDtService;
	
	@Autowired
	private IGrecMetalDtService grecMetalDtService;
	
	@Autowired
	private IGrecMtService grecMtService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IDesignService designService;

	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;

	@Autowired
	private IGrecCompDtService grecCompDtService;

	@Autowired
	private EntityManager entityManager;	
	
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	

	@Autowired
	private IStockMtRepository stockMtRepository;
	
	@Autowired
	private IStockMetalDtRepository stockMetalDtRepository;
	
	@Autowired
	private IStockStnDtRepository stockStnDtRepository;
	
	@Autowired
	private IStockCompDtRepository stockCompDtRepository;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	


	@Override
	public void save(GrecDt grecDt) {
		
		grecDtRepository.save(grecDt);
	}

	@Override
	public void delete(int id) {
		grecDtRepository.delete(id);
	}

	

	@Override
	public GrecDt findOne(int id) {
		
		return grecDtRepository.findOne(id);
	}

	@Override
	public GrecDt findByUniqueId(Long uniqueId) {
	
		return grecDtRepository.findByUniqueId(uniqueId);
	}



	
	  @Override public String transactionalSave(GrecDt grecDt, Integer id, Integer vGrecMtId, String metalDtData, Principal principal, Boolean
	  netWtWithCompFlg) {

			String action = "";
			
			GrecMt grecMt =grecMtService.findOne(vGrecMtId);
				
			Design design = null;
			
			
			Purity purity = purityService.findOne(grecDt.getPurity().getId());
			design =designService.findByMainStyleNoAndDeactive(grecDt.getDesign().getMainStyleNo(), false);
			
			grecDt.setDesign(design);
			grecDt.setGrecMt(grecMt);
		
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				grecDt.setCreatedBy(principal.getName());
				grecDt.setCreatedDate(new java.util.Date());
				
				action = "added";
				
			} else {
				
				GrecDt grecDtDtEdit = findOne(id);
		
				grecDtDtEdit.setModiBy(principal.getName());
				grecDtDtEdit.setModiDate(new java.util.Date());
							
				save(grecDtDtEdit);
				
				
				action = "updated";
				
			}
			

					
			if(action.equals("added")){
				
				grecDt.setPurityConv(purity.getPurityConv());
				
				grecDt.setRemark(grecDt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
				
				save(grecDt);
				
				grecMetalDtService.addGrecMetalDt(metalDtData, grecMt, grecDt, principal);
				
				List<DesignStone> designStones = designStoneService.findByDesign(design); 
				grecStnDtService.setGrecStnDt(designStones, grecMt, grecDt,principal);
				
				List<DesignComponent> designComponents = designComponentService.findByDesign(design);
				grecCompDtService.setGrecCompDt(designComponents, grecMt, grecDt, principal);
				
				
				 Double totNetWt=0.0;	
				 String ktDesc="";
				 List<GrecMetalDt> grecMetalDts=grecMetalDtService.findByGrecDt(grecDt);
				 if(grecMetalDts.size()>0){
					 for(GrecMetalDt grecMetalDt :grecMetalDts){
						 totNetWt  += grecMetalDt.getMetalWeight();	 
					 }
					 
				 }
				 
						
					List<GrecStnDt> grecStnDts = grecStnDtService.findByGrecDt(grecDt);
					Double totStnCarat = 0.0;
					for(GrecStnDt grecStnDt:grecStnDts){
						totStnCarat += grecStnDt.getCarat();
					}
					
					Double temVal = totStnCarat/5;
					Double totGrossWt = totNetWt+temVal;
					
					List<GrecCompDt> grecCompDts = grecCompDtService.findByGrecDt(grecDt);
					Double totCompMetalWt = 0.0;
					for(GrecCompDt grecCompDt:grecCompDts){
						totCompMetalWt += grecCompDt.getMetalWt();
					}
					
					totGrossWt += totCompMetalWt;
					
					Double grossWtdiff = Math.round((grecDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
					
					GrecMetalDt grecMetalDt = grecMetalDtService.findByGrecDtAndMainMetal(grecDt,true);
					
					grecMetalDt.setMetalWeight(Math.round((grecMetalDt.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
					grecMetalDtService.save(grecMetalDt);
						
					
					
					
			/* Add code here for StockMt */				
					
					StockMt stockMt =  new StockMt();
					stockMt.setBarcode(grecDt.getBarcode());
				//	stockMt.setLocation(grecDt.getLocation());
					stockMt.setMemoParty(grecMt.getParty());
					stockMt.setDesign(design);
					stockMt.setCategory(grecDt.getDesign().getCategory());
					stockMt.setMetalValue(grecDt.getMetalValue());
					stockMt.setLabourValue(grecDt.getLabValue());
					stockMt.setFactoryCost(grecDt.getFinalPrice());
					stockMt.setNetWt((grecDt.getNetWt()));
					stockMt.setGrossWt((grecDt.getGrossWt()));
					stockMt.setCreatedDate(new Date());
					stockMt.setCreatedBy(principal.getName());
					stockMt.setCurrStk(true);
					stockMt.setQty(grecDt.getPcs());
					stockMt.setRefTranId(grecDt.getId());
					stockMt.setTranType("GREC");
					stockMtService.save(stockMt);
					
					
					
					//Dump record in stock table
					 List<GrecMetalDt> grecMetalDts2=grecMetalDtService.findByGrecDt(grecDt);
					for (GrecMetalDt grecMetalDt2 : grecMetalDts2) {
						
						StockMetalDt stockMetalDt =  new StockMetalDt();
						stockMetalDt.setPartNm(grecMetalDt2.getPartNm());
						stockMetalDt.setMainMetal(true);
						stockMetalDt.setMetalPcs(grecMetalDt2.getMetalPcs());
						stockMetalDt.setMetalWt(grecMetalDt2.getMetalWeight());
						stockMetalDt.setPurity(grecMetalDt2.getPurity());
						stockMetalDt.setColor(grecMetalDt2.getColor());
						stockMetalDt.setCreatedDate(new Date());
						stockMetalDt.setCreatedBy(principal.getName());
						stockMetalDt.setStockMt(stockMt);
						stockMetalDt.setMetalRate(grecMetalDt2.getMetalRate());
						stockMetalDtService.save(stockMetalDt);
					}
					
					List<GrecStnDt> grecStnDts2 = grecStnDtService.findByGrecDt(grecDt);
					for (GrecStnDt grecStnDt2 : grecStnDts2) {
						
						StockStnDt stockStnDt = new StockStnDt();
						stockStnDt.setStoneType(grecStnDt2.getStoneType());
						stockStnDt.setShape(grecStnDt2.getShape());
						stockStnDt.setQuality(grecStnDt2.getQuality());
						stockStnDt.setSize(grecStnDt2.getSize());
						stockStnDt.setSieve(grecStnDt2.getSieve());
						stockStnDt.setSizeGroup(grecStnDt2.getSizeGroup());
						stockStnDt.setStone(grecStnDt2.getStone());
						stockStnDt.setCarat(grecStnDt2.getCarat());
						stockStnDt.setRate(grecStnDt2.getStnRate());
						stockStnDt.setDiamValue(grecStnDt2.getStoneValue());
						stockStnDt.setPartNm(grecStnDt2.getPartNm());
						stockStnDt.setStockMt(stockMt);
						
						stockStnDt.setAvgRate(grecStnDt2.getStnRate());
						stockStnDt.setTransferRate(grecStnDt2.getStnRate());
						stockStnDt.setFactoryRate(grecStnDt2.getStnRate());
						
						stockStnDt.setCreatedDate(new Date());
						stockStnDt.setCreatedBy(principal.getName());
						
						stockStnDtService.save(stockStnDt);
					}
					
					List<GrecCompDt> grecCompDts2 =  grecCompDtService.findByGrecDt(grecDt);
					for (GrecCompDt grecCompDt2 : grecCompDts2) {
						
						StockCompDt stockCompDt =  new StockCompDt();
						stockCompDt.setColor(grecCompDt2.getColor());
						stockCompDt.setComponent(grecCompDt2.getComponent());
						stockCompDt.setCompQty(grecCompDt2.getCompQty());
						stockCompDt.setCreatedBy(principal.getName());
						stockCompDt.setCreatedDate(new Date());
						stockCompDt.setPurity(grecCompDt2.getPurity());
						stockCompDt.setPurityConv(grecCompDt2.getPurityConv());
						stockCompDt.setCompWt(grecCompDt2.getMetalWt());
						stockCompDt.setStockMt(stockMt);
						stockCompDtService.save(stockCompDt);
						
					}
					
					

					StockTran stockTran = new StockTran();
					stockTran.setBarcode(grecDt.getBarcode());
					stockTran.setCreatedBy(principal.getName());
					stockTran.setCreatedDate(new Date());
					stockTran.setCurrStatus("GREC");
			//		stockTran.setLocation(grecMt.getLocation());
					stockTran.setTranDate(new Date());
					stockTran.setTranType("OPSTK");
					stockTran.setCurrStk(true);
					stockTran.setRefTranId(grecDt.getId());
					stockTran.setTranDate(grecMt.getInvDate());
					stockTran.setTranType("GREC");
					stockTran.setParty(grecMt.getParty());
					stockTran.setStockMt(stockMt);
					stockTranService.save(stockTran);
					
				
			
			}else{
				
				grecMetalDtService.addGrecMetalDt(metalDtData, grecMt, grecDt, principal);
				
				 Double totNetWt=0.0;	
				 List<GrecMetalDt> grecMetalDts=grecMetalDtService.findByGrecDt(grecDt);
				 if(grecMetalDts.size()>0){
					 for(GrecMetalDt grecMetalDt :grecMetalDts){
						 totNetWt  += grecMetalDt.getMetalWeight();	 
					 }
					 
				 }
				 
						
					List<GrecStnDt> grecStnDts = grecStnDtService.findByGrecDt(grecDt);
					Double totStnCarat = 0.0;
					for(GrecStnDt grecStnDt:grecStnDts){
						totStnCarat += grecStnDt.getCarat();
					}
					
					Double temVal = totStnCarat/5;
					Double totGrossWt = totNetWt+temVal;
					
					List<GrecCompDt> grecCompDts = grecCompDtService.findByGrecDt(grecDt);
					Double totCompMetalWt = 0.0;
					for(GrecCompDt grecCompDt:grecCompDts){
						totCompMetalWt += grecCompDt.getMetalWt();
					}
					
					totGrossWt += totCompMetalWt;
					
					Double grossWtdiff = Math.round((grecDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
					
					GrecMetalDt grecMetalDt = grecMetalDtService.findByGrecDtAndMainMetal(grecDt,true);
					
					grecMetalDt.setMetalWeight(Math.round((grecMetalDt.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
					grecMetalDtService.save(grecMetalDt);
					
			}
			
			return action; 
			}
	 
	

	@Override
	public Page<GrecDt> searchAll(Integer limit, Integer offset, String sort, String order, String name, Integer mtId) {
		
		QGrecDt qGrecDt = QGrecDt.grecDt;
		BooleanExpression expression = qGrecDt.grecMt.id.eq(mtId);

		if(name !=null && name !=""){
			expression = qGrecDt.grecMt.id.eq(mtId).and(qGrecDt.design.mainStyleNo.like(name + "%"));
		}
		
		

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if (sort.equalsIgnoreCase("srNo")) {
			sort = "srNo";
		} 

			Page<GrecDt> grecDtList = (Page<GrecDt>) grecDtRepository.findAll(
					expression,
					new PageRequest(page, limit,
							(order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), sort));
			
			return grecDtList;
		
	}

	
	

	@Override
	public Integer getMaxSrNo(Integer mtId) {
		
		QGrecDt qGrecDt = QGrecDt.grecDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		List<Integer> maxItemno = query
			.from(qGrecDt)
			.where(qGrecDt.deactive.eq(false).and(qGrecDt.grecMt.id.eq(mtId)))
			.list(qGrecDt.srNo.max());

			retVal =maxItemno.get(0);	

		return retVal;
	}

	

	@Override
	public String getGrecDtTotal(Integer mtId) {
		
		String retVal = "";

		QGrecDt qGrecDt = QGrecDt.grecDt;
		JPAQuery query = new JPAQuery(entityManager);

		List<Double> totOrderQty = null;

		totOrderQty = query
				.from(qGrecDt)
				.where(qGrecDt.deactive.eq(false).and(
						qGrecDt.grecMt.id.eq(mtId)))
				.groupBy(qGrecDt.grecMt.id).list(qGrecDt.pcs.sum());

		if (totOrderQty.size() > 0) {

			Double ordQty = Math.round(totOrderQty.get(0) * 100.0) / 100.0;
			retVal = ordQty.toString();
		} else {
			retVal = "0";
		}

		return retVal;
	}

	@Override
	public String grecDtLisiting(Integer limit, Integer offset, String sort, String order, String search,Integer mtId, Principal principal,Boolean disableFlg) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("grecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

			StringBuilder sb = new StringBuilder();
			Page<GrecDt> grecDts = null;

			if ((search != null) && (search.trim().length() == 0)) {
				search = null;
			}
			
			
			grecDts = searchAll(limit, offset, sort, order, search,	mtId);
			
			sb.append("{\"total\":").append(grecDts.getTotalElements()).append(",\"rows\": [");
			
			for (GrecDt grecDtObj : grecDts) {
				
			List<GrecMetalDt> grecMetalDts = grecMetalDtService.findByGrecDt(grecDtObj);
			String purityVal = "";
			for (GrecMetalDt grecMetalDt : grecMetalDts) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+grecMetalDt.getPurity().getName()+"-"+grecMetalDt.getColor().getName();
				 }else {
					 purityVal=grecMetalDt.getPurity().getName()+"-"+grecMetalDt.getColor().getName();
				 }
			}
				
				sb.append("{\"id\":\"")
						.append(grecDtObj.getId())
						.append("\",\"style\":\"")
						.append(grecDtObj.getDesign().getMainStyleNo())
						.append("\",\"barcode\":\"")
						.append(grecDtObj.getBarcode() != null ? grecDtObj.getBarcode() : "")
						.append("\",\"ktCol\":\"")
						.append(purityVal) 
						.append("\",\"grossWt\":\"")
						.append((grecDtObj.getGrossWt() != null ? grecDtObj.getGrossWt() : ""))
						.append("\",\"netWt\":\"")
						.append((grecDtObj.getNetWt() != null ? grecDtObj.getNetWt(): ""))
						.append("\",\"metalRate\":\"")
						.append((grecDtObj.getMetalRate() != null ? grecDtObj.getMetalRate() : ""))
						.append("\",\"metalValue\":\"")
						.append((grecDtObj.getMetalValue() != null ? grecDtObj.getMetalValue() : ""))
						.append("\",\"stoneValue\":\"")
						.append((grecDtObj.getStoneValue() != null ? grecDtObj.getStoneValue() : ""))
						.append("\",\"compValue\":\"")
						.append((grecDtObj.getCompValue() != null ? grecDtObj.getCompValue() : ""))
						.append("\",\"labourValue\":\"")
						.append((grecDtObj.getLabValue() != null ? grecDtObj.getLabValue() : ""))
						.append("\",\"finalPrice\":\"")
						.append(grecDtObj.getFinalPrice() == null ? "" : grecDtObj.getFinalPrice())
						.append("\",\"remark\":\"")
						.append(grecDtObj.getRemark() == null ? "" : grecDtObj.getRemark())
						.append("\",\"image\":\"")
						.append(grecDtObj.getDesign().getDefaultImage() != null ? grecDtObj
								.getDesign().getDefaultImage() : "blank.png")
						.append("\",\"rLock\":\"")
						.append(grecDtObj.getrLock());	
						// 1 = lock & 0 = unlock
				
				
				if(!disableFlg) {
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
						sb.append("\",\"actionLock\":\"")
						.append("<a href='javascript:doGrecDtLockUnLock(")
						.append(grecDtObj.getId())
						.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
					
						
						sb.append("\",\"action1\":\"");
						
							sb.append("<a href='javascript:editGrecDt(")
							.append(grecDtObj.getId());
								
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						sb.append("\",\"action2\":\"");
					
						sb.append("<a  href='javascript:deleteGrecDt(event,")
						.append(grecDtObj.getId());	
						sb.append(");' class='btn btn-xs btn-danger triggerRemove")
						.append(grecDtObj.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						
					
				}else {
					
						sb.append("\",\"actionLock\":\"")
						.append("<a href='javascript:doGrecDtLockUnLock(")
						.append(grecDtObj.getId())
						.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
					
						
						sb.append("\",\"action1\":\"");
						if(roleRights.getCanEdit()){
							sb.append("<a href='javascript:editGrecDt(")
							.append(grecDtObj.getId());
								
						}else{
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						sb.append("\",\"action2\":\"");
						if(roleRights.getCanDelete()){
						sb.append("<a  href='javascript:deleteGrecDt(event,")
						.append(grecDtObj.getId());	
						}else{
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(");' class='btn btn-xs btn-danger triggerRemove")
						.append(grecDtObj.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
						}
						sb.append("\"},");
				}else {
					sb.append("\",\"actionLock\":\"")
					.append("")
					.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					 .append("\"},");
				}

			}

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";

			return str;
	
	}

	@Override
	public String updateFob(GrecDt grecDt, Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateGrossWt(GrecDt grecDt, Boolean netWtWithComp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteGrecDt(Integer dtId) {
		// TODO Auto-generated method stub
		GrecDt grecDt = findOne(dtId);

		StockMt stockMt2 = stockMtService.findByRefTranIdAndTranTypeAndDeactive(grecDt.getId(), "GREC", false);
		if(!stockMt2.getCurrStk()) {
			
			return "Can not Delete, Qty Adjusted";
			
		}else {
			List<GrecStnDt> grecStnDts = grecStnDtService.findByGrecDt(grecDt);
			for(GrecStnDt grecStnDt :grecStnDts) {
				grecStnDtService.delete(grecStnDt.getId());
			}
			
			 List<GrecMetalDt> grecMetalDts=grecMetalDtService.findByGrecDt(grecDt);
			for(GrecMetalDt grecMetalDt :grecMetalDts) {
				grecMetalDtService.delete(grecMetalDt.getId());
			}
			
			List<GrecCompDt> grecCompDts =  grecCompDtService.findByGrecDt(grecDt);
			for(GrecCompDt grecCompDt :grecCompDts) {
				grecCompDtService.delete(grecCompDt.getId());
			}
			
			
			
			List<StockCompDt> stockCompDts = stockCompDtService.findByStockMtAndDeactive(stockMt2, false);
			for (StockCompDt stockCompDt : stockCompDts) {
				stockCompDtRepository.delete(stockCompDt.getStkCompId());
			}
			
			List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMt2, false);
			for (StockStnDt stockStnDt : stockStnDts) {
				stockStnDtRepository.delete(stockStnDt.getStkStnId());
			}
			
			List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMt2, false);
			for (StockMetalDt stockMetalDt : stockMetalDts) {
				stockMetalDtRepository.delete(stockMetalDt.getStkMetalId());
			}
			
			stockMtRepository.delete(stockMt2.getMtId()); 
			
			StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("GREC", grecDt.getId(), true);
			if(stockTran !=null) {
				stockTranService.delete(stockTran.getId());
			}
			
			
		//	TranMt tranMt = tranMtService.findByRefMtIdAndCurrStk(dtId, true);
			
		//	tranMtService.delete(tranMt.getId());
			
			
			BagMt bagMt = bagMtService.findOne(stockMt2.getBagId());
		
				bagMt.setBagCloseFlg(false); 
			  
			  bagMt.setModiDate(new Date());
			  bagMtService.save(bagMt);
			
		
		}
			
			delete(grecDt.getId());
		
			return "1";
		
	}

	@Override
	public List<GrecDt> findByGrecMt(GrecMt grecMt) {
		// TODO Auto-generated method stub
		return grecDtRepository.findByGrecMt(grecMt);
	}

	

	
	
	
	
}
