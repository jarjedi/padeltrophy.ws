package com.padeltrophy.repository;

import com.padeltrophy.entity.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


/**
 * Created by Juan Alberto on 08/01/2017.
 */
@CrossOrigin(origins = "http://localhost:8000")
@RepositoryRestResource(collectionResourceRel = "tournament", path = "tournament")
public interface TournamentRestRepository extends MongoRepository<Tournament, String> {

    List<Tournament> findByName(@Param("name") String name);

    List<Tournament> findByProvince(@Param("province") String province);

}