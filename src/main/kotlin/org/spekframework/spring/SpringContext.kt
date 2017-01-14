package org.spekframework.spring

import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.lifecycle.LifecycleAware
import org.jetbrains.spek.api.lifecycle.LifecycleListener
import org.jetbrains.spek.api.lifecycle.TestScope
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestContextManager

/**
 * @author Ranie Jade Ramiso
 */
class SpringContext internal constructor(testContextManager: TestContextManager,
                                         val spec: Spec)
    : LifecycleListener {
    val testContext: TestContext by lazy { testContextManager.testContext }

    inline fun <reified T: Any> inject(): LifecycleAware<T> {
        return spec.memoized {
            testContext.applicationContext.getBean(T::class.java)
        }
    }

    override fun afterExecuteTest(test: TestScope) {
        testContext.markApplicationContextDirty(DirtiesContext.HierarchyMode.EXHAUSTIVE)
    }
}
