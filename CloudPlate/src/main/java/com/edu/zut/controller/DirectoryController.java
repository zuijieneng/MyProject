package com.edu.zut.controller;
import com.edu.zut.entity.Directory;
import com.edu.zut.entity.R;
import com.edu.zut.entity.User;
import com.edu.zut.service.DirectoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/directory")
public class DirectoryController {
    @Autowired
    private DirectoyService directoyService;


    /**
     * 获取子文件或文件夹集合
     * @param dfu
     * @param uid
     * @return
     */
    @GetMapping("/child")
    public R<List<Directory>> listChild(String dfu,int uid){
        List<Directory> childs = directoyService.selectAllChild(dfu,uid);
        return R.success(childs);
    }

    /**
     * 添加文件夹，前端只给文件夹的名字和权限两个数据
     * @param directory
     * @return
     */
    @PostMapping("/insert")
    public R<String> mkdir(@RequestBody Directory directory, HttpServletRequest req){
        //用户uid的获取
        User user =(User) req.getSession().getAttribute("user");
        directory.setUid(user.getUid());
        //设置文件夹类型、创建时间、用户id、文件路径...
        directory.setDtype(0);
        directory.setDtime(new Date());
        directory.setDfid(null);
        directory.setDsize(0);
        int insert = directoyService.insert(directory);
        return insert==1?R.success("创建成功！"):R.error("文件夹可能存在！");
    }

    /**
     * 删除某个文件夹，但是这里不删除文件夹里的文件
     * @param did
     * @return
     */
    @DeleteMapping("/delete/{dids}")
    public R<String> delete(@PathVariable("dids") String did){
        System.out.println("did==="+did);
        int i = directoyService.deleteMany(did);
        return i!=0?R.success("删除成功！"):R.error("删除失败！");
    }

    /**
     * 修改文件夹
     * @param directory
     * @return
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody Directory directory){
        return directoyService.update(directory);
    }

}
