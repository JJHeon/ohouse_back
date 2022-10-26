package com.clone.ohouse.shop.store.domain.access;

import com.clone.ohouse.shop.product.domain.access.CategorySearch;
import com.clone.ohouse.shop.product.domain.entity.QItem;
import com.clone.ohouse.shop.product.domain.entity.QItemCategory;
import com.clone.ohouse.shop.product.domain.entity.QProduct;
import com.clone.ohouse.shop.store.domain.entity.QStorePosts;
import com.clone.ohouse.shop.store.domain.entity.StorePosts;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static com.clone.ohouse.shop.product.domain.entity.QItem.*;
import static com.clone.ohouse.shop.product.domain.entity.QItemCategory.*;
import static com.clone.ohouse.shop.product.domain.entity.QProduct.*;
import static com.clone.ohouse.shop.store.domain.entity.QStorePosts.*;


@RequiredArgsConstructor
public class StorePostsRepositoryImpl implements StorePostsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StorePosts> getBundleViewByCategoryWithConditionV1(Long categoryId, Pageable pageable) {
    return queryFactory
                .select(storePosts)
                .from(storePosts)
                .where(storePosts.id.in(
                        JPAExpressions
                                .select(product.storePosts.id)
                                .from(product)
                                .join(product.item, item)
                                .join(item.itemCategories, itemCategory)
                                .where(itemCategory.category.id.eq(categoryId))
                                .orderBy(product.popular.desc())
                        ))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
//        QStorePosts qStorePosts = new QStorePosts("Q");
//        QProduct qProduct = new QProduct("P");
//        return queryFactory
//                .select(storePosts)
//                .from(storePosts)
//                .where(storePosts.id.in(JPAExpressions
//                        .select(qStorePosts.id)
//                        .from(qProduct)
//                        .join(qProduct.storePosts, qStorePosts)
//                        .where(qStorePosts.id.in(1L, 2L, 3L))))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
    }

    @Override
    public List<StorePosts> getBundleViewByCategoryWithConditionV2(Long categoryId, Pageable pageable) {
        return queryFactory
                .select(storePosts).distinct()
                .from(product)
                .join(product.storePosts, storePosts)
                .join(product.item, item)
                .join(item.itemCategories, itemCategory)
                .where(itemCategory.category.id.eq(categoryId).and(product.popular.gt(50)))
//                .where(itemCategory.category.id.eq(categoryId))
                .orderBy(product.popular.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<StorePosts> getBundleViewByCategoryWithConditionV3(Long categoryId, Pageable pageable) {
        List<StorePosts> result = new ArrayList<>();
        List<Tuple> tuples = queryFactory
                .select(storePosts, product.popular.sum()).distinct()
                .from(storePosts)
                .join(storePosts.productList, product)
                .join(product.item, item)
                .join(item.itemCategories, itemCategory)
                .where(itemCategory.category.id.eq(categoryId))
                .groupBy(storePosts.id)
                .orderBy(product.popular.sum().asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        for (Tuple tuple : tuples) {
            result.add(tuple.get(storePosts));
            System.out.println("product.sum = " + tuple.get(product.popular.sum()).toString());
        }
        return result;
    }
}
