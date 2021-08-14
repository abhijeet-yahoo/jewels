package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeMt;

public interface IBarcodeDtService {

	public BarcodeDt findOne(int id);
	
	public void save(BarcodeDt barcodeDt);
	
	public void delete(int id);
	
	public List<BarcodeDt> findByBarcodeMt(BarcodeMt barcodeMt);

}
