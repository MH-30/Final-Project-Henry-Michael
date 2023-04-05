package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Shirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShirtRepository extends JpaRepository<Shirt, Integer> {
    List<Shirt> findByColor (String color);

    List<Shirt> findBySize (String size);

}
