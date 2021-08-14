package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.mysema.query.types.Predicate;


public interface ISettingChargeService {
	
	public List<SettingCharge> findAll();

	public Page<SettingCharge> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(SettingCharge settingCharge);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public SettingCharge findOne(int id);

	public SettingCharge findByRate(Double rate);

	public Page<SettingCharge> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByShape(Integer id);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<SettingCharge> findByRate(Integer limit, Integer offset,
			String sort, String order, String rate, Boolean onlyActive);
	
	public List<SettingCharge> getRates(Party party,Double pointerWt, Boolean deactive,Metal metal,StoneType stoneType, Shape shape, Setting setting,
			SettingType settingType);
	
	public SettingCharge findByStoneTypeAndShapeAndPartyAndMetalAndSettingAndSettingTypeAndFromWeightAndToWeightAndDeactive(
			StoneType stoneType, Shape shape, Party party,Metal metal, Setting setting,
			SettingType settingType,Double fromWeight, Double toWeight, Boolean deactive);

	public List<SettingCharge> findByShapeAndDeactive(Shape shape,Boolean deative);
	
	public Page<SettingCharge> findByShapeAndDeactive(Shape shape,Boolean deactive, Integer limit,
			Integer offset, String sort, String order, String search);
	
	public Page<SettingCharge> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Long countByShape(Shape shape, String colName, String colValue, Boolean onlyActive);
	
	public Page<SettingCharge> findByShape(Shape shape, Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public SettingCharge findByUniqueId(Long uniqueId);
	
	//----------custom search method ---//
	
	public Page<SettingCharge> customSearch( Integer limit, Integer offset,
				String sort, String order, String partyCode,String metalNm,String stoneTypeNm,
				String shapeNm,String settingNm,String settingTypeNm);
	
	public Long customSearchCount(String partyCode,String metalNm,String stoneTypeNm,
			String shapeNm,String settingNm,String settingTypeNm);
	
public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public Page<SettingCharge> searchAll(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	
	public String CheckDuplicate(Integer id, Integer vStoneTypeId,Integer shapeId, Integer vPartyId, Integer metalId, Integer settingId,Integer settingTypeId, Double fromWeight, Double toWeight);
}
