package com.lp.last.framework.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LP
 * @date 2018/5/1
 */
public class ListUtil {

    /**
     * 将obj转换成list(按,隔开)
     * @param obj
     * @return
     */
    public static List<?> objectToList(Object obj){
        List<?> list = new ArrayList();
        if(obj != null){
            Object[] strs = obj.toString().split(",");
            list = Arrays.asList(strs);
        }else{
            list =  null;
        }
        return list;
    }

    /**
     * 计算按以单位多少条拆分list的总数
     * @param list
     * @param pageSize
     * @return
     */
    public static Integer getRowSize(List<?> list,Integer pageSize){
        return list.size() % pageSize == 0 ? list.size()/pageSize : (list.size()/pageSize + 1);
    }

    /**
     * 按rows条拆分成二维数组(批量操作)
     * @param list 要拆分的list
     * @param rows 按多少条数拆分
     * @return
     */
    public static List<List<?>> getLists(List<?> list,Integer rows){
        int total = list.size();
        int page = total%rows == 0 ? total/rows : total/rows+1;
        List<List<?>> res = new ArrayList();
        int start = 0;
        int end = 0;
        for(int i = 0;i<page;i++){
            start = i * rows;
            end = i == page - 1 ? total : (start + rows);
            res.add(list.subList(start,end));
        }
        return res;
    }
}
