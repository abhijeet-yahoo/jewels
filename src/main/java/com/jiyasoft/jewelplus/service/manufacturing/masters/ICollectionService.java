package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;


public interface ICollectionService {

	
	public List<CollectionMaster> findAll();

	public Page<CollectionMaster> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(CollectionMaster collectionMaster);

	public void delete(int id);

	public Long count();
	
	public CollectionMaster findOne(int id);

	public CollectionMaster findByName(String collectionName);
	
	public CollectionMaster findByCollectionCode(String collectionCode);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<CollectionMaster> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	
	public Map<Integer, String> getCollectionList();

	public Page<CollectionMaster> findActiveCollectionsSortByName();
}
