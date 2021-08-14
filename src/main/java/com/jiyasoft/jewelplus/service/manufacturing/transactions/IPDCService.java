package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;

public interface IPDCService {

	public List<PDC> findAll();

	public Page<PDC> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(PDC pdc);

	public void delete(Integer id);

	public PDC findOne(Integer id);

	public Page<PDC> findByStyleNo(Integer limit, Integer offset, String sort,
			String order, String styleNo, Boolean onlyActive);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public List<PDC> findByCurrentStkAndDeactive(Boolean currStk,Boolean deactive);

	public PDC findByDesignAndDeactive(Design design,Boolean deactive);

}
