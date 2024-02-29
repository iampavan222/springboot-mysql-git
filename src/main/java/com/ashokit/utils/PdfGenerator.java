package com.ashokit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ashokit.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	public void generatePdf(HttpServletResponse response, List<CitizenPlan> plandata,File file) throws Exception 
	{
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);

		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(file));

		// Opening the created document to modify it
		document.open();

		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(20);

		// creating paragraph
		Paragraph paragraph = new Paragraph("Citizen Info");
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		// Creating a table of 3 columns
		PdfPTable pdfTable = new PdfPTable(8);
		pdfTable.addCell("S.NO");
		pdfTable.addCell("Citizen Name");
		pdfTable.addCell("Plan Name");
		pdfTable.addCell("Plan Status");
		pdfTable.addCell("Gender");
		pdfTable.addCell("Plan StartDate");
		pdfTable.addCell("Plan EndDate");
		pdfTable.addCell("BenefitAmount");

		for (CitizenPlan data : plandata) {
			pdfTable.addCell(String.valueOf(data.getCitizenId()));
			pdfTable.addCell(data.getCitizenName());
			pdfTable.addCell(data.getPlanName());
			pdfTable.addCell(data.getPlanStatus());
			pdfTable.addCell(data.getGender());
			if (null != data.getPlanStartDate()) {
				pdfTable.addCell(data.getPlanStartDate() + "");
			} else {
				pdfTable.addCell("N/A");
			}
			if (null != data.getPlanEndDate()) {
				pdfTable.addCell(data.getPlanEndDate() + "");
			} else {
				pdfTable.addCell("N/A");
			}
			if (null != data.getBenefitAmt()) {
				pdfTable.addCell(String.valueOf(data.getBenefitAmt()));
			} else {
				pdfTable.addCell("N/A");
			}
		}

		// Adding the created table to document
		document.add(pdfTable);

		// Closing the document
		document.close();
	}

}
