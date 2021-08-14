package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.io.File;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class RejectionPiecesEntryController {

	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IBagMtService bagMtService;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;


	@ModelAttribute("tranMt")
	public TranMt constructDt() {
		return new TranMt();
	}

	@RequestMapping("/rejectionPiecesEntry")
	public String users(Model model) {
		return "rejectionPiecesEntry";
	}

	@RequestMapping("/rejectionPiecesEntry/tranMt/listing")
	@ResponseBody
	public String tranMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search) {

	
		return tranMtService.rejectionTranMtList(limit, offset, sort, order, search);
		
	}

	@RequestMapping(value = "/rejectionPiecesEntry/add", method = RequestMethod.POST)
	@ResponseBody
	public String addToBagMt(@Valid @ModelAttribute("tranMt") TranMt tranMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pODIds") String pOIds,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "rejectionPiecesEntry";
		}
		
		
		String barcodeuploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator +"barcode"+File.separator;
		
		retVal =bagMtService.recastBag(pOIds, principal,barcodeuploadFilePath);
		
		
		
		

		

		return retVal;

	}

}
