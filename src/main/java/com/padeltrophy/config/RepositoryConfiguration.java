package com.padeltrophy.config;

import com.padeltrophy.entity.Club;
import com.padeltrophy.entity.Player;
import com.padeltrophy.entity.Tournament;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


/**
 * Created by Juan Alberto on 13/03/2017.
 *
 * We need to create this class to expose entities ids
 */
@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Player.class);
        config.exposeIdsFor(Tournament.class);
        config.exposeIdsFor(Club.class);
    }
}
