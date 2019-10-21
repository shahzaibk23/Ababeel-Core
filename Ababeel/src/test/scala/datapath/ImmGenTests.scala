package datapath

import chisel3.iotesters.PeekPokeTester

class ImmGenTests(c: ImmGen) extends PeekPokeTester(c){
	poke(c.io.ins, 20)
	poke(c.io.PC, 0)
	step(1)
	expect(c.io.I_Imm, 0)
}
