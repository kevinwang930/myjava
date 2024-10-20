package kevin.project.myspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ws")
public class WebSocketController {
    @GetMapping("/")
    public String home() {
        return "WebSocket server running";
    }
}
