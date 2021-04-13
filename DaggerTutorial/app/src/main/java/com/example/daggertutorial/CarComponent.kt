package com.example.daggertutorial

import dagger.Component

@Component
interface CarComponent {
    fun getCar() : Car
}