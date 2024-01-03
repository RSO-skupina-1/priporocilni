package si.fri.rso.priporocilni.models.converters;

import si.fri.rso.priporocilni.lib.Priporocilni;
import si.fri.rso.priporocilni.lib.Priporocilni;
import si.fri.rso.priporocilni.models.entities.PriporocilniEntity;

public class PriporocilniConverter {

    public static Priporocilni toDto(PriporocilniEntity entity) {

        Priporocilni dto = new Priporocilni();
        dto.setId(entity.getId());
        dto.setUstvarjen(entity.getUstvarjen());
        dto.setKomentar(entity.getKomentar());
        dto.setOcena(entity.getOcena());
        dto.setLokacija_id(entity.getLokacija_id());
        dto.setUser_id(entity.getUser_id());



        return dto;

    }

    public static PriporocilniEntity toEntity(Priporocilni dto) {

        PriporocilniEntity entity = new PriporocilniEntity();
        entity.setUstvarjen(dto.getUstvarjen());
        entity.setKomentar(dto.getKomentar());
        entity.setOcena(dto.getOcena());
        entity.setLokacija_id(dto.getLokacija_id());
        entity.setUser_id(dto.getUser_id());


        return entity;

    }

}
