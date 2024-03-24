class TabController {
    constructor() {
        this.A_tabsButton = document.querySelectorAll('.tablinks');

        this.tabs = document.querySelectorAll('.tab');

        for (let tabsButton of this.A_tabsButton) {
            tabsButton.addEventListener('click', (event) => {
                this.openTab(event);
            });
        }
    }

    openTab(event) {
        for (let tab of this.tabs) {
            tab.hidden = true;
        }
        for (let tabsButton of this.A_tabsButton) {
            tabsButton.disabled = false;
        }
        document.getElementById(event.target.name + 'Tab').hidden = false;
        event.target.disabled = true;
    }
}

class DataDisplayer {
    constructor(dataId) {
        this.dataZone = document.getElementById(dataId);
        this.history = document.getElementById('history');
    }

    displayDataColor(value, target, withRemove = true) {
        // Remove previous style
        if (withRemove)
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

    displayDataMessage(value) {
        const message = this.dataZone.querySelector('[slot="message"]');
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

    displayData(value) {
        this.dataZone.querySelector('.displayDataArea').innerHTML = value;
        this.displayDataColor(value, this.dataZone.querySelector('[slot="data"]'));
        this.displayDataMessage(value);
    }

    fillHistory(value, date, time) {
        let tr = document.createElement('tr', { is: 'history-row' });

        // Remplir les slots avec les valeurs
        tr.querySelector('[slot="temperature"]').innerHTML = value;
        tr.querySelector('[slot="date"]').innerHTML = date;
        tr.querySelector('[slot="time"]').innerHTML = time;

        this.displayDataColor(value, tr, false);
        this.history.insertBefore(tr, this.history.firstChild);
    }

    update(value, date, time) {
        this.displayData(value);
        this.fillHistory(value, date, time);
    }
}

class DataGetter {
    constructor() {
        this.temp = null;
        this.date = null;
        this.time = null;
        this.tempDataDisplayer = new DataDisplayer("temp");
    }

    #padZero(num) {
        return (num < 10 ? '0' : '') + num;
    }

    async getData() {
        let data = await fetch("https://hothothot.dog/api/capteurs/exterieur")
            .then(response => response.json())
            .then(data => data["capteurs"][0]);
        try {
            this.temp = data["Valeur"];
        } catch (e) {
            this.temp = "Impossible de récupérer la température";
        }

        let timestamp = new Date(data["Timestamp"] * 1000);
        this.date = this.#padZero(timestamp.getDate()) + '/' + this.#padZero(timestamp.getMonth() + 1) + '/' + timestamp.getFullYear();
        this.time = this.#padZero(timestamp.getHours()) + ':' + this.#padZero(timestamp.getMinutes()) + ':' + this.#padZero(timestamp.getSeconds());
    }

    async start() {
        await this.getData();
        setInterval(() => {
            this.getData();
            this.update();
        }, 2000);
    }

    update() {
        this.tempDataDisplayer.update(this.temp, this.date, this.time);
    }
}

async function main() {
    // init tab controller
    new TabController();

    // init data getter
    const dataGetter = new DataGetter();
    await dataGetter.start();
}

main();

// Custom element for history row

customElements.define('history-row', class extends HTMLTableRowElement {
    constructor() {
        super();
        const template = document.getElementById('history-row').content;
        this.appendChild(template.cloneNode(true));
    }
}, {extends: 'tr'});

customElements.define('sensor-data', class extends HTMLElement {
   constructor() {
       super();
       const template = document.getElementById('sensor-data').content;
       this.attachShadow({mode: 'open'}).appendChild(template.cloneNode(true));
   }
});