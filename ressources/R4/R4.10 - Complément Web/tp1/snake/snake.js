const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

// Multiplicateur de la taille des pixels
const pixelSize = 10;
// Nombre de lignes
const rows = 30;
// Nombre de colonnes
const cols = 40;
// Vitesse du jeu
let gameSpeed = 7;

// Ajustez la taille du canvas en fonction du nombre de lignes et de colonnes
canvas.width = pixelSize * cols;
canvas.height = pixelSize * rows;

// Classe pour compter les FPS
class FPSCounter {
    constructor() {
        this.fps = 0;
        this.lastUpdate = Date.now();
        this.frameCount = 0;
    }

    // Met à jour le compteur de FPS
    update() {
        const now = Date.now();
        // Calcule le temps écoulé depuis la dernière mise à jour
        const delta = now - this.lastUpdate;

        // Si plus d'une seconde s'est écoulée
        if (delta >= 1000) {
            // Met à jour le compteur de FPS
            this.fps = this.frameCount;
            // Réinitialise le compteur de FPS
            this.frameCount = 0;
            // Met à jour le dernier temps de mise à jour
            this.lastUpdate = now;
        }
        // Incrémente le compteur de FPS
        this.frameCount++;
    }

    // Affiche le compteur de FPS
    show() {
        const ctx = canvas.getContext('2d');
        ctx.font = '16px Arial';
        ctx.fillStyle = 'black';
        ctx.fillText(`FPS: ${this.fps}`, 10, 20);
    }
}

class Snake {
    constructor() {
        this.reset()
    }

    // Réinitialise le serpent
    reset() {
        this.x = cols * pixelSize / 2;
        this.y = rows * pixelSize / 2;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.total = 0;
        this.tail = [];
        this.interpolation = 0;
        this.directionQueue = [];
        this.grow = false;
    }

    // Met à jour la position du serpent
    update() {
        if (!isPaused) {
            // Si le serpent a une queue, déplacez chaque segment de la queue vers l'avant
            if (!this.grow) {
                for (let i = 0; i < this.tail.length - 1; i++) {
                    this.tail[i] = this.tail[i + 1];
                }
            }
            // Si le serpent a une queue, le dernier segment de la queue prend la position actuelle de la tête
            if (this.total > 0) {
                this.tail[this.total - 1] = {x: this.x, y: this.y};
            }

            this.grow = false;

            this.x += this.xSpeed * pixelSize;
            this.y += this.ySpeed * pixelSize;

            if (this.directionQueue.length > 0) {
                const direction = this.directionQueue.shift();
                this.xSpeed = direction.x;
                this.ySpeed = direction.y;
            }

            this.interpolation = 0;
        }
    }

    show() {
        const ctx = canvas.getContext('2d');
        ctx.fillStyle = 'green';
        for (let i = 0; i < this.tail.length; i++) {
            const nextPos = this.tail[i + 1] ? this.tail[i + 1] : {x: this.x, y: this.y};
            const interpolatedX = this.tail[i].x + (nextPos.x - this.tail[i].x) * this.interpolation;
            const interpolatedY = this.tail[i].y + (nextPos.y - this.tail[i].y) * this.interpolation;
            ctx.fillRect(interpolatedX, interpolatedY, pixelSize, pixelSize);
        }
        ctx.fillRect(this.x + this.xSpeed * pixelSize * this.interpolation, this.y + this.ySpeed * pixelSize * this.interpolation, pixelSize, pixelSize);
    }

    showScore() {
        let width = canvas.width / 2;
        let height = canvas.height - canvas.height / 7;
        displayText(this.total,
            canvas.height,
            'rgba(0,0,0,0.3)',
            width, height);
    }

    changeDirection(x, y) {
        if (this.xSpeed === 0 && x !== 0 || this.ySpeed === 0 && y !== 0) {
            this.directionQueue = []; // Vide la file d'attente des directions
            this.directionQueue.push({x, y});
        }
    }

    eat(pos) {
        if (this.x === pos.x && this.y === pos.y) {
            this.total++;
            this.grow = true;
            // Si le serpent a déjà une queue, ajoutez un nouveau segment à la queue du serpent à la position de la dernière partie de la queue
            if (this.total > 1) {
                this.tail.push({x: this.tail[this.total - 2].x, y: this.tail[this.total - 2].y});
            }
            if (this.total === cols * rows) {
                win();
            }
            return true;
        }
        return false;
    }

    checkCollision() {
        // Si le serpent touche sa queue, réinitialisez le serpent
        for (let i = 0; i < this.tail.length; i++) {
            if (this.x === this.tail[i].x && this.y === this.tail[i].y) {
                lose();
            }
        }
        // Si le serpent touche le bord, réinitialisez le serpent
        if (this.x >= canvas.width || this.x < 0 || this.y >= canvas.height || this.y < 0) {
            lose();
        }
    }
}

class Food {
    constructor() {
        this.x = Math.floor(Math.random() * cols) * pixelSize;
        this.y = Math.floor(Math.random() * rows) * pixelSize;
    }

    show() {
        const ctx = canvas.getContext('2d');
        ctx.fillStyle = 'red';
        ctx.beginPath();
        ctx.arc(this.x + pixelSize / 2, this.y + pixelSize / 2, pixelSize / 2, 0, Math.PI * 2);
        ctx.fill();
    }

    update() {
        if (snake.eat(this)) {
            do {
                this.x = Math.floor(Math.random() * cols) * pixelSize;
                this.y = Math.floor(Math.random() * rows) * pixelSize;
            } while (this.isInSnake());
        }
    }

    isInSnake() {
        for (let i = 0; i < snake.tail.length; i++) {
            if (this.x === snake.tail[i].x && this.y === snake.tail[i].y) {
                return true;
            }
        }
        return this.x === snake.x && this.y === snake.y;

    }
}

const snake = new Snake();
const food = new Food();
const fpsCounter = new FPSCounter();

let isPaused = true;

const FPS = 144; // Augmentez le nombre de FPS
let updateInterval = FPS / gameSpeed; // Ajustez l'intervalle de mise à jour en fonction du nombre de FPS

let updateCount = 0;

function displayText(text, size, color, x, y) {
    ctx.font = size + 'px Arial';
    ctx.fillStyle = `${color}`; // Semi-transparent black
    x = x - ctx.measureText(text).width / 2;
    const textWidth = ctx.measureText(text).width;
    ctx.fillText(text, x, y);
}

function loop() {
    if (!isPaused) {

        fpsCounter.update();
        updateCount++;
        if (updateCount >= updateInterval) {
            update();
            updateCount = 0;
        } else {
            snake.interpolation = updateCount / updateInterval;
        }
        draw();
    }
}

function update() {
    if (isPaused) return;
    snake.update();
    food.update();
    snake.checkCollision();
    if (snake.eat(food)) {
        food.update();
    }
}

function draw() {
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    fpsCounter.show();
    snake.showScore();
    food.show();
    snake.show();
}

function pause() {
    isPaused = true;
}

function resume() {
    isPaused = false;
}

function restart() {
    snake.reset();
    draw();
    pause();
}

function lose() {
    restart();
    let size = canvas.height / 6;
    let width = canvas.width / 2;
    let height = canvas.height - size * 2.5;

    displayText('Game Over',
        size,
        'rgb(255, 120, 120)',
        width, height);
}

function win() {
    restart();
    let size = canvas.height / 6;
    let width = canvas.width / 2;
    let height = canvas.height - size * 2.5;

    displayText('You Win!',
        size,
        'rgb(120, 255, 120)',
        width, height);
}

function keyPressed(e) {
    switch (e.keyCode) {
        case 32:
            isPaused ? resume() : pause();
            break;
        case 82:
            restart();
            break;
        case 37:
        case 38:
        case 39:
        case 40:
            resume(); // Si le jeu est en pause, ignore les touches directionnelles
            snake.changeDirection(e.keyCode === 39 ? 1 : e.keyCode === 37 ? -1 : 0, e.keyCode === 40 ? 1 : e.keyCode === 38 ? -1 : 0);
            break;
    }
}

document.addEventListener('keydown', keyPressed);

setInterval(loop, 1000 / FPS);