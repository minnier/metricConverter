package za.co.minnier.metricConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MetricConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricConverterApplication.class, args);
	}
}
