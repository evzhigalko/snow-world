package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.OrderDetailsDto;
import by.zhigalko.snow.world.dto.response.OrderResponse;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.service.EmailService;
import by.zhigalko.snow.world.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    public final SimpleMailMessage template;
    private final OrderService orderService;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, SimpleMailMessage template, OrderService orderService) {
        this.emailSender = emailSender;
        this.template = template;
        this.orderService = orderService;
    }

    @Override
    public void sendMessage(OrderDetailsDto orderDetailsDto) {
        SimpleMailMessage message = createEmail(orderDetailsDto);
        emailSender.send(message);
        SimpleMailMessage messageToUser = createEmailToUser(orderDetailsDto);
        emailSender.send(messageToUser);
    }

    private SimpleMailMessage createEmail(OrderDetailsDto orderDetailsDto) {
        OrderResponse order = orderService.findById(UUID.fromString(orderDetailsDto.getOrderId()));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(Objects.requireNonNull(template.getTo())[0]);
        message.setSubject("НОВЫЙ ЗАКАЗ: №" + orderDetailsDto.getOrderId() + ", дата: " + LocalDateTime.now());
        StringBuilder contacts = new StringBuilder();
        contacts
                .append("Контактная информация заказчика: \n")
                .append("Фамилия: ").append(orderDetailsDto.getLastname()).append(".\n")
                .append("Имя: ").append(orderDetailsDto.getFirstname()).append(".\n")
                .append("Номер телефона: ").append(orderDetailsDto.getPhoneNumber()).append(".\n")
                .append("Электронный адрес: ").append(orderDetailsDto.getEmail()).append(".\n");
        String newOrderDetails = String.format("\nПоступил новый заказ № %s.\nДата начала бронирования: %s.\n" +
                        "Дней бронирования: %s.\nОбщая сумма заказа: %s\n",
                order.getOrderId(), order.getStartReservationDate().format(getDateTimeFormatter()), order.getReservationDayNumber(), order.getTotalSum());
        String itemDetailsHeader = "\nЗаказанная экипировка: \n";
        String itemDetails = getItemDetails(order);
        message.setText(contacts + newOrderDetails + itemDetailsHeader + itemDetails);
        return message;
    }

    private SimpleMailMessage createEmailToUser(OrderDetailsDto orderDetailsDto) {
        OrderResponse order = orderService.findById(UUID.fromString(orderDetailsDto.getOrderId()));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDetailsDto.getEmail());
        message.setSubject("Snow-World: Ваш заказ № " + order.getOrderId());
        String orderDetails = "\nВы успешно оформили заказ в Snow-World.\nНомер вашего заказа:\n" + order.getOrderId() + ".\n" +
                "Предъявите этот номер администратору в салоне-проката.\nДата начала бронирования: " +
                order.getStartReservationDate().format(getDateTimeFormatter()) + "\n";
        String itemDetailsHeader = "\nВаш заказ:\n";
        String itemDetails = getItemDetails(order);
        message.setText(orderDetails + itemDetailsHeader + itemDetails);
        return message;
    }

    private String getItemDetails(OrderResponse order) {
        StringBuilder itemDetails = new StringBuilder();
        for (Item item : order.getItems()) {
            itemDetails
                    .append("\n")
                    .append("Товар: ")
                    .append(item.getProductName().getName()).append(".\n")
                    .append("Идентификатор: ").append(item.getId()).append(".\n")
                    .append("Производитель: ").append(item.getMaker()).append(".\n")
                    .append("Размер: ").append(item.getEquipmentSize().getEquipmentSizeId()).append(".\n");
        }
        return itemDetails.toString();
    }

    private DateTimeFormatter getDateTimeFormatter() {
        String datePattern = "dd.MM.yyyy";
        return DateTimeFormatter.ofPattern(datePattern);
    }
}
