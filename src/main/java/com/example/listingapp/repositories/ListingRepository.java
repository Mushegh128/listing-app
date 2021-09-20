package com.example.listingapp.repositories;

import com.example.listingapp.model.Category;
import com.example.listingapp.model.Listing;
import com.example.listingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Integer> {

    List<Listing> findAllByUser(User user);

    List<Listing> findAllByCategory(Category category);

}
