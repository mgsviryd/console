package com.sviryd.mikhail.service.consoleUser.option;

import com.sviryd.mikhail.console.option.Option;
import com.sviryd.mikhail.console.exception.OptionException;
import com.sviryd.mikhail.io.UserWriter;
import com.sviryd.mikhail.service.consoleUser.ConsoleUsersService;
import com.sviryd.mikhail.service.consoleUser.IConsoleUsersService;

import java.io.File;
import java.util.Scanner;

public class UploadUsersRewriteOldFileOption extends Option {
    private IConsoleUsersService service;
    private UserWriter writer;

    public UploadUsersRewriteOldFileOption(String optionName) {
        super(optionName);
        this.service = new ConsoleUsersService();
        this.writer = new UserWriter();
    }

    public UploadUsersRewriteOldFileOption(String optionName, IConsoleUsersService service, UserWriter writer) {
        super(optionName);
        this.service = service;
        this.writer = writer;
    }
    @Override
    public void process(Scanner scanner) throws Exception {
        final String input = scanner.nextLine();
        File file = new File(input);
        if (!file.exists()) {
            throw new OptionException("The file '" + file + "' does not exist.");
        } else {
            try {
                writer.rewrite(file, service.findAll());
            } catch (Exception e) {
                throw new OptionException("Failed. " + e.getMessage());
            }
        }
    }
}