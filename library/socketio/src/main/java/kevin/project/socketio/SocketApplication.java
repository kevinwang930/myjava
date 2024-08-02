package kevin.project.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import kevin.project.socketio.listener.PingListenerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @ClassName SocketApplication
 * @Description TODO
 * @Date 2024/8/1
 **/
@SpringBootApplication
@Slf4j
public class SocketApplication implements CommandLineRunner, DisposableBean {

    public static void main(String[] args) {
        SpringApplication.run(SocketApplication.class, args);
    }

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void run(String... strings) {
        socketIOServer.addPingListener(new PingListenerImpl());
        socketIOServer.start();
        log.info("socket.io启动成功！");
    }

    @Override
    public void destroy() throws Exception {
        if (socketIOServer != null) {
            log.info("socket server stopped");
            socketIOServer.stop();
        }
    }
}
