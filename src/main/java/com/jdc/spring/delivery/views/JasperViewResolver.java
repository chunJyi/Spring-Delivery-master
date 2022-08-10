package com.jdc.spring.delivery.views;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import java.util.Locale;

public class JasperViewResolver extends AbstractCachingViewResolver {

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		
		JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(viewName));
		return new JasperView(report);
	}

}
