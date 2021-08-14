package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StyleChange;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IStyleChangeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStyleChangeService;

@Service
@Repository
@Transactional
public class StyleChangeService implements IStyleChangeService {
	
	@Autowired
	private IStyleChangeRepository styleChangeRepository;

	@Override
	public Page<StyleChange> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(StyleChange styleChange) {
		styleChangeRepository.save(styleChange);
		
	}

	@Override
	public Boolean delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StyleChange findByDesign(Design design) {
		return styleChangeRepository.findByDesign(design);
	}

		

}
