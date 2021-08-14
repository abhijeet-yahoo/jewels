package com.jiyasoft.jewelplus.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.QMenuMast;
import com.jiyasoft.jewelplus.repository.admin.IMenuMastRepository;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MenuMastService {
	
	@Autowired
	private IMenuMastRepository menuMastRepository;

	
	
	public MenuMast findByMenuNm(String menuNm) {
		return menuMastRepository.findByMenuNm(menuNm);
	}
	

	
	public List<MenuMast> findAll() {
		return menuMastRepository.findAll();
	}

	
	public Long count() {
		return menuMastRepository.count();
	}

	
	public MenuMast findOne(int id) {
		return menuMastRepository.findOne(id);
	}

	
	public MenuMast findByMenuHeading(String menuHeading) {
		return menuMastRepository.findByMenuHeading(menuHeading);
	}

	
	/*public List<MenuMast> findByTranType(String tranType) {
		return menuMastRepository.findByTranType(tranType);
	}*/
	
	
	public void save(MenuMast menuMast) {
         menuMastRepository.save(menuMast);		
	}

	
	public List<MenuMast> findByParentIdAndDeactiveOrderBySeqNoAsc(
			Integer parentId, Boolean deactive) {
		return menuMastRepository.findByParentIdAndDeactiveOrderBySeqNoAsc(parentId, deactive);
	}
	
	
	
	
}
