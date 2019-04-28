package com.location.service;

import java.util.List;

import com.location.dto.SkuObject;
import com.location.model.SKU;

public interface SkuService {
	public SKU updateSku(SKU sku);
	public void deleteSku(Integer id);
	public List<SkuObject> getSkus();
}
