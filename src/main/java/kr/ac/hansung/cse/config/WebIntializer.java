package kr.ac.hansung.cse.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebIntializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ServletConfig.class);
		
		//context.setConfigLocation("kr.ac.hansung.cse.config");
		
		//servletContext.addListener(new ContextLoaderListener(context));
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		
		dispatcher.setLoadOnStartup(1);
		
		dispatcher.addMapping("/");
		
		AnnotationConfigWebApplicationContext disContext = new AnnotationConfigWebApplicationContext();
		disContext.register(DaoConfig.class);
		disContext.register(ServiceConfig.class);
		disContext.register(WebSecurityConfig.class);
		disContext.register(FlywayConfig.class);
		
		ContextLoaderListener listener = new ContextLoaderListener(disContext);
		servletContext.addListener(listener);
		
		
	}

}
