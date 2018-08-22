package com.dekuofa.model;

import com.dekuofa.model.common.BeanMethod;
import com.dekuofa.model.enums.UserType;
import com.dekuofa.utils.BeanKit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author gx <br>
 * @date 2018-08-21 <br>
 */
public interface BaseEntity {

    // todo 精简代码，转化为函数式编程 ，并且与createInfo合并
    // 目前想法：将设置某个属性值的方法独立出来
//    default BaseEntity setModifyInfo(BaseUserInfo userInfo, int time) {
//        Class<?> clazz = this.getClass();
//
//        Function<BaseUserInfo, UserType>                    function   = BaseUserInfo::getUserType;
//        BeanMethod.BeanMethodBuilder<BaseUserInfo, UserType> builder    = BeanMethod.builder();
//        BeanMethod.BeanMethodBuilder<BaseUserInfo, String>  strBuilder = BeanMethod.builder();
//        // getField 仅用于public
//        BeanMethod<BaseUserInfo, UserType> modifierType = builder.clazz(clazz)
//                .field("modifierType").propertyType(Integer.TYPE)
//                .function(function).build();
//        BeanKit.injectProperty(modifierType, this, userInfo);
//
//        BeanMethod<BaseUserInfo, UserType> modifyTime = builder.clazz(clazz)
//                .field("modifyTime").propertyType(Integer.TYPE)
//                .function(function).build();
//        BeanKit.injectProperty(modifyTime, this, userInfo);
//
//        BeanMethod<BaseUserInfo, UserType> modifierId = builder.clazz(clazz)
//                .field("modifierId").propertyType(Integer.TYPE)
//                .function(function).build();
//        BeanKit.injectProperty(modifierId, this, userInfo);
//
//        BeanMethod<BaseUserInfo, String> modifierName = strBuilder.clazz(clazz)
//                .field("modifierName").propertyType(String.class)
//                .function(BaseUserInfo::getNickName).build();
//        BeanKit.injectProperty(modifierName, this, userInfo);
//        return this;
//    }
//
//
//    default BaseEntity setCreateInfo(BaseUserInfo userInfo, int time) {
//        Class<?> clazz = this.getClass();
//
//        Function<BaseUserInfo, Integer>                     function   = BaseUserInfo::getUserType;
//        BeanMethod.BeanMethodBuilder<BaseUserInfo, Integer> builder    = BeanMethod.builder();
//        BeanMethod.BeanMethodBuilder<BaseUserInfo, String>  strBuilder = BeanMethod.builder();
//        // getField 仅用于public
//        BeanMethod<BaseUserInfo, Integer> creatorType = builder.clazz(clazz)
//                .field("creatorType").propertyType(Integer.TYPE)
//                .function(function).build();
//        BeanKit.injectProperty(creatorType, this, userInfo);
//
//        BeanMethod<BaseUserInfo, Integer> createTime = builder.clazz(clazz)
//                .field("createTime").propertyType(Integer.TYPE)
//                .function(function).build();
//        BeanKit.injectProperty(createTime, this, userInfo);
//
//        BeanMethod<BaseUserInfo, Integer> creatorId = builder.clazz(clazz)
//                .field("creatorId").propertyType(Integer.TYPE)
//                .function(function).build();
//        BeanKit.injectProperty(creatorId, this, userInfo);
//
//        BeanMethod<BaseUserInfo, String> creatorName = strBuilder.clazz(clazz)
//                .field("creatorName").propertyType(String.class)
//                .function(BaseUserInfo::getNickName).build();
//        BeanKit.injectProperty(creatorName, this, userInfo);
//        return this;
//    }

}
