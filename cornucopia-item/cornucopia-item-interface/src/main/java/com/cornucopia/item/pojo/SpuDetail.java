package com.cornucopia.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-09-18
 */
@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {

    @Id
    private Long spuId;
    private String description;
    private String specialSpec;
    private String genericSpec;
    private String packingList;
    private String afterService;

}
