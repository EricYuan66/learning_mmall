package com.mmall.service.Impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by yqn19 on 2017-09-09.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMsg("添加品类错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//这个分类是可用的

        //保存到数据库
        int rowCount = categoryMapper.insert(category);

        if (rowCount > 0) {
            return ServerResponse.createBySuccessMsg("添加品类成功");
        } else
            return ServerResponse.createByErrorMsg("添加品类失败");
    }

    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMsg("添加品类错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setId(categoryId);

        //保存到数据库
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);

        if (rowCount > 0) {
            return ServerResponse.createBySuccessMsg("更改品类名字成功");
        } else
            return ServerResponse.createByErrorMsg("更改品类名字失败");
    }

    public ServerResponse<List<Category>> getChildrenParallerlCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> set = Sets.newHashSet();
        findChildCategory(set, categoryId);

        List<Integer> categoryList = Lists.newArrayList();

        if (categoryId != null) {
            for (Category categoryItem :
                    set) {
                categoryList.add(categoryItem.getId());
            }
            return ServerResponse.createBySuccess(categoryList);
        }
        return ServerResponse.createByErrorMsg("所要查询的品类id为空");
    }

    //递归算法算出子节点
    private Set<Category> findChildCategory(Set<Category> set, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            set.add(category);
        }
        //查找子节点，递归算法一定要有一个退出条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem :
                categoryList) {
            findChildCategory(set, categoryItem.getId());
        }
        return set;
    }
}
