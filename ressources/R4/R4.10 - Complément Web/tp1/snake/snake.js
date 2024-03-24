const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

// Multiplicateur de la taille des pixels
const pixelSize = 10;
// Nombre de lignes
const rows = 30;
// Nombre de colonnes
const cols = 40;
// Vitesse du jeu
let gameSpeed = 7; // Si le jeu semble lent vous pouvez augmenter la vitesse ici

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

            // Déplacez la tête du serpent
            this.x += this.xSpeed * pixelSize;
            this.y += this.ySpeed * pixelSize;

            // Si une direction est en attente, changez la direction du serpent
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
        // Dessine la queue du serpent
        for (let i = 0; i < this.tail.length; i++) {
            // Interpole la position de chaque segment de la queue
            const nextPos = this.tail[i + 1] ? this.tail[i + 1] : {x: this.x, y: this.y};
            // Calcule la position intermédiaire entre la position actuelle et la position suivante
            const interpolatedX = this.tail[i].x + (nextPos.x - this.tail[i].x) * this.interpolation;
            const interpolatedY = this.tail[i].y + (nextPos.y - this.tail[i].y) * this.interpolation;
            // Dessine le segment de la queue
            ctx.fillRect(interpolatedX, interpolatedY, pixelSize, pixelSize);
        }
        // Dessine la tête du serpent
        ctx.fillRect(this.x + this.xSpeed * pixelSize * this.interpolation, this.y + this.ySpeed * pixelSize * this.interpolation, pixelSize, pixelSize);
    }

    // Affiche le score
    showScore() {
        let width = canvas.width / 2;
        let height = canvas.height - canvas.height / 7;
        displayText(this.total,
            canvas.height,
            'rgba(0,0,0,0.3)',
            width, height);
    }

    // Change la direction du serpent
    changeDirection(x, y) {
        if (this.xSpeed === 0 && x !== 0 || this.ySpeed === 0 && y !== 0) {
            this.directionQueue = []; // Vide la file d'attente des directions
            this.directionQueue.push({x, y});
        }
    }

    // Fait manger le serpent
    eat(pos) {
        // Si la position de la nourriture correspond à la position de la tête du serpent
        if (this.x === pos.x && this.y === pos.y) {
            // Augmente le score
            this.total++;
            this.grow = true;
            // Si le serpent a déjà une queue, ajoutez un nouveau segment à la queue du serpent à la position de la dernière partie de la queue
            if (this.total > 1) {
                // Ajoute un nouveau segment à la queue du serpent
                this.tail.push({x: this.tail[this.total - 2].x, y: this.tail[this.total - 2].y});
            }
            // Si le score est égal au nombre de cases, le joueur a gagné
            return true;
        }
        return false;
    }

    // Vérifie les collisions
    checkCollision() {
        // Si le serpent touche sa queue, réinitialisez le serpent
        for (let i = 0; i < this.tail.length; i++) {
            if (this.x === this.tail[i].x && this.y === this.tail[i].y) {
                isLosed = true;
            }
        }
        // Si le serpent touche le bord, réinitialisez le serpent
        if (this.x >= canvas.width || this.x < 0 || this.y >= canvas.height || this.y < 0) {
            isLosed = true;
        }
        if (this.total === cols * rows) {
            isWinned = true;
        }
    }
}

class Food {
    constructor() {
        this.x = Math.floor(Math.random() * cols) * pixelSize;
        this.y = Math.floor(Math.random() * rows) * pixelSize;
    }

    // Affiche la nourriture
    show() {
        const ctx = canvas.getContext('2d');
        ctx.fillStyle = 'red';
        ctx.beginPath();
        // Dessine un cercle pour représenter la nourriture
        ctx.arc(this.x + pixelSize / 2, this.y + pixelSize / 2, pixelSize / 2, 0, Math.PI * 2);
        ctx.fill();
    }

    // Met à jour la position de la nourriture
    update() {
        if (snake.eat(this)) {
            this.pickLocation();
        }
    }

    pickLocation() {
        // Crée une liste de toutes les positions possibles
        let positions = [];
        for (let i = 0; i < cols; i++) {
            for (let j = 0; j < rows; j++) {
                positions.push({x: i * pixelSize, y: j * pixelSize});
            }
        }

        // Supprime les positions occupées par le serpent
        for (let i = 0; i < snake.tail.length; i++) {
            positions = positions.filter(pos => pos.x !== snake.tail[i].x || pos.y !== snake.tail[i].y);
        }

        // Choisis une position aléatoire parmi les positions restantes
        const position = positions[Math.floor(Math.random() * positions.length)];
        this.x = position.x;
        this.y = position.y;
    }
}

const snake = new Snake();
const food = new Food();
const fpsCounter = new FPSCounter();

let isPaused = true;
let isLosed = false;
let isWinned = false;

const FPS = 60; // Augmentez le nombre de FPS
let updateInterval = FPS / gameSpeed; // Ajustez l'intervalle de mise à jour en fonction du nombre de FPS

let updateCount = 0;

function displayText(text, size, color, x, y) {
    ctx.font = size + 'px Arial';
    ctx.fillStyle = `${color}`; // Semi-transparent black
    x = x - ctx.measureText(text).width / 2;
    const textWidth = ctx.measureText(text).width;
    ctx.fillText(text, x, y);
}

// Boucle principale
function loop() {
    draw();
    if (!isPaused) {
        // Met à jour le compteur de FPS
        fpsCounter.update();
        updateCount++;
        // Si le nombre de mises à jour est supérieur à l'intervalle de mise à jour, met à jour le jeu
        if (updateCount >= updateInterval) {
            update();
            updateCount = 0;
        } else {
            // Interpole la position du serpent
            snake.interpolation = updateCount / updateInterval;
        }

    }
}

// Met à jour le jeu
function update() {
    if (isPaused) return;
    snake.update();
    food.update();
    snake.checkCollision();
    if (snake.eat(food)) {
        food.update();
    }
}

// Dessine le jeu
function draw() {
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    fpsCounter.show();
    snake.showScore();
    food.show();
    snake.show();
    if (isLosed) {
        lose();
    }
    if (isWinned) {
        win();
    }
}

// Met le jeu en pause
function pause() {
    isPaused = true;
}

// Reprend le jeu
function resume() {
    isPaused = false;
}

// Réinitialise le jeu
function restart() {
    snake.reset();
    isLosed = false;
    isWinned = false;
    draw();
    pause();
}

// Affiche l'écran de fin de jeu
function lose() {
    pause();
    let size = canvas.height / 6;
    let width = canvas.width / 2;
    let height = canvas.height - size * 2.5;

    displayText('Game Over',
        size,
        'rgb(255, 120, 120)',
        width, height);
}

// Affiche l'écran de victoire
function win() {
    pause();
    let size = canvas.height / 6;
    let width = canvas.width / 2;
    let height = canvas.height - size * 2.5;

    displayText('You Win!',
        size,
        'rgb(120, 255, 120)',
        width, height);
}

// Gère les touches du clavier
function keyPressed(e) {
    switch (e.keyCode) {
        case 32: // Espace
            isPaused ? resume() : pause();
            break;
        case 82: // R
            restart();
            break;
        case 37: // Gauche
        case 38: // Haut
        case 39: // Droite
        case 40: // Bas
            resume(); // Si le jeu est en pause, ignore les touches directionnelles
            snake.changeDirection(e.keyCode === 39 ? 1 : e.keyCode === 37 ? -1 : 0, e.keyCode === 40 ? 1 : e.keyCode === 38 ? -1 : 0);
            break;
    }
}

// Gère les touches du clavier
document.addEventListener('keydown', keyPressed);

// Démarre la boucle principale
setInterval(loop, 1000 / FPS);