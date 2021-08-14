package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;

public interface ISettingQualityRateService {

	
	List<SettingQualityRate> findBySettingChargeAndDeactive(SettingCharge settingCharge,Boolean deactive);
	
	public void save(SettingQualityRate settingQualityRate);

	public void delete(int id);

	public Long count();
	
	public SettingQualityRate findOne(int id);

	
}
