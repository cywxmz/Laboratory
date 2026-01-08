package cn.navg.laboratory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan
@SpringBootApplication
@ServletComponentScan
public class LaboratoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoryApplication.class, args);
    }

}
