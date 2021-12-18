package ru.papont.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.papont.library.dto.ClientDetailsDto;
import ru.papont.library.dto.ClientDto;
import ru.papont.library.dto.ClientSmallDto;
import ru.papont.library.entity.Client;
import ru.papont.library.mapper.ClientMapper;
import ru.papont.library.service.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping("/client")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody ClientDto dto) {
        return clientService.create(dto);
    }

    @GetMapping("/client")
    public Page<ClientSmallDto> getAllClients(@RequestParam Integer page,
                                              @RequestParam Integer size,
                                              @RequestParam(required = false) String query) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Client> clients = clientService.getAll(query, pageable);

        List<ClientSmallDto> dtos = clients.stream()
                .map(clientMapper::toSmall)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, clients.getTotalElements());
    }

    @GetMapping("/client/{id}")
    public ClientDetailsDto getClient(@PathVariable Long id) {
        return clientMapper.toDetails(clientService.get(id));
    }

    @PutMapping("/client/{id}")
    public ClientDetailsDto editClient(@PathVariable Long id,
                                       @Valid @RequestBody ClientDto dto) {
        return clientMapper.toDetails(clientService.update(id, dto));
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }

}
