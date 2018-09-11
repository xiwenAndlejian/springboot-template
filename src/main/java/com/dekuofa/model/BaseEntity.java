package com.dekuofa.model;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author dekuofa <br>
 * @date 2018-08-21 <br>
 */
public interface BaseEntity {

    // 目前想法：将设置某个属性值的方法独立出来
    default <T extends BaseEntity> T setModifyInfo(BaseUserInfo userInfo, Long time) {
        // todo 考虑传入数组循环设值，并且修改为函数式编程
        try {
            BeanUtils.setProperty(this, "modifyTime", time);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            BeanUtils.setProperty(this, "modifierId", userInfo.getUserId());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            BeanUtils.setProperty(this, "modifierName", userInfo.getNickName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        @SuppressWarnings("unchecked") T result = (T) this;
        return result;
    }

    default <T extends BaseEntity> T setCreateInfo(BaseUserInfo userInfo, Long time) {
        try {
            BeanUtils.setProperty(this, "createTime", time);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            BeanUtils.setProperty(this, "creatorId", userInfo.getUserId());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            BeanUtils.setProperty(this, "creatorName", userInfo.getNickName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        @SuppressWarnings("unchecked") T result = (T) this;
        return result;
    }


}
