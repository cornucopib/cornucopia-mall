package com.cornucopia.service.item.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.SpecialProvider;

import java.util.List;

public interface InsertSkuIdListMapper<T>  {

    @Options(useGeneratedKeys = true, keyProperty = "skuId")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);

}