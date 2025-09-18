package fr.eni.onepiecev4.dal;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

@Repository
public class PersonnageDAOImpl implements PersonnageDAO {

    private final String FIND_ALL = "SELECT * FROM PERSONNAGE";

    private final String FIND_ALL_WITH_USERNAME = "SELECT * FROM PERSONNAGE WHERE SURNOM != ''";

    private final String FIND_BY_NUMBER = "SELECT * FROM PERSONNAGE WHERE id = :id";

    private final String INSERT = "INSERT INTO PERSONNAGE(prenom, nom, surnom, particule, sexe, age, prime) " +
            "VALUES (:prenom, :nom, :surnom, :particule, :sexe, :age, :prime)";

    private final String UPDATE = "UPDATE PERSONNAGE SET prenom = :prenom, nom = :nom, surnom = :surnom, particule = :particule, sexe = :sexe, age = :age, prime = :prime " +
            "WHERE id= :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonnageDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Personnage> consulterListPersonnages() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(FIND_ALL, params, new PersonnageMapper());
    }

    @Override
    public List<Personnage> consulterListPersonnagesAvecPseudo() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(FIND_ALL_WITH_USERNAME, params, new PersonnageMapper());
    }

    @Override
    public Personnage consulterPersonnageParId(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",id);
        return jdbcTemplate.queryForObject(FIND_BY_NUMBER, params, new PersonnageMapper());
    }

    @Override
    public void creerPersonnage(Personnage personnage) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("prenom", personnage.getPrenom());
        namedParameters.addValue("nom", personnage.getNom());
        namedParameters.addValue("surnom", personnage.getSurnom());
        namedParameters.addValue("particule", Character.toString(personnage.getParticule()));
        namedParameters.addValue("sexe", Character.toString(personnage.getSexe()));
        namedParameters.addValue("age", personnage.getAge());
        namedParameters.addValue("prime", personnage.getPrime());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            personnage.setId(keyHolder.getKey().longValue());
        }

    }


    @Override
    public void mettreajourPersonnage(Personnage personnage) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", personnage.getId());
        namedParameters.addValue("prenom", personnage.getPrenom());
        namedParameters.addValue("nom", personnage.getNom());
        namedParameters.addValue("surnom", personnage.getSurnom());
        namedParameters.addValue("particule", Character.toString(personnage.getParticule()));
        namedParameters.addValue("sexe", Character.toString(personnage.getSexe()));
        namedParameters.addValue("age", personnage.getAge());
        namedParameters.addValue("prime", personnage.getPrime());
        jdbcTemplate.update(UPDATE, namedParameters);

    }

    @Override
    public void effacerPersonnage(Personnage personnage) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", personnage.getId());
        String sql = "DELETE FROM Personnage WHERE id = :id";

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<Groupe> consulterListGroupes(Personnage personnage) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", personnage.getId());
        String sql =  "SELECT * FROM GROUPE INNER JOIN ATTRIBUTION ON id = id_groupe WHERE id_personnage = :id";

        return jdbcTemplate.query(sql, params, new GroupeMapper());
    }

    class PersonnageMapper implements RowMapper<Personnage> {
        @Override
        public Personnage mapRow(ResultSet rs, int rowNum) throws SQLException {
            Personnage p = new Personnage();
            p.setId(rs.getLong("id"));
            p.setPrenom(rs.getString("prenom"));
            if (rs.getString("nom") == null) {
                p.setNom(" ");
            } else {
                p.setNom(rs.getString("nom"));
            }
            p.setSurnom(rs.getString("surnom"));
            p.setParticule(rs.getString("particule").charAt(0));
            p.setSexe(rs.getString("sexe").charAt(0));
            p.setAge(rs.getInt("age"));
            p.setPrime(rs.getLong("prime"));

            return p;
        }

    }


    class GroupeMapper implements RowMapper<Groupe> {
        @Override
        public Groupe mapRow(ResultSet rs, int rowNum) throws SQLException {
            Groupe g = new Groupe();

            g.setId(rs.getInt("id"));
            g.setNom(rs.getString("nom_groupe"));

            return g;
        }
    }

}
