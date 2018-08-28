# springboot-template

#### 项目介绍
springboot框架模板，用于快速搭建一个后台 `REST` 框架的模板项目

#### 软件架构
1. Spring Boot(Spring + SpringMVC)
2. [shiro](https://shiro.apache.org/documentation.html)：Apache 的安全框架
3. [anima](https://github.com/biezhi/anima)：国人开发的轻量级数据库操作库，简单易用且优美（~~吹爆~~）

待补充...

#### 编码约定

1. Controller 层：处理参数的基本校验（满足格式、必填、用户登录以及权限信息）& 参数组装，以及调用 Manager 的方法
2. Manager 层：处理业务逻辑类的校验（唯一性、数据状态是否能被修改等），以及调用 Service 的方法
3. Service 层：处理与数据库的交互（sql相关）

注意：以上三层，同层之间不能互相调用，仅能通过上层来调用。

##### 例1：用户权限查询

禁止如下写法：

```java
// RoleService.java
public List<Permission> getUserPermission(Integer userId) {
    ...
    List<Role> roles = roleDao.findByUserId(userId);
    // 不应该在 roleService 中使用 permissionService 的方法，因为他们同属 Service
    return permissionService.getPermissions(roles);
}

// PermissionService.java
public List<Permission> getPermissions(List<Role> roles) {
    ...
    return permissionDao.getPermissions(roles);
}
```

规范写法：

```java
// RoleService.java
public List<Role> getRoles(Integer userId) {
    ...
    return roleDao.findByUserId(userId);
}

// PermissionService.java
public List<Permission> getPermissions(List<Role> roles) {
    ...
    return permissionDao.getPermissions(roles);
}

// UserManager.java
public List<Permission> getUserPermissions(Integer userId) {
    List<Role> roles = roleService.getRoles(userId);
    List<Permission> permissions = permissionService.getPermission(roles);
    return permissions;
}
```


#### 安装教程

待补充...

#### 使用说明

待补充...

#### 相关开发博客

待补充...