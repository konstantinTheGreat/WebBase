package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.entity.User;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.example.webbase.constant.PagesConstants.*;

public class UploadFileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(USER);
        String username = user.getUsername();

        try {
            Part filePart = request.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            Path filePath = Paths.get("/Users/user/Desktop/java4thsem/WebBase/src/main/webapp/uploads", fileName);

            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            String relativePath = "uploads/" + fileName;

            session.setAttribute("imagePath", relativePath);
            userService.uploadFile(relativePath, username);

        } catch (ServletException | IOException | ServiceException e) {
            throw new CommandException(e);
        }

        return PROFILE_PAGE;
    }
}
