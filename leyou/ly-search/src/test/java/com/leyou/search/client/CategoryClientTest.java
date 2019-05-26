package com.leyou.search.client;

import com.leyou.item.pojo.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jhmarryme.cn
 * @date 2019/5/23 16:41
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClientTest {

    @Autowired
    private CategoryClient categoryClient;
    @Test
    public void queryCategoryByIds() {
        List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(1L, 2L, 3l));
        for (Category category : categories) {
            System.out.println("category = " + category);
        }
    }

}