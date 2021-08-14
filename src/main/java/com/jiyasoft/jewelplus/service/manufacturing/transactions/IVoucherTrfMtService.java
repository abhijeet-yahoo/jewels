package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VoucherTrfMt;

public interface IVoucherTrfMtService {

	
	public List<VoucherTrfMt> findAll();
	
	public void save(VoucherTrfMt voucherTrfMt);
	
	public void delete(int id);
	
	public VoucherTrfMt findOne(int id);
	
	public List<VoucherTrfMt> findActiveVoucherTrfMt();
	
	public Page<VoucherTrfMt> findAll(Integer limit,Integer offset,String sort,String order,String search);
	
	public Page<VoucherTrfMt> searchAll(Integer limit,Integer offset,String sort,String order,String name,Boolean onlyActive);
 }
