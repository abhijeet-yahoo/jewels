package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Date;
import java.util.List;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.AutoStyleParameter;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QAutoStyleParameter;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IAutoStyleParameterRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IAutoStyleParameterService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICollectionService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class AutoStyleParameterService implements IAutoStyleParameterService {

	@Autowired
	private IAutoStyleParameterRepository autoStyleParameterRepository;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ICollectionService collectionService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDesignService designService;
	
	@Override
	public List<AutoStyleParameter> findAll() {
		
		return autoStyleParameterRepository.findAll();
	}

	@Override
	public void save(AutoStyleParameter autoStyleParameter) {
		autoStyleParameterRepository.save(autoStyleParameter);
		
	}

	@Override
	public void delete(int id) {
		AutoStyleParameter autoStyleParameter = autoStyleParameterRepository.findOne(id);
		autoStyleParameter.setDeactive(true);
		autoStyleParameter.setDeactiveDt(new java.util.Date());
		autoStyleParameterRepository.save(autoStyleParameter);
		
	}

	@Override
	public Page<AutoStyleParameter> searchAll(Integer limit, Integer offset, String sort, String order, String name) {
		
		QAutoStyleParameter qAutoStyleParameter = QAutoStyleParameter.autoStyleParameter;
		BooleanExpression expression = qAutoStyleParameter.deactive.eq(false);

		if(name !=null && name !=""){
		expression =qAutoStyleParameter.deactive.eq(false).and(qAutoStyleParameter.category.name.like(name + "%").or(qAutoStyleParameter.collectionMaster.name.like(name + "%")));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<AutoStyleParameter> autoStyleParameterList = (Page<AutoStyleParameter>) autoStyleParameterRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("search expression "+expression);
		
		
		return autoStyleParameterList;
	}

	@Override
	public AutoStyleParameter findOne(int id) {
		
		return autoStyleParameterRepository.findOne(id);
	}

	@Override
	public AutoStyleParameter findByCategoryAndCollectionMasterAndDeactive(Category category,
			CollectionMaster collectionMaster, Boolean deactive) {
		
		return autoStyleParameterRepository.findByCategoryAndCollectionMasterAndDeactive(category, collectionMaster, deactive);
	}

	@Override
	public String getautoStyleNo(Category category1,CollectionMaster collectionMaster,Party party1) {
			
		
		String category = category1.getCategCode().toUpperCase().substring(0, 2);
		String collection = collectionMaster.getCollectionCode().toUpperCase().substring(0, 2);
		String party = party1.getPartyCode().toUpperCase().substring(0, 2);
		
		AutoStyleParameter autoStyleParameter = findByCategoryAndCollectionMasterAndDeactive(category1, collectionMaster, false);
		String newStyle=null;
		if(autoStyleParameter != null) {
			
			int Lastno =autoStyleParameter.getLastNo()+1;
			
			String number= StringUtil.toZeroPaddedString(Lastno, 4, 4);
			
			 newStyle ="LD"+category+number+collection+party;
			
			autoStyleParameter.setLastNo(Lastno);
			save(autoStyleParameter);
			
		
			
			
			
	}else {
		
		
		 newStyle ="LD"+category+"0001"+collection+party;
		 
			AutoStyleParameter autoStyleParameter2 =  new AutoStyleParameter();
			
			autoStyleParameter2.setCategory(category1);
			autoStyleParameter2.setCollectionMaster(collectionMaster);
			autoStyleParameter2.setLastNo(Integer.parseInt("0001"));
			autoStyleParameter2.setCreatedDate(new Date());
			save(autoStyleParameter2);
		 
		 
	}
		
		Design design3 =designService.findByMainStyleNoAndDeactive(newStyle, false);
		if(design3 != null) {
			
			newStyle=getautoStyleNo(category1, collectionMaster, party1);
			
			
		}
		
		
		
		return newStyle;
	}

}
