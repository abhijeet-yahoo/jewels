package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalRate;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QPackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IPackMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalRateService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;


@Service
@Repository
@Transactional
public class PackMtService implements IPackMtService {
	
	@Autowired
	private IPackMtRepository packMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPackDtService packDtService;

	@Autowired
	private IPackStnDtService packStnDtService;
	
	@Autowired
	private IPackCompDtService packCompDtService;
	
	@Autowired
	private IPackMetalDtService packMetalDtService;

	@Autowired
	private IPackLabDtService packLabDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IClientWastageService clientWastageService;
	
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IPackMetalRateService packMetalRateService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	
	@Override
	public Page<PackMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		QPackMt qPackMt = QPackMt.packMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression = qPackMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qPackMt.invNo.like("%" + search + "%");
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		}
	
		Page<PackMt> packMtList =(Page<PackMt>) packMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return packMtList;
	}

	@Override
	public Integer getMaxInvSrno() {
		QPackMt qPackMt = QPackMt.packMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qPackMt)
			.where(qPackMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qPackMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public void save(PackMt packMt) {
		// TODO Auto-generated method stub
		packMtRepository.save(packMt);
	}

	@Override
	public void delete(int id) {
		packMtRepository.delete(id);
		
	}

	@Override
	public PackMt findOne(int id) {
		// TODO Auto-generated method stub
		return packMtRepository.findOne(id);
	}

	@Override
	public List<PackMt> findByParty(Party party) {
		// TODO Auto-generated method stub
		return packMtRepository.findByParty(party);
	}

	@Override
	public String deletePackMt(Integer mtId) {
		// TODO Auto-generated method stub
		String retVal="-1";
		
		PackMt packMt = findOne(mtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = packMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
			
			List<PackDt> packDts = packDtService.findByPackMt(packMt);
			
			for (PackDt packDt : packDts) {
				
				if(packDt.getAdjQty()>0) {
					
					return "Can not Delete,Record present in consignment issue or sales invoice";
					
				}else {
					
					List<PackStnDt> packStnDts = packStnDtService.findByPackDt(packDt);
					for(PackStnDt packStnDt :packStnDts) {
						packStnDtService.delete(packStnDt.getId());
						
					}
					
					List<PackMetalDt> packMetalDts = packMetalDtService.findByPackDt(packDt);
					for(PackMetalDt packMetalDt :packMetalDts) {
					packMetalDtService.delete(packMetalDt.getId());
						
					}
					
					List<PackCompDt> packCompDts = packCompDtService.findByPackDt(packDt);
					for(PackCompDt packCompDt :packCompDts) {
						packCompDtService.delete(packCompDt.getId());
						
					}
					
					
					List<PackLabDt> packLabDts = packLabDtService.findByPackDt(packDt);
					for(PackLabDt packLabDt :packLabDts) {
						packLabDtService.delete(packLabDt.getId());
						
					}
					
			
					packDtService.delete(packDt.getId());
					
					StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("PackList", packDt.getId(),true);
					if(stockTran !=null) {
					
						
						
						StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
						stockTran2.setCurrStk(true);
						stockTranService.save(stockTran2);
						
						stockTranService.delete(stockTran.getId());
					}
					
					
				//	StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(packDt.getBarcode(), false, false);
					StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());	
					stockMt.setCurrStk(true);
					stockMtService.save(stockMt);
					
					
				}
				
			}
			
			List<PackMetalRate> metalRates = packMetalRateService.findByPackMt(packMt);
			for (PackMetalRate packMetalRate : metalRates) {
				packMetalRateService.delete(packMetalRate.getId());
			}
			
			delete(packMt.getId());
			
			retVal ="1";
			
		}else {
			
			return "Can Not Delete BackDate Entry";
		
		}
		
		
		return retVal;
	}
	
	
	@Override
	public String marketingReport(Integer mtId,String tranType,String uploadDirecotryPath,String uploadParentDirecotryName,String uploadDirecotryName,
			String tmpUploadImage,String reportXmlDirectoryPath,String reportoutputdirectorypath, Principal principal,String opt) throws SQLException {
		// TODO Auto-generated method stub
		synchronized (this) {	
			String retVal = "-1";
			String fileName = "";
			String subRptPath = "";
			String imgPath = "";
			String barCodePath="";
			
			
			String barcodeFolderNm ="/StockBarcode/" ;
			
			Connection conn = null;
			
			String xml = "";
			
			if(tranType.equalsIgnoreCase("pack")) {
				xml = "marketingPackingList";
			}else if(tranType.equalsIgnoreCase("consig")) {
				xml = "deliveryChallan";
			}else if(tranType.equalsIgnoreCase("consigLooseMetal")) {
				xml = "deliveryChallanLooseMetal";
			}else if(tranType.equalsIgnoreCase("sale")) {
				barcodeFolderNm ="/salesBarcode/" ;
				xml = "salesInvoiceReport";
			}else if(tranType.equalsIgnoreCase("branchTrf")) {
				xml = "branchTrf";
			}else if(tranType.equalsIgnoreCase("fgIss")) {
				xml = "fgStockInvoiceReceive";
			}else if(tranType.equalsIgnoreCase("readyBagTrf")) {
				xml = "readyBagIssInvoiceReport";
			}else if(tranType.equalsIgnoreCase("barcodeMt")) {
				xml = "barcodeSticker";
			}else if(tranType.equalsIgnoreCase("readyBagRet")) {
				xml = "readyBagReturnInvoiceReport";
			}else if(tranType.equalsIgnoreCase("saleRet")) {
				xml = "marketingSalesReturn";
			}else if(tranType.equalsIgnoreCase("stockMeltingTransferRprt")) {
				xml = "stockMeltingInvoiceReceive";
			}else if(tranType.equalsIgnoreCase("saleLooseStn")) {
				xml = "salesInvoiceLooseStnReport";
			}else if(tranType.equalsIgnoreCase("consigLooseStn")) {
				xml = "deliveryChallanLooseStn";
			}else if(tranType.equalsIgnoreCase("saleLooseMetal")) {
				xml = "salesInvoiceLooseMetalReport";
			}else if(tranType.equalsIgnoreCase("repairRet")) {
				xml = "repairReturnToCustomer";
			}else if(tranType.equalsIgnoreCase("fgRet")) {
				xml = "issueToFactoryInvoice";
			}else if(tranType.equalsIgnoreCase("grec")) {
				xml = "marketingGrec";
			}else if(tranType.equalsIgnoreCase("salePlainGold")) {
				barcodeFolderNm ="/salesBarcode/" ;
				xml = "salesInvoicePlainGoldReport";
			}
			
			
			
		
			
		try {
			
			
			conn = Utils.getConnection();
		
		
			
			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			imgPath =     uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
			barCodePath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + barcodeFolderNm ;
			
			
			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
			
			parametersMap.put("mtId", mtId);
			
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("subrptpath", subRptPath);
			parametersMap.put("barCodePath", barCodePath);
			
			
			JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
			
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
			System.out.println(e);
			retVal = "-2";
		} finally {
			conn.close();
		}
		
		return retVal;
	}

}

	@Override
	public String packMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
StringBuilder sb = new StringBuilder();
		
		
		Page<PackMt>packList=null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("packList");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		packList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(packList.getTotalElements()).append(",\"rows\": [");

		for (PackMt packMt : packList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(packMt.getInvDate()));

			sb.append("{\"id\":\"").append(packMt.getId())
			.append("\",\"invNo\":\"")
			.append(packMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(packMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(packMt.getParty().getName())
			.append("\",\"location\":\"")
			.append(packMt.getLocation().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/marketing/transactions/packList/edit/")
								.append(packMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deletePackList(event,");					
								sb.append(packMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(packMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/marketing/transactions/packList/edit/")
					.append(packMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deletePackList(event,")	
						.append(packMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(packMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		
		return str;
	}

	@Override
	public String savePackMt(PackMt packMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,
			BindingResult result,Integer pPartyIds,Integer pLocationIds,String vTranDate) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/marketing/transactions/packList/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "packList/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				packMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				packMt.setSrNo(++maxSrno);
				int si = maxSrno.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				Integer presentYear = Integer.parseInt(vYear);
				Integer nextYear = presentYear + 1;
				
				String bagSr = null;
				
				switch (si) {
				case 1:
					bagSr = "000"+maxSrno;
					break;
					
				case 2:
					bagSr = "00"+maxSrno;
					break;
					
				case 3:
					bagSr = "0"+maxSrno;
					break;
					
				default:
					bagSr = maxSrno.toString();
					break;
				}
				
				
				packMt.setInvNo("PL/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				packMt.setCreatedBy(principal.getName());
				packMt.setCreatedDate(new java.util.Date());
				

			} else {
				packMt.setModiBy(principal.getName());
				packMt.setModiDate(new java.util.Date());
				
				if(pPartyIds != null) {
					Party party =  partyService.findOne(pPartyIds);
					Department department = departmentService.findOne(pLocationIds);
					
					packMt.setParty(party);
					packMt.setLocation(department);
						
				}
				
				action = "updated";
				 retVal = "redirect:/marketing/transactions/packList/edit/"+id+".html";
			}
			
		
			if(packMt.getHsnMast().getId() == null) {
				packMt.setHsnMast(null);
			}
			
			save(packMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/packList/edit/"+packMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
	}
	}

	@Override
	public String rateMasterListing(Integer partyId, Integer packMtId) {
		// TODO Auto-generated method stub
		Party party = partyService.findOne(partyId);
		PackMt packMt = findOne(packMtId);
		
		List<Metal> metalList = metalService.findActiveMetals();
		List<PackMetalRate> packMetalRates = packMetalRateService.findByPackMt(packMt);

		
		List<PackMetalRate> finalPackMetalRateList = new ArrayList<PackMetalRate>();
		
		if(packMetalRates.size() > 0){
			//list is not empty
			
			for(Metal metal:metalList){
				Boolean contains = false;
				
				PackMetalRate packMetalRateNew = new PackMetalRate();
				
				packMetalRateNew.setMetal(metal);
				
				for(PackMetalRate packMetalRate:packMetalRates){
					if(metal.getId().equals(packMetalRate.getMetal().getId())){
						packMetalRateNew.setId(packMetalRate.getId());
						packMetalRateNew.setRate(packMetalRate.getRate());
						packMetalRateNew.setLossPerc(packMetalRate.getLossPerc());
						contains = true;
						break;
					}
				}
				
				if(!contains){
					ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
					packMetalRateNew.setId(0);
					packMetalRateNew.setRate(0.0);
					packMetalRateNew.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
				}
				
				finalPackMetalRateList.add(packMetalRateNew);
				
			}
			
		}else{
			//list is empty
			
			for(Metal metal:metalList){
				
				ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
				PackMetalRate packMetalRate = new PackMetalRate();
				packMetalRate.setId(0);
				packMetalRate.setMetal(metal);
				packMetalRate.setRate(0.0);
				packMetalRate.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
				finalPackMetalRateList.add(packMetalRate);
				
			}
			
		}
		
		
		
			StringBuilder sb = new StringBuilder();
			
			sb.append("{\"total\":").append(finalPackMetalRateList.size()).append(",\"rows\": [");
		
			for(PackMetalRate packMetalRate:finalPackMetalRateList){
				sb.append("{\"id\":\"")
					.append(packMetalRate.getId())
					.append("\",\"metal\":\"")
					.append(packMetalRate.getMetal() != null ? packMetalRate.getMetal().getName() : "")
					.append("\",\"rate\":\"")
					.append(packMetalRate.getRate())
					.append("\",\"lossPerc\":\"")
					.append(packMetalRate.getLossPerc())
				.append("\"},");
			}
		
		
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			
			System.out.println(str);
			
			return str;
	}

	@Override
	public String getDtItemSummary(Integer mtId) {
		// TODO Auto-generated method stub
		
		PackMt packMt = findOne(mtId);
		
		List<PackDt> packDts = packDtService.findByPackMt(packMt);
		
		JSONObject jsonObject = new JSONObject();
		
		Double totPcs = 0.0;
		Double grossWt = 0.0;
		Double netWt = 0.0;
	//	Double pureWt = 0.0;
		Integer diaStone =0;
		Double diaCarat = 0.0;
		Integer colStone = 0;
		Double colCarat = 0.0;
		Double totCompWt=0.0;
		Double totMetalVal=0.0;
		Double totStnVal=0.0;
		Double totCompVal=0.0;
		Double totLabVal=0.0;
		Double totSetVal=0.0;
		Double totHdlgVal=0.0;
		Double totFobVal=0.0;
		Double totDiscAmt=0.0;
		Double totFinalPrice=0.0;
		
		
		try {
			
			for (PackDt packDt : packDts) {
				
				totPcs += packDt.getPcs();
				grossWt += packDt.getGrossWt();
				netWt += packDt.getNetWt();
				totMetalVal += packDt.getMetalValue();
				totStnVal += packDt.getStoneValue();
				totCompVal +=packDt.getCompValue();
				totLabVal += packDt.getLabValue();
				totSetVal += packDt.getSetValue();
				totHdlgVal += packDt.getHandlingValue();
				totFobVal += packDt.getFob();
				totDiscAmt+= packDt.getDiscAmount();
				totFinalPrice += packDt.getFinalPrice();
				
				
				List<PackStnDt> packStnDts = packStnDtService.findByPackDt(packDt);
				for (PackStnDt packStnDt : packStnDts) {
					
					if(packStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
						diaStone += packStnDt.getStone();
						diaCarat += packStnDt.getCarat();
					}else {
						colStone += packStnDt.getStone();
						colCarat += packStnDt.getCarat();
						
					}
				}
				
				List<PackCompDt> packCompDts = packCompDtService.findByPackDt(packDt);
				for (PackCompDt packCompDt : packCompDts) {
					totCompWt += packCompDt.getCompWt();
					
				}
				
			}
			
			jsonObject.put("totPcs", totPcs);
			jsonObject.put("grossWt", Math.round((grossWt)*1000.0)/1000.0);
			jsonObject.put("netWt",  Math.round((netWt)*1000.0)/1000.0);
		//	jsonObject.put("pureWt", Math.round((pureWt)*1000.0)/1000.0);
			jsonObject.put("diaStone", diaStone);
			jsonObject.put("diaCarat",  Math.round((diaCarat)*1000.0)/1000.0);
			jsonObject.put("colStone", colStone);
			jsonObject.put("colCarat", Math.round((colCarat)*1000.0)/1000.0);
			
			jsonObject.put("totCompWt", Math.round(totCompWt*1000.0)/1000.0);
			jsonObject.put("totMetalVal",Math.round(totMetalVal*100.0)/100.0);
			jsonObject.put("totStnVal", Math.round(totStnVal*100.0)/100.0);
			jsonObject.put("totCompVal",Math.round(totCompVal*100.0)/100.0);
			jsonObject.put("totLabVal", Math.round(totLabVal*100.0)/100.0);
			jsonObject.put("totSetVal",Math.round(totSetVal*100.0)/100.0);
			jsonObject.put("totHdlgVal",Math.round(totHdlgVal*100.0)/100.0);
			jsonObject.put("totFobVal",Math.round(totFobVal*100.0)/100.0);
			jsonObject.put("totDiscAmt",Math.round(totDiscAmt*100.0)/100.0);
			jsonObject.put("totFinalPrice",Math.round(totFinalPrice*100.0)/100.0);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	return jsonObject.toString();
	}

	@Override
	public String stockPackingTransferListing(Integer deptId) {
		// TODO Auto-generated method stub
		Department location = departmentService.findOne(deptId);
		List<StockMt> stockMt = stockMtService.findByLocationAndCurrStkAndDeactive(location, true, false);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stockMt.size()).append(",\"rows\": [");
		
		for (StockMt stockMt2 : stockMt) {
			
			String orderNo = null;
			String refNo = null;
			String bagNo = null;
			Integer bagId = 0;
			if(stockMt2.getBagId() != null) {
			BagMt bagMt = bagMtService.findOne(stockMt2.getBagId());
			if(bagMt != null) {
				orderNo = bagMt.getOrderMt().getInvNo();
				refNo = bagMt.getOrderDt().getRefNo();
				bagNo = bagMt.getName();
				bagId = bagMt.getId();
			}
			}
			
			
			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stockMt2.getBarcode(), true);
						
			sb.append("{\"party\":\"")
		     .append(stockTran.getParty() != null ? stockTran.getParty().getName() : "")
		     .append("\",\"orderNo\":\"")
			 .append(orderNo != null ? orderNo : "")
			 .append("\",\"refNo\":\"")
			 .append(refNo != null ? refNo : "")
			 .append("\",\"bagNo\":\"")
			 .append(bagNo != null ? bagNo : "")
			 .append("\",\"bagId\":\"")
			 .append(bagId > 0 ? bagId: "")
			 .append("\",\"styleNo\":\"")
			 .append(stockMt2.getDesign() != null ? stockMt2.getDesign().getMainStyleNo() :"")
			 .append("\",\"qty\":\"")
			 .append(stockMt2.getQty() !=null ? stockMt2.getQty() :0.0)
			 .append("\",\"grossWt\":\"")
			 .append(stockMt2.getGrossWt() != null ? stockMt2.getGrossWt() :0.0)
			 .append("\",\"image\":\"")
			 .append(stockMt2.getDesign() != null ? stockMt2.getDesign().getDefaultImage() :"")
			 .append("\",\"barcode\":\"")
			 .append(stockMt2.getBarcode() != null ? stockMt2.getBarcode() : "")
			 .append("\",\"mtId\":\"")
			 .append(stockMt2.getMtId() != null ? stockMt2.getMtId() : "")
			 .append("\"},");
		}
		
		str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		return str;
	}

}
