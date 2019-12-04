// import fuelForMass from '../day1';
var assert = require('assert');
var day1 = require('../day1');

const day1_ = new day1();

// describe('Array', function() {
//   describe('#indexOf()', function() {
//     it('should return -1 when the value is not present', function() {
//       assert.equal([1, 2, 3].indexOf(4), -1);
//     });
//   });
// });


describe('fuelRequiredForMassPart1', function() {
    it('divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMassPart1(12), 2);
    }); 
    it('not divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMassPart1(14), 2);
    }); 
    it('not divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMassPart1(1969), 654);
    }); 
    it('not divisable by 3', function() {
        assert.equal(day1_.fuelRequiredForMassPart1(100756), 33583);
    }); 
    
});

describe('fuelRequiredForMassPart2', function() {
  it('divisable by 3', function() {
      assert.equal(day1_.fuelRequiredForMassPart2(12, 0),  2);
  }); 
  it('not divisable by 3', function() {
      assert.equal(day1_.fuelRequiredForMassPart2(14, 0), 2);
  }); 
  it('not divisable by 3', function() {
      assert.equal(day1_.fuelRequiredForMassPart2(1969, 0), 966);
  }); 
  it('not divisable by 3', function() {
      assert.equal(day1_.fuelRequiredForMassPart2(100756, 0), 50346);
  }); 
  
});