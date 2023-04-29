package com.project.shopmobile.specification;

import com.project.shopmobile.entity.Items;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;

public class ItemsSpecification {

    public static Specification<Items> filterItems(String search, Integer type) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            Collection<Predicate> finalPredicates = new ArrayList<>();
            Collection<Predicate> searchPredicates = new ArrayList<>();

            // 'search' like 'title' or 'description'
            if (StringUtils.hasText(search)) {
                String searchLowerCase = "%" + search.trim().toLowerCase() + "%";
                searchPredicates
                        .add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), searchLowerCase));
                searchPredicates
                        .add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchLowerCase));
                finalPredicates.add(criteriaBuilder.or(searchPredicates.toArray(new Predicate[0])));
            }

            if (null != type) {
                finalPredicates.add(criteriaBuilder.equal(root.get("type"), type));
            }

            return criteriaBuilder.and(finalPredicates.toArray(new Predicate[0]));
        };
    }
}
