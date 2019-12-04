// import fuelForMass from '../day1';
var assert = require('assert');
var day1 = require('../day1');

const day1_ = new day1();

describe('Array', function() {
  describe('#indexOf()', function() {
    it('should return -1 when the value is not present', function() {
      assert.equal([1, 2, 3].indexOf(4), -1);
    });
  });
});


describe('fuelRequiredForMass', function() {
    it('divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMass(12), 2);
    }); 
    it('not divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMass(14), 2);
    }); 
    it('not divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMass(1969), 654);
    }); 
    it('not divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMass(100756), 33583);
    }); 
    
});