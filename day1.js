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

    this.allFuelRequired = function(name, inputFileName, massCalcFunction) {
        const fs = require('fs');
        const input_lines = fs.readFileSync(inputFileName, 'utf8').toString().split("\n")
        const sum = (input_lines) => input_lines.reduce( (acc, e) => acc + massCalcFunction.call(this, parseInt(e), 0), 0);
        console.log(`${name}: ${sum(input_lines)}`);
    }

}

const day1_ = new day1();
day1_.allFuelRequired("part1", "./inputs/day1-input.txt", day1_.fuelRequiredForMassPart1);
day1_.allFuelRequired("part2", "./inputs/day1-input.txt", day1_.fuelRequiredForMassPart2);

module.exports = day1;
// export default fuelForMass;
