package es.caser.spring.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import es.caser.spring.mvc.config.RootConfig;
import es.caser.spring.mvc.config.WebConfig;

public class MainWebAppInitializer extends  AbstractAnnotationConfigDispatcherServletInitializer{

	   @Override
	   protected Class<?>[] getRootConfigClasses() {
	      return new Class[]{RootConfig.class};
	   }

	   @Override
	   protected Class<?>[] getServletConfigClasses() {
	      return new Class[]{WebConfig.class};
	   }

	   @Override
	   protected String[] getServletMappings() {
	      return new String[]{"/"};
	   }

}
