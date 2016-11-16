package com.baotoan.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.DetailProduct;
import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.MenuCate;
import com.baotoan.spring.entities.Product;
import com.baotoan.spring.mapper.DetailProductGroupMapper;
import com.baotoan.spring.mapper.DetailProductMapper;
import com.baotoan.spring.mapper.MenuCateMapper;
import com.baotoan.spring.mapper.ProductMapper;
import com.baotoan.spring.utils.Constant;
import com.baotoan.spring.utils.Pagination;

@Repository("productDAO")
public class ProductDAOImpl extends BaseDAO implements ProductDAO {
	private ImageDAO imageDAO = new ImageDAOImpl();
	
	public boolean addProduct(Product product) {
		String sql = "insert into prods(name,old_price,new_price,views,reviews,tags,postId,promotionId,importDate,cateId,describe)"
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{product.getName(), product.getOldPrice(), product.getNewPrice(),
				product.getViews(), product.getReviews(), product.getTags(), product.getPostId(), product.getPromotionId(),
				product.getImportDate(), product.getCateId(), product.getDescribe()}) > 0);
	}

	public boolean updateProduct(Product product) {
		String sql = "update prods set name=?,old_price=?,new_price=?,views=?,reviews=?,tags=?,"
				+ "postId=?,promotionId=?,importDate=?,cateId=?,describe=? WHERE id=?";
		return (jdbcTemplate.update(sql, new Object[]{product.getName(), product.getOldPrice(), product.getNewPrice(),
				product.getViews(), product.getReviews(), product.getTags(), product.getPostId(), product.getPromotionId(),
				product.getImportDate(), product.getCateId(), product.getDescribe(), product.getId()}) > 0);
	}

	public boolean deleteProduct(int id) {
		String sql = "delete * FROM prods WHERE id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public Product getProductById(int id) {
		String sql = "SELECT * FROM prods WHERE id=?";
		Product product = null;
		try {
			product = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductMapper());
			product.setUrlImage(imageDAO.getAvatarForProduct(product.getId()).getUrl());
		} catch (Exception e) {}
		return product;
	}

	public boolean addDetailProductGroup(DetailProductGroup detailProductGroup) {
		String sql = "insert into prod_group_detail(name) values(?)";
		return (jdbcTemplate.update(sql, new Object[]{detailProductGroup.getName()}) > 0);
	}

	public boolean updateDetailProductGroup(DetailProductGroup detailProductGroup) {
		String sql = "update prod_group_detail set name=? WHERE id=?";
		return (jdbcTemplate.update(sql, new Object[]{detailProductGroup.getName(), detailProductGroup.getId()}) > 0);
	}

	public boolean deleteDetailProductGroup(int id) {
		String sql = "delete * FROM prod_group_detail WHERE id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public DetailProductGroup getDetailProductGroupById(int id) {
		String sql = "SELECT * FROM prod_group_detail WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DetailProductGroupMapper());
	}

	public List<DetailProductGroup> getDetailProductGroups() {
		String sql = "SELECT * FROM prod_group_detail";
		return jdbcTemplate.query(sql, new DetailProductGroupMapper());
	}

	public boolean addDetailProduct(DetailProduct detailProduct) {
		String sql = "insert into prod_detail(name,productId,value,groupId) values(?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{detailProduct.getName(), detailProduct.getProductId(), 
				detailProduct.getValue(), detailProduct.getGroup()}) > 0);
	}

	public boolean updateDetailProduct(DetailProduct detailProduct) {
		String sql = "update prod_detail set name=?,productId=?,value=?,groupId=? WHERE id=?";
		return (jdbcTemplate.update(sql, new Object[]{detailProduct.getName(), detailProduct.getProductId(), 
				detailProduct.getValue(), detailProduct.getGroup(), detailProduct.getId()}) > 0);
	}

	public boolean deleteDetailProduct(int id) {
		String sql = "delete * FROM prod_detail WHERE id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public boolean deleteDetailProductByProductId(int productId) {
		String sql = "delete * FROM prod_detail WHERE productId=?";
		return (jdbcTemplate.update(sql, new Object[]{productId}) > 0);
	}
	
	public boolean deleteDetailProductByGroup(int groupId) {
		String sql = "delete * FROM prod_detail WHERE groupId=?";
		return (jdbcTemplate.update(sql, new Object[]{groupId}) > 0);
	}
	
	public DetailProduct getDetailProductById(int id) {
		String sql = "SELECT * FROM prod_detail WHERE productId=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DetailProductMapper());
	}

	public List<DetailProduct> getDetailProductsByGroupId(int detailProductGroupId) {
		String sql = "SELECT * FROM prod_detail WHERE groupId=?";
		return jdbcTemplate.query(sql, new Object[]{detailProductGroupId}, new DetailProductMapper());
	}
	
	public Map<String, Object> getProducts(Object key, int getBy, int sortBy, int numRecordPerPage, int currentPage) {
		String sql = "";
		String sort = "";
		
		switch (sortBy) {
		case Constant.SORT_BY_ASCENDING:
			sort = " ORDER BY prods.new_price ASC";
			break;
		case Constant.SORT_BY_DECREASE:
			sort = " ORDER BY prods.new_price DESC";
			break;
		case Constant.SORT_BY_NAME:
			sort = " ORDER BY prods.name ASC";
			break;
		case Constant.SORT_BY_LOW:
			sort = " AND prods.new_price <= 5000000";
			break;
		case Constant.SORT_BY_NORMAL:
			sort = " AND prods.new_price > 5000000 AND prods.new_price <= 15000000";
			break;
		case Constant.SORT_BY_HEIGHT:
			sort = " AND prods.new_price > 15000000";
			break;
		case Constant.DEFAULT:
			sort = "";
			break;
		}
		String spec = "";
		if(sortBy == Constant.SORT_BY_LOW || sortBy == Constant.SORT_BY_NORMAL || sortBy == Constant.SORT_BY_HEIGHT) {
			spec = sort;
		}
		
		switch (getBy) {
		case Constant.GET_BY_PROMOTION:
			sql = "SELECT * FROM prods WHERE promotionId is not null";
			break;
		case Constant.GET_BY_TOP_REVIEW:
			sql = "SELECT * FROM prods ORDER BY reviews DESC";
			break;
		case Constant.GET_BY_TOP_VIEW:
			sql = "SELECT * FROM prods ORDER BY views desc";
			break;
		case Constant.GET_BY_LATEST:
			sql = "SELECT * FROM prods ORDER BY importDate desc";
			break;
		case Constant.GET_BY_BEST_SELL:
			sql = "SELECT prods.id, prods.`name`, prods.old_price, prods.new_price, prods.views, "
			+ "prods.reviews, prods.tags, prods.postId, prods.promotionId, prods.importDate, "
			+ "prods.cateId, prods.describe, SUM(order_detail.quantity) quantity" 
			+ " FROM prods INNER JOIN order_detail on prods.id = order_detail.prodId "
			+ "GROUP BY order_detail.prodId ORDER BY quantity DESC";
			break;
		case Constant.GET_BY_CATE:
			sql = "SELECT prods.id, prods.`name`, prods.old_price, prods.new_price, prods.views, "
			+ "prods.reviews, prods.tags, prods.postId, prods.promotionId, prods.importDate, "
			+ "prods.cateId, prods.describe FROM prods INNER JOIN menu on menu.id=prods.cateId and menu.parentId=" + key + sort;
			break;
		case Constant.GET_BY_MANUFACTURER:
			sql = "SELECT prods.id, prods.`name`, prods.old_price, prods.new_price, prods.views, "
			+ "prods.reviews, prods.tags, prods.postId, prods.promotionId, prods.importDate, "
			+ "prods.cateId, prods.describe FROM prods INNER JOIN menu on prods.cateId=" + key + sort;
			break;
		case Constant.GET_BY_RELATED:
			sql = "SELECT * FROM prods WHERE cateId=" + key;
			break;
		case Constant.GET_BY_UPSELL:
			sql = "SELECT * FROM prods WHERE new_price <= " + key + " or new_price >= " + key;
			break;
		case Constant.SEARCH_DEFAULT:
			sql = "SELECT * FROM prods WHERE name LIKE '%"+key+"%'"+spec
				+" or name LIKE '%"+key+"'"+spec
				+" or name LIKE '"+key+"%'" + sort;
			break;
		case Constant.SEARCH_ADVENCE:
			String[] param = ((String)key).split("-");
			int cateId = Integer.parseInt(param[0].trim());
			String keyWord = param[1].trim();
			MenuCate check = null;
			try {
				check = (MenuCate)jdbcTemplate.queryForObject("SELECT * FROM menu WHERE id=" + cateId, new MenuCateMapper()); 
			} catch (Exception e) {
				check = new MenuCate();
				check.setParentId(10);
			}
			if(check.getParentId() == 0) {
				sql = "SELECT * from (select prods.id, prods.`name`, prods.old_price, prods.new_price, prods.views, "
					+ "prods.reviews, prods.tags, prods.postId, prods.promotionId, prods.importDate,"
					+ "prods.cateId, prods.describe FROM prods INNER JOIN menu on prods.cateId = menu.id and parentId = "+cateId+") "
					+ "as prods where prods.name like '%"+keyWord+"%' or prods.name like '%"+keyWord+"' or prods.name like '"+keyWord+"%'" + sort;
			} else {
				sql = "SELECT * FROM prods WHERE name LIKE '%"+ keyWord +"%' AND cateId=" + cateId
						+ " OR name LIKE '%"+ keyWord +"' AND cateId=" + cateId 
						+ " OR name LIKE '"+ keyWord +"%' AND cateId=" + cateId + sort;
			}
			break;
		case Constant.DEFAULT:
			sql = "SELECT prods.id, prods.`name`, prods.old_price, prods.new_price, prods.views, "
			+ "prods.reviews, prods.tags, prods.postId, prods.promotionId, prods.importDate, "
			+ "prods.cateId, prods.describe FROM prods INNER JOIN menu on prods.cateId=" + key + sort;
			break;
		default:
			sql = "SELECT * FROM prods";
			break;
		}
		
		List<Product> total = jdbcTemplate.query(sql, new ProductMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForGrid = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.GRID_PAGE);
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
//		System.out.println(sql);
		List<Product> listResult = jdbcTemplate.query(sql, new ProductMapper());
		for(int i = 0; i < listResult.size(); i++) {
			Product product = listResult.get(i);
			product.setUrlImage(imageDAO.getAvatarForProduct(product.getId()).getUrl());
			product.setListImage(imageDAO.getImagesByProductId(product.getId()));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("products", listResult);
		result.put("html", htmlForGrid);
		result.put("htmlForToDoList", htmlForToDoList);
		
		return result;
	}
	
	public List<DetailProduct> getDetailProductsByProductId(int productId) {
		String sql = "select * from prod_detail where productId = ?";
		return jdbcTemplate.query(sql, new Object[]{productId}, new DetailProductMapper());
	}

	public Map<String, Map<String, DetailProduct>> getDetailProduct(int productId) {
		Map<String, Map<String, DetailProduct>> detail = new TreeMap<String, Map<String, DetailProduct>>();
		
		List<DetailProduct> listDetail = getDetailProductsByProductId(productId);
		for(DetailProduct dp : listDetail) {
			String group = getDetailProductGroupById(dp.getGroup()).getName();
			if(!detail.containsKey(group)) {
				detail.put(group, new HashMap<String, DetailProduct>());
			}
			Map<String, DetailProduct> listOldDetail = detail.get(group);
			listOldDetail.put(dp.getName(), dp);
			detail.put(group, listOldDetail);
		}
		return detail;
	}

	@SuppressWarnings("deprecation")
	public int getTotalProduct() {
		String sql = "select count(id) from prods";
		int total = 0;
		try {
			total = jdbcTemplate.queryForInt(sql);
		} catch (Exception e) { }
		return total;
	}

	public List<Product> getProductsByPostId(int id) {
		String sql = "select * from prods where postId=?";
		List<Product> result = null;
		try {
			result = jdbcTemplate.query(sql, new Object[] {id}, new ProductMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
