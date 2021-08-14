package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.MarketTypeMast;

public interface IMarketTypeService {

	public void save(MarketTypeMast marketTypeMast);

	public void delete(int id);
	
	public MarketTypeMast findOne(int id);

	public Map<Integer, String> getMarketTypeMastList();

	public List<MarketTypeMast> findActiveMarketTypeMasts();

	public Page<MarketTypeMast> findActiveMarketTypeMastSortByName();

	public MarketTypeMast findByCodeAndDeactive(String code, Boolean deactive);

	public MarketTypeMast findByNameAndDeactive(String marketTypeMastName, Boolean deactive);

	public Page<MarketTypeMast> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive);

	public List<MarketTypeMast> findAllByOrderByNameAsc();
}
