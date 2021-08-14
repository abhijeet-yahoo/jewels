package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HSNMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnRecDt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobRecMtController {

	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ILocationRightsService locationRightsService;

	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IReportFilterService reportFilterService;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;


	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
	
	@Autowired
	private IHSNService hsnService;
	
	@Autowired
	private IJobRecLabDtService jobRecLabDtService;


	@ModelAttribute("jobRecMt")
	public JobRecMt construct() {
		return new JobRecMt();
	}
	
	@ModelAttribute("jobRecDt")
	public JobRecDt constructDt() {
		return new JobRecDt();
	}
	
	@ModelAttribute("jobRecStnDt")
	public JobRecStnDt constructStnDt() {
		return new JobRecStnDt();
	}
	
	@ModelAttribute("jobRecCompDt")
	public JobRecCompDt constructCompDt() {
		return new JobRecCompDt();
	}
	
	@ModelAttribute("jobRecLabDt")
	public JobRecLabDt constructLabDt() {
		return new JobRecLabDt();
	}
	

	@ModelAttribute("jobMtlRecDt")
	public JobMtlRecDt constructMtlDt() {
		return new JobMtlRecDt();
	}

	@ModelAttribute("jobStnRecDt")
	public JobStnRecDt constructStnRecDt() {
		return new JobStnRecDt();
	}

	@ModelAttribute("jobCompRecDt")
	public JobCompRecDt constructCompRecDt() {
		return new JobCompRecDt();
	}
	
	@RequestMapping("/jobRecMt")
	public String jobRecMtNew(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("jobRecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "jobRecMt";
	}
	
	
	@RequestMapping("/jobRecMt/listing")
	@ResponseBody
	public String jobRecMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, 
			Principal principal) {
	

		StringBuilder sb = new StringBuilder();
		Page<JobRecMt> jobRecMts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		jobRecMts = jobRecMtService.findByInvNo(limit, offset, sort, order,search);
	

		sb.append("{\"total\":").append(jobRecMts.getTotalElements()).append(",\"rows\": [");

		for (JobRecMt jobRecMt : jobRecMts) {
			
			sb.append("{\"id\":\"")
					.append(jobRecMt.getId())
					.append("\",\"party\":\"")
					.append((jobRecMt.getParty() != null ? jobRecMt.getParty().getPartyCode() : ""))
					.append("\",\"invNo\":\"")
					.append(jobRecMt.getInvNo())
					.append("\",\"vouType\":\"")
					.append(jobRecMt.getDepartment().getName())
					.append("\",\"invDate\":\"")
					.append(jobRecMt.getInvDateStr())
					.append("\",\"expClose\":\"")
					.append((jobRecMt.getExpClose() != null ? (jobRecMt.getExpClose() ? "Closed" : "Open") : ""))
					.append("\",\"actionClose\":\"")
					.append("<a href='javascript:doCostMtOpen(event,")
					.append(jobRecMt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Open Invoice</a>");
			
					sb.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/jobRecMt/edit/")
					.append(jobRecMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteJobRec(event,")
					.append(jobRecMt.getId())
					.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
				
			
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	@RequestMapping("/jobRecMt/add")
	public String addJobRecMt(Model model,Principal principal) {
		model = populateModel(model, principal); 
		
		return "jobRecMt/add";
	}
	
	private Model populateModel(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		
		model.addAttribute("deptMap", departmentService.getVouTypeDepartmentList());
	
		model.addAttribute("metalMap", metalService.getMetalList());
		
		model.addAttribute("metalMtlMap", metalService.getMetalList());
		model.addAttribute("colorMtlMap", colorService.getColorList());
		model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		model.addAttribute("locationStnMap", locationRightsService.getToLocationListFromUser(user.getId(),"stone"));
		model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));

		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("metalMap", metalService.getMetalList());

		model.addAttribute("metalCompMap", metalService.getMetalList());
		model.addAttribute("colorCompMap", colorService.getColorList());
		model.addAttribute("componentCompMap", componentService.getComponentList());
		model.addAttribute("purityCompMap", purityService.getPurityList());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		ReportFilter reportFilter = reportFilterService.findByName("jobworkreceiveRoport");
		model.addAttribute("xml",reportFilter.getXml());
		
		ReportFilter reportFilter1 = reportFilterService.findByName("jobworkRecNewReport");
		model.addAttribute("xml1",reportFilter1.getXml());
		
		model.addAttribute("hsnMap", hsnService.getHsnList());
		
		return model;
	}
	
	@RequestMapping(value = "/jobRecMt/add", method = RequestMethod.POST)
	public String addEditJobRecMt(@Valid @ModelAttribute("jobRecMt") JobRecMt jobRecMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vHsnId", required = false) Integer vHsnId,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/jobRecMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
				return "jobRecMt/add";	
		}
		
		/*
		 * Boolean invoiceAvailable = true;
		 * 
		 * if (id == null) {
		 * 
		 * invoiceAvailable =
		 * (jobRecMtService.findByInvNoAndDeactive(jobRecMt.getInvNo(), false) == null);
		 * } else { JobRecMt jobRecMt2 = jobRecMtService.findOne(id); if (!
		 * (jobRecMt.getInvNo().equalsIgnoreCase(jobRecMt2.getInvNo())) ) {
		 * invoiceAvailable =
		 * (jobRecMtService.findByInvNoAndDeactive(jobRecMt.getInvNo(), false) == null);
		 * } }
		 * 
		 * if (!invoiceAvailable) {
		 * 
		 * return "jobRecMt/add";
		 * 
		 * }
		 */
		
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date dates = originalFormat.parse(vTranDate);
			
			jobRecMt.setInvDate(dates);
			
			}
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			
			aDate = new java.util.Date();
			jobRecMt.setCreatedBy(principal.getName());
			jobRecMt.setCreatedDate(new java.util.Date());
			jobRecMt.setUniqueId(aDate.getTime());
			jobRecMt.setParty(jobRecMt.getParty());
			
			
			Integer maxSrNo=jobRecMtService.getMaxInvSrno();
			maxSrNo = (maxSrNo == null ? 1 : maxSrNo + 1);
			jobRecMt.setSrNo(maxSrNo);
			
			int si = maxSrNo.toString().length();
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			Integer presentYear = Integer.parseInt(vYear);
			Integer nextYear = presentYear + 1;
			
			String bagSr = null;
			
			switch (si) {
			case 1:
				bagSr = "000"+maxSrNo;
				break;
				
			case 2:
				bagSr = "00"+maxSrNo;
				break;
				
			case 3:
				bagSr = "0"+maxSrNo;
				break;
				
			default:
				bagSr = maxSrNo.toString();
				break;
			}
			
			jobRecMt.setInvNo("REC/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				
			if(jobRecMt.getHsnMast().getId() == null) {
				jobRecMt.setHsnMast(null);
			}
			
		} else {
			
			JobRecMt  jobRecMt2 =jobRecMtService.findOne(id);
			jobRecMt.setSrNo(jobRecMt2.getSrNo());
			jobRecMt.setInvNo(jobRecMt2.getInvNo());
		
			jobRecMt.setModiBy(principal.getName());
			jobRecMt.setModiDate(new java.util.Date());
				
			
				Party party =  partyService.findOne(pPartyIds);
				Department department = departmentService.findOne(pLocationIds);
				
				
				jobRecMt.setParty(party);
				jobRecMt.setDepartment(department);
				
					
				if(vHsnId != null) {
					HSNMast hsnMast = hsnService.findOne(vHsnId);
					jobRecMt.setHsnMast(hsnMast);
				}else {
					jobRecMt.setHsnMast(null);
				}
						
			action = "updated";
	
			retVal = "redirect:/manufacturing/transactions/jobRecMt/edit/"+id+".html";
		
			}
		
		
	
		
		jobRecMtService.save(jobRecMt);

		
		
		  if (action.equals("added")){ 
			  JobRecMt jobRecMtNew =   jobRecMtService.findByUniqueId(jobRecMt.getUniqueId()); 
			  retVal =  "redirect:/manufacturing/transactions/jobRecMt/edit/"+jobRecMtNew.getId()+".html"; }
		 
		 
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		return retVal;

	}
	
	@RequestMapping("/jobRecMt/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		JobRecMt jobRecMt = jobRecMtService.findOne(id);
		model.addAttribute("jobRecMt", jobRecMt);
		model = populateModel(model, principal);

		return "jobRecMt/add";
	}
	
	@RequestMapping("/jobRecMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (jobRecMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			JobRecMt jobRecMt = jobRecMtService.findOne(id);
			if (! (invNo.equalsIgnoreCase(jobRecMt.getInvNo())) ) {
				invoiceAvailable = (jobRecMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}
	
	@RequestMapping("/jobIssMt/pickupListing")
	@ResponseBody
	public String mtPickupListing(Model model,
			@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			Principal principal) throws ParseException {
	

		List<Object[]> objects =new ArrayList<Object[]>();
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_getJobIssMtPickUpList(?,?) }");
		query.setParameter(1, partyId);
		query.setParameter(2, deptId);
		objects = query.getResultList();
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
			 String invDate ="";
			 if(list[2] != null ){
				 Date invDt = dfOutput.parse((list[2] != null ? list[2] : "").toString());
				 invDate = dfInput.format(invDt);
			 }
				
				sb.append("{\"mtId\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"invNo\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"invDate\":\"")
				 .append(invDate != null ? invDate : "")
				 .append("\",\"party\":\"")
				 .append(list[3] != null ? list[3] : "")
				 .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			System.out.println("str "+str);
		return str;

	}
	
	@RequestMapping("	/jobIssDt/pickupListing")
	@ResponseBody
	public String dtPickupListing(Model model,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			Principal principal) throws ParseException {
	

		List<Object[]> objects =new ArrayList<Object[]>();
			
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_getJobIssDtPickUpList(?) }");
		query.setParameter(1, mtId);
		objects = query.getResultList();
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			
				
				sb.append("{\"dtId\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"bagNm\":\"")
				 .append(list[3] != null ? list[3] : "")
				 .append("\",\"pcs\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"mainStyleno\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"adjustedQty\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"balanceQty\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"purity\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"color\":\"")
				 .append(list[10] != null ? list[10] : "")
				 	
				 .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			System.out.println("str "+str);
		return str;

	}
	
		
		@RequestMapping("/jobIssMt/jobWorkReceiveReport")
		@ResponseBody
		public String jobWorkReceiveReport(@RequestParam(value = "mtid", required = false) Integer mtid, Principal principal,
				@RequestParam(value = "xml", required = false) String xml,@RequestParam(value = "opt", required = false) String opt
				) throws SQLException {
		
	
			
			
		
			String retVal = "-1";
			String fileName = "";
			
			Connection conn = null;
			
			
	try {
		
		conn = Utils.getConnection();
			
		
		fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
		
		
		InputStream input = new FileInputStream(new File(fileName));
		java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
		
		parametersMap.put("mtid", mtid);
		
		JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
		
		//------------//--common--PDF--------//
		
		if(opt.equalsIgnoreCase("1")) {
			
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			String fileNm=xml+"-"+System.currentTimeMillis()+".xlsx";
			
			//String fileNm="purc.xlsx";
			File outputFile = new File(fileNm);
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
			//Set configuration as you like it!!
			configuration.setDetectCellType(true);
			configuration.setWhitePageBackground(false);
			configuration.setCollapseRowSpan(false);
			configuration.setAutoFitPageHeight(true);
			configuration.setRemoveEmptySpaceBetweenColumns(true);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			
		
			retVal = fileNm;
		
		}else {
			
			String newFileName = System.currentTimeMillis()+"";
			File file = new File(uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
			file.createNewFile();
			JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
			
			String exportFileName = System.currentTimeMillis()+""+principal.getName();
			Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +newFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
			
			file.delete();
			
			retVal = exportFileName;
			
			
		}
	
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	finally {
		conn.close();
	}	
		return retVal;
	
		}

		
		
		
		@RequestMapping("/jobRecMt/delete/{id}")
		@ResponseBody
		public String jobRecMtDelete(@PathVariable int id, Principal principal) {
			
			String retVal= jobRecMtService.deleteJobRecMtInvoice(id, principal);
			
				return retVal;
			
				
			
				
			}	

		
		
		
		
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
}
