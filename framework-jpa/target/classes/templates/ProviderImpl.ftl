package ${package_name}.providerImpl;

import ${package_name}.dao.${model_name}Dao;
import com.lp.first.framework.crud.provider.impl.BaseProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import ${package_name}.model.${model_name};
import ${package_name}.provider.${model_name}Provider;

/**
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${model_name}ProviderImpl extends BaseProviderImpl<${model_name}Dao,${model_name}> implements ${model_name}Provider<${model_name}> {

    @Autowired
    private ${model_name}Dao ${model_variable}Dao;

    @Override
    public ${model_name}Dao getDao() {
        return ${model_variable}Dao;
    }
}
