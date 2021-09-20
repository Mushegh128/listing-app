package com.example.listingapp.servicies;

import com.example.listingapp.model.Category;
import com.example.listingapp.model.Listing;
import com.example.listingapp.model.User;
import com.example.listingapp.repositories.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListingService {
    private final ListingRepository listingRepository;

    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    public List<Listing> findByUser(User user) {
        List<Listing> allByUser = listingRepository.findAllByUser(user);
        if (allByUser.isEmpty()) {
            return null;
        }
        return allByUser;
    }

    public List<Listing> findAllByCategory(Category category) {
        return listingRepository.findAllByCategory(category);
    }

    public Optional<Listing> findById(int id) {
        return listingRepository.findById(id);
    }

    public Listing save(Listing listing) {
        return listingRepository.save(listing);
    }

    public boolean update(Listing listing) {
        Optional<Listing> byId = listingRepository.findById(listing.getId());
        if (byId.isEmpty()) {
            return false;
        }
        listingRepository.save(listing);
        return true;
    }

    public boolean deleteById(int id) {
        Optional<Listing> byId = listingRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }
        listingRepository.deleteById(id);
        return true;
    }
}
