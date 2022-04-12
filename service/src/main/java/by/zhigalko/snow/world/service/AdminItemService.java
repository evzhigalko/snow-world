package by.zhigalko.snow.world.service;

import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.service.util.ServiceEquipmentFactory;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.UUID;

/**
 * Service for admin to change items
 */
public interface AdminItemService {
    /**
     * Add new item from admin credentials
     * @param itemRequest {@link ItemRequest} is received from presentation layer
     * @param filePart    {@link MultipartFile} uploaded image, which will be saved  into database
     * @param product     name of product, which defined to call necessary service from ${@link ServiceEquipmentFactory}
     * @return <ul>
     * <li>{@code true} if saved</li>
     * <li>{@code false} if didn't save</li>
     * </ul>
     * @throws ServletException
     * @throws IOException
     */
    boolean addNewItem(ItemRequest itemRequest, MultipartFile filePart, String product) throws ServletException, IOException;

    /**
     * Delete item from admin credentials
     * @param product     name of product, which defined to call necessary service from ${@link ServiceEquipmentFactory}
     * @param id {@link UUID} is used to define item to delete
     */
    void deleteItem(String product, UUID id);

    /**
     * Update item from admin credentials with
     * @param cost of item,
     * @param availableToRental of item,
     * @param id {@link UUID} is used to define item to update
     * @param service {@link BaseItemService}
     * @return
     */
    boolean updateItem(String cost, String availableToRental, UUID id, BaseItemService service);
}
