package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DiaWtTagRangeMaster;


public interface IDiaWtTagRangeService {

	
     public	DiaWtTagRangeMaster findOne(int id);
     
     public void save(DiaWtTagRangeMaster diaWtTagRangeMaster);
 	
 	public void delete(int id);
 	
 	
 	
	public List<DiaWtTagRangeMaster> getDiaWt(Double pointerWt);
 	
 		
}
