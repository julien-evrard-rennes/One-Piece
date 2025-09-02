package fr.eni.onepiecev4.ihm.converter;

import fr.eni.onepiecev4.bll.GroupeService;
import fr.eni.onepiecev4.bo.Groupe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupeConverter implements Converter<String, Groupe> {
    GroupeService groupeService;

    public GroupeConverter(GroupeService groupeService) {
        this.groupeService = groupeService;
    }



    @Override
    public Groupe convert(String idString) {
        Integer id = Integer.parseInt(idString);
        return groupeService.consulterGroupeParId(id);
    }



}
