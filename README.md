mmall_learning
==============

>1.mapper.xml 是 dao层mapper接口的实现

>2 禁止使用select * ，要什么查什么

>3.mybatis中的mapper.xml文件中编写sql语句时，变量使用#{password} 实现预编译sql语句

>4.salt值的能增加提高破解MD5的复杂度

>5.修改密码时，增加一个token校验，能防止横向越权

>6.无限扩展的树状层级结构：DB设计时，可以设计id，parent_id，当parent_id为0时说明是根节点

>7.先写接口的实现，在复制方法的声明到接口

>8.使用自定义类型存储到set集合时，要重写hashcode和euqals方法