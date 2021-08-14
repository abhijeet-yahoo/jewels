package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStnLooseRetDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetMtService;

@Service
@Repository
@Transactional
public class StnLooseRetDtService implements IStnLooseRetDtService {
	
	@Autowired
	private IStnLooseRetDtRepository stnLooseRetDtRepository;
	
	@Autowired
	private IStnLooseDtService stnLooseDtService;
	
	@Autowired
	private IStnLooseRetMtService stnLooseRetMtService;


	@Override
	public List<StnLooseRetDt> findByStnLooseRetMt(StnLooseRetMt stnLooseRetMt) {
		// TODO Auto-generated method stub
		return stnLooseRetDtRepository.findByStnLooseRetMt(stnLooseRetMt);
	}

	@Override
	public void save(StnLooseRetDt stnLooseRetDt) {
		// TODO Auto-generated method stub
		stnLooseRetDtRepository.save(stnLooseRetDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stnLooseRetDtRepository.delete(id);
	}

	@Override
	public StnLooseRetDt findOne(int id) {
		// TODO Auto-generated method stub
		return stnLooseRetDtRepository.findOne(id);
	}

	

	@Override
	public Integer getMaxLotSrno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String stnLooseRetDtListing(Integer mtId, Boolean canViewFlag, Principal principal) {
		// TODO Auto-generated method stub
		StnLooseRetMt stnLooseRetMt = stnLooseRetMtService.findOne(mtId);
		List<StnLooseRetDt> stnLooseRetDts = findByStnLooseRetMt(stnLooseRetMt);
		
				
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stnLooseRetDts.size()).append(",\"rows\": [");
		 
		 for (StnLooseRetDt stnLooseRetDt : stnLooseRetDts) {
				
				sb.append("{\"id\":\"")
				.append(stnLooseRetDt.getId())
				.append("\",\"stoneType\":\"")
				.append((stnLooseRetDt.getStoneType() != null ? stnLooseRetDt.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((stnLooseRetDt.getShape() != null ? stnLooseRetDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((stnLooseRetDt.getQuality() != null ? stnLooseRetDt.getQuality().getName() : ""))
				.append("\",\"stoneChart\":\"")
				.append((stnLooseRetDt.getSize() != null ? stnLooseRetDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append(stnLooseRetDt.getSieve())
				.append("\",\"sizeGroupStr\":\"")
				.append((stnLooseRetDt.getSizeGroup() != null ? stnLooseRetDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append(stnLooseRetDt.getStone())
				.append("\",\"balStone\":\"")
				.append(stnLooseRetDt.getBalStone())
				.append("\",\"carat\":\"")
				.append(stnLooseRetDt.getCarat())
				.append("\",\"diffCarat\":\"")
				.append(stnLooseRetDt.getDiffCarat())
				.append("\",\"balCarat\":\"")
				.append(stnLooseRetDt.getBalCarat())
				.append("\",\"rate\":\"")
				.append(stnLooseRetDt.getRate())
				.append("\",\"amount\":\"")
				.append(stnLooseRetDt.getAmount())
				.append("\",\"lotNo\":\"")
				.append(stnLooseRetDt.getLotNo() != null ? stnLooseRetDt.getLotNo() : "")
				.append("\",\"remark\":\"")
				.append((stnLooseRetDt.getRemark() != null ? stnLooseRetDt.getRemark() : ""));
		
				sb.append("\",\"action1\":\"");
				sb.append("<a href='javascript:addeditStoneLooseDt(").append(stnLooseRetDt.getId());
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
				sb.append("\",\"action2\":\"");
				sb.append("<a  href='javascript:deleteStoneLooseRetDt(event,")
				.append(stnLooseRetDt.getId()).append(", 0);' href='javascript:void(0);'");
				
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(stnLooseRetDt.getId())
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
	public String stnLooseRetDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		
		try {
			
		
			StnLooseRetDt stnLooseRetDt = findOne(id);
			
			if(principal.getName().equalsIgnoreCase("Administrator")){
					retVal = "1";
					
			}else{
			
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				StnLooseRetMt stnLooseRetMt = stnLooseRetMtService.findOne(stnLooseRetDt.getStnLooseRetMt().getId());
				Date cDate = stnLooseRetMt.getCreatedDt();
				String dbDate = dateFormat.format(cDate);

				Date date = new Date();
				String curDate = dateFormat.format(date);

				if (dbDate.equals(curDate)) {

					retVal = "1";

				}

			}
			
			if(retVal != "-1"){
				
				StnLooseDt stnLooseDt = stnLooseDtService.findOne(stnLooseRetDt.getRefTranId());
				stnLooseDt.setBalCarat(Math.round((stnLooseDt.getBalCarat() + stnLooseRetDt.getCarat())*1000.0)/1000.0);
				stnLooseDt.setBalStone(Math.round((stnLooseDt.getBalStone() + stnLooseRetDt.getStone())));
				stnLooseDt.setBalAmount(Math.round((stnLooseDt.getBalAmount() + stnLooseRetDt.getAmount())*100.0)/100.0);
				stnLooseDt.setModiBy(principal.getName());
				stnLooseDt.setModiDt(new Date());
				stnLooseDtService.save(stnLooseDt);
				
				delete(id);
			}else {
				retVal = "-1";
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}


	@Override
	public String stnLooseRetDtSave(Integer mtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal= "-1";
		
		try {
			
			JSONArray jsonLooseDt = new JSONArray(data);
			
			StnLooseRetMt stnLooseRetMt = stnLooseRetMtService.findOne(mtId);
			
			for (int y = 0; y < jsonLooseDt.length(); y++) {
				JSONObject dataLooseDt = (JSONObject) jsonLooseDt.get(y);
				
				StnLooseDt stnLooseDt = stnLooseDtService.findOne(Integer.parseInt(dataLooseDt.get("dtId").toString()));
				
				Double balAmount = Math.round((Double.parseDouble(dataLooseDt.get("trfCarat").toString()) * Double.parseDouble(dataLooseDt.get("avgRate").toString()))*100.0)/100.0;
				
				
				StnLooseRetDt stnLooseRetDt = new StnLooseRetDt();
				stnLooseRetDt.setRefTranId(stnLooseDt.getId());
				stnLooseRetDt.setRefTranType("stnLooseDt");
				stnLooseRetDt.setStnLooseRetMt(stnLooseRetMt);
				
		//		stnLooseRetDt.setBalAmount(stnLooseDt.getBalAmount());
				stnLooseRetDt.setAmount(balAmount);
				stnLooseRetDt.setCarat(Double.parseDouble(dataLooseDt.get("trfCarat").toString()));
		//		stnLooseRetDt.setBalCarat(stnLooseDt.getBalCarat());
		//		stnLooseRetDt.setBalStone(stnLooseDt.getBalStone());
				stnLooseRetDt.setStone(Integer.parseInt(dataLooseDt.get("trfStone").toString()));
				stnLooseRetDt.setStoneType(stnLooseDt.getStoneType());
				stnLooseRetDt.setShape(stnLooseDt.getShape());
				stnLooseRetDt.setQuality(stnLooseDt.getQuality());
				stnLooseRetDt.setSize(stnLooseDt.getSize());
				stnLooseRetDt.setSizeGroup(stnLooseDt.getSizeGroup());
				stnLooseRetDt.setSieve(stnLooseDt.getSieve());
				stnLooseRetDt.setCreatedBy(principal.getName());
				stnLooseRetDt.setCreatedDt(new Date());
				stnLooseRetDt.setRemark(stnLooseDt.getRemark());
				stnLooseRetDt.setPacketNo(stnLooseDt.getPacketNo());
				stnLooseRetDt.setRate(Double.parseDouble(dataLooseDt.get("avgRate").toString()));
				save(stnLooseRetDt);
				
				
				
				stnLooseDt.setBalCarat(Math.round((stnLooseDt.getBalCarat() - stnLooseRetDt.getCarat())*1000.0)/1000.0);
				stnLooseDt.setBalStone(Math.round((stnLooseDt.getBalStone() - stnLooseRetDt.getStone())));
				stnLooseDt.setBalAmount(Math.round((stnLooseDt.getBalAmount() -stnLooseRetDt.getAmount())*100.0)/100.0);
				stnLooseDt.setModiBy(principal.getName());
				stnLooseDt.setModiDt(new Date());
				stnLooseDtService.save(stnLooseDt);
				
				
				
			}
			
			retVal ="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public List<StnLooseRetDt> findByRefTranId(Integer refTranId) {
		// TODO Auto-generated method stub
		return stnLooseRetDtRepository.findByRefTranId(refTranId);
	}

}
