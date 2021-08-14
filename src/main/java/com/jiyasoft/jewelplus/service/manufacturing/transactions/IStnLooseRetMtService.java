package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetMt;

public interface IStnLooseRetMtService {
	
	public Page<StnLooseRetMt> searchAll(Integer limit, Integer offset,String sort, String order, String search);

	public void save(StnLooseRetMt stnLooseRetMt);

	public void delete(int id);

	public StnLooseRetMt findOne(int id);
	
	public String deleteMt(Integer mtId,Principal principal);
	
	public Integer getMaxInvSrno();

	public String saveStnLooseRetMt(StnLooseRetMt stnLooseRetMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,String vTranDate) throws ParseException;

	public StnLooseRetMt findByInvNo(String invNo);

}
