package ${package_name}.controller;

import ${package_name}.model.${model_name};
import ${package_name}.provider.${model_name}Provider;
import com.lp.first.framework.crud.controller.BaseController;
import com.lp.first.framework.crud.provider.BaseProvider;
import io.swagger.annotations.Api;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${author}
 * @date ${date}
 */
@Api("${table_annotation} 控制层")
@CrossOrigin
@RestController
@RequestMapping("/${model_variable}")
public class ${model_name}Controller extends BaseController<${model_name}> {

    @Reference
    private ${model_name}Provider ${model_variable}Provider;

    @Override
    public BaseProvider<${model_name}> getProvider() {
        return ${model_variable}Provider;
    }
}
