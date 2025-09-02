package fr.eni.onepiecev4.dal;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GroupeDAOImpl implements GroupeDAO {



    private final String FIND_ALL = "SELECT g.id, g.nom_groupe, g.id_capitaine, p.nom, p.prenom, p.particule, p.surnom, p.sexe, p.age, p.prime " +
            "FROM groupe g LEFT JOIN personnage p ON g.id_capitaine = p.id ";

    private final String FIND_BY_ID = "SELECT g.id, g.nom_groupe, g.id_capitaine, p.nom, p.prenom, p.particule, p.surnom, p.sexe, p.age, p.prime  " +
            "FROM groupe g LEFT JOIN personnage p ON g.id_capitaine = p.id " +
            "WHERE g.id = :id";

    private final String INSERT = "INSERT INTO GROUPE(nom_groupe, id_capitaine) " +
            "VALUES (:nom_groupe, :id_capitaine)";

    private final String UPDATE = "UPDATE GROUPE SET nom_groupe = :nom_groupe, id_capitaine = :id_capitaine " +
            "WHERE id= :id";

    private static final String FIND_ALL_CAPITAINE = "SELECT g.id_capitaine, p.nom, p.prenom, p.particule, p.surnom," +
            "p.particule, p.sexe, p.age, p.prime " +
            "FROM groupe g LEFT JOIN personnage p ON g.id_capitaine = p.id ";

    private static final String FIND_CAPITAINE_BY_NUMBER = "SELECT g.id_capitaine, p.nom, p.prenom, p.particule, p.surnom," +
            "p.particule, p.sexe, p.age, p.prime " +
            "FROM groupe g LEFT JOIN personnage p ON g.id_capitaine = p.id "
            + "WHERE g.id = :id";


    private NamedParameterJdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GroupeDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Groupe> consulterGroupeList() {
        MapSqlParameterSource params = new MapSqlParameterSource();

        return jdbcTemplate.query(FIND_ALL, params, new GroupeMapper());
    }

    @Override
    public Groupe consulterGroupeParId(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, params, new GroupeMapper());
    }

    @Override
    public void creerGroupe(Groupe groupe) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_groupe", groupe.getNom());
        namedParameters.addValue("id_capitaine", groupe.getCapitaine().getId());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            groupe.setId(keyHolder.getKey().intValue());
        }

    }

    @Override
    public void mettreajourGroupe(Groupe groupe) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", groupe.getId());
        namedParameters.addValue("nom_groupe", groupe.getNom());
        namedParameters.addValue("id_capitaine", groupe.getCapitaine().getId());

        jdbcTemplate.update(UPDATE, namedParameters);

    }

    @Override
    public void effacerGroupe(Groupe groupe) {
        Integer id = groupe.getId();
        String sql = "DELETE FROM groupe WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void ajouterPersonnageGroupe(Groupe groupe, Personnage personnage) {
        Long id_personnage = personnage.getId();
        Integer id_groupe = groupe.getId();
        String sql = "INSERT INTO ATTRIBUTION(id_personnage, id_groupe) " +
                "VALUES (:id_personnage, :id_groupe)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_personnage", id_personnage);
        params.addValue("id_groupe", id_groupe);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void supprimerPersonnageGroupe(Groupe groupe, Personnage personnage) {
        Long id_personnage = personnage.getId();
        Integer id_groupe = groupe.getId();
        String sql = "DELETE FROM ATTRIBUTION WHERE id_personnage = :id_personnage " +
                "AND id_groupe = :id_groupe";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_personnage", id_personnage);
        params.addValue("id_groupe", id_groupe);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<Personnage> consulterListMembre(Groupe groupe, Groupe groupe1) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", groupe.getId());
        String sql =  "SELECT * FROM PERSONNAGE INNER JOIN ATTRIBUTION ON id = id_personnage WHERE id_groupe = :id ORDER BY id";

        return jdbcTemplate.query(sql, params, new PersonnageMapper());
    }

    class GroupeMapper implements RowMapper<Groupe> {
        @Override
        public Groupe mapRow(ResultSet rs, int rowNum) throws SQLException {
            Groupe g = new Groupe();

            g.setId(rs.getInt("id"));
            g.setNom(rs.getString("nom_groupe"));

            Personnage capitaine = new Personnage();
            capitaine.setId(rs.getInt("id_capitaine"));
                if (rs.getString("nom") == "null")
                {capitaine.setNom(" ");}
                else
                {capitaine.setNom(rs.getString("nom"));}
            capitaine.setPrenom(rs.getString("prenom"));
            capitaine.setSurnom(rs.getString("surnom"));
            capitaine.setParticule(rs.getString("particule").charAt(0));
            capitaine.setSexe(rs.getString("sexe").charAt(0));
            capitaine.setAge(rs.getInt("age"));
            capitaine.setPrime(rs.getInt("prime"));
            g.setCapitaine(capitaine);

            return g;
        }
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

            return p;
        }
    }
}


