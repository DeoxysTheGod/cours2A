const size = document.getElementById('size');
const score = document.getElementById('score');

let board;
let win;
let empty;

let nbClick;

// Génère la grille
function generateBoard(size) {
    let board = [];
    let num = 1;
    for (let i = 0; i < size; i++) {
        let row = [];
        for (let j = 0; j < size; j++) {
                row.push(num++);
        }
        board.push(row);
    }
    board[size-1][size-1] = 0;
    return board;
}

// Affiche le jeu
function displayGame() {
    const div = document.getElementById('taquin');
    for (let i = 0; i < board.length; i++) {
        const row = document.createElement('div');
        row.classList.add('row');
        div.appendChild(row);
        for (let j = 0; j < board[i].length; j++) {
            const cell = document.createElement('button');
            cell.classList.add('cell');
            cell.value = board[i][j].toString();
            if (board[i][j] === 0) {
                cell.classList.add('empty');
            }
            cell.innerHTML = board[i][j] === 0 ? "" : board[i][j].toString();
            row.appendChild(cell);
        }
    }
    score.innerHTML = 'Nombre de mouvement : ' + nbClick;
    isWinning();
}

// Récupère la grille
const grid = document.getElementById('taquin');

// Déplace les cases
grid.addEventListener('click', (event) => {
    // Si on clique sur une case
    if (event.target.classList.contains('cell')) {
        if (event.target.value === '0') {
            return;
        }
        const cell = event.target;
        const row = cell.parentNode;
        const grid = row.parentNode;
        // Récupère l'index de la case
        const row_index = Array.prototype.indexOf.call(grid.children, row);
        const cell_index = Array.prototype.indexOf.call(row.children, cell);
        if (isMovable(row_index, cell_index)) {
            swap(row_index, cell_index);
            displayGame();
        } else {
            // Fait trembler la case
            cell.classList.add('shake');
            setTimeout(() => {
                cell.classList.remove('shake');
            }, 500);
        }
    }
});

// Vérifie si la case est déplaçable
function isMovable(x, y) {
    if (x === empty[0] && y === empty[1] + 1) return true;
    else if (x === empty[0] && y === empty[1] - 1) return true;
    else if (x === empty[0] + 1 && y === empty[1]) return true;
    else if (x === empty[0] - 1 && y === empty[1]) return true;
    else return false;
}

// Echange les cases
function swap(x, y) {
    board[empty[0]][empty[1]] = board[x][y];
    board[x][y] = 0;
    empty = [x, y];
    nbClick++;
    refresh();
}

// Supprime le contenu de la div du jeu
function refresh() {
    const div = document.getElementById('taquin');
    div.innerHTML = '';
}

// Vérifie si le joueur a gagné
function isWinning() {
    if (board.join(',') === win.join(',')) {
        document.getElementById('win').innerHTML = 'Vous avez gagné !';
    }
}

// Mélange le jeu
function shuffle() {
    let i = 0;
    while (i < size.value**2) {
        let x = Math.floor(Math.random() * board.length);
        let y = Math.floor(Math.random() * board.length);
        swap(x, y);
        i++;
    }
}

// Bouton pour lancer le jeu
const starBtn = document.getElementById('start');

// Lance le jeu
starBtn.addEventListener('click', () => {
    if (size.value < 3) {
        alert('La taille minimum est de 3');
        return;
    }
    board = generateBoard(size.value);
    win = generateBoard(size.value);
    empty = [board.length-1, board.length-1];
    shuffle();
    nbClick = 0;
    displayGame();
    document.getElementById('win').innerHTML = '';
});