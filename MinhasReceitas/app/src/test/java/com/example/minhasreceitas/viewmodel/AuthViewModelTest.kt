package com.example.minhasreceitas.viewmodel

import com.example.minhasreceitas.viewmodel.AuthViewModel.Companion.validadeRegistration
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class AuthViewModelTest {

    @Test
    fun `empty username returns false`() {
        val result =  validadeRegistration(
        //Put here values that should make this test work
            "",
            "teste123",
            "teste123")
        assertThat(result).isFalse()
    }
    @Test
    fun `valid username and password returns true`() {
        val result = validadeRegistration(
            "name",
            "teste123",
            "teste123")
        assertThat(result).isTrue()
    }
    @Test
    fun `password matches return true`() {
        val result = validadeRegistration(
            "name",
        "teste123",
        "teste123")
        assertThat(result).isTrue()
    }

    @Test
    fun `username is bigger then 4 chars returns true`() {
        val result = validadeRegistration(
            "name",
            "teste123",
            "teste123")
        assertThat(result).isTrue()
    }
    @Test
    fun `password is bigger than 4 letters returns true`() {
        val result = validadeRegistration(
            "name",
            "teste123",
            "teste123")
        assertThat(result).isTrue()
    }
    @Test
    fun `password is empty returns false`() {
        val result = validadeRegistration(
            "name",
            "",
            "")
        assertThat(result).isFalse()
    }

}
