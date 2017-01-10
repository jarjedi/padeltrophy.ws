package com.padeltrophy.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.padeltrophy.entity.Player;
//import com.padeltrophy.service.PlayerService;
import com.padeltrophy.util.log.Tracer;

import java.io.IOException;

/**
 * Created by JLRB002 on 03/11/2015.
 */
public class TournamentOrganizerDeserializer extends JsonDeserializer<Player> {

    private Tracer tracer = new Tracer(TournamentOrganizerDeserializer.class.getName());

    @Override
    public Player deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        /*try {
            PlayerService playerUtil = ApplicationContextHolder.getContext().getBean(PlayerService.class);
            String organizerId = jsonParser.getText();
            tracer.trace("deserialize() : organizerId: "+organizerId);
            Player organizer=null;
            if(playerUtil!=null) {
                tracer.trace("deserialize() : getPlayerById: "+organizerId);
                organizer = playerUtil.getSimplePlayerById(organizerId);
            }else{
                tracer.trace("deserialize() : playerUtil null!");
            }
            return organizer;
        }catch (Exception e){
            tracer.trace("deserialize() : error:> ",Tracer.LEVEL_ERROR,e);
            return null;
        }*/
            return null;
    }
}
