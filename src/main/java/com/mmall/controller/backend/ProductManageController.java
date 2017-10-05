package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yqn19 on 2017-09-09.
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IFileService iFileService;

    //新增产品，更新产品
    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //调用service存储到数据库
            return iProductService.saveOrUpdateProduct(product);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //产品上下架，更新产品的销售状态
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId, Integer status) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //调用service存储到数据库
            return iProductService.setSaleStatus(productId, status);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //获取商品详情
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //填充业务
            return iProductService.manageProductDetail(productId);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //后台产品列表，分页
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //动态分页
            return iProductService.getProductList(pageNum, pageSize);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //后台产品搜索
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(HttpSession session, String productName, Integer productId,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //动态分页
            return iProductService.searchProduct(productName, productId, pageNum, pageSize);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //上传图片，并保存到FTP服务器
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session,
                                 @RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            String path = request.getSession().getServletContext().getRealPath("upload");// webapp/WEB-INF/
            String targetFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
            Map<String, String> map = new HashMap<>();
            map.put("uri", targetFileName);
            map.put("url", url);
            return ServerResponse.createBySuccess(map);
        } else
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
    }

    //富文本上传
    //富文本返回格式要按照使用的smiditor官方文档的要求进行返回
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session,
                                 @RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = Maps.newHashMap();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员");
            return resultMap;
        }
        //校验下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            String path = request.getSession().getServletContext().getRealPath("upload");// webapp/WEB-INF/
            String targetFileName = iFileService.upload(file, path);
            if (StringUtils.isBlank(targetFileName)) {
                resultMap.put("success", false);
                resultMap.put("msg", "上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
            resultMap.put("success", true);
            resultMap.put("msg", "上传成功");
            resultMap.put("file_path", url);
            //文档要求，在上传成功时添加
            response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
            return resultMap;
        } else {
            resultMap.put("success", false);
            resultMap.put("msg", "无权限操作");
            return resultMap;
        }
    }
}
