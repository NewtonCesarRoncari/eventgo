package com.newton.eventgo

import com.newton.eventgo.extensions.BigDecimalExtensionKtTest
import com.newton.eventgo.extensions.DateExtensionKtTest
import com.newton.eventgo.view.recyclerview.adapter.EventAdapterTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    BigDecimalExtensionKtTest::class,
    DateExtensionKtTest::class,
    EventAdapterTest::class
)
class UnitTestSuite