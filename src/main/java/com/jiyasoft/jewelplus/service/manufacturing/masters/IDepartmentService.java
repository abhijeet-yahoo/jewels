package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;

public interface IDepartmentService {

	public List<Department> findAll();

	public Page<Department> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Department department);

	public void delete(int id);

	public Long count();

	public Department findOne(int id);

	public Department findByName(String name);

	public Map<Integer, String> getDepartmentList();

	public List<Department> findActiveDepartments();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Department> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	//Metal Addition/Deduction
	public Page<Department> findActiveDepartmentForMetalSortByName();

	public Map<Integer, String> getDepartmentListForMetal();
	
	//ComponenetAddition/Deduction
	
	public Page<Department> findActiveDepartmentForCompSortByName();

	public Map<Integer, String> getDepartmentListForComp();
	
	//MetalTransaction
	public Page<Department> findActiveToDepartmentForMetalSortByName(Integer dept);

	public Map<Integer, String> getToDepartmentListForMetal(Integer dept);
	
	public String getToDepartmentListDropDownForMetal(Integer dept) ;
	
	//Component Transaction
	
	public Page<Department> findActiveDepartmentForCompSortByNameNotCentral();

	public Map<Integer, String> getDepartmentListForCompNotCentral();
	
	public Page<Department> findActiveToDepartmentForCompSortByName(Integer dept);

	public Map<Integer, String> getToDepartmentListForComp(Integer dept);
	
	public String getToDepartmentListDropDownForComp(Integer dept) ;
	
	//for OrderFilter page -- location
	
	public List<Department> findByLooseMetalStkOrComponentStkOrStoneStkOrderByNameAsc(Boolean looseMetalStk,Boolean componentStk,Boolean stoneStk);
	
	public Map<Integer, String> LooseMetalStkOrComponentStkOrStoneSt(Boolean looseMetalStk,Boolean componentStk,Boolean stoneStk);
	
	//Emp Stone Production
	public Page<Department> findActiveDepartmentForEmpStoneProductionSortByName();
	
	public Map<Integer, String> getDepartmentListForEmpStoneProduction();
	
	//Emp Pcs Production
	
	public Page<Department> findActiveDepartmentForEmpPcsProductionSortByName();
		
	public Map<Integer, String> getDepartmentListForEmpPcsProduction();
	
	//Pdc Production
	
	public Page<Department> findActiveDepartmentForPdcSortByName();
	
	public Map<Integer, String> getDepartmentListForPdc();
	
	public Page<Department> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	
	public Page<Department> findActiveToDepartmentForStoneTranSortByName(Integer dept);

	public Map<Integer, String> getToDepartmentListForStoneTran(Integer dept);
	
	public String getToDepartmentListDropDownForStoneTran(Integer dept) ;
	
	public Page<Department> findActiveDepartmentForStoneTranSortByName();

	public Map<Integer, String> getDepartmentListForStoneTran();
	
	public Map<Integer, String> getVouTypeDepartmentList();
	
	public Department findByCodeAndDeactive(String code, Boolean deactive);
	
	public String getLocationListDropDown();
	
	public List<Department> findByVouTypeFlgAndDeactiveOrderByNameAsc(Boolean vouTypeFlg,Boolean deactive);
	
	//Reparing Department List
	
	public Page<Department> findActiveReparingDepartment();

	public Map<Integer, String> getReparingDepartmentList();
	
	//Barcode Genaration department  list based on barcode Flg
	
		public List<Department> findByBarcodeFlag(Boolean barcodeFlg);
		
		public Map<Integer, String> getDepartmentListforBarcode();
		
		public String getToDepartmentListDropDownForAll(Integer user) ;

}
