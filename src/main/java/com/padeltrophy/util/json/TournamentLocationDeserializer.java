package com.padeltrophy.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.padeltrophy.entity.Club;
import com.padeltrophy.util.log.Tracer;

import java.io.IOException;

/**
 * Created by JLRB002 on 03/11/2015.
 */
public class TournamentLocationDeserializer extends JsonDeserializer<Club> {

    //@Autowired
    //private clubDAO ClubDAO;


    private Tracer tracer = new Tracer(TournamentOrganizerDeserializer.class.getName());

    @Override
    public Club deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        try {
            String locationId = jsonParser.getText();
            //Club club = clubDAO.getPlayerById(locationId);
            //return club;
            return null;
        }catch (Exception e){
            tracer.trace("deserialize() : error:> "+e.getStackTrace().toString());
            return null;
        }
    }
}
