package com.cursosant.calculatorbase

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsTest {

    @Mock
    lateinit var operations : Operations
    @Mock
    lateinit var listener : OnResolveListener
    var calculatorUtils: CalculatorUtils? = null


    @Before
    fun setup() {
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @After
    fun tearDown() {
        calculatorUtils = null
    }

    @Test
    fun calculator_callCheckOrResolve_notReturn() {
        val operation = "-5x2.5"
        val isFromResolve = true

        calculatorUtils?.checkOrResolve(operation, isFromResolve)
        verify(operations).tryResolve(operation, isFromResolve, listener)
    }

    @Test
    fun calculator_callAddOperator_validSub_noReturn() {
        val operator = "-"
        val operation = "4+"
        var isCorrect = false

        calculatorUtils?.addOperator(operator, operation) {
            isCorrect = true
        }

        assertTrue(isCorrect)
    }

    @Test
    fun calculator_callAddOperator_invalidSub_noReturn() {
        val operator = "-"
        val operation = "4-"
        var isCorrect = false

        calculatorUtils?.addOperator(operator, operation) {
            isCorrect = true
        }

        assertFalse(isCorrect)
    }

}