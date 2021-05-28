package com.knoldus.service

import com.knoldus.operations.FunctionalSet

class NonEmptySet[A](head: A, tail: FunctionalSet[A]) extends FunctionalSet[A] {
  def contains(elem: A): Boolean =
    elem == head || tail.contains(elem)

  def +(elem: A): FunctionalSet[A] =
    if (this contains elem) this
    else new NonEmptySet[A](elem, this)

  /**
    [1 2 3] ++ [4 5] =
    [2 3] ++ [4 5] + 1 =
    [3] ++ [4 5] + 1 + 2 =
    [] ++ [4 5] + 1 + 2 + 3
    [4 5] + 1 + 2 + 3 = [4 5 1 2 3]
   */
  def ++(anotherSet: FunctionalSet[A]): FunctionalSet[A] =
    tail ++ anotherSet + head

  def map[B](f: A => B): FunctionalSet[B] = (tail map f) + f(head)

  def flatMap[B](f: A => FunctionalSet[B]): FunctionalSet[B] = (tail flatMap f) ++ f(head)

  def filter(predicate: A => Boolean): FunctionalSet[A] = {
    val filteredTail = tail filter predicate
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }

  def -(elem: A): FunctionalSet[A] =
    if (head == elem) tail
    else tail - elem + head

  def --(anotherSet: FunctionalSet[A]): FunctionalSet[A] = filter(!anotherSet)

  def &(anotherSet: FunctionalSet[A]): FunctionalSet[A] = filter(anotherSet) // intersection = filtering!

  def unary_! : FunctionalSet[A] = new PropertyBasedSet[A](x => !this.contains(x))
}
