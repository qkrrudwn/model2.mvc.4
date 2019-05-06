package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
//dao접근하는게아니라 접근한dao를 받아먹는애,sqlsession당연히 안씀
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}
	//구매하기
	public void addPurchase(Purchase purchase) throws Exception{
		purchaseDao.addPurchase(purchase);
	}
	//관리자구매상세조회
	public Purchase getPurchase(int tranNo) throws Exception{
		return null;
	}
	//유저구매상세조회
	public Purchase getPurchase2(int ProdNo) throws Exception{
		return purchaseDao.getPurchase2(ProdNo);
	}
	//내가 구매한 상품 리스트
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception{
		
		List<Purchase> list = purchaseDao.getPurchaseList(search, buyerId);
		int totalCount = purchaseDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
		
	}
	//판매관리--배송하기
	public HashMap<String, Object> getSaleList(Search search) throws Exception{
		return null;
	}
	//구매정보수정
	public void updatePurcahse(Purchase purchase) throws Exception{
		purchaseDao.updatePurcahse(purchase);
	}
	//배송현황코드
	public Purchase updateTranCode(Purchase purchase) throws Exception{
		purchaseDao.updateTranCode(purchase);
		return purchase;
	}
	
	
	
}
