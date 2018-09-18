package com.pan.base

import scala.collection.mutable

object Execise1 {

  def main(args: Array[String]): Unit = {

    /* val command = ArrayBuffer("1", "2", "3")
     val pb = new ProcessBuilder(command)*/


    //数组
    /*val array = Array(1,2,3,4,5)

    for (i <- 0 until array.length){
      println(array(i))
    }

    for (a <- array ) {
      println(a)
    }
*/

    //Map

    val map = new mutable.HashMap[String, Double]()
    //val map2 = for ((k, v) <- map) yield (k, v * 0.9)

    //println(map2.get("1"))

    val keys = Array("1", "3")

    for (key <- keys) {
      //if (map.contains(key)) {
      map(key) = map.getOrElse(key, 0.0) + 1.0
      /* } else {
         map.put(key, 1.0)
       }*/
    }

    keys.apply(1)
    for (m <- map) {
      println(m)

    }
  }


}
