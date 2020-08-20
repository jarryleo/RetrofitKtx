package cn.leo.retrofitktx

import cn.leo.retrofit_ktx.utils.io
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)
        io {
            flow<Int> {
                for (i in 1..10) {
                    emit(i)
                }
            }.onEach {
                delay(200)
            }.onCompletion {
                println("Done!")
            }.catch {
                println("catch exception!")
            }.collect {
                println(it)
            }
        }
        Thread.sleep(3000)
    }
}
