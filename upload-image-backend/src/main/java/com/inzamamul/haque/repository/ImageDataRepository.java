package com.inzamamul.haque.repository;

import com.inzamamul.haque.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
}
