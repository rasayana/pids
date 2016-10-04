package pids.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pids.data.PerimeterData;

@RepositoryRestResource(collectionResourceRel = "map", path = "map")
public interface DataRepository extends MongoRepository<PerimeterData, String> {}