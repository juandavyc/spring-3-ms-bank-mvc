package com.juandavyc.clients.service;

import com.juandavyc.core.exceptions.IdentificationAlreadyExistsException;
import com.juandavyc.core.exceptions.PhoneNumberAlreadyExistsException;
import com.juandavyc.core.exceptions.ResourceNotFoundException;
import com.juandavyc.clients.application.dto.ClientCommand;
import com.juandavyc.clients.application.dto.ClientResponse;
import com.juandavyc.clients.application.mapper.ClientApplicationMapper;
import com.juandavyc.clients.application.mapper.ClientApplicationUpdateMapper;
import com.juandavyc.clients.application.service.ClientServiceImpl;
import com.juandavyc.clients.application.usecases.PasswordHasher;
import com.juandavyc.clients.domain.model.Client;
import com.juandavyc.clients.domain.model.enums.ClientStatus;
import com.juandavyc.clients.domain.model.enums.GenderType;
import com.juandavyc.clients.domain.port.ClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl underTest;

    @Mock
    private ClientPort clientPort;

    @Mock
    private ClientApplicationMapper clientMapper;

    @Mock
    private ClientApplicationUpdateMapper updateMapper;

    @Mock
    private PasswordHasher passwordHasher;

    @Captor
    private ArgumentCaptor<Client> clientCaptor;

    @BeforeEach
    void setUp() {
        underTest = new ClientServiceImpl(
                clientPort,
                clientMapper,
                updateMapper,
                passwordHasher
        );
    }

    @Test
    @DisplayName("Debe crear un cliente correctamente con todos los campos de Person")
    void create_ShouldCreateClientSuccessfully() {

        ClientCommand command = new ClientCommand();
        command.setFullName("Jose Lema");
        command.setIdentification("123456789");
        command.setPassword("1234");
        command.setAge(30);

        // El objeto de dominio que el mapper devolvería
        Client domainClient = new Client(
                null,
                "Jose Lema",
                GenderType.MALE,
                30,
                "123456789",
                "Otavalo sn y principal",
                "098254785",
                "1234",
                ClientStatus.ACTIVE
        );

        Client savedClient = new Client(
                UUID.randomUUID(),
                "Jose Lema",
                GenderType.MALE,
                30,
                "123456789",
                "Otavalo sn y principal",
                "098254785",
                "1234",
                ClientStatus.ACTIVE
        );

        ClientResponse expectedResponse = new ClientResponse();
        expectedResponse.setFullName("Jose Lema");

        when(clientMapper.toDomain(command)).thenReturn(domainClient);
        when(clientPort.save(any(Client.class))).thenReturn(savedClient);
        when(clientMapper.toResponse(savedClient)).thenReturn(expectedResponse);

        ClientResponse result = underTest.create(command);

        assertThat(result).isNotNull();
        assertThat(result.getFullName()).isEqualTo("Jose Lema");

        verify(clientPort).save(clientCaptor.capture());
        Client capturedClient = clientCaptor.getValue();

        assertThat(capturedClient.getFullName()).isEqualTo("Jose Lema");
        assertThat(capturedClient.getAge()).isEqualTo(30);
        assertThat(capturedClient.getIdentification()).isEqualTo("123456789");

    }

    @Test
    @DisplayName("Debe fallar si el número de identificación ya está registrado")
    void create_ShouldThrowException_WhenIdentificationExists() {

        ClientCommand command = new ClientCommand();
        command.setIdentification("123456789");

        when(clientPort.existByIdentification("123456789")).thenReturn(true);

        assertThatThrownBy(() -> underTest.create(command))
                .isInstanceOf(IdentificationAlreadyExistsException.class);

        verify(clientPort, never()).save(any());
    }

    @Test
    @DisplayName("Debe fallar si el número de celular ya está registrado")
    void create_ShouldThrowException_WhenPhoneNumberExists() {

        ClientCommand command = new ClientCommand();
        command.setPhoneNumber("123456789");

        when(clientPort.existByPhoneNumber("123456789")).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> underTest.create(command))
                .isInstanceOf(PhoneNumberAlreadyExistsException.class);

        verify(clientPort, never()).save(any());
    }


    @Test
    @DisplayName("Debe actualizar un cliente exitosamente cuando los datos son válidos")
    void update_ShouldUpdateClientSuccessfully() {
        UUID clientId = UUID.randomUUID();
        ClientCommand command = new ClientCommand();
        command.setPhoneNumber("0987654321");
        command.setPassword("newSecurePass123");

        Client existingClient = new Client(clientId, "Original Name", GenderType.MALE, 30,
                "12345", "Original Addr", "0000000000", "oldPass", ClientStatus.ACTIVE);

        ClientResponse expectedResponse = new ClientResponse();
        expectedResponse.setFullName("Original Name");

        when(clientPort.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientPort.existsByPhoneNumberAndIdNot(command.getPhoneNumber(), clientId)).thenReturn(false);
        when(passwordHasher.hash(command.getPassword())).thenReturn("hashed_password_123");
        when(clientPort.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(clientMapper.toResponse(any(Client.class))).thenReturn(expectedResponse);

        ClientResponse result = underTest.update(clientId, command);

        assertThat(result).isNotNull();

        verify(clientPort).findById(clientId);
        verify(clientPort).existsByPhoneNumberAndIdNot(command.getPhoneNumber(), clientId);

        verify(updateMapper).updateFromCommand(command, existingClient);

        verify(clientPort).save(clientCaptor.capture());

    }

    @Test
    @DisplayName("Debe desactivar un cliente correctamente (borrado lógico)")
    void deleteById_ShouldDeactivateClientSuccessfully() {
        UUID clientId = UUID.randomUUID();

        Client existingClient = new Client(
                clientId,
                "Jose Lema",
                GenderType.MALE,
                30,
                "123456789",
                "Direccion",
                "098254785",
                "1234",
                ClientStatus.ACTIVE
        );

        when(clientPort.findById(clientId)).thenReturn(Optional.of(existingClient));

        when(clientPort.save(any(Client.class))).thenReturn(existingClient);

        underTest.deleteById(clientId);

        verify(clientPort).findById(clientId);

        verify(clientPort).save(clientCaptor.capture());
        Client capturedClient = clientCaptor.getValue();
        assertThat(capturedClient.getStatus()).isEqualTo(ClientStatus.INACTIVE);

    }

    @Test
    @DisplayName("Debe lanzar ResourceNotFoundException al intentar eliminar un cliente inexistente")
    void deleteById_ShouldThrowException_WhenClientNotFound() {
        UUID clientId = UUID.randomUUID();
        when(clientPort.findById(clientId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteById(clientId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Client")
                .hasMessageContaining(clientId.toString());

        verify(clientPort, never()).save(any());
    }

}
