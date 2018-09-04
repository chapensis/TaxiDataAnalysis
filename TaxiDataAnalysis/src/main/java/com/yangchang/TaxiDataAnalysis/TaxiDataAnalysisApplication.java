package com.yangchang.TaxiDataAnalysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
@MapperScan("com.yangchang.TaxiDataAnalysis.dao")
@ServletComponentScan // Servlet/Filter/Listtener @WebServlet all need it, @ServletComponentScan for third jar
public class TaxiDataAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiDataAnalysisApplication.class, args);
	}
}
