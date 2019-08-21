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
        if(CollectionUtils.isEmpty(list)){
            throw new ResponseException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
