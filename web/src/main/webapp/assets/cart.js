$('.qty').change(function() {
    updateQuantity(this);
});
function updateQuantity(qtyInput) {
    var cartRow = $(qtyInput).closest('tr');
    var price = parseFloat($('.price', cartRow).text());
    var quantity = $('.qty', cartRow).val();
    var subtotal = $('.subtotal', cartRow);
    var linePrice = price * quantity;
    $(subtotal).text(linePrice.toFixed(2));
    total_calculate()
}
function total_calculate() {
    var total = 0;
    $(".cart-table-body .subtotal").each(function() {
        var value = $(this).text() !== "" ? parseFloat($(this).text()) : 0;
        total += value;
    })
    $("#total").text('Сумма итого: ' + total.toFixed(2) + ' бел.рублей')
}
total_calculate()
