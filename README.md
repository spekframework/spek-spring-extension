# Spring Extension for Spek2
This is a proof of concept for writing spring integration tests in Spek2

## Limitations
- Currently, only injecting beans is supported.
    ```kotlin
    @ContextConfiguration(classes = [MyConfiguration::class])
    object MySpec: Spek({
        val context = createContext(MySpec::class)
        val foo = context.inject<Foo>()
        // val foo: Foo by context.inject()
        
        describe("blah blah") {
            it("should be blah") {
                foo.doSomething()
            }
        }
    })
    ```

## Using spring boot
```kotlin
@RunWith(SpringRunner::class)
@SpringBootTest
object MySpec: Spek({
    val context = createContext(MySpec::class)
    val foo = context.inject<Foo>()
    
    describe("blah blah") {
        it("should be blah") {
            foo.doSomething()
        }
    }
})
```


## Issues
Spring's `TestContext` framework makes assumptions on how tests are structured which is 
incompatible with Spek, which means we can't use `TestContextManager` (we can but it will be very hackish).
