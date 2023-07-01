package com.np.library.service.imp;

import com.np.library.domain.Library;
import com.np.library.domain.LibraryUser;
import com.np.library.domain.User;
import com.np.library.repository.LibraryRepository;
import com.np.library.repository.LibraryUserRepository;
import com.np.library.repository.UserRepository;
import com.np.library.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryUserServiceImp implements LibraryUserService {
    @Autowired
    private LibraryUserRepository libraryUserRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public void saveLibraryUser(LibraryUser libraryUser) {
        if(libraryUser==null){
            throw new IllegalArgumentException("The library user must not be a null");
        }
        Optional<Library> libraryOptional=libraryRepository.findById(libraryUser.getLibrary().getId());
        if(!libraryOptional.isPresent()){
            throw new RuntimeException("Library id doesn exist");
        }
        libraryUserRepository.save(libraryUser);
    }

    @Override
    public List<LibraryUser> getLibraryUsers() {
        return libraryUserRepository.findAll();
    }

    @Override
    public LibraryUser getLibraryUserById(Long id) {
        Optional<LibraryUser> libraryUser=libraryUserRepository.findById(id);
        if(!libraryUser.isPresent()){
            throw new RuntimeException("The Library user with this id does not exist");
        }
        return libraryUser.get();
    }

    @Override
    public String changePassword(String newPassword,Long id) {

        if(newPassword==null || newPassword.length()<5){
            throw new IllegalArgumentException("A new password invalid format");
        }
        Optional<LibraryUser> libraryUser=libraryUserRepository.findById(id);
        if(!libraryUser.isPresent()){
            throw new RuntimeException("The library user with this id does not exist");
        }
        LibraryUser user=libraryUser.get();
        user.setPassword(newPassword);
        libraryUserRepository.save(user);
        return newPassword;
    }
}
