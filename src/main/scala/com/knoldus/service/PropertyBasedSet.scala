package com.knoldus.service

import com.knoldus.operations.FunctionalSet

// all elements of type A which satisfy a property
// { x in A | property(x) }
class PropertyBasedSet[A](property: A => Boolean) extends FunctionalSet[A] {
  def contains(elem: A): Boolean = property(elem)

  // { x in A | property(x) } + element = { x in A | property(x) || x == element }
  def +(elem: A): FunctionalSet[A] =
    new PropertyBasedSet[A](x => property(x) || x == elem)

  // { x in A | property(x) } ++ set => { x in A | property(x) || set contains x }
  def ++(anotherSet: FunctionalSet[A]): FunctionalSet[A] =
    new PropertyBasedSet[A](x => property(x) || anotherSet(x))

  // all integers => (_ % 3) => [0 1 2]
  def map[B](f: A => B): FunctionalSet[B] = politelyFail

  def flatMap[B](f: A => FunctionalSet[B]): FunctionalSet[B] = politelyFail

  def foreach(f: A => Unit): Unit = politelyFail

  def filter(predicate: A => Boolean): FunctionalSet[A] = new PropertyBasedSet[A](x => property(x) && predicate(x))

  def -(elem: A): FunctionalSet[A] = filter(x => x != elem)

  def --(anotherSet: FunctionalSet[A]): FunctionalSet[A] = filter(!anotherSet)

  def &(anotherSet: FunctionalSet[A]): FunctionalSet[A] = filter(anotherSet)

  def unary_! : FunctionalSet[A] = new PropertyBasedSet[A](x => !property(x))

  def politelyFail = throw new IllegalArgumentException("Really deep rabbit hole!")
}
