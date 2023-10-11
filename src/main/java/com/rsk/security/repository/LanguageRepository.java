package com.rsk.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsk.security.entities.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
	
	Language findByKeyAndLocale(String key, String locale);

}