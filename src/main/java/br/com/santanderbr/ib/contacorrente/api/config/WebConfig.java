package br.com.santanderbr.ib.contacorrente.api.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import br.com.santanderbr.ib.contacorrente.api.format.BigDecimalFormat;
import br.com.santanderbr.ib.contacorrente.api.controller.ClienteController;

@Configuration
@ComponentScan(basePackageClasses= {ClienteController.class})
@EnableSpringDataWebSupport
public class WebConfig implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	} 
	
	
	@Bean
	public FormattingConversionService conversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		
		
		BigDecimalFormat bigDecimalFormatter = new BigDecimalFormat("#,##0.00");
		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
		
		BigDecimalFormat integerFormatter = new BigDecimalFormat("#,##0");
		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);
		
		BigDecimalFormat longFormatter = new BigDecimalFormat("#,##0");
		conversionService.addFormatterForFieldType(Long.class, longFormatter);
		
		// API de Datas do Java 8
		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
		dateTimeFormatter.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	
}
