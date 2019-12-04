package com.cornucopia.service.item.mapper;

import com.cornucopia.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通过mapper扩展功能，通过id查询数据列表
 *
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-15
 */
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category,Long> {
}
