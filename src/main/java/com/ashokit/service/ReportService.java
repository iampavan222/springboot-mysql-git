package com.ashokit.service;

import java.util.List;

import com.ashokit.entity.CitizenPlan;
import com.ashokit.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService 
{
	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequest searchRequest);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
	

}
