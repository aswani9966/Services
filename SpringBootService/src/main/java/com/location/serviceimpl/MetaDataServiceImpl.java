package com.location.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location.dao.MetaDataDao;
import com.location.model.MetaData;
import com.location.service.MetaDataService;
@Component
public class MetaDataServiceImpl implements MetaDataService{
	@Autowired
    MetaDataDao metaDataDao;
	public MetaData createMetaData(MetaData metaData) {
		return metaDataDao.createMetaData(metaData);
	}

	public MetaData updateMetaData(Integer id, MetaData metaData) {		
		return metaDataDao.updateMetaData(id, metaData);
	}

	public void deleteMetaData(MetaData metaData) {
		metaDataDao.deleteMetaData(metaData);
		
	}

	public List<MetaData> getAllMetaData() {
		return metaDataDao.getAllMetaData();
	}

}
