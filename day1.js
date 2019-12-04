"use strict";

function day1() {

    this.fuelRequiredForMass = function(mass) {
        return Math.floor(mass / 3) - 2;
    }

    this.fuelRequiredForMassRecursive = function(mass, sum) {
        let reqFuel = Math.floor(mass / 3) - 2;
        // console.log(`sum: ${sum}, mass: ${mass}, reqFuel: ${reqFuel},`);
        return reqFuel >= 0 ? this.fuelRequiredForMassRecursive(reqFuel, sum+reqFuel) : sum;
    }

    this.allFuelRequired = function(inputFileName, f) {
        const fs = require('fs');
        const input_lines = fs.readFileSync(inputFileName, 'utf8').toString().split("\n")
        const sum = (input_lines) => input_lines.reduce( (acc, e) => acc + f.call(this, parseInt(e), 0), 0);
        console.log(sum(input_lines));
    }

}

const day1_ = new day1();
day1_.allFuelRequired("./inputs/day1-input.txt", day1_.fuelRequiredForMass);
day1_.allFuelRequired("./inputs/day1-input.txt", day1_.fuelRequiredForMassRecursive);

module.exports = day1;
// export default fuelForMass;
