package org.example.webbase.command;

import org.example.webbase.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new GoToSignUpPageCommand()),
    GO_TO_CHANGE_PASSWORD(new GoToChangePasswordCommand()),
    GO_TO_GAME(new GoToGame()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    VERIFICATION(new SendEmailCommand()),
    PROFILE(new GoToProfileCommand()),
    DELETE_USER(new DeleteUserCommand()),
    UPLOAD_FILE(new UploadFileCommand()),
    DEFAULT(new DefaultCommand());



    //GO_TO
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr){
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
