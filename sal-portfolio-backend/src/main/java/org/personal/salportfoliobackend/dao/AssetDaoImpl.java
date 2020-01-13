package org.personal.salportfoliobackend.dao;

import java.util.List;
import java.util.Optional;

import org.personal.salportfoliobackend.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AssetDaoImpl implements CommonDao<Asset> {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    static RowMapper<Asset> assetMapper = (rs, rowNum) -> {
        // If this is used in a one-to-many situation, there's no guarantee
        // that user will not be null.
        Optional<String> id = Optional.ofNullable(rs.getString("asset_id"));
        
        if (id.isPresent()) {
            return new Asset (
                id.get(),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("serial_number")
            );
        }
        
        return null;
    };

    @Override
    public Optional<Asset> getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<List<Asset>> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insert(Asset asset) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Asset asset) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteById(String id) {
        // TODO Auto-generated method stub
        return 0;
    }

}
