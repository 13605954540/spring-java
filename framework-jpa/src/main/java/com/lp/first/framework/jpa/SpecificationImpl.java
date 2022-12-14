package com.lp.first.framework.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;

/**
 * @author LP
 * @date 2019/3/9
 */
public class SpecificationImpl<T> implements Specification<T> {

    private OperationTypeEnum operationTypeEnum;

    private String field;

    private Object[] values;

    public SpecificationImpl(OperationTypeEnum operationTypeEnum, String field, Object... value) {
        this.operationTypeEnum = operationTypeEnum;
        this.field = field;
        this.values = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        From from = getRoot(root);
        this.field = getField();
        Predicate predicate = null;
        switch (operationTypeEnum) {
            case EQ:
                predicate = criteriaBuilder.equal(from.get(field), values[0]);
                break;
            case NE:
                predicate = criteriaBuilder.notEqual(from.get(field), values[0]);
                break;
            case GT:
                predicate = criteriaBuilder.gt(from.get(field), (Expression<? extends Number>) values[0]);
                break;
            case GE:
                predicate = criteriaBuilder.ge(from.get(field), (Expression<? extends Number>) values[0]);
                break;
            case LT:
                predicate = criteriaBuilder.lt(from.get(field), (Expression<? extends Number>) values[0]);
                break;
            case LE:
                predicate = criteriaBuilder.le(from.get(field), (Expression<? extends Number>) values[0]);
                break;
            case BETWEEN:
                Assert.notNull(values, "范围查询参数缺失");
                if(values.length != 2){
                    throw new RuntimeException("范围查询参数数量不为2，请检查参数数量");
                }
                Comparable low = (Comparable) values[0];
                Comparable high = (Comparable) values[1];
                predicate = criteriaBuilder.between(from.get(field), low, high);
                break;
            case LIKE:
                predicate = criteriaBuilder.like(from.get(field), String.valueOf(values[0]));
                break;
            case NOT_LIKE:
                predicate = criteriaBuilder.notLike(from.get(field), String.valueOf(values[0]));
                break;
            case IN:
                predicate = from.get(field).in(values);
                break;
            case NOT_IN:
                predicate = from.get(field).in(values).not();
                break;
        }
        return predicate;
    }

    public From getRoot(Root<T> root) {
        if(field.contains(".")) {
            return root.join(field.split("\\.")[0],JoinType.LEFT);
        }
        return root;
    }

    public String getField() {
        if(field.contains(".")) {
            return field.split("\\.")[1];
        }
        return field;
    }
}
