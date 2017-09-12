@file:Suppress("USELESS_CAST")

package com.example.liuchao.kotlin

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.liuchao.kotlin.util.ToastUtil
import com.example.liuchao.multitype.R
import java.util.*
import kotlin.reflect.KProperty

/**
 * 单例
 * Created by yangrenije on 2017/7/7.
 */

class KotlinSingleton private constructor() {

    /**
     * 懒加载
     */
    companion object {
        val instance: KotlinSingleton by lazy { KotlinSingleton() }
    }

    fun test() {
        var list = listOf(2, 3, 4)
        KotlinSingleton.instance
        println(list.filter { it -> KotlinSingleton::isOdd.call(instance, it) })
    }

    fun isOdd(n: Int) = n % 2 == 0
}

fun main(args: Array<String>) {
    var scanner: Scanner = Scanner(System.`in`)
//    println("您输入了：${scanner.nextInt()}")
    var a: Array<String> = Array(2, { "0" })
    a[0] = "1"
    for (x in a) {
        println(x)
    }
    var y = 9
    println(("in: " + (3 in 1..y + 1)))
    when {
        "1" in a -> println("a 中有1元素")
        "1" in a -> {
            println("a 中有1元素")
        }
    }
    var list = ArrayList<String>()
    list.add("3")
    list.add("5")
    list.add("0")
    list.filter { it -> it > "0" }
            .map { a -> Integer.parseInt(a) + 1 }
            .forEach { println("集合过滤遍历：" + it) }
    fun tr() = try {
        10 / 0
        "hello"
    } catch (e: RuntimeException) {
        "hi"
    }
    println(tr())

    var numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))

    println(::fieldValue.get())

    KotlinSingleton.instance.test()
    foo()
    foo1()
    foo2()
    foo3()
    foo4(9)
    testLet()
    testApply()
    testWith()
    testRun()
    foo5()
    foo6()
}

var list = listOf(1, 2, 3, 4, 5)
var strList = listOf("31231111", "312", "64", "d3234")
var mutableList = mutableListOf<String>()//
fun foo() {
    list.forEach {
        if (it == 3) return@forEach
    }
}

fun foo1() {
    list.forEach(fun(value: Int) {
        if (value == 3) return
        println("返回到标签：" + value)
    })
}

fun foo2() {
    strList.filter { it.length > 3 }.sortedBy { it.length }
            .forEach { println("filter:" + it) }
}

fun foo3() {
    println(KotlinSingleton::class.constructors)
    var n1 = 1
    var n2 = 6
    println("+ " + n1.plus(n2))
    println("- " + n1.minus(n2))
    println("* " + n1.times(n2))
    println("/ " + n1.div(n2))
    println("% " + n1.mod(n2))
    println(".. " + n1.rangeTo(n2))

    var map = mapOf(Pair("china", "beijing"))
    var writeMap = mutableMapOf<String, String>()
    writeMap.put("zhangsan", "女")
    writeMap.put("lisi", "男")
    var entries = writeMap.entries
    for (entry in entries) {
        var key = entry.key
        var value = entry.value
        println("$key -> $value")
    }

    for ((key, value) in writeMap) {
        println("$key -> $value")
    }
    var (status, result) = function()
    println("status:$status result:$result")
}

data class Result(val status: Int, val result: String)

fun function(): Result {
    return Result(0, "result")
}

/**
 * 类型判断 强转
 */
fun foo4(field: Any) {
    println(list is List)
    when (field) {
        is Int -> println(field + 1)
        is String -> println(field.length + 1)
        is Array<*> -> println(field.size + 1)
    }

    var x: Int? = field as? Int
    println("安全转换：$x")
    var demo = Demo()
    demo.p = "china"
    println("属性代理：${demo.p}")

}

var fieldValue = null
fun isOdd(num: Int) = num % 2 != 0

//=========方法代理=======//
interface Base {
    fun print()
}


class BaseImpl(val x: Int) : Base {
    val p: String by lazy {
        "World!"
    }

    override fun print() {
        print("$x")
    }
}

class Derived(base: Base) : Base by base {

    fun main() {
        val base = BaseImpl(10)
        Derived(base).print()
    }
}

//===============方法代理=========//
//=============属性代理==========//
class Demo {
    var p: String? by Delegate()
    var q: String? by Delegate()
}

class Delegate {
    private var value: String? = null
    operator fun getValue(thisRef: Any?, properties: KProperty<*>): String? {
        return value
    }

    operator fun setValue(thisRef: Any?, properties: KProperty<*>, value: String?) {
        this.value = value
    }
}

//=============属性代理==========//
//=============let,apply,run,with==========//
fun testLet() {
    "testLet".let {
        println(it.length)
    }.let {
        println(it)
    }
}

fun testApply() {
    "testApply".apply {
        println(this.length)
    }.let {
        println("testApply: $it")
    }
}

fun testWith() {
    with(mutableListOf<String>()) {
        add("china")
        add("english")
        add("american")
        add("japanese")
        println(this)
        this
    }.let {
        println("testWith: $it")
    }
}

fun testWhen() {
    when ("testWith") {
        is String -> println()
    }
}

fun testRun() {
    var r = "testRun".run {
        println(this)
        this
    }.let {
        println("testRun: $it")
        it
    }
    println("$r")
}

//=============let,apply,run,with==========//
//=============高阶函数（带lambda表达式为参数）==========start//
fun foo5() {
    with(mutableListOf("0")) {
        this.add("23421111")
        this.add("34234")
        this.add("0000089")
        this
    }.let {
        println("集合最大值：${lambdaTest(1).max(it, express)}")
    }
    var sum = fun Int.(other: Int): Int = this + other
    println("函数扩展：${2.sum(3)}")

    fun count(x: Int, y: Int, z: Int): Int = x + y + z
    println("函数表达式：${count(1, 2, 3)}")
    println("枚举：${Direction.BLUE.name}")
    println("范型约束:${max(23.123, 123.312)}")
    println("范型约束:${max(23, 123)}")

    var intArr = intArrayOf(2, 3, 5)
    println("整形数组${intArr.asList()}")
    var longArr = longArrayOf(2, 3, 5)
    println("长整形数组${longArr.asList()}")
}

var express = fun(t1: String, t2: String): Boolean {
    return t1.length < t2.length
}

enum class Direction(s: String) {
    RED("#ff0000"), BLUE("#0000ff")
}

class lambdaTest constructor(a: Int) {
    var name: String? = null

    constructor(name: String) : this(3) {
        this@lambdaTest.name = name
    }

    fun <T> max(collections: Collection<out T>, comparator: (T, T) -> Boolean): T? {
        var max: T? = null
        for (it in collections) {
            if (null == max || comparator(max!!, it)) {
                max = it
            }
        }
        return max
    }
}


//=============高阶函数（带lambda表达式为参数）==========end//
//=============范型约束==========start//
fun <T> max(n: T, m: T): T where T : Number {
    var num: Any
    when (n) {
        is Int -> {
            num = m as Int
            if (n > num) return n
            else return m
        }
        is Double -> {
            num = m as Double
            if (n > num) return n
            else return m
        }
    }
    return n
}

//=============范型约束==========end//

fun foo6() {
    strTest()
    println("带参数构造方法：${lambdaTest("zhangsan").name}")
    sealedTest()
    foo7()
    foo8()
}

fun strTest() {
    val text = """
    |Tell me and I forget.
    |Teach me and I remember.
|Involve me and I learn.
|(Benjamin Franklin)"""".trimMargin()
    println("整行字符串：$text")
    println("""${'$'}99999""")
}

//==========密封类==============start／/
sealed class Expr {
    open class Const(val number: Double) : Expr()
    open class Sum(val e1: Expr, val e2: Expr) : Expr()
    object NotANumber : Expr()
}

/**
 * 密封类子类 可以扩展（默认所有类都是final，必须在子类上加open）
 */
class Special(number: Int) : Expr.Const(number.toDouble())

fun eval(expr: Expr): Double = when (expr) {
    is Expr.Const -> expr.number
    is Expr.Sum -> eval(expr.e1) + eval(expr.e2)
    Expr.NotANumber -> Double.NaN
}

fun sealedTest() {
    var number = eval(Expr.Sum(Special(1), Special(7)))
    println("""密封类:$number""")
}

//==========密封类==============end／/
//==========对象表达式==============start／/
interface Object {
    fun print()
}

fun foo7() {
    var o = object : Object {
        var code: Int? = null
        override fun print() {
            println("对象表达式 ${this.javaClass}")
        }
    }
    with(o) {
        print()
        this
    }.let {
        println("对象表达式:$it")
    }
}

//==========对象表达式==============start／/
//==========对象申明==============end／/
object Student : Any() {
    //通过object对象申明实现单例模式
    fun provideObject(): KotlinSingleton {
        return KotlinSingleton.instance;
    }

    var name: String? = null
    var age: Int? = null
}

fun foo8() {
    Student.name = "zhangsan"
    Student.age = 12
    println("Student:姓名： ${Student.name} , 年龄： ${Student.age}")
    foo9()
}
//==========对象申明==============end／/
//==========伴随对象==============start／/

class CompanionObject {
    companion object : Object {
        override fun print() {
        }

        fun getInstance(): CompanionObject {
            return Holder.INSTANCE
        }
    }

    object Holder {
        @JvmStatic val INSTANCE = CompanionObject()
    }
}

fun foo9() {
    ::CompanionObject
    println("伴随对象：${CompanionObject::class.simpleName}")
    foo10()
}
//==========伴随对象==============end//
//==========空==============start／/

class CustomLinearLayout : LinearLayout {
    constructor(ctx: Context) : this(ctx, null)
    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, -1)
    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr)

    fun init() {
        this?.setBackgroundColor(Color.RED)
        var parent = parent
        parent!!.requestLayout()
    }
}

fun foo10() {
    fun CustomLinearLayout.show() {
        if (this.visibility == View.VISIBLE) return@show
        else this.visibility = View.VISIBLE
    }
//    CustomLinearLayout(Activity())?.show()
    TestAnnotation().foo()
}

//==========空================================end//
//===========跳转和返回========================start//
fun loop() {
    text@ for (x in 1..100 step 2) {
        println("${x}")
        for (y in 1..100 step 3) {
            println("${y}")
            continue@text   //直接跳转到外层循环
        }
    }
}
//===========跳转和返回========================end//
//==========注解==============================start／/

annotation class Ann(val desc: String)
annotation class Inject(val ids: IntArray)

data class Bean constructor(var id: Int) {

    @Inject(ids = intArrayOf(R.id.test, R.id.text))
    fun onClick(v: View?) {

        when (v?.id) {
            R.id.test -> {
                ToastUtil.instance.toast(null, "你点击了test", Toast.LENGTH_SHORT)
            }
            R.id.text -> {
                ToastUtil.instance.toast(null, "你点击了text", Toast.LENGTH_SHORT)
            }
        }
    }
}

@Ann("类注解测试")
class TestAnnotation {
    @Ann("属性注解") var annotation: Int? = 0
        @Ann("属性访问者") set(value) {
            println("set方法2：${value?.plus(1)}")
            field = value
        }
        @Ann("Get方法") get() {
            return field!!.times(2)
        }

    @Ann("方法上的注解")
    fun foo() {
        var classAnnotation: Ann? = TestAnnotation::class?.annotations[0] as Ann
        var methodAnnotation: Ann? = TestAnnotation::foo?.annotations[0] as Ann
        var fieldAnnotation: Ann? = TestAnnotation::annotation?.annotations[0] as Ann
        var setAnnotation: Ann = TestAnnotation::annotation.setter.annotations[0] as Ann
        var getAnnotation: Ann = TestAnnotation::annotation.getter.annotations[0] as Ann
        println("获取类注解上的值：${classAnnotation?.desc}")
        println("获取方法注解上的值：${methodAnnotation?.desc}")
        println("获取属性注解上的值：${fieldAnnotation?.desc}")
        println("获取属性访问方法上的注解值：${setAnnotation?.desc}")
        println("获取属性访问方法上的注解值：${getAnnotation?.desc}")
        annotation = 9
        println("set方法：${annotation}")
        foo11()
    }
}

//==========注解============================end//
//==========中缀符号========================start//
infix fun String.append(other: String): String {
    return this.plus(other)
}

fun Int.append(other: Int): Int {
    return this.plus(other)
}

fun foo11() {
    println("""中缀符号：${"hello".append(" world!")}""")
    println("两个整数的和：${2.append(8)}")
    foo12()
}

//==========中缀符号============================end／/
fun foo12() {
    var list = mutableListOf<Int>()
    for (x in 100..999) {
        for (y in 100..999) {
            with(x.times(y).toString()) {
                if (this == this.reversed() && !list.contains(this.toInt())) {
//                    println("x=${x}, y=${y}, times = ${this}")
                    list.add(this.toInt())
                }
            }
        }
    }
    foo13()
}

//==========Range================================start//
fun foo13() {
    var str: String = "你"
    if (str in "我".."她") println(str)
    println("try catch:${foo14()}")
    foo15()
}

//==========Range=================================end//
//==========动态类型=================================start//
//    val dyn: dynamic = null
fun foo14(): Int {
    var a = 1
    try {
        ++a
        return a
    } catch(e: RuntimeException) {

        return a
    } finally {
        ++a
        println("finally-a:${a}")
    }
}

//==========动态类型=================================end//
//==========Ranges,Progression=================================start//
fun foo15() {
    for (x in 1..4 step 2) {
        println("range:${x}")
    }
    for (x in (1..4).reversed() step 2) {
        println("reversed:${x}")
    }

    for (y in 4 downTo 2) {
        println("downTo:${y}")
    }
    for (y in 4 downTo 0 step 2) {
        println("downTo-step:${y}")
    }
    LongProgression.fromClosedRange(1, 10, 3).forEach {
        println("LongProgression:${it}")
    }
    IntProgression.fromClosedRange(1, 6, 2).forEach {
        println("IntProgression:${it}")
    }
    foo16()
}

//==========Ranges,Progression=================================end//
data class Entity(var name: String) {
    var age: Int? = null
        set(value) {
            field = value
        }
        get() {
            return field!!.times(10)
        }
}

//==============suspend=========//挂起函数
suspend fun doSomething(): String {

    return "suspend"
}

suspend fun doOther(): Int {
    return 100
}

fun <T> async(block: suspend () -> T) {
    var result = block.to("suspend")
    println("suspend:" + result)
}

fun foo16() {
    async { doOther() }
}
//==============suspend=========//挂起函数


