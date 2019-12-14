"use strict";
const day3 = require('../day3');
const assert = require('assert');

// console.log(day3.f('R8,U5,L5,D3'));

// describe('coordinates', function() {
//     it('add', function() {
//         assert.deepStrictEqual(day3.g('R8,U5,L5,D3'), [day3.coord(0,0),day3.coord(8,0), day3.coord(7,5), day3.coord(3,5), day3.coord(3,2)]);
//     });     

// });

describe('moveTo', function() {
    it('moveLeft', function() {
        assert.deepStrictEqual(day3.moveTo('L1', new day3.Coord2(0,0)), new day3.Coord2(-1,0));
    });     
    it('moveRight', function() {
        assert.deepStrictEqual(day3.moveTo('R1', new day3.Coord2(0,0)), new day3.Coord2(1,0));
    });     
    it('moveUp', function() {
        assert.deepStrictEqual(day3.moveTo('U1', new day3.Coord2(0,0)), new day3.Coord2(0,1));
    });     
    it('moveDown', function() {
        assert.deepStrictEqual(day3.moveTo('D1', new day3.Coord2(0,0)), new day3.Coord2(0,-1));
    });     
});

describe('movesTo', function() {
    it('movesOnce', function() {
        assert.deepStrictEqual(day3.movesTo(['L1'], new day3.Coord2(0,0)), [new day3.Coord2(0,0), new day3.Coord2(-1,0)]);
    });     
    it('movesTwice', function() {
        assert.deepStrictEqual(day3.movesTo(['L1', 'U1'], new day3.Coord2(0,0)), [new day3.Coord2(0,0), new day3.Coord2(-1,0), new day3.Coord2(-1,1)]);
    });     
    // it('moveUp', function() {
    //     assert.deepStrictEqual(day3.moveTo('U1', new day3.Coord2(0,0)), new day3.Coord2(0,1));
    // });     
    // it('moveDown', function() {
    //     assert.deepStrictEqual(day3.moveTo('D1', new day3.Coord2(0,0)), new day3.Coord2(0,-1));
    // });     
});
