package com.newton.eventgo.extensions

import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class BigDecimalExtensionKtTest {

    @Test
    fun returnForBrazilianCoinWhenNoCents() {
        val stringReturned = BigDecimal("1").formatForBrazilianCoin()
        assertEquals(stringReturned, "R$  1,00")
    }

    @Test
    fun returnForBrazilianCoinWhenHaveCents() {
        val stringReturned = BigDecimal("1.25").formatForBrazilianCoin()
        assertEquals(stringReturned, "R$  1,25")
    }

    @Test
    fun returnForBrazilianCoinWhenDecimalCases() {
        val stringReturned = BigDecimal("10001.47").formatForBrazilianCoin()
        assertEquals(stringReturned, "R$  10.001,47")
    }
}