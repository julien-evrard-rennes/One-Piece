package fr.eni.onepiecev4.ihm.converter;

import fr.eni.onepiecev4.bll.PersonnageService;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonnageConverter implements Converter<String, Personnage> {
    PersonnageService personnageService;

    public PersonnageConverter(PersonnageService personnageService) {
        this.personnageService = personnageService;
    }

    @Override
    public Personnage convert(String idString) {
        long id = Long.parseLong(idString);
        return personnageService.consulterPersonnageParId(id);
    }
}
