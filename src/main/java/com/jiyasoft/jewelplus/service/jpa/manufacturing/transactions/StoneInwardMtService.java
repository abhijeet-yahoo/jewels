package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneInwardMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILooseStkConversionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StoneInwardMtService implements IStoneInwardMtService {
	
	@Autowired
	IStoneInwardMtRepository stoneInwardMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStoneInwardDtService stoneInwardDtService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private ILooseStkConversionService looseStkConversionService;

	@Override
	public List<StoneInwardMt> findAll() {
		return stoneInwardMtRepository.findAll();
	}

	@Override
	public Page<StoneInwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		return stoneInwardMtRepository
				.findAll(new PageRequest(page,limit,(order
						.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC ), sort));
	}

	@Override
	public void save(StoneInwardMt stoneInwardMt) {
		stoneInwardMtRepository.save(stoneInwardMt);	
	}

	@Override
	public void delete(int id) {
		StoneInwardMt stoneInwardMt = stoneInwardMtRepository.findOne(id);
		stoneInwardMt.setDeactive(true);
		stoneInwardMt.setDeactiveDt(new java.util.Date());
		stoneInwardMtRepository.save(stoneInwardMt);
	}

	@Override
	public Long count() {
		return stoneInwardMtRepository.count();
	}

	@Override
	public StoneInwardMt findOne(int id) {
		return stoneInwardMtRepository.findOne(id);
	}

	

	@Override
	public Map<Integer, String> getStoneInwardMtList() {
		
		Map<Integer, String> stoneInwardMtMap = new HashMap<Integer, String>();
		List<StoneInwardMt> stoneInwardMtList = findActiveStoneInwardMts();
		
		for(StoneInwardMt stoneInwardMt : stoneInwardMtList){
			stoneInwardMtMap.put(stoneInwardMt.getId(), stoneInwardMt.getInvNo());
			
		}
		return stoneInwardMtMap;
	}

	@Override
	public List<StoneInwardMt> findActiveStoneInwardMts() {
		QStoneInwardMt qStoneInwardMt = QStoneInwardMt.stoneInwardMt;
		BooleanExpression expression = qStoneInwardMt.deactive.eq(false);
		List<StoneInwardMt> stoneInwardMtList = (List<StoneInwardMt>) stoneInwardMtRepository
				.findAll(expression);
		
		return stoneInwardMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
	
		QStoneInwardMt qStoneInwardMt= QStoneInwardMt.stoneInwardMt;
		BooleanExpression expression = qStoneInwardMt.deactive.eq(false);
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qStoneInwardMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invno") && colValue != null) {
				expression = qStoneInwardMt.deactive.eq(false).and(
						qStoneInwardMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invno"))
					&& colValue != null) {
				expression = qStoneInwardMt.invNo.like(colValue + "%");
			}
		}
		
		return stoneInwardMtRepository.count(expression);
	}

	@Override
	public Page<StoneInwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QStoneInwardMt qStoneInwardMt = QStoneInwardMt.stoneInwardMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qStoneInwardMt.deactive.eq(false);
			} else {
				expression = qStoneInwardMt.deactive.eq(false).and(
						qStoneInwardMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qStoneInwardMt.invNo.like(name + "%");
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

		Page<StoneInwardMt> stoneInwardMtList = (Page<StoneInwardMt>) stoneInwardMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return stoneInwardMtList;
	}

	@Override
	public StoneInwardMt findByUniqueId(Long uniqueId) {
		return stoneInwardMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public StoneInwardMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return stoneInwardMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}
	
	@Override
	public List<StoneInwardMt> getInvoiceList(String inwardTypeIds) {
		
		
		List<Integer> inwardList =new ArrayList<Integer>();
		
		if(inwardTypeIds.length()>0){
			String vInwardTypeId[] = inwardTypeIds.split(",");
			for(int i=0;i<vInwardTypeId.length;i++){
				inwardList.add(Integer.parseInt(vInwardTypeId[i]));
			}
		}
		
		
		QStoneInwardMt qstoneInwardMt = QStoneInwardMt.stoneInwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<StoneInwardMt>stoneInwardmts = null;
		
		if(inwardList.size()>0){
			stoneInwardmts=query.from(qstoneInwardMt).
					where(qstoneInwardMt.deactive.eq(false).and(qstoneInwardMt.inwardType.id.in(inwardList))).orderBy(qstoneInwardMt.id.desc()).list(qstoneInwardMt);
		}else{
			
			stoneInwardmts=query.from(qstoneInwardMt).
					where(qstoneInwardMt.deactive.eq(false)).orderBy(qstoneInwardMt.id.desc()).list(qstoneInwardMt);
			
		}
		
		
		
		
		return stoneInwardmts;
	}

	@Override
	public String deleteMt(Integer mtId,Principal principal) {
		
		String retVal="-1";
		StoneInwardMt stoneInwardMt =findOne(mtId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		Date cDate = stoneInwardMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);
			
		
		if(dbDate.equals(curDate) || principal.getName().equalsIgnoreCase("Admin") ){
				
			List<StoneInwardDt>stoneInwardDts =stoneInwardDtService.findByStoneInwardMt(stoneInwardMt);
			
			for(StoneInwardDt stoneInwardDt :stoneInwardDts){
				
				if(stoneInwardDt.getRefTranType() != null) {
					
					LooseStkConversion looseStkConversion =looseStkConversionService.findOne(stoneInwardDt.getRefTranId());
					looseStkConversion.setAdjFlg(true);
					looseStkConversion.setModiBy(principal.getName());
					looseStkConversion.setModiDt(new Date());
					looseStkConversionService.save(looseStkConversion);
				}
				
				StoneTran stoneTran =stoneTranService.RefTranIdAndTranType(stoneInwardDt.getId(), "Inward");
				stoneTranService.delete(stoneTran.getId());
								
				stoneInwardDtService.delete(stoneInwardDt.getId());
			}
			
			delete(mtId);
			
			retVal = "1";
		}
		
		
//		if(retVal !="-1"){
//			
//			List<StoneInwardDt>stoneInwardDts =stoneInwardDtService.findByStoneInwardMt(stoneInwardMt);
//			
//			for(StoneInwardDt stoneInwardDt :stoneInwardDts){
//				
//				stoneInwardDtService.delete(stoneInwardDt.getId());
//				
//				StoneTran stoneTran =stoneTranService.RefTranIdAndTranType(stoneInwardDt.getId(), "Inward");
//				stoneTranService.delete(stoneTran.getId());
//								
//				
//			}
//			
//			delete(stoneInwardMt.getId());
//			
//			
//		}
		
		
		
		
		return retVal;
	}

	@Override
	public Page<StoneInwardMt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		
		QStoneInwardMt qStoneInwardMt = QStoneInwardMt.stoneInwardMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qStoneInwardMt.deactive.eq(false);
			}else{
				expression = qStoneInwardMt.deactive.eq(false).and(qStoneInwardMt.invNo.like("%" + search + "%")
						.or(qStoneInwardMt.inwardType.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qStoneInwardMt.invNo.like("%" + search + "%").or(qStoneInwardMt.inwardType.name.like("%" + search + "%"));
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
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<StoneInwardMt> stoneInwardList =(Page<StoneInwardMt>) stoneInwardMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return stoneInwardList;
	}

	@Override
	public String stoneInwDelete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMaxInvSrno() {
		QStoneInwardMt qStoneInwardMt = QStoneInwardMt.stoneInwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStoneInwardMt)
			.where(qStoneInwardMt.deactive.eq(false).and(qStoneInwardMt.invDate.year().eq(date.get(Calendar.YEAR)))).list(qStoneInwardMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String stoneInwLooseConvTransfer(Integer mtid, String convDtId, Principal principal) {
		
		String retVal = "-1";
		try {
			
			StoneInwardMt stoneInwardMt = findOne(mtid);
			
			String[] looseConvDtl = convDtId.split(",");
			for (int i = 0; i < looseConvDtl.length; i++) {
				
				LooseStkConversion looseStkConversion = looseStkConversionService.findOne(Integer.parseInt(looseConvDtl[i]));
				
				StoneInwardDt stoneInwardDt = new StoneInwardDt();
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				Integer	maxSrno = stoneInwardDtService.getMaxLotSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				++maxSrno;
				int si = maxSrno.toString().length();
				
				String bagSr = null;
				
				switch (si) {
				case 1:
					bagSr = "0000"+maxSrno;
					break;
					
				case 2:
					bagSr = "000"+maxSrno;
					break;
					
				case 3:
					bagSr = "00"+maxSrno;
					break;
				case 4:
					bagSr = "0"+maxSrno;
					break;	
					
				default:
					bagSr = maxSrno.toString();
					break;
				}
				
				stoneInwardDt.setLotNo("Lot-"+bagSr+"-"+vYear);
				stoneInwardDt.setLotSrNo(maxSrno);
				
				stoneInwardDt.setAmount(looseStkConversion.getAmount());
				stoneInwardDt.setBalCarat(looseStkConversion.getBalCarat());
				stoneInwardDt.setBalStone(looseStkConversion.getBalStone());
				stoneInwardDt.setCarat(looseStkConversion.getCarat());
				stoneInwardDt.setCreatedBy(principal.getName());
				stoneInwardDt.setCreatedDt(new Date());
	//			stoneInwardDt.setDepartment(departmentService.findByName(locationName));
	//			stoneInwardDt.setSordDtId(dataOrderStnDt.getInt("dtId"));
				stoneInwardDt.setQuality(looseStkConversion.getQuality());
				stoneInwardDt.setRate(looseStkConversion.getRate());
				stoneInwardDt.setShape(looseStkConversion.getShape());
				stoneInwardDt.setSieve(looseStkConversion.getSieve());
				stoneInwardDt.setSize(looseStkConversion.getSize());
				stoneInwardDt.setSizeGroup(looseStkConversion.getSizeGroup());
				stoneInwardDt.setStone(looseStkConversion.getStone());
				stoneInwardDt.setStoneInwardMt(stoneInwardMt);
				stoneInwardDt.setStoneType(looseStkConversion.getStoneType());
		//		stoneInwardDt.setBagQty(pBagQty);
				stoneInwardDt.setRefTranId(looseStkConversion.getId());
				stoneInwardDt.setRefTranType("LSCONV");
				
				stoneInwardDtService.save(stoneInwardDt);
				
				StoneTran stoneTran = new StoneTran();
				stoneTran.setLotNo(stoneInwardDt.getLotNo());
				stoneTran.setTranDate(stoneInwardMt.getInvDate()); 
				stoneTran.setDepartment(null);
	//			stoneTran.setDeptLocation(stoneInward.getDepartment());
				stoneTran.setStoneType(stoneInwardDt.getStoneType());
				stoneTran.setShape(stoneInwardDt.getShape());
				stoneTran.setQuality(looseStkConversion.getQuality());
	//			stoneTran.setSubShape(looseStkConversion.getSubShape());
				stoneTran.setSize(stoneInwardDt.getSize());
				stoneTran.setSieve(stoneInwardDt.getSieve());
				stoneTran.setSizeGroup(stoneInwardDt.getSizeGroup());
				stoneTran.setStone(stoneInwardDt.getStone());
				stoneTran.setCarat(stoneInwardDt.getCarat());		//-------------
				stoneTran.setBalStone(stoneInwardDt.getBalStone());
				stoneTran.setBalCarat(stoneInwardDt.getCarat()); 
				stoneTran.setRate(stoneInwardDt.getRate());
				stoneTran.setInOutFld("C");
				stoneTran.setBagMt(null);;
				stoneTran.setBagSrNo(0);
				stoneTran.setSettingType(null);
				stoneTran.setSetting(null);
				stoneTran.setPacketNo(stoneInwardDt.getPacketNo());
				stoneTran.setRemark(stoneInwardDt.getRemark());
				stoneTran.setParty(stoneInwardMt.getSupplier());             		//--------- discussion
				stoneTran.setTranType("Inward");
				stoneTran.setRefTranId(stoneInwardDt.getId());
				stoneTran.setStnRecDtId(stoneInwardDt.getId());
				
				stoneTran.setCreatedBy(principal.getName());
				stoneTran.setCreatedDt(new Date());
				stoneTranService.save(stoneTran);
				
				
				looseStkConversion.setAdjFlg(false);
				looseStkConversion.setModiBy(principal.getName());
				looseStkConversion.setModiDt(new Date());
				looseStkConversionService.save(looseStkConversion);
				
			}
			
			 retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	

}
