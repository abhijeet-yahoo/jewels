package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.DesignRpt;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.reports.IDesignRptRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IDesignRptService;


@Service
@Repository
@Transactional
public class DesignRptService implements IDesignRptService {

	
	@Autowired
	private IDesignRptRepository designRptRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public Page<DesignRpt> getDesignFilterListing(String designGrp,String cat, String subCat,
			String con, String coll, String subCon, String stnType, String shp,
			String sizGrp, String setType, Double frGld, Double toGld,
			Double frCarat, Double toKarat, String frBetDate, String toBetDate,Integer limit, Integer offset,Integer cancelCond,Integer bbcCond
			,Integer musthaveCond, Integer allCond,String mCond,Integer msiCond,Double fromPrice,Double toPrice,String looks,String patterns,String clients) {
		
		
		Page<DesignRpt> designRpt = null;
		
		try{
				
				String designGroupCond 	= "";
				String categCond 		= "";
				String subCatCond 		= "";
				String conceptCond 		= "";
				String collectionCond 	= "";
				String subConceptCond 	= "";
				String stnTypeCond 		= "";
				String shapeCond 		= "";
				String sizeGroupCond 	= "";
				String setTypeCond 		= "";
				String fromWt			= "";
				String toWt				= "";
				String fromCarat		= "";
				String toCarat			= "";
				String startDate 		= "";
				String endDate			= "";
				String lookCond 		="";
				String patternCond 		="";
				String clientCond 		= "";
				
				
				SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
				
				
				
				
				if(designGrp.length() > 0){
					designGroupCond = "DesignGroupId  in ("+designGrp+")";
				}else{
					designGroupCond = "";
				}
				
				if(clients.length() > 0){
					clientCond = "PartyId in ("+clients+")";
				}else {
					clientCond = "";
				}
				
				if(cat.length() > 0){
					categCond = "CategId in ("+cat+")";
				}else{
					categCond = "";
				}
				
				if(subCat.length() > 0){
					subCatCond = " SCategId in ("+subCat+") ";
				}else{
					subCatCond = "";
				}
				
				if(con.length() > 0){
					conceptCond = "ConceptId in ("+con+")";
				}else{
					conceptCond = "";
				}
				
				if(coll.length() > 0){
					collectionCond = "CollectionId in ("+coll+")";
				}else{
					collectionCond = "";
				}
				
				if(subCon.length() > 0){
					subConceptCond = " SubConceptId in ("+subCon+") ";
				}else{
					subConceptCond = "";
				}
				
				if(stnType.length() > 0){
					stnTypeCond = " StoneTypeId in ("+stnType+") ";
				}else{
					stnTypeCond = "";
				}
				
				if(shp.length() > 0){
					shapeCond = " ShapeId in ("+shp+") ";
				}else{
					shapeCond = "";
				}
				
				if(sizGrp.length() > 0){
					sizeGroupCond = " SizeGroupId in ("+sizGrp+") ";
				}else{
					sizeGroupCond = "";
				}
				
				if(setType.length() > 0){
					setTypeCond = " SettypeId in ("+setType+") ";
				}else{
					setTypeCond = "";
				}
				
				if(frGld == null || frGld.equals("")){
					fromWt = "";
				}else{
					fromWt = frGld+"";
				}
				
				if(toGld == null || toGld.equals("")){
					toWt = "";
				}else{
					toWt = toGld+"";
				}
				
				if(frCarat == null || frCarat.equals("")){
					fromCarat = "";
				}else{
					fromCarat = frCarat+"";
				}
				
				if(toKarat == null || toKarat.equals("")){
					toCarat = "";
				}else{
					toCarat = toKarat+"";
				}
				
				if (frBetDate.length() > 0) {
					Date betFromDate = dfInput.parse(frBetDate);
					String fBetFromDate = dfOutput.format(betFromDate);
					startDate = fBetFromDate;
				}else{
					startDate = "";
				}
				
				if (toBetDate.length() > 0) {
					Date betToDate = dfInput.parse(toBetDate);
					String fBetToDate = dfOutput.format(betToDate);
					endDate   = fBetToDate;
				}else{
					endDate = "";
				}
					
				
				int page = 0;
				if (offset == null || limit == null) {
					limit = 10000;
				} else {
					page = (offset == 0 ? 0 : (offset / limit));
				}
				
				
				if(mCond.equalsIgnoreCase("0")){
					mCond = "";
				}else{
					mCond = "'%"+mCond+"%'";
				}
				
				if(looks.length() > 0){
					lookCond = " lookId in ("+looks+") ";
				}else{
					lookCond = "";
				}
				if(patterns.length() > 0){
					patterns= " patternId in ("+patterns+") ";
				}else{
					patternCond = "";
				}
				
				
				@SuppressWarnings("unchecked")
				TypedQuery<DesignRpt> query =  (TypedQuery<DesignRpt>)entityManager.createNativeQuery("{ CALL jsp_designlistnew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }", DesignRpt.class);
				query.setParameter(1,categCond);
				query.setParameter(2,subCatCond);
				query.setParameter(3,conceptCond);
				query.setParameter(4,subConceptCond);
				query.setParameter(5,stnTypeCond);
				query.setParameter(6,shapeCond); 
				query.setParameter(7,sizeGroupCond);
				query.setParameter(8,setTypeCond);
				query.setParameter(9,fromWt);
				query.setParameter(10,toWt);
				query.setParameter(11,fromCarat);
				query.setParameter(12,toCarat);
				query.setParameter(13,startDate);
				query.setParameter(14,endDate);
				query.setParameter(15,designGroupCond);
				query.setParameter(16,limit);
				query.setParameter(17,offset);
				query.setParameter(18,cancelCond);
			//	query.setParameter(19,bbcCond);
			//	query.setParameter(20,musthaveCond);
			//	query.setParameter(21,allCond);
				query.setParameter(19,mCond);
				query.setParameter(20,collectionCond);
			//	query.setParameter(24,msiCond);
				query.setParameter(21,fromPrice);
				query.setParameter(22,toPrice);
				query.setParameter(23,lookCond);
				query.setParameter(24,patternCond);
				query.setParameter(25,clientCond);
				
				
		
				 designRpt = new PageImpl<DesignRpt>(query.getResultList(), new PageRequest(page, limit), 0);
				
				
				
		
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		return designRpt;
	}

	
	
	

	@Override
	public Long count() {
		return designRptRepository.count();
	}
	
	
	
	@Override
	public List<Design> getAll() {
		
		
		List<Design> designs = null;
		
	
		
		String styleCond =  "StyleId in (73027)" ;
		String catCond	= "";
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Design> query =  (TypedQuery<Design>)entityManager.createNativeQuery("{ CALL jsp_rep_designcatalog(?,?) }", Design.class);
		query.setParameter(1, styleCond);
		query.setParameter(2, catCond);
		
		designs = query.getResultList();
		
		return designs;
	}





	@Override
	public Long getDesignFilterListingCount(String designGrp, String cat,String subCat, String con, String coll, String subCon, String stnType,
			String shp, String sizGrp, String setType, Double frGld,Double toGld, Double frCarat, Double toKarat, String frBetDate,
			String toBetDate,Integer cancelCond,Integer bbcCond,Integer musthaveCond, Integer allCond,String mCond,Integer msiCond,Double fromPrice,Double toPrice,
			String looks,String patterns,String clients) {
			
			Long count = 0l;
				
				
				try{
						
						String designGroupCond 	= "";
						String categCond 		= "";
						String subCatCond 		= "";
						String conceptCond 		= "";
						String collectionCond 	= "";
						String subConceptCond 	= "";
						String fromWt			= "";
						String toWt				= "";
						String startDate 		= "";
						String endDate			= "";
						
						
						
						String stnTypeCond 		= "";
						String shapeCond 		= "";
						String sizeGroupCond 	= "";
						String setTypeCond 		= "";
						String fromCarat		= "";
						String toCarat			= "";
						
						String lookCond			="";
						String patternCond 		="";
						String clientCond 		="";
						
						SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
						
						
						
						
						if(designGrp.length() > 0){
							designGroupCond = "DesignGroupId  in ("+designGrp+")";
						}else{
							designGroupCond = "";
						}
						
						if(cat.length() > 0){
							categCond = "CategId in ("+cat+")";
						}else{
							categCond = "";
						}
						
						if(clients.length() > 0){
							clientCond = "PartyId in ("+clients+")";
						}else{
							clientCond = "";
						}
						
						if(subCat.length() > 0){
							subCatCond = " SCategId in ("+subCat+") ";
						}else{
							subCatCond = "";
						}
						
						if(con.length() > 0){
							conceptCond = "ConceptId in ("+con+")";
						}else{
							conceptCond = "";
						}
						
						if(coll.length() > 0){
							collectionCond = "CollectionId in ("+coll+")";
						}else{
							collectionCond = "";
						}
						
						if(subCon.length() > 0){
							subConceptCond = " SubConceptId in ("+subCon+") ";
						}else{
							subConceptCond = "";
						}
						
						if(stnType.length() > 0){
							stnTypeCond = " StoneTypeId in ("+stnType+") ";
						}else{
							stnTypeCond = "";
						}
						
						if(shp.length() > 0){
							shapeCond = " ShapeId in ("+shp+") ";
						}else{
							shapeCond = "";
						}
						
						if(sizGrp.length() > 0){
							sizeGroupCond = " SizeGroupId in ("+sizGrp+") ";
						}else{
							sizeGroupCond = "";
						}
						
						if(setType.length() > 0){
							setTypeCond = " SettypeId in ("+setType+") ";
						}else{
							setTypeCond = "";
						}
						
						if(frGld == null || frGld.equals("")){
							fromWt = "";
						}else{
							fromWt = frGld+"";
						}
						
						if(toGld == null || toGld.equals("")){
							toWt = "";
						}else{
							toWt = toGld+"";
						}
						
						if(frCarat == null || frCarat.equals("")){
							fromCarat = "";
						}else{
							fromCarat = frCarat+"";
						}
						
						if(toKarat == null || toKarat.equals("")){
							toCarat = "";
						}else{
							toCarat = toKarat+"";
						}
						
						if (frBetDate.length() > 0) {
							Date betFromDate = dfInput.parse(frBetDate);
							String fBetFromDate = dfOutput.format(betFromDate);
							startDate = fBetFromDate;
						}else{
							startDate = "";
						}
						
						if (toBetDate.length() > 0) {
							Date betToDate = dfInput.parse(toBetDate);
							String fBetToDate = dfOutput.format(betToDate);
							endDate   = fBetToDate;
						}else{
							endDate = "";
						}
						
						if(mCond.equalsIgnoreCase("0")){
							mCond = "";
						}else{
							mCond = "'%"+mCond+"%'";
						}
							
						if(looks.length() > 0){
							lookCond = "lookId in ("+looks+")";
						}else{
							lookCond = "";
						}
						if(patterns.length() > 0){
							patterns= "patternId in ("+patterns+")";
						}else{
							patternCond = "";
						}
						@SuppressWarnings("unchecked")
						TypedQuery<Object> query =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_designcountnew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
						
						query.setParameter(1,categCond);
						query.setParameter(2,subCatCond);
						query.setParameter(3,conceptCond);
						query.setParameter(4,subConceptCond);
						query.setParameter(5,stnTypeCond);
						query.setParameter(6,shapeCond); 
						query.setParameter(7,sizeGroupCond);
						query.setParameter(8,setTypeCond);
						query.setParameter(9,fromWt);
						query.setParameter(10,toWt);
						query.setParameter(11,fromCarat);
						query.setParameter(12,toCarat);
						query.setParameter(13,startDate);
						query.setParameter(14,endDate);
						query.setParameter(15,designGroupCond);
						query.setParameter(16,cancelCond);
					//	query.setParameter(17,bbcCond);
					//	query.setParameter(18,musthaveCond);
					//	query.setParameter(19,allCond);
						query.setParameter(17,mCond);
						query.setParameter(18,collectionCond);
					//	query.setParameter(22,msiCond);
						query.setParameter(19,fromPrice);
						query.setParameter(20,toPrice);
						query.setParameter(21,lookCond);
						query.setParameter(22,patternCond);
						query.setParameter(23,clientCond);
				
					
						List<Object> designRptList = query.getResultList();
						
						String tempCount = designRptList.get(0).toString();
						count =  Long.parseLong(tempCount);
			
						
						
				
				}catch(Exception e){
					System.out.println(e);
				}
				
				
				return count;

	}
	
	
	/*@Override
	public List<Object[]> exportList(String subCat, Double expQty,String frBetDate,String toBetDate){
		
		List<Object[]> designRptList1=new ArrayList<Object[]>();
		
		try{
		String subCatCond 		= "";
		String startDate 		= "";
		String endDate 		= "";
		
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		
		if(cat.length() > 0){
			categCond = "CategId in ("+cat+")";
		}else{
			categCond = "";
		}
		
		if(subCat.length() > 0){
			subCatCond = " SCategId in ("+subCat+") ";
		}else{
			subCatCond = "";
		}
		
		if (frBetDate.length() > 0) {
			Date betFromDate = dfInput.parse(frBetDate);
			String fBetFromDate = dfOutput.format(betFromDate);
			startDate = fBetFromDate;
		}else{
			startDate = "";
		}
		
		if (toBetDate.length() > 0) {
			Date betToDate = dfInput.parse(toBetDate);
			String fBetToDate = dfOutput.format(betToDate);
			endDate   = fBetToDate;
		}else{
			endDate = "";
		}
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_rep_bestseller(?,?,?,?,?) }");
		query.setParameter(1,startDate);
		query.setParameter(2,endDate);
		query.setParameter(3,expQty);
		query.setParameter(4,subCatCond);
		query.setParameter(5,"");
		
		designRptList1 = query.getResultList();
		
		
		
		
		}catch(Exception e){
			System.out.println(e);
		}
		
		return designRptList1;
		
	}
*/

	
	
	@Override
	public List<Object[]> exportLists(String subCat, String cat,
			String concept, String party, Double expQty, String frBetDate,
			String toBetDate) {
		
		
		List<Object[]> designRptList1=new ArrayList<Object[]>();
		
		try{
		
		String startDate 		= "";
		String endDate 		= "";
		String categCond 		= "";
		String subCatCond 		= "";
		String conceptCond 		= "";
		String partyCond 		= "";
		
		
		
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		
		if(cat.length() > 0){
			categCond = "CategId in ("+cat+")";
		}else{
			categCond = "";
		}
		
		if(subCat.length() > 0){
			subCatCond = " SCategId in ("+subCat+") ";
		}else{
			subCatCond = "";
		}
		
		if(concept.length() > 0){
			conceptCond = "ConceptId in ("+concept+")";
		}else{
			conceptCond = "";
		}
		
		if(party.length() > 0){
			partyCond = "ordpartyid in ("+party+")";
		}else{
			partyCond = "";
		}
		
		if (frBetDate.length() > 0) {
			Date betFromDate = dfInput.parse(frBetDate);
			String fBetFromDate = dfOutput.format(betFromDate);
			startDate = fBetFromDate;
		}else{
			startDate = "";
		}
		
		if (toBetDate.length() > 0) {
			Date betToDate = dfInput.parse(toBetDate);
			String fBetToDate = dfOutput.format(betToDate);
			endDate   = fBetToDate;
		}else{
			endDate = "";
		}

		
		
		//System.out.println(startDate+"--- "+endDate+"--------"+expQty+"--------------"+subCatCond+"--------------"+partyCond+"--------------"+categCond+"--------------"+conceptCond);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_rep_bestseller(?,?,?,?,?,?,?,?) }");
		query.setParameter(1,startDate);
		query.setParameter(2,endDate);
		query.setParameter(3,expQty);
		query.setParameter(4,categCond);
		query.setParameter(5,subCatCond);
		query.setParameter(6,conceptCond);
		query.setParameter(7,partyCond);
		query.setParameter(8,"");

		
		System.out.println("Query   "+ query);
		
		designRptList1 = query.getResultList();
		
		
		
		
		}catch(Exception e){
			System.out.println(e);
		}
		
		return designRptList1;
	}

}
