package com.project.shopmobile.service;

import com.project.shopmobile.dto.ListRes;
import com.project.shopmobile.entity.Items;
import com.project.shopmobile.repository.TypeItemsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemsService {
    private final R2dbcEntityTemplate entityTemplate;
    private final TypeItemsRepository typeItemsRepository;
    public ItemsService(R2dbcEntityTemplate entityTemplate, TypeItemsRepository typeItemsRepository) {
        this.entityTemplate = entityTemplate;
        this.typeItemsRepository = typeItemsRepository;
    }

    public Mono<?> getAllItems(Integer type, String search, Integer page, Integer size) {
        Criteria criteria = Criteria.empty();
        criteria = criteria.and("type").is(type);
        if (StringUtils.hasText(search)) {
            Criteria criteriaSearch = Criteria.empty().and("title").like("%" + search + "%")
                    .or("description").like("%" + search + "%");
            criteria = criteria.and(criteriaSearch);
        }
        Query query = Query.query(criteria);
        Pageable pageable = PageRequest.of(page, size);
        return entityTemplate.select(query.with(pageable), Items.class)
                .collectList()
//                .zipWith(entityTemplate.count(query, Items.class))
                .zipWith(entityTemplate.select(query, Items.class).collectList().map(data -> Long.valueOf(data.size())))
                .map(tuples ->
                        ListRes
                                .builder()
                                .data(tuples.getT1())
                                .count(tuples.getT2())
                                .build()
                );

    }

    public Flux<?> getAllTypeItems() {
        return typeItemsRepository.findAll();
    }
}
