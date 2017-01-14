package org.spekframework.spring

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration

class Foo
class Bar(val foo: Foo)

@Configuration
class MyConfiguration {

    @Bean("foo")
    fun foo() = Foo()

    @Bean
    fun bar(foo: Foo): Bar {
        return Bar(foo)
    }
}

@ContextConfiguration(classes = arrayOf(MyConfiguration::class))
object SampleInjectSpec: Spek({
    val context = createContext(SampleInjectSpec::class)

    val foo = context.inject<Foo>()
    val bar = context.inject<Bar>()

    it("should be something") {
        println(foo())
        println(bar())
    }

    it("should be another") {
        println(foo())
        println(bar())
    }
})
