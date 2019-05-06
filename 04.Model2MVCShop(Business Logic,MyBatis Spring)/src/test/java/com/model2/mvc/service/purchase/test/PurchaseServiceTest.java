package com.model2.mvc.service.purchase.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })

public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception{

		Purchase purchase = new Purchase();
		
		Product product = new Product();
		product.setProdNo(10002);
		
		User user = new User();
		user.setUserId("user19");
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("kkk");
		purchase.setReceiverPhone("kkk");
		purchase.setDivyAddr("kkk");
		purchase.setDivyRequest("kkk");
		purchase.setTranCode("1");
		purchase.setDivyDate("2019-05-08");

		System.out.println(purchase);
		purchaseService.addPurchase(purchase);
		purchase = purchaseService.getPurchase2(10002);
		
		Assert.assertEquals("1  ",purchase.getPaymentOption());
		Assert.assertEquals("kkk", purchase.getReceiverName());
		Assert.assertEquals("kkk", purchase.getReceiverPhone());
		Assert.assertEquals("kkk", purchase.getDivyAddr());
		Assert.assertEquals("kkk", purchase.getDivyRequest());
		Assert.assertEquals("1  ", purchase.getTranCode());
//		Assert.assertEquals("2019-05-08", purchase.getDivyDate());
	}

	//@Test
	public void testGetPurchase() throws Exception{

		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase2(10001);

		Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("1",purchase.getPaymentOption());
		Assert.assertEquals("kkk", purchase.getReceiverName());
		Assert.assertEquals("kkk", purchase.getReceiverPhone());
		Assert.assertEquals("kkk", purchase.getDivyAddr());
		Assert.assertEquals("kkk", purchase.getDivyRequest());
		Assert.assertEquals("1", purchase.getTranCode());
		Assert.assertEquals("kkk", purchase.getDivyDate());

	}

	//@Test 
	public void testUpdatePurchase() throws Exception{

		Product product = new Product();
		Purchase purchase = purchaseService.getPurchase2(10001);

		purchase.setPaymentOption("2");
		purchase.setReceiverName("jjj");
		purchase.setReceiverPhone("jjj");
		purchase.setDivyAddr("jjj");
		purchase.setDivyRequest("jjj");
		purchase.setDivyDate("2012-12-12");
		
		purchaseService.updatePurcahse(purchase);

		purchase = purchaseService.getPurchase2(10001);
		
		System.out.println("purchase :: " + purchase);
		Assert.assertEquals(10001,purchase.getPurchaseProd().getProdNo() );
		Assert.assertEquals("2  ",purchase.getPaymentOption() );
		Assert.assertEquals("jjj",purchase.getReceiverName() );
		Assert.assertEquals("jjj",purchase.getReceiverPhone() );
		Assert.assertEquals("jjj",purchase.getDivyAddr() );
		Assert.assertEquals("jjj",purchase.getDivyRequest() );

	}


	//@Test 
	public void testUpdateTranCode() throws Exception{

		Purchase purchase = purchaseService.getPurchase2(10001);
		purchaseService.updateTranCode(purchase);

		Assert.assertEquals("3  ", purchase.getTranCode());

	}


	@Test
	public void testGetPurchaseListAll() throws Exception{

		Search search = new Search(); 
		search.setCurrentPage(1);
		search.setPageSize(3);
	
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase2(10001);
		
		String buyerId = purchase.getBuyer().getUserId(); 

		
		Map<String,Object> map = purchaseService.getPurchaseList(search,buyerId);
	
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3,list.size());

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0"); 
		search.setSearchKeyword(""); 
		map = purchaseService.getPurchaseList(search,buyerId);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

	}

}
