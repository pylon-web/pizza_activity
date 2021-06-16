package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.business.bean.PizzaBean;
import com.accenture.lkm.business.bean.PizzaOrderBean;
import com.accenture.lkm.entity.PizzaEntity;
import com.accenture.lkm.entity.PizzaOrderEntity;

@Repository
@Transactional(value = "txManager")
public class PizzaDAOWrapper {

	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Autowired
	private PizzaOrderDAO pizzaOrderDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<PizzaBean> findAllPizzaDetails()
	{
		List<PizzaBean> pizzaBeans=new ArrayList<PizzaBean>();
		
		try {
			List<PizzaEntity> listEntity=pizzaDAO.findAllPizzaDetails();
			for (PizzaEntity pizzaEntity : listEntity) {
				PizzaBean pizzaBean=convertEntityToBean(pizzaEntity);
				pizzaBeans.add(pizzaBean);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return pizzaBeans;
		
	}
	
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean)
	{
		PizzaOrderBean orderBean=null;
		PizzaOrderEntity orderEntity=convertOrderBeanToOrderEntity(pizzaOrderBean);
		try {
			orderBean=convertOrderEntityToOrderBean(pizzaOrderDAO.save(orderEntity));
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return orderBean;
		
	}
	
	public Double getPizzaPrice(Integer pizzaId)
	{
		double price=0;
		PizzaEntity pizzaEntity=entityManager.find(PizzaEntity.class,pizzaId);
		if(pizzaEntity!=null)
		{
			price=pizzaEntity.getPrice();
		}
		return price;
		
	}
	
	public List<PizzaOrderBean> getOrderDetails(Double fromBill,Double toBill)
	{
		List<PizzaOrderBean> pizzaOrderBeans=null;
		try {
			Query query=entityManager.createQuery("select k from PizzaOrderEntity k where k.bill>=?1 and k.bill<=?2");
			query.setParameter(1,fromBill);
			query.setParameter(2,toBill);
			List<PizzaOrderEntity> entities=query.getResultList();
			pizzaOrderBeans=new ArrayList<PizzaOrderBean>();
			for (PizzaOrderEntity pizzaOrderEntity : entities) {
				PizzaOrderBean pizzaOrderBean=convertOrderEntityToOrderBean(pizzaOrderEntity);
				pizzaOrderBeans.add(pizzaOrderBean);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return pizzaOrderBeans;
		
	}
	// Utility Methods.......
	public static PizzaBean convertEntityToBean(PizzaEntity entity) {
		PizzaBean customerBean = new PizzaBean();
		BeanUtils.copyProperties(entity, customerBean);
		return customerBean;
	}
	public static PizzaEntity convertBeanToEntity(PizzaBean bean) {
		PizzaEntity entity = new PizzaEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}
	public static PizzaOrderBean convertOrderEntityToOrderBean(PizzaOrderEntity entity) {
		PizzaOrderBean customerBean = new PizzaOrderBean();
		BeanUtils.copyProperties(entity, customerBean);
		return customerBean;
	}
	public static PizzaOrderEntity convertOrderBeanToOrderEntity(PizzaOrderBean bean) {
		PizzaOrderEntity entity = new PizzaOrderEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}
	
}
