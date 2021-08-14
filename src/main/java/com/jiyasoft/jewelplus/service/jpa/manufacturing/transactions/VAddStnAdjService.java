package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddStnAdjRepositoty;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnDtService;

@Service
@Transactional
@Repository
public class VAddStnAdjService implements IVAddStnAdjService{
	
	
	@Autowired
	private IVAddStnAdjRepositoty vAddStnAdjRepositoty;
	
	@Autowired
	private IVAddStnDtService vAddStnDtService;
	
	@Autowired
	private IVAddDtService vAddDtService;
	
	@Autowired
	private IStonePurchaseDtService stonePurchaseDtService;
	
	@Autowired
	private ICostingMtService costingMtService;

	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(VAddStnAdj vAddStnAdj) {
		// TODO Auto-generated method stub
		vAddStnAdjRepositoty.save(vAddStnAdj);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
		vAddStnAdjRepositoty.delete(id);
	}

	@Override
	public VAddStnAdj findOne(int id) {
		// TODO Auto-generated method stub
		return vAddStnAdjRepositoty.findOne(id);
	}

	@Override
	public String saveStoneAdjustment(String dtIds, Integer costMtIdPk, Integer stonetypeid, Integer shapeid,
			Integer qualityid, Integer sizegroupid, String size, Double rate, Double carat, Principal principal,String adjCarat,String vaddstnadjtype) {
		// TODO Auto-generated method stub
		
		
		String retVal = "-1";
		
		if(dtIds.length() > 0 && dtIds != "" ){
			
			String stnPurcDtId[] 	= dtIds.split(",");
			
			CostingMt costingMt = costingMtService.findOne(costMtIdPk);
			
			String vAdjCart[] = adjCarat.split(",");

			for (int j = 0; j < stnPurcDtId.length; j++) {

				StonePurchaseDt stonePurchaseDt =stonePurchaseDtService.findOne(Integer.parseInt(stnPurcDtId[j]));
				
				Double stockCarat = stonePurchaseDt.getBalCarat();
				Double currAdjCarat = Double.parseDouble(vAdjCart[j]);

				if (stockCarat < currAdjCarat) {
					retVal = "-3";
					break;
				}



			} // ending the first for loop

			if (retVal == "-3") {
				return retVal;
			}

			
			
			
			
			Double caratSum=0.0;
			Double stnValSum=0.0;
			for(int i = 0;i<stnPurcDtId.length;i++){
				
				
			StonePurchaseDt stonePurchaseDt =stonePurchaseDtService.findOne(Integer.parseInt(stnPurcDtId[i]));

			Double sAdjCarat = Double.parseDouble(vAdjCart[i]);

			if (sAdjCarat <= 0) {
				continue;
			}

			
			
			VAddStnAdj vAddStnAdjNew = new VAddStnAdj();
			
			vAddStnAdjNew.setCostingMt(costingMt);
			vAddStnAdjNew.setStonePurchaseDt(stonePurchaseDt);
			vAddStnAdjNew.setStoneType(stoneTypeService.findOne(stonetypeid));
			vAddStnAdjNew.setShape(shapeService.findOne(shapeid));
			vAddStnAdjNew.setQuality(qualityService.findOne(qualityid));
			vAddStnAdjNew.setSizeGroup(sizeGroupService.findOne(sizegroupid));
			vAddStnAdjNew.setSize(size);
			vAddStnAdjNew.setAdjustmentWt(sAdjCarat);
			vAddStnAdjNew.setStnRate(stonePurchaseDt.getRate());
			vAddStnAdjNew.setCreatedBy(principal.getName());
			vAddStnAdjNew.setCreatedDt(new java.util.Date());
			save(vAddStnAdjNew);
			caratSum+=sAdjCarat;
			stnValSum+=sAdjCarat*stonePurchaseDt.getRate();
			
//----------------update InwardDt metal-------------------------------------//
			
			stonePurchaseDt.setBalCarat( Math.round((stonePurchaseDt.getBalCarat() - sAdjCarat)*1000.0)/1000.0);
			stonePurchaseDtService.save(stonePurchaseDt);
			
			
		} //end loop
			
			Double avgRate= (double) Math.round(stnValSum/caratSum*100)/100;
			
			/*-------------------------------------------------Stone Value Fob--------------------------------*/		
			List<VAddStnDt>vAddStnDts =null;
			
			if(vaddstnadjtype.equalsIgnoreCase("Quality")) {
				vAddStnDts = vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndQualityAndDeactive(costingMt,stoneTypeService.findOne(stonetypeid), shapeService.findOne(shapeid), qualityService.findOne(qualityid),false);
			}else if(vaddstnadjtype.equalsIgnoreCase("SizeGroup")) {
				vAddStnDts=vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(costingMt, stoneTypeService.findOne(stonetypeid), shapeService.findOne(shapeid), qualityService.findOne(qualityid), sizeGroupService.findOne(sizegroupid), false);
			}else if(vaddstnadjtype.equalsIgnoreCase("Shape")) {
				vAddStnDts=vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndDeactive(costingMt, stoneTypeService.findOne(stonetypeid), shapeService.findOne(shapeid), false);
			}else {
				vAddStnDts=vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndDeactive(costingMt, stoneTypeService.findOne(stonetypeid), shapeService.findOne(shapeid), qualityService.findOne(qualityid), size, false);
			}
			
			
			for(VAddStnDt vAddStnDt :vAddStnDts) {
				vAddStnDt.setStnRate(avgRate);
				if(vAddStnDt.getPerPcsRateFlg().equals(false)){
					vAddStnDt.setStoneValue(Math.round((vAddStnDt.getCarat() * vAddStnDt.getStnRate())*100.0)/100.0);
				}else{
					vAddStnDt.setStoneValue(Math.round((vAddStnDt.getStone() * vAddStnDt.getStnRate())*100.0)/100.0);
				}
				vAddStnDtService.save(vAddStnDt);
				
				
				List<VAddStnDt>vAddStnDts2 =vAddStnDtService.findByVAddDtAndDeactive(vAddStnDt.getvAddDt(), false);
				Double totStoneVal=0.0;
				for(VAddStnDt vAddStnDt2 :vAddStnDts2){
					totStoneVal +=vAddStnDt2.getStoneValue();
					
					
				}
				
				VAddDt vAddDt = vAddStnDt.getvAddDt();
				vAddDt.setStoneValue(Math.round(totStoneVal*1000.0)/1000.0);
				vAddDtService.save(vAddDt);
				
				
			}
			
			
			
			
			retVal = "success";
			
		}else{
			retVal = "-2";
		}
		
		return retVal;

	}
	


	@Override
	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShape(CostingMt costingMt, StoneType stoneType,
			Shape shape) {
		// TODO Auto-generated method stub
		return vAddStnAdjRepositoty.findByCostingMtAndStoneTypeAndShape(costingMt, stoneType, shape);
	}

	@Override
	public String deleteAdjustment(Integer costMtIdPk, Integer stonetypeid, Integer shapeid, Integer qualityid,
			Integer sizegroupid, String size, Double rate, Principal principal,String vaddstnadjtype) {
		
		String retVal="-1";
		
		
		try {
			
			
			List<VAddStnAdj> vAddStnDts= null; 
			
			if(vaddstnadjtype.equalsIgnoreCase("Quality")) {
				vAddStnDts=findByCostingMtAndStoneTypeAndShapeAndQuality(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
						shapeService.findOne(shapeid), qualityService.findOne(qualityid));
			}else if(vaddstnadjtype.equalsIgnoreCase("SizeGroup")) {
				vAddStnDts=findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroup(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
						shapeService.findOne(shapeid), qualityService.findOne(qualityid), sizeGroupService.findOne(sizegroupid));
			}else if(vaddstnadjtype.equalsIgnoreCase("Shape")) {
				vAddStnDts=findByCostingMtAndStoneTypeAndShape(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
						shapeService.findOne(shapeid));
			}else {
				vAddStnDts=findByCostingMtAndStoneTypeAndShapeAndQualityAndSize(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
						shapeService.findOne(shapeid), qualityService.findOne(qualityid), size);
			}
			
			
			for (VAddStnAdj vAddStnAdj : vAddStnDts) {
				
				StonePurchaseDt stonePurchaseDt = stonePurchaseDtService.findOne(vAddStnAdj.getStonePurchaseDt().getId());
				//VAddStnAdj addStnAdj = findByStonePurchaseDt(stonePurchaseDt);
				
				Double balanceCarat = stonePurchaseDt.getBalCarat()+vAddStnAdj.getAdjustmentWt();
				stonePurchaseDt.setBalCarat(balanceCarat);
				stonePurchaseDtService.save(stonePurchaseDt);
				
				delete(vAddStnAdj.getId());
				
				
			}
			
			
			
			/*
			 * if(vaddstnadjtype.equalsIgnoreCase("size")){
			 * 
			 * List<VAddStnAdj> vAddStnAdjs =
			 * findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndStnRateAndSizeGroup(
			 * costingMt, stoneType, shape, quality, size, rate, sizeGroup); for (VAddStnAdj
			 * vAddStnAdj : vAddStnAdjs) {
			 * 
			 * StonePurchaseDt stonePurchaseDt =
			 * stonePurchaseDtService.findOne(vAddStnAdj.getStonePurchaseDt().getId());
			 * VAddStnAdj addStnAdj = findByStonePurchaseDt(stonePurchaseDt);
			 * 
			 * Double balanceCarat =
			 * stonePurchaseDt.getBalCarat()+addStnAdj.getAdjustmentWt();
			 * stonePurchaseDt.setBalCarat(balanceCarat);
			 * stonePurchaseDtService.save(stonePurchaseDt);
			 * 
			 * delete(vAddStnAdj.getId());
			 * 
			 * 
			 * }
			 * 
			 * retVal="1"; }else if(vaddstnadjtype.equalsIgnoreCase("sizeGroup")){
			 * 
			 * List<VAddStnAdj> vAddStnAdjs =
			 * findByCostingMtAndStoneTypeAndShapeAndQualityAndStnRateAndSizeGroup(
			 * costingMt, stoneType, shape, quality, rate, sizeGroup); for (VAddStnAdj
			 * vAddStnAdj : vAddStnAdjs) {
			 * 
			 * StonePurchaseDt stonePurchaseDt =
			 * stonePurchaseDtService.findOne(vAddStnAdj.getStonePurchaseDt().getId());
			 * VAddStnAdj addStnAdj = findByStonePurchaseDt(stonePurchaseDt);
			 * 
			 * Double balanceCarat =
			 * stonePurchaseDt.getBalCarat()+addStnAdj.getAdjustmentWt();
			 * stonePurchaseDt.setBalCarat(balanceCarat);
			 * stonePurchaseDtService.save(stonePurchaseDt);
			 * 
			 * delete(vAddStnAdj.getId());
			 * 
			 * }
			 * 
			 * retVal="1"; }else if(vaddstnadjtype.equalsIgnoreCase("quality")){
			 * 
			 * List<VAddStnAdj> vAddStnAdjs =
			 * findByCostingMtAndStoneTypeAndShapeAndQualityAndStnRate(costingMt, stoneType,
			 * shape, quality, rate); for (VAddStnAdj vAddStnAdj : vAddStnAdjs) {
			 * 
			 * StonePurchaseDt stonePurchaseDt =
			 * stonePurchaseDtService.findOne(vAddStnAdj.getStonePurchaseDt().getId());
			 * VAddStnAdj addStnAdj = findByStonePurchaseDt(stonePurchaseDt);
			 * 
			 * Double balanceCarat =
			 * stonePurchaseDt.getBalCarat()+addStnAdj.getAdjustmentWt();
			 * stonePurchaseDt.setBalCarat(balanceCarat);
			 * stonePurchaseDtService.save(stonePurchaseDt);
			 * 
			 * delete(vAddStnAdj.getId());
			 * 
			 * }
			 * 
			 * retVal="1"; }else if(vaddstnadjtype.equalsIgnoreCase("shape")){
			 * 
			 * List<VAddStnAdj> vAddStnAdjs =
			 * findByCostingMtAndStoneTypeAndShapeAndStnRate(costingMt,stoneType,shape,rate)
			 * ; for (VAddStnAdj vAddStnAdj : vAddStnAdjs) {
			 * 
			 * StonePurchaseDt stonePurchaseDt =
			 * stonePurchaseDtService.findOne(vAddStnAdj.getStonePurchaseDt().getId());
			 * VAddStnAdj addStnAdj = findByStonePurchaseDt(stonePurchaseDt);
			 * 
			 * Double balanceCarat =
			 * stonePurchaseDt.getBalCarat()+addStnAdj.getAdjustmentWt();
			 * stonePurchaseDt.setBalCarat(balanceCarat);
			 * stonePurchaseDtService.save(stonePurchaseDt);
			 * 
			 * delete(vAddStnAdj.getId());
			 * 
			 * }
			 * 
			 * retVal="1"; }
			 */
			
			retVal="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public VAddStnAdj findByStonePurchaseDt(StonePurchaseDt stonePurchaseDt) {
		// TODO Auto-generated method stub
		return vAddStnAdjRepositoty.findByStonePurchaseDt(stonePurchaseDt);
	}

	@Override
	public String deleteAllAdjustment(Integer costMtIdPk, Principal principal, String vaddstnadjtype) {
		
		String retVal="-1";
	try {
		
		List<VAddStnAdj>vAddStnAdjs=findByCostingMt(costingMtService.findOne(costMtIdPk));
		
		
		for (VAddStnAdj vAddStnAdj : vAddStnAdjs) {
			
			StonePurchaseDt stonePurchaseDt = stonePurchaseDtService.findOne(vAddStnAdj.getStonePurchaseDt().getId());
			//VAddStnAdj addStnAdj = findByStonePurchaseDt(stonePurchaseDt);
			
			Double balanceCarat = stonePurchaseDt.getBalCarat()+vAddStnAdj.getAdjustmentWt();
			stonePurchaseDt.setBalCarat(balanceCarat);
			stonePurchaseDtService.save(stonePurchaseDt);
			
			delete(vAddStnAdj.getId());
				
		}
		
		retVal="1";
		
		
	
		
	} catch (Exception e) {
		// TODO: handle exception
	}
			
		return retVal;
	}

	@Override
	public String vaddStnDtListing(Integer vAddMtId,String vaddstnadjtype) {
		StringBuilder sb = new StringBuilder();
			
		
		@SuppressWarnings("unchecked")
		TypedQuery<VAddStnDt> query = (TypedQuery<VAddStnDt>) entityManager.createNativeQuery("{ call jsp_vaddstnadj(?,?) }",VAddStnDt.class);
		query.setParameter(1, vAddMtId);
		query.setParameter(2, vaddstnadjtype);
		
		List<VAddStnDt>vAddStnDts = query.getResultList();
		
			
			
			sb.append("{\"total\":").append(vAddStnDts.size())
			.append(",\"rows\": [");

			for(VAddStnDt vAddStnDt:vAddStnDts){
					
					sb.append("{\"id\":\"")
						.append(vAddStnDt.getId())
						.append("\",\"stoneType\":\"")
						.append((vAddStnDt.getStoneType() != null ? vAddStnDt.getStoneType().getName() : ""))
						.append("\",\"shape\":\"")
						.append((vAddStnDt.getShape() != null ? vAddStnDt.getShape().getName() : ""))
						.append("\",\"quality\":\"")
						.append((vAddStnDt.getQuality() != null ? vAddStnDt.getQuality().getName() : ""))
						.append("\",\"qualityGroup\":\"")
						.append((vAddStnDt.getQuality() != null ? vAddStnDt.getQuality().getQualityGroup() : ""))
						.append("\",\"size\":\"")
						.append((vAddStnDt.getSize() != null ? vAddStnDt.getSize() : ""))
						.append("\",\"sizeGroup\":\"")
						.append((vAddStnDt.getSizeGroup() != null ? vAddStnDt.getSizeGroup().getName() : ""))
						.append("\",\"stone\":\"")
						.append((vAddStnDt.getStone() != null ? vAddStnDt.getStone() : ""))
						.append("\",\"carat\":\"")
						.append((vAddStnDt.getCarat() != null ? vAddStnDt.getCarat() : ""))
						.append("\",\"rate\":\"")
						.append((vAddStnDt.getStnRate() != null ? vAddStnDt.getStnRate() : ""))
						.append("\",\"stonetypeid\":\"")
						.append((vAddStnDt.getStoneType() != null ? vAddStnDt.getStoneType().getId() : ""))
						.append("\",\"shapeid\":\"")
						.append((vAddStnDt.getShape() != null ? vAddStnDt.getShape().getId() : ""))
						.append("\",\"qualityid\":\"")
						.append((vAddStnDt.getQuality() != null ? vAddStnDt.getQuality().getId() : ""))
						.append("\",\"sizegroupid\":\"")
						.append((vAddStnDt.getSizeGroup() != null ? vAddStnDt.getSizeGroup().getId() : ""))
						.append("\",\"settingType\":\"")
						.append((vAddStnDt.getSettingType() != null ? vAddStnDt.getSettingType().getName() : ""))
						.append("\",\"settingRate\":\"")
						.append((vAddStnDt.getSetRate() != null ? vAddStnDt.getSetRate() : ""))
						.append("\",\"settingValue\":\"")
						.append((vAddStnDt.getSetValue() != null ? vAddStnDt.getSetValue() : ""))
						.append("\",\"rLock\":\"")
						.append((vAddStnDt.getrLock() == null ? "": (vAddStnDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
						.append("\",\"actionLock\":\"")
						.append("<a href='javascript:doStoneDtLockUnLock(")
						.append(vAddStnDt.getId())
						.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
						.append("\",\"action1\":\"")
						.append("<a href='javascript:editvAddStnDt(")
						.append(vAddStnDt.getId())
						.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
						.append("\",\"action2\":\"")
						.append("<a href='javascript:deleteStoneAdjument(event,")
						.append(vAddStnDt.getId())
						.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete Adjustment</a>")
						.append("\"},");
					
				}
			
			
			
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";

			return str;
	}

	@Override
	public String adjListing(String size, Integer costMtIdPk, Integer stonetypeid, Integer shapeid, Integer qualityid,
			Integer sizegroupid, String vaddstnadjtype) {

		StringBuilder sb = new StringBuilder();
		
		List<VAddStnAdj> vAddStnDts= null; 
				
		if(vaddstnadjtype.equalsIgnoreCase("Quality")) {
			vAddStnDts=findByCostingMtAndStoneTypeAndShapeAndQuality(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
					shapeService.findOne(shapeid), qualityService.findOne(qualityid));
		}else if(vaddstnadjtype.equalsIgnoreCase("SizeGroup")) {
			vAddStnDts=findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroup(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
					shapeService.findOne(shapeid), qualityService.findOne(qualityid), sizeGroupService.findOne(sizegroupid));
		}else if(vaddstnadjtype.equalsIgnoreCase("Shape")) {
			vAddStnDts=findByCostingMtAndStoneTypeAndShape(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
					shapeService.findOne(shapeid));
		}else {
			vAddStnDts=findByCostingMtAndStoneTypeAndShapeAndQualityAndSize(costingMtService.findOne(costMtIdPk), stoneTypeService.findOne(stonetypeid),
					shapeService.findOne(shapeid), qualityService.findOne(qualityid), size);
		}
		
		
		sb.append("{\"total\":").append(vAddStnDts.size()).append(",\"rows\": [");

		for(VAddStnAdj vAddStnAdj : vAddStnDts){
				
				sb.append("{\"id\":\"")
					.append(vAddStnAdj.getId())
					.append("\",\"invNo\":\"")
					.append((vAddStnAdj.getStonePurchaseDt() != null ? vAddStnAdj.getStonePurchaseDt().getStonePurchaseMt().getInvNo() : ""))
					.append("\",\"invDate\":\"")
					.append((vAddStnAdj.getStonePurchaseDt().getStonePurchaseMt().getInvDateStr()))
					.append("\",\"adjustedWt\":\"")
					.append((vAddStnAdj.getAdjustmentWt() != null ? vAddStnAdj.getAdjustmentWt() : ""))
					.append("\",\"rate\":\"")
					.append((vAddStnAdj.getStnRate() != null ? vAddStnAdj.getStnRate() : ""))
					.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@Override
	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQualityAndSize(CostingMt costingMt,
			StoneType stoneType, Shape shape, Quality quality, String size) {
		// TODO Auto-generated method stub
		return vAddStnAdjRepositoty.findByCostingMtAndStoneTypeAndShapeAndQualityAndSize(costingMt, stoneType, shape, quality, size);
	}

	@Override
	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroup(CostingMt costingMt,
			StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup) {
		// TODO Auto-generated method stub
		return vAddStnAdjRepositoty.findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroup(costingMt, stoneType, shape, quality, sizeGroup);
	}

	@Override
	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQuality(CostingMt costingMt, StoneType stoneType,
			Shape shape, Quality quality) {
		// TODO Auto-generated method stub
		return vAddStnAdjRepositoty.findByCostingMtAndStoneTypeAndShapeAndQuality(costingMt, stoneType, shape, quality);
	}

	@Override
	public List<VAddStnAdj> findByCostingMt(CostingMt costingMt) {
		// TODO Auto-generated method stub
		return vAddStnAdjRepositoty.findByCostingMt(costingMt);
	}

	@Override
	public String fifoAllAdjustment(Integer vAddMtId, String vaddstnadjtype,Principal principal) {
		// TODO Auto-generated method stub
		
		CostingMt costingMt =costingMtService.findOne(vAddMtId);
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{ call jsp_vaddstnadj(?,?) }");
		query.setParameter(1, vAddMtId);
		query.setParameter(2, vaddstnadjtype);
		
		List<Object[]>vAddStnDtObjList = query.getResultList();
		
		for(Object[] obj : vAddStnDtObjList) {
			
		StoneType stoneType= stoneTypeService.findOne(Integer.parseInt(obj[42].toString()));
		Shape shape= shapeService.findOne(Integer.parseInt(obj[39].toString()));
		Quality quality= qualityService.findOne(Integer.parseInt(obj[36].toString()));
		SizeGroup sizeGroup=sizeGroupService.findOne(Integer.parseInt(obj[40].toString()));
		
	/*------------------ check adjustment done or not by following logic ------------------------*/
			
			
			List<VAddStnAdj> vAddStnAdjs= null; 
			
			if(vaddstnadjtype.equalsIgnoreCase("Quality")) {
				vAddStnAdjs=findByCostingMtAndStoneTypeAndShapeAndQuality(costingMt,stoneType ,
						shape, quality);
			}else if(vaddstnadjtype.equalsIgnoreCase("SizeGroup")) {
				vAddStnAdjs=findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroup(costingMt, stoneType,
						shape, quality, sizeGroup);
			}else if(vaddstnadjtype.equalsIgnoreCase("Shape")) {
				vAddStnAdjs=findByCostingMtAndStoneTypeAndShape(costingMt, stoneType,shape);
			}else {
				vAddStnAdjs=findByCostingMtAndStoneTypeAndShapeAndQualityAndSize(costingMt,stoneType,shape,
						quality, obj[28].toString());
			}
			
			
			Double adjustedCarat=0.0;
			
			for(VAddStnAdj vAddStnAdj :vAddStnAdjs) {
				adjustedCarat +=vAddStnAdj.getAdjustmentWt();
				
			}
			
			
			/*-------------------------------------------------------------*/	
			Double  reqAdjCarat = Math.round((Double.parseDouble(obj[2].toString())-adjustedCarat)*1000.0)/1000.0;
			
			System.out.println("reqAdjCarat  "+reqAdjCarat);
			
			
			
			/*------------------ Purchase List------------------------*/
			
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query1 =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_vaddstnpurcadj(?,?,?,?,?,?,?) }");
			query1.setParameter(1,stoneType.getId());
			query1.setParameter(2,shape.getId());
			query1.setParameter(3,quality.getId());
			query1.setParameter(4,sizeGroup.getId());
			query1.setParameter(5,obj[28].toString());
			query1.setParameter(6,vaddstnadjtype.trim());
			query1.setParameter(7,"");
			
			
			List<Object[]> dtList = query1.getResultList();
			Double stkCarat=0.0;
			for (Object[] result : dtList) {
				stkCarat +=Double.parseDouble(result[7].toString());
			}
			
			if(stkCarat>=reqAdjCarat) {
				
				
				Double caratSum=0.0;
				Double stnValSum=0.0;
				for (Object[] result : dtList) {
					
					if(reqAdjCarat>0) {
					StonePurchaseDt stonePurchaseDt =stonePurchaseDtService.findOne(Integer.parseInt(result[0].toString()));
					
						
					
					VAddStnAdj vAddStnAdjNew = new VAddStnAdj();
					if(stonePurchaseDt.getBalCarat() >reqAdjCarat) {
						
						vAddStnAdjNew.setAdjustmentWt(reqAdjCarat);
						reqAdjCarat=0.0;
						
						
						
					}else {
						vAddStnAdjNew.setAdjustmentWt(stonePurchaseDt.getBalCarat());
						reqAdjCarat =Math.round((reqAdjCarat-stonePurchaseDt.getBalCarat())*1000.0)/1000.0;     
								
					}
					
					
					
				
					
					vAddStnAdjNew.setCostingMt(costingMt);
					vAddStnAdjNew.setStonePurchaseDt(stonePurchaseDt);
					vAddStnAdjNew.setStoneType(stoneType);
					vAddStnAdjNew.setShape(shape);
					vAddStnAdjNew.setQuality(quality);
					vAddStnAdjNew.setSizeGroup(sizeGroup);
					vAddStnAdjNew.setSize(obj[28].toString());
					
					vAddStnAdjNew.setStnRate(stonePurchaseDt.getRate());
					vAddStnAdjNew.setCreatedBy(principal.getName());
					vAddStnAdjNew.setCreatedDt(new java.util.Date());
					save(vAddStnAdjNew);
					caratSum+=vAddStnAdjNew.getAdjustmentWt();
					stnValSum+=vAddStnAdjNew.getAdjustmentWt()*stonePurchaseDt.getRate();
					
		//----------------update InwardDt metal-------------------------------------//
					
					stonePurchaseDt.setBalCarat( Math.round((stonePurchaseDt.getBalCarat() - vAddStnAdjNew.getAdjustmentWt())*1000.0)/1000.0);
					stonePurchaseDtService.save(stonePurchaseDt);
				}
				}
				
				
				Double avgRate= (double) Math.round(stnValSum/caratSum*100)/100;
				if(avgRate>0) {
				
				/*-------------------------------------------------Stone Value Fob--------------------------------*/		
				List<VAddStnDt>vAddStnDtsList =null;
				
				if(vaddstnadjtype.equalsIgnoreCase("Quality")) {
					vAddStnDtsList = vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndQualityAndDeactive(costingMt,stoneType,
							shape,quality,false);
				}else if(vaddstnadjtype.equalsIgnoreCase("SizeGroup")) {
					vAddStnDtsList=vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(costingMt,stoneType,
							shape,quality,sizeGroup, false);
				}else if(vaddstnadjtype.equalsIgnoreCase("Shape")) {
					vAddStnDtsList=vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndDeactive(costingMt,stoneType,shape, false);
				}else {
					vAddStnDtsList=vAddStnDtService.findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndDeactive(costingMt,stoneType,
							shape,quality,obj[28].toString(), false);
				}
				
				 
				for(VAddStnDt vAddStnDt :vAddStnDtsList) {
					
					vAddStnDt.setStnRate(avgRate);
					if(vAddStnDt.getPerPcsRateFlg().equals(false)){
						vAddStnDt.setStoneValue(Math.round((vAddStnDt.getCarat() * vAddStnDt.getStnRate())*100.0)/100.0);
					}else{
						vAddStnDt.setStoneValue(Math.round((vAddStnDt.getStone() * vAddStnDt.getStnRate())*100.0)/100.0);
					}
					vAddStnDtService.save(vAddStnDt);
					
					
					List<VAddStnDt>vAddStnDts2 =vAddStnDtService.findByVAddDtAndDeactive(vAddStnDt.getvAddDt(), false);
					Double totStoneVal=0.0;
					for(VAddStnDt vAddStnDt3 :vAddStnDts2){
						totStoneVal +=vAddStnDt3.getStoneValue();
						
						
					}
					
					VAddDt vAddDt = vAddStnDt.getvAddDt();
					vAddDt.setStoneValue(Math.round(totStoneVal*1000.0)/1000.0);
					vAddDtService.save(vAddDt);
					
					
				}
				
				
				
				/*-------------------------------------------------------------*/	

				}
				
				
				
			}
			
			
			
			
			
			
		
		}
		
		
		
		return "1";
	}

}
