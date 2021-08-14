package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingMt;

public interface IStockMeltingMtService {
	
	public void save(StockMeltingMt stockMeltingMt);

	public void delete(int id);

	public StockMeltingMt findOne(int id);
	
	public Page<StockMeltingMt> searchAll(Integer limit, Integer offset,String sort, String order,String search);
	
	public Integer getMaxInvSrno();
	
	public String saveStockMeltingMt(StockMeltingMt stockMeltingMt,Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,
			String vTranDate,Integer pLocationIds) throws ParseException;

	public String stockMeltingMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;
	
	public String stockMeltingTransferListing(Integer deptId);
	
	public String stockMeltingMtDelete(Integer mtId,Principal principal);
}
