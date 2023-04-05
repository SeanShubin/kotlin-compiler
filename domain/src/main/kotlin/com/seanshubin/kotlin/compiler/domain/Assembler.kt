package com.seanshubin.kotlin.compiler.domain

interface Assembler<SourceType, ConstructedType> {
    val name: String
    fun assemble(
        lookupByName: (String) -> Assembler<SourceType, ConstructedType>,
        tree: Tree<SourceType>
    ): ConstructedType

    fun part(
        lookupByName: (String) -> Assembler<SourceType, ConstructedType>,
        tree: Tree<SourceType>,
        index: Int
    ): ConstructedType {
        tree as Tree.Branch
        val treePart = tree.list[index]
        val part = lookupByName(treePart.name).assemble(lookupByName, treePart)
        return part
    }

    fun branchParts(
        lookupByName: (String) -> Assembler<SourceType, ConstructedType>,
        tree: Tree<SourceType>
    ): List<ConstructedType> {
        tree as Tree.Branch
        val parts = tree.list.map { lookupByName(it.name).assemble(lookupByName, it) }
        return parts
    }

    fun interleaveBranchConstructedOuter(
        lookupByName: (String) -> Assembler<SourceType, ConstructedType>,
        tree: Tree<SourceType>
    ): List<ConstructedType> {
        tree as Tree.Branch
        if (tree.list.size % 2 == 0) {
            throw RuntimeException("expected odd number of branch parts")
        }
        val list = tree.list.filterIndexed { index, _ ->
            index % 2 == 0
        }
        val parts = list.map { lookupByName(it.name).assemble(lookupByName, it) }
        return parts
    }

    fun interleaveBranchSourceInner(tree: Tree<SourceType>): List<SourceType> {
        tree as Tree.Branch
        if (tree.list.size % 2 == 0) {
            throw RuntimeException("expected odd number of branch parts")
        }
        val list = tree.list.filterIndexed { index, _ ->
            index % 2 == 1
        }
        val parts = list.map { leaf ->
            leaf as Tree.Leaf
            leaf.value
        }
        return parts
    }

    fun assembleInterleaved(
        lookupByName: (String) -> Assembler<SourceType, ConstructedType>,
        tree: Tree<SourceType>,
        combine: (ConstructedType, SourceType, ConstructedType) -> ConstructedType
    ): ConstructedType {
        val constructs = interleaveBranchConstructedOuter(lookupByName, tree)
        val sources = interleaveBranchSourceInner(tree)
        var top = constructs[0]
        sources.indices.forEach { sourceIndex ->
            val constructIndex = sourceIndex + 1
            val left = top
            val middle = sources[sourceIndex]
            val right = constructs[constructIndex]
            top = combine(left, middle, right)
        }
        return top
    }
}
