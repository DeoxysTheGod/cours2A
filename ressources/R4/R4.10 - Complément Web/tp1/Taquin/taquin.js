const size = document.getElementById('size');
const score = document.getElementById('score');

var board;
var win;
var empty;

var nbClick;

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
    score.innerHTML = nbClick;
    areYaWinningSon();
}

const grid = document.getElementById('taquin');

grid.addEventListener('click', (event) => {
    if (event.target.value === '0') {
        return;
    }
    const cell = event.target;
    const row = cell.parentNode;
    const grid = row.parentNode;
    const row_index = Array.prototype.indexOf.call(grid.children, row);
    const cell_index = Array.prototype.indexOf.call(row.children, cell);
    if (isMovable(row_index, cell_index)) {
        swap(row_index, cell_index);
        displayGame();
    }
});

function isMovable(x, y) {
    if (x === empty[0] && y === empty[1] + 1) return true;
    else if (x === empty[0] && y === empty[1] - 1) return true;
    else if (x === empty[0] + 1 && y === empty[1]) return true;
    else if (x === empty[0] - 1 && y === empty[1]) return true;
    else return false;
}

function swap(x, y) {
    board[empty[0]][empty[1]] = board[x][y];
    board[x][y] = 0;
    empty = [x, y];
    nbClick++;
    refresh();
}

function refresh() {
    const div = document.getElementById('taquin');
    div.innerHTML = '';
}

function areYaWinningSon() {
    if (board.join(',') === win.join(',')) {
        document.getElementById('win').innerHTML = 'You win !';
    }
}

function shuffle() {
    let i = 0;
    while (i < size.value**2) {
        let x = Math.floor(Math.random() * board.length);
        let y = Math.floor(Math.random() * board.length);
        swap(x, y);
        i++;
    }
}

const starBtn = document.getElementById('start');

starBtn.addEventListener('click', () => {
    board = generateBoard(size.value);
    win = generateBoard(size.value);
    empty = [board.length-1, board.length-1];
    shuffle();
    nbClick = 0;
    displayGame();
    document.getElementById('win').innerHTML = '';
});