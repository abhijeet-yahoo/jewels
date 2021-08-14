package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStnLooseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStnLooseMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILooseStkConversionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetDtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StnLooseMtService implements IStnLooseMtService{
	
	@Autowired
	private IStnLooseMtRepository stnLooseMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStnLooseDtService stnLooseDtService;
	
	@Autowired
	private ILooseStkConversionService looseStkConversionService;
	
	@Autowired
	private IStnLooseRetDtService stnLooseRetDtService;
	

	@Override
	public Page<StnLooseMt> searchAll(Integer limit, Integer offset, String sort, String order, String search) {
		// TODO Auto-generated method stub
		
		QStnLooseMt qStnLooseMt = QStnLooseMt.stnLooseMt;
		BooleanExpression expression = null;

		
			if (search != null) {
				expression =qStnLooseMt.invNo.like("%" + search + "%");
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
		}
	
		Page<StnLooseMt> stnLooseMtList =(Page<StnLooseMt>) stnLooseMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return stnLooseMtList;
	}

	@Override
	public void save(StnLooseMt stnLooseMt) {
		// TODO Auto-generated method stub
		stnLooseMtRepository.save(stnLooseMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stnLooseMtRepository.delete(id);
		}

	@Override
	public StnLooseMt findOne(int id) {
		// TODO Auto-generated method stub
		return stnLooseMtRepository.findOne(id);
	}

	@Override
	public String deleteMt(Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		StnLooseMt stnLooseMt =findOne(mtId);
		
		List<StnLooseDt> stnLooseDtsNew = stnLooseDtService.findByStnLooseMt(stnLooseMt);
		for (StnLooseDt stnLooseDt : stnLooseDtsNew) {
			List<StnLooseRetDt> stnLooseRetDt = stnLooseRetDtService.findByRefTranId(stnLooseDt.getId());
			if(stnLooseRetDt.size() > 0) {
				
				retVal ="-3";
				break;
				}
		}
		
		if(retVal.equalsIgnoreCase("-3")){
			return "-3";
		}
		
		List<LooseStkConversion> looseStkConversions = looseStkConversionService.findByStnLooseMt(stnLooseMt);
		
		if(looseStkConversions.size() > 0) {
			retVal = "-2";
		}else {
			
			for (LooseStkConversion looseStkConversion : looseStkConversions) {
				looseStkConversionService.stnLooseConversionDtDelete(looseStkConversion.getId(), principal);
			
			}
			
			List<StnLooseDt> stnLooseDts = stnLooseDtService.findByStnLooseMt(stnLooseMt);
			for (StnLooseDt stnLooseDt : stnLooseDts) {
				stnLooseDtService.delete(stnLooseDt.getId());
			}
			
			delete(mtId);
			
			retVal = "1";
		}
		
			
		
				
		return retVal;
		
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QStnLooseMt qStnLooseMt = QStnLooseMt.stnLooseMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStnLooseMt)
			.where(qStnLooseMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qStnLooseMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String saveStnLooseMt(StnLooseMt stnLooseMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, String vTranDate) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/stnLooseMt/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "stnLooseMt/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				stnLooseMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				stnLooseMt.setSrNo(++maxSrno);
				int si = maxSrno.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				Integer presentYear = Integer.parseInt(vYear);
				Integer nextYear = presentYear + 1;
				
				String bagSr = null;
				
				switch (si) {
				case 1:
					bagSr = "000"+maxSrno;
					break;
					
				case 2:
					bagSr = "00"+maxSrno;
					break;
					
				case 3:
					bagSr = "0"+maxSrno;
					break;
					
				default:
					bagSr = maxSrno.toString();
					break;
				}
				
				
				stnLooseMt.setInvNo("LSTIN/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				stnLooseMt.setCreatedBy(principal.getName());
				stnLooseMt.setCreatedDt(new java.util.Date());
				

			} else {
				stnLooseMt.setModiBy(principal.getName());
				stnLooseMt.setModiDt(new java.util.Date());
				
				
				
				action = "updated";
				 retVal = "redirect:/manufacturing/transactions/stnLooseMt/edit/"+id+".html";
			}
			
		
			if(stnLooseMt.getParty().getId() == null) {
				stnLooseMt.setParty(null);
			}
			
			if(stnLooseMt.getSupplier().getId() == null) {
				stnLooseMt.setSupplier(null);
			}
			
			stnLooseMt.getRemark().trim();
			stnLooseMt.setRemark(stnLooseMt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			
			save(stnLooseMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/manufacturing/transactions/stnLooseMt/edit/"+stnLooseMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
	}

}

	@Override
	public StnLooseMt findByInvNo(String invNo) {
		// TODO Auto-generated method stub
		return stnLooseMtRepository.findByInvNo(invNo);
	}

	@Override
	public String stnLoosePickupList(Principal principal) throws ParseException {
		// TODO Auto-generated method stub
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
	
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call JSP_looseStonePickUpDt() }");
			objects = query.getResultList();
		
			
			String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
				 String	invDate="";
				 
				 if(list[3] != null ){
					 Date ordDate = dfOutput.parse((list[3] != null ? list[3] : "").toString());
					 invDate = dfInput.format(ordDate);
				 }
				 
				
			 	
				sb.append("{\"mtId\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"dtId\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"invNo\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"invDate\":\"")
				 .append(invDate)
				 .append("\",\"client\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"supplier\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"stoneType\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"shape\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"subShape\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"quality\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"size\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"sieve\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"sizeGroup\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"stone\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\",\"carat\":\"")
				 .append(list[14] != null ? list[14] : "")
				 .append("\",\"balstone\":\"")
				 .append(list[15] != null ? list[15] : "")
				 .append("\",\"balcarat\":\"")
				 .append(list[16] != null ? list[16] : "")
				 .append("\",\"rate\":\"")
				 .append(list[17] != null ? list[17] : "")
				 .append("\",\"amount\":\"")
				 .append(list[18] != null ? list[18] : "")
				 .append("\",\"balAmount\":\"")
				 .append(list[19] != null ? list[19] : "")
				 .append("\",\"avgRate\":\"")
				 .append(list[20] != null ? list[20] : "")
				 .append("\",\"action1\":\"")
				 .append("<a onClick='javascript:popStnLoosConversion()'")
				.append(" class='btn btn-xs btn-info' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Convert</a>")
				.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public String looseConversionPickupList(Integer partyId,Principal principal) throws ParseException {
		// TODO Auto-generated method stub

		List<Object[]> objects =new ArrayList<Object[]>();
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
	
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_stnLooseConversionPickup(?) }");
			query.setParameter(1, partyId);
			objects = query.getResultList();
		
			
			String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
				 String	invDate="";
				 
				 if(list[3] != null ){
					 Date ordDate = dfOutput.parse((list[3] != null ? list[3] : "").toString());
					 invDate = dfInput.format(ordDate);
				 }
				 
				
			 	
				sb.append("{\"mtId\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"dtId\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"invNo\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"invDate\":\"")
				 .append(invDate)
				 .append("\",\"client\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"supplier\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"stoneType\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"shape\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"quality\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"size\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"sieve\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"sizeGroup\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"stone\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"carat\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\",\"rate\":\"")
				 .append(list[14] != null ? list[14] : "")
				 .append("\",\"amount\":\"")
				 .append(list[15] != null ? list[15] : "")
				 .append("\",\"conversionDtId\":\"")
				 .append(list[16] != null ? list[16] : "")
				 
				 
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public String stnLoosePickupPartyWiseList(Integer partyId, Principal principal) throws ParseException {
		// TODO Auto-generated method stub
List<Object[]> objects =new ArrayList<Object[]>();
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
	
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call JSP_stnLoosePartyWisePickUpDt(?) }");
			query.setParameter(1, partyId);
			objects = query.getResultList();
		
			
			String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
				 String	invDate="";
				 
				 if(list[3] != null ){
					 Date ordDate = dfOutput.parse((list[3] != null ? list[3] : "").toString());
					 invDate = dfInput.format(ordDate);
				 }
				 
				
			 	
				sb.append("{\"mtId\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"dtId\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"id\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"invNo\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"invDate\":\"")
				 .append(invDate)
				 .append("\",\"client\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"supplier\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"stoneType\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"shape\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"subShape\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"quality\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"size\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"sieve\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"sizeGroup\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"stone\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\",\"carat\":\"")
				 .append(list[14] != null ? list[14] : "")
				 .append("\",\"balstone\":\"")
				 .append(list[15] != null ? list[15] : "")
				 .append("\",\"balcarat\":\"")
				 .append(list[16] != null ? list[16] : "")
				 .append("\",\"rate\":\"")
				 .append(list[17] != null ? list[17] : "")
				 .append("\",\"amount\":\"")
				 .append(list[18] != null ? list[18] : "")
				 .append("\",\"balAmount\":\"")
				 .append(list[19] != null ? list[19] : "")
				 .append("\",\"avgRate\":\"")
				 .append(list[20] != null ? list[20] : "")
				 .append("\",\"trfStone\":\"")
				 .append("0")
				 .append("\",\"trfCarat\":\"")
				 .append("0.0")
				 .append("\",\"action1\":\"")
				 .append("<a onClick='javascript:popStnLoosConversion()'")
				.append(" class='btn btn-xs btn-info' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Convert</a>")
				.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}
	
}
