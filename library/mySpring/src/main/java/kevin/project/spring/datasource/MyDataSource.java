package kevin.project.spring.datasource;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSource extends BasicDataSource {

    @Override
    protected DataSource createDataSource() throws SQLException {
        super.createDataSource();
        setUrl("jdbc:mariadb://localhost:3306/employees");
        setUsername("root");
        return super.createDataSource();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }
}
