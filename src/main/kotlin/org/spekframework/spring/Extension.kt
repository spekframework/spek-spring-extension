package org.spekframework.spring

import org.spekframework.spek2.dsl.Root
import org.springframework.test.context.TestContextManager
import kotlin.reflect.KClass

/**
 * @author Fitz <wndtn853@gmail.com>
 */
fun Root.createContext(spec: KClass<*>): SpringContext {
    return SpringContext(TestContextManager(spec.java), this@createContext).apply {
        registerListener(this)
    }
}
