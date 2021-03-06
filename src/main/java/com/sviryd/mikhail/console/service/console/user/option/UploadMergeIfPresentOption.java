package com.sviryd.mikhail.console.service.console.user.option;

import com.sviryd.mikhail.console.console.option.Option;
import com.sviryd.mikhail.console.dao.entity.User;
import com.sviryd.mikhail.console.io.user.UserUploader;
import com.sviryd.mikhail.console.service.console.user.cache.IConsoleUsersCacheService;
import com.sviryd.mikhail.console.service.console.user.cache.impl.ConsoleUsersCacheService;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UploadMergeIfPresentOption extends Option {
    private IConsoleUsersCacheService usersService;
    private UserUploader uploader;

    public UploadMergeIfPresentOption(String optionName) {
        super(optionName);
        this.usersService = new ConsoleUsersCacheService();
        this.uploader = new UserUploader();
    }

    public UploadMergeIfPresentOption(String optionName, IConsoleUsersCacheService usersService, UserUploader uploader) {
        super(optionName);
        this.usersService = usersService;
        this.uploader = uploader;
    }

    @Override
    public void process(Scanner scanner) throws Exception {
        final String input = scanner.nextLine();
        File file = new File(input);
        final List<User> users = uploader.upload(file);
        usersService.uploadMergeIfPresent(users);
    }
}
