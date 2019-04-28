package com.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.location.model.Location;
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	@Query(value = "SELECT * FROM location l WHERE l.name = ?1", nativeQuery = true)
	Location findLocationByLocationName(String locationName);
	@Query(value = "SELECT * FROM location l WHERE l.id = ?1", nativeQuery = true)
	Location findByID(Integer locationId);
}
