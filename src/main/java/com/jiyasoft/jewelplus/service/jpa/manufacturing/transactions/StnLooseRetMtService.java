package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStnLooseRetMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStnLooseRetMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StnLooseRetMtService implements IStnLooseRetMtService{

	@Autowired
	private IStnLooseRetMtRepository stnLooseRetMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStnLooseRetDtService stnLooseRetDtService;
	
	@Autowired
	private IStnLooseDtService stnLooseDtService;
	
	@Override
	public Page<StnLooseRetMt> searchAll(Integer limit, Integer offset, String sort, String order, String search) {
		// TODO Auto-generated method stub
		QStnLooseRetMt qStnLooseRetMt = QStnLooseRetMt.stnLooseRetMt;
		BooleanExpression expression = null;
		
		if (search != null) {
			expression =qStnLooseRetMt.invNo.like("%" + search + "%");
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
	
		Page<StnLooseRetMt> stnLooseRetMtList =(Page<StnLooseRetMt>) stnLooseRetMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		
	
		return stnLooseRetMtList;
	}

	@Override
	public void save(StnLooseRetMt stnLooseRetMt) {
		// TODO Auto-generated method stub
		stnLooseRetMtRepository.save(stnLooseRetMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stnLooseRetMtRepository.delete(id);
		
	}

	@Override
	public StnLooseRetMt findOne(int id) {
		// TODO Auto-generated method stub
		return stnLooseRetMtRepository.findOne(id);
	}

	@Override
	public String deleteMt(Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		
		try {
			
			StnLooseRetMt stnLooseRetMt = findOne(mtId);
			
			if(principal.getName().equalsIgnoreCase("Administrator")){
					retVal = "1";
					
			}else{
			
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date cDate = stnLooseRetMt.getCreatedDt();
				String dbDate = dateFormat.format(cDate);

				Date date = new Date();
				String curDate = dateFormat.format(date);

				if (dbDate.equals(curDate)) {

					retVal = "1";

				}

			}
			
			if(retVal != "-1"){
				
				List<StnLooseRetDt> stnLooseRetDts = stnLooseRetDtService.findByStnLooseRetMt(stnLooseRetMt);
				for (StnLooseRetDt stnLooseRetDt : stnLooseRetDts) {
					StnLooseDt stnLooseDt = stnLooseDtService.findOne(stnLooseRetDt.getRefTranId());
					stnLooseDt.setBalCarat(Math.round((stnLooseDt.getBalCarat() + stnLooseRetDt.getCarat())*1000.0)/1000.0);
					stnLooseDt.setBalStone(Math.round((stnLooseDt.getBalStone() + stnLooseRetDt.getStone())));
					stnLooseDt.setBalAmount(Math.round((stnLooseDt.getBalAmount() + stnLooseRetDt.getAmount())*100.0)/100.0);
					stnLooseDt.setModiBy(principal.getName());
					stnLooseDt.setModiDt(new Date());
					stnLooseDtService.save(stnLooseDt);
					
					stnLooseRetDtService.delete(stnLooseRetDt.getId());
				}
				
				delete(mtId);	
				
				retVal = "1";
				
				
			}else {
				retVal = "-1";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QStnLooseRetMt qStnLooseRetMt = QStnLooseRetMt.stnLooseRetMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStnLooseRetMt)
			.where(qStnLooseRetMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qStnLooseRetMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String saveStnLooseRetMt(StnLooseRetMt stnLooseRetMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, String vTranDate) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/stnLooseRetMt/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "stnLooseRetMt/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				stnLooseRetMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				stnLooseRetMt.setSrNo(++maxSrno);
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
				
				
				stnLooseRetMt.setInvNo("LSTOUT/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				stnLooseRetMt.setCreatedBy(principal.getName());
				stnLooseRetMt.setCreatedDt(new java.util.Date());
				

			} else {
				stnLooseRetMt.setModiBy(principal.getName());
				stnLooseRetMt.setModiDt(new java.util.Date());
				
				
				
				action = "updated";
				 retVal = "redirect:/manufacturing/transactions/stnLooseRetMt/edit/"+id+".html";
			}
			
		
			if(stnLooseRetMt.getParty().getId() == null) {
				stnLooseRetMt.setParty(null);
			}
			
			if(stnLooseRetMt.getSupplier().getId() == null) {
				stnLooseRetMt.setSupplier(null);
			}
			
			stnLooseRetMt.getRemark().trim();
			stnLooseRetMt.setRemark(stnLooseRetMt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			
			save(stnLooseRetMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/manufacturing/transactions/stnLooseRetMt/edit/"+stnLooseRetMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
		}
	}

	@Override
	public StnLooseRetMt findByInvNo(String invNo) {
		// TODO Auto-generated method stub
		return stnLooseRetMtRepository.findByInvNo(invNo);
	}

}
