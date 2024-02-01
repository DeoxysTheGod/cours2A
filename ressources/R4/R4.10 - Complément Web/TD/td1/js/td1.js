const content = document.getElementById('content');
const displayButton = document.getElementById('displayBtn');
const inputN = document.getElementById('n');
const inputMin = document.getElementById('min');
const inputMax = document.getElementById('max');
const data = document.getElementById('data');
const datas = document.getElementById('datas');


let tab = []

// Fill array with random number
function fillArray(n, min, max) {
    let array = [];
    for (let i = 0 ; i < n ; i++) {
        array.push(Math.floor(Math.random() * (max - min) + min));
    }
    return array;
}

// Send data to the #data balise
async function sendData(tab, timeout) {
    for (let i of tab) {
        await new Promise(resolve => setTimeout(resolve, timeout));
        data.innerHTML = i;
        data.dispatchEvent(new Event('change'));
    }
}

data.addEventListener('change', () => {
    changeColor(datas, parseInt(data.innerHTML));
    showMessage();
});

function showMessage() {
    const message = document.getElementById('message');
    if (data.innerHTML < 0) {
        message.innerHTML = 'Brrrrrrr, un peu froid ce matin, mets ta cagoule !';
    } else if (data.innerHTML > 30) {
        message.innerHTML = 'Caliente ! Vamos a la playa, ho hoho hoho !!';
    } else {
        message.innerHTML = '';
    }

}

function changeColor(target, value) {
    // Remove previous style
    target.classList.remove('black-border', 'blue-border', 'green-border', 'orange-border', 'red-border');

    // add new style
    if (value >= -10 && value <= 0)
        target.classList.add('blue-border');
    else if (value <= 20)
        target.classList.add('green-border');
    else if (value <= 30)
        target.classList.add('orange-border');
    else if (value <= 40)
        target.classList.add('red-border');
    else
        target.classList.add('black-border');
}

if (displayButton) {
    displayButton.addEventListener('click', async () => {
        tab = fillArray(10, -10, 40);
        displayButton.disabled = true;
        await sendData(tab, 1000);
        displayButton.disabled = false;
    });
}
