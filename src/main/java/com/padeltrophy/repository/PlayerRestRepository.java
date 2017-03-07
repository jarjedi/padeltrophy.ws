package com.padeltrophy.repository;

import com.padeltrophy.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * Created by Juan Alberto on 09/01/2017.
 */
@CrossOrigin(origins = "http://localhost:8000")
@RepositoryRestResource(collectionResourceRel = "player", path = "player")
public interface PlayerRestRepository extends MongoRepository<Player, String> {

    List<Player> findByEmail(@Param("email") String email);

}
