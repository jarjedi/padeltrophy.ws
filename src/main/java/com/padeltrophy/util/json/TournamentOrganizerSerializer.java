package com.padeltrophy.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import com.padeltrophy.entity.Player;
import com.padeltrophy.util.log.Tracer;

import java.io.IOException;

/**
 * Created by JLRB002 on 05/11/2015.
 */
public class TournamentOrganizerSerializer extends JsonSerializer<Player> {

    private Tracer tracer = new Tracer(TournamentOrganizerSerializer.class.getName());

    @Override
    public void serialize(Player o, JsonGenerator j, SerializerProvider s) throws IOException, JsonProcessingException {
        try {
            if (o == null) {
                tracer.trace("serialize() : o is null");
                j.writeNull();
            } else {
                tracer.trace("serialize() : o is not null");
                JacksonTransformer jsonTransformer = new JacksonTransformer();
                j.writeString(o.getId().toString());
            }
        }catch (Exception e){
            tracer.trace("serialize() : error ",Tracer.LEVEL_ERROR, e);
        }
    }

}