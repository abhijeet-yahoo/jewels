package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.MetalLock;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetalLock;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IMetalLockRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalLockService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MetalLockService implements IMetalLockService {

	@Autowired IMetalLockRepository metalLockRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(MetalLock metalLock) {
		metalLockRepository.save(metalLock);
		
	}

	@Override
	public void delete(int id) {
		MetalLock metalLock = metalLockRepository.findOne(id);
		metalLock.setDeactive(true);
		metalLock.setDeactiveDt(new java.util.Date());
		metalLockRepository.save(metalLock);
	}

	@Override
	public MetalLock findOne(int id) {
		
		return metalLockRepository.findOne(id);
	}

	@Override
	public Page<MetalLock> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		
		QMetalLock qMetalLock = QMetalLock.metalLock;
		BooleanExpression expression = null;
		
		
		if (onlyActive) {
			if (search == null) {
				expression = qMetalLock.deactive.eq(false);
			} else {
				expression = qMetalLock.deactive.eq(false).and(qMetalLock.metal.name.like("%" + search + "%"));
			}
		} else {
			if (search != null) {
				expression = qMetalLock.metal.name.like("%" + search + "%");
			}
		}

		if (limit == null) {
			limit = 1000;
		}
		if (offset == null) {
			offset = 0;
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} 
		Page<MetalLock> metallockList = (Page<MetalLock>) metalLockRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

	
		return metallockList;
	}

	@Override
	public MetalLock findByMetalLockDtAndMetalAndDeactive(Date lockDate, Metal metal, Boolean deactive) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		String fBetFromDate = dfOutput.format(lockDate);
		@SuppressWarnings("unchecked")
		TypedQuery<MetalLock> query =  (TypedQuery<MetalLock>)entityManager.createNativeQuery("select * from metallock where deactive =0 and  "
				+ "cast(metallockdt as date) ='"+fBetFromDate+"' and metalid ="+metal.getId(),MetalLock.class);
		
		
		
		List<MetalLock> metalLocks = query.getResultList();
		
		MetalLock metalLock =null;
		
		if(metalLocks.size()>0) {
			metalLock= metalLocks.get(0);
		}
		
		return metalLock;
	}

}
