"use strict";

// exports.line = class Line extends Array {
//     constructor(startCoord, endCoord) {
//         super(startCoord, endCoord);
//         Object.freeze(this);
//     }
// }

class Coord2 {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }    
}

class Coord extends Array {
    constructor(x, y) {
        super(x, y);
        Object.freeze(this);
    }

    // get x() {
    //     const [x, y] = this;
    //     return this.x;
    // }
    // get y() {
    //     const [x, y] = this;
    //     return this.y;
    // }
    
}

function movesTo(moves, initialCoord) {
    let result = [initialCoord];
    let currentCoord = initialCoord;
    for(let move of moves) {
        result.push(moveTo(move, currentCoord));
    }
    return result;
}

function moveTo(move, currentCoord) {
    const direction = move.substring(0, 1);
    const length = parseInt(move.substring(1), 10);    
    // const [currentX, currentY] = currentCoord;
    switch (direction) {
        case 'U': return new Coord2(currentCoord.x, currentCoord.y + length);
        case 'D': return new Coord2(currentCoord.x, currentCoord.y - length);
        case 'L': return new Coord2(currentCoord.x - length, currentCoord.y);
        case 'R': return new Coord2(currentCoord.x + length, currentCoord.y);
        // case 'U': return new Coord(currentX, currentY + length);
        // case 'D': return new Coord(currentX, currentY - length);
        // case 'L': return new Coord(currentX - length, currentY);
        // case 'R': return new Coord(currentX + length, currentY);
    }    
}

// console.log(moveTo('D2', new Coord(0, 0)));

// exports.movesToCoordiantes = function (moves) {
//     return moves.map(move => );
// }

module.exports = {moveTo, movesTo, Coord2};
