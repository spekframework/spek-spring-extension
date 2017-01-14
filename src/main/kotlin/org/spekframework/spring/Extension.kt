package org.spekframework.spring

import org.jetbrains.spek.api.dsl.Spec
import org.springframework.test.context.TestContextManager
import kotlin.reflect.KClass


fun Spec.createContext(spec: KClass<*>): SpringContext {
    return SpringContext(TestContextManager(spec.java), this@createContext).apply {
        registerListener(this)
    }
}
