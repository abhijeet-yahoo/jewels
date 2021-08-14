package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.QProcessMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QVoucherTrfMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VoucherTrfMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVoucherTrfMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVoucherTrfMtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class VoucherTrfMtService implements IVoucherTrfMtService {
	
	@Autowired
	private IVoucherTrfMtRepository voucherTrfMtRepository;
	
	@Override
	public List<VoucherTrfMt> findAll(){
			return voucherTrfMtRepository.findAll();
	}

	@Override
	public Page<VoucherTrfMt> findAll(Integer limit,Integer offset,String sort,String order,String search){
		limit =(limit == null ? 10:limit);
		offset = (offset == null ? 0 : offset);
		
		int page = (offset == 0 ? 0 : (offset / limit));
		if(sort == null) {sort = "id";}
		
		return voucherTrfMtRepository.findAll(new PageRequest(page, limit,(order.equalsIgnoreCase("asc")? Direction.ASC : Direction.DESC),sort));
	}

	@Override
	public void save(VoucherTrfMt voucherTrfMt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		VoucherTrfMt voucherTrfMt= voucherTrfMtRepository.findOne(id);
		voucherTrfMt.setDeactive(true);
		voucherTrfMt.setDeactiveDt(new java.util.Date());
		voucherTrfMtRepository.save(voucherTrfMt);
	}

	@Override
	public VoucherTrfMt findOne(int id) {
		// TODO Auto-generated method stub
		return voucherTrfMtRepository.findOne(id);
	}

	@Override
	public List<VoucherTrfMt> findActiveVoucherTrfMt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<VoucherTrfMt> searchAll(Integer limit, Integer offset, String sort, String order, String name,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		QVoucherTrfMt qVoucherTrfMt= QVoucherTrfMt.voucherTrfMt;
		BooleanExpression expression = qVoucherTrfMt.deactive.eq(false);
		
		if(name !=null && name !=""){
			expression =qVoucherTrfMt.deactive.eq(false).and(qVoucherTrfMt.voucherno.like(name + "%").or(qVoucherTrfMt.bagMt.name.like(name + "%")));
			}

			int page = (offset == 0 ? 0 : (offset / limit));

			if (sort == null) {
				sort = "id";
			}

			Page<VoucherTrfMt> voucherTrfmtList = (Page<VoucherTrfMt>) voucherTrfMtRepository.findAll(
					expression,
					new PageRequest(page, limit,
							(order.equalsIgnoreCase("asc") ? Direction.ASC
									: Direction.DESC), sort));
			
		return voucherTrfmtList;
	}
	
	
	
	
	
	
}
