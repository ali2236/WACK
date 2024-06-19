package ir.annotations

import generation.WebAssemblyInstruction
import ir.finder.Visitable


interface WackAnnotation : WebAssemblyInstruction, Visitable {

}