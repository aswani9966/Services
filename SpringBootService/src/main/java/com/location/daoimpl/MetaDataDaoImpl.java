package com.location.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.dao.MetaDataDao;
import com.location.model.MetaData;
import com.location.repository.MetaDataRepository;

@Repository
public class MetaDataDaoImpl implements MetaDataDao{
	@Autowired
    MetaDataRepository metaDataRepository;
	public MetaData createMetaData(MetaData metaData) {
		return metaDataRepository.save(metaData);
	}

	public MetaData updateMetaData(Integer id, MetaData metaData) {
		MetaData metaData2 = metaDataRepository.save(metaData);
	    return metaData2;
	}

	public void deleteMetaData(MetaData metaData) {
		metaDataRepository.delete(metaData);
	}

	public List<MetaData> getAllMetaData() {
		System.out.println("getAllMetaDataDao");
		return metaDataRepository.findAll();
	}

}
