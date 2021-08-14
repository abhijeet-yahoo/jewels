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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IStoneTypeRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneOutwardDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class StoneOutwardDtService implements IStoneOutwardDtService {
	
	@Autowired
	IStoneOutwardDtRepository stoneOutwardDtRepository;
		
	@Autowired
	private IStoneOutwardMtService stoneOutwardMtService;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	
	@Autowired
	private IDepartmentService departmentService;
	

	@Override
	public List<StoneOutwardDt> findAll() {
		return stoneOutwardDtRepository.findAll();
	}

	@Override
	public Page<StoneOutwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return stoneOutwardDtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(StoneOutwardDt stoneOutwardDt) {
		stoneOutwardDtRepository.save(stoneOutwardDt);
		
	}

	@Override
	public void delete(int id) {
		StoneOutwardDt stoneOutwardDt = stoneOutwardDtRepository.findOne(id);
		stoneOutwardDt.setDeactive(true);
		stoneOutwardDt.setDeactiveDt(new java.util.Date());
		stoneOutwardDtRepository.save(stoneOutwardDt);		
	}

	@Override
	public Long count() {
		return stoneOutwardDtRepository.count();
	}

	@Override
	public StoneOutwardDt findOne(int id) {
		return stoneOutwardDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getStoneOutwardDtList() {
		return null;
	}

	@Override
	public Page<StoneOutwardDt> findByStoneOutwardMt(
			StoneOutwardMt stoneOutwardMt, Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return stoneOutwardDtRepository.findByStoneOutwardMt(stoneOutwardMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public StoneOutwardDt findByUniqueId(Long uniqueId) {
		return stoneOutwardDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<StoneOutwardDt> findByStoneOutwardMt(
			StoneOutwardMt stoneOutwardMt) {
		return stoneOutwardDtRepository.findByStoneOutwardMt(stoneOutwardMt);
	}

	@Override
	public List<StoneOutwardDt> findByStoneOutwardMtAndDeactiveOrderByIdDesc(
			StoneOutwardMt stoneOutwardMt, Boolean deactive) {
			return stoneOutwardDtRepository.findBystoneOutwardMtAndDeactiveOrderByIdDesc(stoneOutwardMt, deactive);

	}

	@Override
	public String saveStoneOutwardDt(StoneOutwardDt stoneOutwardDt, Integer id,
			String pInvNo, Double vCarat,Double prevCarat,Integer vStone, String sizeGroupStr,
			Principal principal,String stockType,Boolean allowNegative) {
		
		String action = "added";
		String retVal = "-1";
		
		Department location = departmentService.findByName("Diamond");
		

		
		SizeGroup sizeGroup =sizeGroupService.findByShapeAndNameAndDeactive(stoneOutwardDt.getShape(), sizeGroupStr,false);
			
			if(!allowNegative.equals(true)){
				
				Double stockBal = stoneTranService.getStockBalance(location.getId(),stoneOutwardDt.getStoneType().getId(),stoneOutwardDt.getShape().getId(), 
						stoneOutwardDt.getQuality().getId(),sizeGroup.getId(), stoneOutwardDt.getSieve(), stockType);
				
				if(stockBal <stoneOutwardDt.getCarat()){
					return "Error : Stock Not Available (Available In Stock : "+stockBal+")";
				}
				
			}
			
			stoneOutwardDt.setCreatedBy(principal.getName());
			stoneOutwardDt.setCreatedDt(new java.util.Date());
			stoneOutwardDt.setStoneOutwardMt(stoneOutwardMtService.findByInvNoAndDeactive(pInvNo, false));
			stoneOutwardDt.setUniqueId(new Date().getTime());
			stoneOutwardDt.setDepartment(location);
			stoneOutwardDt.setBalCarat(stoneOutwardDt.getCarat());
			stoneOutwardDt.setBalStone(stoneOutwardDt.getStone());
			stoneOutwardDt.setSizeGroup(sizeGroup);
			if (stoneOutwardDt.getSubShape().getId() == null) {
				stoneOutwardDt.setSubShape(null);
			}
			
			stoneOutwardDt.getRemark().trim();
			stoneOutwardDt.setRemark(stoneOutwardDt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			
			save(stoneOutwardDt);
			
			
	
			Double adjCarat = stoneOutwardDt.getCarat();
			Integer adjStone= stoneOutwardDt.getStone();

		
			StoneType stoneType =stoneTypeService.findOne(stoneOutwardDt.getStoneType().getId());
			Shape shape =shapeService.findOne(stoneOutwardDt.getShape().getId());
			Quality quality =qualityService.findOne(stoneOutwardDt.getQuality().getId());
		
		
		if(adjCarat>0) {
			
			List<StoneTran>stoneTrans =stoneTranService.getDiamondStockRate(stoneOutwardDt.getDepartment().getId(),stoneType.getName(),
					shape.getName(), quality.getName(), stoneOutwardDt.getSize(), stoneOutwardDt.getSieve());
			
			
			Double valSum=0.0;
			Double caratSum=0.0;

			
			 for (StoneTran stoneTranBal : stoneTrans) {
				 
				 if(adjCarat>0) {
					 
					 if(stoneTranBal.getBalCarat()>= adjCarat) {
						 
						 StoneTran stoneTran = new StoneTran();
							stoneTran.setCreatedBy(stoneOutwardDt.getCreatedBy());
							stoneTran.setCreatedDt(stoneOutwardDt.getCreatedDt());
							stoneTran.setLotNo(stoneTranBal.getLotNo());

							
							stoneTran.setTranDate(stoneOutwardDt.getStoneOutwardMt().getInvDate()); 
							stoneTran.setDepartment(null);
							stoneTran.setDeptLocation(stoneOutwardDt.getDepartment());
							stoneTran.setStoneType(stoneTranBal.getStoneType());
							stoneTran.setShape(stoneTranBal.getShape());
							stoneTran.setQuality(stoneTranBal.getQuality());
							stoneTran.setSize(stoneTranBal.getSize());
							stoneTran.setSieve(stoneTranBal.getSieve());
							stoneTran.setSizeGroup(stoneTranBal.getSizeGroup());
							stoneTran.setStone(adjStone);
							stoneTran.setCarat(adjCarat);		
							stoneTran.setBalStone(adjStone*-1);
							stoneTran.setBalCarat(adjCarat*-1); 
							stoneTran.setRate(stoneTranBal.getRate());
							stoneTran.setInOutFld("D"); 
							stoneTran.setBagMt(null);
							stoneTran.setBagSrNo(0);
							stoneTran.setSettingType(null);
							stoneTran.setSetting(null);
							stoneTran.setPacketNo(stoneOutwardDt.getPacketNo());
							stoneTran.setRemark(stoneOutwardDt.getRemark());
							stoneTran.setParty(stoneOutwardDt.getStoneOutwardMt().getParty());
							stoneTran.setTranType("Outward");
							stoneTran.setRefTranId(stoneOutwardDt.getId());
							stoneTran.setAvgRate(stoneTranBal.getRate());
							stoneTran.setTransferRate((double)Math.round((stoneTran.getAvgRate()+(stoneTran.getAvgRate()*stoneTranBal.getStoneType().getTransferRatePerc()/100))*100)/100);
							stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneTranBal.getStoneType().getFactoryRatePerc()/100))*100)/100);
							
							
							stoneTranService.save(stoneTran);
						 
						 valSum += Math.round((adjCarat*stoneTranBal.getRate())*100.0)/100.0;
						  caratSum += Math.round(adjCarat*1000.0)/1000.0;
						
						break;
					 }else {
						 adjCarat =(double) Math.round((adjCarat-stoneTranBal.getBalCarat())*1000)/1000;
							adjStone = Math.round(adjStone-stoneTranBal.getBalStone());
							
							 StoneTran stoneTran = new StoneTran();
								stoneTran.setCreatedBy(stoneOutwardDt.getCreatedBy());
								stoneTran.setCreatedDt(stoneOutwardDt.getCreatedDt());
								stoneTran.setLotNo(stoneTranBal.getLotNo());

								
								stoneTran.setTranDate(stoneOutwardDt.getStoneOutwardMt().getInvDate()); 
								stoneTran.setDepartment(null);
								stoneTran.setDeptLocation(stoneOutwardDt.getDepartment());
								stoneTran.setStoneType(stoneTranBal.getStoneType());
								stoneTran.setShape(stoneTranBal.getShape());
								stoneTran.setQuality(stoneTranBal.getQuality());
								stoneTran.setSize(stoneTranBal.getSize());
								stoneTran.setSieve(stoneTranBal.getSieve());
								stoneTran.setSizeGroup(stoneTranBal.getSizeGroup());
								stoneTran.setStone(stoneTranBal.getBalStone());
								stoneTran.setCarat(stoneTranBal.getBalCarat());		
								stoneTran.setBalStone(stoneTranBal.getBalStone()*-1);
								stoneTran.setBalCarat(stoneTranBal.getBalCarat()*-1); 
								stoneTran.setRate(stoneTranBal.getRate());
								stoneTran.setInOutFld("D"); 
								stoneTran.setBagMt(null);
								stoneTran.setBagSrNo(0);
								stoneTran.setSettingType(null);
								stoneTran.setSetting(null);
								stoneTran.setPacketNo(stoneOutwardDt.getPacketNo());
								stoneTran.setRemark(stoneOutwardDt.getRemark());
								stoneTran.setParty(stoneOutwardDt.getStoneOutwardMt().getParty());
								stoneTran.setTranType("Outward");
								stoneTran.setRefTranId(stoneOutwardDt.getId());
								stoneTran.setAvgRate(stoneTranBal.getRate());
								stoneTran.setTransferRate((double)Math.round((stoneTran.getAvgRate()+(stoneTran.getAvgRate()*stoneTranBal.getStoneType().getTransferRatePerc()/100))*100)/100);
								stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneTranBal.getStoneType().getFactoryRatePerc()/100))*100)/100);
								
								
								stoneTranService.save(stoneTran);
							
							 valSum += Math.round((stoneTranBal.getBalCarat()*stoneTranBal.getRate())*100.0)/100.0;
							  caratSum += Math.round(stoneTranBal.getBalCarat()*1000.0)/1000.0;
					 }
					 
					 
					
					 
					 
				 }
				 
				 
			 }
			 
			 Double avgRate= Math.round((valSum/caratSum)*100.0)/100.0;
			 stoneOutwardDt.setRate(avgRate);
			
			 save(stoneOutwardDt);
			
			
		}
		
		
		
		
		retVal="1"+","+action;
		
		return retVal;
	}

	@Override
	public String stoneOutDtDelete(Integer id, Principal principal) {
		
		String retVal ="-1";
		
		try {
			StoneOutwardDt stoneOutwardDt = findOne(id);
			if(principal.getName().equalsIgnoreCase("Administrator")){
					retVal = "1";
					
			}else{
			
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					StoneOutwardMt stoneOutwardMt = stoneOutwardMtService.findOne(stoneOutwardDt.getStoneOutwardMt().getId());
					Date cDate = stoneOutwardMt.getInvDate();
					String dbDate = dateFormat.format(cDate);
					
					Date date = new Date();
					String curDate = dateFormat.format(date);

					if(dbDate.equals(curDate)){
						
							retVal = "1";
						
									
					}
					
					
				}
			
			
			if(retVal !="-1"){
				delete(id);
				//StoneTran stoneTran=stoneTranService.RefTranIdAndTranType(id, "Outward");
				List<StoneTran>stoneTrans =stoneTranService.findByRefTranIdAndTranType(id, "Outward");
				for(StoneTran stoneTran :stoneTrans) {
					stoneTranService.delete(stoneTran.getId());
				}
				
				
			}
			retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public Integer getMaxTranSrno(Integer sordDtId) {
		QStoneOutwardDt qStoneOutwardDt = QStoneOutwardDt.stoneOutwardDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		List<Integer> maxSrno = query
			.from(qStoneOutwardDt)
			.where(qStoneOutwardDt.deactive.eq(false).and(qStoneOutwardDt.sordDtId.eq(sordDtId)))
			.list(qStoneOutwardDt.tranSrNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}
		
		if(retVal == null){
			retVal =0;
		}
		

		return retVal;
	}
	
	
	
	

}
