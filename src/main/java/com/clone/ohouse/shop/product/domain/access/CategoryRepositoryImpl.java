package com.clone.ohouse.shop.product.domain.access;


import com.clone.ohouse.shop.product.domain.entity.Category;
import com.clone.ohouse.shop.product.domain.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.clone.ohouse.shop.product.domain.entity.QCategory.category;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Category findCategory(CategorySearch condition) {
        QCategory category1 = new QCategory("c1");
        QCategory category2 = new QCategory("c2");
        QCategory category3 = new QCategory("c3");
        QCategory category4 = new QCategory("c4");


        if(condition.code2 == null){

        }
        else if(condition.code3 == null){

        }
        else if(condition.code4 == null){

        }

//        Category result = queryFactory
//                .select(category4)
//                .from(category4)
//                .join(category4.parent, category3)
//                .join(category3.parent, category2)
//                .join(category2.parent, category1)
//                .where(
//                        category1.code.eq(condition.code1)
//                                .and(category2.code.eq(condition.code2))
//                                .and(category3.code.eq(condition.code3))
//                                .and(category4.code.eq(condition.code4)))
//                .fetchOne();

        return null;
    }
}
