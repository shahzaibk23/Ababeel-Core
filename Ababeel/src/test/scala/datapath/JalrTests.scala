package datapath

import chisel3.iotesters.PeekPokeTester

class JalrTests(c: JalrTarget) extends PeekPokeTester(c){
	poke(c.io.a, 2)
	poke(c.io.b, 6)
	step(1)
	step(1)
}
