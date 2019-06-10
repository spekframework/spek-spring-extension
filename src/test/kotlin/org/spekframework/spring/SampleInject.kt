package org.spekframework.spring

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import kotlin.test.assertTrue

class Foo {
    fun bar() {
        println("Hello!")
    }
}

@Configuration
open class MyConfiguration {
    @Bean("foo")
    open fun foo() = Foo()
}

@ContextConfiguration(classes = [MyConfiguration::class])
object SampleInjectSpec: Spek({
    val context = createContext(SampleInjectSpec::class)

    val foo = context.inject<Foo>()

    describe("blah blah") {
        it("should be return true") {
            foo.bar()
            assertTrue(true)
        }
    }
})
