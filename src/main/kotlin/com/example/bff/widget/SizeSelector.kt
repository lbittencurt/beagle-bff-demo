package com.example.bff.widget

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget

enum class SizeType {
    XS, S, M, L, XL
}

@RegisterWidget
class SizeSelector (
    val sizes: List<SizeType>
) : Widget()