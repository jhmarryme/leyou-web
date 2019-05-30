package com.leyou.search.service;

import com.leyou.item.pojo.SpuBo;
import com.leyou.item.vo.PageResult;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.repository.GoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author jhmarryme.cn
 * @date 2019/5/30 18:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexServiceTest {
    @Autowired
    private IndexService indexService;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private GoodsRepository goodsRepository;
    @Test
    public void buildGoods() {
        int page = 1;
        int rows = 100;
        int size = 0;
        do {
            // 查询spu
            PageResult<SpuBo> result = this.goodsClient.querySpuByPage(page, rows, true, null);
            List<SpuBo> spus = result.getItems();
            if (CollectionUtils.isEmpty(spus)){
                break;
            }
            // spu转为goods
            List<Goods> goods = spus.stream().map(spu -> this.indexService.buildGoods(spu))
                    .collect(Collectors.toList());

            // 把goods放入索引库
            this.goodsRepository.saveAll(goods);

            size = spus.size();
            page++;
        }while (size == 100);
    }
}