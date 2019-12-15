"use strict";

class Line {
    constructor(coordBegin, coordEnd) {
        this.coords = coordRange(coordBegin, coordEnd);
    }

    has(coord) {
        return this.coords.filter(coordInThisLine => coordInThisLine.x !== 0 && coordInThisLine.y !== 0).filter(coordInThisLine => JSON.stringify(coordInThisLine) === JSON.stringify(coord));
    }

    intersects(otherLine) {
        // return this.coords.filter(coord => otherLine.has(coord));
        let result = [];
        for(let point of this.coords) {
            let commonPoints = otherLine.has(point);
            if(commonPoints.length !== 0) {
                result = result.concat(commonPoints);
            }
        }
        return result;
    }
}

class Coord2 {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }    
}

function intRange(start, end) {
    let result = [];
    if(end > start) {
        for (let i = start; i <= end; i++) {
            result.push(i);
        }
    } else {
        for (let i = start; i >= end; i--) {
            result.push(i);
        }
    }
    return result;
}

function coordRange(coordBegin, coordEnd) {
    if(coordBegin.x === coordEnd.x) {
        return intRange(coordBegin.y, coordEnd.y).map(nextY => new Coord2(coordBegin.x, nextY));
    } else {
        return intRange(coordBegin.x, coordEnd.x).map(nextX => new Coord2(nextX, coordBegin.y));
    }
}

function intersections(lines1, lines2) {
    let result = [];
    for (let line1 of lines1) {
        for (let line2 of lines2) {
            let intersections = line1.intersects(line2);
            if (intersections !== undefined) {
                result = result.concat(intersections);
            }
        }
    }
    return result;
}

// class Coord extends Array {
//     constructor(x, y) {
//         super(x, y);
//         Object.freeze(this);
//     }

//     // get x() {
//     //     const [x, y] = this;
//     //     return this.x;
//     // }
//     // get y() {
//     //     const [x, y] = this;
//     //     return this.y;
//     // }
    
// }

function movesToCoordinates(moves, initialCoord) {
    let result = [initialCoord];
    let currentCoord = initialCoord;
    for(let move of moves) {        
        currentCoord = moveToCoordinates(move, currentCoord);
        result.push(currentCoord);
    }
    return result;
}

function moveToCoordinates(move, currentCoord) {
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

function readInput(inputFile) {
    let inputLines = [];
    var lineReader = require('readline').createInterface({
        input: require('fs').createReadStream(inputFile)
      });
      
      lineReader.on('line', function (line) {          
        // console.log('Line from file:', line.toString().split(','));
        inputLines = inputLines.concat(line.toString().split(','));
      });
    return inputLines;
}

function readInputSync(inputFile) {
    const fs = require('fs');    
    const inputLines = fs.readFileSync(inputFile, 'utf8').toString().split("\n");
    return [inputLines[0].toString().split(','), inputLines[1].toString().split(',')];
}

function pointsToLines(points) {
    let result = [];
    let prevPoint = null;
    for (let point of points) {
        if (prevPoint != null) {
            result.push(new Line(prevPoint, point));
        } 
        prevPoint = point;        
    }
    return result;
}

function manhattanDistanceForClosestIntersection(intersectionPoints) {
    let result = Number.MAX_VALUE;
    for(let intersectionPoint of intersectionPoints) {
        let distance = Math.abs(intersectionPoint.x) + Math.abs(intersectionPoint.y);
        if(distance < result) {
            result = distance;
        }
    }
    return result;
}

let [inputLine1, inputLine2] = readInputSync('./inputs/day3-input.txt');
// console.log(movesToCoordinates(inputLine1, new Coord2(0,0)));
// console.log(pointsToLines(movesToCoordinates(inputLine1, new Coord2(0,0))));
// console.log(pointsToLines(movesToCoordinates(inputLine2, new Coord2(0,0))));
console.log(manhattanDistanceForClosestIntersection(intersections(pointsToLines(movesToCoordinates(inputLine1, new Coord2(0,0))), pointsToLines(movesToCoordinates(inputLine2, new Coord2(0,0))))));

module.exports = {moveToCoordinates, movesToCoordinates, Coord2, intRange, coordRange, Line, intersections, pointsToLines};
