package kevin.project.swagger.api;

import java.util.Map;
import kevin.project.swagger.model.Order;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-14T21:54:51.375+08:00")

@Controller
public class StoreApiController implements StoreApi {

    private final StoreApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public StoreApiController(StoreApiDelegate delegate) {
        this.delegate = delegate;
    }
    public ResponseEntity<Void> deleteOrder(@DecimalMin("1.0")@ApiParam(value = "ID of the order that needs to be deleted",required=true) @PathVariable("orderId") String orderId) {
        return delegate.deleteOrder(orderId);
    }

    public ResponseEntity<Map<String, Integer>> getInventory() {
        return delegate.getInventory();
    }

    public ResponseEntity<Order> getOrderById(@Min(1L) @Max(5L) @ApiParam(value = "ID of pet that needs to be fetched",required=true) @PathVariable("orderId") Long orderId) {
        return delegate.getOrderById(orderId);
    }

    public ResponseEntity<Order> placeOrder(@ApiParam(value = "order placed for purchasing the pet" ,required=true )  @Valid @RequestBody Order body) {
        return delegate.placeOrder(body);
    }

}
