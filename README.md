mmall_learning
==============

>1.mapper.xml 是 dao层mapper接口的实现

>2 禁止使用select * ，要什么查什么

>3.mybatis中的mapper.xml文件中编写sql语句时，变量使用#{password} 实现预编译sql语句

>4.salt值的能增加提高破解MD5的复杂度

>5.修改密码时，增加一个token校验，能防止横向越权

>6.无限扩展的树状层级结构：DB设计时，可以设计id，parent_id，当parent_id为0时说明是根节点

>7.先写接口的实现，在复制方法的声明到接口

>8.使用自定义类型存储到set集合时，要重写hashcode和equals方法

>9.PageHelper 会自动给Mapper中的SQL语句添加limit语句，因此不必在手写SQL语句时加上 结尾处的**分号**

>10.Mybatis 中，在写SQL语句时，可以使用<where>标签，自动拼接where语句，不管where的条件是否存在

>11.set集合contains复杂度O(1), list集合contains复杂度O(n)

>12.BigDecimal **一定**要用 String参数的构造器，否则也会有精度丢失问题

>13.MySQL IFNULL函数