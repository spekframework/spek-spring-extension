package org.spekframework.spring

import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.lifecycle.LifecycleListener
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestContextManager

/**
 * @author Fitz <wndtn853@gmail.com>
 */
class SpringContext internal constructor(testContextManager: TestContextManager, val spec: Root) : LifecycleListener {
    val testContext: TestContext by lazy { testContextManager.testContext }

    inline fun <reified T : Any> inject(): T {
        val bean by spec.memoized { testContext.applicationContext.getBean(T::class.java) }
        return bean
    }

    inline fun <reified T : Any> inject(beanName: String): T {
        val bean by spec.memoized { testContext.applicationContext.getBean(beanName, T::class.java) }
        return bean
    }
}
