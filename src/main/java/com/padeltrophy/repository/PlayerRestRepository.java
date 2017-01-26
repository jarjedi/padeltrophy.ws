package com.padeltrophy.repository;

import com.padeltrophy.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Juan Alberto on 09/01/2017.
 */
@RepositoryRestResource(collectionResourceRel = "player", path = "player")
public interface PlayerRestRepository extends MongoRepository<Player, String> {

    List<Player> findByEmail(@Param("email") String email);

}
