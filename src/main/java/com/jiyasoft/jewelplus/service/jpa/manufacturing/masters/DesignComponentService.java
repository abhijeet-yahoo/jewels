package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignComponent;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignComponentRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DesignComponentService implements IDesignComponentService {

	@Autowired
	private IDesignComponentRepository designComponentRepository;

	@Override
	public List<DesignComponent> findAll() {
		return designComponentRepository.findAll();
	}

	@Override
	public Page<DesignComponent> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return designComponentRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(DesignComponent designComponent) {
		designComponentRepository.save(designComponent);
	}

	@Override
	public void delete(int id) {
		DesignComponent designComponent = designComponentRepository.findOne(id);
		designComponent.setDeactive(true);
		designComponent.setDeactiveDt(new java.util.Date());
		designComponentRepository.save(designComponent);
	}

	@Override
	public Long count() {
		return designComponentRepository.count();
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QDesignComponent qDesignComponent  = QDesignComponent.designComponent;
		BooleanExpression expression = qDesignComponent.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDesignComponent.deactive.eq(false);
			} else if (colName == null && colValue != null) {
				expression = qDesignComponent.deactive.eq(false).and(
						qDesignComponent.design.id.eq(Integer.parseInt(colValue)));
			} else if (colName.equalsIgnoreCase("styleId") && colValue != null) {
				expression = qDesignComponent.deactive.eq(false).and(
						qDesignComponent.design.id.eq(Integer.parseInt(colValue)));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("styleId")) && colValue != null) {
				expression = qDesignComponent.design.id.eq(Integer.parseInt(colValue));
			} else if ((colName != null || colName.equalsIgnoreCase("styleId")) && colValue != null) {
				expression = qDesignComponent.design.id.eq(Integer.parseInt(colValue));
			} /* else if (colName != null && colName.equalsIgnoreCase("style") && colValue != null) {
				expression = qInventory.style.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("category") && colValue != null) {
				expression = qInventory.category.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("subCategory")
					&& colValue != null) {
				expression = qInventory.subCategory.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("metal") && colValue != null) {
				expression = qInventory.metal.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("purity") && colValue != null) {
				expression = qInventory.purity.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("pieces") && colValue != null) {
				Integer tmpPieces = 0;

				try {
					tmpPieces = Integer.parseInt(colValue);
				} catch (NumberFormatException ex) {
					tmpPieces = 0;
				}

				expression = qInventory.pieces.eq(tmpPieces);
			} else if (colName != null && colValue == null) {
				return inventoryRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return inventoryRepository.count();
			}
*/		}

		return designComponentRepository.count(expression);
	}

	@Override
	public DesignComponent findOne(int id) {
		return designComponentRepository.findOne(id);
	}

/*
	public DesignComponent findByDesign(Design design) {
		return designComponentRepository.findByDesign(design);
	}
*/

	@Override
	public Map<Integer, String> getDesignComponentList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesignComponent> findByDesign(Design design) {
		QDesignComponent qDesignComponent = QDesignComponent.designComponent;
		BooleanExpression expression = qDesignComponent.deactive.eq(false).and(qDesignComponent.design.id.eq(design.getId()));

		return (List<DesignComponent>) designComponentRepository.findAll(expression);		
	}

	@Override
	public Page<DesignComponent> findByDesign(Design design, Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return designComponentRepository.findByDesign(design, new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

}
