package com.jiyasoft.jewelplus.service.manufacturing.masters;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StyleChange;

public interface IStyleChangeService {
	
	public Page<StyleChange> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(StyleChange styleChange);
	
	public Boolean delete(int id);

	public Long count();
	
	public StyleChange findByDesign(Design design);

}
