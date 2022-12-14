package ${package_name}.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lp.first.framework.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ${author}
 * @date ${date}
 */
@Getter
@Setter
@Entity(name = "${table_name_small}")
@ApiModel("${table_annotation}模型")
public class ${model_name} extends BaseEntity {

    private static final long serialVersionUID = 1L;

<#if model_column?exists>
    <#list model_column as model>
    @ApiModelProperty("${model.columnComment}")
        <#if (model.columnType = 'varchar' || model.columnType = 'text')>
    private String ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'timestamp' || model.columnType = 'date' || model.columnType = 'datetime'>
    private Date ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'tinyint' >
    private Boolean ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'int' >
    private Integer ${model.changeColumnName?uncap_first};

        </#if>
    </#list>
</#if>
}