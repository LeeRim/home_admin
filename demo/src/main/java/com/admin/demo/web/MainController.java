package com.admin.demo.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String viewMain() {
		return "main";
	}
	
	@RequestMapping("/common")
	public String viewIndex2() {
		return "fragment/common/ex";
	}
	
	@RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.setViewName("home");
        
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Bamdule");
        map.put("date", LocalDateTime.now());
        
        modelAndView.addObject("data", map);
        
        return modelAndView;
    }

}
