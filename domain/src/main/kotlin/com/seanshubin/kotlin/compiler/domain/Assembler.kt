package com.seanshubin.kotlin.compiler.domain

interface Assembler<SourceType, ConstructedType> {
    val name: String
    fun assemble(lookupByName:(String)->Assembler<SourceType, ConstructedType>, tree: Tree<SourceType>): ConstructedType
}