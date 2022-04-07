const days = document.querySelector('.qty');
const totalSum = document.querySelector('.cart-total-sum');
const hiddenTotalSum = document.querySelector('.totalSum');

let variable = totalSum.textContent;
let inputValue = 1;
days.addEventListener('change', updateValue);
function updateValue(e) {
    if(inputValue < e.target.value) {
        inputValue = e.target.value
        totalSum.textContent = variable * e.target.value;
    }
    if(inputValue > e.target.value) {
        inputValue = e.target.value;
        totalSum.textContent = totalSum.textContent - variable;
    }
    hiddenTotalSum.value = totalSum.textContent;
}
