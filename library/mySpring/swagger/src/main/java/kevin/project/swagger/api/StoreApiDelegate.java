package kevin.project.swagger.api;

import java.util.Map;
import kevin.project.swagger.model.Order;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * A delegate to be called by the {@link StoreApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-14T21:54:51.375+08:00")

public interface StoreApiDelegate {

    /**
     * @see StoreApi#deleteOrder
     */
    ResponseEntity<Void> deleteOrder( String  orderId);

    /**
     * @see StoreApi#getInventory
     */
    ResponseEntity<Map<String, Integer>> getInventory();

    /**
     * @see StoreApi#getOrderById
     */
    ResponseEntity<Order> getOrderById( Long  orderId);

    /**
     * @see StoreApi#placeOrder
     */
    ResponseEntity<Order> placeOrder( Order  body);

}
