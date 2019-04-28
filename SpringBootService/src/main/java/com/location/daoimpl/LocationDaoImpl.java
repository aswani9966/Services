package com.location.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.dao.LocationDao;
import com.location.model.Location;
import com.location.repository.LocationRepository;
@Repository
public class LocationDaoImpl implements LocationDao {
	@Autowired
    LocationRepository locationRepository;
	public Location createLocationData(Location locationData) {
		return locationRepository.saveAndFlush(locationData);
	}

	public void deleteLocationData(Location locationData) {
		locationRepository.delete(locationData);		
	}

	public List<Location> getAllLocationData() {
		return locationRepository.findAll();
	}
	public Location getLocation(Integer locationId) {
		return locationRepository.findByID(locationId);
	}
	public Location getLocationByLocationName(String locationName){
		return locationRepository.findLocationByLocationName(locationName);
	}

	@Override
	public void deleteLocation(Location loc) {
		
		locationRepository.delete(loc);
	}

	@Override
	public Location updateLocation(Location loc) {
		return locationRepository.saveAndFlush(loc);
		
	}

}
