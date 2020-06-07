package com.lab.jobportal.users;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("ApplicationUserRepository")
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

	private List<ApplicationUsers> appUsers = new LinkedList<ApplicationUsers>();
	
	@Override
	public List<ApplicationUsers> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApplicationUsers> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApplicationUsers> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ApplicationUsers> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends ApplicationUsers> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<ApplicationUsers> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ApplicationUsers getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ApplicationUsers> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ApplicationUsers> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ApplicationUsers> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ApplicationUsers> S save(S entity) {
		appUsers.add(entity);
		return null;
	}

	@Override
	public Optional<ApplicationUsers> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ApplicationUsers entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends ApplicationUsers> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends ApplicationUsers> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ApplicationUsers> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ApplicationUsers> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends ApplicationUsers> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ApplicationUsers findByUsername(String username) {
		Assert.notNull(username, "username cannot be is null");
		List<ApplicationUsers> userFound = appUsers.stream().filter((aUser) -> {
			return aUser.getUsername().equalsIgnoreCase(username);
		}).collect(Collectors.toList());

		if (!userFound.isEmpty())
			return userFound.stream().findFirst().get();
		return null;

	}

}
