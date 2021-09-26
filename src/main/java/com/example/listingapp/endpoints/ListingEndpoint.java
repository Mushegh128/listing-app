package com.example.listingapp.endpoints;

import com.example.listingapp.model.Category;
import com.example.listingapp.model.Listing;
import com.example.listingapp.model.User;
import com.example.listingapp.servicies.CategoryService;
import com.example.listingapp.servicies.ListingService;
import com.example.listingapp.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ListingEndpoint {

    private final ListingService listingService;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping("/listings")
    public ResponseEntity<List<Listing>> listings() {
        List<Listing> allListings = listingService.findAll();
        return ResponseEntity.ok(allListings);
    }

    @GetMapping("/listings/byUser/{email}")
    public ResponseEntity<List<Listing>> byUserEmail(@PathVariable("email") String email) {
        User userByEmail = userService.findByEmail(email);
        if (userByEmail == null) {
            return ResponseEntity.notFound().build();
        }
        List<Listing> byUser = listingService.findByUser(userByEmail);
        return ResponseEntity.ok(byUser);
    }

    @GetMapping("/listings/byCategory/{categoryId}")
    public ResponseEntity<List<Listing>> byCategoryId(@PathVariable("categoryId") int id) {
        Optional<Category> byId = categoryService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Listing> allByCategory = listingService.findAllByCategory(byId.get());
        return ResponseEntity.ok(allByCategory);
    }

    @GetMapping("/listings/{id}")
    public ResponseEntity<Listing> listingById(@PathVariable("id") int id) {
        Optional<Listing> byId = listingService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/listings")
    public ResponseEntity<Listing> addListing(@RequestBody Listing listing) {
        return ResponseEntity.ok(listingService.save(listing));
    }

    @PutMapping("/listings")
    public ResponseEntity<Listing> updateListing(@RequestBody Listing listing) {
        if (listingService.update(listing)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listing);
    }

    @DeleteMapping("/listings/{id}")
    public ResponseEntity deleteListing(@PathVariable("id") int id) {
        if (!listingService.deleteById(id)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
