package com.yangchang.TaxiDataAnalysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yangchang.TaxiDataAnalysis.dao")
public class TaxiDataAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiDataAnalysisApplication.class, args);
	}
}
