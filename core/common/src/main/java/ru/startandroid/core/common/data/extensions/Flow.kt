package ru.startandroid.core.common.data.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.startandroid.core.common.data.mapping.Mapping

fun <I, O> Flow<List<I>>.mapListWith(mapping: Mapping<I, O>): Flow<List<O>> =
    map { list ->
        list.map { item ->
            mapping.map(item)
        }
    }