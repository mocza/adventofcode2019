"use strict";
var _ = require('lodash');

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
                let result;
                if(opCode === 1) {
                    result = input[op1Pos] + input[op2Pos];
                } else if (opCode === 2) {
                    result = input[op1Pos] * input[op2Pos];
                } else {
                    console.log(`invalid opCode: ${opCode}`);
                    break;
                }
                // const result = opCode === 1 ? input[op1Pos] + input[op2Pos] : input[op1Pos] * input[op2Pos];
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

    this.setProgramState = function(input, noun, verb) {
        input[1] = noun;
        input[2] = verb
        return input;
    }
}

function executor(inputFile, calcObj) {

    this.execute = function(name, noun, verb) {
        const fs = require('fs');
        const input_integers = fs.readFileSync(inputFile, 'utf8').toString().split(",").map(s => parseInt(s));
        const input1202Adjusted = calcObj.setProgramState(input_integers, noun, verb);
        const intCodeResult = calcObj.runIntCode(input1202Adjusted, 0);
        // console.log(`${name}: ${intCodeResult}`);
        return intCodeResult;
    }

}

const day2Instance = new day2();
const executorInstance = new executor("./inputs/day2-input.txt", day2Instance);
executorInstance.execute("part1", 12, 2);


function _cartesianProductOf(args) {
    if (arguments.length>1) args=_.toArray(arguments);

    // strings to arrays of letters
    args=_.map(args, opt=>typeof opt==='string'?_.toArray(opt):opt)

    return _.reduce(args, function(a, b) {
        return _.flatten(_.map(a, function(x) {
            return _.map(b, function(y) {
                return _.concat(x,[y]);
            });
        }), true);
    }, [ [] ]);
}

/** Generate all combination of arguments from objects
  *  {Object} opts    - An object or arrays with keys describing options  {firstName:['Ben','Jade','Darren'],lastName:['Smith','Miller']}
  *  {Array}        - An array of objects e.g. [{firstName:'Ben',LastName:'Smith'},{..]
  **/
function _cartesianProductObj(optObj){
    var keys = _.keys(optObj);
    var opts = _.values(optObj);
    var combs = _cartesianProductOf(opts);
    return _.map(combs,function(comb){
        return _.zipObject(keys,comb);
    });
}

function product(opts){
    if (arguments.length===1 && !_.isArray(opts))
        return _cartesianProductObj(opts)
    else if (arguments.length===1)
        return _cartesianProductOf(opts)
    else
        return _cartesianProductOf(arguments)
}

function permutations(obj, n){
    if (typeof obj=='string') obj = _.toArray(obj)
    n = n?n:obj.length
    // make n copies of keys/indices
    for (var j = 0, nInds=[]; j < n; j++) {nInds.push(_.keys(obj)) }
    // get product of the indices, then filter to remove the same key twice
    var arrangements = product(nInds).filter(pair=>pair[0]!==pair[1])
    return _.map(arrangements,indices=>_.map(indices,i=>obj[i]))
}

function range(start, end) {
    var ans = [];
    for (let i = start; i <= end; i++) {
        ans.push(i);
    }
    return ans;
}


//console.log(permutations([1,2,3], 2));
// console.log(range(0, 100));
for (let perm of permutations(range(0, 100), 2)) {
    let noun, verb;
    [noun, verb] = perm;
    const answer = executorInstance.execute("part1", noun, verb);
    if (answer[0] === 19690720) {
        console.log(`noun: ${noun}, verb: ${verb}`);
        break;
    }
}


module.exports = day2;