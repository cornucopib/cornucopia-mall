package com.cornucopia.service.item.service;

import com.cornucopia.common.enums.ExceptionEnum;
import com.cornucopia.common.exception.ResponseException;
import com.cornucopia.item.pojo.Category;
import com.cornucopia.service.item.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 分类管理
 * <p>
 * 普通数据查询
 *
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-15
 */

@Service
public class CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> getCategoryListByPid(Long pid) {
        Category t = new Category();
        t.setParentId(pid);
        List<Category> list = categoryMapper.select(t);
        if (CollectionUtils.isEmpty(list)) {
            throw new ResponseException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    /**
     * 通过ids查询分类列表
     * @param ids
     * @return
     */
    public List<Category> queryByIds(List<Long> ids) {
        List<Category> list = categoryMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(list)) {
            throw new ResponseException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

}
