package pers.yangchang.TaxiDataAnalysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("pers.yangchang.TaxiDataAnalysis.dao")
@ServletComponentScan // Servlet/Filter/Listtener @WebServlet all need it, @ServletComponentScan for third jar
public class TaxiDataAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiDataAnalysisApplication.class, args);
	}
}
