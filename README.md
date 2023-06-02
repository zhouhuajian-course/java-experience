# Java 手册

## 操作数据库 MySQL

mysql » mysql-connector-java 移到了  com.mysql » mysql-connector-j

https://mvnrepository.com/artifact/com.mysql/mysql-connector-j

```xml
<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>
```

JDBC 技术规范，提供了 Connection，Statement,ResultSet 三个经常用的接口。 
DatabaseMetaData 和 ResultSetMetaData 元数据接口。

元数据在 use information_schema
select * from tables\G  
select * from columns\G

