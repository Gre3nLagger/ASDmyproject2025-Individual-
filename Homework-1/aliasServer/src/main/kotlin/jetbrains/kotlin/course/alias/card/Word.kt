package jetbrains.kotlin.course.alias.card

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
//A value class Word with one String word property to store a word.
value class Word(val word: String)
