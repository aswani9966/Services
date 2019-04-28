package com.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.location.model.MetaData;
import com.location.service.MetaDataService;

@RestController
@RequestMapping({"/api"})
public class MetaDataController {
	@Autowired
	MetaDataService metaDataService;
	
	@GetMapping(path={"/metaData"}, produces = "application/json")
	public List<MetaData> getAllMetaData() {
		List<MetaData> responseDatas=null;
		try{
			System.out.println("start::getAllMetaData");
			responseDatas= metaDataService.getAllMetaData();
			System.out.println("End::getAllMetaData");
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDatas;
	}
}
