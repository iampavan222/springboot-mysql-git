package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.entity.CitizenPlan;
import com.ashokit.request.SearchRequest;
import com.ashokit.service.ReportService;
import com.ashokit.utils.EmailUtils;
import com.ashokit.utils.ExcelGenerator;
import com.ashokit.utils.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportsController {
	@Autowired
	private ReportService service;
	
	@PostMapping("/search")
	public String handleSearch(Model model, @ModelAttribute("search") SearchRequest request) {
		System.out.println("ReportsController.handleSearch()");
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans", plans);
	    //model.addAttribute("search", request);
		init(model);
		return "index";

	}

	@GetMapping("/")
	public String getWelcome(Model model) {
		System.out.println("ReportsController.getWelcome()");
		SearchRequest searchRequest = new SearchRequest();
		model.addAttribute("search", searchRequest);
		init(model);
		return "index";
	}

	private void init(Model model) {
		System.out.println("ReportsController.init()");
		model.addAttribute("Names", service.getPlanNames());
		model.addAttribute("Status", service.getPlanStatus());
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception
	{
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plansInfo.xlsx";
		response.setContentType("application/octet-stream");
		response.setHeader(headerKey, headerValue);
		service.exportExcel(response);	
	}
	
	@GetMapping("/home")
	public ResponseEntity<String> getWelcome()
	{
		return ResponseEntity.ok("Hello Team");
	}
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception
	{
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plansInfo.pdf";
		response.setContentType("application/pdf");
		response.setHeader(headerKey, headerValue);
		service.exportPdf(response);
	}
	
	
	
	

}
