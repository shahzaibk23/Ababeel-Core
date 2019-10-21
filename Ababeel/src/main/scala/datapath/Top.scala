package datapath

import chisel3._

class Top extends Module{
	val io = IO(new Bundle{
		//val main_Instruction = Input(UInt(32.W))
		//val main_PC = Input(UInt(32.W))
		val main_RegOut = Output(SInt(32.W))
	})

	//io.main_RegOut := 0.S	

	val control = Module(new Control())
	val regFile = Module(new RegFile())
	val immGen = Module(new ImmGen())
	val aluControl = Module(new AluControl())
	val alu = Module(new ALU())
	val Pc = Module(new PC())
	val insMem = Module(new InsMem())
	val jalr = Module(new JalrTarget())
	val dataMem = Module(new MainMem())
	
		
	
	// PC
	Pc.io.input := Pc.io.pc4
	
	// Instruction
	insMem.io.wrAdder := Pc.io.pc(11,2)
	 val main_Instruction = insMem.io.rData
	
	//opcode
	control.io.OpCode := main_Instruction(6,0)
	
	//Register
	regFile.io.RegWrite := control.io.RegWrite
	regFile.io.rs1 := main_Instruction(19,15)
	regFile.io.rs2 := main_Instruction(24,20)
	regFile.io.rd := main_Instruction(11,7)
	
	//ImmediTE
	immGen.io.ins := main_Instruction
	immGen.io.PC := Pc.io.pc

	//aLUcONTROL
	aluControl.io.AluOp := control.io.AluOp
	aluControl.io.Func3 := main_Instruction(14,12)
	aluControl.io.Func7 := main_Instruction(30)

	
	// Operand A
	when(control.io.Operand_aSel === "b01".U){
		alu.io.a := (Pc.io.pc).asSInt
	}.elsewhen(control.io.Operand_aSel === "b10".U){
		alu.io.a := (Pc.io.pc4).asSInt 
	}.otherwise{
		alu.io.a := regFile.io.rd1
	}	

	//Operand B
	when(control.io.ExtendSel === "b00".U & control.io.Operand_bSel === 1.U){
		alu.io.b := immGen.io.I_Imm
	}.elsewhen(control.io.ExtendSel === "b01".U & control.io.Operand_bSel === 1.U){
		alu.io.b := immGen.io.S_Imm
	}.elsewhen(control.io.ExtendSel === "b10".U & control.io.Operand_bSel === 1.U){
		alu.io.b := immGen.io.U_Imm
	}.otherwise{
		alu.io.b := regFile.io.rd2
	}

	alu.io.AluControl := aluControl.io.AluC

	//ALU
	
	//regFile.io.WriteData := alu.io.output



	//Jalr
	jalr.io.a := regFile.io.rd1
	jalr.io.b := immGen.io.I_Imm

	when(control.io.NextPcSel === "b11".U){
		Pc.io.input := (jalr.io.output).asUInt
	}.elsewhen(control.io.NextPcSel === "b01".U & (control.io.Branch & alu.io.AluBranch) === 1.U){
		Pc.io.input := (immGen.io.SB_Imm).asUInt
	}.elsewhen(control.io.NextPcSel === "b10".U ){
		Pc.io.input := (immGen.io.UJ_Imm).asUInt
	}.otherwise{
		Pc.io.input := Pc.io.pc4
	}

	//DataMemory
	dataMem.io.Address := (alu.io.output(9,2)).asUInt
	dataMem.io.str := control.io.MemWrite
	dataMem.io.ld := control.io.MemRead
	dataMem.io.DataIn := regFile.io.rd2
	//regFile.io.WriteData := dataMem.io.DataOut  



	when(control.io.MemToReg === 1.U){
		regFile.io.WriteData :=  dataMem.io.DataOut
	}.otherwise{
		regFile.io.WriteData := alu.io.output
	}

	io.main_RegOut := regFile.io.WriteData
}
