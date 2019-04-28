package com.location.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.location.model.MetaData;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaData, Long>{

}
