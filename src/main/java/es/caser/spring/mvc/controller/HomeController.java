package es.caser.spring.mvc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class HomeController {
	@RequestMapping(path={"/"},method=RequestMethod.GET)
	   public String sayHello(Model model) {
	      model.addAttribute("message","Hello Spring MVC!");
	     
	      //Java 8 LocalDate
	      DateTimeFormatter formatter=DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
	      LocalDate date=LocalDate.now();
	      model.addAttribute("date", date.format(formatter));
	      
	      return "index";
	   }
}