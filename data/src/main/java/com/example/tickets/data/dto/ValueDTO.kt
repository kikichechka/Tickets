package com.example.tickets.data.dto

import com.example.tickets.domain.models.Value

class ValueDTO(
    val value: Int
)

fun ValueDTO.toModel() : Value {
    return Value(value)
}