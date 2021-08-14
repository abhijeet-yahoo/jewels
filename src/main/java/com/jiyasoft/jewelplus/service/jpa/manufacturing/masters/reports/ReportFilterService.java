package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters.reports;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFormat;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.reports.IReportFilterRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.reports.IReportFormatRepository;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;


@Service
@Repository
@Transactional
public class ReportFilterService implements IReportFilterService {

	@Autowired
	private IReportFormatRepository reportFormatRepository;
	
	@Autowired
	private IReportFilterRepository reportFilterRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	public List<ReportFilter> findAll() {
		return reportFilterRepository.findAll();
	}

	@Override
	public Page<ReportFilter> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return reportFilterRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(ReportFilter reportFilter) {
		reportFilterRepository.save(reportFilter);
	}


	@Override
	public Long count() {
		return reportFilterRepository.count();
	}

	@Override
	public ReportFilter findOne(int id) {
		return reportFilterRepository.findOne(id);
	}

	@Override
	public ReportFilter findByName(String reportFilterName) {
		return reportFilterRepository.findByName(reportFilterName);
	}

	
	
	List<String> listLevel1 =  new ArrayList<String>();
	List<String> listLevel2 =  new ArrayList<String>();
	List<String> listLevel3 =  new ArrayList<String>();
	
	@Override
	public List<String> popLevelOneList(String dbGroupVal) {
		listLevel1.removeAll(listLevel1);
		if(listLevel1.size() == 0 ){
			if(dbGroupVal != null){
				String dbGroupArr[] = dbGroupVal.split(",");
				for(int i=0;i<dbGroupArr.length;i++){
					listLevel1.add(dbGroupArr[i]);
				}
			}
			
		}
		return listLevel1;
	}

	
	@Override
	public List<String> popLevelTwoList(String dbGroupVal) {
		listLevel2.removeAll(listLevel2);
		if(listLevel2.size() == 0){
			if(dbGroupVal != null){
				String dbGroupArr[] = dbGroupVal.split(",");
				for(int i=0;i<dbGroupArr.length;i++){
					listLevel2.add(dbGroupArr[i]);
				}
			}
		}
		return listLevel2;
	}

	
	@Override
	public List<String> popLevelThreeList(String dbGroupVal) {
		listLevel3.removeAll(listLevel3);
		if(listLevel3.size() == 0){
			if(dbGroupVal != null){
				String dbGroupArr[] = dbGroupVal.split(",");
				for(int i=0;i<dbGroupArr.length;i++){
					listLevel3.add(dbGroupArr[i]);
				}
			}
		}
		return listLevel3;
	}
	
	
	
	
	//---------on change---------//
	
	
	@Override
	public String onChangeLevelOne(String group) {
		
		System.out.println("level1======"+group);
		
		StringBuilder sb = new StringBuilder();
		
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(listLevel1);
		
		String grpArr[] = group.split(",");
		
		for(int i=0;i<grpArr.length;i++){
			tempList.remove(grpArr[i]);
		}
		
		sb.append("<select id=\"image1\" name=\"image1\" class=\"form-control\"onChange=\"javascript:popLevelTwo(this.value);popLevelThree(this.value);\">");
		sb.append("<option value=\"\">- Select Level 1 -</option>");
		
		for(String list:tempList){
			sb.append("<option value=\"").append(list).append("\">").append(list).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
		
	}
	
	
	@Override
	public String onChangeLevelTwo(String group) {
		System.out.println("level2======"+group);
		StringBuilder sb = new StringBuilder();
		
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(listLevel2);
		String grpArr[] = group.split(",");
		
		for(int i=0;i<grpArr.length;i++){
			tempList.remove(grpArr[i]);
		}
		
		sb.append("<select id=\"image2\" name=\"image2\" class=\"form-control\"onChange=\"javascript:popLevelOne(this.value);popLevelThree(this.value);\">");
		sb.append("<option value=\"\">- Select Level 2 -</option>");
		
		for(String list:tempList){
			sb.append("<option value=\"").append(list).append("\">").append(list).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
		
	}

	

	@Override
	public String onChangeLevelThree(String group) {
		System.out.println("level3======"+group);
		StringBuilder sb = new StringBuilder();
		
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(listLevel3);
		String grpArr[] = group.split(",");
		
		for(int i=0;i<grpArr.length;i++){
			tempList.remove(grpArr[i]);
		}
		
		sb.append("<select id=\"image3\" name=\"image3\" class=\"form-control\"onChange=\"javascript:popLevelOne(this.value);popLevelTwo(this.value);\">");
		sb.append("<option value=\"\">- Select Level 3 -</option>");
		
		for(String list:tempList){
			sb.append("<option value=\"").append(list).append("\">").append(list).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
		
	}
	@Override
	public String getWipSummary(String partyCond, String orderTypeCond,
			String orderCond, String fromOrderDate, String toOrderDate,
			String fromDispatchDate, String toDispatchDate,String departmentCond,String pWipFormat,Principal principal,String customerTypeCond,
			String regionCond,String divisionCond) {
		
	if(pWipFormat.equalsIgnoreCase("wipSummary")) {
		
		Boolean ordValFlg=false;
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("SUPER")) {
			
			ordValFlg=true;
		}
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipsummary(?,?,?,?,?,?,?,?,?,?,?,?) }");
		query.setParameter(1, partyCond);
		query.setParameter(2, orderTypeCond);
		query.setParameter(3, orderCond);
		query.setParameter(4, fromOrderDate);
		query.setParameter(5, toOrderDate);
		query.setParameter(6, fromDispatchDate);
		query.setParameter(7, toDispatchDate);
		query.setParameter(8, departmentCond);
		query.setParameter(9, ordValFlg);
		query.setParameter(10, customerTypeCond);
		query.setParameter(11, regionCond);
		query.setParameter(12, divisionCond);
		
		
		List<Object[]> objects = query.getResultList();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipsummarystructure(?,?,?,?,?,?,?,?) }");
		queryheader.setParameter(1, partyCond);
		queryheader.setParameter(2, orderTypeCond);
		queryheader.setParameter(3, orderCond);
		queryheader.setParameter(4, fromOrderDate);
		queryheader.setParameter(5, toOrderDate);
		queryheader.setParameter(6, fromDispatchDate);
		queryheader.setParameter(7, toDispatchDate);
		queryheader.setParameter(8, departmentCond);
		
		List<Object[]> objectHeader = queryheader.getResultList();

		Map<String, String> mapList = new LinkedHashMap<String, String>();
		 String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 for(Object[] list:objects){
			 int i=0;
			 
			 sb.append("{");
				for(Object element : list){
					
					Object xx =objectHeader.get(i);
					
					 mapList.put(xx.toString(), element !=null?element.toString():"");
					 i++;
				}
				
				
				 StringBuilder sb1 = new StringBuilder();
				
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb1.append("\""+map.getKey()+"\":\"")
					 	 .append(map.getValue())
					 .append("\",");
				}
				
				
				sb.append(sb1.indexOf(",") != -1 ? sb1.substring(0, sb1.length() - 1):sb);
				
				
				
				sb.append("},");
		
			 
			 
			 
		 }
		
		 str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		
			
		return str;
		
	}else if(pWipFormat.equalsIgnoreCase("wipClientSummary")) {
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipclientsummary(?,?,?,?,?,?,?,?) }");
		query.setParameter(1, partyCond);
		query.setParameter(2, orderTypeCond);
		query.setParameter(3, orderCond);
		query.setParameter(4, fromOrderDate);
		query.setParameter(5, toOrderDate);
		query.setParameter(6, fromDispatchDate);
		query.setParameter(7, toDispatchDate);
		query.setParameter(8, departmentCond);
		
		List<Object[]> objects = query.getResultList();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipclientsummarystructure(?,?,?,?,?,?,?,?) }");
		queryheader.setParameter(1, partyCond);
		queryheader.setParameter(2, orderTypeCond);
		queryheader.setParameter(3, orderCond);
		queryheader.setParameter(4, fromOrderDate);
		queryheader.setParameter(5, toOrderDate);
		queryheader.setParameter(6, fromDispatchDate);
		queryheader.setParameter(7, toDispatchDate);
		queryheader.setParameter(8, departmentCond);
		
		List<Object[]> objectHeader = queryheader.getResultList();

		Map<String, String> mapList = new LinkedHashMap<String, String>();
		 String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 for(Object[] list:objects){
			 int i=0;
			 
			 sb.append("{");
				for(Object element : list){
					
					Object xx =objectHeader.get(i);
					
					 mapList.put(xx.toString(), element !=null?element.toString():"");
					 i++;
				}
				
				
				 StringBuilder sb1 = new StringBuilder();
				
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb1.append("\""+map.getKey()+"\":\"")
					 	 .append(map.getValue())
					 .append("\",");
				}
				
				
				sb.append(sb1.indexOf(",") != -1 ? sb1.substring(0, sb1.length() - 1):sb);
				
				
				
				sb.append("},");
		
			 
			 
			 
		 }
		
		 str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		
			
		return str;
		
	}else if(pWipFormat.equalsIgnoreCase("styleWip")) {
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_styleWisewip(?,?,?,?,?,?,?,?) }");
		query.setParameter(1, partyCond);
		query.setParameter(2, orderTypeCond);
		query.setParameter(3, orderCond);
		query.setParameter(4, fromOrderDate);
		query.setParameter(5, toOrderDate);
		query.setParameter(6, fromDispatchDate);
		query.setParameter(7, toDispatchDate);
		query.setParameter(8, departmentCond);
		
		List<Object[]> objects = query.getResultList();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_stylewisewipstructure() }");
		
		List<Object[]> objectHeader = queryheader.getResultList();

		Map<String, String> mapList = new LinkedHashMap<String, String>();
		 String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 for(Object[] list:objects){
			 int i=0;
			 
			 sb.append("{");
				for(Object element : list){
					
					
					
					Object xx =objectHeader.get(i);
					
					
					 mapList.put(xx.toString(), element !=null?element.toString():"");
					 i++;
				}
				
				
				 StringBuilder sb1 = new StringBuilder();
				
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb1.append("\""+map.getKey()+"\":\"")
					 	 .append(map.getValue())
					 .append("\",");
				}
				
				
				sb.append(sb1.indexOf(",") != -1 ? sb1.substring(0, sb1.length() - 1):sb);
				
				
				
				sb.append("},");
		
			 
			 
			 
		 }
		
		 str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		
			
		return str;
		
	}else if(pWipFormat.equalsIgnoreCase("selStyleWip")) {
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_selectedStyleWip(?,?,?,?,?,?,?,?) }");
		query.setParameter(1, partyCond);
		query.setParameter(2, orderTypeCond);
		query.setParameter(3, orderCond);
		query.setParameter(4, fromOrderDate);
		query.setParameter(5, toOrderDate);
		query.setParameter(6, fromDispatchDate);
		query.setParameter(7, toDispatchDate);
		query.setParameter(8, departmentCond);
		
		List<Object[]> objects = query.getResultList();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_stylewisewipstructure() }");
		
		List<Object[]> objectHeader = queryheader.getResultList();

		Map<String, String> mapList = new LinkedHashMap<String, String>();
		 String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 for(Object[] list:objects){
			 int i=0;
			 
			 sb.append("{");
				for(Object element : list){
					
					
					
					Object xx =objectHeader.get(i);
					
					
					 mapList.put(xx.toString(), element !=null?element.toString():"");
					 i++;
				}
				
				
				 StringBuilder sb1 = new StringBuilder();
				
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb1.append("\""+map.getKey()+"\":\"")
					 	 .append(map.getValue())
					 .append("\",");
				}
				
				
				sb.append(sb1.indexOf(",") != -1 ? sb1.substring(0, sb1.length() - 1):sb);
				
				
				
				sb.append("},");
		
			 
			 
			 
		 }
		
		 str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		
			
		return str;
	}
		
	return null;	
	}

	@Override
	public String getWipDetail(Integer sordMtid) {
	List<Object[]> objects =new ArrayList<Object[]>();
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipdetail(?) }");
		query.setParameter(1, sordMtid);
		objects = query.getResultList();
		
		
		System.out.println("objj   "+objects.size());
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		 for(Object[] list:objects){
				
				sb.append("{\"invNo\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"ordQty\":\"")
			     .append(list[2] != null ? list[2] : "")
			     .append("\",\"purity\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"color\":\"")
			     .append(list[4] != null ? list[4] : "")
			     .append("\",\"styleNo\":\"")
			     .append(list[6] != null ? list[6] : "")
			     .append("\",\"grinding\":\"")
				 .append(list[8] != null ? list[8] : "")
			     .append("\",\"filling\":\"")
				 .append(list[9] != null ? list[9] : "")
			     .append("\",\"pol\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"waxSet\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"wax\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"casting\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\",\"readyExp\":\"")
				 .append(list[14] != null ? list[14] : "")
				 .append("\",\"prepol\":\"")
				 .append(list[25] != null ? list[25] : "")
				 .append("\",\"balQty\":\"")
				 .append(list[30] != null ? list[30] : "")
	             .append("\",\"metalSet\":\"")
				 .append(list[18] != null ? list[18] : "")
				 .append("\",\"rej\":\"")
				 .append(list[19] != null ? list[19] : "")
				 .append("\",\"exp\":\"")
				 .append(list[22] != null ? list[22] : "")
				 .append("\",\"cancel\":\"")
				 .append(list[24] != null ? list[24] : "")
				 .append("\",\"sordMtId\":\"")
				 .append(list[1] != null ? list[1] : 0)
				 .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			System.out.println("str "+str);
		return str;
	}

	@Override
	public List<ReportFormat> findByFilterForm(String reportNm) {
		// TODO Auto-generated method stub
		return reportFormatRepository.findByFilterForm(reportNm);
	}

	@Override
	public Map<String, String> getFilterFormList(String reportNm) {
		Map<String, String> filterFormMap = new LinkedHashMap<String, String>();
		List<ReportFormat> reportFormats = findByFilterForm(reportNm);
		
		for (ReportFormat reportFormat : reportFormats) {
			filterFormMap.put(reportFormat.getReportNm(), reportFormat.getReportHeading());
		}
		return filterFormMap;
	}

	@Override
	public String getWipStatus(String partyCond, String orderTypeCond,
			String orderCond, String departmentCond, String purityCond,
			String colorCond, Integer delayCond,String styleCond,String fromOrderDate,String toOrderDate, String fProdToDate,
			String fProdFromDate,String priorityCond) throws ParseException {

		List<Object[]> objects =new ArrayList<Object[]>();
		
		
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
		/*
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipnew(?,?,?,?,?,?,?,?,?,?,?) }");
		query.setParameter(1, partyCond);
		query.setParameter(2, orderTypeCond);
		query.setParameter(3, orderCond);
		query.setParameter(4, departmentCond);
		query.setParameter(5, styleCond);
		query.setParameter(6, purityCond);
		query.setParameter(7, colorCond);
		query.setParameter(8, delayCond);
		query.setParameter(9, fProdFromDate);
		query.setParameter(10, fProdToDate);
		query.setParameter(11, priorityCond);
		objects = query.getResultList();
		
		*/
		
		
		

		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call Jsp_Vw_BagWiseStatus(?,?,?,?,?,?,?,?,?,?) }");
		query.setParameter(1, fromOrderDate);
		query.setParameter(2, toOrderDate);
		query.setParameter(3, partyCond);
		query.setParameter(4, orderTypeCond);
		query.setParameter(5, orderCond);
		query.setParameter(6, styleCond);
		query.setParameter(7, departmentCond);
		query.setParameter(8, purityCond);
		query.setParameter(9, colorCond);
		query.setParameter(10, "");
		
		objects = query.getResultList();
		
		
		
		
		String str="";
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		//sb.append("{\"total\":").append(objects.size()).append(",\"data\": [");
		 
		
		
		 for(Object[] list:objects){
			 
			 
			 
			 String	forderDate="";
			 String	fprodDate="";
			 String	fcancelDate="";
			 String	frecDate="";
			 String prodDate="";
			 
			 if(list[4] != null ){
				 Date ordDate = dfOutput.parse((list[4] != null ? list[4] : "").toString());
				 forderDate = dfInput.format(ordDate);
			 }
			 
			 if(list[40] != null ){
				 Date prodDt = dfOutput.parse((list[40] != null ? list[40] : "").toString());
				 prodDate = dfInput.format(prodDt);
			 }
			 
			 
			 /*if(list[6] != null ){
				 Date prodDate = dfOutput.parse((list[6] != null ? list[6] : "").toString());
				 fprodDate = dfInput.format(prodDate);
			 }
			 
			 
			 if(list[7] != null ){
				 Date cancelDate = dfOutput.parse((list[7] != null ? list[7] : "").toString());
				 fcancelDate = dfInput.format(cancelDate);
			 }
			 
	 
			 if(list[32] != null ){
				 Date recDate=(Date) (list[32] != null ? dfOutput.parse(list[32].toString()) :"");
				 frecDate = dfInput.format(recDate);
			 }
			 
				sb.append("{\"party\":\"")
				.append(list[24] != null ? list[24] : "")
				.append("\",\"invNo\":\"")
				.append(list[2] != null ? list[2] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"ordDate\":\"")
				 .append(forderDate != null ? forderDate : "")
				 .append("\",\"prodDate\":\"")
				 .append(fprodDate != null ? fprodDate : "")
				 .append("\",\"canDt\":\"")
				 .append(fcancelDate != null ? fcancelDate : "")
				 .append("\",\"ordSr\":\"")
				 .append(list[11] != null ? list[11] : "")
				  .append("\",\"mastBal\":\"")
				 .append(list[38] == "1" ? "Master Bal" : "")
				 .append("\",\"ordQty\":\"")
				 .append(list[16] != null ? list[16] : "")
				 .append("\",\"style\":\"")
				 .append(list[37] != null ? list[37] : "")
				 .append("\",\"images\":\"")
				 .append(list[36] != null ? list[36] : "")
				 .append("\",\"kt\":\"")
				 .append(list[25] != null ? list[25] : "")
				 .append("\",\"color\":\"")
				 .append(list[26] != null ? list[26] : "")
				 .append("\",\"size\":\"")
				 .append(list[27] != null ? list[27] : "")
				 .append("\",\"bagNm\":\"")
				 .append(list[30] != null ? list[30] : "")
				 .append("\",\"bagQty\":\"")
				 .append(list[31] != null ? list[31] : "")
				 .append("\",\"recWt\":\"")
				 .append(list[33] != null ? list[33] : "")
				 .append("\",\"loc\":\"")
				 .append(list[39] != null ? list[39] : "")
				 .append("\",\"partyCode\":\"")
				 .append(list[40] != null ? list[40] : "")
				 .append("\",\"locRecDate\":\"")
				 .append(frecDate != null ? frecDate : "")
				 .append("\",\"locDealyDays\":\"")
				 .append(list[35] != null ? list[35] : "")
				 .append("\",\"sordMtId\":\"")
				 .append(list[1] != null ? list[1] : 0)
				  .append("\",\"priority\":\"")
				 .append(list[28] != null ? list[28] :"")
				 .append("\"},");
			}
		 */
		 
			 
			 if(list[33] != null ){
				 Date recDate=(Date) (list[33] != null ? dfOutput.parse(list[33].toString()) :"");
				 frecDate = dfInput.format(recDate);
			 }
			 
			 
				
				sb.append("{\"invNo\":\"")
				.append(list[2] != null ? list[2] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"ordDate\":\"")
				 .append(forderDate != null ? forderDate : "")
				 .append("\",\"ordQty\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"bagNm\":\"")
				 .append(list[26] != null ? list[26] : "")
				 .append("\",\"style\":\"")
				 .append(list[45] != null ? list[45] : "")
				 .append("\",\"dtrefno\":\"")
				 .append(list[15] != null ? list[15] : "")
				 .append("\",\"dtitem\":\"")
				 .append(list[19] != null ? list[19] : "")
				 .append("\",\"size\":\"")
				 .append(list[46] != null ? list[46] : "")
				 .append("\",\"kt\":\"")
				 .append(list[47] != null ? list[47] : "")
				 .append("\",\"color\":\"")
				 .append(list[48] != null ? list[48] : "")
				 .append("\",\"ageing\":\"")
				 .append(list[49] != null ? list[49] : "")
				 .append("\",\"stoneWt\":\"")
				 .append(list[50] != null ? list[50] : "")
				 .append("\",\"colorstnWt\":\"")
				 .append(list[51] != null ? list[51] : "")
				 .append("\",\"metalWt\":\"")
				 .append(list[53] != null ? list[53] : "")
				 .append("\",\"compwt\":\"")
				 .append(list[54] != null ? list[54] : "")
				 .append("\",\"compDt\":\"")
				 .append(list[55] != null ? list[55] : "")
				 .append("\",\"bagQty\":\"")
				 .append(list[25] != null ? list[25] : "")
				 .append("\",\"recWt\":\"")
				 .append(list[29] != null ? list[29] : "")
				 .append("\",\"department\":\"")
				 .append(list[43] != null ? list[43] : "")
				 .append("\",\"partyCode\":\"")
				 .append(list[42] != null ? list[42] : "")
				 .append("\",\"locRecDate\":\"")
				 .append(frecDate != null ? frecDate : "")
				 .append("\",\"sordMtId\":\"")
				 .append(list[0] != null ? list[0] : 0)
//				 .append("\",\"stampinst\":\"")
//				 .append(list[35] != null ? list[35] : "")
				 .append("\",\"ordRef\":\"")
				 .append(list[16] != null ? list[16] : "")
						/*
						 * .append("\",\"designremark\":\"") .append(list[13] != null ? list[13] : "")
						 * .append("\",\"itemremark\":\"") .append(list[14] != null ? list[14] : "")
						 */
				 .append("\",\"confirmDate\":\"")
				 .append(list[37] != null ? list[37] : "")
				 .append("\",\"ordnetwt\":\"")
				 .append(list[38] != null ? list[38] : "")
				 .append("\",\"ordreqcts\":\"")
				 .append(list[39] != null ? list[39] : "")
				 .append("\",\"diarecPcs\":\"")
				 .append(list[59] != null ? list[59] : "")
				 .append("\",\"diabalance\":\"")
				 .append(list[52] != null ? list[52] : "")
				 .append("\",\"orderAging\":\"")
				 .append(list[60] != null ? list[60] : "")
				 .append("\",\"findingDetails\":\"")
				 .append(list[61] != null ? list[61] : "")
				 .append("\",\"productionDate\":\"")
				 .append(prodDate != null ? prodDate : "")
				 .append("\"},");
			}
		 
		 
	
		 
		   
		str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			System.out.println("string    "+str);
			
		return str;
	}

	@Override
	public String getOrderStatus(String partyCond,String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String fromDispatchDate,String toDispatchDate,String departmentCond) throws ParseException {
		// TODO Auto-generated method stub
List<Object[]> objects =new ArrayList<Object[]>();
		
		
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
			
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wiporderstatus(?,?,?,?,?,?,?,?) }");
		
		query.setParameter(1, partyCond);
		query.setParameter(2, orderTypeCond);
		query.setParameter(3, orderCond);
		query.setParameter(4, fromOrderDate);
		query.setParameter(5, toOrderDate);
		query.setParameter(6, fromDispatchDate);
		query.setParameter(7, toDispatchDate);
		query.setParameter(8, departmentCond);
		
		objects = query.getResultList();
		
		
		JSONObject jsonObject =  new JSONObject();
		JSONArray jsonArray =  new JSONArray();
		
		for(Object[] list:objects){
			
			 String	orderDate="";
			 String	dispatchDate="";
			
			 
			 if(list[0] != null ){
				 Date ordDate = dfOutput.parse((list[0] != null ? list[0] : "").toString());
				 orderDate = dfInput.format(ordDate);
			 }
			 
			 if(list[1] != null ){
				 Date dispatchDt = dfOutput.parse((list[1] != null ? list[1] : "").toString());
				 dispatchDate = dfInput.format(dispatchDt);
			 }
			 
			 
			JSONObject jsonObj =  new JSONObject();
			jsonObj.put("orderDate", orderDate);
			jsonObj.put("dispatchDate", dispatchDate);
			jsonObj.put("custOrderNo", list[2] != null ? list[2] : "");
			jsonObj.put("orderNo", list[3] != null ? list[3] : "");
			jsonObj.put("mainStyleno", list[4] != null ? list[4] : "");
			jsonObj.put("dfRefNo", list[5] != null ? list[5] : "");
			jsonObj.put("item", list[6] != null ? list[6] : "");
			jsonObj.put("ordRef", list[7] != null ? list[7] : "");
			jsonObj.put("ordNetwt", list[8] != null ? list[8] : "");
			jsonObj.put("reqcarat", list[9] != null ? list[9] : "");
			jsonObj.put("ktCol", list[26] != null ? list[26] : "");
			jsonObj.put("sizeNm", list[12] != null ? list[12] : "");
			jsonObj.put("qty", list[13] != null ? list[13] : "");
			jsonObj.put("expqty", list[14] != null ? list[14] : "");
			jsonObj.put("ordBal", list[15] != null ? list[15] : "");
			jsonObj.put("compStatus", list[16] != null ? list[16] : "");
			jsonObj.put("itemremark", list[17] != null ? list[17] : "");
			jsonObj.put("stninqty", list[18] != null ? list[18] : "");
			jsonObj.put("diabal", list[19] != null ? list[19] : "");
			jsonObj.put("ordBalnetwt", list[20] != null ? list[20] : "");
			jsonObj.put("ordBalpg", list[21] != null ? list[21] : "");
			jsonObj.put("rate", list[22] != null ? list[22] : "");
			jsonObj.put("deptstatus", list[23] != null ? list[23] : "");
			jsonObj.put("ageing", list[24] != null ? list[24] : "");
			jsonObj.put("stampinst", list[25] != null ? list[25] : "");
			jsonObj.put("partyNm", list[28] != null ? list[28] : "");
			jsonObj.put("finalPrice", list[29] != null ? list[29] : "");
			
			jsonArray.put(jsonObj);
		}	
		
		jsonObject.put("total", objects.size());
		jsonObject.put("rows", jsonArray);
		 	 
			
		return jsonObject.toString();
	}

	@Override
	public String getWipCasting(String partyCond, String orderTypeCond, String orderCond, String fromOrderDate,
			String toOrderDate, String fromDispatchDate, String toDispatchDate, String departmentCond,
			String pWipFormat) {
		// TODO Auto-generated method stub
		
		if(pWipFormat.equalsIgnoreCase("wipCasting")) {
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_clientCastBalsummary(?,?,?,?,?,?,?,?) }");
			query.setParameter(1, partyCond);
			query.setParameter(2, orderTypeCond);
			query.setParameter(3, orderCond);
			query.setParameter(4, fromOrderDate);
			query.setParameter(5, toOrderDate);
			query.setParameter(6, fromDispatchDate);
			query.setParameter(7, toDispatchDate);
			query.setParameter(8, departmentCond);
			
			List<Object[]> objects = query.getResultList();
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_clientCastBalsummarystructure(?,?,?,?,?,?,?,?) }");
			queryheader.setParameter(1, partyCond);
			queryheader.setParameter(2, orderTypeCond);
			queryheader.setParameter(3, orderCond);
			queryheader.setParameter(4, fromOrderDate);
			queryheader.setParameter(5, toOrderDate);
			queryheader.setParameter(6, fromDispatchDate);
			queryheader.setParameter(7, toDispatchDate);
			queryheader.setParameter(8, departmentCond);
			
			List<Object[]> objectHeader = queryheader.getResultList();

			Map<String, String> mapList = new LinkedHashMap<String, String>();
			 String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 for(Object[] list:objects){
				 int i=0;
				 
				 sb.append("{");
					for(Object element : list){
						
						Object xx =objectHeader.get(i);
						
						 mapList.put(xx.toString(), element !=null?element.toString():"");
						 i++;
					}
					
					
					 StringBuilder sb1 = new StringBuilder();
					
					for(Map.Entry<String, String> map : mapList.entrySet()){	
						sb1.append("\""+map.getKey()+"\":\"")
						 	 .append(map.getValue())
						 .append("\",");
					}
					
					
					sb.append(sb1.indexOf(",") != -1 ? sb1.substring(0, sb1.length() - 1):sb);
					
					
					
					sb.append("},");
			
				 
				 
				 
			 }
			
			 str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
			
				
			return str;
			
		}else {
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_clientOrderCastBalsummary(?,?,?,?,?,?,?,?) }");
			query.setParameter(1, partyCond);
			query.setParameter(2, orderTypeCond);
			query.setParameter(3, orderCond);
			query.setParameter(4, fromOrderDate);
			query.setParameter(5, toOrderDate);
			query.setParameter(6, fromDispatchDate);
			query.setParameter(7, toDispatchDate);
			query.setParameter(8, departmentCond);
			
			List<Object[]> objects = query.getResultList();
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_clientOrderCastBalsummarystructure(?,?,?,?,?,?,?,?) }");
			queryheader.setParameter(1, partyCond);
			queryheader.setParameter(2, orderTypeCond);
			queryheader.setParameter(3, orderCond);
			queryheader.setParameter(4, fromOrderDate);
			queryheader.setParameter(5, toOrderDate);
			queryheader.setParameter(6, fromDispatchDate);
			queryheader.setParameter(7, toDispatchDate);
			queryheader.setParameter(8, departmentCond);
			
			List<Object[]> objectHeader = queryheader.getResultList();

			Map<String, String> mapList = new LinkedHashMap<String, String>();
			 String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 for(Object[] list:objects){
				 int i=0;
				 
				 sb.append("{");
					for(Object element : list){
						
						Object xx =objectHeader.get(i);
						
						 mapList.put(xx.toString(), element !=null?element.toString():"");
						 i++;
					}
					
					
					 StringBuilder sb1 = new StringBuilder();
					
					for(Map.Entry<String, String> map : mapList.entrySet()){	
						sb1.append("\""+map.getKey()+"\":\"")
						 	 .append(map.getValue())
						 .append("\",");
					}
					
					
					sb.append(sb1.indexOf(",") != -1 ? sb1.substring(0, sb1.length() - 1):sb);
					
					
					
					sb.append("},");
			
				 
				 
				 
			 }
			
			 str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
			
				
			return str;
			
		}	
	}

	

}
