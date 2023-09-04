package com.josearroyo.comparatexto

import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testCompareButton() {
        // Ingresa texto en el primer EditText
        onView(withId(R.id.primerTexto)).perform(typeText("Texto1"))

        // Ingresa texto en el segundo EditText
        onView(withId(R.id.segundoTexto)).perform(typeText("Texto1"))

        // Presiona el botón "Comparar"
        onView(withId(R.id.btnPulsado)).perform(click())

        // Verifica que el TextView muestre el resultado esperado
        onView(withId(R.id.resultadoComp)).check { view, _ ->
            val textView = view as AppCompatTextView
            val expectedText = "Las cadenas son iguales"
            val actualText = textView.text.toString()
            if (actualText != expectedText) {
                throw AssertionError("Texto esperado: $expectedText, Texto actual: $actualText")
            }
        }
    }
}
