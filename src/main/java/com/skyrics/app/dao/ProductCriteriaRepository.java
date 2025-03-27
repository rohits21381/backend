package com.skyrics.app.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.skyrics.app.entities.Product;
import com.skyrics.app.model.ProductPage;
import com.skyrics.app.model.ProductSearchCriteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

	@Repository
	public class ProductCriteriaRepository {

	    private final EntityManager entityManager;
	    private final CriteriaBuilder criteriaBuilder;

	    public ProductCriteriaRepository(EntityManager entityManager) {
	        this.entityManager = entityManager;
	        this.criteriaBuilder = entityManager.getCriteriaBuilder();
	    }

	    public Page<Product> findAllWithFilters(ProductPage productPage,
	    		ProductSearchCriteria productSearchCriteria){
	        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
	        Root<Product> productRoot = criteriaQuery.from(Product.class);
	        System.out.println("************ cri 1");

	        Predicate predicate = getPredicate(productSearchCriteria, productRoot);
	        System.out.println("************ cri 2");
	        criteriaQuery.where(predicate);
	        System.out.println("************ cri 3");

	        setOrder(productPage, criteriaQuery, productRoot);

	        System.out.println("************ cri 4");

	        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
	        System.out.println("************ cri 5");

	        typedQuery.setFirstResult(productPage.getPAGE_NUMBER() * productPage.getPAGE_Size());
	        System.out.println("************ cri 6");

	        typedQuery.setMaxResults(productPage.getPAGE_Size());
	        System.out.println("************ cri 7");

	        Pageable pageable = getPageable(productPage);
	        System.out.println("************ cri 8");

	        long employeesCount = getEmployeesCount(predicate);
	        System.out.println("************ cri 9");

	        return new PageImpl<>(typedQuery.getResultList(), pageable, employeesCount);
	    }

	    private Predicate getPredicate(ProductSearchCriteria productSearchCriteria,
	                                   Root<Product> productRoot) {
	        List<Predicate> predicates = new ArrayList<>();
	        System.out.println("########### predicates");
	        	        
	        if(Objects.nonNull(productSearchCriteria.getProductName())){
	            predicates.add(
	                    criteriaBuilder.like(productRoot.get("productName"),
	                            "%" + productSearchCriteria.getProductName() + "%")
	            );
	        }
	        if(Objects.nonNull(productSearchCriteria.getProductBrand())){
	            predicates.add(
	                    criteriaBuilder.like(productRoot.get("productBrand"),
	                            "%" + productSearchCriteria.getProductBrand() + "%")
	            );
	        }
	        if(Objects.nonNull(productSearchCriteria.getProductCatagoryTitle())){
	            predicates.add(
	                    criteriaBuilder.like(productRoot.get("productCatagoryTitle"),
	                            "%" + productSearchCriteria.getProductCatagoryTitle() + "%")
	            );
	        }
	        if(Objects.nonNull(productSearchCriteria.getProductColor())){
	            predicates.add(
	                    criteriaBuilder.like(productRoot.get("productColor"),
	                            "%" + productSearchCriteria.getProductColor() + "%")
	            );
	        }
	        if(Objects.nonNull(productSearchCriteria.getProductPrice())){
	            predicates.add(
	                    criteriaBuilder.like(productRoot.get("productPrice"),
	                            "%" + productSearchCriteria.getProductPrice() + "%")
	            );
	        }
	        if(Objects.nonNull(productSearchCriteria.getProductType())){
	            predicates.add(
	                    criteriaBuilder.like(productRoot.get("productType"),
	                            "%" + productSearchCriteria.getProductType() + "%")
	            );
	        }
	        System.out.println("########### predicates End");

	        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	    }

	    private void setOrder(ProductPage productPage,
	                          CriteriaQuery<Product> criteriaQuery,
	                          Root<Product> productRoot) {
	        if(productPage.getSortDirection().equals(Sort.Direction.ASC)){
	            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPage.getSORT_BY())));
	        } else {
	            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPage.getSORT_BY())));
	        }
	    }

	    private Pageable getPageable(ProductPage productPage) {
	        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSORT_BY());
	        return PageRequest.of(productPage.getPAGE_NUMBER(),productPage.getPAGE_Size(), sort);
	    }

	    private long getEmployeesCount(Predicate predicate) {
	        System.out.println("************ cri 8.1");

	        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
	        System.out.println("************ cri 8.2");

	        Root<Product> countRoot = countQuery.from(Product.class);
	        System.out.println("************ cri 8.3");

	        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
	        System.out.println("************ cri 8.4");
	        
	        Long singleResult = entityManager.createQuery(countQuery).getSingleResult();
	        System.out.println("************ cri 8.5");

	        return singleResult;
	    }
}
