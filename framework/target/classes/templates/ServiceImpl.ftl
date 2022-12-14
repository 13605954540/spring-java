package ${package_name}.serviceImpl;

import ${package_name}.dao.${model_name}Dao;
import com.lp.first.framework.crud.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${package_name}.model.${model_name};
import ${package_name}.service.${model_name}Service;

/**
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${model_name}ServiceImpl extends BaseServiceImpl<${model_name}Dao,${model_name}> implements ${model_name}Service<${model_name}> {

    @Autowired
    private ${model_name}Dao ${model_variable}Dao;

    @Override
    public ${model_name}Dao getDao() {
        return ${model_variable}Dao;
    }
}
