package com.cornucopia.item.pojo;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-09-18
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 和数据库进行映射的叫做po,业务层之间的交互叫做vo。实际开发中应该分离，这里为了方便，才写在一起
 */
@Table(name = "tb_spu")
@Data
public class Spu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private String title;
    private String subTitle;
    private Boolean saleable;
    @JsonIgnore
    private Boolean valid;//是否有效
    private Date createTime;//创建时间

    @JsonIgnore //不让json处理这个字段
    private Date last_update_time;

    @Transient //不让mapper处理这个字段
    private String cname;
    @Transient
    private String bname;

    @Transient
    private List<Sku> skus;

    @Transient
    private SpuDetail spuDetail;
}
