package com.baotoan.spring.dto;

import java.util.List;

import com.baotoan.spring.entities.DetailProduct;

public class ProductDetailFormDTO {
	private int productId;
	private List<DetailProduct> detailsProduct;

	public ProductDetailFormDTO() {
	}

	public ProductDetailFormDTO(int productId, List<DetailProduct> detailsProduct) {
		super();
		this.productId = productId;
		this.detailsProduct = detailsProduct;
	}
	
	public boolean isContain(String name, int group) {
		return detailsProduct.contains(new DetailProduct(0, name, 0, "", group));
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<DetailProduct> getDetailsProduct() {
		return detailsProduct;
	}

	public void setDetailsProduct(List<DetailProduct> detailsProduct) {
		this.detailsProduct = detailsProduct;
	}

	@Override
	public String toString() {
		if (null == detailsProduct) {
			return "";
		}
		for (DetailProduct d : detailsProduct) {
			System.out.println(d.toString());
		}
		return super.toString();
	}

}
