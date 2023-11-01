package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

@Component

public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withHighPrice(params.getPriceGt()))
                .and(withLowPrice(params.getPriceLt()))
                .and(withHighRating(params.getRatingGt()))
                .and(withTitle(params.getTitleCont()));
        // .and(withCreatedAtGt(params.getCreatedAtGt()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {

        return (root, query, cb) -> categoryId == null ?
                cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withHighPrice(Integer priceGt) {
        return ((root, query, cb) -> priceGt == null ? cb.conjunction() :
                cb.greaterThan(root.get("price"), priceGt));
    }

    private Specification<Product> withLowPrice(Integer priceLt) {
        return ((root, query, cb) -> priceLt == null ? cb.conjunction() :
                cb.lessThan(root.get("price"), priceLt));
    }

    private Specification<Product> withHighRating(Double ratingGt) {
        return ((root, query, cb) -> ratingGt == null ? cb.conjunction() :
                cb.greaterThan(root.get("rating"), ratingGt));
    }

    private Specification<Product> withTitle(String titleCont) {
        return ((root, query, cb) -> titleCont == null ? cb.conjunction() :
                cb.like(root.get("title"), titleCont.toLowerCase()));
    }


}
