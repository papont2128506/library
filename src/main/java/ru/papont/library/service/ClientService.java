package ru.papont.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.papont.library.dto.ClientDto;
import ru.papont.library.entity.Client;
import ru.papont.library.exceptions.ConflictException;
import ru.papont.library.exceptions.NotFoundException;
import ru.papont.library.repository.ClientRepository;

import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {

    final private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(ClientDto dto) {
        Optional<Client> optional = clientRepository.findByEmailAndPhone(dto.getEmail(), dto.getPhone());
        if (optional.isPresent()) {
            throw new ConflictException("Client already exists.");
        }

        Client client = new Client(dto);

        Client saved = clientRepository.save(client);

        log.info("Client {} created", saved);

        return saved;
    }

    public Page<Client> getAll(String query, Pageable pageable) {
        if (query != null) {
            return clientRepository.findByQuery("%" + query.toLowerCase() + "%", pageable);
        }

        return clientRepository.findAll(pageable);
    }

    public Client get(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ClientNotFound"));
    }

    public Client update(Long id, ClientDto dto) {
        Client client = get(id);

        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());

        return clientRepository.save(client);
    }

    public void delete(Long id) {
        Client client = get(id);
        clientRepository.delete(client);
        log.info("Client {} successfully deleted.", client);
    }
}
