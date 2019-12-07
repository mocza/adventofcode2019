"use strict;"
var assert = require('assert');
var day2 = require('../day2');

const day2Instance = new day2();

describe('runIntCode', function() {
    it('add', function() {
        assert.deepStrictEqual(day2Instance.runIntCode([1,0,0,0,99], 0), [2,0,0,0,99]);
    }); 
    it('multiply', function() {
        assert.deepStrictEqual(day2Instance.runIntCode([2,3,0,3,99], 0), [2,3,0,6,99]);
    }); 
    it('multiply', function() {
        assert.deepStrictEqual(day2Instance.runIntCode([2,4,4,5,99,0], 0), [2,4,4,5,99,9801]);
    }); 
    it('add then multiply', function() {
        assert.deepStrictEqual(day2Instance.runIntCode([1,1,1,4,99,5,6,0,99], 0), [30,1,1,4,2,5,6,0,99]);
    }); 
    it('multiply then add', function() {
        assert.deepStrictEqual(day2Instance.runIntCode([1,1,1,4,99,5,6,0,99], 0), [30,1,1,4,2,5,6,0,99]);
    }); 
    
});

describe('restore1202ProgramAlarmState', function() {
    it('success', function() {
        assert.deepStrictEqual(day2Instance.restore1202ProgramAlarmState([1,0,0,0,99]), [1,12,2,0,99]);
    }); 
    
});



