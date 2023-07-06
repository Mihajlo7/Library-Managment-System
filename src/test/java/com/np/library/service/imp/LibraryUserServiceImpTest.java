package com.np.library.service.imp;

import com.np.library.domain.Library;
import com.np.library.domain.LibraryUser;
import com.np.library.repository.LibraryRepository;
import com.np.library.repository.LibraryUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryUserServiceImpTest {
    @Mock
    private LibraryUserRepository libraryUserRepository;

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryUserServiceImp libraryUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveLibraryValidUserSuccess() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setLibrary(new Library());
        libraryUser.getLibrary().setId(1L);

        when(libraryRepository.findById(libraryUser.getLibrary().getId())).thenReturn(Optional.of(new Library()));
        when(libraryUserRepository.save(libraryUser)).thenReturn(libraryUser);

        libraryUserService.saveLibraryUser(libraryUser);

        verify(libraryUserRepository,times(1)).save(libraryUser);
    }
    @Test
    public void saveLibraryUserInvalidLibraryIdExceptionThrown() {

        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setLibrary(new Library());
        libraryUser.getLibrary().setId(1L);

        when(libraryRepository.findById(libraryUser.getLibrary().getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> {
            libraryUserService.saveLibraryUser(libraryUser);
        });
        verify(libraryUserRepository, never()).save(libraryUser);
    }

    @Test
    void getLibraryUsersSuccess() {
        List<LibraryUser> libraryUsers = new ArrayList<>();
        libraryUsers.add(new LibraryUser());
        libraryUsers.add(new LibraryUser());
        when(libraryUserRepository.findAll()).thenReturn(libraryUsers);

        List<LibraryUser> result = libraryUserService.getLibraryUsers();

        Assertions.assertEquals(libraryUsers.size(), result.size());
        Assertions.assertEquals(libraryUsers, result);
    }
    @Test
    void getLibraryUserByIdSuccess() {
        Long userId = 1L;
        LibraryUser libraryUser = new LibraryUser();
        when(libraryUserRepository.findById(userId)).thenReturn(Optional.of(libraryUser));

        LibraryUser result = libraryUserService.getLibraryUserById(userId);

        Assertions.assertEquals(libraryUser, result);
    }
    @Test
    public void getLibraryUserByIdNonExistingUserExceptionThrown() {
        // Arrange
        Long userId = 1L;
        when(libraryUserRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            libraryUserService.getLibraryUserById(userId);
        });
    }

    @Test
    void changePassword() {
        Long userId = 1L;
        String newPassword = "newPassword";
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setId(userId);

        when(libraryUserRepository.findById(userId)).thenReturn(Optional.of(libraryUser));
        when(libraryUserRepository.save(libraryUser)).thenReturn(libraryUser);

        // Act
        String result = libraryUserService.changePassword(newPassword, userId);

        // Assert
        verify(libraryUserRepository, times(1)).save(libraryUser);
        Assertions.assertEquals(newPassword, result);
    }
    @Test
    public void changePasswordInvalidPasswordExceptionThrown() {

        Long userId = 1L;
        String newPassword = "1234";
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setId(userId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            libraryUserService.changePassword(newPassword, userId);
        });

        verify(libraryUserRepository, never()).save(libraryUser);
    }

    @Test
    public void changePasswordNonExistingUserExceptionThrown() {

        Long userId = 1L;
        String newPassword = "newPassword";
        when(libraryUserRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> {
            libraryUserService.changePassword(newPassword, userId);
        });

        verify(libraryUserRepository, never()).save(any(LibraryUser.class));
    }
}