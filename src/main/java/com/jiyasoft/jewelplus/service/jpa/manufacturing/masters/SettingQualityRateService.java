package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISettingQualityRateRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;

@Service
@Repository
@Transactional
public class SettingQualityRateService implements ISettingQualityRateService{

	
	@Autowired
	private ISettingQualityRateRepository settingQualityRateRepository;
	
	
	@Override
	public List<SettingQualityRate> findBySettingChargeAndDeactive(
			SettingCharge settingCharge, Boolean deactive) {
		return settingQualityRateRepository.findBySettingChargeAndDeactive(settingCharge, deactive);
	}

	@Override
	public void save(SettingQualityRate settingQualityRate) {
		settingQualityRateRepository.save(settingQualityRate);
		
	}

	@Override
	public void delete(int id) {
		SettingQualityRate settingQualityRate = findOne(id);
		settingQualityRate.setDeactive(false);
		settingQualityRate.setDeactiveDt(new java.util.Date());
		settingQualityRateRepository.save(settingQualityRate);
		
	}

	@Override
	public Long count() {
		return settingQualityRateRepository.count();
	}

	@Override
	public SettingQualityRate findOne(int id) {
		return settingQualityRateRepository.findOne(id);
	}

	
	
	
	
}
