package com.knoldus.service

import com.knoldus.operations.FunctionalSet

class EmptySet[A] extends FunctionalSet[A] {
  def contains(elem: A): Boolean = false

  def +(elem: A): FunctionalSet[A] = new NonEmptySet[A](elem, this)

  def ++(anotherSet: FunctionalSet[A]): FunctionalSet[A] = anotherSet

  def map[B](f: A => B): FunctionalSet[B] = new EmptySet[B]

  def flatMap[B](f: A => FunctionalSet[B]): FunctionalSet[B] = new EmptySet[B]

  def filter(predicate: A => Boolean): FunctionalSet[A] = this

  def foreach(f: A => Unit): Unit = ()

  def -(elem: A): FunctionalSet[A] = this

  def --(anotherSet: FunctionalSet[A]): FunctionalSet[A] = this

  def &(anotherSet: FunctionalSet[A]): FunctionalSet[A] = this

  def unary_! : FunctionalSet[A] = new PropertyBasedSet[A](_ => true)
}