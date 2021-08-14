package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IPackCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;

@Service
@Repository
@Transactional
public class PackCompDtService implements IPackCompDtService {
	@Autowired
	private IPackCompDtRepository packCompDtRepository;
	
	@Autowired
	private IPackDtService packDtService;
	
	@Autowired
	private IPackMtService packMtService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	

	@Override
	public void save(PackCompDt packCompDt) {
		// TODO Auto-generated method stub
		packCompDtRepository.save(packCompDt);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		packCompDtRepository.delete(id);
		
	}

	@Override
	public PackCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return packCompDtRepository.findOne(id);
	}

	@Override
	public List<PackCompDt> findByPackDt(PackDt packDt) {
		// TODO Auto-generated method stub
		return packCompDtRepository.findByPackDt(packDt);
	}

	@Override
	public String updateCompRate(Principal principal, Integer packCompId, Double compRate) {
		PackCompDt packCompDt =findOne(packCompId);
		packCompDt.setCompRate(compRate);
		packCompDt.setModiBy(principal.getName());
		packCompDt.setModiDate(new Date());
		save(packCompDt);
		
		packDtService.updateFob(packCompDt.getPackDt());
		
		return "1";
	}

	@Override
	public String packCompDtListing(Integer dtId, String disableFlg) {
		// TODO Auto-generated method stub
		PackDt packDt=packDtService.findOne(dtId);
		List<PackCompDt>packCompDts = findByPackDt(packDt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(packCompDts.size()).append(",\"rows\": [");
		  for(PackCompDt packCompDt :packCompDts){
				sb.append("{\"id\":\"")
				.append(packCompDt.getId())
				.append("\",\"compName\":\"")
				.append((packCompDt.getComponent() != null ? packCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((packCompDt.getPurity() != null ? packCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((packCompDt.getColor() != null ? packCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((packCompDt.getCompWt() != null ? packCompDt.getCompWt() : ""))
				.append("\",\"rate\":\"")
				.append((packCompDt.getCompRate() != null ? packCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((packCompDt.getCompValue() != null ? packCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((packCompDt.getCompQty() != null ? packCompDt.getCompQty() : ""))
				.append("\",\"perPcs\":\"")
				.append(packCompDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(packCompDt.getPerGramRate())
				.append("\",\"rLock\":\"")
				.append((packCompDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doPackDtLockUnLock(")
				.append(packCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editPackCompDt(")
				.append(packCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deletePackCompDt(event,")
				.append(packCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(packCompDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
				}else {
					 sb.append("\",\"actionLock\":\"")
						.append("")
						.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						 .append("\"},");
				}
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public String packCompDtSave(PackCompDt packCompDt, Integer id, Integer packMtId, Integer packDtId,
			Integer componentId, Integer purityId, Integer colorId, Principal principal) {
		// TODO Auto-generated method stub
String retVal ="-1";
		
		try {
			
			PackMt packMt = packMtService.findOne(packMtId);
			PackDt packDt = packDtService.findOne(packDtId);
			
			Component component = componentService.findOne(componentId);
			Purity purity = purityService.findOne(purityId);
			Color color = colorService.findOne(colorId);
			
			
			int i=0;
			if(packCompDt.getPerPcRate() == true){
				i +=1;
			}
			
			if(packCompDt.getPerGramRate() == true){
				i +=1;
			}
			
			
			
			if(i >1){
				return retVal = "-11";
			}else if(i==0){
				return retVal = "-12";
			}
			
			
			
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				packCompDt.setPackMt(packMt);
				packCompDt.setPackDt(packDt);
				packCompDt.setCreatedBy(principal.getName());
				packCompDt.setCreatedDate(new java.util.Date());

				
				
			}else{
				packCompDt.setId(id);
				packCompDt.setPackMt(packMt);
				packCompDt.setPackDt(packDt);
			
				packCompDt.setModiBy(principal.getName());
				packCompDt.setModiDate(new java.util.Date());
				retVal = "-2";
			}
			
			packCompDt.setComponent(component);
			packCompDt.setPurity(purity);
			packCompDt.setColor(color);
			
			
			save(packCompDt);
			
			
				
		//costingDtItemService.applyItemLabRate(costingDtItem, principal);
		packDtService.updateFob(packDt);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	

}
