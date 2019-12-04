"use strict";

function day1() {

    this.fuelRequiredForMassPart1 = function(mass) {
        return Math.floor(mass / 3) - 2;
    }

    this.fuelRequiredForMassPart2 = function(mass, sum) {
        let reqFuel = Math.floor(mass / 3) - 2;
        // console.log(`sum: ${sum}, mass: ${mass}, reqFuel: ${reqFuel},`);
        return reqFuel >= 0 ? this.fuelRequiredForMassPart2(reqFuel, sum+reqFuel) : sum;
    }

}

function executor(inputFile, calcObj) {

    this.execute = function(name, calcFunction) {
        const fs = require('fs');
        const input_lines = fs.readFileSync(inputFile, 'utf8').toString().split("\n")
        const sum = (input_lines) => input_lines.reduce( (acc, elem) => acc + calcFunction.call(calcObj, parseInt(elem), 0), 0);
        console.log(`${name}: ${sum(input_lines)}`);
    }

}

const day1_ = new day1();
const executor_ = new executor("./inputs/day1-input.txt", day1_);
executor_.execute("part1", day1_.fuelRequiredForMassPart1);
executor_.execute("part2", day1_.fuelRequiredForMassPart2);


module.exports = day1;
// export default fuelForMass;
