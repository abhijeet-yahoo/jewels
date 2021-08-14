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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
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
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobIssMtController {

	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	@Autowired
	private IJobIssMtService jobIssMtService;

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
	private ILocationRightsService locationRightsService;

	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IHSNService hsnService;
	
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

	
	@ModelAttribute("jobIssMt")
	public JobIssMt construct() {
		return new JobIssMt();
	}

	@ModelAttribute("jobIssDt")
	public JobIssDt constructDt() {
		return new JobIssDt();
	}

	@ModelAttribute("jobIssStnDt")
	public JobIssStnDt constructStnDt() {
		return new JobIssStnDt();
	}

	@ModelAttribute("jobIssCompDt")
	public JobIssCompDt constructCompDt() {
		return new JobIssCompDt();
	}

	@ModelAttribute("jobIssLabDt")
	public JobIssLabDt constructLabDt() {
		return new JobIssLabDt();
	}

	@ModelAttribute("jobMtlIssDt")
	public JobMtlIssDt constructMtlDt() {
		return new JobMtlIssDt();
	}

	@ModelAttribute("jobStnIssDt")
	public JobStnIssDt constructStnIssDt() {
		return new JobStnIssDt();
	}

	@ModelAttribute("jobCompIssDt")
	public JobCompIssDt constructCompIssDt() {
		return new JobCompIssDt();
	}

	@ModelAttribute("tranMt")
	public TranMt constructTranMt() {
		return new TranMt();
	}

	@RequestMapping("/jobIssMt")
	public String jobIssMtNew(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("jobIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (roleRights == null) {
			return "accesDenied";
		} else {
			model.addAttribute("canAdd", roleRights.getCanAdd());

		}

		return "jobIssMt";
	}

	@RequestMapping("/jobIssMt/listing")
	@ResponseBody
	public String jobIssMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) throws ParseException {

	return jobIssMtService.jobIssMtListing(limit, offset, sort, order, search, principal);

	}

	@RequestMapping("/jobIssMt/add")
	public String addJobIssMt(Model model, Principal principal) {
		model = populateModel(model, principal);

		return "jobIssMt/add";
	}

	private Model populateModel(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());

		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("labourTypeMap", labourTypeService.getLabourTypeList());
		model.addAttribute("settingMap", settingService.getSettingList());
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());
		model.addAttribute("deptMap", departmentService.getVouTypeDepartmentList());
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
		
		ReportFilter reportFilter = reportFilterService.findByName("jobworkissueRoport");
		model.addAttribute("xml",reportFilter.getXml());
		
		ReportFilter reportFilter1 = reportFilterService.findByName("jobworkIssNewReport");
		model.addAttribute("xml1",reportFilter1.getXml());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		model.addAttribute("hsnMap", hsnService.getHsnList());

		return model;
	}

	@RequestMapping(value = "/jobIssMt/add", method = RequestMethod.POST)
	public String addEditJobIssMt(@Valid @ModelAttribute("jobIssMt") JobIssMt jobIssMt, BindingResult result,
			@RequestParam(value = "id") Integer id, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vHsnId", required = false) Integer vHsnId,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/jobIssMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "jobIssMt/add";
		}

		/*
		 * Boolean invoiceAvailable = true;
		 * 
		 * if (id == null) {
		 * 
		 * invoiceAvailable =
		 * (jobIssMtService.findByInvNoAndDeactive(jobIssMt.getInvNo(), false) == null);
		 * } else { JobIssMt jobIssMt2 = jobIssMtService.findOne(id); if
		 * (!(jobIssMt.getInvNo().equalsIgnoreCase(jobIssMt2.getInvNo()))) {
		 * invoiceAvailable =
		 * (jobIssMtService.findByInvNoAndDeactive(jobIssMt.getInvNo(), false) == null);
		 * } }
		 * 
		 * if (!invoiceAvailable) {
		 * 
		 * return "jobIssMt/add";
		 * 
		 * }
		 */
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date dates = originalFormat.parse(vTranDate);
			
			jobIssMt.setInvDate(dates);
			
			}

		if (id == null || id.equals("") || (id != null && id == 0)) {

			aDate = new java.util.Date();
			jobIssMt.setCreatedBy(principal.getName());
			jobIssMt.setCreatedDate(new java.util.Date());
			jobIssMt.setUniqueId(aDate.getTime());
			jobIssMt.setParty(jobIssMt.getParty());
			
			
			Integer maxSrNo=jobIssMtService.getMaxInvSrno();
			maxSrNo = (maxSrNo == null ? 1 : maxSrNo + 1);
			jobIssMt.setSrNo(maxSrNo);
			
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
			
			jobIssMt.setInvNo("ISS/" + (bagSr) + "/" + presentYear+"-"+nextYear);
			
			if(jobIssMt.getHsnMast().getId() == null) {
				jobIssMt.setHsnMast(null);
			}

		} else {
			
			JobIssMt  jobIssMt2 =jobIssMtService.findOne(id);
			jobIssMt.setSrNo(jobIssMt2.getSrNo());
			jobIssMt.setInvNo(jobIssMt2.getInvNo());
			jobIssMt.setModiBy(principal.getName());
			jobIssMt.setModiDate(new java.util.Date());

			
		
				Party party =  partyService.findOne(pPartyIds);
				Department department = departmentService.findOne(pLocationIds);
				
				
				jobIssMt.setParty(party);
				jobIssMt.setDepartment(department);
				
				if(vHsnId != null) {
					HSNMast hsnMast = hsnService.findOne(vHsnId);
					jobIssMt.setHsnMast(hsnMast);
				}else {
					jobIssMt.setHsnMast(null);
				}

			action = "updated";

			retVal = "redirect:/manufacturing/transactions/jobIssMt/edit/" + id + ".html";

		}
		
		
		
		
		jobIssMtService.save(jobIssMt);
		
		  
		  if (action.equals("added")) { 
			  JobIssMt jobIssMtNew =  jobIssMtService.findByUniqueId(jobIssMt.getUniqueId()); 
			  Integer jobIssMtId = 	  jobIssMtNew.getId(); 
			  retVal =  "redirect:/manufacturing/transactions/jobIssMt/edit/"+jobIssMtId+".html"; 
			  }
		 

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/jobIssMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		JobIssMt jobIssMt = jobIssMtService.findOne(id);
		model.addAttribute("jobIssMt", jobIssMt);
		model = populateModel(model, principal);

		return "jobIssMt/add";
	}

	@RequestMapping("/jobIssMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo, @RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (jobIssMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			JobIssMt jobIssMt = jobIssMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(jobIssMt.getInvNo()))) {
				invoiceAvailable = (jobIssMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}
	
	
	
		@RequestMapping("/jobIssMt/jobWorkIssueReport")
		@ResponseBody
		public String jobWorkIssueReport(@RequestParam(value = "mtid", required = false) Integer mtid, Principal principal,
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
		
		
		
		@RequestMapping("/jobIssMt/delete/{id}")
		@ResponseBody
		public String jobIssMtDelete(@PathVariable int id, Principal principal) {
			
			String retVal= jobIssMtService.deleteJobIssMtInvoice(id, principal);
			
				return retVal;
			
				
			
				
			}		
		
	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
