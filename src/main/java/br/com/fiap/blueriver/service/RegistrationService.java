package br.com.fiap.blueriver.service;

import br.com.fiap.blueriver.entity.Region;
import br.com.fiap.blueriver.entity.User;
import br.com.fiap.blueriver.model.RegistrationDto;
import br.com.fiap.blueriver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository repository;

    public RegistrationDto register(RegistrationDto data){
        var user = new User();
        user.setUsername(data.username);
        user.setPassword(data.password);

        var region = new Region();
        region.setRegion(data.region);
        region.setDeviceId(data.deviceId);
        region.setUser(user);

        user.setRegion(region);

        var entity = repository.save(user);

        data.id = entity.getId().toString();
        return data;
    }

    public RegistrationDto updateRegistration(RegistrationDto data, String id){
        var user = new User();
        user.setId(UUID.fromString(id));
        user.setUsername(data.username);
        user.setPassword(data.password);

        var region = new Region();
        region.setRegion(data.region);
        region.setDeviceId(data.deviceId);

        user.setRegion(region);

        var entity = repository.save(user);

        data.id = entity.getId().toString();
        return data;
    }

    public void deleteRegistration(String id){
        repository.deleteById(UUID.fromString(id));
    }

    public RegistrationDto getRegistration(String id){
        var entityOP = repository.findById(UUID.fromString(id));

        if(entityOP.isEmpty()){
            return null;
        }

        var entity = entityOP.get();
        var dto = new RegistrationDto();

        dto.id = entity.getId().toString();
        dto.username = entity.getUsername();
        dto.password = entity.getPassword();

        dto.region = entity.getRegion().getRegion();
        dto.deviceId = entity.getRegion().getDeviceId();

        return dto;
    }
}
