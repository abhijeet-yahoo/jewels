package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStkTrfCompDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfDtService;

@Service
@Repository
@Transactional
public class StkTrfCompDtService implements IStkTrfCompDtService{
	
	@Autowired
	private IStkTrfCompDtRepository stkTrfCompDtRepository;
	
	@Autowired
	private IStkTrfDtService stkTrfDtService;

	@Override
	public void save(StkTrfCompDt stkTrfCompDt) {
		// TODO Auto-generated method stub
		stkTrfCompDtRepository.save(stkTrfCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	 stkTrfCompDtRepository.delete(id);
		
	}

	@Override
	public StkTrfCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return  stkTrfCompDtRepository.findOne(id);
	}

	@Override
	public List<StkTrfCompDt> findByStkTrfDt(StkTrfDt stkTrfDt) {
		// TODO Auto-generated method stub
		return stkTrfCompDtRepository.findByStkTrfDt(stkTrfDt);
	}

	@Override
	public String stkTrfCompDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		
		StkTrfDt stkTrfDt = stkTrfDtService.findOne(dtId);
		List<StkTrfCompDt> stkTrfCompDts = findByStkTrfDt(stkTrfDt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stkTrfCompDts.size()).append(",\"rows\": [");
		  for(StkTrfCompDt stkTrfCompDt :stkTrfCompDts){
				sb.append("{\"id\":\"")
				.append(stkTrfCompDt.getId())
				.append("\",\"compName\":\"")
				.append((stkTrfCompDt.getComponent() != null ? stkTrfCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((stkTrfCompDt.getPurity() != null ? stkTrfCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((stkTrfCompDt.getColor() != null ? stkTrfCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((stkTrfCompDt.getCompWt() != null ? stkTrfCompDt.getCompWt() : ""))
				.append("\",\"rate\":\"")
				.append((stkTrfCompDt.getCompRate() != null ? stkTrfCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((stkTrfCompDt.getCompValue() != null ? stkTrfCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((stkTrfCompDt.getCompQty() != null ? stkTrfCompDt.getCompQty() : ""))
				.append("\",\"rLock\":\"")
				.append((stkTrfCompDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doConsigDtLockUnLock(")
				.append(stkTrfCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editConsigCompDt(")
				.append(stkTrfCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteConsigCompDt(event,")
				.append(stkTrfCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(stkTrfCompDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
				}else {
					sb.append("\",\"actionLock\":\"")
					.append("")
					.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					 .append("\"},");
				}
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	}

}
