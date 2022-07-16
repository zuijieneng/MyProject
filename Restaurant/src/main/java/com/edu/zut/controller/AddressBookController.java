package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edu.zut.entity.AddressBook;
import com.edu.zut.entity.common.R;
import com.edu.zut.service.AddressBookService;
import com.edu.zut.util.BaseThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址簿管理
 */
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 查询指定用户的全部地址
     * @param addressBook
     * @return
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook) {
        addressBook.setUserId(BaseThreadLocal.getUserId());

        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != addressBook.getUserId(), AddressBook::getUserId, addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        //SQL:select * from address_book where user_id = ? order by update_time desc
        return R.success(addressBookService.list(queryWrapper));
    }

    /**
     * 新增地址
     * @param addressBook
     * @return
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseThreadLocal.getUserId());
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 删除地址
     * @param
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam Long id){
        if (id == null){ return R.error("请求异常");}
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getId,id).eq(AddressBook::getUserId,BaseThreadLocal.getUserId());
        boolean remove = addressBookService.remove(queryWrapper);
        return remove?R.success("删除成功！"):R.error("删除失败！");
    }

    /**
     * 设置默认地址，设置其他地址is_default=0，设置当前地址is_default=1
     * @param addressBook
     * @return
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId, BaseThreadLocal.getUserId());

        wrapper.set(AddressBook::getIsDefault, 0);
        //SQL:update address_book set is_default = 0 where user_id = ?
        addressBookService.update(wrapper);

        addressBook.setIsDefault(1);
        //SQL:update address_book set is_default = 1 where id = ?
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("没有找到该对象");
        }
    }

    /**
     * 查询默认地址
     * @return
     */
    @GetMapping("default")
    public R<AddressBook> getDefault() {
        //SQL:select * from address_book where user_id = ? and is_default = 1
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, BaseThreadLocal.getUserId()).eq(AddressBook::getIsDefault, 1);

        AddressBook addressBook = addressBookService.getOne(queryWrapper);

        if (null == addressBook) {
            return R.error("没有找到该对象");
        } else {
            return R.success(addressBook);
        }
    }


    /**
     * 修改收货地址
     * @param addressBook
     * @return
     *
     */
    @PutMapping
    public R<String> update(@RequestBody AddressBook addressBook){
        if (addressBook == null){
            return R.error("请求异常");
        }
        addressBookService.updateById(addressBook);
        return R.success("修改地址成功");
    }


}
