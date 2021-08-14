package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BranchMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QBranchMaster;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IBranchRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IBranchService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class BranchService implements IBranchService {

	@Autowired
	private IBranchRepository branchRepository;
	
	
	@Override
	public void save(BranchMaster branchMaster) {
		
		branchRepository.save(branchMaster);
		
	}

	@Override
	public void delete(int id) {
		BranchMaster branchMaster = branchRepository.findOne(id);
		branchMaster.setDeactive(true);
		branchMaster.setDeactiveDt(new java.util.Date());
		branchRepository.save(branchMaster);
		
	}

	@Override
	public List<BranchMaster> findAll() {
		
		return branchRepository.findAll();
	}

	@Override
	public Map<Integer, String> getBranchMasterList() {
		Map<Integer, String> branchMasterMap = new LinkedHashMap<Integer, String>();
		Page<BranchMaster> branchMasterList = findActiveBrancMasterSortByName();
		for(BranchMaster branchMaster : branchMasterList)
		{
			branchMasterMap.put(branchMaster.getId(), branchMaster.getName());
		}
		return branchMasterMap;
	}
	
	@Override
	public Page<BranchMaster> searchAll(Integer limit, Integer offset, String sort, String order, String search, Boolean onlyActive) {
		QBranchMaster qBranchMaster = QBranchMaster.branchMaster;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qBranchMaster.deactive.eq(false);
			}else{
				expression = qBranchMaster.deactive.eq(false).and(qBranchMaster.name.like("%" + search + "%").or(qBranchMaster.code.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qBranchMaster.name.like("%" + search + "%").or(qBranchMaster.code.like("%" + search + "%"));
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
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("code")) {
			sort = "code";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<BranchMaster> branchMasterList =(Page<BranchMaster>) branchRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		return branchMasterList;
	}

	@Override
	public BranchMaster findOne(int id) {

		return branchRepository.findOne(id);
	}

	@Override
	public List<BranchMaster> findActiveBranchMasters() {
		QBranchMaster qBranchMaster= QBranchMaster.branchMaster;
		BooleanExpression expression = qBranchMaster.deactive.eq(false);

		List<BranchMaster> branchMasterList = (List<BranchMaster>) branchRepository
				.findAll(expression);

		return branchMasterList;
	}

	@Override
	public Page<BranchMaster> findActiveBrancMasterSortByName() {
	QBranchMaster qBranchMaster = QBranchMaster.branchMaster;
	BooleanExpression expression = qBranchMaster.deactive.eq(false);
	Page<BranchMaster> branchMasterList = branchRepository
			.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name")); 
		return branchMasterList;
	}

	@Override
	public BranchMaster findByCodeAndDeactive(String code, Boolean deactive) {
		
		return branchRepository.findByCodeAndDeactive(code, deactive);
	}

	@Override
	public BranchMaster findByNameAndDeactive(String branchName, Boolean deactive) {
		
		return branchRepository.findByNameAndDeactive(branchName, deactive);
	}

	

	
}
