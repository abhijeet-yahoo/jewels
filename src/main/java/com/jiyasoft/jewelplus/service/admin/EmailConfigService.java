package com.jiyasoft.jewelplus.service.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jiyasoft.jewelplus.domain.admin.EmailConfig;
import com.jiyasoft.jewelplus.repository.admin.IEmailConfigRepository;


@Service
@Repository
@Transactional
public class EmailConfigService {

	@Autowired
	private IEmailConfigRepository emailConfigRepository;
	
	
	public List<EmailConfig> findAll() {
		return emailConfigRepository.findAll();
	}
	
	public void save(EmailConfig emailConfig) {
		emailConfigRepository.save(emailConfig);
	}
	
	public void delete(int id) {
		EmailConfig emailConfig = emailConfigRepository.findOne(id);
		emailConfig.setDeactive(true);
		emailConfig.setDeactiveDt(new java.util.Date());
		emailConfigRepository.save(emailConfig);
	}

	public Long count() {
		return emailConfigRepository.count();
	}

	public EmailConfig findOne(int id) {
		return emailConfigRepository.findOne(id);
	}

	public EmailConfig findByName(String userName) {
		return emailConfigRepository.findByName(userName);	
	}

	public EmailConfig findByIdAndName(Integer id, String userName) {
		return emailConfigRepository.findByIdAndName(id, userName);	
	}
	
	
}
