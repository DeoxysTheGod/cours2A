const number1 = document.getElementById('number1');
const operator = document.getElementById('operator');
const number2 = document.getElementById('number2');
const submit = document.getElementById('submit');
const result = document.getElementById('result');

submit.addEventListener('click', function() {
    const n1 = parseInt(number1.value);
    const n2 = parseInt(number2.value);
    let r = 0;
    switch (operator.value) {
        case '+':
            r = n1 + n2;
            break;
        case '-':
            r = n1 - n2;
            break;
        case '*':
            r = n1 * n2;
            break;
        case '/':
            r = n1 / n2;
            break;
    }
    console.log(operator.value);
    result.innerHTML = r.toString();
});