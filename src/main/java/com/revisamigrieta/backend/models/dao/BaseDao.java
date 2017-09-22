package com.revisamigrieta.backend.models.dao;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.common.collect.Lists;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;

import java.util.*;

import static com.googlecode.objectify.ObjectifyService.ofy;

public abstract class BaseDao<T> {
	/**
	 * Limit of rows
	 */
	private static final int DEFAULT_LIST_LIMIT = 20;
	/**
	 * T Class
	 */
	protected final Class<T> clazz;

	/**
	 * Constructor for T Class
	 *
	 * @param clazz
	 */
	protected BaseDao(final Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Delete Entity
	 *
	 * @param id
	 */
	public void delete(long id) {
		ofy().delete().type(clazz).id(id).now();
	}

	/**
	 * Get all entity
	 *
	 * @return
	 */
	public List<T> getAll() {
		return query().list();
	}

	/**
	 * Save/Update Entity
	 *
	 * @param object
	 * @return
	 */
	public T put(T object) {
		ofy().save().entity(object).now();
		return object;
	}

	/**
	 * Save/Update Entities collection
	 *
	 * @param entities
	 * @return
	 */
	public Collection<T> put(Iterable<T> entities) {
		return ofy().save().entities(entities).now().values();
	}

	/**
	 * Get entity from key
	 *
	 * @param key
	 * @return
	 */
	public T get(Key<T> key) {
		return ofy().load().key(key).now();
	}

	/**
	 * Get Entities from keys
	 *
	 * @param keys
	 * @return
	 */
	public List<T> get(List<Key<T>> keys) {
		return Lists.newArrayList(ofy().load().keys(keys).values());
	}

	/**
	 * Get Entity from Long id
	 *
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		return query().id(id).now();
	}

	/**
	 * Get Entity from String id
	 *
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return query().id(id).now();
	}

	/**
	 * Get Entity from Entity object
	 *
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return query(entity);
	}

	/**
	 * Get Entities from list id's
	 *
	 * @param ids
	 * @return
	 */
	public List<T> getSubset(Iterable<Long> ids) {
		return new ArrayList<T>(query().ids(ids).values());
	}

	/**
	 * Get Entities from HashMap Ids
	 *
	 * @param ids
	 * @return
	 */
	public Map<Long, T> getSubsetMap(Iterable<Long> ids) {
		return new HashMap<>(query().ids(ids));
	}

	/**
	 * Delete Entity
	 *
	 * @param object
	 */
	public void delete(T object) {
		ofy().delete().entity(object);
	}

	/**
	 * Delete Entities
	 *
	 * @param objects
	 */
	public void delete(List<T> objects) {
		ofy().delete().entities(objects);
	}

	/**
	 * Delete Parent from Entity Id
	 *
	 * @param parent
	 * @param id
	 */
	public void delete(Object parent,
	                   Long id) {
		ofy().delete().type(clazz).parent(parent).id(id).now();
	}

	/**
	 * Get LoadType<T>
	 *
	 * @return LoadType
	 */
	protected LoadType<T> query() {
		return ofy().load().type(clazz);
	}

	/**
	 * Get Entity from Entity object
	 *
	 * @param entity
	 * @return Entity
	 */
	protected T query(T entity) {
		return ofy().load().entity(entity).now();
	}

	/**
	 * Delete all Entities
	 */
	public void deleteAll() {
		List<Key<T>> keys = ofy().load().type(clazz).keys().list();
		ofy().delete().entities(keys).now();
	}

	/**
	 * Query for filters
	 *
	 * @param filters
	 * @return List
	 */
	public ArrayList<T> getWithFilters(ArrayList<Query.Filter> filters) {
		Query.Filter validFilter = null;
		if (filters != null && filters.size() > 0) {
			if (filters.size() == 1)
				validFilter = filters.get(0);
			else
				validFilter = Query.CompositeFilterOperator.and(filters);
		} else {
			throw new IllegalArgumentException("At least one or more filters are required.");
		}
		ArrayList<T> bgfyList = new ArrayList<>();
		QueryResultIterator<T> resultIterator = query().filter(validFilter).iterator();
		while (resultIterator.hasNext()) {
			bgfyList.add(resultIterator.next());
		}
		return bgfyList;
	}

	/**
	 * Query for filters and pagination
	 *
	 * @param limit
	 * @param cursor
	 * @param filters
	 * @return CollectionResponse
	 */
	public CollectionResponse<T> getCollectionResponse(Integer limit, String cursor, List<Query.Filter> filters) {
		limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
		com.googlecode.objectify.cmd.Query<T> query = null;
		if (filters != null && filters.size() > 0) {
			Query.Filter validFilter = null;

			if (filters.size() == 1)
				validFilter = filters.get(0);
			else
				validFilter = Query.CompositeFilterOperator.and(filters);

			query = query().filter(validFilter).limit(limit);
		} else {
			query = query().limit(limit);
		}
		if (cursor != null) {
			query = query.startAt(Cursor.fromWebSafeString(cursor));
		}
		QueryResultIterator<T> queryIterator = query.iterator();
		List<T> bgfyList = new ArrayList<T>(limit);
		while (queryIterator.hasNext()) {
			bgfyList.add(queryIterator.next());
		}
		return CollectionResponse.<T>builder().setItems(bgfyList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
	}
}