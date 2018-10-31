package com.bourne.caesar.peptribeconcept.Retrofit.JsonDesereilizers;

import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.NotificationsHashed;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class NotificationDesereializer implements JsonDeserializer<NotificationsHashed> {

    @Override
    public NotificationsHashed deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
