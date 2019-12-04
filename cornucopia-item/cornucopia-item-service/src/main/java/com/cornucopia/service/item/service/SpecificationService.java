package com.cornucopia.service.item.service;

import com.cornucopia.common.enums.ExceptionEnum;
import com.cornucopia.common.exception.ResponseException;
import com.cornucopia.item.pojo.SpecGroup;
import com.cornucopia.item.pojo.SpecParam;
import com.cornucopia.service.item.mapper.SpecGroupMapper;
import com.cornucopia.service.item.mapper.SpecParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 * @author cornucopia
 * @version 1.0
 * @since 2019-09-15
 */
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    public List<SpecGroup> queryGroupByCid(Long cid){
        SpecGroup group=new SpecGroup();
        group.setCid(cid);
        List<SpecGroup> list=groupMapper.select(group);
        if(CollectionUtils.isEmpty(list)){
            throw new ResponseException(ExceptionEnum.SPEC_GROUP_NOT_FIND);
        }
        return list;
    }


    /**
     * 查询列表
     * @param gid
     * @param cid
     * @param searching
     * @return
     */
    public List<SpecParam> queryParamList(Long gid, Long cid, boolean searching) {
        SpecParam param=new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(searching);
        List<SpecParam> list=paramMapper.select(param);
        if(CollectionUtils.isEmpty(list)){
            throw new ResponseException(ExceptionEnum.SPEC_PARAM_NOT_FIND);
        }
        return list;

    }
}
