package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.MarketTypeMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductTypeMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubConcept;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IAutoStyleParameterService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICollectionService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IConceptService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMarketTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubCategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubConceptService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DesignService implements IDesignService {

	@Autowired
	private IDesignRepository designRepository;

	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IAutoStyleParameterService autoStyleParameterService;
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private IDesignMetalService designMetalService;
	
	@Autowired
	private IPDCService pdcService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IDesignGroupService designGroupService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ISubCategoryService subCategoryService;
	
	@Autowired
	private ICollectionService collectionService;
	
	@Autowired
	private IConceptService conceptService;
	
	@Autowired
	private ISubConceptService subConceptService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IProductSizeService productSizeService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMarketTypeService marketTypeService;
	
	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Override
	public List<Design> findAll() {
		return designRepository.findAll();
	}

	@Override
	public Page<Design> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = qDesign.deactive.eq(false);
			
		if(search !=null){
		expression =qDesign.deactive.eq(false).and(qDesign.styleNo.like(search.trim() + "%").or(qDesign.designNo.like(search.trim() +"%"))
				.or(qDesign.refNo.like(search.trim() +"%")));
		}
		
		
		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		if (sort == null) {
			sort = "id";
		}
		
			
		
		
		return designRepository.findAll(expression, new PageRequest(page, limit,(order.equalsIgnoreCase("asc") ? Direction.DESC
				: Direction.ASC), sort));
		
		
	}

	@Override
	public void save(Design design) {
		designRepository.save(design);
	}

	@Override
	public Boolean delete(int id) {
		Design design = designRepository.findOne(id);
		Page<OrderDt> orderDtList = orderDtService.findByStyleNo(null, null, null, null, design.getStyleNo(), true);
		
	

		Boolean lFound = false;
		/*
		 * for (OrderDt orderDt : orderDtList) { lFound = true; }
		 */
		
		if(orderDtList.getTotalElements()>0) {
			lFound = true;
		}
		
		PDC pdc =pdcService.findByDesignAndDeactive(design, false);
		if(pdc !=null){
			lFound = true;
		}
		
		

		if (!(lFound)) {
			design.setDeactive(true);
			design.setDeactiveDt(new java.util.Date());
			designRepository.save(design);
			
			List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
			for(DesignMetal designMetal:designMetals){
				designMetalService.delete(designMetal.getId());
			}
			
			
			List<DesignStone>designStones =designStoneService.findByDesign(design);
			for(DesignStone designStone :designStones){
				designStoneService.delete(designStone.getId());
			}
			
			List<DesignComponent>designComponents =designComponentService.findByDesign(design);
			for(DesignComponent designComponent : designComponents){
				designComponentService.delete(designComponent.getId());
			}
		}

		return lFound;
	}

	@Override
	public Long count() {
		return designRepository.count();
	}

	@Override
	public Design findOne(int id) {
		return designRepository.findOne(id);
	}

	@Override
	public Design findByStyleNo(String styleNo) {
		return designRepository.findByStyleNo(styleNo);
	}

	@Override
	public Design findByStyleNoAndVersion(String styleNo, String version) {
		QDesign qDesign = QDesign.design;
		BooleanExpression expression = qDesign.deactive.eq(false).and(qDesign.styleNo.eq(styleNo).and(qDesign.version.eq(version)));

		List<Design> designList = (List<Design>) designRepository.findAll(expression);

		return (designList.size() == 0 ? null : designList.get(0));
	}

	@Override
	public Map<Integer, String> getDesignList() {
		Map<Integer, String> designMap = new HashMap<Integer, String>();
		List<Design> designList = findActiveDesigns();

		for (Design design : designList) {
			designMap.put(design.getId(), design.getStyleNo());
		}

		return designMap;
	}

	@Override
	public List<Design> findActiveDesigns() {
		QDesign qDesign = QDesign.design;
		BooleanExpression expression = qDesign.deactive.eq(false);

		List<Design> designList = (List<Design>) designRepository
				.findAll(expression);

		return designList;
	}

	@Override
	public Map<Integer, String> getOrderDesigns(String pStyleNo) {
		Map<Integer, String> designMap = new HashMap<Integer, String>();
		Page<Design>designs = findByStyleNo(10, 0, "styleNo", "ASC", pStyleNo, true);
		for (Design design : designs) {
			designMap.put(design.getId(), design.getStyleNo());
		}

		return designMap;		
	}

	@Override
	public Page<Design> findByStyleNo(Integer limit, Integer offset,
			String sort, String order, String styleNo, Boolean onlyActive) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (styleNo == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.styleNo.like(styleNo.trim() + "%"));
			}
		} else {
			if (styleNo != null) {
				expression = qDesign.styleNo.like(styleNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		
		

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, Direction.DESC, sort));

		return designList;

	}

	@Override
	public Page<Design> findByOldStyleNo(Integer limit, Integer offset,	String sort, String order, String oldStyleNo, Boolean onlyActive) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (oldStyleNo == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.oldStyleNo.like(oldStyleNo + "%"));
			}
		} else {
			if (oldStyleNo != null) {
				expression = qDesign.oldStyleNo.like(oldStyleNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return designList;

	}

	@Override
	public Page<Design> findByDesignNo(Integer limit, Integer offset,
			String sort, String order, String designNo, Boolean onlyActive) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (designNo == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.designNo.like(designNo + "%"));
			}
		} else {
			if (designNo != null) {
				expression = qDesign.designNo.like(designNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return designList;

	}

	@Override
	public Page<Design> findByCategory(Integer limit, Integer offset, String sort, String order,
		String category, Boolean onlyActive) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (category == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.category.name.like(category + "%"));
				
			}
		} else {
			if (category != null) {
				expression = qDesign.category.name.like(category + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return designList;

	}

	@Override
	public Page<Design> findBySubCategory(Integer limit, Integer offset, String sort, String order,
		String subCategory, Boolean onlyActive) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (subCategory == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.subCategory.name.like(subCategory + "%"));
			}
		} else {
			if (subCategory != null) {
				expression = qDesign.subCategory.name.like(subCategory + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return designList;

	}

	@Override
	public Page<Design> findByConcept(Integer limit, Integer offset, String sort, String order,
			String concept, Boolean onlyActive) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (concept == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.concept.name.like(concept + "%"));
			}
		} else {
			if (concept != null) {
				expression = qDesign.concept.name.like(concept + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return designList;

	}

	@Override
	public Page<Design> findByVersion(Integer limit, Integer offset, String sort, String order,
			String version, Boolean onlyActive) {

		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (version == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.version.like(version + "%"));
			}
		} else {
			if (version != null) {
				expression = qDesign.version.like(version + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return designList;

	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		System.out.println("colName "+ colName);
		System.out.println("colValue "+ colValue);
		System.out.println("onlyActive "+ onlyActive);

		
		QDesign qDesign = QDesign.design;
		BooleanExpression expression = qDesign.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDesign.deactive.eq(false);
			} else if (colName == null && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.styleNo.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("designNo") && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.designNo.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("styleNo") && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.styleNo.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("oldStyleNo") && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.oldStyleNo.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("category") && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.category.name.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("subCategory") && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.subCategory.name.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("concept") && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.concept.name.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("version") && colValue != null) {
				expression = qDesign.deactive.eq(false).and(qDesign.version.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("designNo")) && colValue != null) {
				expression = qDesign.designNo.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("designNo") && colValue != null) {
				expression = qDesign.designNo.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("styleNo") && colValue != null) {
				expression = qDesign.styleNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return designRepository.count(expression);
			} else {
				return designRepository.count(expression);
			}
		}

		return designRepository.count(expression);
	}


	public Design findByUniqueId(Long uniqueId) {
		return designRepository.findByUniqueId(uniqueId);
	}
	

	@Override
	public Design findByDesignNoAndDeactive(String designNo,Boolean deactive) {
		return designRepository.findByDesignNoAndDeactive(designNo,deactive);
	}

	@Override
	public String updateCarat(Design design) {
		// TODO Auto-generated method stub
		String retVal="-1";
		
		List<DesignStone> designStones=designStoneService.findByDesign(design);
		
		
		Integer totStone=0;
		Double totCarat=0.0;
		
		for(DesignStone designStone:designStones){
			totStone+=designStone.getStone();
			totCarat+=designStone.getCarat();
			
		}
		
		design.setTotCarat(Math.round(totCarat * 1000.0) / 1000.0);
		design.setTotStone(totStone);
		save(design);
		retVal="1";
		
		return retVal;
	}
	
	
	@Override
	public Page<Design> findByMainStyleNo(Integer limit, Integer offset,
			String sort, String order, String mainStyleNo, Boolean onlyActive) {
		QDesign qDesign = QDesign.design;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (mainStyleNo == null) {
				expression = qDesign.deactive.eq(false);
			} else {
				expression = qDesign.deactive.eq(false).and(qDesign.mainStyleNo.like(mainStyleNo.trim() + "%"));
			}
		} else {
			if (mainStyleNo != null) {
				expression = qDesign.mainStyleNo.like(mainStyleNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		
		

		Page<Design> designList = (Page<Design>) designRepository.findAll(expression,
				new PageRequest(page, limit, Direction.DESC, sort));

		return designList;
	}
	
	

	@Override
	public Design findByMainStyleNoAndDeactive(String mainStyleNo,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return designRepository.findByMainStyleNoAndDeactive(mainStyleNo, deactive);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@Override
	public String designCopy(Integer styleId, String styleNo, String designNo,String versionNo, Principal principal) {
	
		String retVal = "-1";
		
		Boolean designNoAvailable = true;
		
		designNoAvailable =checkDesignDuplicate(styleNo, versionNo, null);
		if(designNoAvailable.equals(false)){
			return "-1";
		}
		
		designNoAvailable =checkDuplicateDesignNo(designNo, versionNo, null);
		
		if(designNoAvailable.equals(false)){
			return "-2";
		}
				
		Design design = findOne(styleId);
		
		List<DesignStone> designStoneList = designStoneService.findByDesign(design);
		List<DesignComponent> designComponentList = designComponentService.findByDesign(design);
		List<DesignMetal> designMetalList=designMetalService.findByDesignAndDeactive(design, false);
		
		
			
			 Design designNew = new Design(); 
			
		 try {
			
			BeanUtils.copyProperties(designNew, design);
			 } catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
			}
		

			designNew.setId(null);
			designNew.setStyleNo(styleNo);
			designNew.setDesignNo(designNo);
			designNew.setCreateDate(new java.util.Date());
			designNew.setCreatedBy(principal.getName());
			
			
			
			if(versionNo != null && !versionNo.trim().isEmpty()){
				designNew.setVersion(versionNo.trim());
				designNew.setMainStyleNo(styleNo+"_"+designNew.getVersion());
			 }else{
				 designNew.setMainStyleNo(styleNo);
				 designNew.setVersion("");
			 }
			
			save(designNew);
			
			//design Stone
			
		for (DesignStone designStone : designStoneList) {

			DesignStone designStoneNew = new DesignStone();

			try {
				BeanUtils.copyProperties(designStoneNew, designStone);
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (InvocationTargetException e) {

				e.printStackTrace();
			}

			designStoneNew.setId(null);
			designStoneNew.setDesign(designNew);
			designStoneNew.setCreateDate(new java.util.Date());
			designStoneNew.setCreatedBy(principal.getName());

			designStoneService.save(designStoneNew);

		}
		
		// DESIGN COMPONENT
					for(DesignComponent designComponent : designComponentList){
						
						DesignComponent designComponentNew = new DesignComponent();
						
										
						try {
							BeanUtils.copyProperties(designComponentNew, designComponent);
						} catch (IllegalAccessException e) {
							
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							
							e.printStackTrace();
						}
						
						designComponentNew.setId(null); 
						designComponentNew.setDesign(designNew);
						designComponentNew.setCreateDate(new java.util.Date());
						designComponentNew.setCreatedBy(principal.getName());
						
						designComponentService.save(designComponentNew);
							
					}
					
					for(DesignMetal designMetal: designMetalList){
						
						DesignMetal designMetalNew= new DesignMetal();
						
						try {
							BeanUtils.copyProperties(designMetalNew, designMetal);
						} catch (IllegalAccessException e) {
							
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							
							e.printStackTrace();
						}
						
						designMetalNew.setId(null);
						designMetalNew.setDesign(designNew);
						designMetalNew.setCreateDate(new java.util.Date());
						designMetalNew.setCreatedBy(principal.getName());
						
						designMetalService.save(designMetalNew);
						
						retVal = "success_"+designNew.getId();
						

					

					}
					
					return retVal;
	

	
	
	
	}

	@Override
	public Boolean checkDesignDuplicate(String styleNo, String version,
			Integer id) {
	
		Boolean designNoAvailable = true;
		styleNo = styleNo.trim();
		version = version.trim();
		String vMainStyle="";
		 if(version != null && !version.isEmpty()){
			 vMainStyle = styleNo+"_"+version;
		 }else{
			 vMainStyle = styleNo;
		 }
		
		if (id == null) {
			designNoAvailable = (findByMainStyleNoAndDeactive(vMainStyle,false) == null);
			} 
		else {
			
			Design design = findOne(id);
			
			if (!(styleNo.equalsIgnoreCase(design.getStyleNo().trim()) && version.equalsIgnoreCase(design.getVersion().trim()))) {
				designNoAvailable = (findByMainStyleNoAndDeactive(vMainStyle,false) == null);
			}
		}

		return designNoAvailable;
	}

	@Override
	public Design findByDesignNoAndVersionAndDeactive(String designNo,
			String version, Boolean deactive) {
		// TODO Auto-generated method stub
		return designRepository.findByDesignNoAndVersionAndDeactive(designNo, version, deactive);
	}

	@Override
	public Boolean checkDuplicateDesignNo(String designNo, String version,
			Integer id) {
		Boolean designNoAvailable = true;
		designNo = designNo.trim();
		version = version.trim();
		
		
		if (id == null) {
			designNoAvailable = (findByDesignNoAndVersionAndDeactive(designNo, version, false) == null);
			} 
		else {
			
			Design design = findOne(id);
			
			if (!(designNo.equalsIgnoreCase(design.getDesignNo().trim()) && version.equalsIgnoreCase(design.getVersion().trim()))) {
				designNoAvailable = (findByDesignNoAndVersionAndDeactive(designNo, version, false) == null);
			}
		}

		return designNoAvailable;
	}

	@Override
	public Page<Design> customSearch(Integer limit, Integer offset,
			String sort, String order, String styleNo, String versionNo,
			String designNo, String reffNo, String categoryNm,
			String subCategoryNm, String conceptNm,String designDate) throws ParseException {
		// TODO Auto-generated method stub
		
		QDesign qDesign = QDesign.design;
		BooleanBuilder where = new BooleanBuilder();
		
		
		
		if(styleNo !=null){
			where.and(qDesign.mainStyleNo.like("%"+ styleNo+"%"));
		}
		if(versionNo !=null){
			where.and(qDesign.version.like("%"+ versionNo+"%"));
		}
		if(designNo !=null){
			where.and(qDesign.designNo.like("%"+ designNo+"%"));
		}
		if(reffNo !=null){
			where.and(qDesign.refNo.like("%"+ reffNo+"%"));
		}
		if(categoryNm !=null){
			where.and(qDesign.category.name.like( categoryNm+"%"));
		}
		if(subCategoryNm !=null){
			where.and(qDesign.subCategory.name.like("%"+ subCategoryNm+"%"));
		}
		if(conceptNm !=null){
			where.and(qDesign.concept.name.like("%"+ conceptNm+"%"));
		}
		if(designDate !=null && designDate != ""){
			 Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(designDate);  
			where.and(qDesign.designDt.eq(date1));
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<Design> designList = (Page<Design>) designRepository.findAll(where,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return designList;
	
	}

	@Override
	public List<Design> findByMainStyleNoContaining(String name) {
		// TODO Auto-generated method stub
		return designRepository.findByMainStyleNoContaining(name);
	}

	@Override
	public String designExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile,
			Principal principal) throws ParseException {

		synchronized (this) {
			
			String retVal ="";
			
			Boolean remarkFlg = false;
			
			  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			
			
			try {
				if(!tempExcelFile.isEmpty()){
				
				List<DesignExcel> designExcelList = new ArrayList<DesignExcel>();
				
				
				    if (tempExcelFile.endsWith("xlsx")) {
				    	
				    	int i=1;
				    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				        XSSFSheet worksheet = workbook.getSheetAt(0);
						while (i <= worksheet.getLastRowNum()) {
							
							DesignExcel designExcel = new DesignExcel();
							XSSFRow row = worksheet.getRow(i++);
					
							if (isRowEmpty(row)) {
								continue;
							}
							
							if( row != null) {
							
								String remark = "";	
								
								/*
								 * if(row.getCell(0, row.RETURN_BLANK_AS_NULL) == null){ continue; }
								 */
							
							
							Cell deisgnCell = row.getCell(5, row.RETURN_BLANK_AS_NULL);
							if (deisgnCell != null) {
								if (row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() != "") {
								
								Design design = findByMainStyleNoAndDeactive(row.getCell(5).toString().trim(), false);
								if (design != null) {
									if (remark == "") {
										remark = "Duplicate Design No";
									} else {
										remark += ",Duplicate Design No";
									}
								}
							
								
								Cell dsgGroupCell = row.getCell(1, row.RETURN_BLANK_AS_NULL);
								if (dsgGroupCell != null) {
									if (row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() != "") {

										DesignGroup designGroup = designGroupService
												.findByName(row.getCell(1).toString().trim());
										if (designGroup == null) {
											if (remark == "") {
												remark = "No such designGroup exists";
											} else {
												remark += ",No such designGroup exists";
											}

										}
									}
								}else {
									if(remark == ""){
										remark = "DesignGroup Compulsory"; 
									}else {
										remark += ",DesignGroup compulsory";
									}
								}
								
								
								Cell designerCell = row.getCell(7, row.RETURN_BLANK_AS_NULL);
								if (designerCell != null) {
									if (row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Employee designerEmployee = employeeService.findByName(row.getCell(7).toString().trim());
										if(designerEmployee == null){
											if(remark == ""){
												remark = "No such designer exists"; 
											}else {
												remark += ",No such designer exists";
											}
										}
									}
								}
								
								
								Cell categoryCell = row.getCell(8, row.RETURN_BLANK_AS_NULL);
								if (categoryCell != null) {
									if (row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Category category =  categoryService.findByName(row.getCell(8).toString().trim());
										if(category == null){
											if(remark == ""){
												remark = "No such category exists"; 
											}else {
												remark += ",No such category exists";
											}
											
										}
									}
								}else {
									if(remark == ""){
										remark = "Category is compulsory"; 
									}else {
										remark += ",Category is compulsory";
									}
								}
								
								Cell subCategoryCell = row.getCell(9, row.RETURN_BLANK_AS_NULL);
								if (subCategoryCell != null) {
									if (row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() != "") {

										SubCategory subCategory = subCategoryService.findByName(row.getCell(9).toString().trim());
										if(subCategory == null){
											if(remark == ""){
												remark = "No such subCategory exists"; 
											}else {
												remark += ",No such subCategory exists";
											}
											
										}
									}
								}
								
								Cell marketCell = row.getCell(10, row.RETURN_BLANK_AS_NULL);
								if (marketCell != null) {
									if (row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() != "") {
										MarketTypeMast marketTypeMast = marketTypeService.findByNameAndDeactive(row.getCell(10).toString().trim(), false);
										if(marketTypeMast == null){
											if(remark == ""){
												remark = "No such MarketType exists"; 
											}else {
												remark += ",No such MarketType exists";
											}
											
										}
									}
								}else {
									if(remark == ""){
										remark = "MarketType Compulsory"; 
									}else {
										remark += ",MarketType compulsory";
									}
								}
								
								Cell producttypeCell = row.getCell(11, row.RETURN_BLANK_AS_NULL);
								if (producttypeCell != null) {
									if (row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() != "") {
										ProductTypeMast productTypeMast = productTypeService.findByNameAndDeactive(row.getCell(11).toString().trim(), false);
										if(productTypeMast == null){
											if(remark == ""){
												remark = "No such ProductType exists"; 
											}else {
												remark += ",No such ProductType exists";
											}
											
										}
									}
								}else {
									if(remark == ""){
										remark = "ProductType Compulsory"; 
									}else {
										remark += ",ProductType compulsory";
									}
								}
								
								
								Cell collectionCell = row.getCell(12, row.RETURN_BLANK_AS_NULL);
								if (collectionCell != null) {
									if (row.getCell(12, row.RETURN_BLANK_AS_NULL).toString() != "") {
										CollectionMaster collectionMaster =  collectionService.findByName(row.getCell(12).toString().trim());
										if(collectionMaster == null){
											if(remark == ""){
												remark = "No such collection exists"; 
											}else {
												remark += ",No such collection exists";
											}
											
										}
									}
								}else {
									if(remark == ""){
										remark = "Collection Compulsory"; 
									}else {
										remark += ",Collection compulsory";
									}
								}
								
								
								Cell conceptCell = row.getCell(13, row.RETURN_BLANK_AS_NULL);
								if (conceptCell != null) {
									if (row.getCell(13, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Concept concept = conceptService.findByName(row.getCell(13).toString().trim());
										if(concept == null){
											if(remark == ""){
												remark = "No such concept exists"; 
											}else {
												remark += ",No such concept exists";
											}
											
										}
									}
								}
								
								
								
								Cell subconceptCell = row.getCell(14, row.RETURN_BLANK_AS_NULL);
								if (subconceptCell != null) {
									if (row.getCell(14, row.RETURN_BLANK_AS_NULL).toString() != "") {
										SubConcept subConcept = subConceptService.findByName(row.getCell(14).toString().trim());
										if(subConcept == null){
											if(remark == ""){
												remark = "No such subConcept exists"; 
											}else {
												remark += ",No such subConcept exists";
											}
											
										}
									}
								}
								
								
								Cell productSizeCell = row.getCell(15, row.RETURN_BLANK_AS_NULL);
								if (productSizeCell != null) {
									if (row.getCell(15, row.RETURN_BLANK_AS_NULL).toString() != "") {
										ProductSize productSize = productSizeService.findByName(row.getCell(15).toString().trim());
										if(productSize == null){
											if(remark == ""){
												remark = "No such productSize exists"; 
											}else {
												remark += ",No such productSize exists";
											}
											
										}
									}
								}
								
								
								Cell cadDesignerCell = row.getCell(17, row.RETURN_BLANK_AS_NULL);
								if (cadDesignerCell != null) {
									if (row.getCell(17, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Employee cadDesigner = employeeService.findByName(row.getCell(17).toString().trim());
										if(cadDesigner == null){
											if(remark == ""){
												remark = "No such cadDesigner exists"; 
											}else {
												remark += ",No such cadDesigner exists";
											}
											
										}
									}
								}

								Shape shape = null;
								Cell stonetypeCell = row.getCell(20, row.RETURN_BLANK_AS_NULL);
								if (stonetypeCell != null) {
									if (row.getCell(20, row.RETURN_BLANK_AS_NULL).toString() != "") {
										StoneType stoneType = stoneTypeService.findByName(row.getCell(20).toString().trim());
										if(stoneType == null){
											if(remark == ""){
												remark = "No such stoneType exists"; 
											}else {
												remark += ",No such stoneType exists";
											}
											
										}
									}
									
									
									Cell shapeCell = row.getCell(21, row.RETURN_BLANK_AS_NULL);
									if (shapeCell != null) {
										if (row.getCell(21, row.RETURN_BLANK_AS_NULL).toString() != "") {
											shape = shapeService.findByName(row.getCell(21).toString().trim());	
											if(shape == null){
												if(remark == ""){
													remark = "No such shape exists"; 
												}else {
													remark += ",No such shape exists";
												}
												
											}
										}
									}
									
//									  else { if(remark == ""){ 
//										  remark = "Shape compulsory"; 
//										  }else { 
//											  remark +=  ",Shape compulsory"; } }
									
									
									
									
									Cell qualityCell = row.getCell(22, row.RETURN_BLANK_AS_NULL);
									if (qualityCell != null) {
										if (row.getCell(22, row.RETURN_BLANK_AS_NULL).toString() != "") {
											Quality quality = qualityService.findByShapeAndName(shape,row.getCell(22).toString().trim());
											if(quality == null){
												if(remark == ""){
													remark = "No such quality exists";
												}else {
													remark += ",No such quality exists ";
												}
											
											}
										}
									}
									
									
									SizeGroup sizeGroup = null;
									Cell sizegroupCell = row.getCell(25, row.RETURN_BLANK_AS_NULL);
									if (sizegroupCell != null) {
										if (row.getCell(25, row.RETURN_BLANK_AS_NULL).toString() != "") {
											 sizeGroup = sizeGroupService.findByName(row.getCell(25).toString().trim());
											if(sizeGroup == null){
												if(remark == ""){
													remark = "No such sizeGroup exists";
												}else {
													remark += ",No such sizeGroup exists ";
												}
											
											}
										}
									}else {
										if(shape != null) {
										if(remark == ""){
											remark = "sizeGroup Compulsory"; 
										}else {
											remark += ",sizeGroup compulsory";
										}
										}
										
									}
									
									
									Cell sieveCell = row.getCell(24, row.RETURN_BLANK_AS_NULL);
									if (sieveCell != null) {
										if (row.getCell(24, row.RETURN_BLANK_AS_NULL).toString() != "") {
											StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndSieveAndSizeGroupAndDeactive(shape,row.getCell(23, row.RETURN_BLANK_AS_NULL).toString(),
													sieveCell.toString().trim(), sizeGroup,false);
											if(stoneChart == null){
												if(remark == ""){
													remark = "Breakup not match with chartmaster";
												}else {
													remark += ",Breakup not match with chartmaster ";
												}
											
											}
										}
									}else {
										if(shape != null) {
										if(remark == ""){
											remark = "Sieve Compulsory"; 
										}else {
											remark += ",Sieve compulsory";
										}
										}
										
									}
									
									
									
									
									
								}
								/*
								 * else {
								 * 
								 * if(remark == ""){ remark = "StoneType compulsory"; }else { remark +=
								 * ",StoneType compulsory"; } }
								 */
								
								
								
							
							
								
							
								
								
							
								
								
								
								
								/*
								 * Cell sizeCell = row.getCell(23, row.RETURN_BLANK_AS_NULL); if(sizeCell ==
								 * null) { if(shape != null) { if(remark == ""){ remark = "Size Compulsory";
								 * }else { remark += ",Size compulsory"; } } }
								 * 
								 * Cell sieveCell = row.getCell(24, row.RETURN_BLANK_AS_NULL); if(sieveCell ==
								 * null) { if(shape != null) { if(remark == ""){ remark = "Sieve Compulsory";
								 * }else { remark += ",Sieve compulsory"; } } }
								 */
								
								
								Cell settingTypeCell = row.getCell(29, row.RETURN_BLANK_AS_NULL);
								if (settingTypeCell != null) {
									if (row.getCell(29, row.RETURN_BLANK_AS_NULL).toString() != "") {
										SettingType settingType =settingTypeService.findByName(row.getCell(29).toString().trim());
										if(settingType == null){
											if(remark == ""){
												remark = "No such settingType exists";
											}else {
												remark += ",No such settingType exists ";
											}
										
										}
									}
								}
								
								
								Cell settingCell = row.getCell(28, row.RETURN_BLANK_AS_NULL);
								if (settingCell != null) {
									if (row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Setting setting = settingService.findByName(row.getCell(28).toString().trim());
										if(setting == null){
											if(remark == ""){
												remark = "No such setting exists";
											}else {
												remark += ",No such setting exists ";
											}
										
										}
									}
								}
								
								
								Cell partyCell = row.getCell(30, row.RETURN_BLANK_AS_NULL);
								if (partyCell != null) {
									if (row.getCell(30, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Party party = partyService.findByPartyCodeAndDeactive(row.getCell(30).toString().trim(), false);
										if(party == null){
											if(remark == ""){
												remark = "No such Vendor exists";
											}else {
												remark += ",No such Vendor exists ";
											}
										
										}
									}
								}else {
									if(remark == ""){
										remark = "Vendor Compulsory"; 
									}else {
										remark += ",Vendor compulsory";
									}
								}
								
								
								
								
								
								
								}
							}
							
							
							
							
							if(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null) {
							if (remark != "") {
								
								
								remarkFlg = true;
								
									designExcel.setDesignGroup(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setStyleNo(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setDesigner(row.getCell(7, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setCategory(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setSubCategory(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setMarketType(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setProductType(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setCollection(row.getCell(12, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(12, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setConcept(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(13, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setSubConcept(row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(14, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setProductSize(row.getCell(15, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(15, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setCadDesigner(row.getCell(17, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(17, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setStoneType(row.getCell(20, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(20, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setShape(row.getCell(21, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(21, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setQuality(row.getCell(22, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(22, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setSizegroup(row.getCell(25, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(25, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setSettingType(row.getCell(29, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(29, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setSetting(row.getCell(28, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() :"");
									
									designExcel.setSize(row.getCell(23, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(23, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setSieve(row.getCell(24, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(24, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setVendor(row.getCell(30, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(30, row.RETURN_BLANK_AS_NULL).toString() :"");
									designExcel.setStatusRemark(remark);
									
									remark ="";
									designExcelList.add(designExcel);
								}
								
							
							}
							
							
						}
													
						}
						
						workbook.close();
						
				    } else if (tempExcelFile.endsWith("xls")) {
				    	int i = 1;
						HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
						HSSFSheet worksheet = workbook.getSheetAt(0);
						while (i <= worksheet.getLastRowNum()) {
							
							DesignExcel designExcel = new DesignExcel();
							
							String remark = "";	
							
							HSSFRow row = worksheet.getRow(i++);
							if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
								continue;
							}
							
							
							Cell deisgnCell = row.getCell(5, row.RETURN_BLANK_AS_NULL);
							if (deisgnCell != null) {
								if (row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() != "") {
								
								Design design = findByMainStyleNoAndDeactive(row.getCell(5).toString().trim(), false);
								if (design != null) {
									if (remark == "") {
										remark = "Duplicate Design No";
									} else {
										remark += ",Duplicate Design No";
									}
								}
							
								
								Cell dsgGroupCell = row.getCell(1, row.RETURN_BLANK_AS_NULL);
								if (dsgGroupCell != null) {
									if (row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() != "") {

										DesignGroup designGroup = designGroupService
												.findByName(row.getCell(1).toString().trim());
										if (designGroup == null) {
											if (remark == "") {
												remark = "No such designGroup exists";
											} else {
												remark += ",No such designGroup exists";
											}

										}
									}
								}else {
									if(remark == ""){
										remark = "DesignGroup Compulsory"; 
									}else {
										remark += ",DesignGroup compulsory";
									}
								}
								
								
								Cell designerCell = row.getCell(7, row.RETURN_BLANK_AS_NULL);
								if (designerCell != null) {
									if (row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Employee designerEmployee = employeeService.findByName(row.getCell(7).toString().trim());
										if(designerEmployee == null){
											if(remark == ""){
												remark = "No such designer exists"; 
											}else {
												remark += ",No such designer exists";
											}
										}
									}
								}
								
								
								Cell categoryCell = row.getCell(8, row.RETURN_BLANK_AS_NULL);
								if (categoryCell != null) {
									if (row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Category category =  categoryService.findByName(row.getCell(8).toString().trim());
										if(category == null){
											if(remark == ""){
												remark = "No such category exists"; 
											}else {
												remark += ",No such category exists";
											}
											
										}
									}
								}else {
									if(remark == ""){
										remark = "Category is compulsory"; 
									}else {
										remark += ",Category is compulsory";
									}
								}
								
								Cell subCategoryCell = row.getCell(9, row.RETURN_BLANK_AS_NULL);
								if (subCategoryCell != null) {
									if (row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() != "") {

										SubCategory subCategory = subCategoryService.findByName(row.getCell(9).toString().trim());
										if(subCategory == null){
											if(remark == ""){
												remark = "No such subCategory exists"; 
											}else {
												remark += ",No such subCategory exists";
											}
											
										}
									}
								}
								
								Cell marketCell = row.getCell(10, row.RETURN_BLANK_AS_NULL);
								if (marketCell != null) {
									if (row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() != "") {
										MarketTypeMast marketTypeMast = marketTypeService.findByNameAndDeactive(row.getCell(10).toString().trim(), false);
										if(marketTypeMast == null){
											if(remark == ""){
												remark = "No such MarketType exists"; 
											}else {
												remark += ",No such MarketType exists";
											}
											
										}
									}
								}
								
								  else { 
									  
									  if(remark == ""){ 
									  remark = "MarketType Compulsory"; 
									  }
									  else { 
										  remark += ",MarketType compulsory"; 
										  } 
									  }
								 
								
								Cell producttypeCell = row.getCell(11, row.RETURN_BLANK_AS_NULL);
								if (producttypeCell != null) {
									if (row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() != "") {
										ProductTypeMast productTypeMast = productTypeService.findByNameAndDeactive(row.getCell(11).toString().trim(), false);
										if(productTypeMast == null){
											if(remark == ""){
												remark = "No such ProductType exists"; 
											}else {
												remark += ",No such ProductType exists";
											}
											
										}
									}
								}
								
								  else { 
									  if(remark == ""){ 
										  remark = "ProductType Compulsory"; 
										  }else { 
											  remark += ",ProductType compulsory"; 
											  } 
									  }
								 
								
								
								Cell collectionCell = row.getCell(12, row.RETURN_BLANK_AS_NULL);
								if (collectionCell != null) {
									if (row.getCell(12, row.RETURN_BLANK_AS_NULL).toString() != "") {
										CollectionMaster collectionMaster =  collectionService.findByName(row.getCell(12).toString().trim());
										if(collectionMaster == null){
											if(remark == ""){
												remark = "No such collection exists"; 
											}else {
												remark += ",No such collection exists";
											}
											
										}
									}
								}
								
								  else { 
									  if(remark == ""){ 
										  remark = "Collection Compulsory"; 
										  }else { 
											  remark +=  ",Collection compulsory"; } }
								 
								
								
								Cell conceptCell = row.getCell(13, row.RETURN_BLANK_AS_NULL);
								if (conceptCell != null) {
									if (row.getCell(13, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Concept concept = conceptService.findByName(row.getCell(13).toString().trim());
										if(concept == null){
											if(remark == ""){
												remark = "No such concept exists"; 
											}else {
												remark += ",No such concept exists";
											}
											
										}
									}
								}
								
								
								
								Cell subconceptCell = row.getCell(14, row.RETURN_BLANK_AS_NULL);
								if (subconceptCell != null) {
									if (row.getCell(14, row.RETURN_BLANK_AS_NULL).toString() != "") {
										SubConcept subConcept = subConceptService.findByName(row.getCell(14).toString().trim());
										if(subConcept == null){
											if(remark == ""){
												remark = "No such subConcept exists"; 
											}else {
												remark += ",No such subConcept exists";
											}
											
										}
									}
								}
								
								
								Cell productSizeCell = row.getCell(15, row.RETURN_BLANK_AS_NULL);
								if (productSizeCell != null) {
									if (row.getCell(15, row.RETURN_BLANK_AS_NULL).toString() != "") {
										ProductSize productSize = productSizeService.findByName(row.getCell(15).toString().trim());
										if(productSize == null){
											if(remark == ""){
												remark = "No such productSize exists"; 
											}else {
												remark += ",No such productSize exists";
											}
											
										}
									}
								}
								
								
								Cell cadDesignerCell = row.getCell(17, row.RETURN_BLANK_AS_NULL);
								if (cadDesignerCell != null) {
									if (row.getCell(17, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Employee cadDesigner = employeeService.findByName(row.getCell(17).toString().trim());
										if(cadDesigner == null){
											if(remark == ""){
												remark = "No such cadDesigner exists"; 
											}else {
												remark += ",No such cadDesigner exists";
											}
											
										}
									}
								}

								
								Cell stonetypeCell = row.getCell(20, row.RETURN_BLANK_AS_NULL);
								if (stonetypeCell != null) {
									if (row.getCell(20, row.RETURN_BLANK_AS_NULL).toString() != "") {
										StoneType stoneType = stoneTypeService.findByName(row.getCell(20).toString().trim());
										if(stoneType == null){
											if(remark == ""){
												remark = "No such stoneType exists"; 
											}else {
												remark += ",No such stoneType exists";
											}
											
										}
									}
								}
								/*
								 * else {
								 * 
								 * if(remark == ""){ remark = "StoneType compulsory"; }else { remark +=
								 * ",StoneType compulsory"; } }
								 */
								
								Shape shape = null;
								Cell shapeCell = row.getCell(21, row.RETURN_BLANK_AS_NULL);
								if (shapeCell != null) {
									if (row.getCell(21, row.RETURN_BLANK_AS_NULL).toString() != "") {
										shape = shapeService.findByName(row.getCell(21).toString().trim());	
										if(shape == null){
											if(remark == ""){
												remark = "No such shape exists"; 
											}else {
												remark += ",No such shape exists";
											}
											
										}
									}
								}
								/*
								 * else { if(remark == ""){ remark = "Shape compulsory"; }else { remark +=
								 * ",Shape compulsory"; } }
								 */
							
								
								Cell qualityCell = row.getCell(22, row.RETURN_BLANK_AS_NULL);
								if (qualityCell != null) {
									if (row.getCell(22, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Quality quality = qualityService.findByShapeAndName(shape,row.getCell(22).toString().trim());
										if(quality == null){
											if(remark == ""){
												remark = "No such quality exists";
											}else {
												remark += ",No such quality exists ";
											}
										
										}
									}
								}
								
								
								
								Cell sizegroupCell = row.getCell(25, row.RETURN_BLANK_AS_NULL);
								if (sizegroupCell != null) {
									if (row.getCell(25, row.RETURN_BLANK_AS_NULL).toString() != "") {
										SizeGroup sizeGroup = sizeGroupService.findByName(row.getCell(25).toString().trim());
										if(sizeGroup == null){
											if(remark == ""){
												remark = "No such sizeGroup exists";
											}else {
												remark += ",No such sizeGroup exists ";
											}
										
										}
									}
								}else {
									if(shape != null) {
									if(remark == ""){
										remark = "sizeGroup Compulsory"; 
									}else {
										remark += ",sizeGroup compulsory";
									}
									}
									
								}
								
								
								Cell sizeCell = row.getCell(23, row.RETURN_BLANK_AS_NULL);
								if(sizeCell == null) {
								if(shape != null) {
									if(remark == ""){
										remark = "Size Compulsory"; 
									}else {
										remark += ",Size compulsory";
									}
									}
								}
								
								Cell sieveCell = row.getCell(24, row.RETURN_BLANK_AS_NULL);
								if(sieveCell == null) {
								if(shape != null) {
									if(remark == ""){
										remark = "Sieve Compulsory"; 
									}else {
										remark += ",Sieve compulsory";
									}
									}
								}
								
								
								Cell settingTypeCell = row.getCell(29, row.RETURN_BLANK_AS_NULL);
								if (settingTypeCell != null) {
									if (row.getCell(29, row.RETURN_BLANK_AS_NULL).toString() != "") {
										SettingType settingType =settingTypeService.findByName(row.getCell(29).toString().trim());
										if(settingType == null){
											if(remark == ""){
												remark = "No such settingType exists";
											}else {
												remark += ",No such settingType exists ";
											}
										
										}
									}
								}
								
								
								Cell settingCell = row.getCell(28, row.RETURN_BLANK_AS_NULL);
								if (settingCell != null) {
									if (row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Setting setting = settingService.findByName(row.getCell(28).toString().trim());
										if(setting == null){
											if(remark == ""){
												remark = "No such setting exists";
											}else {
												remark += ",No such setting exists ";
											}
										
										}
									}
								}
								
								
								Cell partyCell = row.getCell(30, row.RETURN_BLANK_AS_NULL);
								if (partyCell != null) {
									if (row.getCell(30, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Party party = partyService.findByPartyCodeAndDeactive(row.getCell(30).toString().trim(), false);
										if(party == null){
											if(remark == ""){
												remark = "No such party exists";
											}else {
												remark += ",No such party exists ";
											}
										
										}
									}
								}else {
									if(remark == ""){
										remark = "Vendor Compulsory"; 
									}else {
										remark += ",Vendor compulsory";
									}
								}
								
								
								
								
								
								
								}
							}
						
						
							if(row.getCell(5).toString() != null) {
						if (remark != "") {
							
							remarkFlg = true;
							
							designExcel.setDesignGroup(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setStyleNo(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setDesigner(row.getCell(7, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setCategory(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setSubCategory(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setMarketType(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setProductType(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setCollection(row.getCell(12, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(12, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setConcept(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(13, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setSubConcept(row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(14, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setProductSize(row.getCell(15, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(15, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setCadDesigner(row.getCell(17, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(17, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setStoneType(row.getCell(20, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(20, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setShape(row.getCell(21, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(21, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setQuality(row.getCell(22, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(22, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setSizegroup(row.getCell(25, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(25, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setSettingType(row.getCell(29, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(29, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setSetting(row.getCell(28, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() :"");
							
							designExcel.setSize(row.getCell(23, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(23, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setSieve(row.getCell(24, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(24, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setVendor(row.getCell(30, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(30, row.RETURN_BLANK_AS_NULL).toString() :"");
							designExcel.setStatusRemark(remark);
							
							designExcelList.add(designExcel);
						
						}
							}
					}
					
						workbook.close();
						
				    } else {
				        throw new IllegalArgumentException("The specified file is not Excel file");
				    }
				    
				    
				    // Create Design
				    
				    if (remarkFlg) {
				    	 session.setAttribute("designExcelSessionList", designExcelList);  
				    	 retVal="yes";
				    	
				    }else {
				    	
				    	
				    	   if (tempExcelFile.endsWith("xlsx")) {
				    		 
				    		   int i=1;
						    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
						        XSSFSheet worksheet = workbook.getSheetAt(0);
						        
						        Integer styleId=0;
						        String designDate = null;
								Date designdt = new Date();
								
								
								
								Integer	stone =0;
								Double carat =0.0;
								while (i <= worksheet.getLastRowNum()) {
									
									XSSFRow row = worksheet.getRow(i++);
									
									if (isRowEmpty(row)) {
										continue;
									}
									
									if(row != null) {
										
										
									
										/*
										 * if(row.getCell(0, row.RETURN_BLANK_AS_NULL) == null){ continue; }
										 */
									 
								
									//note : temporary list
									//remark is set in createdBy for temporary list
									//status is set in updatedBy for temporary list
																		
									Design design = findByMainStyleNoAndDeactive(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :null,false);	
								
									StoneType stoneType = stoneTypeService.findByName(row.getCell(20, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(20, row.RETURN_BLANK_AS_NULL).toString() :null);
									
									Shape shape = null;
									StoneChart stoneChart = null;
									if(row.getCell(21, row.RETURN_BLANK_AS_NULL) != null) {
										shape = shapeService.findByName(row.getCell(21, row.RETURN_BLANK_AS_NULL).toString().trim());
										
									//	 stoneChart = stoneChartService.findByShapeAndSieveAndDeactive(shape, row.getCell(24, row.RETURN_BLANK_AS_NULL).toString().trim(), false);
									}
									
									
									Quality quality = null;
									if(shape != null) {
									if(row.getCell(22, row.RETURN_BLANK_AS_NULL) != null) {
											quality  = qualityService.findByShapeAndName(shape,row.getCell(22, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(22, row.RETURN_BLANK_AS_NULL).toString().trim() :null);	
										}
									}
									
									
									SizeGroup sizeGroup = sizeGroupService.findByNameAndDeactive(row.getCell(25, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(25, row.RETURN_BLANK_AS_NULL).toString().trim() :null, false);
									SettingType settingType =settingTypeService.findByName(row.getCell(29, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(29, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
									Setting setting = settingService.findByName(row.getCell(28, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(28, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
									
									if(stoneType != null) {
										 stoneChart = stoneChartService.findByShapeAndSizeAndSieveAndSizeGroupAndDeactive(shape, row.getCell(23, row.RETURN_BLANK_AS_NULL).toString().trim(),
												 row.getCell(24, row.RETURN_BLANK_AS_NULL).toString().trim(),sizeGroup, false);
									}
									
										
									if(design == null) {
										
										
										if(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null) {
											stone =0;
											carat =0.0;
										
											DesignGroup designGroup =designGroupService.findByName(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											Employee designerEmployee = employeeService.findByName(row.getCell(7, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(7, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											Category category =  categoryService.findByName(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(8, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											SubCategory subCategory = subCategoryService.findByName(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											CollectionMaster collectionMaster =  collectionService.findByName(row.getCell(12, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(12, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											Concept concept = conceptService.findByName(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(13, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											SubConcept subConcept = subConceptService.findByName(row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(14, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											ProductSize productSize = productSizeService.findByName(row.getCell(15, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(15, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											Employee cadDesigner = employeeService.findByName(row.getCell(17, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(17, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											Color color = colorService.findByDefColorAndDeactive(true, false);
											Purity purity = purityService.findByDefPurityAndDeactive(true, false);
											MarketTypeMast marketTypeMast =  marketTypeService.findByNameAndDeactive(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString().trim() :null, false);
											ProductTypeMast productTypeMast =  productTypeService.findByNameAndDeactive(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(11, row.RETURN_BLANK_AS_NULL).toString().trim() :null, false);
											Party party =  partyService.findByPartyCodeAndDeactive(row.getCell(30, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(30, row.RETURN_BLANK_AS_NULL).toString().trim() :null, false);

											
											if(row.getCell(2, row.RETURN_BLANK_AS_NULL) != null) {
												designDate = row.getCell(2).toString();
												designdt = df.parse(designDate);
													
											}
											
										Double waxWtConv = purity.getWaxWtConv() != null ? purity.getWaxWtConv() : 0.0;		
											
										String mainStyle = 	(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :null) +
															(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() :"");
										
										
										Double waxtwt = Math.round(((row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0) / 10.50) * 1000.0)/1000.0 ;
										
											
										Design design2 =  new Design();
										design2.setDesignGroup(designGroup);
										design2.setDesignDt(designdt);
										design2.setDesignNo(row.getCell(3, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(3, row.RETURN_BLANK_AS_NULL).toString() :null);
										design2.setRefNo(row.getCell(4, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(4, row.RETURN_BLANK_AS_NULL).toString() :null);
										design2.setMainStyleNo(mainStyle);
										design2.setStyleNo(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :null);
										design2.setVersion(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() :"");
										design2.setDesignerEmployee(designerEmployee);
										design2.setCategory(category);
										design2.setSubCategory(subCategory);
										design2.setCollectionMaster(collectionMaster);
										design2.setConcept(concept);
										design2.setSubConcept(subConcept);
										design2.setProductSize(productSize);
										design2.setSilverWt(row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										design2.setCadDesigner(cadDesigner);
										design2.setCastedPcs(row.getCell(18, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(18, row.RETURN_BLANK_AS_NULL).toString())) :1);
										design2.setRemarks(row.getCell(19, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(19, row.RETURN_BLANK_AS_NULL).toString() :null);
										design2.setCreateDate(new Date());
										design2.setCreatedBy(principal.getName());
										design2.setMarketTypeMast(marketTypeMast);
										design2.setProductTypeMast(productTypeMast);
										design2.setVendor(party);
										design2.setWaxWt(waxtwt);
										design2.setVendorStyle(row.getCell(31, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(31, row.RETURN_BLANK_AS_NULL).toString() :null);
										design2.setTotStone(row.getCell(26, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(26, row.RETURN_BLANK_AS_NULL).toString())) :0);
										design2.setTotCarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										design2.setDefaultImage(mainStyle+".jpg");
										design2.setImage1(mainStyle+".jpg");
										design2.setDefImgChk(1);
										save(design2);
										
										
										DesignMetal designMetal =  new DesignMetal();
										designMetal.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
										designMetal.setMainMetal(true);
										designMetal.setMetalPcs(row.getCell(18, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(18, row.RETURN_BLANK_AS_NULL).toString())) :1);
										designMetal.setSilverWt(row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										designMetal.setPerPcSilverWt(row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										designMetal.setPurity(purity);
										designMetal.setColor(color);
										designMetal.setPerPcWaxWt(waxtwt);
										designMetal.setWaxWt(waxtwt);
										designMetal.setDesign(design2);
										designMetal.setCreateDate(new Date());
										designMetal.setCreatedBy(principal.getName());
										designMetal.setPerPcMetalWeight(Math.round((waxWtConv * waxtwt)*1000.0)/1000.0);
										designMetalService.save(designMetal);
										
										if(stoneType != null) {
										
										DesignStone designStone = new DesignStone();
										designStone.setStoneType(stoneType);
										designStone.setShape(stoneChart != null ? stoneChart.getShape() :null);
										designStone.setQuality(quality);
										designStone.setSize(stoneChart != null ? stoneChart.getSize() :null);
										designStone.setSieve(stoneChart != null ? stoneChart.getSieve() :null);
										designStone.setSizeGroup(stoneChart != null ? stoneChart.getSizeGroup() :null);
										designStone.setStone(row.getCell(26, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(26, row.RETURN_BLANK_AS_NULL).toString())) :0);
										designStone.setCarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										designStone.setMcarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										designStone.setSetting(setting);
										designStone.setSettingType(settingType);
										designStone.setDiaCateg("");
										designStone.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
										
										designStone.setCreateDate(new Date());
										designStone.setCreatedBy(principal.getName());
										
										designStone.setDesign(design2);
										designStoneService.save(designStone);
										stone +=designStone.getStone();
										carat +=designStone.getCarat();
										
										}
										styleId = design2.getId();
										
										
										}else {
											
										//	StoneChart stoneChart = stoneChartService.findByShapeAndSieveAndDeactive(shape, row.getCell(24, row.RETURN_BLANK_AS_NULL).toString(), false);
											if(stoneType != null) {
											DesignStone designStone = new DesignStone();
											designStone.setStoneType(stoneType);
											designStone.setShape(stoneChart != null ? stoneChart.getShape() :null);
											designStone.setQuality(quality);
											designStone.setSize(stoneChart != null ? stoneChart.getSize() :null);
											designStone.setSieve(stoneChart != null ? stoneChart.getSieve() :null);
											designStone.setSizeGroup(stoneChart != null ? stoneChart.getSizeGroup() :null);
											designStone.setStone(row.getCell(26, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(26, row.RETURN_BLANK_AS_NULL).toString())) :0);
											designStone.setCarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											designStone.setSetting(setting);
											designStone.setSettingType(settingType);
											designStone.setDiaCateg("");
											designStone.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
											designStone.setMcarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											
											designStone.setCreateDate(new Date());
											designStone.setCreatedBy(principal.getName());
											
											designStone.setDesign(findOne(styleId));
											designStoneService.save(designStone);
											stone +=designStone.getStone();
											carat +=designStone.getCarat();
											}
											
										}
										
										Design ds = findOne(styleId);
										ds.setTotCarat(Math.round((carat)*1000.0)/1000.0);
										ds.setTotStone(stone);
										save(ds);
									
									
								}

								}
								
								workbook.close();
								}
								
						    } else if (tempExcelFile.endsWith("xls")) {
						    	int i = 1;
								HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
								HSSFSheet worksheet = workbook.getSheetAt(0);
								
								 Integer styleId=0;	
								 String designDate = null;
									Date designdt = new Date();
									
									
									Integer	stone =0;
									Double carat =0.0;
									while (i <= worksheet.getLastRowNum()) {
										HSSFRow row = worksheet.getRow(i++);
							
										if (isRowEmpty(row)) {
											continue;
										}
										
										if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
											continue;
										}
									
									
										//note : temporary list
										//remark is set in createdBy for temporary list
										//status is set in updatedBy for temporary list
																			
										Design design = findByMainStyleNoAndDeactive(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :null,false);	
										
										StoneType stoneType = stoneTypeService.findByName(row.getCell(20, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(20, row.RETURN_BLANK_AS_NULL).toString() :null);
										
										Shape shape = null;
										StoneChart stoneChart = null;
										if(row.getCell(21, row.RETURN_BLANK_AS_NULL) != null) {
											shape = shapeService.findByName(row.getCell(21, row.RETURN_BLANK_AS_NULL).toString());
											
											 stoneChart = stoneChartService.findByShapeAndSieveAndDeactive(shape, row.getCell(24, row.RETURN_BLANK_AS_NULL).toString(), false);
										}
										
										
										Quality quality = null;
										if(shape != null) {
										if(row.getCell(22, row.RETURN_BLANK_AS_NULL) != null) {
										
												quality  = qualityService.findByShapeAndName(shape,row.getCell(22, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(22, row.RETURN_BLANK_AS_NULL).toString() :null);	
											}
										}
										
										SizeGroup sizeGroup = sizeGroupService.findByNameAndDeactive(row.getCell(25, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(25, row.RETURN_BLANK_AS_NULL).toString() :null, false);
										SettingType settingType =settingTypeService.findByName(row.getCell(29, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(29, row.RETURN_BLANK_AS_NULL).toString() :null);
										Setting setting = settingService.findByName(row.getCell(28, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(28, row.RETURN_BLANK_AS_NULL).toString() :null);
										
										
										if(design == null) {
									
											if(row.getCell(5).toString() !="") {
												
												stone =0;
												carat =0.0;
											
												DesignGroup designGroup =designGroupService.findByName(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() :null);
												Employee designerEmployee = employeeService.findByName(row.getCell(7, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() :null);
												Category category =  categoryService.findByName(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() :null);
												SubCategory subCategory = subCategoryService.findByName(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() :null);
												CollectionMaster collectionMaster =  collectionService.findByName(row.getCell(12, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(12, row.RETURN_BLANK_AS_NULL).toString() :null);
												Concept concept = conceptService.findByName(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(13, row.RETURN_BLANK_AS_NULL).toString() :null);
												SubConcept subConcept = subConceptService.findByName(row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(14, row.RETURN_BLANK_AS_NULL).toString() :null);
												ProductSize productSize = productSizeService.findByName(row.getCell(15, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(15, row.RETURN_BLANK_AS_NULL).toString() :null);
												Employee cadDesigner = employeeService.findByName(row.getCell(17, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(17, row.RETURN_BLANK_AS_NULL).toString() :null);
												Color color = colorService.findByDefColorAndDeactive(true, false);
												Purity purity = purityService.findByDefPurityAndDeactive(true, false);
												MarketTypeMast marketTypeMast =  marketTypeService.findByNameAndDeactive(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() :null, false);
												ProductTypeMast productTypeMast =  productTypeService.findByNameAndDeactive(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(11, row.RETURN_BLANK_AS_NULL).toString() :null, false);
												Party party =  partyService.findByPartyCodeAndDeactive(row.getCell(30, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(30, row.RETURN_BLANK_AS_NULL).toString() :null, false);
												
											
												if(row.getCell(2, row.RETURN_BLANK_AS_NULL) != null) {
													designDate = row.getCell(2).toString();
													designdt = df.parse(designDate);
														
												}
												
												
												String mainStyle = 	(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :null) +
														(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() :"");
									
									
												Double waxtwt = Math.round(((row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0) / 10.50) * 1000.0)/1000.0 ;

												Double waxWtConv = purity.getWaxWtConv() != null ? purity.getWaxWtConv() : 0.0;		
												
												
												
												Design design2 =  new Design();
												design2.setDesignGroup(designGroup);
												design2.setDesignDt(designdt);
												design2.setDesignNo(row.getCell(3, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(3, row.RETURN_BLANK_AS_NULL).toString() :null);
												design2.setRefNo(row.getCell(4, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(4, row.RETURN_BLANK_AS_NULL).toString() :null);
												design2.setMainStyleNo(mainStyle);
												design2.setStyleNo(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :null);
												design2.setVersion(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() :"");
												design2.setDesignerEmployee(designerEmployee);
												design2.setCategory(category);
												design2.setSubCategory(subCategory);
												design2.setCollectionMaster(collectionMaster);
												design2.setConcept(concept);
												design2.setSubConcept(subConcept);
												design2.setProductSize(productSize);
												design2.setSilverWt(row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												design2.setCadDesigner(cadDesigner);
												design2.setCastedPcs(row.getCell(18, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(18, row.RETURN_BLANK_AS_NULL).toString())) :1);
												design2.setRemarks(row.getCell(19, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(19, row.RETURN_BLANK_AS_NULL).toString() :null);
												design2.setCreateDate(new Date());
												design2.setCreatedBy(principal.getName());
												design2.setMarketTypeMast(marketTypeMast);
												design2.setProductTypeMast(productTypeMast);
												design2.setVendor(party);
												design2.setWaxWt(waxtwt);
												design2.setVendorStyle(row.getCell(31, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(31, row.RETURN_BLANK_AS_NULL).toString() :null);
												design2.setTotStone(row.getCell(26, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(26, row.RETURN_BLANK_AS_NULL).toString())) :0);
												design2.setTotCarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												design2.setDefaultImage(mainStyle+".jpg");
												design2.setImage1(mainStyle+".jpg");
												design2.setDefImgChk(1);
												save(design2);
											
												
												DesignMetal designMetal =  new DesignMetal();
												designMetal.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
												designMetal.setMainMetal(true);
												designMetal.setMetalPcs(row.getCell(18, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(18, row.RETURN_BLANK_AS_NULL).toString())) :1);
												designMetal.setSilverWt(row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												designMetal.setPerPcSilverWt(row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												designMetal.setPurity(purity);
												designMetal.setColor(color);
												designMetal.setPerPcWaxWt(waxtwt);
												designMetal.setWaxWt(waxtwt);
												designMetal.setDesign(design2);
												designMetal.setCreateDate(new Date());
												designMetal.setCreatedBy(principal.getName());
												designMetal.setPerPcMetalWeight(Math.round((waxWtConv * waxtwt)*1000.0)/1000.0);
												designMetalService.save(designMetal);
												
												if(stoneType != null) {
												
												DesignStone designStone = new DesignStone();
												designStone.setStoneType(stoneType);
												designStone.setShape(shape);
												designStone.setQuality(quality);
												designStone.setSize(stoneChart != null ? stoneChart.getSize() :null);
												designStone.setSieve(row.getCell(24, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(24, row.RETURN_BLANK_AS_NULL).toString() :null);
												designStone.setSizeGroup(sizeGroup);
												designStone.setStone(row.getCell(26, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(26, row.RETURN_BLANK_AS_NULL).toString())) :0);
												designStone.setCarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												designStone.setMcarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												designStone.setSetting(setting);
												designStone.setSettingType(settingType);
												designStone.setDiaCateg("");
												designStone.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
												
												designStone.setCreateDate(new Date());
												designStone.setCreatedBy(principal.getName());
												
												designStone.setDesign(design2);
												designStoneService.save(designStone);
												stone +=designStone.getStone();
												carat +=designStone.getCarat();
												
												}
												
												styleId = design2.getId();
												
												
											}else {
												
											//	StoneChart stoneChart = stoneChartService.findByShapeAndSieveAndDeactive(shape, row.getCell(24, row.RETURN_BLANK_AS_NULL).toString(), false);
												
												if(stoneType != null) {
												DesignStone designStone = new DesignStone();
												designStone.setStoneType(stoneType);
												designStone.setShape(shape);
												designStone.setQuality(quality);
												designStone.setSize(stoneChart != null ? stoneChart.getSize() :null);
												designStone.setSieve(row.getCell(24, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(24, row.RETURN_BLANK_AS_NULL).toString() :null);
												designStone.setSizeGroup(sizeGroup);
												designStone.setStone(row.getCell(26, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(26, row.RETURN_BLANK_AS_NULL).toString())) :0);
												designStone.setCarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												designStone.setSetting(setting);
												designStone.setSettingType(settingType);
												designStone.setDiaCateg("");
												designStone.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
												designStone.setMcarat(row.getCell(27, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(27, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
												
												designStone.setCreateDate(new Date());
												designStone.setCreatedBy(principal.getName());
												
												designStone.setDesign(findOne(styleId));
												designStoneService.save(designStone);
												
												stone +=designStone.getStone();
												carat +=designStone.getCarat();
												}
												
											}
										
											Design ds = findOne(styleId);
											ds.setTotCarat(Math.round((carat)*1000.0)/1000.0);
											ds.setTotStone(stone);
											save(ds);
										
									}

										
									}

								workbook.close();
								
						    }
				    	
				    	retVal="1";
				    	   
				    }
				    
				    
				    
				    
				    
				   
					}
				
				}catch (IOException e) {
					
					e.printStackTrace();
				}
			
			
			
			return retVal;
			
			
			
			
			
			
		}
		
		}
	

	@Override
	public String designAutofill(String mainStyleNo, Integer partyId) {
		// TODO Auto-generated method stub
		
		Integer limit = 15;
		
		if(mainStyleNo.length() >= 5){
			limit = 100;
		}
		
		
		Page<Design> designList = findByMainStyleNo(limit,0,"mainStyleNo", "ASC", mainStyleNo.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (Design design : designList) {
			if(partyId !=null && partyId>0 && design.getExclusiveClient().equals(true) && !design.getParty().getId().equals(partyId)) {
				continue;
			}
			sb.append("\"")
				.append(design.getMainStyleNo())
				.append("\", ");
		}
	

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}

	@Override
	public String designAutofillForClientRef(String mainStyleNo, Integer partyId) {
		// TODO Auto-generated method stub
		Integer limit = 15;
		
		if(mainStyleNo.length() >= 5){
			limit = 100;
		}
		
		
		Page<Design> designList = findByMainStyleNo(limit,0,"mainStyleNo", "ASC", mainStyleNo.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (Design design : designList) {
			sb.append("\"")
				.append(design.getMainStyleNo())
				.append("\", ");
		}
	

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}

	@Override
	public String desingNoAutoGeneration(Design design, Principal principal) {
		Category category1 = categoryService.findOne(design.getCategory().getId());
		CollectionMaster collectionMaster = collectionService.findOne(design.getCollectionMaster().getId());
		Party party1 = partyService.findOne( design.getVendor().getId());
		
		String category = category1.getCategCode().toUpperCase().substring(0, 2);
		String collection = collectionMaster.getCollectionCode().toUpperCase().substring(0, 2);
		String party = party1.getPartyCode().toUpperCase().substring(0, 2);
		
		
		String newStyle=autoStyleParameterService.getautoStyleNo(category1, collectionMaster, party1);
		
		Design design2 = new Design();
		
		design2.setMainStyleNo(newStyle);
		design2.setDesignNo(newStyle);
		design2.setStyleNo(newStyle);
		design2.setDesignDt(new Date());
		design2.setCreatedBy(principal.getName());
		design2.setCreateDate(new Date());
		design2.setVersion("");
		design2.setCollectionMaster(collectionMaster);
		design2.setCategory(category1);
		design2.setVendor(party1);
		
		
		save(design2);
		
		
		
		
		
		//Design Metal
		
		DesignMetal designMetal =new DesignMetal();
		designMetal.setColor(colorService.findByDefColorAndDeactive(true, false));
		designMetal.setCreateDate(new Date());
		designMetal.setCreatedBy(principal.getName());
		designMetal.setDesign(design2);
		designMetal.setMainMetal(true);
		designMetal.setLossPerc(15.0);
		designMetal.setMetalPcs(1);
		designMetal.setPartNm(lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", "Main Part", false));
		designMetal.setPurity(purityService.findByDefPurityAndDeactive(true, false));
		designMetalService.save(designMetal);
		
		/*
		 * AutoStyleParameter autoStyleParameter =
		 * autoStyleParameterService.findByCategoryAndCollectionMasterAndDeactive(design
		 * .getCategory(), design.getCollectionMaster(), false); if(autoStyleParameter
		 * != null) {
		 * 
		 * int Lastno =autoStyleParameter.getLastNo()+1;
		 * 
		 * String number= StringUtil.toZeroPaddedString(Lastno, 4, 4);
		 * 
		 * String newStyle ="LD"+category+number+collection+party;
		 * 
		 * autoStyleParameter.setLastNo(Lastno);
		 * autoStyleParameterService.save(autoStyleParameter);
		 * 
		 * Design design3 = findByMainStyleNoAndDeactive(newStyle, false); if(design3 !=
		 * null) {
		 * 
		 * 
		 * 
		 * return "Serial number already exist, Please try again"; }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 */ /*
			 * else {
			 * 
			 * String lastNo = "0001";
			 * 
			 * String newStyle ="LD"+category+"0001"+collection+party; Design design2 = new
			 * Design(); design2.setMainStyleNo(newStyle); design2.setDesignNo(newStyle);
			 * design2.setStyleNo(newStyle); design2.setDesignDt(new Date());
			 * design2.setCreatedBy(principal.getName()); design2.setCreateDate(new Date());
			 * design2.setVersion(""); design2.setCollectionMaster(collectionMaster);
			 * design2.setCategory(category1); design2.setVendor(party1); save(design2);
			 * 
			 * AutoStyleParameter autoStyleParameter2 = new AutoStyleParameter();
			 * 
			 * autoStyleParameter2.setCategory(category1);
			 * autoStyleParameter2.setCollectionMaster(collectionMaster);
			 * autoStyleParameter2.setLastNo(Integer.parseInt(lastNo));
			 * autoStyleParameter2.setCreatedBy(principal.getName());
			 * autoStyleParameter2.setCreatedDate(new Date());
			 * autoStyleParameterService.save(autoStyleParameter2);
			 * 
			 * 
			 * 
			 * //Design Metal
			 * 
			 * DesignMetal designMetal =new DesignMetal();
			 * designMetal.setColor(colorService.findByDefColorAndDeactive(true, false));
			 * designMetal.setCreateDate(new Date());
			 * designMetal.setCreatedBy(principal.getName());
			 * designMetal.setDesign(design2); designMetal.setMainMetal(true);
			 * designMetal.setLossPerc(15.0); designMetal.setMetalPcs(1);
			 * designMetal.setPartNm(lookUpMastService.
			 * findByNameAndFieldValueAndDeactive("Design Part", "Main Part", false));
			 * designMetal.setPurity(purityService.findByDefPurityAndDeactive(true, false));
			 * designMetalService.save(designMetal);
			 * 
			 * }
			 */
		
		
		return "added";
	}
	

	private static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}

		return isEmpty;
	}
	
}
