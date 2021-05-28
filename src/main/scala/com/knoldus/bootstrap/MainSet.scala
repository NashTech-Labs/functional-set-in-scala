package com.knoldus.bootstrap

object MainSet extends App {
  val s = FunctionalSet(1,2,3,4)
  println("Elements are: ")
  s foreach println

  println("Elements after adding are :")
  s + 5  foreach println

  println("Elements after union are: ")
  s+5 ++ FunctionalSet(-1,-2) + 3 foreach println

  println("Filtering elements which are even: ")
  s + 5 ++ FunctionalSet(-1, -2) + 3 flatMap (x => FunctionalSet(x, 10 * x)) filter (_ % 2 == 0) foreach println

 println(" is negation of elements are: ")
    val negative = !s // s.unary_! = all the naturals not equal to 1,2,3,4
    println(negative(2))
    println(negative(5))
 println("is even negations are: ")
    val negativeEven = negative.filter(_ % 2 == 0)
    println(negativeEven(5))

    val negativeEven5 = negativeEven + 5 // all the even numbers > 4 + 5
    println(negativeEven5(5))
  }




