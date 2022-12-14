package com.lp.last.framework.jpa;

import javax.persistence.criteria.Predicate;

/**
 * @author LP
 * @date 2019/3/3
 */
public class SpecificationBuilder<T> {

    public static <T>PredicateBuilder<T> init() {
        return new PredicateBuilder<>();
    }

    public static <T>PredicateBuilder<T> and() {
        return new PredicateBuilder<>(Predicate.BooleanOperator.AND);
    }

    public static <T>PredicateBuilder<T> or() {
        return new PredicateBuilder<>(Predicate.BooleanOperator.OR);
    }
}
