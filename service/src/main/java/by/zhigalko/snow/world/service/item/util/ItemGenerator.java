package by.zhigalko.snow.world.service.item.util;

import by.zhigalko.snow.world.entity.Equipment;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.ClothesWithMembrane;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class ItemGenerator {
    public Equipment setEquipmentData(HttpServletRequest request, Equipment equipment, EquipmentSize equipmentSize, Image image) {
        setData(request, equipment, equipmentSize, image);
        return equipment;
    }

    public ClothesWithMembrane setEquipmentData(HttpServletRequest request, ClothesWithMembrane clothes, EquipmentSize equipmentSize, Image image) {
        setData(request, clothes, equipmentSize, image);
        clothes.setMembrane(Integer.parseInt(request.getParameter("membrane")));
        return clothes;
    }

    private void setData(HttpServletRequest request, Equipment equipment, EquipmentSize equipmentSize, Image image) {
        equipment.setMaker(request.getParameter("maker"));
        equipment.setGender(Gender.valueOf(request.getParameter("gender")));
        equipment.setCost(Double.parseDouble(request.getParameter("cost")));
        equipment.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        equipment.setEquipmentSizeId(equipmentSize);
        equipment.setImage(image);
        equipment.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
    }
}
