package com.company.my.service.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * spring mvc mecanism always needs to return a model to have a view rendered !
 * -> Controllers interpret user input and transform it into a model that is represented to the user by the view !
 * 
 * 
 * @author karnonoviseth
 *
 */
@Controller
public class CardRestService {

	String message = "simple call message :)";

	@RequestMapping("/simplecall")
	public ModelAndView simpleCall() {
		
		
		return new ModelAndView(
				"hello", /* view name */
				"message", /* model name */
				this.message /* single object model */
		);
	}
	
	@RequestMapping(value = "/simplecallmodelmap", method = RequestMethod.GET)
    public ModelMap simpleCallForModelMap() {
        return new ModelMap(
        		"attributeName",
        		"attributeValue"
        		);
    }
}
