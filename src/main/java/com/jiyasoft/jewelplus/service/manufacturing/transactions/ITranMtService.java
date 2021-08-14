package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface ITranMtService {


	//------- two method for readyBag------>>>>>>>>>
	public Page<TranMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public Page<TranMt> searchAllByDepartment(Integer limit, Integer offset, String sort,
			String order, String name,Integer deptId);
	
	public Page<TranMt> getBagsForCosting(Integer limit, Integer offset,String sort, String order, String name,Integer deptId,Boolean partyFlg,Integer partyId);
	
	public Page<TranMt> getBagsForJobIss(Integer limit, Integer offset,String sort, String order, String name,Integer deptId, Date date2);

	public List<TranMt> getBagsForMultiComponentAddtion(Integer deptId);
	
	public void save(TranMt tranMt);

	public void delete(int id);

	
	public TranMt findOne(int id);

	List<TranMt> findByBagMt(BagMt bagMt);

	public TranMt findByBagMtAndDeptToAndCurrStkAndPendApprovalFlg(BagMt bagMt,Department departmentTo,Boolean currStk,Boolean pendApprovalFlg);

	public TranMt findByBagMtAndDepartmentAndCurrStk(BagMt bagMt,
			Department department, Boolean currStk);
	
	public TranMt findByBagMtCurrStk(BagMt bagMt, Boolean currStk);
	
	public List<TranMt> findByBagMtAndCurrStk(BagMt bagMt, Boolean currStk);
	
	public List<TranMt> findByDepartmentAndCurrStk(Department department, Boolean currStk);
	
	public List<TranMt> findByDeptIdAndCostDt(Integer deptId);
	
	public List<Object[]> getBagsForCasting(Department department,Purity purity,Color color);
	
	public TranMt findByDepartmentAndDeptFromAndBagMtAndCurrStk(Department department,Department deptFrom,BagMt bagMt,Boolean currStk);
	
	public TranMt findByIdAndCurrStk(Integer id,Boolean currStk);
	
	public Double getTotalTranQtys(Integer orderMtId);

	public TranMt findByBagMtIdAndCurrStk(Integer bagId, Boolean currStk);
	
	public String setOpeningTransfer(String bagIds,Principal principal,TranMt tranMt);
	
	public String transfer(String vBagNo,Integer vDepartmentId,Integer vDepartmentToId,Principal principal,
			String remark, Date tranDate,Boolean trfApproval);
	
	
	public String pendingApprovaltransfer(String vBagNo,Integer vDepartmentToId,Principal principal,
			String remark, Date tranDate);
	
	public String notApprovalAndReturn(String vBagNo,Integer vDepartmentToId,Principal principal,
			String remark, Date tranDate,Boolean trfApproval);
	
	
	public TranMt findByRefMtIdAndCurrStk(Integer refMtId,Boolean currStk);	
	
	public Page<TranMt> rejectionRecastList(Integer limit, Integer offset,String sort, String order, String name,Integer deptId);
	
	public List<TranMt> findByRefMtId(Integer refMtid);
	
	public String bagRollBack(Integer mtId);
	
	public TranMt getBagForExportClose(Integer bagId);
	
	public List<TranMt> getTranMtListForExportClose(List<Integer> bagList);
	
	public 	TranMt findByBagMtAndPendApprovalFlgAndCurrStk(BagMt bagMt,Boolean pendApprovalFlg, Boolean currStk);

	
 	public TranMt findByBagMtCurrStkNew(BagMt bagMt, Boolean currStk);
	
 	public String transferForReadybag(String vBagNo,Integer vDepartmentId,Integer vDepartmentToId,Principal principal,
			String remark, Date tranDate,Boolean trfApproval);
 	
 	
 	public List<TranMt> getBagsNotCloseByDepartment(Integer deptId);
 	
 	public String displayTranDetails(String BagNo,Integer departmentId,TranMt tranMt,String uploadFilePath);
 	
 	public String multiCompAdditionList(Integer deptId);
 	
 	public List<TranMt> findByDepartmentAndCurrStkAndPendApprovalFlg(Department department, Boolean currStk,Boolean pendApprovalFlg);
 	
 	public String departmentstockList(Integer limit,Integer offset,String sort, String order, String name,Integer deptId);
 	
 	public  String costingTransferListing(Integer limit, Integer offset,String sort, String order, String name,Integer deptId,Boolean partyFlg,Integer partyId);
 	
 	
 	public String rejectionTranMtList(Integer limit, Integer offset, String sort, String order, String search);
 	
 	public String getBagsForJobIssMt(Integer limit, Integer offset,String sort, String order, String search,Integer deptId, String invDt) throws ParseException;
 	
}
