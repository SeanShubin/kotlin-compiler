package com.seanshubin.kotlin.compiler.domain

interface TopAssembler<SourceType, ConstructedType> {
    fun assemble(tree: Tree<SourceType>): ConstructedType
}
