package com.cornucopia.service.item.mapper;

import com.cornucopia.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-29
 */
public interface BrandMapper extends Mapper<Brand> {

    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid,@Param("bid") Long bid);

    @Select("select b.* from tb_category_brand cb inner join tb_brand b on b.id = cb.brand_id where cb" +
            ".category_id=#{cid}")
    List<Brand> queryByCategroryId(@Param("cid") Long cid);

}
