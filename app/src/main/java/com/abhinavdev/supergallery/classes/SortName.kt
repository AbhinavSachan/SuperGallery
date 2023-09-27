package com.abhinavdev.supergallery.classes

sealed class SortName {
    class DISPLAY_NAME(val name: String) : SortName()
}
