package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.common.VAddUtils;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddMetalDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVaddMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVaddMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVaddMtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class VaddMtService implements IVaddMtService {

	@Autowired
	private IVaddMtRepository vaddMtRepository;
	
	@Autowired
	private IVAddDtService vAddDtService;
	
	@Autowired
	private IVAddStnDtService VAddStnDtService;
	
	@Autowired
	private IVaddMetalDtService vaddMetalDtService;
	
	@Autowired
	private IVAddCompDtService vaddCompDtService;

	
	@Override
	public List<CostingMt> findAll() {
		return vaddMtRepository.findAll();
	}

	@Override
	public Page<CostingMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return vaddMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(CostingMt costingMt) {
		vaddMtRepository.save(costingMt);
		
	}

	@Override
	public void delete(int id) {
		CostingMt costingMt = vaddMtRepository.findOne(id);
		costingMt.setDeactive(true);
		costingMt.setDeactiveDt(new java.util.Date());
		vaddMtRepository.save(costingMt);
	}

	@Override
	public Long count() {
		return vaddMtRepository.count();
	}

	@Override
	public CostingMt findOne(int id) {
		return vaddMtRepository.findOne(id);
	}

	@Override
	public CostingMt findByInvNo(String invNo) {
		return vaddMtRepository.findByInvNo(invNo);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QCostingMt qCostingMt = QCostingMt.costingMt;
		BooleanExpression expression = qCostingMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCostingMt.deactive.eq(false);
			} else if (colValue != null) {
				expression = qCostingMt.deactive.eq(false).and(qCostingMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invNo"))
					&& colValue != null) {

				expression = qCostingMt.invNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return vaddMtRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return vaddMtRepository.count();
			}
		}

		return vaddMtRepository.count(expression);
		
	}

	@Override
	public Page<CostingMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String invNo, Boolean onlyActive) {
	
		QCostingMt qCostingMt = QCostingMt.costingMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (invNo == null) {
				expression = qCostingMt.deactive.eq(false);
			} else {
				expression = qCostingMt.deactive.eq(false).and(
						qCostingMt.invNo.like(invNo + "%"));
			}
		} else {
			if (invNo != null) {
				expression = qCostingMt.invNo.like(invNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "invDate";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<CostingMt> costingMtList = (Page<CostingMt>) vaddMtRepository.findAll(expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		return costingMtList;
	}

	@Override
	public CostingMt findByUniqueId(Long uniqueId) {
		return vaddMtRepository.findByUniqueId(uniqueId);
	}
	
	 
	@Override
	public String printWord(CostingMt costingMt) {
		
		DecimalFormat df = new DecimalFormat("#.##");
		Double totFob = vAddDtService.calulateVAddDtTotFob(costingMt.getId());
		Double totLoanVal = VAddStnDtService.calulateVAddStnDtTotLoanVal(costingMt.getId());
		Double netFob = (Double.parseDouble(df.format(totFob))+costingMt.getFrieght()+costingMt.getInsuranceAmount())-totLoanVal-costingMt.getLoanGold();
		
		String words = VAddUtils.displayResult(df.format(netFob));
		
		return words;
		
	}

	@Override
	public String updateLossPerc(int id, Double lossPerc) {

		try {
			VaddMetalDt vaddMetalDt =  vaddMetalDtService.findOne(id);
			
			vaddMetalDt.setLossPerc(lossPerc);
			vaddMetalDtService.save(vaddMetalDt);
			
			
			
			VAddDt vAddDt = vAddDtService.findOne(vaddMetalDt.getvAddDt().getId());
			
			vAddDtService.updateValueAddition(vAddDt);
			
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
	
	@Override
	public String updateLossPercComp(int id, Double lossPerc) {

		try {
			VAddCompDt vAddCompDt =  vaddCompDtService.findOne(id);
			
			vAddCompDt.setLossPerc(lossPerc);
			vaddCompDtService.save(vAddCompDt);
			
			
			
			VAddDt vAddDt = vAddDtService.findOne(vAddCompDt.getvAddDt().getId());
			
			vAddDtService.updateValueAddition(vAddDt);
			
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

}
