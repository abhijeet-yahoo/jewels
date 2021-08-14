package com.jiyasoft.jewelplus.service.manufacturing.masters;



import org.springframework.data.domain.Page;


import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStyleLabDt;


public interface IClientStyleLabService {

	

	public Page<ClientStyleLabDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(ClientStyleLabDt clientStyleLabDt);

	public void delete(int id);

	

	public ClientStyleLabDt findOne(int id);

	public ClientStyleLabDt findByRate(Double rate);
	
	
	public Page<ClientStyleLabDt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search);
	
	public String CheckDuplicate(Integer id, Integer vPartyId, Integer vStyleId, Integer metalId, Integer labourTypeId,Double fromWt, Double toWt,Integer purityId);



}
