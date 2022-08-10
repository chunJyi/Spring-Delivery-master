package com.jdc.spring.delivery.views;

import net.sf.jasperreports.engine.*;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JasperView extends AbstractView {

	private JasperReport report;

	public JasperView(JasperReport report) {
		super();
		this.report = report;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + report.getName());
		
		JasperPrint print = JasperFillManager.fillReport(report, model, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
	}

}
