package com.example.bff.builder

import br.com.zup.beagle.annotation.BeaglePreview
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.action.Navigate
import br.com.zup.beagle.widget.action.Route
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.*
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import com.example.bff.widget.*

@BeaglePreview
fun buildPreview() = OutfitScreen()

data class ShirtData(
    val id: String,
    val price: String
)

class OutfitScreen : ScreenBuilder {
    override fun build(): Screen {
        return Screen(
            navigationBar = navBar(),
            child = Container(
                context = ContextData(
                    id = "shirtData",
                    value = ShirtData( id = "123", price = "$23.99" )
                ),
                children = listOf(
                    outfitImage(),
                    outfitColors(),
                    outfitSize(),
                    submitButton()
                )
            )
        )
    }

    private fun navBar() : NavigationBar {
        return NavigationBar(
                title = "Outfit",
                styleId = "customNavigation",
                navigationBarItems = listOf(
                        NavigationBarItem(
                                text = "Heart Icon",
                                image = ImagePath.Local.justMobile("heart"),
                                action = Alert(
                                        title = "Clicked Heart Icon",
                                        message = "You've clicked in Heart Icon",
                                        labelOk = "Ok"
                                )
                        ),
                        NavigationBarItem(
                                text = "Bag Icon",
                                image = ImagePath.Local.justMobile("bag"),
                                action = Alert(
                                        title = "Clicked Bag Icon",
                                        message = "You've clicked in Bag Icon",
                                        labelOk = "Ok"
                                )
                        )
                )
        )
    }

    private fun outfitImage() : Widget {
        return Container(
            children = listOf(
                Image(
                    path = ImagePath.Local.justMobile("shirt"),
                    mode = ImageContentMode.CENTER
                ).applyStyle(
                    Style(cornerRadius = CornerRadius(20.0))
                ),
                ImageDetail(
                    value = expressionOf("@{shirtData.price}"),
                    image = ImageType.RED_HEART
                ).applyStyle(
                    Style(
                        padding = EdgeValue(bottom = 5.unitReal()),
                        margin = EdgeValue(horizontal = 10.unitReal()),
                        positionType = PositionType.ABSOLUTE
                    )
                )
            )
        ).applyStyle(
            Style(
                flex = Flex(justifyContent = JustifyContent.FLEX_END),
                margin = EdgeValue(left = 18.unitReal(), right = 18.unitReal()),
                size = Size(height = 65.unitReal())
            )
        )
    }

    private fun outfitColors() : Widget {
        return ColorSelector(
            colors = listOf(
                Color(hex = "#FFFFFF", onPress = SetContext("shirtData", path = "price", value = "$23.99")),
                Color(hex = "#422332", onPress = SetContext("shirtData", path = "price", value = "$25.99")),
                Color(hex = "#C0C0C0", onPress = SetContext("shirtData", path = "price", value = "$26.99")),
                Color(hex = "#DDDDDD", onPress = SetContext("shirtData", path = "price", value = "$26.99"))
            )
        ).applyStyle(
            Style(margin = EdgeValue(top = 10.unitReal()))
        )
    }

    private fun outfitSize() : Widget {
        return SizeSelector(
                sizes = listOf(
                    SizeType.XS, SizeType.S, SizeType.M, SizeType.L, SizeType.XL
                )
        ).applyStyle(
                Style(margin = EdgeValue(top = 10.unitReal()))
        )
    }

    private fun submitButton() : Widget {
        return Button(
            text = "Add to cart",
            styleId = "customButton",
            onPress = listOf(Navigate.PushView(Route.Remote("/detail")))
        )
    }
}
