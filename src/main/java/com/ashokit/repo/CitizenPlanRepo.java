package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.CitizenPlan;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer>
{
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getplanName();
	
	@Query("select distinct(planStatus) from CitizenPlan ")
	public List<String> getplanStatus();
	
	

}
