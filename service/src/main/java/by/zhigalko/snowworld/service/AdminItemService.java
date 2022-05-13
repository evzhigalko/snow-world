package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import org.springframework.web.multipart.MultipartFile;
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
     * @param product     name of product
     * @return <ul>
     * <li>{@code true} if saved</li>
     * <li>{@code false} if didn't save</li>
     * </ul>
     * @throws IOException
     */
    boolean addNewItem(ItemRequest itemRequest, MultipartFile filePart, String product) throws IOException;

    /**
     * Delete item from admin credentials
     * @param id {@link UUID} is used to define item to delete
     */
    void deleteItem(UUID id);

    /**
     * Update item from admin credentials with
     * @param cost of item,
     * @param availableToRental of item,
     * @param id {@link UUID} is used to define item to update
     * @return <ul>
     * <li>{@code true} if saved</li>
     * <li>{@code false} if didn't save</li>
     * </ul>
     */
    boolean updateItem(String cost, String availableToRental, UUID id);
}
