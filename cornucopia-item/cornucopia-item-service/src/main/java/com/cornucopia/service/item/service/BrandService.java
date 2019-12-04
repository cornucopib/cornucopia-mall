package com.cornucopia.service.item.service;

import com.cornucopia.common.enums.ExceptionEnum;
import com.cornucopia.common.exception.ResponseException;
import com.cornucopia.common.vo.PageResult;
import com.cornucopia.item.pojo.Brand;
import com.cornucopia.service.item.mapper.BrandMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 品牌管理
 * <p>
 * 分页
 *
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-29
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(Integer page,
                                              Integer rows,
                                              String sortBy,
                                              Boolean desc,
                                              String key) {
        //分页
        PageHelper.startPage(page, rows);
        //过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            //过滤条件
            example.createCriteria().orLike("name", "%" + key + "%")
                    .orEqualTo("letter", key.toLowerCase());
        }
        //排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new ResponseException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        PageInfo<Brand> info = new PageInfo<>(list);
        return new PageResult<>(info.getTotal(), list);
    }


    /**
     * @param brand
     * @param cids
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        /**
         *新增品牌
         */
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if (count != 1) {
            throw new ResponseException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        //新增中间表
        for (Long cid : cids) {
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (count != 1) {
                throw new ResponseException(ExceptionEnum.BRAND_NOT_FOUND);
            }
        }

    }

    /**
     * @param id
     * @return
     */
    public Brand queryById(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (brand == null) {
            throw new ResponseException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return brand;
    }

    /**
     * @param cid
     * @return
     */
    public List<Brand> queryBrandByCid(Long cid) {
        List<Brand> list = brandMapper.queryByCategroryId(cid);
        if(CollectionUtils.isEmpty(list)){
            throw new ResponseException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return list;
    }
}
