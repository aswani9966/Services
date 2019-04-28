package com.location.service;

import java.util.List;

import com.location.model.MetaData;

public interface MetaDataService {
	public MetaData createMetaData(MetaData metaData);
	public MetaData updateMetaData(Integer id,MetaData metaData);
	public void deleteMetaData(MetaData metaData);
	public List<MetaData> getAllMetaData();
}
