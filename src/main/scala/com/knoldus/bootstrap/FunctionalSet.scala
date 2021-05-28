package com.knoldus.bootstrap

import com.knoldus.operations.FunctionalSet
import com.knoldus.service.EmptySet

import scala.annotation.tailrec

object FunctionalSet {

  def apply[A](values: A*): FunctionalSet[A] = {
    @tailrec
    def buildSet(valSeq: Seq[A], acc: FunctionalSet[A]): FunctionalSet[A] =
      if (valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)

    buildSet(values.toSeq, new EmptySet[A])
  }
}