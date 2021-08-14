package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMeltingMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Repository
@Transactional
public class MeltingMtService implements IMeltingMtService {

	@Autowired
	private IMeltingMtRepository meltingMtRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IMeltingIssDtService meltingIssDtService;

	@Autowired
	private IMeltingRecDtService meltingRecDtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Override
	public List<MeltingMt> findAll() {
		return meltingMtRepository.findAll();
	}

	@Override
	public void save(MeltingMt meltingMt) {
		meltingMtRepository.save(meltingMt);
	}

	@Override
	public void delete(int id) {
		MeltingMt meltingMt = meltingMtRepository.findOne(id);
		meltingMt.setDeactive(true);
		meltingMt.setDeactiveDt(new java.util.Date());
		meltingMtRepository.save(meltingMt);

	}

	@Override
	public MeltingMt findOne(int id) {
		return meltingMtRepository.findOne(id);
	}

	@Override
	public Page<MeltingMt> findAll(Integer limit, Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		
		return meltingMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));

	}

	@Override
	public Long count() {
		return meltingMtRepository.count();
	}

	

	@Override
	public Integer maxSrNo() {

		QMeltingMt qMeltingMt = QMeltingMt.meltingMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
				.from(qMeltingMt)
				.where(qMeltingMt.deactive.eq(false).and(qMeltingMt.createdDt.year().eq(date.get(Calendar.YEAR)))).list(qMeltingMt.srNo.max());

		for (Integer srNo : maxSrno) {
			retVal = (srNo == null ? 0 : srNo);
		}

		return retVal;
	}

	@Override
	public MeltingMt findByUniqueId(Long uniqueId) {

		return meltingMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QMeltingMt qMeltingMt = QMeltingMt.meltingMt;
		BooleanExpression expression = qMeltingMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qMeltingMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invNo") && colValue != null) {
				expression = qMeltingMt.deactive.eq(false).and(
						qMeltingMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qMeltingMt.invNo.like(colValue + "%");
			}
		}

		return meltingMtRepository.count(expression);
	}

	@Override
	public Page<MeltingMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QMeltingMt qMeltingMt = QMeltingMt.meltingMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qMeltingMt.deactive.eq(false);
			} else {
				expression = qMeltingMt.deactive.eq(false).and(
						qMeltingMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qMeltingMt.invNo.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<MeltingMt> meltingMtList = (Page<MeltingMt>) meltingMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return meltingMtList;
	}

	@Override
	public MeltingMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return meltingMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Page<MeltingMt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QMeltingMt qMeltingMt = QMeltingMt.meltingMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qMeltingMt.deactive.eq(false);
			}else{
				expression = qMeltingMt.deactive.eq(false).and(qMeltingMt.invNo.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression =qMeltingMt.invNo.like("%" + search + "%");
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
		Page<MeltingMt> meltingList =(Page<MeltingMt>) meltingMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return meltingList;
	}

	@Override
	public String meltingMtDelete(int id) {
	
		String retVal ="-1";
		
		try {

		

			MeltingMt meltingMt = findOne(id);
			
			List<StoneTran> meltingStnRec = stoneTranService.findByRefTranIdAndTranType(id, "MELTREC");
			for(StoneTran stnrecDt : meltingStnRec)
			{
				stoneTranService.delete(stnrecDt.getId());
			}
			
			List<MeltingIssDt> meltingIssDts = meltingIssDtService.findByMeltingMtAndDeactive(meltingMt, false);
			
			
			for(MeltingIssDt meltingIssDt : meltingIssDts){
				meltingIssDtService.meltingIssDtDelete(meltingIssDt.getId());
				
//				List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(meltingIssDt.getId(), "MeltingIssue",false);
//				for(MetalTran metalTran : metalTrans){
//					metalTranService.delete(metalTran.getId());
//				}
				
			}
			
			List<MeltingRecDt> meltingRecDts = meltingRecDtService.findByMeltingMtAndDeactive(meltingMt,false);
			for(MeltingRecDt meltingRecDt : meltingRecDts){
				meltingRecDtService.delete(meltingRecDt.getId());
				
				List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(meltingRecDt.getId(), "MeltingReceive",false);
				for(MetalTran metalTran : metalTrans){
					metalTranService.delete(metalTran.getId());
				}
				
			}
			delete(id);
			
			retVal ="1";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	@Override
	public String meltingStnRecReport(Integer mtId, String uploadDirecotryPath, String uploadParentDirecotryName,
			String uploadDirecotryName, String tmpUploadImage, String reportXmlDirectoryPath,
			String reportoutputdirectorypath, Principal principal) throws SQLException {
		// TODO Auto-generated method stub
		synchronized (this) {	
			String retVal = "-1";
			String fileName = "";
			String subRptPath = "";
			String imgPath = "";
			String barCodePath="";
			
			Connection conn = null;
			
			String xml = "meltingRecStnReport";
			
			Department department = departmentService.findByName("Refining");
		
			
		try {
			
			
			conn = Utils.getConnection();
		
		
			
			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			imgPath =     uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
			barCodePath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/StockBarcode/" ;
		
			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
			
			parametersMap.put("mtId", mtId);
			parametersMap.put("deptid", department.getId());
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("subrptpath", subRptPath);
			parametersMap.put("barCodePath", barCodePath);
			
			
			JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
		
				
				String newFileName = System.currentTimeMillis()+"";
				File file = new File(uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
				file.createNewFile();
				JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
				
				String exportFileName = System.currentTimeMillis()+""+principal.getName();
				Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +newFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
				
				file.delete();
				
				retVal = exportFileName;
				
			
		} catch (Exception e) {
			System.out.println(e);
			retVal = "-2";
		} finally {
			conn.close();
		}
		
		return retVal;
	}
	}

}
