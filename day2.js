"use strict";

function day2() {

    this.runIntCode = function(input) {
        let step = 'opCode';
        let opCode, op1Pos, op2Pos, resultPos;
        for(let i = 0; i < input.length; i++) {
            if(step === 'opCode') {
                opCode = input[i];
                if(opCode === 99) {
                    break;
                }
                step = 'op1Pos';
            } else if(step === 'op1Pos') {
                op1Pos = input[i];
                step = 'op2Pos';
            } else if(step === 'op2Pos') {
                op2Pos = input[i];
                step = 'resultPos';
            } else if(step === 'resultPos') {
                resultPos = input[i];
                const result = opCode === 1 ? input[op1Pos] + input[op2Pos] : input[op1Pos] * input[op2Pos];
                input[resultPos] = result;
                step = 'opCode';
            }
        }
        return input;
    }

    this.runIntCodeRecursive = function(input, pos) {
        const opCode = input[pos];
        const op1 = input[input[pos+1]];
        const op2 = input[input[pos+2]];
        let opResult = undefined;
        if(opCode === 1) {
            opResult = op1 + op2;
        } else if (opCode === 2) {
            opResult = op1 * op2;
        } else if (opCode === 99) {
            return input;
        }
        input[input[pos+3]] = opResult
        return this.runIntCode(input, pos+4);
    }

    this.restore1202ProgramAlarmState = function(input) {
        input[0] = 12;
        input[2] = 2
        return input;
    }
}

function executor(inputFile, calcObj) {

    this.execute = function(name) {
        const fs = require('fs');
        const input_integers = fs.readFileSync(inputFile, 'utf8').toString().split(",");
        const input1202Adjusted = calcObj.restore1202ProgramAlarmState(input_integers);
        const intCodeResult = calcObj.runIntCode(input1202Adjusted, 0);
        console.log(`${name}: ${intCodeResult}`);
    }

}

const day2Instance = new day2();
const executorInstance = new executor("./inputs/day2-input.txt", day2Instance);
executorInstance.execute("part1");


module.exports = day2;