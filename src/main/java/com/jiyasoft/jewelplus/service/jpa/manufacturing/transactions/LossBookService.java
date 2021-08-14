package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpStoneProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LossBook;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ILossBookRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpStoneProductionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILossBookService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@Service
@Repository
@Transactional
public class LossBookService implements ILossBookService {
	
	
	@Autowired
	private ILossBookRepository lossBookRepository;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IEmpStoneProductionService empStoneProductionService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	
	@Autowired
	private IPurityService purityService;
	
	
	@Autowired
	private ITranDtService tranDtService;

	@Override
	public void save(LossBook lossBook) {
		// TODO Auto-generated method stub
		lossBookRepository.save(lossBook);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		LossBook lossBook = lossBookRepository.findOne(id);
		lossBook.setDeactive(true);
		lossBook.setDeactiveDt(new Date());
		save(lossBook);
	}

	@Override
	public String lossBookSave(LossBook lossBook,String empStoneData,Principal principal, Date tranDate) {

		String retVal="1";
	
		
		
		
		BagMt bagMt = bagMtService.findByName(lossBook.getBagMt().getName());
		Department department = departmentService.findOne(lossBook.getDepartment().getId());
		
		if(department.getPcsProd().equals(true)){
			if(lossBook.getEmployee().getId()==null){
				return "Error : Employee Compulsory";
			}
		}
		
		if(department.getStoneProd().equals(true)){
			if(Strings.isNullOrEmpty(empStoneData)){
				return "Error :Stone Production Compulsory";
			}
		}
		
		TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		Purity purity =purityService.findByName(lossBook.getPurity().getName());
		
		if(tranMtt !=null){
			
			tranMtt.setRecWt( Math.round((tranMtt.getRecWt()-lossBook.getLoss())*1000.0)/1000.0);
			
			TranMetal tranMetal = tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), lossBook.getPartNm().getId(), true);
			tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight()-lossBook.getLoss())*1000.0)/1000.0);
			
			tranMtService.save(tranMtt);
			tranMetalService.save(tranMetal);
			
			lossBook.setCreatedBy(principal.getName());
			lossBook.setCreatedDate(new Date());
			lossBook.setBagMt(bagMt);
			lossBook.setPurity(purity);
			lossBook.setPurityConv(purity.getPurityConv());
			lossBook.setTranMt(tranMtt);
			if(lossBook.getEmployee().getId()==null){
				lossBook.setEmployee(null);
			}
			
			if(lossBook.getProdLabourType().getId()==null){
				lossBook.setProdLabourType(null);
			}
			lossBook.setTrandate(tranDate);
			
			save(lossBook);
							
			
			if(empStoneData !=""){
				
				JSONArray jsonArray = new JSONArray(empStoneData);
				
				
				for(int i=0;i<jsonArray.length();i++){
					JSONObject object = (JSONObject) jsonArray.get(i);
					
					Employee employee =employeeService.findByNameAndDepartmentAndDeactive(object.getString("employee"), department, false);
					if(employee == null){
						retVal="Error : Employee Compulsory";
						break;
					}
					
					
					
					
				}
				
				if(retVal !="1"){
					return retVal;
				}
				
				for(int i=0;i<jsonArray.length();i++){
					EmpStoneProduction empStoneProduction = new EmpStoneProduction();
					
					JSONObject object = (JSONObject) jsonArray.get(i);
					
					
					empStoneProduction.setBagMt(bagMt);
					empStoneProduction.setBagSr(object.getInt("bagSrNo"));
					empStoneProduction.setCarat(object.getDouble("carat"));
					empStoneProduction.setCreatedBy(principal.getName());
					empStoneProduction.setCreatedDt(new Date());
					empStoneProduction.setDepartment(department);
					empStoneProduction.setEmployee(employeeService.findByNameAndDepartmentAndDeactive(object.getString("employee"), department, false));
					empStoneProduction.setLossBook(lossBook);
					empStoneProduction.setPcs(lossBook.getQty());
					empStoneProduction.setRate(object.getDouble("rate"));
					empStoneProduction.setShape(shapeService.findByName(object.getString("shape")));
					empStoneProduction.setQuality(qualityService.findByShapeAndNameAndDeactive(empStoneProduction.getShape(), object.getString("quality"), false));
					empStoneProduction.setSetting(settingService.findByName(object.getString("setting")));
					empStoneProduction.setSettingType(settingTypeService.findByName(object.getString("setType")));
					
					empStoneProduction.setSieve(object.getString("sieve"));
					empStoneProduction.setSize(object.getString("size"));
					empStoneProduction.setSizeGroup(sizeGroupService.findByName(object.getString("sizeGroup")));
					empStoneProduction.setStone(object.getInt("stone"));
					empStoneProduction.setStoneType(stoneTypeService.findByName(object.getString("stoneType")));
					empStoneProduction.setTime(new java.sql.Time(new java.util.Date().getTime()));
					empStoneProduction.setTranMt(tranMtt);
					empStoneProduction.setPartNm(lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", object.getString("partNm"), false));
					empStoneProductionService.save(empStoneProduction);
					

				
				
				}
				
			}
			
		
			
			
			
			
			
		}else{
			return "Bag Not In "+department.getName()+" Department  ";
		}
		
		
		
		return retVal;
	}

	@Override
	public List<LossBook> findByDepartmentAndBagMtAndDeactive(
			Department department, BagMt bagMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return lossBookRepository.findByDepartmentAndBagMtAndDeactive(department, bagMt, deactive);
	}

	@Override
	public List<LossBook> findByTranMtAndDeactive(TranMt tranMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return lossBookRepository.findByTranMtAndDeactive(tranMt, deactive);
	}

	@Override
	public String getPartListForLossBook(Integer departmentId, String bagNo,String uploadFilePath) {

		StringBuilder sb = new StringBuilder();
		
		Department department = departmentService.findOne(departmentId);
		BagMt bagMt =bagMtService.findByName(bagNo);
		TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		Double totCarat = 0.0;
		Integer totStone = 0;
		if(tranMt != null){
			List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
			for (TranDt tranDt : tranDts) {
				totCarat += tranDt.getCarat() !=null ? tranDt.getCarat() :0.0;
				totStone += tranDt.getStone() !=null ? tranDt.getStone() : 0;
			}
			
		}

		String img =bagMt.getOrderDt().getDesign().getDefaultImage();
		
		String styleNo =bagMt.getOrderDt().getDesign().getMainStyleNo();
		
		
		Map<Integer, String> partNmMap = tranMetalService.getLossBookPart(bagMt, department);
		
		sb.append("<select id=\"partNm.id\" name=\"partNm.id\" class=\"form-control\" onchange=\"javascript:popGetPartDetail()\" >");
		sb.append("<option value=\"\">- Select Part Name -</option>");
		
		for (Object key : partNmMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(partNmMap.get(key)).append("</option>");
		}
		sb.append("</select>");
		
		String partData=sb.toString();
		
		TranMetal tranMetal =tranMetalService.findByBagMtIdAndMainMetalAndCurrStk(bagMt.getId(), true, true);
		
		JSONObject object = new JSONObject();
		object.put("partData", partData);
		object.put("imgPath", uploadFilePath+img);
		object.put("styleNo", styleNo);
		object.put("stonProd", department.getStoneProd());
		object.put("defPart", tranMetal.getPartNm().getId());
		object.put("totCarat",Math.round((totCarat)*1000.0)/1000.0);
		object.put("totStone",totStone);
		
	
		return object.toString();
	}

	
	

}
