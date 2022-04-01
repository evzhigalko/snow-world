package by.zhigalko.snow.world.service.item.util;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.ClothesWithMembrane;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class ItemGenerator {
    public Item setEquipmentData(HttpServletRequest request, Item item, EquipmentSize equipmentSize, Image image) {
        setData(request, item, equipmentSize, image);
        return item;
    }

    public ClothesWithMembrane setEquipmentData(HttpServletRequest request, ClothesWithMembrane clothes, EquipmentSize equipmentSize, Image image) {
        setData(request, clothes, equipmentSize, image);
        clothes.setMembrane(Integer.parseInt(request.getParameter("membrane")));
        return clothes;
    }

    private void setData(HttpServletRequest request, Item item, EquipmentSize equipmentSize, Image image) {
        item.setMaker(request.getParameter("maker"));
        item.setGender(Gender.valueOf(request.getParameter("gender")));
        item.setCost(Double.parseDouble(request.getParameter("cost")));
        item.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        item.setEquipmentSizeId(equipmentSize);
        item.setImage(image);
        item.setProductName(Product.valueOf(request.getParameter("product_group")));
    }
}
