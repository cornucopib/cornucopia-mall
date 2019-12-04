package com.cornucopia.service.item.service;

import com.cornucopia.common.enums.ExceptionEnum;
import com.cornucopia.common.exception.ResponseException;
import com.cornucopia.common.vo.PageResult;
import com.cornucopia.item.pojo.*;
import com.cornucopia.service.item.mapper.SkuMapper;
import com.cornucopia.service.item.mapper.SpuDetailMapper;
import com.cornucopia.service.item.mapper.SpuMapper;
import com.cornucopia.service.item.mapper.StockMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-09-18
 */
@Service
public class GoodsService {


    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private SpuDetailMapper detailMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;


    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        //分页
        PageHelper.startPage(page, rows);
        //过滤
        Example example = new Example(Spu.class);
        //搜索字段过滤
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        //上下架
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        //排序
        example.setOrderByClause("last_update_time DESC");
        List<Spu> spus = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spus)) {
            throw new ResponseException(ExceptionEnum.GOODS_NOT_FOND);
        }
        loadCategoryAndBrandName(spus);
        //分析分页结果
        PageInfo<Spu> info = new PageInfo<>(spus);
        return new PageResult<>(info.getTotal(), spus);
    }

    /**
     * 加载分类以及品牌名称
     */
    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            //处理分类名称
            List<String> names = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names, "/"));
            //处理品牌名称
            spu.setBname(brandService.queryById(spu.getBrandId()).getName());
        }
    }

    /**
     * 保存商品
     *
     * @param spu
     */
    @Transactional
    public void saveGoods(Spu spu) {
        //新增spu
        spu.setCreateTime(new Date());
        spu.setId(null);
        spu.setLast_update_time(spu.getCreateTime());
        spu.setSaleable(true);
        spu.setValid(false);
        int count = spuMapper.insert(spu);
        if (count != 1) {
            throw new ResponseException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        //新增detail
        SpuDetail detail = spu.getSpuDetail();
        detail.setSpuId(spu.getId());
        detailMapper.insert(detail);

        //定义库存集合
        List<Stock> stocks = new ArrayList<>();
        //新增sku
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());

            count = skuMapper.insert(sku);
            if (count != 1) {
                throw new ResponseException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
            //新增库存
            Stock stock = new Stock();

            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stocks.add(stock);
        }
        //批量新增库存
        stockMapper.insertList(stocks);
    }

    /**
     * @param spuId
     * @return
     */
    public SpuDetail queryDetailById(Long spuId) {
        SpuDetail detail = detailMapper.selectByPrimaryKey(spuId);
        if (detail == null) {
            throw new ResponseException(ExceptionEnum.GOODS_DETAIL_NOT_FOND);
        }
        return detail;
    }

    /**
     * @param spuId
     * @return
     */
    public List<Sku> querySkuBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)) {
            throw new ResponseException(ExceptionEnum.GOODS_SKU_NOT_FOND);
        }
        //查询库存
//        for (Sku s : skuList) {
//            Stock stock = stockMapper.selectByPrimaryKey(s.getId());
//            if (stock == null) {
//                throw new ResponseException(ExceptionEnum.GOODS_STOCK_NOT_FOND);
//            }
//            s.setStock(stock.getStock());
//        }
        //查询库存
        List<Long> ids=skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stockList=stockMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(stockList)){
            throw new ResponseException(ExceptionEnum.GOODS_STOCK_NOT_FOND);
        }
        //我们把stock变成一个map,其key是:sku的id，值是库存值
        Map<Long,Long> stockMap=stockList.stream().collect(Collectors.toMap(Stock::getSkuId,Stock::getStock));
        skuList.forEach(sku1 -> sku1.setStock(stockMap.get(sku1.getId())));
        return skuList;
    }
}
