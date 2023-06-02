import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlDemo {
    public static void main(String[] args) throws Exception {


        // Class.forName("com.mysql.jdbc.Driver");
        // Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://192.168.1.205:3306/db1",
            "root",
            "root"
        );

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT user_name, age FROM users");
        while (rs.next()) {
            System.out.println(
                rs.getString("user_name") + " " + rs.getInt("age"));
        }


        getAllTableColums();
    }

    private static void getAllTableColums() throws SQLException {
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://192.168.1.205:3306/",
            "root",
            "root"
        );
        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.println("数据库已知的用户: " + dbmd.getUserName());
        System.out.println("数据库的系统函数的逗号分隔列表: " + dbmd.getSystemFunctions());
        System.out.println("数据库的时间和日期函数的逗号分隔列表: " + dbmd.getTimeDateFunctions());
        System.out.println("数据库的字符串函数的逗号分隔列表: " + dbmd.getStringFunctions());
        System.out.println("数据库供应商用于 'schema' 的首选术语: " + dbmd.getSchemaTerm());
        System.out.println("数据库URL: " + dbmd.getURL());
        System.out.println("是否允许只读:" + dbmd.isReadOnly());
        System.out.println("数据库的产品名称:" + dbmd.getDatabaseProductName());
        System.out.println("数据库的版本:" + dbmd.getDatabaseProductVersion());
        System.out.println("驱动程序的名称:" + dbmd.getDriverName());
        System.out.println("驱动程序的版本:" + dbmd.getDriverVersion());

        HashMap<String, HashMap<Integer, String>> tableColums = new HashMap<>();

        // 遍历数据库
        ResultSet catalogs = dbmd.getCatalogs();
        while (catalogs.next()) {
            String databaseName = catalogs.getString("TABLE_CAT");
            System.out.println(databaseName);
            // 遍历数据库里面的普通表
            ResultSet tables = dbmd.getTables(databaseName, null, null, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
                ResultSet columns = dbmd.getColumns(databaseName, null, tableName, null);
                String key = databaseName + "." + tableName;
                tableColums.put(key, new HashMap<>());
                while (columns.next()) {
                    int ordinalPosition = columns.getInt("ORDINAL_POSITION");
                    String columnName = columns.getString("COLUMN_NAME");
                    tableColums.get(key).put(ordinalPosition, columnName);
                }
            }

        }
        System.out.println(tableColums);
    }
}

/*
mysql> create table users (user_id int auto_increment, user_name varchar(128), age tinyint unsigned, primary key (user_id));
Query OK, 0 rows affected (0.01 sec)

mysql> insert into users values (0, '张三', 18);
Query OK, 1 row affected (0.01 sec)

 */