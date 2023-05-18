package com.gyj.api.controller;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyj.api.domain.AjaxResult;
import com.gyj.api.domain.PageBean;
import com.gyj.api.domain.SysUser;
import com.gyj.api.service.SysUserService;
import com.gyj.api.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${avatarImagesFilePath}")
    private String avatarImagesFilePath;


    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:user:add')" + "||" + "hasAuthority('system:user:edit')")
    public AjaxResult save(@RequestBody SysUser sysUser) {

        if (sysUser.getId() == null || sysUser.getId() == -1){
            return AjaxResult.error();
        }else {
            sysUser.setUpdateTime(new Date());
            sysUserService.updateById(sysUser);
        }
        return AjaxResult.success();
    }


    @PostMapping("/updateUserPwd")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public AjaxResult updateUserPwd(@RequestBody SysUser sysUser) {

        if (sysUser.getId() == null || sysUser.getId() == -1){
            return AjaxResult.error();
        }

        SysUser curUser = sysUserService.getById(sysUser.getId());
        if (bCryptPasswordEncoder.matches(sysUser.getOldPassword(), curUser.getPassword())){

            String newPwd = bCryptPasswordEncoder.encode(sysUser.getNewPassword());
            curUser.setPassword(newPwd);
            curUser.setUpdateTime(new Date());
            sysUserService.updateById(curUser);
            return AjaxResult.success();
        }else {
            return AjaxResult.error("旧密码不匹配");
        }
    }



    /**
     * 上传用户头像图片
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public AjaxResult uploadImage(MultipartFile file)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        if(!file.isEmpty()){
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName=originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName= DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(avatarImagesFilePath+newFileName));

            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("title",newFileName);
            dataMap.put("src","/image/userAvatar/"+newFileName);
            return AjaxResult.success("上传成功", dataMap);

        }else {
            return AjaxResult.error("文件为空，请检查");
        }
    }


    /**
     * 修改用户头像
     * @param sysUser
     * @return
     */
    @RequestMapping("/updateAvatar")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public AjaxResult updateAvatar(@RequestBody SysUser sysUser){
        SysUser currentUser = sysUserService.getById(sysUser.getId());
        currentUser.setUpdateTime(new Date());
        currentUser.setAvatar(sysUser.getAvatar());
        sysUserService.updateById(currentUser);
        return AjaxResult.success();
    }

    /**
     * 根据条件分页查询用户信息
     * @param pageBean
     * @return
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:user:query')")
    public AjaxResult list(@RequestBody PageBean pageBean){
        Page<SysUser> pageResult = sysUserService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()));
        List<SysUser> userList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userList", userList);
        resultMap.put("total", pageResult.getTotal());
        return AjaxResult.success(resultMap);



    }

}
