package kevin.project;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void test1() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.calcite.avatica.remote.Driver");
        Properties prop = new Properties();
//        prop.put("serialization", "protobuf");
        try (Connection conn = DriverManager.getConnection("jdbc:avatica:remote:url=http://localhost:5888;serialization=protobuf", prop)) {
            // 查询数据
            final PreparedStatement stmt = conn.prepareStatement("select * from dept_emp");

            final ResultSet rs = stmt.executeQuery();
//            stmt.setObject(1,"test");
            int index = 0;
            while (rs.next()) {
                int column1 = rs.getInt("emp_no");
                index++;
                System.out.println(column1);
                if (index == 99) {
                    System.out.println("99");
                }
            }

        }
    }

    public static void testSwithDatabase() throws ClassNotFoundException {
        Class.forName("org.apache.calcite.avatica.remote.Driver");
        Properties prop = new Properties();
        try (Connection conn = DriverManager.getConnection("jdbc:avatica:remote:url=http://localhost:5888;serialization=protobuf", prop)) {
            // switch database
            conn.setSchema("test");
            System.out.println(conn.getSchema());
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tv1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        test1();
        testSwithDatabase();
    }
}