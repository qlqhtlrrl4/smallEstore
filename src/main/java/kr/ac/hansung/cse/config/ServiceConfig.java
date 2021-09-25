package kr.ac.hansung.cse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.ac.hansung.cse.service","kr.ac.hansung.cse.util"})
public class ServiceConfig {

}
