package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStonePurchaseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStonePurchaseMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StonePurchaseMtService implements IStonePurchaseMtService {
	
	@Autowired
	private IStonePurchaseMtRepository stonePurchaseMtRepository;
	
	@Autowired
	private IStonePurchaseDtService stonePurchaseDtService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void save(StonePurchaseMt stonePurchaseMt) {
		// TODO Auto-generated method stub
		stonePurchaseMtRepository.save(stonePurchaseMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		StonePurchaseMt stonePurchaseMt = stonePurchaseMtRepository.findOne(id);
		stonePurchaseMt.setDeactive(true);
		stonePurchaseMt.setDeactiveDt(new Date());
		stonePurchaseMtRepository.save(stonePurchaseMt);
	}

	@Override
	public StonePurchaseMt findOne(int id) {
		// TODO Auto-generated method stub
		return stonePurchaseMtRepository.findOne(id);
	}

	@Override
	public StonePurchaseMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return stonePurchaseMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public StonePurchaseMt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return stonePurchaseMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Page<StonePurchaseMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {

		QStonePurchaseMt qStonePurchaseMt = QStonePurchaseMt.stonePurchaseMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qStonePurchaseMt.deactive.eq(false);
			}else{
				expression = qStonePurchaseMt.deactive.eq(false).and(qStonePurchaseMt.invNo.like("%" + search + "%").or(qStonePurchaseMt.party.name.like("%" + search + "%"))
						.or(qStonePurchaseMt.inwardType.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qStonePurchaseMt.invNo.like("%" + search + "%").or(qStonePurchaseMt.party.name.like("%" + search + "%")).or(qStonePurchaseMt.inwardType.name.like("%" + search + "%"));
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
	
		Page<StonePurchaseMt> stonePurchaseList =(Page<StonePurchaseMt>) stonePurchaseMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return stonePurchaseList;

	}

	@Override
	public String stonePurcDelete(int id,Principal principal) {
		// TODO Auto-generated method stub
	String retVal ="-1";
		
		try {
				List<StonePurchaseDt> stonePurchaseDts = stonePurchaseDtService.findByStonePurchaseMtAndDeactive(findOne(id), false);
				for (StonePurchaseDt stonePurchaseDt : stonePurchaseDts) {
					if(stonePurchaseDt.getBalCarat() < stonePurchaseDt.getCarat()){
						return "-2";
					}
					stonePurchaseDtService.delete(stonePurchaseDt.getId());	
				}
				
				
				delete(id);
				
				
				retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public List<StonePurchaseMt> getInvoiceList(String inwardTypeIds,String fromDate, String toDate) throws ParseException {
		
		List<Integer> inwardList =new ArrayList<Integer>();
		
		if(inwardTypeIds.length()>0){
			String vInwardTypeId[] = inwardTypeIds.split(",");
			for(int i=0;i<vInwardTypeId.length;i++){
				inwardList.add(Integer.parseInt(vInwardTypeId[i]));
			}
		}
		
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fromDate1 = dfInput.parse(fromDate);
		Date toDate1 = dfInput.parse(toDate);
		
		
		
		
		
		
		
		QStonePurchaseMt qStonePurchaseMt = QStonePurchaseMt.stonePurchaseMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<StonePurchaseMt>stonePurchaseMts = null;
		
		if(inwardList.size()>0){
			stonePurchaseMts=query.from(qStonePurchaseMt).
					where(qStonePurchaseMt.deactive.eq(false).and(qStonePurchaseMt.invDate.between(fromDate1, toDate1)).and(qStonePurchaseMt.inwardType.id.in(inwardList))).orderBy(qStonePurchaseMt.id.desc()).list(qStonePurchaseMt);
		}else{
			
			stonePurchaseMts=query.from(qStonePurchaseMt).
					where(qStonePurchaseMt.deactive.eq(false).and(qStonePurchaseMt.invDate.between(fromDate1, toDate1))).orderBy(qStonePurchaseMt.id.desc()).list(qStonePurchaseMt);
			
		}
		
		
		
		
		return stonePurchaseMts;
	}

	

}
