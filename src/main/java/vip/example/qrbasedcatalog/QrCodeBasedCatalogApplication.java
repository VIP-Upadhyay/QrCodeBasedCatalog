package vip.example.qrbasedcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import vip.example.qrbasedcatalog.files.FileProperty;


@SpringBootApplication
@EnableConfigurationProperties({
	FileProperty.class
})
public class QrCodeBasedCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrCodeBasedCatalogApplication.class, args);
	}

}
