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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IReadyBagRetDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagRetMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;

@Service
@Transactional
@Repository
public class ReadyBagRetDtService implements IReadyBagRetDtService {

	@Autowired
	private IReadyBagRetDtRepository readyBagRetDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IReadyBagRetMtService readyBagRetMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	
	@Autowired
	private IReadyBagService readyBagService; 
	
	@Override
	public void save(ReadyBagRetDt readyBagRetDt) {
		// TODO Auto-generated method stub
		readyBagRetDtRepository.save(readyBagRetDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		readyBagRetDtRepository.delete(id);
	}

	@Override
	public ReadyBagRetDt findOne(int id) {
		// TODO Auto-generated method stub
		return readyBagRetDtRepository.findOne(id);
	}

	@Override
	public String readyBagRetDtListing(Integer mtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReadyBagRetDt> findByReadyBagRetMt(ReadyBagRetMt readyBagRetMt) {
		// TODO Auto-generated method stub
		return readyBagRetDtRepository.findByReadyBagRetMt(readyBagRetMt);
	}

	@Override
	public String returnReadyBagTrfListing(Integer mtId,Principal principal) {
		// TODO Auto-generated method stub
	
		ReadyBagRetMt readyBagRetMt = readyBagRetMtService.findOne(mtId);
		
		List<Object[]> objects =new ArrayList<Object[]>();
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_returnReadyBagTransferList(?) }");
			
			query.setParameter(1, readyBagRetMt.getLocation().getId());
			
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
			
	
	}

	@Override
	public String returnReadyBagIss(String pBagIds,Integer readyBagRetMtId,Principal principal) {
	
		String retVal = "-1";
		ReadyBagRetMt readyBagRetMt = readyBagRetMtService.findOne(readyBagRetMtId);
		
		String[] BagDtl = pBagIds.split(",");
		for (int i = 0; i < BagDtl.length; i++) {
			
			BagMt bagMt = bagMtService.findOne(Integer.parseInt(BagDtl[i].toString()));
		
			List<ReadyBag> readyBags = readyBagService.findByBagMtAndCurrentStockAndLocationAndPendApprovalFlg(bagMt,true,readyBagRetMt.getLocation(),false);
			
			for (ReadyBag readyBag : readyBags) {
			
				ReadyBagRetDt readyBagRetDt = new ReadyBagRetDt();
				readyBagRetDt.setBagMt(bagMt);
				readyBagRetDt.setBagPcs(readyBag.getBagPcs());
				readyBagRetDt.setCarat(readyBag.getCarat());
				readyBagRetDt.setStone(readyBag.getStone());
				readyBagRetDt.setRefReadyBagId(readyBag.getId());
				readyBagRetDt.setReadyBagRetMt(readyBagRetMt);
				readyBagRetDt.setCreatedBy(principal.getName());
				readyBagRetDt.setCreatedDt(new Date());
				readyBagRetDt.setPendApprovalFlg(true);
				save(readyBagRetDt);
				
				
				/*
				 * readyBag.setLocation(null);
				 * 
				 * readyBagService.save(readyBag);
				 */
				
				retVal = "1";
			}
		}
		
		return retVal;
	}

	@Override
	public String ReadyBagRetDtListing(Integer mtId,String disableFlg) {
		// TODO Auto-generated method stub
	
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_readyBagRetDtList(?) }");
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
				 .append("<a  href='javascript:deleteReadyBagRetDt(event,")
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
			
			List<ReadyBagRetDt> readyBagRetDts = findByBagMt(bagMt);
			for (ReadyBagRetDt readyBagRetDt : readyBagRetDts) {
				
				ReadyBag readyBag = readyBagService.findOne(readyBagRetDt.getRefReadyBagId());
				if(readyBag.getPendApprovalFlg()) {
					
						readyBag.setLocation(null);
						readyBag.setPendApprovalFlg(false);
						readyBagService.save(readyBag);
					
					delete(readyBagRetDt.getId());
					
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
	public List<ReadyBagRetDt> findByBagMt(BagMt bagMt) {
		// TODO Auto-generated method stub
		return readyBagRetDtRepository.findByBagMt(bagMt);
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
	
	
	
	
	
	@Override
	public String ReadyBagRetDtPendingApprovalListing() {
		// TODO Auto-generated method stub
	
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_readyBagRetDtPendingApprovalList() }");
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
				 .append(list[11] != null ? list[11] : "")
				 .append("\"},");
				
			
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
		
		
	}
	
	
	
	

	@Override
	public List<ReadyBagRetDt> findByPendApprovalFlg(Boolean pendApprovalFlg) {
		// TODO Auto-generated method stub
		return readyBagRetDtRepository.findByPendApprovalFlg(pendApprovalFlg);
	}

	@Override
	public String readyBagRetApprove(String pBagIds, Principal principal) {
		String retVal = "-1";
		
		
		String[] BagDtl = pBagIds.split(",");
		for (int i = 0; i < BagDtl.length; i++) {
			
			BagMt bagMt = bagMtService.findOne(Integer.parseInt(BagDtl[i].toString()));
		
			List<ReadyBag> readyBags = readyBagService.findByBagMtAndCurrentStock(bagMt,true);
			
			for (ReadyBag readyBag : readyBags) {
				
				  readyBag.setLocation(null);
				  
				  readyBagService.save(readyBag);
				 
				
				
			}
			
			List<ReadyBagRetDt> readyBagRetDts =findByBagMt(bagMt);
			for(ReadyBagRetDt readyBagRetDt :readyBagRetDts) {
				
				readyBagRetDt.setPendApprovalFlg(false);
				readyBagRetDt.setModiBy(principal.getName());
				readyBagRetDt.setModiDate(new Date());
				save(readyBagRetDt);
			}
			retVal = "1";
			
		}
		
		return retVal;
	}

}
