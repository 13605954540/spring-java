package com.lp.first.framework.jpa;

import org.assertj.core.util.Lists;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

import static javax.persistence.criteria.Predicate.BooleanOperator.OR;

/**
 * @author LP
 * @date 2019/3/3
 */
public class PredicateBuilder<T> {

    private Predicate.BooleanOperator booleanOperator;

    private List<Specification<T>> specifications = Lists.newArrayList();


    public PredicateBuilder() {
        this.booleanOperator = Predicate.BooleanOperator.AND;
    }

    public PredicateBuilder(Predicate.BooleanOperator booleanOperator) {
        this.booleanOperator = booleanOperator;
    }

    public PredicateBuilder<T> eq(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.EQ,property,values));
    }

    public PredicateBuilder<T> eq(boolean bool,String property,Object... values) {
        if(bool == true) {
            eq(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> ne(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.NE,property,values));
    }

    public PredicateBuilder<T> ne(boolean bool,String property,Object... values) {
        if(bool == true) {
            ne(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> gt(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.GT,property,values));
    }

    public PredicateBuilder<T> gt(boolean bool,String property,Object... values) {
        if(bool == true) {
            gt(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> ge(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.GE,property,values));
    }

    public PredicateBuilder<T> ge(boolean bool,String property,Object... values) {
        if(bool == true) {
            ge(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> lt(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.LT,property,values));
    }

    public PredicateBuilder<T> lt(boolean bool,String property,Object... values) {
        if(bool == true) {
            lt(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> le(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.LE,property,values));
    }

    public PredicateBuilder<T> le(boolean bool,String property,Object... values) {
        if(bool == true) {
            le(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> between(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.BETWEEN,property,values));
    }

    public PredicateBuilder<T> between(boolean bool,String property,Object... values) {
        if(bool == true) {
            between(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> like(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.LIKE,property,values));
    }

    public PredicateBuilder<T> like(boolean bool,String property,Object... values) {
        if(bool == true) {
            like(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> leftLike(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.LIKE,property,"%" + values[0]));
    }

    public PredicateBuilder<T> leftLike(boolean bool,String property,Object... values) {
        if(bool == true) {
            leftLike(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> rightLike(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.LIKE,property,values[0] + "%"));
    }

    public PredicateBuilder<T> rightLike(boolean bool,String property,Object... values) {
        if(bool == true) {
            rightLike(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> notLike(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.NOT_LIKE,property,values));
    }

    public PredicateBuilder<T> notLike(boolean bool,String property,Object... values) {
        if(bool == true) {
            notLike(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> in(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.IN,property,values));
    }

    public PredicateBuilder<T> in(boolean bool,String property,Object... values) {
        if(bool == true) {
            in(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> notIn(String property , Object...values) {
        return predicate(new SpecificationImpl<>(OperationTypeEnum.NOT_IN,property,values));
    }

    public PredicateBuilder<T> notIn(boolean bool,String property,Object... values) {
        if(bool == true) {
            notIn(property,values);
        }
        return this;
    }

    public PredicateBuilder<T> predicate(Specification specification) {
        this.specifications.add(specification);
        return this;
    }

    public Specification<T> build() {
        return (Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            Predicate[] predicates = new Predicate[specifications.size()];
            for (int i = 0; i < specifications.size(); i++) {
                predicates[i] = specifications.get(i).toPredicate(root, criteriaQuery, criteriaBuilder);
            }
            if (Objects.equals(predicates.length, 0)) {
                return null;
            }
            return OR.equals(booleanOperator) ? criteriaBuilder.or(predicates) : criteriaBuilder.and(predicates);
        };
    }
}
