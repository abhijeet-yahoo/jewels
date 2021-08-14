package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;

@Controller
@RequestMapping("/manufacturing/masters")
public class SettingQualityRateController {

	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	
	@RequestMapping("/settingQualityRate/listing")
	@ResponseBody
	public String settingQualityRateListing(
			@RequestParam(value = "settingChargeId") Integer settingChargeId,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		SettingCharge settingCharge = settingChargeService.findOne(settingChargeId);
		
		List<SettingQualityRate> settingQualityRates = settingQualityRateService.findBySettingChargeAndDeactive(settingCharge,false);
		

		sb.append("{\"total\":").append("100").append(",\"rows\": [");

		
		for(SettingQualityRate settingQualityRate:settingQualityRates){
			sb.append("{\"id\":\"")
				.append(settingQualityRate.getId())
				.append("\",\"quality\":\"")
				.append(settingQualityRate.getQuality() != null ? settingQualityRate.getQuality().getName(): "")
				.append("\",\"qualityRate\":\"")
				.append(settingQualityRate.getQualityRate())
				.append("\",\"action1\":\"")
				.append("<a onClick='javascript:settingQRDelete(event, ").append(settingQualityRate.getId()).append(");' href='javascript:void(0);'")
				.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(settingQualityRate.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
			.append("\"},");
			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
}
