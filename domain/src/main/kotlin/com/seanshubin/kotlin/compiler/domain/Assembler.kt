package com.seanshubin.kotlin.compiler.domain

interface Assembler<SourceType, ConstructedType> {
    val name: String
    fun assemble(lookupByName:(String)->Assembler<SourceType, ConstructedType>, tree: Tree<SourceType>): ConstructedType
    fun part(lookupByName:(String)->Assembler<SourceType, ConstructedType>, tree: Tree<SourceType>, index:Int):ConstructedType{
        tree as Tree.Branch
        val treePart = tree.list[index]
        val part = lookupByName(treePart.name).assemble(lookupByName, treePart)
        return part
    }
    fun branchParts(lookupByName:(String)->Assembler<SourceType, ConstructedType>, tree: Tree<SourceType>):List<ConstructedType> {
        tree as Tree.Branch
        val parts = tree.list.map { lookupByName(it.name).assemble(lookupByName, it) }
        return parts
    }
    fun interleaveBranchPartsOuter(lookupByName:(String)->Assembler<SourceType, ConstructedType>, tree: Tree<SourceType>):List<ConstructedType> {
        tree as Tree.Branch
        if(tree.list.size % 2 == 0){
            throw RuntimeException("expected odd number of branch parts")
        }
        val list = tree.list.filterIndexed{ index, _ ->
            index %2 == 0
        }
        val parts = list.map { lookupByName(it.name).assemble(lookupByName, it) }
        return parts
    }

}