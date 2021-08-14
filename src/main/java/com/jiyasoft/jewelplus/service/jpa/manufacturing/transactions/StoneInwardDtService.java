package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneInwardDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILooseStkConversionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class StoneInwardDtService implements IStoneInwardDtService {

	@Autowired
	IStoneInwardDtRepository stoneInwardDtRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IStoneInwardMtService stoneInwardMtService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IQualityService qualityService;

	
	@Autowired
	private IStoneTranService stoneTranService;
	
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IOrderStnDtService orderStnDtService;
	
	@Autowired
	private IStoneOutwardMtService stoneOutwardMtService;
	
	@Autowired
	private IStoneOutwardDtService stoneOutwardDtService;
	
	@Autowired
	private ILooseStkConversionService looseStkConversionService;

	
	@Override
	public List<StoneInwardDt> findAll() {
		return stoneInwardDtRepository.findAll();
	}

	@Override
	public Page<StoneInwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return stoneInwardDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(StoneInwardDt stoneInwardDt) {
		stoneInwardDtRepository.save(stoneInwardDt);

	}

	@Override
	public void delete(int id) {
		StoneInwardDt stoneInwardDt = stoneInwardDtRepository.findOne(id);
		stoneInwardDt.setDeactive(true);
		stoneInwardDt.setDeactiveDt(new java.util.Date());
		stoneInwardDtRepository.save(stoneInwardDt);
	}

	@Override
	public Long count() {
		return stoneInwardDtRepository.count();
	}

	@Override
	public StoneInwardDt findOne(int id) {
		return stoneInwardDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getStoneInwardDtList() {
		return null;
	}

	@Override
	public Page<StoneInwardDt> findByStoneInwardMt(StoneInwardMt stoneInwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search) {

		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return stoneInwardDtRepository.findByStoneInwardMt(stoneInwardMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

	}

	@Override
	public StoneInwardDt findByUniqueId(Long uniqueId) {
		return stoneInwardDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<StoneInwardDt> findByStoneInwardMt(StoneInwardMt stoneInwardMt) {
		return stoneInwardDtRepository.findByStoneInwardMt(stoneInwardMt);
	}

	@Override
	public List<StoneInwardDt> findByStoneInwardMtAndDeactiveOrderByIdDesc(
			StoneInwardMt stoneInwardMt, Boolean deactive) {
		return stoneInwardDtRepository.findByStoneInwardMtAndDeactiveOrderByIdDesc(
				stoneInwardMt, deactive);
	}

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndDeactive(StoneType stoneType, Shape shape,Boolean deactive) {
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndDeactive(stoneType, shape, deactive);
	}

	//------------------->>>>>>>>>>>>>>>>

	@Override
	public List<StoneInwardDt> getFifoDetails(String pStoneType, String pShape, String pQuality,
		String pSize, String pBagStn, String pCarat, String pPointer, String pSizeGroup) {

		QStoneInwardDt qStoneInwardDt = QStoneInwardDt.stoneInwardDt;

		JPAQuery query = new JPAQuery(entityManager);
		List<Tuple> fifoDetailsList = null;

		if (pSizeGroup.equals("-")) {
			fifoDetailsList = query
				.from(qStoneInwardDt)
				.where(qStoneInwardDt.stoneType.name.eq(pStoneType)
					.and(qStoneInwardDt.shape.name.eq(pShape)
					.and(qStoneInwardDt.quality.name.eq(pQuality)
					.and(qStoneInwardDt.size.trim().eq(pSize.trim()))
					.and(qStoneInwardDt.deactive.eq(false)))))
				.orderBy(qStoneInwardDt.id.asc())
				.list(qStoneInwardDt.balStone, qStoneInwardDt.carat, qStoneInwardDt.id);
		} else {
			fifoDetailsList = query
				.from(qStoneInwardDt)
				.where(qStoneInwardDt.stoneType.name.eq(pStoneType)
					.and(qStoneInwardDt.shape.name.eq(pShape)
					.and(qStoneInwardDt.quality.name.eq(pQuality)
					.and(qStoneInwardDt.sizeGroup.name.trim().eq(pSizeGroup.trim()))
					.and(qStoneInwardDt.deactive.eq(false)))))
				.orderBy(qStoneInwardDt.id.asc())
				.list(qStoneInwardDt.balStone, qStoneInwardDt.carat, qStoneInwardDt.id);			
		}

		List<StoneInwardDt> stoneInwardDts = new ArrayList<StoneInwardDt>();
		for (Tuple row : fifoDetailsList) {
			stoneInwardDts.add(stoneInwardDtRepository.findOne(row.get(qStoneInwardDt.id)));
		}

		return stoneInwardDts;
	}

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndDeactive(StoneType stoneType, Shape shape, Quality quality, String size,Boolean deactive) {
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndQualityAndSizeAndDeactive(stoneType, shape, quality, size,deactive);		
	}

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup,Boolean deactive) {
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(stoneType, shape, quality, sizeGroup,deactive);
	}

	

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndDeactiveOrderByIdAsc(
			StoneType stoneType, Shape shape, Quality quality,Boolean deactive) {
		
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndQualityAndDeactiveOrderByIdAsc(stoneType, shape, quality,deactive);
	}

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndSizeGroupAndDeactive(
			StoneType stoneType, Shape shape, Quality quality, String size,
			SizeGroup sizeGroup,Boolean deactive) {
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndQualityAndSizeAndSizeGroupAndDeactive(stoneType, shape, quality, size, sizeGroup,deactive);
	}
	
	
	
	
	//----all new method for fifoButton
	
	

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndDeactiveOrderByRateDesc(
			StoneType stoneType, Shape shape, Quality quality, String size,Boolean deactive) {
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndQualityAndSizeAndDeactiveOrderByRateDesc(stoneType, shape, quality, size, deactive);
	}

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveOrderByRateDesc(
			StoneType stoneType, Shape shape, Quality quality,
			SizeGroup sizeGroup,Boolean deactive) {
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveOrderByRateDesc(stoneType, shape, quality, sizeGroup,deactive);
	}

	@Override
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndDeactiveOrderByRateDesc(
			StoneType stoneType, Shape shape, Quality quality,Boolean deactive) {
		return stoneInwardDtRepository.findByStoneTypeAndShapeAndQualityAndDeactiveOrderByRateDesc(stoneType, shape, quality,deactive);
	}

	//--for StoneRate master---//
	
	@Override
	public List<Tuple> getListForStoneRateMast() {
		
		QStoneInwardDt qStoneInwardDt = QStoneInwardDt.stoneInwardDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Tuple> stoneInwardDts = null;
		
		stoneInwardDts = query.from(qStoneInwardDt).
				where(qStoneInwardDt.deactive.eq(false).and(qStoneInwardDt.balCarat.gt(0))).
				groupBy(qStoneInwardDt.stoneType,qStoneInwardDt.shape,qStoneInwardDt.quality,qStoneInwardDt.size).
				list(qStoneInwardDt.rate.max(),qStoneInwardDt.stoneType.name,qStoneInwardDt.shape.name,qStoneInwardDt.quality.name,qStoneInwardDt.size);
		
		return stoneInwardDts;
	}
	

	@Override
	public void saveAll(List<StoneInwardDt> stoneInwardDts) {
		stoneInwardDtRepository.save(stoneInwardDts);
	}

	@Override
	public String saveStoneInwardDt(StoneInwardDt stoneInwardDt, Integer id,
			String pInvNo, Double vCarat, Integer vStone, String sizeGroupStr,
			Principal principal) {
		String action = "added";
		String retVal = "-1";
		
		StoneTran stoneTran=null;

		if (id == null || id.equals("") || (id != null && id == 0)) {
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			Integer	maxSrno = getMaxLotSrno();
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
			stoneInwardDt.setCreatedBy(principal.getName());
			stoneInwardDt.setCreatedDt(new java.util.Date());
			stoneInwardDt.setStoneInwardMt(stoneInwardMtService.findByInvNoAndDeactive(pInvNo, false));
			stoneInwardDt.setUniqueId(new Date().getTime());
			stoneInwardDt.setDepartment(departmentService.findByName("Diamond"));
			stoneInwardDt.setBalCarat(vCarat);
			stoneInwardDt.setBalStone(vStone);
			stoneInwardDt.setCarat(vCarat);
			stoneInwardDt.setStone(vStone);
			stoneInwardDt.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(stoneInwardDt.getShape(), sizeGroupStr,false));
			stoneTran = new StoneTran();
			retVal = "1";
			
		} else {
			
			if(stoneInwardDt.getBalCarat() > stoneInwardDt.getCarat()){
				return retVal = "-11";
			}
			
			
			stoneInwardDt.setModiBy(principal.getName());
			stoneInwardDt.setModiDt(new java.util.Date());
			stoneInwardDt.setStoneInwardMt(stoneInwardMtService.findByInvNoAndDeactive(pInvNo, false));
			stoneInwardDt.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(stoneInwardDt.getShape(), sizeGroupStr,false));
			stoneInwardDt.setBalCarat(vCarat);
			stoneInwardDt.setBalStone(vStone);
			stoneInwardDt.setDepartment(departmentService.findByName("Diamond"));
			stoneInwardDt.setCarat(vCarat);
			stoneInwardDt.setStone(vStone);
			stoneTran=stoneTranService.RefTranIdAndTranType(id, "Inward");
			action = "updated";
			
		}
		
	
		
		if (stoneInwardDt.getSubShape().getId() == null) {
			stoneInwardDt.setSubShape(null);
		}
		
		
		save(stoneInwardDt);
		
		StoneInwardMt stoneInwardMt = stoneInwardMtService.findByInvNoAndDeactive(pInvNo, false);
		
		StoneInwardDt stoneInward = null;
		
		if(action == "added"){
			stoneInward=findByUniqueId(stoneInwardDt.getUniqueId());
			stoneTran.setCreatedBy(stoneInward.getCreatedBy());
			stoneTran.setCreatedDt(stoneInwardMt.getInvDate());
		}else{
			stoneInward=findOne(id);
			stoneTran.setModiBy(principal.getName());
			stoneTran.setModiDt(new java.util.Date());
		}
		
		if(stoneTran != null){
			stoneTran.setLotNo(stoneInwardDt.getLotNo());
			stoneTran.setTranDate(stoneInwardMt.getInvDate()); 
			stoneTran.setDepartment(null);
			stoneTran.setDeptLocation(stoneInward.getDepartment());
			stoneTran.setStoneType(stoneInward.getStoneType());
			stoneTran.setShape(stoneInward.getShape());
			stoneTran.setQuality(stoneInward.getQuality());
			stoneTran.setSubShape(stoneInward.getSubShape());
			stoneTran.setSize(stoneInward.getSize());
			stoneTran.setSieve(stoneInward.getSieve());
			stoneTran.setSizeGroup(stoneInward.getSizeGroup());
			stoneTran.setStone(stoneInward.getStone());
			stoneTran.setCarat(stoneInward.getCarat());		//-------------
			stoneTran.setBalStone(stoneInward.getBalStone());
			stoneTran.setBalCarat(stoneInward.getCarat()); //--------------
			stoneTran.setRate(stoneInward.getRate());
			stoneTran.setInOutFld("C");
			stoneTran.setBagMt(null);;
			stoneTran.setBagSrNo(0);
			stoneTran.setSettingType(null);
			stoneTran.setSetting(null);
			stoneTran.setPacketNo(stoneInward.getPacketNo());
			stoneTran.setRemark(stoneInward.getRemark());
			stoneTran.setParty(stoneInward.getStoneInwardMt().getParty());
			stoneTran.setTranType("Inward");
			stoneTran.setRefTranId(stoneInward.getId());
			stoneTran.setStnRecDtId(stoneInward.getId());

	
			
			stoneTranService.save(stoneTran);
		
		}
		
		if(action =="updated") {
			
			List<StoneTran>stoneTrans =stoneTranService.findByStnRecDtIdAndDeactive(id, false);
			for(StoneTran stoneTran2 :stoneTrans) {
				stoneTran2.setRate(stoneInwardDt.getRate());
				stoneTran2.setModiBy(principal.getName());
				stoneTran2.setModiDt(new java.util.Date());
				stoneTranService.save(stoneTran2);
				
			}
			
		}
		
		retVal=retVal+","+action;
		
		return retVal;
	}

	@Override
	public String stoneInwDtDelete(Integer id, Principal principal) {
		
		String retVal ="-1";
		
		try {
			
			StoneInwardDt stoneInwardDt = findOne(id);
			
			StoneTran stoneTran=stoneTranService.RefTranIdAndTranType(id, "Inward");
			stoneTranService.delete(stoneTran.getId());
			
			if(stoneInwardDt.getRefTranType() != null) {
				
				LooseStkConversion looseStkConversion =looseStkConversionService.findOne(stoneInwardDt.getRefTranId());
				looseStkConversion.setAdjFlg(true);
				looseStkConversion.setModiBy(principal.getName());
				looseStkConversion.setModiDt(new Date());
				looseStkConversionService.save(looseStkConversion);
			}
			
			delete(id);
			retVal ="1";
			
				
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public String saveOrderStoneInward(Integer pInwardMtid, String data, Double pBagQty,String mOpt, Principal principal,Boolean allowNegative,String stockType,String companyName) {

		
		String retVal="-1";
		Integer vStone=0;
		Double vCarat =0.0;
		StoneInwardMt stoneInwardMt =  null;
		StoneOutwardMt stoneOutwardMt = null;
		
		String locationName="Diamond";
		
		if(companyName.equalsIgnoreCase("nuance II")) {
			locationName="Bagging";
		}
		
		
		JSONArray jsonBagDt = new JSONArray(data);
		
		
		String qltyCheck="";
		String rateCheck="";
		for (int y = 0; y < jsonBagDt.length(); y++) {
			
			JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
			
				
			if(dataBagDt.get("quality").toString().isEmpty()) {
				if(qltyCheck == ""){
					qltyCheck = dataBagDt.get("srNo").toString();
				}else{
					qltyCheck =  qltyCheck+","+dataBagDt.get("srNo").toString();
				}
			}
			
			
			
			if(dataBagDt.getDouble("rate")>0) {
				
				
			}else {
				if(rateCheck == ""){
					rateCheck = dataBagDt.get("srNo").toString();
				}else{
					rateCheck =  rateCheck+","+dataBagDt.get("srNo").toString();
				}
			}
			
			
			
			
		}
		
		if(qltyCheck != ""){
			return "Warning : Quality Not Found For Serial no ["+qltyCheck+"]";
		}
		
		
		if(rateCheck != ""){
			return "Warning :  Rate is Zero  For Serial no ["+rateCheck+"]";
		}
		
		
		if(!mOpt.equalsIgnoreCase("Inward")){
			
			if(!allowNegative.equals(true)){
				String stockSrNo = "";
				
				  Department location=  departmentService.findByName(locationName);
				
				for (int y = 0; y < jsonBagDt.length(); y++) {
					JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
					
					
					
					Double stockBal = stoneTranService.getStockBalanceStr(location.getId(),dataBagDt.get("stoneType").toString(), dataBagDt.get("shape").toString(), 
							dataBagDt.get("quality").toString(), dataBagDt.get("sizeGroup").toString(), dataBagDt.get("sieve").toString(), stockType,dataBagDt.getInt("dtId"));
					
					if(stockBal < Double.parseDouble(dataBagDt.get("bagCarat").toString())){
						if(stockSrNo == ""){
							stockSrNo = dataBagDt.get("srNo").toString();
						}else{
							stockSrNo =  stockSrNo+","+dataBagDt.get("srNo").toString();
						}
					}
					
				}
				
				
				if(stockSrNo != ""){
					return "Warning : Stock Not Found of Serial no ["+stockSrNo+"]";
				}
				
			}
			
		}
		
	
		
		
		
		
		
		Integer inwardTranSrno=0;
		Integer outwardTranSrno=0;
		
		for (int y = 0; y < jsonBagDt.length(); y++) {
			
			
			JSONObject dataOrderStnDt = (JSONObject) jsonBagDt.get(y);
			
			if(dataOrderStnDt.getDouble("bagCarat")>0) {
				
				OrderStnDt orderStnDt = orderStnDtService.findOne(dataOrderStnDt.getInt("id"));
								
				if(mOpt.equalsIgnoreCase("Inward")){
				
					stoneInwardMt =  stoneInwardMtService.findOne(pInwardMtid);
					if(y==0) {
						inwardTranSrno=getMaxTranSrno(dataOrderStnDt.getInt("dtId"));	
					}
					 
					
				StoneInwardDt stoneInwardDt = new StoneInwardDt();
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				Integer	maxSrno = getMaxLotSrno();
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
				
				stoneInwardDt.setAmount(Math.round((dataOrderStnDt.getDouble("rate")*dataOrderStnDt.getDouble("bagCarat"))*100.0)/100.0);
				stoneInwardDt.setBalCarat(dataOrderStnDt.getDouble("bagCarat"));
				stoneInwardDt.setBalStone(dataOrderStnDt.getInt("bagStone"));
				stoneInwardDt.setCarat(dataOrderStnDt.getDouble("bagCarat"));
				stoneInwardDt.setCreatedBy(principal.getName());
				stoneInwardDt.setCreatedDt(new Date());
				stoneInwardDt.setDepartment(departmentService.findByName(locationName));
				stoneInwardDt.setSordDtId(dataOrderStnDt.getInt("dtId"));
				stoneInwardDt.setQuality(orderStnDt.getQuality());
				stoneInwardDt.setRate(dataOrderStnDt.getDouble("rate"));
				stoneInwardDt.setShape(orderStnDt.getShape());
				stoneInwardDt.setSieve(orderStnDt.getSieve());
				stoneInwardDt.setSize(orderStnDt.getSize());
				stoneInwardDt.setSizeGroup(orderStnDt.getSizeGroup());
				stoneInwardDt.setStone(dataOrderStnDt.getInt("bagStone"));
				stoneInwardDt.setStoneInwardMt(stoneInwardMt);
				stoneInwardDt.setStoneType(orderStnDt.getStoneType());
				stoneInwardDt.setSubShape(orderStnDt.getSubShape());
				stoneInwardDt.setBagQty(pBagQty);
				stoneInwardDt.setTranSrNo(inwardTranSrno+1);
				stoneInwardDt.setOrdStnSrNo(orderStnDt.getSrNo());
				
				save(stoneInwardDt);
				
				
				//StoneTran
				
				StoneTran stoneTran =  new StoneTran();
				stoneTran.setLotNo(stoneInwardDt.getLotNo());
				stoneTran.setTranDate(stoneInwardMt.getInvDate()); 
				stoneTran.setDepartment(null);
				stoneTran.setDeptLocation(stoneInwardDt.getDepartment());
				stoneTran.setStoneType(stoneInwardDt.getStoneType());
				stoneTran.setShape(stoneInwardDt.getShape());
				stoneTran.setQuality(stoneInwardDt.getQuality());
				stoneTran.setSubShape(stoneInwardDt.getSubShape());
				stoneTran.setSize(stoneInwardDt.getSize());
				stoneTran.setSieve(stoneInwardDt.getSieve());
				stoneTran.setSizeGroup(stoneInwardDt.getSizeGroup());
				stoneTran.setStone(stoneInwardDt.getStone());
				stoneTran.setCarat(stoneInwardDt.getCarat());		//-------------
				stoneTran.setBalStone(stoneInwardDt.getBalStone());
				stoneTran.setBalCarat(stoneInwardDt.getCarat()); //--------------
				stoneTran.setRate(stoneInwardDt.getRate());
				stoneTran.setInOutFld("C");
				stoneTran.setBagMt(null);;
				stoneTran.setBagSrNo(0);
				stoneTran.setSettingType(null);
				stoneTran.setSetting(null);
				stoneTran.setParty(stoneInwardMt.getParty());
				stoneTran.setTranType("Inward");
				stoneTran.setRefTranId(stoneInwardDt.getId());
				stoneTran.setStnRecDtId(stoneInwardDt.getId());
				stoneTran.setSordDtId(stoneInwardDt.getSordDtId());
				stoneTran.setCreatedDt(new Date());
				stoneTran.setCreatedBy(principal.getName());
				stoneTran.setBagQty(pBagQty);
				stoneTran.setDiaCateg(dataOrderStnDt.getString("diaCateg"));
				stoneTran.setBagSrNo(stoneInwardDt.getOrdStnSrNo());
				stoneTran.setTranSrNo(stoneInwardDt.getTranSrNo());
				stoneTran.setAvgRate(stoneTran.getRate());
				stoneTran.setTransferRate((double) Math.round((stoneTran.getAvgRate() + (stoneTran.getAvgRate() * stoneTran.getStoneType().getTransferRatePerc() / 100)) * 100)/ 100);
				stoneTran.setFactoryRate((double) Math.round((stoneTran.getTransferRate() + (stoneTran.getTransferRate()* stoneTran.getStoneType().getFactoryRatePerc() / 100)) * 100)/ 100);
				stoneTran.setSordMtId(orderStnDt.getOrderMt().getId());
		

				stoneTranService.save(stoneTran);
			
				}else{
					
					stoneOutwardMt =  stoneOutwardMtService.findOne(pInwardMtid);
					if(y==0) {
					 outwardTranSrno=stoneOutwardDtService.getMaxTranSrno(dataOrderStnDt.getInt("dtId"));
					}
					StoneOutwardDt stoneOutwardDt = new StoneOutwardDt();
					
					stoneOutwardDt.setAmount(Math.round((dataOrderStnDt.getDouble("rate")*dataOrderStnDt.getDouble("bagCarat"))*100.0)/100.0);
					stoneOutwardDt.setBalCarat(dataOrderStnDt.getDouble("bagCarat"));
					stoneOutwardDt.setBalStone(dataOrderStnDt.getInt("bagStone"));
					stoneOutwardDt.setCarat(dataOrderStnDt.getDouble("bagCarat"));
					stoneOutwardDt.setCreatedBy(principal.getName());
					stoneOutwardDt.setCreatedDt(new Date());
					stoneOutwardDt.setDepartment(departmentService.findByName(locationName));
					stoneOutwardDt.setSordDtId(dataOrderStnDt.getInt("dtId"));
					stoneOutwardDt.setQuality(orderStnDt.getQuality());
					stoneOutwardDt.setRate(dataOrderStnDt.getDouble("rate"));
					stoneOutwardDt.setShape(orderStnDt.getShape());
					stoneOutwardDt.setSieve(orderStnDt.getSieve());
					stoneOutwardDt.setSize(orderStnDt.getSize());
					stoneOutwardDt.setSizeGroup(orderStnDt.getSizeGroup());
					stoneOutwardDt.setStone(dataOrderStnDt.getInt("bagStone"));
					stoneOutwardDt.setStoneOutwardMt(stoneOutwardMt);
					stoneOutwardDt.setStoneType(orderStnDt.getStoneType());
					stoneOutwardDt.setSubShape(orderStnDt.getSubShape());
					stoneOutwardDt.setBagQty(pBagQty);
					stoneOutwardDt.setTranSrNo(outwardTranSrno+1);
					stoneOutwardDt.setOrdStnSrNo(orderStnDt.getSrNo());
					
					stoneOutwardDtService.save(stoneOutwardDt);
					
					
					//StoneTran
					
					StoneTran stoneTran =  new StoneTran();
					
					stoneTran.setTranDate(stoneOutwardMt.getInvDate()); 
					stoneTran.setDepartment(null);
					stoneTran.setDeptLocation(stoneOutwardDt.getDepartment());
					stoneTran.setStoneType(stoneOutwardDt.getStoneType());
					stoneTran.setShape(stoneOutwardDt.getShape());
					stoneTran.setQuality(stoneOutwardDt.getQuality());
					stoneTran.setSubShape(stoneOutwardDt.getSubShape());
					stoneTran.setSize(stoneOutwardDt.getSize());
					stoneTran.setSieve(stoneOutwardDt.getSieve());
					stoneTran.setSizeGroup(stoneOutwardDt.getSizeGroup());
					stoneTran.setStone(stoneOutwardDt.getStone());
					stoneTran.setCarat(stoneOutwardDt.getCarat());		//-------------
					stoneTran.setBalStone(stoneOutwardDt.getBalStone()*-1);
					stoneTran.setBalCarat(stoneOutwardDt.getCarat()*-1); //--------------
					stoneTran.setRate(stoneOutwardDt.getRate());
					stoneTran.setInOutFld("D");
					stoneTran.setBagMt(null);;
					stoneTran.setBagSrNo(0);
					stoneTran.setSettingType(null);
					stoneTran.setSetting(null);
					stoneTran.setCreatedDt(new Date());
					stoneTran.setCreatedBy(principal.getName());
					stoneTran.setTranType("Outward");
					stoneTran.setRefTranId(stoneOutwardDt.getId());
					stoneTran.setSordDtId(stoneOutwardDt.getSordDtId());
					stoneTran.setBagQty(pBagQty);
					stoneTran.setDiaCateg(dataOrderStnDt.getString("diaCateg"));
					stoneTran.setBagSrNo(stoneOutwardDt.getOrdStnSrNo());
					stoneTran.setTranSrNo(stoneOutwardDt.getTranSrNo());
					stoneTran.setSordMtId(orderStnDt.getOrderMt().getId());

					stoneTranService.save(stoneTran);
					
					
				}
				
			}
			
		
		}
		
		if(mOpt == "Inward"){
			List<StoneInwardDt> stoneInwardDts = findByStoneInwardMtAndDeactiveOrderByIdDesc(stoneInwardMt, false);
			for (StoneInwardDt stoneInwardDt2 : stoneInwardDts) {
				vStone += stoneInwardDt2.getStone();
				vCarat += stoneInwardDt2.getCarat();
			}
		}else {
			
			List<StoneOutwardDt> stoneOutwardDts = stoneOutwardDtService.findByStoneOutwardMt(stoneOutwardMt);
			for (StoneOutwardDt stoneOutwardDt : stoneOutwardDts) {
				vStone += stoneOutwardDt.getStone();
				vCarat += stoneOutwardDt.getCarat();
			}
		}
		
		
		
		vCarat=	Math.round((vCarat)*1000.0)/1000.0;
		
		
		return retVal+"^"+vStone+"^"+vCarat;
	}

	@Override
	public Integer getMaxTranSrno(Integer sordDtId) {
		
		QStoneInwardDt qStoneInwardDt = QStoneInwardDt.stoneInwardDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		List<Integer> maxSrno = query
			.from(qStoneInwardDt)
			.where(qStoneInwardDt.deactive.eq(false).and(qStoneInwardDt.sordDtId.eq(sordDtId)))
			.list(qStoneInwardDt.tranSrNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}
		
		if(retVal == null){
			retVal =0;
		}
		

		return retVal;
		
	}

	@Override
	public Integer getMaxLotSrno() {
		QStoneInwardDt qStoneInwardDt = QStoneInwardDt.stoneInwardDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStoneInwardDt)
			.where(qStoneInwardDt.deactive.eq(false).and(qStoneInwardDt.createdDt.year().eq(date.get(Calendar.YEAR)))).list(qStoneInwardDt.lotSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	synchronized public String ordStockAllocation(String pInvNo, String data, Principal principal, Boolean allowNegative,
			String stockType, String companyName) {
		// TODO Auto-generated method stub
		
		JSONArray jsonBagDt = new JSONArray(data);
		
		 Department location=  departmentService.findByName("Diamond");
		
		if(!allowNegative.equals(true)){
			String stockSrNo = "";
			
			 
			
			for (int y = 0; y < jsonBagDt.length(); y++) {
				JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
				
				
				
				Double stockBal = stoneTranService.getStockBalanceStr(location.getId(),dataBagDt.get("stoneType").toString(), dataBagDt.get("shape").toString(), 
						dataBagDt.get("quality").toString(), dataBagDt.get("sizeGroup").toString(), dataBagDt.get("sieve").toString(), "size",0);
				
				if(stockBal < Double.parseDouble(dataBagDt.get("issueCarat").toString())){
					if(stockSrNo == ""){
						stockSrNo = dataBagDt.get("srno").toString();
					}else{
						stockSrNo =  stockSrNo+","+dataBagDt.get("srno").toString();
					}
				}
				
			}
			
			
			if(stockSrNo != ""){
				return "Warning : Stock Not Found of Serial no ["+stockSrNo+"]";
			}
			
		}
		
		
		OrderMt orderMt =orderMtService.findByInvNoAndDeactive(pInvNo, false);
		
		for (int y = 0; y < jsonBagDt.length(); y++) {
			JSONObject dataOrderStnDt = (JSONObject) jsonBagDt.get(y);
			
			Double adjCarat = dataOrderStnDt.getDouble("issueCarat");
			Integer adjStone= dataOrderStnDt.getInt("issueStone");
			if(adjCarat>0) {
				
	
				
				List<StoneTran>stoneTrans =stoneTranService.getDiamondStockForAllocation(location.getId(),dataOrderStnDt.getString("stoneType"),
						dataOrderStnDt.getString("shape"), dataOrderStnDt.getString("quality"), dataOrderStnDt.getString("size"), dataOrderStnDt.getString("sieve"));
				
				
				/*
				 * Double valSum=0.0; Double caratSum=0.0; for (StoneTran stoneTran :
				 * stoneTrans) {
				 * 
				 * valSum +=
				 * Math.round((stoneTran.getBalCarat()*stoneTran.getRate())*100.0)/100.0;
				 * caratSum += Math.round(stoneTran.getBalCarat()*1000.0)/1000.0;
				 * 
				 * 
				 * }
				 * 
				 * Double avgRate= Math.round((valSum/caratSum)*100.0)/100.0;
				 */
				
				
				
				for (StoneTran stoneTran : stoneTrans) {
					if (adjCarat > 0) {

						if (stoneTran.getBalCarat() >= adjCarat) {

							StoneTran stoneTranDiaLoc = new StoneTran();
							stoneTranDiaLoc.setAvgRate(stoneTran.getRate());
							stoneTranDiaLoc.setLotNo(stoneTran.getLotNo());
							stoneTranDiaLoc.setTranDate(new Date());
							stoneTranDiaLoc.setDepartment(departmentService.findByName("Bagging"));
							stoneTranDiaLoc.setDeptLocation(location);
							stoneTranDiaLoc.setStoneType(stoneTran.getStoneType());
							stoneTranDiaLoc.setShape(stoneTran.getShape());
							stoneTranDiaLoc.setQuality(stoneTran.getQuality());
							stoneTranDiaLoc.setSize(stoneTran.getSize());
							stoneTranDiaLoc.setSieve(stoneTran.getSieve());
							stoneTranDiaLoc.setSizeGroup(stoneTran.getSizeGroup());
							stoneTranDiaLoc.setStone(adjStone);
							stoneTranDiaLoc.setCarat(Math.round(adjCarat * 1000.0) / 1000.0);
							stoneTranDiaLoc.setBalStone(adjStone * -1);
							stoneTranDiaLoc.setBalCarat((Math.round(adjCarat * 1000.0) / 1000.0) * -1);
							stoneTranDiaLoc.setRate(stoneTran.getRate());
							stoneTranDiaLoc.setInOutFld("D");
							stoneTranDiaLoc.setBagMt(null);
							stoneTranDiaLoc.setBagSrNo(0);
							stoneTranDiaLoc.setSettingType(null);
							stoneTranDiaLoc.setSetting(null);
							stoneTranDiaLoc.setParty(orderMt.getParty());
							stoneTranDiaLoc.setTranType("ordAlloc");
							stoneTranDiaLoc.setSordMtId(orderMt.getId());
							stoneTranDiaLoc.setCreatedDt(new Date());
							stoneTranDiaLoc.setCreatedBy(principal.getName());
							stoneTranService.save(stoneTranDiaLoc);

							StoneTran stoneTranBagLoc = new StoneTran();
							stoneTranBagLoc.setAvgRate(stoneTran.getRate());
							stoneTranBagLoc.setLotNo(stoneTran.getLotNo());
							stoneTranBagLoc.setTranDate(new Date());
							stoneTranBagLoc.setDepartment(departmentService.findByName("Diamond"));
							stoneTranBagLoc.setDeptLocation(departmentService.findByName("Bagging"));
							stoneTranBagLoc.setStoneType(stoneTran.getStoneType());
							stoneTranBagLoc.setShape(stoneTran.getShape());
							stoneTranBagLoc.setQuality(stoneTran.getQuality());
							stoneTranBagLoc.setSize(stoneTran.getSize());
							stoneTranBagLoc.setSieve(stoneTran.getSieve());
							stoneTranBagLoc.setSizeGroup(stoneTran.getSizeGroup());
							stoneTranBagLoc.setStone(adjStone);
							stoneTranBagLoc.setCarat(Math.round(adjCarat * 1000.0) / 1000.0);
							stoneTranBagLoc.setBalStone(adjStone);
							stoneTranBagLoc.setBalCarat(Math.round(adjCarat * 1000.0) / 1000.0);
							stoneTranBagLoc.setRate(stoneTran.getRate());
							stoneTranBagLoc.setInOutFld("C");
							stoneTranBagLoc.setBagMt(null);
							stoneTranBagLoc.setBagSrNo(0);
							stoneTranBagLoc.setSettingType(null);
							stoneTranBagLoc.setSetting(null);
							stoneTranBagLoc.setParty(orderMt.getParty());
							stoneTranBagLoc.setTranType("ordAlloc");
							stoneTranBagLoc.setSordMtId(orderMt.getId());
							stoneTranBagLoc.setCreatedDt(new Date());
							stoneTranBagLoc.setCreatedBy(principal.getName());
							stoneTranBagLoc.setTransferRate((double) Math.round((stoneTranBagLoc.getAvgRate() + (stoneTranBagLoc.getAvgRate() * stoneTran.getStoneType().getTransferRatePerc() / 100)) * 100)/ 100);
							stoneTranBagLoc.setFactoryRate((double) Math.round((stoneTranBagLoc.getTransferRate() + (stoneTranBagLoc.getTransferRate()* stoneTran.getStoneType().getFactoryRatePerc() / 100)) * 100)/ 100);
							stoneTranService.save(stoneTranBagLoc);

							break;
						} else {

							adjCarat = Math.round((adjCarat - stoneTran.getBalCarat()) * 1000.0) / 1000.0;
							adjStone = Math.round(adjStone - stoneTran.getBalStone());

							StoneTran stoneTranDiaLoc = new StoneTran();
							stoneTranDiaLoc.setAvgRate(stoneTran.getRate());
							stoneTranDiaLoc.setLotNo(stoneTran.getLotNo());
							stoneTranDiaLoc.setTranDate(new Date());
							stoneTranDiaLoc.setDepartment(departmentService.findByName("Bagging"));
							stoneTranDiaLoc.setDeptLocation(location);
							stoneTranDiaLoc.setStoneType(stoneTran.getStoneType());
							stoneTranDiaLoc.setShape(stoneTran.getShape());
							stoneTranDiaLoc.setQuality(stoneTran.getQuality());
							stoneTranDiaLoc.setSize(stoneTran.getSize());
							stoneTranDiaLoc.setSieve(stoneTran.getSieve());
							stoneTranDiaLoc.setSizeGroup(stoneTran.getSizeGroup());
							stoneTranDiaLoc.setStone(stoneTran.getBalStone());
							stoneTranDiaLoc.setCarat(Math.round(stoneTran.getBalCarat() * 1000.0) / 1000.0);
							stoneTranDiaLoc.setBalStone(stoneTran.getBalStone() * -1);
							stoneTranDiaLoc.setBalCarat((Math.round(stoneTran.getBalCarat() * 1000.0) / 1000.0) * -1);
							stoneTranDiaLoc.setRate(stoneTran.getRate());
							stoneTranDiaLoc.setInOutFld("D");
							stoneTranDiaLoc.setBagMt(null);
							stoneTranDiaLoc.setBagSrNo(0);
							stoneTranDiaLoc.setSettingType(null);
							stoneTranDiaLoc.setSetting(null);
							stoneTranDiaLoc.setParty(orderMt.getParty());
							stoneTranDiaLoc.setTranType("ordAlloc");
							stoneTranDiaLoc.setSordMtId(orderMt.getId());
							stoneTranDiaLoc.setCreatedDt(new Date());
							stoneTranDiaLoc.setCreatedBy(principal.getName());
							stoneTranService.save(stoneTranDiaLoc);

							StoneTran stoneTranBagLoc = new StoneTran();
							stoneTranBagLoc.setAvgRate(stoneTran.getRate());
							stoneTranBagLoc.setLotNo(stoneTran.getLotNo());
							stoneTranBagLoc.setTranDate(new Date());
							stoneTranBagLoc.setDepartment(departmentService.findByName("Diamond"));
							stoneTranBagLoc.setDeptLocation(departmentService.findByName("Bagging"));
							stoneTranBagLoc.setStoneType(stoneTran.getStoneType());
							stoneTranBagLoc.setShape(stoneTran.getShape());
							stoneTranBagLoc.setQuality(stoneTran.getQuality());
							stoneTranBagLoc.setSize(stoneTran.getSize());
							stoneTranBagLoc.setSieve(stoneTran.getSieve());
							stoneTranBagLoc.setSizeGroup(stoneTran.getSizeGroup());
							stoneTranBagLoc.setStone(stoneTran.getBalStone());
							stoneTranBagLoc.setCarat(Math.round(stoneTran.getBalCarat() * 1000.0) / 1000.0);
							stoneTranBagLoc.setBalStone(stoneTran.getBalStone());
							stoneTranBagLoc.setBalCarat(Math.round(stoneTran.getBalCarat() * 1000.0) / 1000.0);
							stoneTranBagLoc.setRate(stoneTran.getRate());
							stoneTranBagLoc.setInOutFld("C");
							stoneTranBagLoc.setBagMt(null);
							stoneTranBagLoc.setBagSrNo(0);
							stoneTranBagLoc.setSettingType(null);
							stoneTranBagLoc.setSetting(null);
							stoneTranBagLoc.setParty(orderMt.getParty());
							stoneTranBagLoc.setTranType("ordAlloc");
							stoneTranBagLoc.setSordMtId(orderMt.getId());
							stoneTranBagLoc.setCreatedDt(new Date());
							stoneTranBagLoc.setCreatedBy(principal.getName());
							stoneTranBagLoc.setTransferRate((double) Math.round((stoneTranBagLoc.getAvgRate() + (stoneTranBagLoc.getAvgRate() * stoneTran.getStoneType().getTransferRatePerc() / 100)) * 100)/ 100);
							stoneTranBagLoc.setFactoryRate((double) Math.round((stoneTranBagLoc.getTransferRate() + (stoneTranBagLoc.getTransferRate()* stoneTran.getStoneType().getFactoryRatePerc() / 100)) * 100)/ 100);
							stoneTranService.save(stoneTranBagLoc);

						}

					}

				}
				 
				
			}
			
			
		}
		
		
		return "1";
	}

	@Override
	public String returnOrdStock(String pInvNo, String data, Principal principal, Boolean allowNegative,
			String stockType, String companyName) {
		// TODO Auto-generated method stub
		JSONArray jsonBagDt = new JSONArray(data);
		
		 Department location=  departmentService.findByName("Bagging");
		
		
		
		
		OrderMt orderMt =orderMtService.findByInvNoAndDeactive(pInvNo, false);
		
		for (int y = 0; y < jsonBagDt.length(); y++) {
			JSONObject dataOrderStnDt = (JSONObject) jsonBagDt.get(y);
			
			Double adjCarat = dataOrderStnDt.getDouble("issueCarat");
			Integer adjStone= dataOrderStnDt.getInt("issueStone");
			if(adjCarat>0) {
	
				StoneType stoneType =stoneTypeService.findByName(dataOrderStnDt.getString("stoneType"));
				Shape shape =shapeService.findByName(dataOrderStnDt.getString("shape"));
				Quality quality = qualityService.findByShapeAndName(shape, dataOrderStnDt.getString("quality"));
				SizeGroup sizeGroup =sizeGroupService.findByShapeAndNameAndDeactive(shape, dataOrderStnDt.getString("sizeGroup"), false);
				
				
				StoneTran stoneTranDiaLoc = new StoneTran();
				stoneTranDiaLoc.setAvgRate(dataOrderStnDt.getDouble("avgRate"));
				stoneTranDiaLoc.setLotNo("DeAlloc-"+dataOrderStnDt.getInt("stonetranId"));
				stoneTranDiaLoc.setTranDate(new Date()); 
				stoneTranDiaLoc.setDepartment(departmentService.findByName("Diamond"));
				stoneTranDiaLoc.setDeptLocation(location);
				stoneTranDiaLoc.setStoneType(stoneType);
				stoneTranDiaLoc.setShape(shape);
				stoneTranDiaLoc.setQuality(quality);
				stoneTranDiaLoc.setSize(dataOrderStnDt.getString("size"));
				stoneTranDiaLoc.setSieve(dataOrderStnDt.getString("sieve"));
				stoneTranDiaLoc.setSizeGroup(sizeGroup);
				stoneTranDiaLoc.setStone(adjStone);
				stoneTranDiaLoc.setCarat(adjCarat);		//-------------
				stoneTranDiaLoc.setBalStone(adjStone*-1);
				stoneTranDiaLoc.setBalCarat(adjCarat*-1); //--------------
				stoneTranDiaLoc.setRate(dataOrderStnDt.getDouble("avgRate"));
				stoneTranDiaLoc.setInOutFld("D");
				stoneTranDiaLoc.setParty(orderMt.getParty());
				stoneTranDiaLoc.setTranType("ordDeAlloc");
				stoneTranDiaLoc.setCreatedDt(new Date());
				stoneTranDiaLoc.setSordMtId(orderMt.getId());
				stoneTranDiaLoc.setCreatedBy(principal.getName());
				stoneTranDiaLoc.setTransferRate((double)Math.round((stoneTranDiaLoc.getAvgRate()+(stoneTranDiaLoc.getAvgRate()*stoneType.getTransferRatePerc()/100))*100)/100);
				stoneTranDiaLoc.setFactoryRate((double)Math.round((stoneTranDiaLoc.getTransferRate()+(stoneTranDiaLoc.getTransferRate()*stoneType.getFactoryRatePerc()/100))*100)/100);
				stoneTranService.save(stoneTranDiaLoc);
				
				
				
				StoneTran stoneTranBagLoc = new StoneTran();
				stoneTranBagLoc.setAvgRate(dataOrderStnDt.getDouble("avgRate"));
				stoneTranBagLoc.setLotNo("DeAlloc-"+dataOrderStnDt.getInt("stonetranId"));
				stoneTranBagLoc.setTranDate(new Date()); 
				stoneTranBagLoc.setDepartment(departmentService.findByName("Bagging"));
				stoneTranBagLoc.setDeptLocation(departmentService.findByName("Diamond"));
				stoneTranBagLoc.setStoneType(stoneType);
				stoneTranBagLoc.setShape(shape);
				stoneTranBagLoc.setQuality(quality);
				stoneTranBagLoc.setSize(dataOrderStnDt.getString("size"));
				stoneTranBagLoc.setSieve(dataOrderStnDt.getString("sieve"));
				stoneTranBagLoc.setSizeGroup(sizeGroup);
				stoneTranBagLoc.setStone(adjStone);
				stoneTranBagLoc.setCarat(adjCarat);		//-------------
				stoneTranBagLoc.setBalStone(adjStone);
				stoneTranBagLoc.setBalCarat(adjCarat); //--------------
				stoneTranBagLoc.setRate(dataOrderStnDt.getDouble("avgRate"));
				stoneTranBagLoc.setInOutFld("C");
				stoneTranBagLoc.setParty(orderMt.getParty());
				stoneTranBagLoc.setTranType("ordDeAlloc");
				stoneTranBagLoc.setCreatedDt(new Date());
				stoneTranBagLoc.setCreatedBy(principal.getName());
				stoneTranBagLoc.setTransferRate((double)Math.round((stoneTranBagLoc.getAvgRate()+(stoneTranBagLoc.getAvgRate()*stoneType.getTransferRatePerc()/100))*100)/100);
				stoneTranBagLoc.setFactoryRate((double)Math.round((stoneTranBagLoc.getTransferRate()+(stoneTranBagLoc.getTransferRate()*stoneType.getFactoryRatePerc()/100))*100)/100);
				stoneTranService.save(stoneTranBagLoc);
				
				
				
				
				
				
				
				
				
				
				
				
				
							
				
			
				
				
			
			
				
			}
			
			
		}
		
		
		return "1";
	}
	
}
