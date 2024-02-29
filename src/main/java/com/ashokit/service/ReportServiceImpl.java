package com.ashokit.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.entity.CitizenPlan;
import com.ashokit.repo.CitizenPlanRepo;
import com.ashokit.request.SearchRequest;
import com.ashokit.utils.EmailUtils;
import com.ashokit.utils.ExcelGenerator;
import com.ashokit.utils.PdfGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService
{
	@Autowired
	private CitizenPlanRepo repo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanNames() {
		return repo.getplanName();
	}

	@Override
	public List<String> getPlanStatus() {
		return repo.getplanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest searchRequest) {

		CitizenPlan entity = new CitizenPlan();
		
		if (null != searchRequest.getPlanName() && !"".equals(searchRequest.getPlanName())) {
			entity.setPlanName(searchRequest.getPlanName());
		}
		if (null != searchRequest.getPlanStatus() && !"".equals(searchRequest.getPlanStatus())) {
			entity.setPlanStatus(searchRequest.getPlanStatus());
		}
		if( null != searchRequest.getGender() && !"".equals(searchRequest.getGender())) {
			entity.setGender(searchRequest.getGender());
		}
		if (null != searchRequest.getStartDate() && !"".equals(searchRequest.getStartDate())) {
			String startDate = searchRequest.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate stdate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(stdate);
		}
		if (null != searchRequest.getEndDate() && !"".equals(searchRequest.getEndDate())) {
			String endDate = searchRequest.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate eddate = LocalDate.parse(endDate, formatter);
			entity.setPlanStartDate(eddate);
		}
		return repo.findAll(Example.of(entity));

	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception
	{
		File file = new File("Plans.xlsx");
		
		List<CitizenPlan> plans = repo.findAll();
		excelGenerator.generateExcelReport(response, plans, file);
		
		//data for mail sending
		String subject ="Test Results";
		String body="<h1>Hello Team,Today Test Results</h1>";
		String to = "pavan.bonasi.qa07@gmail.com";
		
		//attachement for email 
		emailUtils.sendMailWithAttachment(subject, body, to,file);
		
		file.delete();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception
	{
		File file = new File("plansInfo.pdf");
		List<CitizenPlan> plans = repo.findAll();
		
		pdfGenerator.generatePdf(response, plans,file);
		
		String subject ="Test Results";
		String body="<h1>Hello Team,Today Test Results</h1>";
		String to = "pavan.bonasi.qa07@gmail.com";
		emailUtils.sendMailWithAttachment(subject, body, to,file);
		
		file.delete();
		
		return true;
	}

}
