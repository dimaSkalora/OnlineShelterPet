package com.online.shelter.pet.servlet.repository.jdbc;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcPetRepositoryImpl implements PetRepository {
    private static final RowMapper<Pet> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Pet.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertPet;

    @Autowired
    public JdbcPetRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertPet = new SimpleJdbcInsert(dataSource)
                .withTableName("pets")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Pet save(Pet pet, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", pet.getId())
                .addValue("createdDate", pet.getCreatedDate())
                .addValue("typePet", pet.getTypePet())
                .addValue("namePet", pet.getNamePet())
                .addValue("breed", pet.getBreed())
                .addValue("sex", pet.getSex())
                .addValue("color", pet.getColor())
                .addValue("age", pet.getAge())
                .addValue("growth", pet.getGrowth())
                .addValue("weight", pet.getWeight())
                .addValue("namePerson", pet.getNamePerson())
                .addValue("phone", pet.getPhone())
                .addValue("email", pet.getEmail())
                .addValue("user_id", userId);
        if(pet.isNew()){
            Number newId = insertPet.executeAndReturnKey(map);
            pet.setId(newId.intValue());
        }else {
            if(namedParameterJdbcTemplate.update("UPDATE pets SET typepet=:typePet, namepet=:namePet, breed=:breed," +
                    "sex=:sex,color=:color,age=:age,growth=:growth,weight=:weight,nameperson=:namePerson,phone=:phone,email=:email"+
                    " WHERE id=:id AND user_id=:user_id ",map)==0){
                return null;
            }

        }

        return pet;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM pets WHERE id=? AND  user_id=?",id,userId) != 0;
    }

    @Override
    public Pet get(int id, int userId) {
        List<Pet> pets = jdbcTemplate.query(
                "SELECT * FROM pets WHERE id=? AND  user_id=?",
        ROW_MAPPER,id,userId);
        return (Pet) DataAccessUtils.singleResult(pets);
    }

    @Override
    public List<Pet> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM pets WHERE user_id=? ORDER BY createddate DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Pet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM pets WHERE user_id=?  AND createddate BETWEEN  ? AND ? ORDER BY createddate DESC",
                ROW_MAPPER, userId, startDate, endDate);
    }
}
