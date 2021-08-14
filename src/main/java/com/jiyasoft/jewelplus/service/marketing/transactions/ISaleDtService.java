package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;


import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;

public interface ISaleDtService {

public List<SaleDt> findAll();
	
public void save(SaleDt saleDt);

public void delete(int id);

public SaleDt findOne(int id);

public String addBarcodeItem(Integer mtId,String barcode,Principal principal);

public List<SaleDt> findBySaleMt(SaleMt saleMt);

public String deleteSaleDt(Integer dtId);

public String saleDtListing(Integer mtId,String disableFlg);

public String saleDtPickupListing(Integer mtId);

public SaleDt findByRefTranDtIdAndTranType(Integer refTranDtId,String tranType);

public SaleDt findBySaleMtAndBarcode(SaleMt saleMt,String barcode);
}
