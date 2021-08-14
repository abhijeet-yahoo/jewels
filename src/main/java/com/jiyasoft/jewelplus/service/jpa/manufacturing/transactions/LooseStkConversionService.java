package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QLooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ILooseStkConversionRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILooseStkConversionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseMtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class LooseStkConversionService implements ILooseStkConversionService{
	
	@Autowired
	private ILooseStkConversionRepository looseStkConversionRepository;
	
	@Autowired
	private IStnLooseMtService stnLooseMtService;
	
	@Autowired
	private IStnLooseDtService stnLooseDtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<LooseStkConversion> findByStnLooseDt(StnLooseDt stnLooseDt) {
		// TODO Auto-generated method stub
		return looseStkConversionRepository.findByStnLooseDt(stnLooseDt);
	}

	@Override
	public void save(LooseStkConversion looseStkConversion) {
		// TODO Auto-generated method stub
		looseStkConversionRepository.save(looseStkConversion);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		looseStkConversionRepository.delete(id);;
	}

	@Override
	public LooseStkConversion findOne(int id) {
		// TODO Auto-generated method stub
		return looseStkConversionRepository.findOne(id);
	}

	@Override
	public String saveStnLooseConversionDt(LooseStkConversion looseStkConversion, Integer id, 
			Double vCarat, Integer vStone, String sizeGroupStr, Principal principal, Integer vMtId, Integer vDtId) {
		// TODO Auto-generated method stub
		String retVal ="-1";
		String action = "added";
		
		try {
			
			StnLooseMt stnLooseMt = stnLooseMtService.findOne(vMtId);
			StnLooseDt stnLooseDt = stnLooseDtService.findOne(vDtId);
			
			
		
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
				
				looseStkConversion.setLotNo("LSCONV-"+bagSr+"-"+vYear);
				looseStkConversion.setLotSrNo(maxSrno);
				looseStkConversion.setCreatedBy(principal.getName());
				looseStkConversion.setCreatedDt(new java.util.Date());
				
				looseStkConversion.setUniqueId(new Date().getTime());
				looseStkConversion.setDepartment(departmentService.findByName("Diamond"));
				looseStkConversion.setBalCarat(vCarat);
				looseStkConversion.setBalStone(vStone);
				looseStkConversion.setCarat(vCarat);
				looseStkConversion.setStone(vStone);
				looseStkConversion.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(looseStkConversion.getShape(), sizeGroupStr,false));
				looseStkConversion.setAdjFlg(true);
				looseStkConversion.setStnLooseMt(stnLooseMt);
				looseStkConversion.setStnLooseDt(stnLooseDt);
				
				
				retVal = "1";
				
			} 
			
			looseStkConversion.setBalAmount(looseStkConversion.getAmount());
			
		
			
			save(looseStkConversion);
			
			stnLooseDt.setBalCarat(Math.round((stnLooseDt.getBalCarat() -looseStkConversion.getCarat())*1000.0)/1000.0);
			stnLooseDt.setBalStone(Math.round((stnLooseDt.getBalStone() -looseStkConversion.getStone())));
			stnLooseDt.setBalAmount(Math.round((stnLooseDt.getBalAmount() -looseStkConversion.getAmount())*100.0)/100.0);
			stnLooseDtService.save(stnLooseDt);
			
			retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		retVal=retVal+","+action;
		
		return retVal;
	}

	@Override
	public Integer getMaxLotSrno() {
		// TODO Auto-generated method stub
		QLooseStkConversion qLooseStkConversion = QLooseStkConversion.looseStkConversion;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qLooseStkConversion)
			.where(qLooseStkConversion.createdDt.year().eq(date.get(Calendar.YEAR))).list(qLooseStkConversion.lotSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String stnLooseConversionDtListing(Integer dtId,Principal principal) {
		// TODO Auto-generated method stub
		StnLooseDt stnLooseDt = stnLooseDtService.findOne(dtId);
		List<LooseStkConversion> looseStkConversions = findByStnLooseDt(stnLooseDt);
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(looseStkConversions.size()).append(",\"rows\": [");
		 
		 for (LooseStkConversion looseStkConversion : looseStkConversions) {
				
				sb.append("{\"id\":\"")
				.append(looseStkConversion.getId())
				.append("\",\"stoneType\":\"")
				.append((looseStkConversion.getStoneType() != null ? looseStkConversion.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((looseStkConversion.getShape() != null ? looseStkConversion.getShape().getName() : ""))
				.append("\",\"subShape\":\"")
				.append((looseStkConversion.getSubShape() != null ? looseStkConversion.getSubShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((looseStkConversion.getQuality() != null ? looseStkConversion.getQuality().getName() : ""))
				.append("\",\"stoneChart\":\"")
				.append((looseStkConversion.getSize() != null ? looseStkConversion.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append(looseStkConversion.getSieve())
				.append("\",\"size\":\"")
				.append(looseStkConversion.getSize())
				.append("\",\"sizeGroupStr\":\"")
				.append((looseStkConversion.getSizeGroup() != null ? looseStkConversion.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append(looseStkConversion.getStone())
				.append("\",\"balStone\":\"")
				.append(looseStkConversion.getBalStone())
				.append("\",\"carat\":\"")
				.append(looseStkConversion.getCarat())
				.append("\",\"diffCarat\":\"")
				.append(looseStkConversion.getDiffCarat())
				.append("\",\"balCarat\":\"")
				.append(looseStkConversion.getBalCarat())
				.append("\",\"rate\":\"")
				.append(looseStkConversion.getRate())
				.append("\",\"amount\":\"")
				.append(looseStkConversion.getAmount())
				.append("\",\"lotNo\":\"")
				.append(looseStkConversion.getLotNo() != null ? looseStkConversion.getLotNo() : "")
				.append("\",\"remark\":\"")
				.append((looseStkConversion.getRemark() != null ? looseStkConversion.getRemark() : ""));
		
					sb.append("\",\"action1\":\"");
							
								sb.append("<a href='javascript:addeditStoneLooseDt(").append(looseStkConversion.getId());
							
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
							sb.append("\",\"action2\":\"");
							
								sb.append("<a  href='javascript:deleteStoneLooseConversionDt(event,")
									.append(looseStkConversion.getId()).append(", 0);' href='javascript:void(0);'");
							
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(looseStkConversion.getId())
							 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
				
						sb.append("\"},");
			
			
				
				
			}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@Override
	public String stnLooseConversionDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
		String retVal ="-1";
		try {
			
			LooseStkConversion looseStkConversion = looseStkConversionRepository.findOne(id);
			if(looseStkConversion.getAdjFlg()) {
				
				StnLooseDt stnLooseDt = stnLooseDtService.findOne(looseStkConversion.getStnLooseDt().getId());
				stnLooseDt.setBalCarat(Math.round((stnLooseDt.getBalCarat() + looseStkConversion.getCarat())*1000.0)/1000.0);
				stnLooseDt.setBalStone(Math.round((stnLooseDt.getBalStone() + looseStkConversion.getStone())));
				stnLooseDt.setBalAmount(Math.round((stnLooseDt.getBalAmount() + looseStkConversion.getAmount())*1000.0)/1000.0);
				stnLooseDtService.save(stnLooseDt);
				
				
				delete(id);
				retVal ="1";
			}else {
				retVal ="-2";
			}
		
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public List<LooseStkConversion> findByStnLooseMt(StnLooseMt stnLooseMt) {
		// TODO Auto-generated method stub
		return looseStkConversionRepository.findByStnLooseMt(stnLooseMt);
	}

}
