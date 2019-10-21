package datapath

import chisel3.iotesters.PeekPokeTester

class MainMemTests(c: MainMem) extends PeekPokeTester(c){
	poke(c.io.Address, 1)
	poke(c.io.str, 1)
	poke(c.io.DataIn, 5)
	step(1)
	step(1)
	step(1)
	poke(c.io.Address, 1)
	poke(c.io.ld, 1)
	step(1)
	step(1)
	step(1)

}
