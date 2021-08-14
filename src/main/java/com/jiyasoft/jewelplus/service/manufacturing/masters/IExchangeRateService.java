package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ExchangeRateMaster;

public interface IExchangeRateService {

	public ExchangeRateMaster findOne(int id);
	
	public void save(ExchangeRateMaster exchangeRateMaster);
	
	public void delete(int id);
	
	public List<ExchangeRateMaster> findByName(String name);
	
	public String addExchngRateMaster(Model model,Principal principal) ;
	
	public Page<ExchangeRateMaster> searchAll (Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);
	
	public String exchngRtListing (Integer limit, Integer offset,String sort, String order, String search,Principal principal);
	
	public String exchangeRateMasterSave(ExchangeRateMaster exchangeRateMaster, Integer id,RedirectAttributes redirectAttributes, Principal principal,BindingResult result) throws ParseException;

	public String checkDuplicate(ExchangeRateMaster exchangeRateMaster, Integer id); 
}
