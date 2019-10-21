package datapath

import chisel3.iotesters.PeekPokeTester

class InsMemTests(c: InsMem) extends PeekPokeTester(c){
	poke(c.io.wrAdder, 0)
	step(1)
}
