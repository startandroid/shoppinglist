package ru.startandroid.core.common.data.mapping

interface Mapping<I, O> {
    fun map(input: I): O
}