package com.np.library.service;

import com.np.library.domain.LibraryUser;

import java.util.List;

public interface LibraryUserService {
    public void saveLibraryUser(LibraryUser libraryUser);
    public List<LibraryUser> getLibraryUsers();
    public LibraryUser getLibraryUserById(Long id);
    public String changePassword(String newPassword,Long id);
}
