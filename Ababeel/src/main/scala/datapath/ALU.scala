package datapath

import chisel3._

class ALU extends Module{
	val io = IO(new Bundle{
		val a = Input(SInt(32.W))
		val b = Input(SInt(32.W))
		val AluControl = Input(UInt(5.W))
		val AluBranch = Output(UInt(1.W))
		val output = Output(SInt(32.W))
	})

	io.output := 0.S

	when(io.AluControl === "b00000".U){ 								// 0 Add
		io.output := io.a + io.b
	}.elsewhen(io.AluControl === "b00001".U){ 							// 1 sll, slli(Shifting)
		io.output:= (io.a(4,0) << io.b(4,0)).asSInt
	}.elsewhen(io.AluControl === "b00010".U){							// 2 slt,slti (Less than)
		when(io.a < io.b){
			io.output := 1.S
		}.otherwise{
			io.output := 0.S
		}
	}.elsewhen((io.AluControl === "b00011".U) | (io.AluControl === "b10110".U)){			// 3,22 sltu, sltui (Less than Unsigned)
		val Ua = io.a.asUInt
		val Ub = io.b.asUInt
		when(Ua < Ub){
			io.output := 1.S
		}.otherwise{
			io.output := 0.S
		}
	}.elsewhen(io.AluControl === "b00100".U){							// 4 xor, xori
		io.output := io.a ^ io.b
	}.elsewhen(io.AluControl === "b00101".U){							// 5 srl, srli
		val newA = io.a(4,0)
		val newB = io.b(4,0)		
		val shift = io.a.asUInt >> io.b.asUInt
		io.output := shift.asSInt
	}.elsewhen(io.AluControl === "b00110".U){							// 6 or, ori
		io.output := io.a | io.b
	}.elsewhen(io.AluControl === "b00111".U){							// 7 and,andi
		io.output := io.a & io.b
	}.elsewhen(io.AluControl === "b01000".U){							// 8 sub
		io.output := io.a - io.b
	}.elsewhen(io.AluControl === "b01101".U){							// 13 sra, srai
		io.output := (io.a(4,0) >> io.b(4,0)).asSInt
	}.elsewhen(io.AluControl === "b10000".U){							// 16 beq
		when(io.a === io.b){
			io.output := 1.S
		}.otherwise{
			io.output := 0.S
		}
	}.elsewhen(io.AluControl === "b10001".U){							//17 bne
		when(io.a === io.b){
			io.output := 0.S
		}.otherwise{
			io.output := 1.S
		}
	}.elsewhen(io.AluControl === "b10100".U){							//20 blt
		when(io.a < io.b){
			io.output := 1.S
		}.otherwise{
			io.output := 0.S
		}
	}.elsewhen(io.AluControl === "b10101".U){							//21 bge
		when((io.a === io.b) | (io.a > io.b)){
			io.output := 1.S
		}.otherwise{
			io.output := 0.S
		}
	}.elsewhen(io.AluControl === "b10111".U){							//23 bgeU
		val Ua = io.a.asUInt
		val Ub = io.b.asUInt
		when((Ua === Ub) | (Ua > Ub)){
			io.output := 1.S
		}.otherwise{
			io.output := 0.S
		}
	}.elsewhen(io.AluControl === "b11111".U){							//31 jal,jalr
		io.output := io.a
	}


	//Branching
	when((io.AluControl(4,3) === "b10".U) && (io.output === 1.S)){
		io.AluBranch := 1.U
	}.otherwise{
		io.AluBranch := 0.U
	}
}
