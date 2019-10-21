package datapath

import chisel3.iotesters.PeekPokeTester

class AluControlTests(c: AluControl) extends PeekPokeTester(c){
	poke(c.io.AluOp,0)
	poke(c.io.Func3, 6)
	poke(c.io.Func7, 0)
	step(1)
	expect(c.io.AluC, 6)
}
