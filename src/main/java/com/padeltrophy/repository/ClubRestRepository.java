package com.padeltrophy.repository;

import com.padeltrophy.entity.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Juan Alberto on 10/01/2017.
 */
@RepositoryRestResource(collectionResourceRel = "club", path = "club")
public interface ClubRestRepository extends MongoRepository<Club, String> {

    List<Club> findByName(@Param("name") String name);

    @Query(value="{ 'location.country' : ?0, 'location.province' : ?1 }")
    List<Club> findByCountryProvince(@Param("country") String country, @Param("province") String province);

}
