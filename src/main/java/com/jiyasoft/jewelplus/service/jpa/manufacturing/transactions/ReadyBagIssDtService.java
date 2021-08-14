package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IReadyBagIssDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;

@Service
@Transactional
@Repository
public class ReadyBagIssDtService implements IReadyBagIssDtService {

	@Autowired
	private IReadyBagIssDtRepository readyBagIssDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IReadyBagIssMtService readyBagIssMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	
	@Autowired
	private IReadyBagService readyBagService; 
	
	@Override
	public void save(ReadyBagIssDt readyBagIssDt) {
		// TODO Auto-generated method stub
		readyBagIssDtRepository.save(readyBagIssDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		readyBagIssDtRepository.delete(id);
	}

	@Override
	public ReadyBagIssDt findOne(int id) {
		// TODO Auto-generated method stub
		return readyBagIssDtRepository.findOne(id);
	}

	@Override
	public String readyBagIssDtListing(Integer mtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReadyBagIssDt> findByReadyBagIssMt(ReadyBagIssMt readyBagIssMt) {
		// TODO Auto-generated method stub
		return readyBagIssDtRepository.findByReadyBagIssMt(readyBagIssMt);
	}

	@Override
	public String readyBagTrfListing(Principal principal) {
		// TODO Auto-generated method stub
	
		try {
			
			List<Object[]> objects =new ArrayList<Object[]>();
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_readyBagIssueTransferList() }");
			objects = query.getResultList();
			
			String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
				
			 for(Object[] list:objects){
		
					
					sb.append("{\"dtId\":\"")
				     .append(list[0] != null ? list[0] : "")
				     .append("\",\"bagId\":\"")
				     .append(list[1] != null ? list[1] : "")
				     .append("\",\"bagQty\":\"")
				     .append(list[2] != null ? list[2] : "")
				     .append("\",\"carat\":\"")
				     .append(list[3] != null ? list[3] : "")
				     .append("\",\"stone\":\"")
					 .append(list[4] != null ? list[4] : "")
				     .append("\",\"bagNm\":\"")
					 .append(list[5] != null ? list[5] : "")
				     .append("\",\"orderNo\":\"")
					 .append(list[6] != null ? list[6] : "")
					 .append("\",\"partyCode\":\"")
					 .append(list[7] != null ? list[7] : "")
					 .append("\",\"styleNo\":\"")
					 .append(list[8] != null ? list[8] : "")
					 .append("\",\"purity\":\"")
					 .append(list[9] != null ? list[9] : "")
					 .append("\",\"color\":\"")
					 .append(list[10] != null ? list[10] : "")
					 .append("\"},");
				}
			   
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				
			return str;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

	@Override
	public String transferReadyBagIss(String pBagIds, Integer readtBagIssMtId, Principal principal) {
	
		String retVal = "-1";
		ReadyBagIssMt readyBagIssMt = readyBagIssMtService.findOne(readtBagIssMtId);
		
		String[] BagDtl = pBagIds.split(",");
		for (int i = 0; i < BagDtl.length; i++) {
			
			BagMt bagMt = bagMtService.findOne(Integer.parseInt(BagDtl[i].toString()));
		
			List<ReadyBag> readyBags = readyBagService.findByBagMtAndLocationIsNull(bagMt);
			
			for (ReadyBag readyBag : readyBags) {
			
				ReadyBagIssDt readyBagIssDt = new ReadyBagIssDt();
				readyBagIssDt.setBagMt(bagMt);
				readyBagIssDt.setBagPcs(readyBag.getBagPcs());
				readyBagIssDt.setCarat(readyBag.getCarat());
				readyBagIssDt.setStone(readyBag.getStone());
				readyBagIssDt.setRefReadyBagId(readyBag.getId());
				readyBagIssDt.setReadyBagIssMt(readyBagIssMt);
				readyBagIssDt.setCreatedBy(principal.getName());
				readyBagIssDt.setCreatedDt(new Date());
				save(readyBagIssDt);
				
				
				readyBag.setLocation(readyBagIssMt.getLocation());
				readyBag.setPendApprovalFlg(true);
				
				readyBagService.save(readyBag);
				
				retVal = "1";
			}
		}
		
		return retVal;
	}

	@Override
	public String ReadyBagIssDtListing(Integer mtId,String disableFlg) {
		// TODO Auto-generated method stub
	
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_readyBagIssDtList(?) }");
		query.setParameter(1,mtId);
		objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		 for(Object[] list:objects){
	
				
				sb.append("{\"mtId\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"bagId\":\"")
			     .append(list[1] != null ? list[1] : "")
			     .append("\",\"bagQty\":\"")
			     .append(list[2] != null ? list[2] : "")
			     .append("\",\"carat\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"stone\":\"")
				 .append(list[4] != null ? list[4] : "")
			     .append("\",\"bagNm\":\"")
				 .append(list[5] != null ? list[5] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"partyCode\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"purity\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"color\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"approvalFlg\":\"")
				 .append(list[11] != null ? list[11] : "");
				if(disableFlg.equalsIgnoreCase("false")) {
					
				 
				sb.append("\",\"action2\":\"")
				 .append("<a  href='javascript:deleteReadyBagIssDt(event,")
				 .append(list[1]+","+list[11])
				 .append(");' class='btn btn-xs btn-danger triggerRemove")
				 .append(list[1])
				 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				 .append("\"},");
				}else {
					 sb.append("\",\"actionLock\":\"")
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
	public String deleteDt(Integer bagId) {
		// TODO Auto-generated method stub
		String retVal = "-1";
		
		try {
			
			BagMt bagMt = bagMtService.findOne(bagId);
			
			List<ReadyBagIssDt> readyBagIssDts = findByBagMt(bagMt);
			for (ReadyBagIssDt readyBagIssDt : readyBagIssDts) {
				
				ReadyBag readyBag = readyBagService.findOne(readyBagIssDt.getRefReadyBagId());
				if(readyBag.getPendApprovalFlg()) {
					
						readyBag.setLocation(null);
						readyBag.setPendApprovalFlg(false);
						readyBagService.save(readyBag);
					
					delete(readyBagIssDt.getId());
					
					retVal = "1";
				}else {
					
					return "Cannot Delete";
				
				}
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		return retVal;
	}

	@Override
	public List<ReadyBagIssDt> findByBagMt(BagMt bagMt) {
		// TODO Auto-generated method stub
		return readyBagIssDtRepository.findByBagMt(bagMt);
	}
	
	@Override
	public String getReadyBagPendingApproval(Integer locationId, String bagIds) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		
		try {
			
			String[] BagDtl = bagIds.split(",");
			for (int i = 0; i < BagDtl.length; i++) {
				
				BagMt bagMt = bagMtService.findOne(Integer.parseInt(BagDtl[i].toString()));
				List<ReadyBag> readyBags = readyBagService.findByBagMt(bagMt);
				
				for (ReadyBag readyBag : readyBags) {
					readyBag.setPendApprovalFlg(false);
					
					readyBagService.save(readyBag);
				}
						
			}
			retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return retVal;
	}

}
