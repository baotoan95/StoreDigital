package com.baotoan.spring.dto;

import java.util.ArrayList;
import java.util.List;

import com.baotoan.spring.entities.DetailProduct;

public class ProductDetailFormDTO {
	private int productId;
	private List<DetailProduct> detailsProduct;

	public ProductDetailFormDTO() {
		this.detailsProduct = new ArrayList<DetailProduct>();
	}

	public ProductDetailFormDTO(int productId, List<DetailProduct> detailsProduct) {
		super();
		this.productId = productId;
		this.detailsProduct = detailsProduct;
		if(this.detailsProduct == null) {
			this.detailsProduct = new ArrayList<DetailProduct>();
		}
	}
	
	public String getValue(String name, int group) {
		for(DetailProduct d : detailsProduct) {
			if(d.getName().trim().equals(name) && d.getGroup() == group) {
				return d.getValue();
			}
		}
		return "";
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
