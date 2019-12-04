"use strict";

const fs = require('fs');

function day1() {

    this.fuelRequiredForMass = function(mass) {
        return Math.floor(mass / 3) - 2;
    }

    this.allFuelRequired = function(inputFileName) {
        var sum = 0;

        // processInput_.processLineByLine();

        // var lineReader = require('readline').createInterface({
        //     input: require('fs').createReadStream('./inputs/day1-input.txt')
        //   });
          
        // lineReader.on('line', function (line) {
        //     //console.log(line);
        //     sum += 1;//this.fuelRequiredForMass(line);
        // });
        // return sum;        
        
        // console.log(buf);
    }

}


const day1_ = new day1();
const input_lines = fs.readFileSync('./inputs/day1-input.txt', 'utf8').toString().split("\n")
const sum = (input_lines) => input_lines.reduce( (acc, e) => acc + parseInt(e), 0);

let sum1 = 0;
for(let line of input_lines) {
    sum1 = sum1 + day1_.fuelRequiredForMass(line); 
    // console.log(day1_.fuelRequiredForMass(line));
}
console.log(sum1);


module.exports = day1;
// export default fuelForMass;
