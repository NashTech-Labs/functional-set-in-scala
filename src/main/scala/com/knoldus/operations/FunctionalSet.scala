package com.knoldus.operations

trait FunctionalSet[A] extends (A => Boolean) {
  /**
   * Implement a functional set operation
   */
  def apply(elem: A): Boolean =
    contains(elem)

  def contains(elem: A): Boolean

  def +(elem: A): FunctionalSet[A] //add

  def ++(anotherSet: FunctionalSet[A]): FunctionalSet[A] // union

  def map[B](f: A => B): FunctionalSet[B]

  def flatMap[B](f: A => FunctionalSet[B]): FunctionalSet[B]

  def filter(predicate: A => Boolean): FunctionalSet[A]

  def foreach(f: A => Unit): Unit

  def -(elem: A): FunctionalSet[A] // removing

  def --(anotherSet: FunctionalSet[A]): FunctionalSet[A] // difference

  def &(anotherSet: FunctionalSet[A]): FunctionalSet[A] // intersection

  def unary_! : FunctionalSet[A] //unary_! = NEGATION of a set
}
