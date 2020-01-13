package org.personal.salportfoliobackend.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.personal.salportfoliobackend.domain.Asset;
import org.personal.salportfoliobackend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao implements CommonDao<User> {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    RowMapper<User> userMapper = (rs, rowNum) -> {
        // If this is used in a one-to-many situation, there's no guarantee
        // that user will not be null.
        Optional<String> id = Optional.ofNullable(rs.getString("user_id"));
        
        if (id.isPresent()) {
            return new User (
                id.get(),
                rs.getString("first_name"),
                rs.getString("middle_initial"),
                rs.getString("last_name")
            );
        }
        
        return null;
    };
    
    ResultSetExtractor<User> userWithAssetsMapper = rs -> {
        int orderIdx = 0;
        User user = null;
        
        while (rs.next()) {
            if (user == null) {
                user = userMapper.mapRow(rs, orderIdx);
            }
            
            Asset asset = AssetDao.assetMapper.mapRow(rs, orderIdx);
            
            if (asset != null) {
                user.addAsset(asset);
            }
            
            orderIdx++;
        }
        
        return user;
    };
    
    private ResultSetExtractor<List<User>> usersWithAssetsMapper = rs -> {
        List<User> users = new ArrayList<User>();
        User currentUser = null;
        int orderIdx = 0;
        
        while (rs.next()) {
            if (currentUser == null 
                || !currentUser.getId().equals(rs.getString("user_id"))) {
                currentUser = userMapper.mapRow(rs, orderIdx);
                users.add(currentUser);
            }
            
            Asset asset = AssetDao.assetMapper.mapRow(rs, orderIdx);
            
            if (asset != null) {
                currentUser.addAsset(asset);
            }
            
            orderIdx++;
        }
        
        return users;
    };
    
    public Optional<List<User>> getByFirstAndLastName (String firstName, 
        String lastName) {
        
        String sql = "SELECT u.*, a.* FROM user u LEFT OUTER JOIN asset a "
            + "ON u.user_id = a.user_id_fk "
            + "WHERE u.first_name = ? AND u.last_name = ?";
        return Optional.ofNullable(jdbcTemplate.query(sql, 
            new Object[] {firstName,  lastName}, usersWithAssetsMapper));
    }

    @Override
    public Optional<User> getById(String id) {
        String sql = "SELECT u.*, a.* FROM user u LEFT OUTER JOIN asset a "
            + "ON u.user_id = a.user_id_fk "
            + "WHERE u.user_id = ?";
        return Optional.ofNullable(jdbcTemplate.query(sql, 
            new Object[] {id}, userWithAssetsMapper));
    }

    @Override
    public Optional<List<User>> getAll() {
        String sql = "SELECT u.*, a.* FROM user u LEFT OUTER JOIN asset a "
            + "ON u.user_id = a.user_id_fk";
        return Optional.ofNullable(jdbcTemplate.query(sql, 
            usersWithAssetsMapper));
    }

    @Override
    public int insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRow = jdbcTemplate.update(conn -> {
            String sql = "INSERT INTO user (first_name, middle_initial, "
                    + "last_name) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, 
                new String[] {"user_id"});
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getMiddleInitial());
            ps.setString(3, user.getLastName());
            
            return ps;
        }, keyHolder);
        
        String userId = String.valueOf(keyHolder.getKeyList().get(0)
            .get("user_id"));
        user.setId(userId);
        
        return affectedRow;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE user SET first_name = ?, middle_initial = ? "
            + "last_name = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, new Object[] {
            user.getFirstName(), user.getMiddleInitial(), user.getLastName()
            }, new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
    }

    @Override
    public int deleteById(String id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        return jdbcTemplate.update(sql, new Object[] {id}, 
            new int[] {Types.VARCHAR});
    }

}
