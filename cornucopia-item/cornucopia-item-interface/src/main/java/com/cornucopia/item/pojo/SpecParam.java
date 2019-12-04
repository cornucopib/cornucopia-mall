package com.cornucopia.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-09-17
 */
@Table(name="tb_spec_param")
@Data
public class SpecParam {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @Column(name = "`numeric`")
    //numeric在sql中是关键字，需要进行转义
    private Boolean numberic;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;


}
