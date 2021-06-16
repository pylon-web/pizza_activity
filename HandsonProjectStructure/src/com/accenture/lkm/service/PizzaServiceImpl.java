package com.accenture.lkm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PizzaBean;
import com.accenture.lkm.business.bean.PizzaOrderBean;
import com.accenture.lkm.dao.PizzaDAO;
import com.accenture.lkm.dao.PizzaDAOWrapper;
import com.accenture.lkm.dao.PizzaOrderDAO;

@Service
public class PizzaServiceImpl implements PizzaService{

	@Autowired
	private PizzaDAOWrapper pizzaDAOWrapper;
	
	@Override
	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill)throws Exception {

		List<PizzaOrderBean> list=pizzaDAOWrapper.getOrderDetails(fromBill, toBill);
		if(list==null||list.size()==0)
		{
			throw new Exception("No records were found");
		}
		
		return list;  
	}

	@Override
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean)throws Exception {
		double price=pizzaDAOWrapper.getPizzaPrice(pizzaOrderBean.getPizzaId());
		Double bill=pizzaOrderBean.getNumberOfPiecesOrdered()*price;
		pizzaOrderBean.setBill(bill);
		return pizzaDAOWrapper.addPizzaOrderDetails(pizzaOrderBean);
	}

	@Override
	public Map<Integer, String> findAllPizzaDetails() {

		List<PizzaBean> beans= pizzaDAOWrapper.findAllPizzaDetails();
		Map<Integer, String> map=new HashMap<Integer,String>();
		for (PizzaBean pizzaBean : beans) {
			map.put(pizzaBean.getPizzaId(), pizzaBean.getPizzaName());
		}
		return map; 
	}
	
}
