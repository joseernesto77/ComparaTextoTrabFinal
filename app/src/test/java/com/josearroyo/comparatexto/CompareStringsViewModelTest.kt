package com.josearroyo.comparatexto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CompareStringsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CompareStringsViewModel
    private lateinit var comparisonResultObserver: Observer<String>
    private var comparisonResultValue: String? = null

    @Before
    fun setUp() {
        viewModel = CompareStringsViewModel()
        comparisonResultObserver = Observer { value ->
            comparisonResultValue = value
        }
        viewModel.comparisonResult.observeForever(comparisonResultObserver)
    }

    @Test
    fun testCompareStringsEqual() {
        val text1 = "Hello"
        val text2 = "Hello"
        viewModel.compareStrings(text1, text2)
        assertEquals("Las cadenas son iguales", comparisonResultValue)
    }

    @Test
    fun testCompareStringsNotEqual() {
        val text1 = "Hello"
        val text2 = "World"
        viewModel.compareStrings(text1, text2)
        assertEquals("Las cadenas son diferentes", comparisonResultValue)
    }
}
