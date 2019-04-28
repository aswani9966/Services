package com.location.dao;

import java.util.List;

import com.location.model.Location;

public interface LocationDao {
	public Location createLocationData(Location locationData);
	public void deleteLocationData(Location locationData);
	public List<Location> getAllLocationData();
	public Location getLocationByLocationName(String locationName);
	public Location getLocation(Integer locationId);
	public void deleteLocation(Location loc);
	public Location updateLocation(Location loc);
}
