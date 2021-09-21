package com.example.listingapp.servicies;

import com.example.listingapp.model.Category;
import com.example.listingapp.repositories.CategoryRepository;
import com.example.listingapp.repositories.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ListingRepository listingRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        Optional<Category> byName = categoryRepository.findByName(category.getName().toUpperCase(Locale.ROOT));
        if (byName.isPresent()) {
            return byName.get();
        }
        category.setName(category.getName().toUpperCase(Locale.ROOT));
        return categoryRepository.save(category);
    }

    public boolean deleteById(int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }
        listingRepository.changeAllListingsCategory(byId.get().getId(), null);
        categoryRepository.deleteById(id);
        return true;
    }
}
