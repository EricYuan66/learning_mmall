package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by yqn19 on 2017-09-09.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IUserService iUserService;

    //添加category
    @RequestMapping(value = "add_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //调用service存储到数据库
            return iCategoryService.addCategory(categoryName, parentId);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }


    //更新category的名字
    @RequestMapping(value = "set_category_name.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //更新category的名字
            return iCategoryService.updateCategoryName(categoryId, categoryName);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //根据categoryid查询该category下一级所有的category，不递归
    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //查询子节点的category信息
            return iCategoryService.getChildrenParallerlCategory(categoryId);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //根据categoryid查询该category下所有的category，递归
    @RequestMapping(value = "get_deep_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //查询当前节点的id和递归子节点的id
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }
}
