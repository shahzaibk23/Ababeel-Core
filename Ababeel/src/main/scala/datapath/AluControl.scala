package datapath

import chisel3._
import chisel3.util.Cat

class AluControl extends Module{
	val io = IO(new Bundle{
		val AluOp = Input(UInt(3.W))

		val Func3 = Input(UInt(3.W))
		val Func7 = Input(UInt(1.W))
		val AluC = Output(UInt(5.W))
	})


	io.AluC := 0.U

	when(io.AluOp === "b000".U){
		io.AluC := Cat(0.U, io.Func7, io.Func3)	// R
	}.elsewhen(io.AluOp === "b100".U){
		io.AluC := "b00000".U			// Load
	}.elsewhen(io.AluOp === "b101".U){
		io.AluC := "b00000".U			// store
	}.elsewhen(io.AluOp === "b010".U){
		io.AluC := Cat("b10".U, io.Func3)	// SB
	}.elsewhen(io.AluOp === "b001".U){
		when(io.Func3 === "b001".U){
			io.AluC := Cat("b0".U,io.Func7, io.Func3) 
		}.otherwise{
			io.AluC := Cat("b00".U,io.Func3)
		}
	}.elsewhen(io.AluOp === "b011".U){
		io.AluC := "b11111".U			// jal, jalr
	}.elsewhen(io.AluOp === "b110".U){
		io.AluC := "b00000".U 			//lui
	}
	
}
