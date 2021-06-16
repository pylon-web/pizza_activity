package com.accenture.lkm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.BillRangeBean;
import com.accenture.lkm.business.bean.PizzaOrderBean;
import com.accenture.lkm.service.PizzaService;

@Controller
public class ReportController {

	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping(value="/loadBillRangeReport.html")
	public ModelAndView loadDateRangeReportPage() {
		ModelAndView modelAndView=new ModelAndView("OrderReport");
		modelAndView.addObject("billRangeObj", new BillRangeBean());
		return modelAndView;
	}
	
	@RequestMapping(value="/FatchRecordWithinDateRange",method=RequestMethod.POST)
	public ModelAndView getOrderDetails(@ModelAttribute("billRangeObj") BillRangeBean billRangeBean) throws Exception
	{
		List<PizzaOrderBean> pizzaOrderBeans=pizzaService.getOrderDetails(billRangeBean.getFromPrice(), billRangeBean.getToPrice());
		ModelAndView modelAndView=new ModelAndView("OrderReport");
		modelAndView.addObject("pizzaOrderList",pizzaOrderBeans);
		return modelAndView;
	}
	
	@ExceptionHandler(value=Exception.class)
	public ModelAndView handleAllExceptions(Exception exception){	
		ModelAndView  modelAndView = new ModelAndView();
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}
}
