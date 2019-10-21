package datapath

import chisel3.iotesters.PeekPokeTester

class AluTests(c: ALU) extends PeekPokeTester(c){
	poke(c.io.a , 6)
	poke(c.io.b , 7)
	poke(c.io.AluControl, 0)
	step(1)
	expect(c.io.output, 13)
}
