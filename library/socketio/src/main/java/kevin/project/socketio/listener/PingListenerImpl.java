package kevin.project.socketio.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.PingListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @ClassName PingListenerImpl
 * @Description TODO
 * @Date 2024/8/2
 **/
@Slf4j
public class PingListenerImpl implements PingListener {
    @Override
    public void onPing(SocketIOClient client) {
      log.info("pring from {}",client.getRemoteAddress());
    }
}
