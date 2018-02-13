package com.online.shelter.pet.servlet.repository.jdbc;

import com.online.shelter.pet.servlet.Profiles;
import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public abstract class JdbcPetRepositoryImpl<T> implements PetRepository {
    private static final RowMapper<Pet> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Pet.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertPet;

    protected abstract T toDbDateTime(LocalDateTime ldt);

    @Repository
    @Profile(Profiles.POSTGRES_DB)
    public static class Java8JdbcMealRepositoryImpl extends JdbcPetRepositoryImpl<LocalDateTime> {
        @Override
        protected LocalDateTime toDbDateTime(LocalDateTime ldt) {
            return ldt;
        }
    }

    @Repository
    @Profile(Profiles.HSQL_DB)
    public static class TimestampJdbcMealRepositoryImpl extends JdbcPetRepositoryImpl<Timestamp> {
        @Override
        protected Timestamp toDbDateTime(LocalDateTime ldt) {
            return Timestamp.valueOf(ldt);
        }
    }

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.insertPet = new SimpleJdbcInsert(dataSource)
                .withTableName("pets")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Pet save(Pet pet, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", pet.getId())
                .addValue("createdDate", toDbDateTime(pet.getCreatedDate()))
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
                ROW_MAPPER, userId, toDbDateTime(startDate), toDbDateTime(endDate));
    }
}
