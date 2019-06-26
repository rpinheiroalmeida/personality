package com.sparke.networks.personality.category;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Categories, String> {
}
