package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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



import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneOutwardMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StoneOutwardMtService implements IStoneOutwardMtService {
	
	@Autowired
	IStoneOutwardMtRepository stoneOutwardMtRepository;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStoneOutwardDtService stoneOutwardDtService;
	

	@Override
	public List<StoneOutwardMt> findAll() {
		// TODO Auto-generated method stub
		return stoneOutwardMtRepository.findAll();
	}

	@Override
	public Page<StoneOutwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		// TODO Auto-generated method stub
		return stoneOutwardMtRepository
				.findAll(new PageRequest(page,limit,(order
						.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC ), sort));
	}

	@Override
	public void save(StoneOutwardMt stoneOutwardMt) {
		// TODO Auto-generated method stub
		stoneOutwardMtRepository.save(stoneOutwardMt);
		
		
	}

	@Override
	public void delete(int id) {
		
		StoneOutwardMt stoneOutwardMt = stoneOutwardMtRepository.findOne(id);
		stoneOutwardMt.setDeactive(true);
		stoneOutwardMt.setDeactiveDt(new java.util.Date());
		stoneOutwardMtRepository.save(stoneOutwardMt);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return stoneOutwardMtRepository.count();
	}

	@Override
	public StoneOutwardMt findOne(int id) {
		// TODO Auto-generated method stub
		return stoneOutwardMtRepository.findOne(id);
	}

	

	@Override
	public Map<Integer, String> getStoneOutwardMtList() {
		
		Map<Integer, String> stoneOutwardMtMap = new HashMap<Integer, String>();
		List<StoneOutwardMt> stoneOutwardMtList = findActiveStoneOutwardMts();
		
		for(StoneOutwardMt stoneOutwardMt : stoneOutwardMtList){
			stoneOutwardMtMap.put(stoneOutwardMt.getId(), stoneOutwardMt.getInvNo());
			
		}
		// TODO Auto-generated method stub
		return stoneOutwardMtMap;
	}

	@Override
	public List<StoneOutwardMt> findActiveStoneOutwardMts() {
		// TODO Auto-generated method stub
		QStoneOutwardMt qStoneOutwardMt = QStoneOutwardMt.stoneOutwardMt;
		BooleanExpression expression = qStoneOutwardMt.deactive.eq(false);
		List<StoneOutwardMt> stoneOutwardMtList = (List<StoneOutwardMt>) stoneOutwardMtRepository
				.findAll(expression);
		
		return stoneOutwardMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QStoneOutwardMt qStoneOutwardMt= QStoneOutwardMt.stoneOutwardMt;
		BooleanExpression expression = qStoneOutwardMt.deactive.eq(false);
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qStoneOutwardMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invno") && colValue != null) {
				expression = qStoneOutwardMt.deactive.eq(false).and(
						qStoneOutwardMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invno"))
					&& colValue != null) {
				expression = qStoneOutwardMt.invNo.like(colValue + "%");
			}
		}
		
		
			
	

		
		// TODO Auto-generated method stub
		return stoneOutwardMtRepository.count(expression);
	}

	@Override
	public Page<StoneOutwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QStoneOutwardMt qStoneOutwardMt = QStoneOutwardMt.stoneOutwardMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qStoneOutwardMt.deactive.eq(false);
			} else {
				expression = qStoneOutwardMt.deactive.eq(false).and(
						qStoneOutwardMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qStoneOutwardMt.invNo.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<StoneOutwardMt> stoneOutwardMtList = (Page<StoneOutwardMt>) stoneOutwardMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		// TODO Auto-generated method stub
		return stoneOutwardMtList;
	}

	@Override
	public String deleteMt(Integer mtId, Principal principal) {
		String retVal="-1";
		StoneOutwardMt stoneOutwardMt =findOne(mtId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		Date cDate = stoneOutwardMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);
			
		
		if(dbDate.equals(curDate) || principal.getName().equalsIgnoreCase("Admin") ){
			
			List<StoneOutwardDt>stoneOutwardDts =stoneOutwardDtService.findByStoneOutwardMt(stoneOutwardMt);
			
			for(StoneOutwardDt stoneOutwardDt :stoneOutwardDts){
				
				stoneOutwardDtService.delete(stoneOutwardDt.getId());
				
				
				
				List<StoneTran>stoneTrans =stoneTranService.findByRefTranIdAndTranType(stoneOutwardDt.getId(), "Outward");
				for(StoneTran stoneTran :stoneTrans) {
					stoneTranService.delete(stoneTran.getId());
				}
				
				
								
				
			}
			
			delete(stoneOutwardMt.getId());
			retVal = "1";
						
		}		
		
//		if(retVal !="-1"){
//			
//			List<StoneOutwardDt>stoneOutwardDts =stoneOutwardDtService.findByStoneOutwardMt(stoneOutwardMt);
//			
//			for(StoneOutwardDt stoneOutwardDt :stoneOutwardDts){
//				
//				stoneOutwardDtService.delete(stoneOutwardDt.getId());
//				
//				StoneTran stoneTran =stoneTranService.RefTranIdAndTranType(stoneOutwardDt.getId(), "Outward");
//				stoneTranService.delete(stoneTran.getId());
//								
//				
//			}
//			
//			delete(stoneOutwardMt.getId());
//			
//			
//		}
		
		
		
		
		return retVal;
	}

	

	@Override
	public StoneOutwardMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneOutwardMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public List<StoneOutwardMt> getInvoiceList(String inwardTypeIds) {
	List<Integer> outwardList =new ArrayList<Integer>();
		
		if(inwardTypeIds.length()>0){
			String vInwardTypeId[] = inwardTypeIds.split(",");
			for(int i=0;i<vInwardTypeId.length;i++){
				outwardList.add(Integer.parseInt(vInwardTypeId[i]));
			}
		}
		
		
		QStoneOutwardMt qstoneOutwardMt = QStoneOutwardMt.stoneOutwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<StoneOutwardMt>stoneOutwardmts = null;
		
		if(outwardList.size()>0){
			stoneOutwardmts=query.from(qstoneOutwardMt).
					where(qstoneOutwardMt.deactive.eq(false).and(qstoneOutwardMt.inwardType.id.in(outwardList))).orderBy(qstoneOutwardMt.invNo.desc()).list(qstoneOutwardMt);
		}else{
			
			stoneOutwardmts=query.from(qstoneOutwardMt).
					where(qstoneOutwardMt.deactive.eq(false)).orderBy(qstoneOutwardMt.invNo.desc()).list(qstoneOutwardMt);
			
		}
		
		
		
		
		return stoneOutwardmts;
	}

	@Override
	public Page<StoneOutwardMt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QStoneOutwardMt qStoneOutwardMt = QStoneOutwardMt.stoneOutwardMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qStoneOutwardMt.deactive.eq(false);
			}else{
				expression = qStoneOutwardMt.deactive.eq(false).and(qStoneOutwardMt.invNo.like("%" + search + "%").or(qStoneOutwardMt.party.name.like("%" + search + "%"))
						.or(qStoneOutwardMt.inwardType.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qStoneOutwardMt.invNo.like("%" + search + "%").or(qStoneOutwardMt.party.name.like("%" + search + "%")).or(qStoneOutwardMt.inwardType.name.like("%" + search + "%"));
			}
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
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<StoneOutwardMt> stoneOutwardList =(Page<StoneOutwardMt>) stoneOutwardMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return stoneOutwardList;
	}

	@Override
	public String stoneOutDelete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMaxStoneOutInvSrno() {
		// TODO Auto-generated method stub
		QStoneOutwardMt qStoneOutwardMt= QStoneOutwardMt.stoneOutwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStoneOutwardMt)
			.where(qStoneOutwardMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qStoneOutwardMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;

	}
	

	

}
