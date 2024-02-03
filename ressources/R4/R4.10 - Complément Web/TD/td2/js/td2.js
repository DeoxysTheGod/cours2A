
const displayButton = document.getElementById('displayBtn');
// p for displaying the temperature
const data = document.getElementById('data');
const dataTab = document.getElementById('dataTab');
// tbody for displaying the history
const tbody = document.getElementById('tbody');
// tabs button
const tabs = document.getElementById('tabs');
// tab
const historyTab = document.getElementById('historyTab');

const abbrCelcius = document.createElement('abbr');
abbrCelcius.setAttribute('title', 'degrés Celcius');
abbrCelcius.innerHTML = '°C';

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
        data.innerHTML = i + abbrCelcius.outerHTML
        data.dispatchEvent(new Event('change'));
    }
}

// on data change
data.addEventListener('change', () => {
    changeColor(dataTab, parseInt(data.innerHTML));
    showMessage(parseInt(data.innerHTML));
});

function showMessage(value) {
    const message = document.getElementById('message');
    if (value < 0) {
        message.hidden = false;
        message.innerHTML = 'Brrrrrrr, un peu froid ce matin, mets ta cagoule !';
    } else if (value > 30) {
        message.hidden = false;
        message.innerHTML = 'Caliente ! Vamos a la playa, ho hoho hoho !!';
    } else {
        message.hidden = true;
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

tabs.addEventListener('click', (event) => {
    console.log(event.target.id);
    if (event.target.id === 'getTemp') {
        dataTab.hidden = false;
        historyTab.hidden = true;
    } else if (event.target.id === 'getHistory') {
        dataTab.hidden = true;
        historyTab.hidden = false;
    }
});

if (displayButton) {
    displayButton.addEventListener('click', async () => {
        tab = fillArray(10, -10, 40);
        displayButton.disabled = true;
        await sendData(tab, 1000);
        displayButton.disabled = false;
    });
}
