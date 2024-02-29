package com.ashokit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.ashokit.entity.CitizenPlan;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator 
{
	public void generateExcelReport(HttpServletResponse response, List<CitizenPlan> records,File file) throws Exception {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("S.NO");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("plan Name");
		headerRow.createCell(3).setCellValue("plan Status");
		headerRow.createCell(4).setCellValue("Gender");
		headerRow.createCell(5).setCellValue("plan start date");
		headerRow.createCell(6).setCellValue("plan end date");
		headerRow.createCell(7).setCellValue("benefit Amount");

		int rowIndex = 1;
		for (CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(rowIndex);
			dataRow.createCell(0).setCellValue(Integer.toString(plan.getCitizenId()));
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getGender());
			if (null != plan.getPlanStartDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if (null != plan.getPlanEndDate()) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate() + "");
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			if (null != plan.getBenefitAmt()) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmt());
			} else {
				dataRow.createCell(7).setCellValue("N/A");
			}
			rowIndex++;
		}
		
		//get the xl file 
		FileOutputStream fileOutputStream= new FileOutputStream(file);
		workbook.write(fileOutputStream);
		fileOutputStream.close();

		ServletOutputStream stream = response.getOutputStream();
		workbook.write(stream);
		workbook.close();

	}

}
