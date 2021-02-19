package com.example.hotelservice;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHotelBuchung.Builder.class)
public abstract class HotelBuchung {
    public abstract String getName();
}