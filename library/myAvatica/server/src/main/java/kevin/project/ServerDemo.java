package kevin.project;

import org.apache.calcite.avatica.jdbc.JdbcMeta;
import org.apache.calcite.avatica.remote.Driver;
import org.apache.calcite.avatica.remote.LocalService;
import org.apache.calcite.avatica.server.HttpServer;

import java.sql.SQLException;

public class ServerDemo {

    public static void demo() throws SQLException, InterruptedException {
        String url = "jdbc:mariadb://localhost:3306";
        final JdbcMeta meta = new JdbcMeta(url, "kevin", "123456");
        final LocalService service = new LocalService(meta);
        final HttpServer server = new HttpServer.Builder<>()
                .withPort(5888)
                .withHandler(service, Driver.Serialization.PROTOBUF)
                .build();
        server.start();
        System.out.println(server);
        server.join();
    }

    public static void main(String[] args) throws SQLException, InterruptedException {
        demo();
    }
}