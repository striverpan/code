package com.pan.study

import scala.collection.mutable.ArrayBuffer

/**
  * @author: panxiwen
  * @Description: scala基础
  * @date 2018/9/18
  */

class Study1 {

  def test(name:String): Unit ={
    println(name)
  }


}
object Study1 {

  def main(args: Array[String]): Unit = {


    val intValueArr = Array("132",45,33)
    var v = ArrayBuffer

    val list = List(1,2,3,4)

    println(list(2))
    list.filter((x) => if(x==1) true else false)

    intValueArr.map(x=>2).reduce((a,b)=>a+b)
  }



}
