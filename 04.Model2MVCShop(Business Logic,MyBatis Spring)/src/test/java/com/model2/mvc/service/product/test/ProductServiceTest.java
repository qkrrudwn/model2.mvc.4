package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

//테스트용으로 구동하기 위해 환경을 잡아주겠다는 표시
@RunWith(SpringJUnit4ClassRunner.class) 
//runwith에 대한 상세환경설정을 말함
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {


	//스프링에서 Autowired import하나와 @Autowired이 문장 하나로 get/set접근 메소드를 알아서 해줌
	//자동주입대상에 annotation사용 생성자에 적용하거나 필드에 적용하거나 설정메소드에 적용하거나
	//스프링은 타입을 이용해서 의존대상객체를 검색한다 즉, 해당 타입에 할당할 수 있는 빈 객체를 찾아서 주입 대상을 검색한다.
	@Autowired

	//같은 타입의 빈이 2개 이상이 존재할 경우 exception발생함
	//그런한 문제를 해결하기위해 있는것이 @Qualifier임.
	@Qualifier("productServiceImpl")
	private ProductService productService;


	//이 어노테이션붙은 구간을 테스트하겠다는 의미
	//	@Test
	public void testAddProduct() throws Exception {

		Product product = new Product();
		product.setFileName("zzz");
		product.setManuDate("zzz");
		product.setPrice(5555);
		product.setProdDetail("zzz");
		product.setProdName("zzz");
		product.setProdNo(555);

		productService.addProduct(product);
		product = productService.getProduct(555);

		// ==> console Ȯ��
		System.out.println(product);

		Assert.assertEquals("zzz", product.getFileName());
		Assert.assertEquals("zzz", product.getManuDate());
		Assert.assertEquals(5555, product.getPrice());
		Assert.assertEquals("zzz", product.getProdDetail());
		Assert.assertEquals("zzz", product.getProdName());
		Assert.assertEquals(555, product.getProdNo());
	}

	//@Test
	public void testGetProduct() throws Exception {

		Product product = new Product();
		product = productService.getProduct(555);
		Assert.assertEquals("zzz", product.getFileName());
		Assert.assertEquals("zzz", product.getManuDate());
		Assert.assertEquals(5555, product.getPrice());
		Assert.assertEquals("zzz", product.getProdDetail());
		Assert.assertEquals("zzz", product.getProdName());
		Assert.assertEquals(555, product.getProdNo());

		Assert.assertNotNull(productService.getProduct(555));
		//Assert.assertNotNull(productService.getProduct(11)); 
	}

	//@Test 
	public void testUpdateProduct() throws Exception{

		Product product = productService.getProduct(555);
		Assert.assertNotNull(product);

		product.setProdNo(666);
		product.setProdName("aaa");
		product.setProdDetail("aaa");
		product.setManuDate("aaa");
		product.setPrice(666);
		product.setFileName("aaa");

		productService.updateProduct(product);

		product = productService.getProduct(666);
		Assert.assertNotNull(product);

		Assert.assertEquals(666, product.getProdNo());
		Assert.assertEquals("aaa",product.getProdName());
		Assert.assertEquals("aaa",product.getProdDetail());
		Assert.assertEquals("zzz", product.getManuDate());
		Assert.assertEquals(666,product.getPrice());
		Assert.assertEquals("aaa",product.getFileName());


	}


	//@Test
	public void testGetProductListAll() throws Exception{

		Search search = new Search(); 
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3,list.size());



		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0"); 
		search.setSearchKeyword(""); 
		map =productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ�� //System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}

	//@Test 
	public void testGetProductListByProductNo() throws Exception{

		Search search = new Search(); 
		search.setCurrentPage(1);
		search.setPageSize(3); 
		search.setSearchCondition("0");
		search.setSearchKeyword("10010");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1,list.size());



		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list"); 
		Assert.assertEquals(0, list.size());



		totalCount = (Integer)map.get("totalCount"); 
		System.out.println(totalCount);
	}

	//@Test 
	public void testGetProductListByProductName() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("testtest");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list"); 
		Assert.assertEquals(1,list.size());



		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("1");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());



		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}

	//@Test 
	public void testGetProductListByProductPrice() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("11");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list"); 
		Assert.assertEquals(1,list.size());



		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("2");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());



		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}

}