package com.sviryd.mikhail.console.service.console.user.option;

import com.sviryd.mikhail.console.console.option.Option;
import com.sviryd.mikhail.console.dao.entity.User;
import com.sviryd.mikhail.console.service.console.user.cache.IConsoleUserCacheService;
import com.sviryd.mikhail.console.service.console.user.cache.impl.ConsoleUserCacheService;
import com.sviryd.mikhail.console.service.validation.console.user.EmailPatternValidationService;
import com.sviryd.mikhail.console.service.validation.exception.ValidationException;

import java.util.Scanner;

public class EmailOption extends Option {
    private IConsoleUserCacheService userService;
    private EmailPatternValidationService validationService;

    public EmailOption(String optionName) {
        super(optionName);
        this.userService = new ConsoleUserCacheService();
        this.validationService = new EmailPatternValidationService();
    }

    public EmailOption(String optionName, IConsoleUserCacheService userService, EmailPatternValidationService validationService) {
        super(optionName);
        this.userService = userService;
        this.validationService = validationService;
    }

    @Override
    public void process(Scanner scanner) throws ValidationException {
        final String email = scanner.nextLine();
        User user = userService.getUser();
        validationService.validate(email);
        user.setEmail(email);
    }
}
