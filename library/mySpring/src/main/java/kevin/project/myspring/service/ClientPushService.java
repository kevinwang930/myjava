package kevin.project.myspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0
 * @ClassName ClientPushService
 * @Description TODO
 * @Date 6/29/24
 **/
@Service
public class ClientPushService implements InitializingBean {

    private final List<SseEmitter> emitters = new ArrayList<>();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    Logger logger = LoggerFactory.getLogger(ClientPushService.class);

    public void addEmitter(SseEmitter emitter) {
        emitter.onCompletion(() ->
        {
            emitters.remove(emitter);
            logger.info("emitter closed");

        });
        emitter.onError((throwable) -> {
            logger.error("emitter error", throwable);
            emitters.remove(emitter);
        });
        emitters.add(emitter);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        executorService.submit(() -> {

            while (true) {
                try {
                    Thread.sleep(5000);
                    for (int i = 0; i < emitters.size(); i++) {
                        SseEmitter emitter = emitters.get(i);
                        emitter.send(SseEmitter.event().data("inside emitter" + i));
                        logger.info("inside emitter " + i);
                    }
                } catch (Exception e) {
                    logger.error("emitter error in loop", e);
                }
            }
        });
    }
}
