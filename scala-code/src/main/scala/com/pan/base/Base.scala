package com.pan.base

import scala.beans.BeanProperty

/**
  * Created by pan on 2017/8/12.
  * 如果主要构造函数参数 没有修饰符  如果内部方法使用到 则会生成private[this] 修饰符 否则不会生成field
  */
object MyEnumetation extends  Enumeration {
  val Red,Green,Blue = Value
}


class Person( name: String, age: String) {

  //BeanProperty var 会生成set get方法 外部方法可以访问 val只会生成 get方法
  @BeanProperty var sex: String = _
  @BeanProperty var height: String = _
  val heigt: String = ""
  val enum: MyEnumetation.Value = MyEnumetation.Red



  def test(): Unit = {


  }

}




object Base {

  def add(a: Int, b: Int = 3): Int = {
    return a + b;
  }

  def main(args: Array[String]): Unit = {

    var p = new Person("q", "a")


    /*
   Scala没有for循环，只能使用while替代for循环，或者使用简易版的for语句
    ·简易版for语句：var n = 10; for(i <- 1 to n) println(i)
    ·或者使用until，表式不达到上限：for(i <- 1 until n) println(i) util 表示不包含 n
     */
    //  for (i <- 1 to 10) println(i)
    //  for (i <-1 until 10) println(i)

    /*
    while循环 跟java一样
     */
    /*  var n = 10
      while(n > 0) {
        println(n)
        n -= 1
      }*/
    /*
    双重for循环
     */
    /* for (i <- 1 to 9) {
       for (j <- 1 to 9) {
         print(i * j + " ")
         if (j == 9 ) println()
       }
     }
 */
    /*
    生成集合
     */
    /*    val a: immutable.IndexedSeq[Int] = for (i <- 1 to 10) yield i
        println(a)*/

    /*  val res = add(1)
       println(res)*/
    //多参函数
    /*def sum2(nums: Int*): Int = {
      //if (nums.length == 0) 0
      //else nums.head + sum2(nums.tail: _*)
      println(nums.head)
      println(nums.tails.next())
      0
    }*/
    //sum2(1 to 5:_*)
    //数组
    /* val array = Array[Int](1,2,3)
     array(0) = 1
     array(1) = 2
     val arrayBuffer = ArrayBuffer[Int]()
     arrayBuffer +=1
     arrayBuffer ++=array
     array.filter(x => x%2==0).map(x => 'A').foreach(x => println(x))
 */
    //Map数据结构 immute包下的 mute包下的区别 是一个Map中内容可变 另一个不变
    // val ages = Map("Leo" -> 30, "Jen" -> 25, "Jack" -> 23)
    //  val ages2 = ages + ("Mike" -> 36, "Tom" -> 40)
    //  val a: Map[Int, String] = for ((key, value) <- ages) yield (value, key)
    // println(a.get(30).toString)

    //ListBuffer
   /* val l = new scala.collection.mutable.ListBuffer[String]
    l += "aaa"
    l += "bb"
    l += "cc"
    l.foreach(println(_))*/


    val v:String =null
    println(v.charAt(1))


  }
}
