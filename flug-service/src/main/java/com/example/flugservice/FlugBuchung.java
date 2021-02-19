package com.example.flugservice;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(builder = ImmutableFlugBuchung.Builder.class)
public abstract class FlugBuchung {
    public abstract String getFlugnummer();
}
