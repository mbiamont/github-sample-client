package com.mbiamont.github.navigation

interface IRouter {

    fun parse(destination: String): Route
}