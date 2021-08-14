package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmailConcept;

public interface IEmailConceptService {
	
	public List<EmailConcept> findAll();
	
	public Page<EmailConcept> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(EmailConcept emailConcept);
	
	public void delete(int id);
	
	public Long count();
	
	public EmailConcept findOne(int id);
	
	public EmailConcept findByName(String name);
	
	public Map<Integer, String> getEmailConceptList();

	public List<EmailConcept> findActiveEmialConcept();
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	public Long countAll(String colValue);

	public Page<EmailConcept> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Page<EmailConcept> searchAll(Integer limit, Integer offset, String sort,
			String order, String name);
	
	public Integer maxSrNo();

}
