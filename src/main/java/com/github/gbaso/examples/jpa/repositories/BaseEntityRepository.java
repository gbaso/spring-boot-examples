package com.github.gbaso.examples.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.gbaso.examples.jpa.entities.BaseEntity;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity>
        extends JpaRepository<T, Long>, ListQuerydslPredicateExecutor<T> {

}
