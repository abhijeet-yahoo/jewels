package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondChangingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@Service
@Repository
@Transactional
public class DiamondChangingService implements IDiamondChangingService{

	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IReadyBagService readyBagService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	
	@Override
	public String diamondChangingDetails(String bagName, String uploadDirecotryName) {
		// TODO Auto-generated method stub
		BagMt 	 		bagMt 		= bagMtService.findByName(bagName);
		OrderDt  		orderDt 	= orderDtService.findOne(bagMt.getOrderDt().getId());
		TranMt 	 		tranMt 		= tranMtService.findByBagMtCurrStk(bagMt, true);
		List<ReadyBag> 	readyBag 	= readyBagService.findByBagMtAndCurrentStock(bagMt, true);
		

		Integer readyBagStatus = 1;
		if(readyBag.size() > 0){
			readyBagStatus = 0;
		}
		
		
		String uploadFilePath = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		String img = orderDt.getDesign().getDefaultImage();

		/*String imgName = orderDt.getDesign().getImage1();
		if (img != null && img.equals("2")) {
			imgName = orderDt.getDesign().getImage2();
		} else if (img != null && img.equals("3")) {
			imgName = orderDt.getDesign().getImage3();
		}*/

		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("invNo", orderDt.getOrderMt().getInvNo());
		jsonObject.put("partyName", orderDt.getOrderMt().getParty().getPartyCode());
		jsonObject.put("styleNo", orderDt.getDesign().getMainStyleNo());
		jsonObject.put("bagPcs", bagMt.getQty());
		jsonObject.put("vDepartment", (tranMt == null ? "" : tranMt.getDepartment().getName()));
		/*jsonObject.put("purityName", (orderDt.getPurity() == null ? "" : orderDt.getPurity().getName()));
		jsonObject.put("colorName", (orderDt.getColor() == null ? "" : orderDt.getColor().getName()));*/
		jsonObject.put("image", uploadFilePath + img);
		jsonObject.put("orderDtId", orderDt.getId());
		jsonObject.put("readyBagStatus", readyBagStatus);
		
		return jsonObject.toString();
	}


	@Override
	public String diamondChangingListing(String bagName) {
		// TODO Auto-generated method stub
		
		String str="{\"total\":0,\"rows\": []}";

		
		StringBuilder sb = new StringBuilder();
		BagMt bagMt = bagMtService.findByName(bagName);
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
		
		if(tranMt != null){
			
			List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
			
			sb.append("{\"total\":").append(tranDts.size()).append(",\"rows\": [");
			Integer srNo = 0;
			for(TranDt tranDt:tranDts){
				
					sb.append("{\"id\":\"")
						.append(tranDt.getId())
						.append("\",\"bagSrNo\":\"")
						.append((tranDt.getBagSrNo() != null ? tranDt.getBagSrNo() : ""))
						.append("\",\"srNo\":\"")
						.append(srNo)
						.append("\",\"partNm\":\"")
						.append((tranDt.getPartNm() != null ? tranDt.getPartNm().getFieldValue() : ""))
						.append("\",\"stoneType\":\"")
						.append((tranDt.getStoneType() != null ? tranDt.getStoneType().getName() : ""))
						.append("\",\"shape\":\"")
						.append((tranDt.getShape() != null ? tranDt.getShape().getName() : ""))
						.append("\",\"subShape\":\"")
						.append((tranDt.getSubShape() != null ? tranDt.getSubShape().getName() : ""))
						.append("\",\"quality\":\"")
						.append((tranDt.getQuality() != null ? tranDt.getQuality().getName() : ""))
						.append("\",\"mm\":\"")
						.append((tranDt.getSize() != null ? tranDt.getSize() : ""))
						.append("\",\"sieve\":\"")
						.append((tranDt.getSieve() != null ? tranDt.getSieve() : ""))
						.append("\",\"sizeGroup\":\"")
						.append((tranDt.getSizeGroup() != null ? tranDt.getSizeGroup().getName() : ""))
						.append("\",\"stones\":\"")
						.append(tranDt.getStone())
						.append("\",\"carat\":\"")
						.append(tranDt.getCarat())
						.append("\",\"setting\":\"")
						.append((tranDt.getSetting() != null ? tranDt.getSetting().getName() : ""))
						.append("\",\"setType\":\"")
						.append((tranDt.getSettingType() != null ? tranDt.getSettingType().getName() : ""))
						.append("\",\"centerStone\":\"")
						.append((tranDt.getCenterStone() != null ? (tranDt.getCenterStone() ? tranDt.getCenterStone() : false) : false))
						.append("\",\"action1\":\"")
						.append("<a onClick='javascript:addChangingPage(")
						.append(tranDt.getId())
						.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-plus'></span>&nbsp;Add</a>")
						.append("\",\"action2\":\"")
						.append("<a href='javascript:popChangingReturn(")
						.append(tranDt.getId())
						.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-plus'></span>&nbsp;Return</a>")
						.append("\"},");
					
						srNo++;
						
				}
			
		
			
			 str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			
			
		
		}
		
		
		return str;
	}


	@Override
	public String trandtRetDetail(Integer tranDtId) {
		// TODO Auto-generated method stub
		//System.err.println("trandtid ==== "+tranDtId);
		
				TranDt tranDt = tranDtService.findOne(tranDtId);
				
				/*Double stockBalance = stoneTranService.getStockBalance(tranDt.getStoneType().getId(), tranDt.getShape().getId(), 
						tranDt.getQuality().getId(), tranDt.getSizeGroup().getId(), tranDt.getSize(), "size");*/
				
				
				
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("modRetStoneType", tranDt.getStoneType().getName());
				jsonObject.put("modRetShape", tranDt.getShape().getName());
				jsonObject.put("modRetQuality", tranDt.getQuality().getName());
				jsonObject.put("modRetSize", tranDt.getSize());
				jsonObject.put("modRetStone", tranDt.getStone());
				jsonObject.put("modRetCarat", tranDt.getCarat());
				jsonObject.put("mPartNmId", tranDt.getPartNm().getFieldValue());
				
				
				return jsonObject.toString();
	}

	
}
