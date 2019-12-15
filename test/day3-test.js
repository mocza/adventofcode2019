"use strict";
const day3 = require('../day3');
const assert = require('assert');

// console.log(day3.f('R8,U5,L5,D3'));

// describe('coordinates', function() {
//     it('add', function() {
//         assert.deepStrictEqual(day3.g('R8,U5,L5,D3'), [day3.coord(0,0),day3.coord(8,0), day3.coord(7,5), day3.coord(3,5), day3.coord(3,2)]);
//     });     

// });

describe('moveToCoordinates', function() {
    it('moveLeft', function() {
        assert.deepStrictEqual(day3.moveToCoordinates('L1', new day3.Coord2(0,0)), new day3.Coord2(-1,0));
    });     
    it('moveRight', function() {
        assert.deepStrictEqual(day3.moveToCoordinates('R1', new day3.Coord2(0,0)), new day3.Coord2(1,0));
    });     
    it('moveUp', function() {
        assert.deepStrictEqual(day3.moveToCoordinates('U1', new day3.Coord2(0,0)), new day3.Coord2(0,1));
    });     
    it('moveDown', function() {
        assert.deepStrictEqual(day3.moveToCoordinates('D1', new day3.Coord2(0,0)), new day3.Coord2(0,-1));
    });     
});

describe('movesToCoordinates', function() {
    it('movesOnce', function() {
        assert.deepStrictEqual(day3.movesToCoordinates(['L1'], new day3.Coord2(0,0)), [new day3.Coord2(0,0), new day3.Coord2(-1,0)]);
    });     
    it('movesTwice', function() {
        assert.deepStrictEqual(day3.movesToCoordinates(['L1', 'U1'], new day3.Coord2(0,0)), [new day3.Coord2(0,0), new day3.Coord2(-1,0), new day3.Coord2(-1,1)]);
    });     
});

describe('pointsToLines', function() {
    it('two points to one line one length', function() {
        assert.deepStrictEqual(day3.pointsToLines([new day3.Coord2(0,0), new day3.Coord2(-1,0)]), [new day3.Line(new day3.Coord2(0,0), new day3.Coord2(-1,0))]);
    });     
    it('two points to one line two length', function() {
        assert.deepStrictEqual(day3.pointsToLines([new day3.Coord2(0,0), new day3.Coord2(2,0)]), [new day3.Line(new day3.Coord2(0,0), new day3.Coord2(2,0))]);
    });     
});

describe('intRange', function() {
    it('0 to 5 inclusive', function() {
        assert.deepStrictEqual(day3.intRange(0, 5), [0, 1, 2, 3, 4, 5]);
    });     
    it('3 to 0 inclusive', function() {
        assert.deepStrictEqual(day3.intRange(3, 0), [3, 2, 1, 0]);
    });     
});

describe('coordRange', function() {
    it('single point line', function() {
        assert.deepStrictEqual(day3.coordRange(new day3.Coord2(0,0), new day3.Coord2(0,0)), [new day3.Coord2(0,0)]);
    });         
    it('two points line', function() {
        assert.deepStrictEqual(day3.coordRange(new day3.Coord2(0,0), new day3.Coord2(0,1)), [new day3.Coord2(0,0), new day3.Coord2(0,1)]);
    });     
    it('three points line vertical', function() {
        assert.deepStrictEqual(day3.coordRange(new day3.Coord2(0,0), new day3.Coord2(0,2)), [new day3.Coord2(0,0), new day3.Coord2(0,1), new day3.Coord2(0,2)]);
    });     
    it('three points line horizontal', function() {
        assert.deepStrictEqual(day3.coordRange(new day3.Coord2(0,0), new day3.Coord2(2,0)), [new day3.Coord2(0,0), new day3.Coord2(1,0), new day3.Coord2(2,0)]);
    });     
    it('three points line horizontal going right to left', function() {
        assert.deepStrictEqual(day3.coordRange(new day3.Coord2(2,0), new day3.Coord2(0,0)), [new day3.Coord2(2,0), new day3.Coord2(1,0), new day3.Coord2(0,0)]);
    });     
    it('three points line vertical going from up to down', function() {
        assert.deepStrictEqual(day3.coordRange(new day3.Coord2(0,2), new day3.Coord2(0,0)), [new day3.Coord2(0,2), new day3.Coord2(0,1), new day3.Coord2(0,0)]);
    });     
});

describe('line', function() {    
    it('point is not on the line ', function() {
        assert.deepStrictEqual(new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0)).has(new day3.Coord2(1,1)), []);
    });         
    it('point is on the line#1', function() {
        assert.deepStrictEqual(new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0)).has(new day3.Coord2(0,0)), [new day3.Coord2(0,0)]);
    });         
    it('point is on the line#2', function() {
        assert.deepStrictEqual(new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0)).has(new day3.Coord2(1,0)), [new day3.Coord2(1,0)]);
    });         
    it('lines do not intersect ', function() {
        assert.deepStrictEqual(new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0)).intersects(
            new day3.Line(new day3.Coord2(8,8), new day3.Coord2(8,9))), []);
    });         
    it('lines intersect at one point #1', function() {
        assert.deepStrictEqual(new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0)).intersects(
            new day3.Line(new day3.Coord2(-1,0), new day3.Coord2(0,0))), [new day3.Coord2(0,0)]);
    });         
    it('lines intersect at one point #2', function() {
        assert.deepStrictEqual(new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0)).intersects(
            new day3.Line(new day3.Coord2(1,1), new day3.Coord2(1,0))), [new day3.Coord2(1,0)]);
    });         
});

describe('intersections', function() {
    it('no intersection', function() {
        assert.deepStrictEqual(day3.intersections([new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0))], 
        [new day3.Line(new day3.Coord2(1,1), new day3.Coord2(1,2))]), []);
    });         
    it('one intersection point', function() {
        assert.deepStrictEqual(day3.intersections([new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0))], 
        [new day3.Line(new day3.Coord2(1,1), new day3.Coord2(1,0))]), [new day3.Coord2(1,0)]);
    });         
    it('two intersection points', function() {
        assert.deepStrictEqual(day3.intersections([new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0))], 
        [new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0))]), [new day3.Coord2(0,0), new day3.Coord2(1,0)]);
    });         
    it('two intersection points', function() {
        assert.deepStrictEqual(day3.intersections([new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0))], 
        [new day3.Line(new day3.Coord2(0,0), new day3.Coord2(1,0))]), [new day3.Coord2(0,0), new day3.Coord2(1,0)]);
    });         

});

