package org.personal.salportfoliobackend.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {

    /**
     * A common getById method as all domains will contain an ID.
     * <br /><br />
     * <b>NOTE: At the minimum, the id value will be validated for empty/null
     * value.</b>
     * 
     * @param id The domain ID
     * @return An Optional potentially containing the matching domain.
     */
    Optional<T> getById(String id);
    
    /**
     * A common getAll method to fetch all records of domain. <br /><br />
     * <b>WARNING: </b> This will fetch all the record. Ensure that you know
     * what data you're fetching prior to implementing/invoking this method.
     * 
     * @return All the records for the domain.
     */
    Optional<List<T>> getAll();
    
    /**
     * Inserts a new record based on the passed domain and updates the passed
     * domain's ID with generated value from DB.
     * <br /><br />
     * <b>NOTE: At the minimum, the id value will be validated for empty/null
     * value.</b>
     * @param t The domain to persist.
     * @return The number of affected rows.
     */
    int insert(T domain);
    
    /**
     * Updates a new record based on the passed domain.
     * <br /><br />
     * <b>NOTE: At the minimum, the id value will be validated for empty/null
     * value.</b>
     * @param t The domain to update.
     * @return The number of affected rows.
     */
    int update(T domain);
    
    /**
     * Deletes a record based on the passed ID.
     * 
     * @param t The ID of domain to be deleted.
     * @return The number of affected rows.
     */
    int deleteById(String id);
}
